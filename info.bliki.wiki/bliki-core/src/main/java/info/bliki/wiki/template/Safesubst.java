package info.bliki.wiki.template;

import info.bliki.htmlcleaner.Utils;
import info.bliki.wiki.filter.TemplateParser;
import info.bliki.wiki.model.IWikiModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A template parser function for <code>{{safesubst: ... }}</code>. See <a
 * href="http://en.wikipedia.org/wiki/en:Help:Substitution#safesubst:"
 * >Wikipedia-Help:Substitution</a>
 * 
 */
public class Safesubst extends AbstractTemplateFunction {
	public final static ITemplateFunction CONST = new Safesubst();

	public Safesubst() {

	}

	@Override
	public String parseFunction(List<String> parts1, IWikiModel model, char[] src, int beginIndex, int endIndex, boolean isSubst) {
		String substArg = new String(src, beginIndex, endIndex - beginIndex);
		String substituted = Safesubst.parsePreprocess(substArg, model, null);
		char[] src2 = substituted.toCharArray();

		Object[] objs = TemplateParser.createParameterMap(src2, 0, src2.length);
		List<String> parts = (List<String>) objs[0];
		String templateName = ((String) objs[1]);

		int currOffset = TemplateParser.checkParserFunction(substituted);
		if (currOffset > 0) {
			String function = substituted.substring(0, currOffset - 1).trim();
			if (function != null) {
				ITemplateFunction templateFunction = model.getTemplateFunction(function);
				if (templateFunction != null) {
					// if (function.charAt(0) == '#') {
					// #if:, #ifeq:,...
					parts.set(0, templateName.substring(currOffset));
					String plainContent;
					try {
						plainContent = templateFunction.parseFunction(parts, model, src2, currOffset, src2.length, true);
						if (plainContent != null) {
							return plainContent;
						}
					} catch (IOException e) {
					}
				}
				return "";
			}
		}

		LinkedHashMap<String, String> parameterMap = new LinkedHashMap<String, String>();
		List<String> unnamedParameters = new ArrayList<String>();
		for (int i = 1; i < parts.size(); i++) {
			if (i == parts.size() - 1) {
				TemplateParser.createSingleParameter(parts.get(i), parameterMap, unnamedParameters);
			} else {
				TemplateParser.createSingleParameter(parts.get(i), parameterMap, unnamedParameters);
			}
		}
		TemplateParser.mergeParameters(parameterMap, unnamedParameters);

		String plainContent;
		if (templateName.length() > 0 && templateName.charAt(0) == ':') {
			plainContent = model.getRawWikiContent("", templateName.substring(1), parameterMap);
		} else {
			plainContent = model.getRawWikiContent(model.getTemplateNamespace(), templateName, parameterMap);
		}

		if (plainContent != null) {
			return Safesubst.parsePreprocess(plainContent, model, parameterMap);
		}
		return "";
	}

	/**
	 * Parse the preprocess step for the given content string with the template
	 * parser and <code>Utils#trimNewlineLeft()</code> the resulting string.
	 * 
	 * @param content
	 * @param model
	 * @return
	 */
	public static String parsePreprocess(String content, IWikiModel model, Map<String, String> templateParameterMap) {
		if (content == null || content.length() == 0) {
			return "";
		}
		StringBuilder buf = new StringBuilder(content.length());
		try {
			TemplateParser.parsePreprocessRecursive(content, model, buf, false, false, false, templateParameterMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Utils.trimNewlineLeft(buf.toString());
	}
}
