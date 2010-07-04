package info.bliki.wiki.template;

import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.namespaces.INamespace;

import java.util.List;

/**
 * A template parser function for <code>{{ns: ... }}</code> <i>namespace/i>
 * syntax
 * 
 */
public class NS extends AbstractTemplateFunction {
	public final static ITemplateFunction CONST = new NS();

	public NS() {

	}

	public String parseFunction(List<String> list, IWikiModel model, char[] src, int beginIndex, int endIndex) {
		if (list.size() > 0) {
			String arg0 = parse(list.get(0), model);
			INamespace namespace = model.getNamespace();
			try {
				int numberCode = Integer.valueOf(arg0).intValue();
				if (numberCode >= (-2) || numberCode <= 15) {
					return namespace.getNamespaceByNumber(numberCode);
				}
			} catch (NumberFormatException nfe) {
				// the given argument could not be parsed as integer number
				arg0 = arg0.replace(' ', '_');
				String value = namespace.getNamespaceByLowercase(arg0.toLowerCase());
				if (value != null) {
					return value;
				}
				return "[[:Template:Ns:" + arg0 + "]]";
			}
		}
		return null;
	}

}
