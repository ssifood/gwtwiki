package info.bliki.wiki.filter;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.StringEscapeUtils;

public class TemplateParserTest extends FilterTestSupport {

	public TemplateParserTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(TemplateParserTest.class);
	}

	private final String TEST_STRING_03 = "{{{1|{{PAGENAME}}}}}";

	/**
	 * Issue 86
	 */
	public void testOnlyicludeDemo001() {
		assertEquals("abcdefghi<hr>\n" + 
				";Only active template content is above.\n" + 
				"\n" + 
				";The verbatim active code within reads:\n" + 
				" abc'''&lt;onlyinclude>'''def'''&lt;/onlyinclude>'''ghi'''&lt;includeonly>'''jkl'''&lt;/includeonly>'''\n" + 
				"\n" + 
				"If transposed, the only part included will be the string literal <code>def</code>. \n" + 
				"\n" + 
				"==Example==\n" + 
				"Including [[:Help:Template/onlyinclude demo]] yields only:\n" + 
				" {{:Help:Template/onlyinclude demo}}\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"[[Category:Handbook templates]]\n" + 
				"[[Category:Template documentation|PAGENAME]]\n" + 
				"\n" + 
				"", wikiModel.parseTemplates(WikiTestModel.ONLYINCLUDE_DEMO));
	}
	 
	/**
	 * Issue 86
	 */
	public void testOnlyicludeDemo002() {
		assertEquals("def", wikiModel.parseTemplates("{{OnlyicludeDemo}}"));
	}

	/**
	 * Issue 86
	 */
	public void testInclude() {
		assertEquals("{| class=\"wikitable float-right\" style=\"width:30%; min-width:250px; max-width:400px; font-size:90%; margin-top:0px;\"\n" + 
				"|--\n" + 
				"! colspan=\"2\" style=\"background-color:Khaki; font-size:110%;\" | [[Asteroid]]<br/>{{{Name}}}\n" + 
				"|--\n" + 
				"|}", wikiModel.parseTemplates("{{TestInclude}}"));
	}

	/**
	 * Issue 86
	 */
	public void testInclude2() {
		assertEquals("{| class=\"wikitable float-right\" style=\"width:30%; min-width:250px; max-width:400px; font-size:90%; margin-top:0px;\"\n" + 
				"|--\n" + 
				"! colspan=\"2\" style=\"background-color:Khaki; font-size:110%;\" | [[Asteroid]]<br/>{{{Name}}}\n" + 
				"|--\n" + 
				"|}", wikiModel.parseTemplates("{{TestInclude2}}"));
	}
 
	/**
	 * Issue 86
	 */
	public void testInclude3() {
		assertEquals("text", wikiModel.parseTemplates("{{TestInclude3}}"));
	}

	/**
	 * Issue 86
	 */
	public void testInclude4() {
		assertEquals("Text ", wikiModel.parseTemplates("{{TestInclude4}}"));
	}

	public void test00() {
		assertEquals(
				"{| class=\"float-right\" style=\"width:290px; font-size:90%; background:#FAFAFA;  border:1px solid #bbb; margin:0px 0px 1em 1em; border-collapse:collapse;\" summary=\"Infobox\"\n"
						+ "|-\n"
						+ "| colspan=\"2\" style=\"background:#ffffff; text-align:center; font-size:135%;\" | '''PAGENAME'''</font></br><small></small>\n"
						+ "|- class=\"hintergrundfarbe2\"\n"
						+ "| colspan=\"2\" style=\"font-weight:bold; padding-left:8px; border-top:solid 1px #bbb;\" |\n"
						+ "|- class=\"hintergrundfarbe2\" style=\"text-align: center;\"\n"
						+ "| style=\"width: 50%;\" | [[Bild:Sin escudo.svg|120px|Wappn fêîht]]\n"
						+ "| align=\"center\" style=\"width: 50%;\" | \n"
						+ "{| border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\"\n"
						+ "|<div style=\"position: relative;\"><div style=\"font-size: 5px; position: absolute; display: block; left:87px; top:117px; padding:0;\">[[Bild:reddot.svg|5px|PAGENAME]]</div>[[Bild:Karte Deutschland.svg|140x175px|Deitschlandkartn, Position vo PAGENAME heavoghom]]</div>\n"
						+ "|}\n"
						+ "|-\n"
						+ "! colspan=\"2\" style=\"background-color:#ABCDEF; border:1px solid #bbb;\" | Basisdatn\n"
						+ "|- class=\"hintergrundfarbe2\"\n"
						+ "| '''[[Bundesland (Deutschland)|Bundesland]]''': || [[Bayern]]\n"
						+ "|- class=\"hintergrundfarbe2\"\n" + "|}", wikiModel.parseTemplates("{{Infobox Ort in Deutschland\n"
						+ "|Art               = Stadt\n" + "|Wappen            = Wappen_Grafenwöhr.png\n"
						+ "|lat_deg           = 49 |lat_min = 43\n" + "|lon_deg           = 11 |lon_min = 54\n" + "|Lageplan          = \n"
						+ "|Bundesland        = Bayern\n" + "}}"));
	}

	public void test000() {
		assertEquals(
				"{| border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\"\n"
						+ "|<div style=\"position: relative;\"><div style=\"font-size: 5px; position: absolute; display: block; left:108px; top:55px; padding:0;\">[[Bild:reddot.svg|5px|PAGENAME]]</div>[[Bild:Karte Deutschland.svg|140x175px|Deitschlandkartn, Position vo PAGENAME heavoghom]]</div>\n"
						+ "|}", wikiModel
						.parseTemplates("{{Lageplan\n" + "|marker     = reddot.svg\n" + "|markersize = 5\n"
								+ "|markertext = {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }}\n"
								+ "|pos_y      = {{#expr: (55.1 - {{{lat_deg|52.5}}} - {{{lat_min|0}}} / 60) * 100 / 7.9}}\n"
								+ "|pos_x      = {{#expr: ({{{lon_deg|13.4}}} + {{{lon_min|0}}} / 60) * 10 - 55}}\n"
								+ "|map        = Karte Deutschland.svg\n" + "|mapsize_x  = 140\n" + "|mapsize_y  = 175\n"
								+ "|maptext    = Deitschlandkartn, Position vo {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }} heavoghom\n"
								+ "|warning    = [[Bild:Missing Map of Germany.png|140px|Koordinaten san außerhoib vom darstellbarn Bereich]]\n"
								+ "}}"));
	}

	public void test001() {
		assertEquals(
				"\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\">\n"
						+ "<tr>\n"
						+ "<td>\n"
						+ "<div style=\"position: relative;\">\n"
						+ "<div style=\"font-size: 5px; position: absolute; display: block; left:108px; top:55px; padding:0;\"><div style=\"width:5px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:5px-reddot.svg.png\" title=\"PAGENAME\"><img src=\"http://www.bliki.info/wiki/5px-reddot.svg.png\" alt=\"PAGENAME\" title=\"PAGENAME\" class=\"location-none\" width=\"5\" />\n"
						+ "</a></div>\n"
						+ "</div><div style=\"height:175px;width:140px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:140px-Karte_Deutschland.svg.png\" title=\"Deitschlandkartn, Position vo PAGENAME heavoghom\"><img src=\"http://www.bliki.info/wiki/140px-Karte_Deutschland.svg.png\" alt=\"Deitschlandkartn, Position vo PAGENAME heavoghom\" title=\"Deitschlandkartn, Position vo PAGENAME heavoghom\" class=\"location-none\" height=\"175\" width=\"140\" />\n"
						+ "</a></div>\n" + "</div></td></tr></table></div>", wikiModel
						.render("{{Lageplan\n" + "|marker     = reddot.svg\n" + "|markersize = 5\n"
								+ "|markertext = {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }}\n"
								+ "|pos_y      = {{#expr: (55.1 - {{{lat_deg|52.5}}} - {{{lat_min|0}}} / 60) * 100 / 7.9}}\n"
								+ "|pos_x      = {{#expr: ({{{lon_deg|13.4}}} + {{{lon_min|0}}} / 60) * 10 - 55}}\n"
								+ "|map        = Karte Deutschland.svg\n" + "|mapsize_x  = 140\n" + "|mapsize_y  = 175\n"
								+ "|maptext    = Deitschlandkartn, Position vo {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }} heavoghom\n"
								+ "|warning    = [[Bild:Missing Map of Germany.png|140px|Koordinaten san außerhoib vom darstellbarn Bereich]]\n"
								+ "}}"));
	}

	public void test002() {
		assertEquals(
				"{| class=\"float-right\" style=\"width:290px; font-size:90%; background:#FAFAFA;  border:1px solid #bbb; margin:0px 0px 1em 1em; border-collapse:collapse;\" summary=\"Infobox\"\n"
						+ "|-\n"
						+ "| colspan=\"2\" style=\"background:#ffffff; text-align:center; font-size:135%;\" | '''PAGENAME'''</font></br><small></small>\n"
						+ "|- class=\"hintergrundfarbe2\"\n"
						+ "| colspan=\"2\" style=\"font-weight:bold; padding-left:8px; border-top:solid 1px #bbb;\" |\n"
						+ "|- class=\"hintergrundfarbe2\" style=\"text-align: center;\"\n"
						+ "| style=\"width: 50%;\" | [[Bild:Sin escudo.svg|120px|Wappn fêîht]]\n"
						+ "| align=\"center\" style=\"width: 50%;\" | [[Bild:Karte Deutschland.png|140px|Koordinatn san net da]]\n"
						+ "|-\n"
						+ "! colspan=\"2\" style=\"background-color:#ABCDEF; border:1px solid #bbb;\" | Basisdatn\n"
						+ "|- class=\"hintergrundfarbe2\"\n"
						+ "| '''[[Bundesland (Deutschland)|Bundesland]]''': || [[]]\n"
						+ "|- class=\"hintergrundfarbe2\"\n" + "|}", wikiModel.parseTemplates("{{Infobox Ort in Deutschland}}"));
	}

	public void test003() {
		assertEquals(
				"\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table class=\"float-right\" style=\"width:290px; font-size:90%; background:#FAFAFA;  border:1px solid #bbb; margin:0px 0px 1em 1em; border-collapse:collapse;\" summary=\"Infobox\">\n"
						+ "<tr>\n"
						+ "<td colspan=\"2\" style=\"background:#ffffff; text-align:center; font-size:135%;\"><b>PAGENAME</b><small /></td></tr>\n"
						+ "<tr class=\"hintergrundfarbe2\">\n"
						+ "<td colspan=\"2\" style=\"font-weight:bold; padding-left:8px; border-top:solid 1px #bbb;\" /></tr>\n"
						+ "<tr class=\"hintergrundfarbe2\" style=\"text-align: center;\">\n"
						+ "<td style=\"width: 50%;\"><div style=\"width:120px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:120px-Sin_escudo.svg.png\" title=\"Wappn fêîht\"><img src=\"http://www.bliki.info/wiki/120px-Sin_escudo.svg.png\" alt=\"Wappn fêîht\" title=\"Wappn fêîht\" class=\"location-none\" width=\"120\" />\n"
						+ "</a></div>\n"
						+ "</td>\n"
						+ "<td align=\"center\" style=\"width: 50%;\"><div style=\"width:140px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:140px-Karte_Deutschland.png\" title=\"Koordinatn san net da\"><img src=\"http://www.bliki.info/wiki/140px-Karte_Deutschland.png\" alt=\"Koordinatn san net da\" title=\"Koordinatn san net da\" class=\"location-none\" width=\"140\" />\n"
						+ "</a></div>\n"
						+ "</td></tr>\n"
						+ "<tr>\n"
						+ "<th colspan=\"2\" style=\"background-color:#ABCDEF; border:1px solid #bbb;\">Basisdatn</th></tr>\n"
						+ "<tr class=\"hintergrundfarbe2\">\n"
						+ "<td><b><a href=\"http://www.bliki.info/wiki/Bundesland_(Deutschland)\" title=\"Bundesland (Deutschland)\">Bundesland</a></b>: </td>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/\" title=\"\" /></td></tr></table></div>", wikiModel
						.render("{{Infobox Ort in Deutschland}}"));
	}

	public void test004() {
		assertEquals(
				"{| class=\"float-right\" style=\"width:290px; font-size:90%; background:#FAFAFA;  border:1px solid #bbb; margin:0px 0px 1em 1em; border-collapse:collapse;\" summary=\"Infobox\"\n"
						+ "|-\n"
						+ "| colspan=\"2\" style=\"background:#ffffff; text-align:center; font-size:135%;\" | '''PAGENAME'''</font></br><small></small>\n"
						+ "|- class=\"hintergrundfarbe2\"\n"
						+ "| colspan=\"2\" style=\"font-weight:bold; padding-left:8px; border-top:solid 1px #bbb;\" |\n"
						+ "|- class=\"hintergrundfarbe2\" style=\"text-align: center;\"\n"
						+ "| style=\"width: 50%;\" | [[Bild:Sin escudo.svg|120px|Wappn fêîht]]\n"
						+ "| align=\"center\" style=\"width: 50%;\" | \n"
						+ "{| border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\"\n"
						+ "|<div style=\"position: relative;\"><div style=\"font-size: 5px; position: absolute; display: block; left:87px; top:117px; padding:0;\">[[Bild:reddot.svg|5px|PAGENAME]]</div>[[Bild:Karte Deutschland.svg|140x175px|Deitschlandkartn, Position vo PAGENAME heavoghom]]</div>\n"
						+ "|}\n"
						+ "|-\n"
						+ "! colspan=\"2\" style=\"background-color:#ABCDEF; border:1px solid #bbb;\" | Basisdatn\n"
						+ "|- class=\"hintergrundfarbe2\"\n"
						+ "| '''[[Bundesland (Deutschland)|Bundesland]]''': || [[Bayern]]\n"
						+ "|- class=\"hintergrundfarbe2\"\n" + "|}", wikiModel.parseTemplates("{{Infobox Ort in Deutschland\n"
						+ "|Art               = Stadt\n" + "|Wappen            = Wappen_Grafenwöhr.png\n"
						+ "|lat_deg           = 49 |lat_min = 43\n" + "|lon_deg           = 11 |lon_min = 54\n" + "|Lageplan          = \n"
						+ "|Bundesland        = Bayern\n" + "}}"));
	}

	public void test005() {
		assertEquals(
				"\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table class=\"float-right\" style=\"width:290px; font-size:90%; background:#FAFAFA;  border:1px solid #bbb; margin:0px 0px 1em 1em; border-collapse:collapse;\" summary=\"Infobox\">\n"
						+ "<tr>\n"
						+ "<td colspan=\"2\" style=\"background:#ffffff; text-align:center; font-size:135%;\"><b>PAGENAME</b><small /></td></tr>\n"
						+ "<tr class=\"hintergrundfarbe2\">\n"
						+ "<td colspan=\"2\" style=\"font-weight:bold; padding-left:8px; border-top:solid 1px #bbb;\" /></tr>\n"
						+ "<tr class=\"hintergrundfarbe2\" style=\"text-align: center;\">\n"
						+ "<td style=\"width: 50%;\"><div style=\"width:120px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:120px-Sin_escudo.svg.png\" title=\"Wappn fêîht\"><img src=\"http://www.bliki.info/wiki/120px-Sin_escudo.svg.png\" alt=\"Wappn fêîht\" title=\"Wappn fêîht\" class=\"location-none\" width=\"120\" />\n"
						+ "</a></div>\n"
						+ "</td>\n"
						+ "<td align=\"center\" style=\"width: 50%;\">\n"
						+ "\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\">\n"
						+ "<tr>\n"
						+ "<td>\n"
						+ "<div style=\"position: relative;\">\n"
						+ "<div style=\"font-size: 5px; position: absolute; display: block; left:87px; top:117px; padding:0;\"><div style=\"width:5px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:5px-reddot.svg.png\" title=\"PAGENAME\"><img src=\"http://www.bliki.info/wiki/5px-reddot.svg.png\" alt=\"PAGENAME\" title=\"PAGENAME\" class=\"location-none\" width=\"5\" />\n"
						+ "</a></div>\n"
						+ "</div><div style=\"height:175px;width:140px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:140px-Karte_Deutschland.svg.png\" title=\"Deitschlandkartn, Position vo PAGENAME heavoghom\"><img src=\"http://www.bliki.info/wiki/140px-Karte_Deutschland.svg.png\" alt=\"Deitschlandkartn, Position vo PAGENAME heavoghom\" title=\"Deitschlandkartn, Position vo PAGENAME heavoghom\" class=\"location-none\" height=\"175\" width=\"140\" />\n"
						+ "</a></div>\n"
						+ "</div></td></tr></table></div></td></tr>\n"
						+ "<tr>\n"
						+ "<th colspan=\"2\" style=\"background-color:#ABCDEF; border:1px solid #bbb;\">Basisdatn</th></tr>\n"
						+ "<tr class=\"hintergrundfarbe2\">\n"
						+ "<td><b><a href=\"http://www.bliki.info/wiki/Bundesland_(Deutschland)\" title=\"Bundesland (Deutschland)\">Bundesland</a></b>: </td>\n"
						+ "<td><a href=\"http://www.bliki.info/wiki/Bayern\" title=\"Bayern\">Bayern</a></td></tr></table></div>", wikiModel
						.render("{{Infobox Ort in Deutschland\n" + "|Art               = Stadt\n"
								+ "|Wappen            = Wappen_Grafenwöhr.png\n" + "|lat_deg           = 49 |lat_min = 43\n"
								+ "|lon_deg           = 11 |lon_min = 54\n" + "|Lageplan          = \n" + "|Bundesland        = Bayern\n" + "}}"));
	}

	public void test006() {
		assertEquals(
				"\n"
						+ "\n"
						+ "<div style=\"page-break-inside: avoid;\">\n"
						+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\">\n"
						+ "<tr>\n"
						+ "<td>\n"
						+ "<div style=\"position: relative;\">\n"
						+ "<div style=\"font-size: 5px; position: absolute; display: block; left:108px; top:55px; padding:0;\"><div style=\"width:5px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:5px-reddot.svg.png\" title=\"PAGENAME\"><img src=\"http://www.bliki.info/wiki/5px-reddot.svg.png\" alt=\"PAGENAME\" title=\"PAGENAME\" class=\"location-none\" width=\"5\" />\n"
						+ "</a></div>\n"
						+ "</div><div style=\"height:175px;width:140px;\"><a class=\"internal\" href=\"http://www.bliki.info/wiki/Bild:140px-Karte_Deutschland.svg.png\" title=\"Deitschlandkartn, Position vo PAGENAME heavoghom\"><img src=\"http://www.bliki.info/wiki/140px-Karte_Deutschland.svg.png\" alt=\"Deitschlandkartn, Position vo PAGENAME heavoghom\" title=\"Deitschlandkartn, Position vo PAGENAME heavoghom\" class=\"location-none\" height=\"175\" width=\"140\" />\n"
						+ "</a></div>\n" + "</div></td></tr></table></div>",
				wikiModel
						.render("{{#if: {{{Karte|}}} | [[Bild:{{{Karte}}}|140x175px|Deitschlandkartn, Position vo {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }} heavoghobn]] | {{#if: {{{lat_deg|t2}}} |\n"
								+ "{{Lageplan\n"
								+ "|marker     = reddot.svg\n"
								+ "|markersize = 5\n"
								+ "|markertext = {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }}\n"
								+ "|pos_y      = {{#expr: (55.1 - {{{lat_deg|52.5}}} - {{{lat_min|0}}} / 60) * 100 / 7.9}}\n"
								+ "|pos_x      = {{#expr: ({{{lon_deg|13.4}}} + {{{lon_min|0}}} / 60) * 10 - 55}}\n"
								+ "|map        = Karte Deutschland.svg\n"
								+ "|mapsize_x  = 140\n"
								+ "|mapsize_y  = 175\n"
								+ "|maptext    = Deitschlandkartn, Position vo {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }} heavoghom\n"
								+ "|warning    = [[Bild:Missing Map of Germany.png|140px|Koordinaten san außerhoib vom darstellbarn Bereich]]\n"
								+ "}}\n" + "| [[Bild:Karte Deutschland.png|140px|Koordinatn san net da]] }}\n" + "}}"));
	}

	public void test010() {
		assertEquals(
				"start\n"
						+ "{|border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\"\n"
						+ "|<div style=\"position: relative;\"><div style=\"font-size:16px\">middle</div></div> \n" + "|}\n" + "end",
				wikiModel
						.parseTemplates("start\n{{#if: {{{1|test}}} | {{{!}}border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\"\n"
								+ "{{!}}<div style=\"position: relative;\"><div style=\"font-size:16px\">middle</div></div> \n{{!}}} }}\nend"));
	}

	public void testIf00() {
		assertEquals(
				"start{{es-verb form of/indicative}}end",
				wikiModel
						.parseTemplates("start{{{{ #if:  | l | u }}cfirst:  {{ es-verb form of/{{ #switch: indicative  | ind | indicative = indicative  | subj | subjunctive = subjunctive  | imp | imperative = imperative  | cond | conditional = conditional  | par | part | participle | past participle  | past-participle = participle  | adv | adverbial | ger | gerund | gerundive  | gerundio | present participle  | present-participle = adverbial  | error  }}  | tense =  {{ #switch: present  | pres | present = present  | imp | imperfect = imperfect  | pret | preterit | preterite = preterite  | fut | future = future  | cond | conditional = conditional  }}  | number =  {{ #switch: singular  | s | sg | sing | singular = singular  | p | pl | plural = plural  }}  | person =  {{ #switch: 1  | 1 | first | first person | first-person = first  | 2 | second|second person | second-person = second  | 3 | third | third person | third-person = third  | 0 | - | imp | impersonal = impersonal  }}  | formal =  {{ #switch: {{{formal}}}  | y | yes = yes  | n | no = no  }}  | gender =  {{ #switch:   | m | masc | masculine = masculine  | f | fem | feminine = feminine  }}  | sense =  {{ #switch: {{{sense}}}  | + | aff | affirmative = affirmative  | - | neg | negative = negative  }}  | sera = {{ #switch: {{{sera}}} | se = se | ra = ra }}  | ending =  {{ #switch: ar  | ar | -ar = -ar  | er | -er = -er  | ir | -ir = -ir  }}  | participle =   | voseo = {{ #if:  | yes | no }}  }}}}end"));
	}

	public void testIf01() {
		assertEquals("startnoend", wikiModel.parseTemplates("start{{#if:\n" + "\n" + "\n" + "| yes | no}}end"));
	}

	public void testIf02() {
		assertEquals("startend", wikiModel.parseTemplates("start{{      #if:   \n    |{{#ifeq:{{{seperator}}}|;|;|. }}     }}end"));
	}

	public void testIf03() {
		assertEquals("startyesend", wikiModel.parseTemplates("start{{#if:string\n" + "\n" + "\n" + "| yes | no}}end"));
	}

	public void testIf04() {
		assertEquals("startend", wikiModel.parseTemplates("start{{#if: | yes }}end"));
	}

	public void testIfeq01() {
		assertEquals("startyesend", wikiModel.parseTemplates("start{{#ifeq: 01 | 1 | yes | no}}end"));
	}

	public void testIfeq02() {
		assertEquals("startyesend", wikiModel.parseTemplates("start{{#ifeq: 0 | -0 | yes | no}}end"));
	}

	public void testIfeq03() {
		assertEquals("startnoend", wikiModel.parseTemplates("start{{#ifeq: \"01\" | \"1\" | yes | no}}end"));
	}

	public void testIf05() {
		assertEquals("startend", wikiModel.parseTemplates("start{{#if: foo | | no}}end"));
	}

	public void testIfexpr01() {
		assertEquals("startnoend", wikiModel.parseTemplates("start{{#ifexpr: 1 < 0 | | no}}end"));
	}

	public void testIfexpr02() {
		assertEquals("startend", wikiModel.parseTemplates("start{{#ifexpr: 1 > 0 | | no}}end"));
	}

	public void testBORN_DATA() {
		assertEquals(
				"test Thomas Jeffrey Hanks<br />[[Concord, California]],  [[United States|U.S.]] test123",
				wikiModel
						.parseTemplates("test {{Born_data | birthname = Thomas Jeffrey Hanks | birthplace = [[Concord, California]],  [[United States|U.S.]] }} test123"));
	}

	public void testMONTHNUMBER() {
		assertEquals("test 10 test123", wikiModel.parseTemplates("test {{MONTHNUMBER | 10 }} test123"));
	}

	public void testMONTHNAME() {
		assertEquals("test October test123", wikiModel.parseTemplates("test {{MONTHNAME | 10 }} test123"));
	}

	public void testAnarchismSidebar() {
		assertEquals("{{Sidebar}}", wikiModel.parseTemplates("{{Anarchism sidebar}}", false));
	}

	public void testNonExistentTemplate() {
		assertEquals("==Other areas of Wikipedia==\n" + "{{WikipediaOther}}", wikiModel.parseTemplates("==Other areas of Wikipedia==\n"
				+ "{{WikipediaOther}}<!--Template:WikipediaOther-->", false));
	}

	public void testTemplateCall1() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("start-an include page-end", wikiModel.parseTemplates("start-{{:Include Page}}-end", false));
	}

	public void testTemplateCall2() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("start-b) First: 3 Second: b-end start-c) First: sdfsf Second: klj-end", wikiModel.parseTemplates(
				"start-{{templ1|a=3|b}}-end start-{{templ2|sdfsf|klj}}-end", false));
	}

	public void testTemplateCall3() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("b) First: Test1 Second: c) First: sdfsf Second: klj \n" + "\n" + "", wikiModel.parseTemplates("{{templ1\n"
				+ " | a = Test1\n" + " |{{templ2|sdfsf|klj}} \n" + "}}\n" + "", false));
	}

	public void testTemplateCall4() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals("{{[[Template:example|example]]}}", wikiModel.parseTemplates("{{tl|example}}", false));
	}

	public void testTemplateCall5() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals(
				"(pronounced <span title=\"Pronunciation in the International Phonetic Alphabet (IPA)\" class=\"IPA\">[[WP:IPA for English|/dəˌpeʃˈmoʊd/]]</span>)",
				wikiModel.parseTemplates("({{pron-en|dəˌpeʃˈmoʊd}})", false));
	}

	public void testTemplateParameter01() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start-a) First: arg1 Second: arg2-end", wikiModel.parseTemplates("start-{{Test|arg1|arg2}}-end", false));
	}

	public void testTemplateParameter02() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals(
				"start- ''[http://www.etymonline.com/index.php?search=hello&searchmode=none Online Etymology Dictionary]''. -end",
				wikiModel
						.parseTemplates(
								"start- {{cite web|url=http://www.etymonline.com/index.php?search=hello&searchmode=none|title=Online Etymology Dictionary}} -end",
								false));
	}

	public void testTemplateParameter03() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start- <div class=\"references-small\" style=\"-moz-column-count:2; -webkit-column-count:2; column-count:2;\">\n"
				+ "<references /></div> -end", wikiModel.parseTemplates("start- {{reflist|2}} -end", false));
	}

	public void testTemplateParameter04() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start-<nowiki>{{Test|arg1|arg2}}-</noWiKi>end", wikiModel.parseTemplates(
				"start-<nowiki>{{Test|arg1|arg2}}-</noWiKi>end", false));
	}

	public void testTemplateParameter05() {
		// see method WikiTestModel#getTemplateContent()
		assertEquals("start- end", wikiModel.parseTemplates("start- <!-- {{Test|arg1|arg2}} \n --->end", false));
	}

	// 
	public void testTemplate06() {
		assertEquals("A is not equal B", wikiModel.parseTemplates("{{#ifeq: A | B | A equals B | A is not equal B}}", false));
	}

	public void testTemplate07() {
		assertEquals("start- A is not equal B \n" + " end", wikiModel.parseTemplates("start- {{ifeq|A|B}} \n end", false));
	}

	public void testNestedTemplate() {
		assertEquals("test a a nested template text template", wikiModel.parseTemplates("{{nested tempplate test}}", false));
	}

	public void testEndlessRecursion() {
		assertEquals("{{Error - recursion limit exceeded parsing templates.}}", wikiModel.parseTemplates("{{recursion}}", false));
	}

	private final String TEST_STRING_01 = "[[Category:Interwiki templates|wikipedia]]\n" + "[[zh:Template:Wikipedia]]\n"
			+ "&lt;/noinclude&gt;&lt;div class=&quot;sister-\n"
			+ "wikipedia&quot;&gt;&lt;div class=&quot;sister-project&quot;&gt;&lt;div\n"
			+ "class=&quot;noprint&quot; style=&quot;clear: right; border: solid #aaa\n"
			+ "1px; margin: 0 0 1em 1em; font-size: 90%; background: #f9f9f9; width:\n"
			+ "250px; padding: 4px; text-align: left; float: right;&quot;&gt;\n"
			+ "&lt;div style=&quot;float: left;&quot;&gt;[[Image:Wikipedia-logo-\n" + "en.png|44px|none| ]]&lt;/div&gt;\n"
			+ "&lt;div style=&quot;margin-left: 60px;&quot;&gt;{{#if:{{{lang|}}}|\n"
			+ "{{{{{lang}}}}}&amp;nbsp;}}[[Wikipedia]] has {{#if:{{{cat|\n" + "{{{category|}}}}}}|a category|{{#if:{{{mul|{{{dab|\n"
			+ "{{{disambiguation|}}}}}}}}}|articles|{{#if:{{{mulcat|}}}|categories|an\n" + "article}}}}}} on:\n"
			+ "&lt;div style=&quot;margin-left: 10px;&quot;&gt;'''''{{#if:{{{cat|\n"
			+ "{{{category|}}}}}}|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}Category:\n"
			+ "{{ucfirst:{{{cat|{{{category}}}}}}}}|{{ucfirst:{{{1|{{{cat|\n"
			+ "{{{category}}}}}}}}}}}]]|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}{{ucfirst:\n"
			+ "{{#if:{{{dab|{{{disambiguation|}}}}}}|{{{dab|{{{disambiguation}}}}}}|\n"
			+ "{{{1|{{PAGENAME}}}}}}}}}|{{ucfirst:{{{2|{{{1|{{{dab|{{{disambiguation|\n"
			+ "{{PAGENAME}}}}}}}}}}}}}}}}]]}}''''' {{#if:{{{mul|{{{mulcat|}}}}}}|and\n"
			+ "'''''{{#if:{{{mulcat|}}}|[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}Category:\n"
			+ "{{ucfirst:{{{mulcat}}}}}|{{ucfirst:{{{mulcatlabel|{{{mulcat}}}}}}}}]]|\n"
			+ "[[w:{{#if:{{{lang|}}}|{{{lang}}}:}}{{ucfirst:{{{mul}}}}}|{{ucfirst:\n"
			+ "{{{mullabel|{{{mul}}}}}}}}]]'''''}}|}}&lt;/div&gt;\n" + "&lt;/div&gt;\n" + "&lt;/div&gt;\n"
			+ "&lt;/div&gt;&lt;/div&gt;&lt;span class=&quot;interProject&quot;&gt;[[w:\n"
			+ "{{#if:{{{lang|}}}|{{{lang}}}:}}{{#if:{{{cat|{{{category|}}}}}}|\n"
			+ "Category:{{ucfirst:{{{cat|{{{category}}}}}}}}|{{ucfirst:{{{dab|\n"
			+ "{{{disambiguation|{{{1|{{PAGENAME}}}}}}}}}}}}}}}|Wikipedia {{#if:\n"
			+ "{{{lang|}}}|&lt;sup&gt;{{{lang}}}&lt;/sup&gt;}}]]&lt;/span&gt;{{#if:\n"
			+ "{{{mul|{{{mulcat|}}}}}}|&lt;span class=&quot;interProject&quot;&gt;[[w:\n"
			+ "{{#if:{{{lang|}}}|{{{lang}}}:}}{{#if:{{{mulcat|}}}|Category:{{ucfirst:\n"
			+ "{{{mulcat}}}}}|{{ucfirst:{{{mul}}}}}}}|Wikipedia {{#if:{{{lang|}}}|\n"
			+ "&lt;sup&gt;{{{lang}}}&lt;/sup&gt;}}]]&lt;/span&gt;}}";

	public void testNestedIf01() {
		String temp = StringEscapeUtils.unescapeHtml(TEST_STRING_01);
		assertEquals("[[Category:Interwiki templates|wikipedia]]\n" + "[[zh:Template:Wikipedia]]\n"
				+ "</noinclude><div class=\"sister-\n" + "wikipedia\"><div class=\"sister-project\"><div\n"
				+ "class=\"noprint\" style=\"clear: right; border: solid #aaa\n"
				+ "1px; margin: 0 0 1em 1em; font-size: 90%; background: #f9f9f9; width:\n"
				+ "250px; padding: 4px; text-align: left; float: right;\">\n" + "<div style=\"float: left;\">[[Image:Wikipedia-logo-\n"
				+ "en.png|44px|none| ]]</div>\n" + "<div style=\"margin-left: 60px;\">[[Wikipedia]] has an\n" + "article on:\n"
				+ "<div style=\"margin-left: 10px;\">\'\'\'\'\'[[w:PAGENAME|PAGENAME]]\'\'\'\'\' </div>\n" + "</div>\n" + "</div>\n"
				+ "</div></div><span class=\"interProject\">[[w:\n" + "PAGENAME|Wikipedia ]]</span>", wikiModel.parseTemplates(temp, false));
	}

	private final String TEST_STRING_02 = " {{#if:{{{cat|\n" + "{{{category|}}}}}}|a category|{{#if:{{{mul|{{{dab|\n"
			+ "{{{disambiguation|}}}}}}}}}|articles|{{#if:{{{mulcat|}}}|categories|an\n" + "article}}}}}} on:\n";

	public void testNestedIf02() {
		assertEquals(" an\n" + "article on:\n" + "", wikiModel.parseTemplates(TEST_STRING_02, false));
	}

	public void testNestedIf03() {
		assertEquals("PAGENAME", wikiModel.parseTemplates(TEST_STRING_03, false));
	}

	private final String TEST_STRING_04 = "{{ucfirst:{{{cat|{{{category}}}}}}}}";

	public void testNestedIf04() {
		assertEquals("{{{category}}}", wikiModel.parseTemplates(TEST_STRING_04, false));
	}//

	public void testSwitch001() {
		assertEquals("UPPER", wikiModel.parseTemplates("{{ #switch: A | a=lower | A=UPPER  }}", false));
	}

	public void testSwitch002() {
		assertEquals("lower", wikiModel.parseTemplates("{{ #switch: {{lc:A}} | a=lower | UPPER  }}", false));
	}

	public void testSwitch003() {
		assertEquals("'''''abc''' or '''ABC'''''", wikiModel.parseTemplates("{{#switch: {{lc: {{{1| B }}} }}\n" + "| a\n" + "| b\n"
				+ "| c = '''''abc''' or '''ABC'''''\n" + "| A\n" + "| B\n" + "| C = ''Memory corruption due to cosmic rays''\n"
				+ "| #default = N/A\n" + "}}", false));
	}

	public void testSwitch004() {
		assertEquals("Yes", wikiModel.parseTemplates("{{ #switch: +07 | 7 = Yes | 007 = Bond | No  }}", false));
	}

	public void testSwitch005() {
		assertEquals("Nothing", wikiModel.parseTemplates("{{#switch: | = Nothing | foo = Foo | Something }}", false));
	}

	public void testSwitch006() {
		assertEquals("Something", wikiModel.parseTemplates("{{#switch: test | = Nothing | foo = Foo | Something }}", false));
	}

	public void testSwitch007() {
		assertEquals("Bar", wikiModel.parseTemplates("{{#switch: test | foo = Foo | #default = Bar | baz = Baz }}", false));
	}

	public void testSwitch008() {
		assertEquals("{{Templ1/ind&}}", wikiModel.parseTemplates("{{Templ1/{{ #switch: imperative  | ind | ind&}}}}", false));
	}

	public void testExpr001() {
		assertEquals("1.0E-6", wikiModel.parseTemplates("{{ #expr: 0.000001 }}", false));
	}

	public void testExpr002() {
		assertEquals("210", wikiModel.parseTemplates("{{ #expr: +30 * +7 }}", false));
	}

	public void testExpr003() {
		assertEquals("210", wikiModel.parseTemplates("{{ #expr: -30 * -7 }}", false));
	}

	public void testExpr004() {
		assertEquals("210", wikiModel.parseTemplates("{{ #expr: 30 * 7 }}", false));
	}

	public void testExpr005() {
		assertEquals("4.285714285714286", wikiModel.parseTemplates("{{ #expr: 30 / 7 }}", false));
		// assertEquals("4.285714285714286", wikiModel.parseTemplates("{{ #expr: 30
		// div 7 }}", false));
	}

	public void testExpr006() {
		assertEquals("37", wikiModel.parseTemplates("{{ #expr: 30 + 7 }}", false));
	}

	public void testExpr007() {
		assertEquals("23", wikiModel.parseTemplates("{{ #expr: 30 - 7 }}", false));
	}

	public void testExpr008() {
		assertEquals("19", wikiModel.parseTemplates("{{ #expr: 30 - 7 - 4}}", false));
	}

	public void testExpr009() {
		assertEquals("4.2857", wikiModel.parseTemplates("{{ #expr: 30 / 7 round 4}}", false));
	}

	public void testExpr010() {
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 30 <> 7}}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 30 != 7}}", false));
	}

	public void testExpr011() {
		assertEquals("0", wikiModel.parseTemplates("{{ #expr: 30 < 7}}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 30 <= 42}}", false));
	}

	public void testExpr012() {
		assertEquals("259", wikiModel.parseTemplates("{{ #expr: (30 + 7)*7 }}", false));
		assertEquals("79", wikiModel.parseTemplates("{{ #expr: 30 + 7 * 7 }}", false));
	}

	public void testExpr013() {
		assertEquals("0", wikiModel.parseTemplates("{{ #expr: 4 < 5 and 4 mod 2 }}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: 4 < 5 or 4 mod 2 }}", false));
	}

	public void testExpr014() {
		assertEquals("7", wikiModel.parseTemplates("{{ #expr: not 0 * 7 }}", false));
		assertEquals("7", wikiModel.parseTemplates("{{ #expr: not 30 + 7 }}", false));
	}

	public void testFormatnum001() {
		assertEquals("1401", wikiModel.parseTemplates("{{formatnum:1401}}", false));
	}

	public void testPlural001() {
		assertEquals("is", wikiModel.parseTemplates("{{plural:n|is|are}}", false));
		assertEquals("is", wikiModel.parseTemplates("{{plural:0|is|are}}", false));
		assertEquals("is", wikiModel.parseTemplates("{{plural:1|is|are}}", false));
		assertEquals("are", wikiModel.parseTemplates("{{plural:2|is|are}}", false));
		assertEquals("are", wikiModel.parseTemplates("{{plural:3|is|are}}", false));
		assertEquals("are", wikiModel.parseTemplates("{{plural:{{#expr:30+7}}|is|are}}", false));
	}

	public void testExpr015() {
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: trunc1.2}}", false));
		assertEquals("-1", wikiModel.parseTemplates("{{ #expr: trunc-1.2 }}", false));
		assertEquals("1", wikiModel.parseTemplates("{{ #expr: floor 1.2}}", false));
		assertEquals("-2", wikiModel.parseTemplates("{{ #expr: floor -1.2 }}", false));
		assertEquals("-2", wikiModel.parseTemplates("{{ #expr: fLoOr -1.2 }}", false));
		assertEquals("2", wikiModel.parseTemplates("{{ #expr: ceil 1.2}}", false));
		assertEquals("-1", wikiModel.parseTemplates("{{ #expr: ceil-1.2 }}", false));
	}

	public void testExpr016() {
		assertEquals("1.0E-7", wikiModel.parseTemplates("{{#expr:1.0E-7}}", false));
	}

	public void testExpr017() {
		assertEquals("<div class=\"error\">Expression error: Division by zero</div>", wikiModel.parseTemplates("{{#expr:4/0}}", false));
		assertEquals("0.75", wikiModel.parseTemplates("{{#expr:3/4}}", false));
		assertEquals("<div class=\"error\">Expression error: Division by zero</div>", wikiModel.parseTemplates("{{#expr:13 mod 0}}",
				false));
		assertEquals("1", wikiModel.parseTemplates("{{#expr:13 mod 3}}", false));
		assertEquals("27", wikiModel.parseTemplates("{{#expr:3 ^3}}", false));
		assertEquals("0.037037037037037035", wikiModel.parseTemplates("{{#expr:3 ^ (-3)}}", false));
		assertEquals("NAN", wikiModel.parseTemplates("{{#expr:(-4) ^ (-1/2)}}", false));
		assertEquals("1", wikiModel.parseTemplates("{{#expr:ln EXp 1 }}", false));
		assertEquals("2.7182818284590455", wikiModel.parseTemplates("{{#expr:exp ln e }}", false));
		assertEquals("1", wikiModel.parseTemplates("{{#expr:sin (pi/2) }}", false));
		assertEquals("6.123233995736766E-17", wikiModel.parseTemplates("{{#expr:(sin pi)/2 }}", false));
		assertEquals("6.123233995736766E-17", wikiModel.parseTemplates("{{#expr:sin pi/2 }}", false));
	}

	public void testNS001() {
		assertEquals("User_talk", wikiModel.parseTemplates("{{ns:3}}", false));
		assertEquals("Help_talk", wikiModel.parseTemplates("{{ns:{{ns:12}}_talk}}", false));
		assertEquals("MediaWiki_talk", wikiModel.parseTemplates("{{ns:{{ns:8}}_talk}}", false));
		assertEquals("MediaWiki_talk", wikiModel.parseTemplates("{{ns:{{ns:8}} talk}}", false));
		assertEquals("MediaWiki_talk", wikiModel.parseTemplates("{{ns:{{ns:8}} talk  }}", false));
		assertEquals("[[:Template:Ns:MediaWikitalk]]", wikiModel.parseTemplates("{{ns:{{ns:8}}talk}}", false));
	}

	public void testURLEncode001() {
		assertEquals("%22%23%24%25%26%27%28%29*%2C%3B%3F%5B%5D%5E%60%7B%7D", wikiModel.parseTemplates(
				"{{urlencode: \"#$%&'()*,;?[]^`{}}}", false));
		assertEquals("%3C", wikiModel.parseTemplates("{{urlencode:<}}", false));
		assertEquals("%3E", wikiModel.parseTemplates("{{urlencode:>}}", false));
		assertEquals("%7C", wikiModel.parseTemplates("{{urlencode:{{!}}}}", false));
	}

	public void testPadleft001() {
		assertEquals("8", wikiModel.parseTemplates("{{padleft:8}}", false));
		assertEquals("008", wikiModel.parseTemplates("{{padleft:8|3}}", false));
		assertEquals("8", wikiModel.parseTemplates("{{padleft:8|a}}", false));
		assertEquals("007", wikiModel.parseTemplates("{{padleft:7|3|0}}", false));
		assertEquals("000", wikiModel.parseTemplates("{{padleft:0|3|0}}", false));
		assertEquals("aaabcd", wikiModel.parseTemplates("{{padleft:bcd|6|a}}", false));
		assertEquals("----cafe", wikiModel.parseTemplates("{{padleft:cafe|8|-}}", false));
		assertEquals("|||bcd", wikiModel.parseTemplates("{{padleft:bcd|6|{{!}}}}", false));
	}

	public void testPadright001() {
		assertEquals("8", wikiModel.parseTemplates("{{padright:8}}", false));
		assertEquals("800", wikiModel.parseTemplates("{{padright:8|3}}", false));
		assertEquals("8", wikiModel.parseTemplates("{{padright:8|a}}", false));
		assertEquals("bcdaaa", wikiModel.parseTemplates("{{padright:bcd|6|a}}", false));
		assertEquals("0aaaaa", wikiModel.parseTemplates("{{padright:0|6|a}}", false));

	}

	public void testTime001() {
		// seconds since January 1970
		String currentSecondsStr = wikiModel.parseTemplates("{{ #time: U }}", false);
		Long currentSeconds = Long.valueOf(currentSecondsStr);
		assertTrue(currentSeconds > 1212598361);
	}

	public void testTag001() {
		assertEquals("<references =\"\"></references>", wikiModel.parseTemplates("{{#tag:references|{{{refs|}}}|group={{{group|}}}}}"));
	}

	public void testPipe001() {
		assertEquals("hello worldhello world ", wikiModel.parseTemplates("{{2x|hello world" + "}} ", false));
	}

	public void testPipe001a() {
		assertEquals("Hello World\n" + "Hello World\n" + "", wikiModel.parseTemplates("{{2x|Hello World\n" + "}}", false));
	}

	public void testPipe002() {
		assertEquals("{| \n" + "| A \n" + "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n" + "| B\n" + "|- \n"
				+ "| C\n" + "| D\n" + "|}\n", wikiModel.parseTemplates("{{2x|{{{!}} \n" + "{{!}} A \n" + "{{!}} B\n" + "{{!}}- \n"
				+ "{{!}} C\n" + "{{!}} D\n" + "{{!}}}\n" + "}}", false));
	}

	public void testPipe003() {
		assertEquals("{| \n" + "| A \n" + "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n" + "| B\n" + "|- \n"
				+ "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n" + "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "{| \n" + "| A \n"
				+ "| B\n" + "|- \n" + "| C\n" + "| D\n" + "|}\n" + "", wikiModel.parseTemplates("{{2x|{{2x|{{{!}} \n" + "{{!}} A \n"
				+ "{{!}} B\n" + "{{!}}- \n" + "{{!}} C\n" + "{{!}} D\n" + "{{!}}}\n" + "}}}}", false));
	}

	public void testInvalidNoinclude() {
		assertEquals("test123 start\n" + 
				"test123 end", wikiModel.parseTemplates("test123 start<noinclude>\n" + "test123 end"));
	}

	public void testTemplateImage1() {
		// see method WikiTestModel#getRawWikiContent()
		assertEquals(
				"{|\n"
						+ "! | <h2 style=\"background:#cedff2;\">In the news</h2>\n"
						+ "|-\n"
						+ "| style=\"color:#000; padding:2px 5px;\" | <div id=\"mp-itn\">[[File:Yoshihiko Noda-1.jpg|Yoshihiko Noda|alt=Yoshihiko Noda]]\n"
						+ "The ruling Democratic Party of Japan selects '''Yoshihiko Noda''' ''(pictured)'' as the country's new prime minister, following the resignation of Naoto Kan\n"
						+ "</div>\n" + "|}",
				wikiModel
						.parseTemplates("{|\n"
								+ "! | <h2 style=\"background:#cedff2;\">In the news</h2>\n"
								+ "|-\n"
								+ "| style=\"color:#000; padding:2px 5px;\" | <div id=\"mp-itn\">{{Image\n"
								+ " |image  = Yoshihiko Noda-1.jpg\n"
								+ " |title  = Yoshihiko Noda\n"
								+ "}}\n"
								+ "The ruling Democratic Party of Japan selects '''Yoshihiko Noda''' ''(pictured)'' as the country's new prime minister, following the resignation of Naoto Kan\n"
								+ "</div>\n" + "|}"));
	}

	public void testInvalidIncludeonly() {
		assertEquals("test123 start", wikiModel.parseTemplates("test123 start<includeonly>\n" + "test123 end"));
	}

	public void testInvalidOnlyinclude() {
		assertEquals("test123 start\n" + 
				"test123 end", wikiModel.parseTemplates("test123 start<onlyinclude>\n" + "test123 end"));
	}

	public void testMagicCURRENTYEAR() {
		// assertEquals("test 2010 test123",
		// wikiModel.parseTemplates("test {{CURRENTYEAR}} test123"));
	}

	public void testMagicPAGENAME01() {
		assertEquals("test [[PAGENAME]] test123", wikiModel.parseTemplates("test [[{{PAGENAME}}]] test123"));
	}

	public void testMagicPAGENAME02() {
		assertEquals("test [[Sandbox]] test123", wikiModel.parseTemplates("test [[{{PAGENAME:Sandbox}}]] test123"));
	}

	public void testMagicTALKPAGENAME01() {
		assertEquals("test [[Talk:Sandbox]] test123", wikiModel.parseTemplates("test [[{{TALKPAGENAME:Sandbox}}]] test123"));
	}

	public void testMagicTALKPAGENAME02() {
		assertEquals("test [[Help_talk:Sandbox]] test123", wikiModel.parseTemplates("test [[{{TALKPAGENAME:Help:Sandbox}}]] test123"));
	}

	public void testMagicTALKPAGENAME03() {
		assertEquals("test [[Help_talk:Sandbox]] test123", wikiModel.parseTemplates("test [[{{TALKPAGENAME:\nHelp:Sandbox}}]] test123"));
	}

	// public void testRef001() {
	// assertEquals(
	// "",
	// wikiModel.parseTemplates("<ref>{{cite web |url=http://www.pottsmerc.com/articles/2009/04/12/opinion/srv0000005095974.txt |title=Actor Tom Hanks talks about religion |author=Terry Mattingly |work=The Mercury |date=April 12, 2009 |accessdate=October 19, 2010}}</ref>\n\n<references/>"));
	// }
	//
	//	
	// public void testCommonsCategory() {
	// assertEquals(
	// "",
	// wikiModel.parseTemplates("{{Commons category}}"));
	// }

	public void testTemplateSwitch() {
		// issue #32
		assertEquals(
				"1001",
				wikiModel
						.parseTemplates("{{#switch:y|y=1001|d={{#switch:w1001|w0=1|w-0=-8|{{#expr:\n"
								+ "1001*10{{#ifexpr:1001<0|-8|+1}}}}}}|c={{#expr:1001*100{{#ifexpr: 1001>0|-98|+1}}}}|m={{#expr:1001*1000{{#ifexpr:1001>0|-998|+1}}}}}}"));
	}

	public void testTitleparts000() {
		assertEquals("Talk:Foo/bar/baz/quok", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok }}", false));
	}

	public void testTitleparts001() {
		assertEquals("Talk:Foo", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | 1 }}", false));
	}

	public void testTitleparts002() {
		assertEquals("Talk:Foo/bar", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | 2 }}", false));
	}

	public void testTitleparts003() {
		assertEquals("Talk:Foo/bar/baz", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | 3 }}", false));
	}

	public void testTitlepartsn01() {
		assertEquals("Talk:Foo/bar/baz", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | -1 }}", false));
	}

	public void testTitlepartsn02() {
		assertEquals("Talk:Foo/bar", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | -2 }}", false));
	}

	public void testTitlepartsn03() {
		assertEquals("Talk:Foo", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | -3 }}", false));
	}

	public void testTitlepartsn04() {
		assertEquals("", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | -4 }}", false));
	}

	public void testTitlepartsn05() {
		assertEquals("", wikiModel.parseTemplates("{{#titleparts: Talk:Foo/bar/baz/quok | -5 }}", false));
	}

	public void testIssue77_001() {
		assertEquals(
				"{|\n"
						+ "! | <h2 style=\"background:#cedff2;\">In the news</h2>\n"
						+ "|-\n"
						+ "| | <div><div style=\"float:right;margin-left:0.5em;\">\n"
						+ "[[File:Yoshihiko Noda-1.jpg|100x100px||Yoshihiko Noda|alt=Yoshihiko Noda|link=File:Yoshihiko Noda-1.jpg]]\n"
						+ "</div>\n"
						+ "The ruling Democratic Party of Japan selects '''Yoshihiko Noda''' ''(pictured)'' as the country's new Prime Minister of Japan|prime minister, following the resignation of Naoto Kan.</div>\n"
						+ "|}",
				wikiModel
						.parseTemplates("{|\n"
								+ "! | <h2 style=\"background:#cedff2;\">In the news</h2>\n"
								+ "|-\n"
								+ "| | <div>{{In the news/image\n"
								+ " |image  = Yoshihiko Noda-1.jpg\n"
								+ " |size   = 100x100px\n"
								+ " |title  = Yoshihiko Noda\n"
								+ " |link   = \n"
								+ " |border = no\n"
								+ "}}\n"
								+ "The ruling Democratic Party of Japan selects '''Yoshihiko Noda''' ''(pictured)'' as the country's new Prime Minister of Japan|prime minister, following the resignation of Naoto Kan.</div>\n"
								+ "|}"));
	}

	public void testIssue77_002() {
		assertEquals(
				"{| style=\"width: 100%; height: auto; border: 1px solid #88A; background-color: #ACF; vertical-align: top; margin: 0em 0em 0.5em 0em; border-spacing: 0.6em;\" cellspacing=\"6\"\n"
						+ "|-\n"
						+ "\n"
						+ "| style=\"width: 100%; vertical-align:top; color:#000; border: 3px double #AAA; background-color: #ffffff; padding: 0.5em; margin: 0em;\" colspan=\"2\" |\n"
						+ "{| style=\"vertical-align: top; margin: 0em; width: 100% !important; width: auto; display: table !important; display: inline; background-color: transparent;\"\n"
						+ "\n"
						+ "! colspan=\"2\" style=\"background:#F0F0F0; margin: 0em; height: 1em; font-weight:bold; border:1px solid #AAA; text-align:left; color:#000;\" | <div style=\"float:right;\"></div><h1 style=\"text-align: left; font-size: 1.2em; border: none; margin: 0; padding: 1.5px 0 2px 4px;\">'''Knowledge groups'''</h1></div>\n"
						+ "|-\n"
						+ "|\n"
						+ "TEST1\n"
						+ "|}\n"
						+ "\n"
						+ "|-\n"
						+ "\n"
						+ "| style=\"width: 100%; vertical-align:top; color:#000; border: 3px double #AAA; background-color: #ffffff; padding: 0.5em; margin: 0em;\" colspan=\"2\" |\n"
						+ "{| style=\"vertical-align: top; margin: 0em; width: 100% !important; width: auto; display: table !important; display: inline; background-color: transparent;\"\n"
						+ "\n"
						+ "! colspan=\"2\" style=\"background:#F0F0F0; margin: 0em; height: 1em; font-weight:bold; border:1px solid #AAA; text-align:left; color:#000;\" | <div style=\"float:right;\"></div><h1 style=\"text-align: left; font-size: 1.2em; border: none; margin: 0; padding: 1.5px 0 2px 4px;\">'''Sister projects'''</h1></div>\n"
						+ "|-\n" + "|\n" + "TEST2\n" + "|}\n" + "|}", wikiModel.parseTemplates("{{Main Page panel|\n"
						+ "{{Main Page subpanel|column=both|title=Knowledge groups|1=\n" + "TEST1\n" + "}}\n" + "|\n"
						+ "{{Main Page subpanel|column=both|title=Sister projects|1=\n" + "TEST2\n" + "}}\n" + "}}"));
	}

	public void testIssue81_001() {
		assertEquals(" April 14 ", wikiModel.parseTemplates(" {{{1|April 14}}} "));
	}

	public void testIssue81_002() {
		assertEquals("104", wikiModel.parseTemplates("{{#time:z|{{{1|April 14}}}}}"));
	}

	public void testIssue82_001() {
		assertEquals("105", wikiModel.parseTemplates("{{#expr:{{#time:z|{{{1|April 14}}}}}+1}}"));
	}

	public void testIssue82_002() {
		assertEquals("105th", wikiModel.parseTemplates("{{ordinal|105}}"));
	}

	public void testIssue82_003() {
		assertEquals("105th", wikiModel.parseTemplates("{{ordinal|{{#expr:{{#time:z|{{{1|April 14}}}}}+1}}}}"));
	}

	public void testIssue82_004() {
		assertEquals("<div class=\"error\">Expression error: Unrecognised punctuation character: \"{\"</div>", wikiModel
				.parseTemplates("{{subst:#expr:{{#time:z|{{{1|April 14}}}}}+1}}"));
	}
}