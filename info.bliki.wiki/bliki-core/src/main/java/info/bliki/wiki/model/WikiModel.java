package info.bliki.wiki.model;

import info.bliki.htmlcleaner.ContentToken;
import info.bliki.htmlcleaner.TagToken;
import info.bliki.wiki.filter.Encoder;
import info.bliki.wiki.filter.HTMLConverter;
import info.bliki.wiki.filter.ITextConverter;
import info.bliki.wiki.filter.WikipediaParser;
import info.bliki.wiki.namespaces.INamespace;
import info.bliki.wiki.tags.PTag;
import info.bliki.wiki.tags.WPATag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Standard model implementation
 * 
 */
public class WikiModel extends AbstractWikiModel {
	/**
	 * A map for categories and their associated sort keys
	 */
	protected Map<String, String> categories = null;

	protected Set<String> links = null;

	protected Set<String> templates = null;

	protected List<SemanticRelation> semanticRelations = null;

	protected List<SemanticAttribute> semanticAttributes = null;

	private String fExternalImageBaseURL;

	private String fExternalWikiBaseURL;

	/**
	 * 
	 * @param imageBaseURL
	 *          a url string which must contains a &quot;${image}&quot; variable
	 *          which will be replaced by the image name, to create links to
	 *          images.
	 * @param linkBaseURL
	 *          a url string which must contains a &quot;${title}&quot; variable
	 *          which will be replaced by the topic title, to create links to
	 *          other wiki topics.
	 */
	public WikiModel(String imageBaseURL, String linkBaseURL) {
		this(Configuration.DEFAULT_CONFIGURATION, imageBaseURL, linkBaseURL);
	}

	public WikiModel(Configuration configuration, String imageBaseURL, String linkBaseURL) {
		super(configuration);
		fExternalImageBaseURL = imageBaseURL;
		fExternalWikiBaseURL = linkBaseURL;
	}

	public WikiModel(Configuration configuration, Locale locale, String imageBaseURL, String linkBaseURL) {
		super(configuration, locale);
		fExternalImageBaseURL = imageBaseURL;
		fExternalWikiBaseURL = linkBaseURL;
	}

	public WikiModel(Configuration configuration, ResourceBundle resourceBundle, INamespace namespace, String imageBaseURL,
			String linkBaseURL) {
		super(configuration, resourceBundle, namespace);
		fExternalImageBaseURL = imageBaseURL;
		fExternalWikiBaseURL = linkBaseURL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addCategory(String categoryName, String sortKey) {
		categories.put(categoryName, sortKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLink(String topicName) {
		links.add(topicName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addSemanticAttribute(String attribute, String attributeValue) {
		if (semanticAttributes == null) {
			semanticAttributes = new ArrayList<SemanticAttribute>();
		}
		semanticAttributes.add(new SemanticAttribute(attribute, attributeValue));
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addSemanticRelation(String relation, String relationValue) {
		if (semanticRelations == null) {
			semanticRelations = new ArrayList<SemanticRelation>();
		}
		semanticRelations.add(new SemanticRelation(relation, relationValue));
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTemplate(String template) {
		templates.add(template);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void appendInternalLink(String topic, String hashSection, String topicDescription, String cssClass, boolean parseRecursive) {
		String hrefLink;
		String description = topicDescription;
		WPATag aTagNode = new WPATag();
		if (topic.length() > 0) {
			aTagNode.addAttribute("title", topic, true);
			String encodedtopic = encodeTitleToUrl(topic, true);
			if (replaceColon()) {
				encodedtopic = encodedtopic.replace(':', '/');
			}
			hrefLink = getWikiBaseURL().replace("${title}", encodedtopic);
		} else {
			if (hashSection != null) {
				hrefLink = "";
				if (description.length() == 0) {
					description = "&#35;" + hashSection; // #....
				}
			} else {
				hrefLink = getWikiBaseURL().replace("${title}", "");
			}
		}

		String href = hrefLink;
		if (hashSection != null) {
			href = href + '#' + encodeTitleDotUrl(hashSection, true);
		}
		aTagNode.addAttribute("href", href, true);
		if (cssClass != null) {
			aTagNode.addAttribute("class", cssClass, true);
		}
		aTagNode.addObjectAttribute("wikilink", topic);

		pushNode(aTagNode);
		if (parseRecursive) {
			WikipediaParser.parseRecursive(description.trim(), this, false, true);
		} else {
			aTagNode.addChild(new ContentToken(description));
		}
		popNode();
	}

	/**
	 * Get the set of Wikipedia category names used in this text
	 * 
	 * @return the set of category strings
	 */
	public Map<String, String> getCategories() {
		return categories;
	}

	/**
	 * Get the set of Wikipedia links used in this text
	 * 
	 * @return the set of category strings
	 */
	public Set<String> getLinks() {
		return links;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SemanticAttribute> getSemanticAttributes() {
		return semanticAttributes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SemanticRelation> getSemanticRelations() {
		return semanticRelations;
	}

	/**
	 * {@inheritDoc}
	 */
	public Set<String> getTemplates() {
		return templates;
	}

	/**
	 * Append the internal wiki image link to this model.
	 * 
	 * <br/>
	 * <br/>
	 * <b>Note</b>: the pipe symbol (i.e. &quot;|&quot;) splits the
	 * <code>rawImageLink</code> into different segments. The first segment is
	 * used as the <code>&lt;image-name&gt;</code> and typically ends with
	 * extensions like <code>.png</code>, <code>.gif</code>, <code>.jpg</code> or
	 * <code>.jpeg</code>.
	 * 
	 * <br/>
	 * <br/>
	 * <b>Note</b>: if the image link contains a "width" attribute, the filename
	 * is constructed as <code>&lt;size&gt;px-&lt;image-name&gt;</code>, otherwise
	 * it's only the <code>&lt;image-name&gt;</code>.
	 * 
	 * <br/>
	 * <br/>
	 * See <a href="http://en.wikipedia.org/wiki/Image_markup">Image markup</a>
	 * and see <a href="http://www.mediawiki.org/wiki/Help:Images">Help:Images</a>
	 * 
	 * @param imageNamespace
	 *          the image namespace
	 * @param rawImageLink
	 *          the raw image link text without the surrounding
	 *          <code>[[...]]</code>
	 */
	public void parseInternalImageLink(String imageNamespace, String rawImageLink) {
		String imageSrc = getImageBaseURL();
		if (imageSrc != null) {
			String imageHref = getWikiBaseURL();
			ImageFormat imageFormat = ImageFormat.getImageFormat(rawImageLink, imageNamespace);

			String imageName = imageFormat.getFilename();
			String sizeStr = imageFormat.getWidthStr();
			if (sizeStr != null) {
				imageName = sizeStr + '-' + imageName;
			}
			if (imageName.endsWith(".svg")) {
				imageName += ".png";
			}
			imageName = Encoder.encodeUrl(imageName);
			if (replaceColon()) {
				imageName = imageName.replace(':', '/');
			}
			String link = imageFormat.getLink();
			if (link != null) {
				if (link.length() == 0) {
					imageHref = "";
				} else {
					String encodedTitle = encodeTitleToUrl(link, true);
					imageHref = imageHref.replace("${title}", encodedTitle);
				}

			} else {
				if (replaceColon()) {
					imageHref = imageHref.replace("${title}", imageNamespace + '/' + imageName);
				} else {
					imageHref = imageHref.replace("${title}", imageNamespace + ':' + imageName);
				}
			}
			imageSrc = imageSrc.replace("${image}", imageName);
			String type = imageFormat.getType();
			TagToken tag = null;
			if ("thumb".equals(type) || "frame".equals(type)) {
				if (fTagStack.size() > 0) {
					tag = peekNode();
				}
				reduceTokenStack(Configuration.HTML_DIV_OPEN);

			}
			appendInternalImageLink(imageHref, imageSrc, imageFormat);
			if (tag instanceof PTag) {
				pushNode(new PTag());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean replaceColon() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUp() {
		super.setUp();
		categories = new HashMap<String, String>();
		links = new HashSet<String>();
		templates = new HashSet<String>();
		semanticRelations = null;
		semanticAttributes = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public INamespace getNamespace() {
		return fNamespace;
	}

	/**
	 * Convert a given text in wiki notation into another format.
	 * 
	 * @param model
	 *          a wiki model
	 * @param converter
	 *          a text converter. <b>Note</b> the converter may be
	 *          <code>null</code>, if you only would like to analyze the raw wiki
	 *          text and don't need to convert. This speeds up the parsing
	 *          process.
	 * @param rawWikiText
	 *          a raw wiki text
	 * @param resultBuffer
	 *          the buffer to which to append the resulting HTML code.
	 * @param templateTopic
	 *          if <code>true</code>, render the wiki text as if a template topic
	 *          will be displayed directly, otherwise render the text as if a
	 *          common wiki topic will be displayed.
	 * @param parseTemplates
	 *          parses the template expansion step (parses include, onlyinclude,
	 *          includeonly etc)
	 * @throws IOException
	 */
	public static void toText(IWikiModel model, ITextConverter converter, String rawWikiText, Appendable resultBuffer,
			boolean templateTopic, boolean parseTemplates) throws IOException {
		model.render(converter, rawWikiText, resultBuffer, templateTopic, parseTemplates);
	}

	/**
	 * Convert a given text in wiki notation into HTML text.
	 * 
	 * @param rawWikiText
	 *          a raw wiki text
	 * @param resultBuffer
	 *          the buffer to which to append the resulting HTML code.
	 * @param imageBaseURL
	 *          a url string which must contains a &quot;${image}&quot; variable
	 *          which will be replaced by the image name, to create links to
	 *          images.
	 * @param linkBaseURL
	 *          a url string which must contains a &quot;${title}&quot; variable
	 *          which will be replaced by the topic title, to create links to
	 *          other wiki topics.
	 * @throws IOException
	 */
	public static void toHtml(String rawWikiText, Appendable resultBuffer, String imageBaseURL, String linkBaseURL)
			throws IOException {
		toText(new WikiModel(imageBaseURL, linkBaseURL), new HTMLConverter(), rawWikiText, resultBuffer, false, false);
	}

	/**
	 * Convert a given text in wiki notation into HTML text.
	 * 
	 * @param rawWikiText
	 *          a raw wiki text
	 * @param resultBuffer
	 *          the buffer to which to append the resulting HTML code.
	 * @throws IOException
	 */
	public static void toHtml(String rawWikiText, Appendable resultBuffer) throws IOException {
		toText(new WikiModel("/${image}", "/${title}"), new HTMLConverter(), rawWikiText, resultBuffer, false, false);
	}

	/**
	 * Convert a given text in wiki notation into HTML text.
	 * 
	 * @param rawWikiText
	 *          a raw wiki text
	 * @param resultBuffer
	 *          the buffer to which to append the resulting HTML code.
	 * @return the resulting HTML text; nay returns <code>null</code>, if an
	 *         <code>IOException</code> occured.
	 */
	public static String toHtml(String rawWikiText) {
		try {
			StringBuilder resultBuffer = new StringBuilder(rawWikiText.length() + rawWikiText.length() / 10);
			toText(new WikiModel("/${image}", "/${title}"), new HTMLConverter(), rawWikiText, resultBuffer, false, false);
			return resultBuffer.toString();
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * Set the model's locale to a new value. You can use this function in JUnit
	 * tests, but otherwise it's preferred to set the Locale in the nodels
	 * constructor and nether changeing it.
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		fLocale = locale;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getImageBaseURL() {
		return fExternalImageBaseURL;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getWikiBaseURL() {
		return fExternalWikiBaseURL;
	}
}
