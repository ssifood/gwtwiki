package info.bliki.wiki.impl;

import info.bliki.api.creator.ImageData;
import info.bliki.api.creator.TopicData;
import info.bliki.api.creator.WikiDB;
import info.bliki.htmlcleaner.TagNode;
import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.filter.WikipediaParser;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.ImageFormat;
import info.bliki.wiki.model.WikiModel;
import info.bliki.wiki.namespaces.INamespace;
import info.bliki.wiki.tags.WPATag;

import java.io.File;
import java.util.Locale;
import java.util.Map;

public class DumpWikiModel extends WikiModel {
	private Siteinfo fSiteinfo;
	private WikiDB fWikiDB;
	private final String fTemplateNamespace;

	private final String fImageDirectoryName;
	static {
		TagNode.addAllowedAttribute("style");
	}

	/**
	 * 
	 * 
	 * @param wikiDB
	 *          a wiki database to retrieve already cached templates
	 * @param imageBaseURL
	 *          a url string which must contains a &quot;${image}&quot; variable
	 *          which will be replaced by the image name, to create links to
	 *          images.
	 * @param linkBaseURL
	 *          a url string which must contains a &quot;${title}&quot; variable
	 *          which will be replaced by the topic title, to create links to
	 *          other wiki topics.
	 * @param imageDirectoryName
	 *          a directory for storing downloaded Wikipedia images. The directory
	 *          must already exist.
	 */
	public DumpWikiModel(WikiDB wikiDB, Siteinfo siteinfo, String imageBaseURL, String linkBaseURL, String imageDirectoryName) {
		this(wikiDB, siteinfo, Locale.ENGLISH, imageBaseURL, linkBaseURL, imageDirectoryName);
	}

	/**
	 * 
	 * @param wikiDB
	 *          a wiki database to retrieve already cached templates
	 * @param locale
	 *          a locale for loading language specific resources
	 * @param imageBaseURL
	 *          a url string which must contains a &quot;${image}&quot; variable
	 *          which will be replaced by the image name, to create links to
	 *          images.
	 * @param linkBaseURL
	 *          a url string which must contains a &quot;${title}&quot; variable
	 *          which will be replaced by the topic title, to create links to
	 *          other wiki topics.
	 * @param imageDirectoryName
	 *          a directory for storing downloaded Wikipedia images. The directory
	 *          must already exist.
	 */
	public DumpWikiModel(WikiDB wikiDB, Siteinfo siteinfo, Locale locale, String imageBaseURL, String linkBaseURL,
			String imageDirectoryName) {
		super(Configuration.DEFAULT_CONFIGURATION, locale, imageBaseURL, linkBaseURL);
		fWikiDB = wikiDB;
		fSiteinfo = siteinfo;
		fTemplateNamespace = fSiteinfo.getNamespace(INamespace.TEMPLATE_NAMESPACE_KEY);
		if (imageDirectoryName != null) {
			if (imageDirectoryName.charAt(imageDirectoryName.length() - 1) == '/') {
				fImageDirectoryName = imageDirectoryName;
			} else {
				fImageDirectoryName = imageDirectoryName + "/";
			}
			File file = new File(fImageDirectoryName);
			if (!file.exists()) {
				file.mkdir();
			}
		} else {
			fImageDirectoryName = null;
		}
	}

	/**
	 * Get the raw wiki text for the given namespace and article name. This model
	 * implementation uses a Derby database to cache downloaded wiki template
	 * texts.
	 * 
	 * @param namespace
	 *          the namespace of this article
	 * @param templateName
	 *          the name of the template
	 * @param templateParameters
	 *          if the namespace is the <b>Template</b> namespace, the current
	 *          template parameters are stored as <code>String</code>s in this map
	 * 
	 * @return <code>null</code> if no content was found
	 * 
	 * @see info.bliki.api.User#queryContent(String[])
	 */
	@Override
	public String getRawWikiContent(String namespace, String articleName, Map<String, String> templateParameters) {
		String result = super.getRawWikiContent(namespace, articleName, templateParameters);
		if (result != null) {
			// found magic word template
			return result;
		}
		if (namespace.equals(fTemplateNamespace)) {
			String name = articleName;
			if (fSiteinfo.getCharacterCase().equals("first-letter")) {
				// first character as uppercase
				name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
			}

			String content = null;
			try {
				TopicData topicData = fWikiDB.selectTopic(fTemplateNamespace + ":" + name);
				if (topicData != null) {
					content = topicData.getContent();
					content = getRedirectedWikiContent(content, templateParameters);
					if (content != null) {
						return content.length() == 0 ? null : content;
					} else {
						return null;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return content;
		}
		return null;
	}

	public String getRedirectedWikiContent(String rawWikitext, Map<String, String> templateParameters) {
		if (rawWikitext.length() < 9) {
			// less than "#REDIRECT" string
			return rawWikitext;
		}
		String redirectedLink = WikipediaParser.parseRedirect(rawWikitext, this);
		if (redirectedLink != null) {
			String redirNamespace = "";
			String redirArticle = redirectedLink;
			int index = redirectedLink.indexOf(":");
			if (index > 0) {
				redirNamespace = redirectedLink.substring(0, index);
				if (isNamespace(redirNamespace)) {
					redirArticle = redirectedLink.substring(index + 1);
				} else {
					redirNamespace = "";
				}
			}
			try {
				int level = incrementRecursionLevel();
				if (level > Configuration.PARSER_RECURSION_LIMIT) {
					return "Error - getting content of redirected link: " + redirNamespace + ":" + redirArticle;
				}
				return getRawWikiContent(redirNamespace, redirArticle, templateParameters);
			} finally {
				decrementRecursionLevel();
			}
		}
		return rawWikitext;
	}

	public void appendInternalImageLink(String hrefImageLink, String srcImageLink, ImageFormat imageFormat) {
		try {
			String imageName = imageFormat.getFilename();
			ImageData imageData = fWikiDB.selectImage(imageName);
			if (imageData != null) {
				File file = new File(imageData.getFilename());
				if (file.exists()) {
					super.appendInternalImageLink(hrefImageLink, "file:///" + imageData.getFilename(), imageFormat);
					return;
				}
			}
			String imageNameURL = Encoder.encodeTitleLocalUrl(imageName);
			super.appendInternalImageLink(hrefImageLink, "file:///" + fImageDirectoryName + imageNameURL, imageFormat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void appendInternalLink(String topic, String hashSection, String topicDescription, String cssClass, boolean parseRecursive) {
		WPATag aTagNode = new WPATag();
		// append(aTagNode);
		aTagNode.addAttribute("id", "w", true);
		String titleURL = Encoder.encodeTitleLocalUrl(topic);
		String href = titleURL + ".html";
		if (hashSection != null) {
			href = href + '#' + hashSection;
		}
		aTagNode.addAttribute("href", href, true);
		if (cssClass != null) {
			aTagNode.addAttribute("class", cssClass, true);
		}
		aTagNode.addObjectAttribute("wikilink", topic);
		pushNode(aTagNode);
		WikipediaParser.parseRecursive(topicDescription.trim(), this, false, true);
		popNode();
		// ContentToken text = new ContentToken(topicDescription);
		// aTagNode.addChild(text);
	}

	public void parseInternalImageLink(String imageNamespace, String rawImageLink) {
		if (fExternalImageBaseURL != null) {
			String imageHref = fExternalWikiBaseURL;
			String imageSrc = fExternalImageBaseURL;
			ImageFormat imageFormat = ImageFormat.getImageFormat(rawImageLink, imageNamespace);

			String imageName = imageFormat.getFilename();
			// String sizeStr = imageFormat.getSizeStr();
			// if (sizeStr != null) {
			// imageName = sizeStr + '-' + imageName;
			// }
			// if (imageName.endsWith(".svg")) {
			// imageName += ".png";
			// }
			imageName = Encoder.encodeUrl(imageName);
			// if (replaceColon()) {
			// imageName = imageName.replaceAll(":", "/");
			// }
			if (replaceColon()) {
				imageHref = imageHref.replace("${title}", imageNamespace + '/' + imageName);
				imageSrc = imageSrc.replace("${image}", imageName);
			} else {
				imageHref = imageHref.replace("${title}", imageNamespace + ':' + imageName);
				imageSrc = imageSrc.replace("${image}", imageName);
			}
			appendInternalImageLink(imageHref, imageSrc, imageFormat);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.bliki.wiki.model.AbstractWikiModel#getCategoryNamespace()
	 */
	@Override
	public String getCategoryNamespace() {
		return fSiteinfo.getNamespace(INamespace.CATEGORY_NAMESPACE_KEY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.bliki.wiki.model.AbstractWikiModel#getImageNamespace()
	 */
	@Override
	public String getImageNamespace() {
		return fSiteinfo.getNamespace(INamespace.FILE_NAMESPACE_KEY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.bliki.wiki.model.AbstractWikiModel#getTemplateNamespace()
	 */
	@Override
	public String getTemplateNamespace() {
		return fSiteinfo.getNamespace(INamespace.TEMPLATE_NAMESPACE_KEY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.bliki.wiki.model.AbstractWikiModel#isCategoryNamespace(java.lang.String
	 * )
	 */
	@Override
	public boolean isCategoryNamespace(String namespace) {
		return INamespace.CATEGORY_NAMESPACE_KEY.equals(fSiteinfo.getIntegerNamespace(namespace));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.bliki.wiki.model.AbstractWikiModel#isImageNamespace(java.lang.String)
	 */
	@Override
	public boolean isImageNamespace(String namespace) {
		return INamespace.FILE_NAMESPACE_KEY.equals(fSiteinfo.getIntegerNamespace(namespace));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.bliki.wiki.model.AbstractWikiModel#isNamespace(java.lang.String)
	 */
	@Override
	public boolean isNamespace(String namespace) {
		return fSiteinfo.getIntegerNamespace(namespace) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.bliki.wiki.model.AbstractWikiModel#isTemplateNamespace(java.lang.String
	 * )
	 */
	@Override
	public boolean isTemplateNamespace(String namespace) {
		return INamespace.TEMPLATE_NAMESPACE_KEY.equals(fSiteinfo.getIntegerNamespace(namespace));
	}

}
