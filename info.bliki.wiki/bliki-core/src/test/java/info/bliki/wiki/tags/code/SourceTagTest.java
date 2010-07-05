package info.bliki.wiki.tags.code;

import info.bliki.wiki.filter.FilterTestSupport;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SourceTagTest extends FilterTestSupport {
	public SourceTagTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(SourceTagTest.class);
	}

	public void testUnknownSourceTag001() {
		String result = wikiModel.render("start <source lang=unknown>Hello World</source> end");

		assertEquals("\n" + "<p>start </p><pre class=\"unknown\">Hello World</pre> end", result);
	}

	public void testUnknownSourceTag002() {
		String result = wikiModel.render("start <source lang=unknown>first line\n second line\n <html> third line\n</source> end");

		assertEquals("\n" + "<p>start </p><pre class=\"unknown\">first line\n" + " second line\n" + " &#60;html&#62; third line\n"
				+ "</pre> end", result);
	}

	/**
	 * Test escaping of JavaSyript
	 */
	public void testUnknownSourceTag003() {
		final String result = wikiModel.render("'''Example'''\n" + "<source lang=unknown>\n"
				+ "<form><input type=\"button\" onclick=\"alert('Are you sure you want to do this?')\" value=\"Alert\"></form>\n"
				+ "</source>");
		String expect = "\n"
				+ "<p><b>Example</b>\n"
				+ "</p><pre class=\"unknown\">\n"
				+ "&#60;form&#62;&#60;input type=&#34;button&#34; onclick=&#34;alert(&#39;Are you sure you want to do this?&#39;)&#34; value=&#34;Alert&#34;&#62;&#60;/form&#62;\n"
				+ "</pre>";
		assertEquals("SQL test002", expect, result);
	}
}
