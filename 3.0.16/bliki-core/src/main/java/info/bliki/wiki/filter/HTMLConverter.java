package info.bliki.wiki.filter;

import info.bliki.htmlcleaner.ContentToken;
import info.bliki.htmlcleaner.EndTagToken;
import info.bliki.htmlcleaner.TagNode;
import info.bliki.htmlcleaner.Utils;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.model.ImageFormat;
import info.bliki.wiki.tags.HTMLTag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A converter which renders the internal tree node representation as HTML text
 * 
 */
public class HTMLConverter implements ITextConverter {
	private boolean fNoLinks;

	public HTMLConverter(boolean noLinks) {
		this.fNoLinks = noLinks;
	}

	public HTMLConverter() {
		this(false);
	}

	public void nodesToText(List<? extends Object> nodes, Appendable resultBuffer, IWikiModel model) throws IOException {
		if (nodes != null && !nodes.isEmpty()) {
			try {
				int level = model.incrementRecursionLevel();

				if (level > Configuration.RENDERER_RECURSION_LIMIT) {
					resultBuffer
							.append("<span class=\"error\">Error - recursion limit exceeded rendering tags in HTMLConverter#nodesToText().</span>");
					return;
				}
				Iterator<? extends Object> childrenIt = nodes.iterator();
				while (childrenIt.hasNext()) {
					Object item = childrenIt.next();
					if (item != null) {
						if (item instanceof List) {
							nodesToText((List) item, resultBuffer, model);
						} else if (item instanceof ContentToken) {
							ContentToken contentToken = (ContentToken) item;
							String content = contentToken.getContent();
							Utils.escapeXmlToBuffer(content, resultBuffer, true, true, true);
						} else if (item instanceof HTMLTag) {
							((HTMLTag) item).renderHTML(this, resultBuffer, model);
						} else if (item instanceof TagNode) {
							TagNode node = (TagNode) item;
							Map<String, Object> map = node.getObjectAttributes();
							if (map != null && map.size() > 0) {
								Object attValue = map.get("wikiobject");
								if (attValue instanceof ImageFormat) {
									imageNodeToText(node, (ImageFormat) attValue, resultBuffer, model);
								}
							} else {
								nodeToHTML(node, resultBuffer, model);
							}
						} else if (item instanceof EndTagToken) {
							EndTagToken node = (EndTagToken) item;
							resultBuffer.append('<');
							resultBuffer.append(node.getName());
							resultBuffer.append("/>");
						}
					}
				}
			} finally {
				model.decrementRecursionLevel();
			}
		}
	}

	protected void nodeToHTML(TagNode node, Appendable resultBuffer, IWikiModel model) throws IOException {
		String name = node.getName();
		if (HTMLTag.NEW_LINES) {
			if (name.equals("div") || name.equals("p") || name.equals("table") || name.equals("ul") || name.equals("ol")
					|| name.equals("li") || name.equals("th") || name.equals("tr") || name.equals("td") || name.equals("pre")) {
				resultBuffer.append('\n');
			}
		}
		resultBuffer.append('<');
		resultBuffer.append(name);

		Map<String, String> tagAtttributes = node.getAttributes();

		for (Map.Entry<String, String> currEntry : tagAtttributes.entrySet()) {
			String attName = currEntry.getKey();
			if (attName.length() >= 1 && Character.isLetter(attName.charAt(0))) {
				String attValue = currEntry.getValue();

				resultBuffer.append(" ");
				resultBuffer.append(attName);
				resultBuffer.append("=\"");
				resultBuffer.append(attValue);
				resultBuffer.append("\"");
			}
		}

		List<Object> children = node.getChildren();
		if (children.size() == 0 && !name.equals("a")) {
			resultBuffer.append(" />");
		} else {
			resultBuffer.append('>');
			if (children.size() != 0) {
				nodesToText(children, resultBuffer, model);
			}
			resultBuffer.append("</");
			resultBuffer.append(node.getName());
			resultBuffer.append('>');
		}
	}

	public void imageNodeToText(TagNode imageTagNode, ImageFormat imageFormat, Appendable resultBuffer, IWikiModel model)
			throws IOException {
		Map<String, String> map = imageTagNode.getAttributes();
		String caption = imageFormat.getCaption();
		String alt = null;
		if (caption != null && caption.length() > 0) {
			alt = imageFormat.getAlt();
			caption = Utils.escapeXml(caption, true, false, true);
		}
		if (alt == null) {
			alt = "";
		}
		String location = imageFormat.getLocation();
		String type = imageFormat.getType();
		int pxWidth = imageFormat.getWidth();
		int pxHeight = imageFormat.getHeight();
		boolean hasDimensions = pxHeight != -1 || pxWidth != -1;
		if (hasDimensions) {
			resultBuffer.append("<div style=\"");
			if (pxHeight != -1) {
				resultBuffer.append("height:").append(Integer.toString(pxHeight)).append("px;");
			}
			if (pxWidth != -1) {
				resultBuffer.append("width:").append(Integer.toString(pxWidth)).append("px;");
			}
			resultBuffer.append("\">");
		}
		String href = map.get("href");
		if (href != null) {
			resultBuffer.append("<a class=\"internal\" href=\"").append(href).append("\" ");

			if (caption != null && caption.length() > 0) {
				resultBuffer.append("title=\"").append((alt.length() == 0) ? caption : alt).append('"');
			}
			resultBuffer.append('>');
		}

		resultBuffer.append("<img src=\"").append(map.get("src")).append('"');

		if (caption != null && caption.length() > 0) {
			if (alt.length() == 0) {
				resultBuffer.append(" alt=\"").append(caption).append('"').append(" title=\"").append(caption).append('"');
			} else {
				resultBuffer.append(" alt=\"").append(alt).append('"').append(" title=\"").append(alt).append('"');
			}
		}

		if (location != null || type != null) {
			StringBuilder clazz = new StringBuilder(64);
			resultBuffer.append(" class=\"");
			if (location != null) {
				clazz.append(" location-").append(location);
			}
			if (type != null) {
				clazz.append(" type-").append(type);
			}
			resultBuffer.append(clazz.toString().trim()).append('"');
		}
		if (pxHeight != -1) {
			resultBuffer.append(" height=\"").append(Integer.toString(pxHeight)).append('"');
		}
		if (pxWidth != -1) {
			resultBuffer.append(" width=\"").append(Integer.toString(pxWidth)).append('\"');
		}
		resultBuffer.append(" />\n");

		if (href != null) {
			resultBuffer.append("</a>");
		}
		List<Object> children = imageTagNode.getChildren();
		if (children.size() != 0) {
			nodesToText(children, resultBuffer, model);
		}
		// if (caption != null && caption.length() > 0) {
		// writer.append("<div class=\"");
		// if (type != null) {
		// writer.append(type);
		// }
		// writer.append("caption\">\n");
		// writer.append(WikipediaParser.parseRecursive(caption, this));
		// writer.append("</div>\n");
		// }

		if (hasDimensions) {
			resultBuffer.append("</div>\n");
		}
	}

	public boolean noLinks() {
		return fNoLinks;
	}
}
