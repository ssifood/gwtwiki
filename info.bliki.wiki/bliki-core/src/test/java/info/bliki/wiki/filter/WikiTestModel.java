package info.bliki.wiki.filter;

import info.bliki.htmlcleaner.ContentToken;
import info.bliki.htmlcleaner.TagNode;
import info.bliki.htmlcleaner.Utils;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.WikiModel;
import info.bliki.wiki.tags.IgnoreTag;
import info.bliki.wiki.tags.extension.ChartTag;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Wiki model implementation which allows some special JUnit tests with
 * namespaces and predefined templates
 * 
 */
public class WikiTestModel extends WikiModel {

	public static String RELLINK = "<div class=\"rellink{{#if:{{{extraclasses|}}}|<nowiki> </nowiki>{{{extraclasses}}}}}\">{{{1}}}</div><noinclude>\n"
			+ "{{documentation}}\n" + "</noinclude>\n" + "";

	public static String MAIN = "{{Rellink|extraclasses=relarticle mainarticle|{{#ifeq:{{SUBJECTSPACE}}|Category|The main {{#ifeq:{{NAMESPACE:{{{1}}}}}||article|page}}{{#if:{{{2|}}}|s}} for this [[Wikipedia:Categorization|category]] {{#if:{{{2|}}}|are|is}}|Main {{#ifeq:{{NAMESPACE:{{{1}}}}}||article|page}}{{#if:{{{2|}}}|s}}:}} [[{{{1|{{PAGENAME}}}}}|{{{l1|{{{1|{{PAGENAME}}}}}}}}]]{{#if:{{{2| }}}\n"
			+ " |{{#if:{{{3|}}}|,&#32;|&#32;and&#32;}}[[{{{2}}}|{{{l2|{{{2}}}}}}]]}}{{#if:{{{3|}}}\n"
			+ " |{{#if:{{{4|}}}|,&#32;|,&#32;and&#32;}}[[{{{3}}}|{{{l3|{{{3}}}}}}]]}}{{#if:{{{4|}}}\n"
			+ " |{{#if:{{{5|}}}|,&#32;|,&#32;and&#32;}}[[{{{4}}}|{{{l4|{{{4}}}}}}]]}}{{#if:{{{5|}}}\n"
			+ " |{{#if:{{{6|}}}|,&#32;|,&#32;and&#32;}}[[{{{5}}}|{{{l5|{{{5}}}}}}]]}}{{#if:{{{6|}}}\n"
			+ " |{{#if:{{{7|}}}|,&#32;|,&#32;and&#32;}}[[{{{6}}}|{{{l6|{{{6}}}}}}]]}}{{#if:{{{7|}}}\n"
			+ " |{{#if:{{{8|}}}|,&#32;|,&#32;and&#32;}}[[{{{7}}}|{{{l7|{{{7}}}}}}]]}}{{#if:{{{8|}}}\n"
			+ " |{{#if:{{{9|}}}|,&#32;|,&#32;and&#32;}}[[{{{8}}}|{{{l8|{{{8}}}}}}]]}}{{#if:{{{9|}}}\n"
			+ " |{{#if:{{{10|}}}|,&#32;|,&#32;and&#32;}}[[{{{9}}}|{{{l9|{{{9}}}}}}]]}}{{#if:{{{10|}}}\n"
			+ " |, and [[{{{10}}}|{{{l10|{{{10}}}}}}]]}}{{#if:{{{11| }}}|&#32; (too many parameters in &#123;&#123;[[Template:main|main]]&#125;&#125;)}}}}<noinclude>\n"
			+ "\n" + "{{Documentation}}\n" + "\n" + "</noinclude>";

	public static String SEE_ALSO  = "{{rellink|extraclasses=boilerplate seealso|{{{altphrase|See also}}}: {{#if:{{{1<includeonly>|</includeonly>}}} |<!--then:-->[[:{{{1}}}{{#if:{{{label 1|{{{l1|}}}}}}|{{!}}{{{label 1|{{{l1}}}}}}}}]] |<!--else:-->'''Error: [[Template:See also|Template must be given at least one article name]]'''\n" + 
	"}}{{#if:{{{2|}}}|{{#if:{{{3|}}}|, |&nbsp;and }} [[:{{{2}}}{{#if:{{{label 2|{{{l2|}}}}}}|{{!}}{{{label 2|{{{l2}}}}}}}}]]\n" + 
	"}}{{#if:{{{3|}}}|{{#if:{{{4|}}}|, |,&nbsp;and }} [[:{{{3}}}{{#if:{{{label 3|{{{l3|}}}}}}|{{!}}{{{label 3|{{{l3}}}}}}}}]]\n" + 
	"}}{{#if:{{{4|}}}|{{#if:{{{5|}}}|, |,&nbsp;and }} [[:{{{4}}}{{#if:{{{label 4|{{{l4|}}}}}}|{{!}}{{{label 4|{{{l4}}}}}}}}]]\n" + 
	"}}{{#if:{{{5|}}}|{{#if:{{{6|}}}|, |,&nbsp;and }} [[:{{{5}}}{{#if:{{{label 5|{{{l5|}}}}}}|{{!}}{{{label 5|{{{l5}}}}}}}}]]\n" + 
	"}}{{#if:{{{6|}}}|{{#if:{{{7|}}}|, |,&nbsp;and }} [[:{{{6}}}{{#if:{{{label 6|{{{l6|}}}}}}|{{!}}{{{label 6|{{{l6}}}}}}}}]]\n" + 
	"}}{{#if:{{{7|}}}|{{#if:{{{8|}}}|, |,&nbsp;and }} [[:{{{7}}}{{#if:{{{label 7|{{{l7|}}}}}}|{{!}}{{{label 7|{{{l7}}}}}}}}]]\n" + 
	"}}{{#if:{{{8|}}}|{{#if:{{{9|}}}|, |,&nbsp;and }} [[:{{{8}}}{{#if:{{{label 8|{{{l8|}}}}}}|{{!}}{{{label 8|{{{l8}}}}}}}}]]\n" + 
	"}}{{#if:{{{9|}}}|{{#if:{{{10|}}}|, |,&nbsp;and }} [[:{{{9}}}{{#if:{{{label 9|{{{l9|}}}}}}|{{!}}{{{label 9|{{{l9}}}}}}}}]]\n" + 
	"}}{{#if:{{{10|}}}|{{#if:{{{11|}}}|, |,&nbsp;and }} [[:{{{10}}}{{#if:{{{label 10|{{{l10|}}}}}}|{{!}}{{{label 10|{{{l10}}}}}}}}]]\n" + 
	"}}{{#if:{{{11|}}}|{{#if:{{{12|}}}|, |,&nbsp;and }} [[:{{{11}}}{{#if:{{{label 11|{{{l11|}}}}}}|{{!}}{{{label 11|{{{l11}}}}}}}}]]\n" + 
	"}}{{#if:{{{12|}}}|{{#if:{{{13|}}}|, |,&nbsp;and }} [[:{{{12}}}{{#if:{{{label 12|{{{l12|}}}}}}|{{!}}{{{label 12|{{{l12}}}}}}}}]]\n" + 
	"}}{{#if:{{{13|}}}|{{#if:{{{14|}}}|, |,&nbsp;and }} [[:{{{13}}}{{#if:{{{label 13|{{{l13|}}}}}}|{{!}}{{{label 13|{{{l13}}}}}}}}]]\n" + 
	"}}{{#if:{{{14|}}}|{{#if:{{{15|}}}|, |,&nbsp;and }} [[:{{{14}}}{{#if:{{{label 14|{{{l14|}}}}}}|{{!}}{{{label 14|{{{l14}}}}}}}}]]\n" + 
	"}}{{#if:{{{15|}}}|,&nbsp;and [[:{{{15}}}{{#if:{{{label 15|{{{l15|}}} }}}|{{!}}{{{label 15|{{{l15|}}} }}} }}]]\n" + 
	"}}{{#if:{{{16|}}}| &mdash; '''<br/>Error: [[Template:See also|Too many links specified (maximum is 15)]]'''\n" + 
	"}}}}<noinclude>\n" + 
	"{{documentation}}\n" + 
	"</noinclude>\n" + 
	"";
	
	public static String RNDFRAC = "<includeonly>{{#ifexpr:({{{2}}}-1)round0=abs({{{2}}}-1)|{{#ifexpr:{{{1}}}*{{{2}}}round0<0|−}}{{formatnum:{{rndfrac/out|{{#expr:floor((abs{{{1}}}*{{{2}}}round0)/{{{2}}})}}|{{#expr:(abs{{{1}}}*{{{2}}}round0)mod{{{2}}}}}|{{{2}}}|{{gcd|{{#expr:(abs{{{1}}}*{{{2}}}round0)mod{{{2}}}}}|{{{2}}}}}}}}}|{{color|red|This fractional rounding function only accepts positive intergers as the denominator.}}}}</includeonly><noinclude>{{documentation}}</noinclude>\n";
	public static String RNDFRAC_OUT = "<includeonly>{{#ifeq:{{{2}}}|0|{{{1}}}|<span class=\"frac nowrap\">{{#ifexpr:{{{1}}}>0|{{{1}}}<sup>&#32;</sup>}}{{#iferror:{{#expr:1/{{{4}}}}}|<sup>{{{2}}}</sup>&frasl;<sub>{{{3}}}</sub>|<sup>{{#expr:{{{2}}}/{{{4}}}}}</sup>&frasl;<sub>{{#expr:{{{3}}}/{{{4}}}}}</sub>}}</span>}}</includeonly><noinclude>{{documentation}}</noinclude>";
	public static String SORTNAME = "<includeonly><</includeonly><noinclude>&lt;</noinclude>span style=\"display:none;\">{{#if:{{{4|{{{sort|}}}}}} | {{{4|{{{sort}}}}}} | {{{2|{{{last}}}}}}, {{{1|{{{first}}}}}} }}<includeonly><</includeonly><noinclude>&lt;</noinclude>/span>{{#if:{{{nolink|}}}\n"
			+ "| <span class=\"vcard\"><span class=\"fn\">{{{1|{{{first}}}}}} {{{2|{{{last}}}}}}</span></span>\n"
			+ "| <span class=\"vcard\"><span class=\"fn\">[[{{#if:{{{3|{{{link|}}}}}}\n"
			+ "  | {{{3|{{{link}}}}}}|{{{1|{{{first}}}}}} {{{2|{{{last}}}}}} {{#if:{{{dab<includeonly>|</includeonly>}}}\n"
			+ "    | ({{{dab}}})\n"
			+ "    }}\n"
			+ "  }}|{{{1|{{{first}}}}}} {{{2|{{{last}}}}}}]]</span></span>\n"
			+ "}}{{#ifeq:{{NAMESPACE}}|{{ns:0}}|[[Category:Articles with hCards]]}}<noinclude>{{documentation}}</noinclude>";

	/**
	 * Issue 86
	 */
	public static String ONLYINCLUDE_DEMO = "abc<onlyinclude>def</onlyinclude>ghi<includeonly>jkl</includeonly><!---\n" + "-----\n"
			+ "----><noinclude><hr>\n" + ";Only active template content is above.\n" + "\n" + ";The verbatim active code within reads:\n"
			+ " abc'''&lt;onlyinclude>'''def'''&lt;/onlyinclude>'''ghi'''&lt;includeonly>'''jkl'''&lt;/includeonly>'''\n" + "\n"
			+ "If transposed, the only part included will be the string literal <code>def</code>. \n" + "\n" + "==Example==\n"
			+ "Including [[:Help:Template/onlyinclude demo]] yields only:\n" + " {{:Help:Template/onlyinclude demo}}\n" + "\n"
			+ "<includeonly>Then there's this other stuff one would think would be included... (I)\n" + "</includeonly><noinclude>\n"
			+ "\n" + "[[Category:Handbook templates]]</noinclude>\n" + "[[Category:Template documentation|{{PAGENAME}}]]\n"
			+ "</noinclude><includeonly>Then there's this other stuff one would think would be included (II)...</includeonly>\n" + "";
	/**
	 * Issue 86
	 */
	public static String TEST_INCLUDE = "<includeonly>{| class=\"wikitable float-right\" style=\"width:30%; min-width:250px; max-width:400px; font-size:90%; margin-top:0px;\"\n"
			+ "|--\n"
			+ "! colspan=\"2\" style=\"background-color:Khaki; font-size:110%;\" | [[Asteroid]]<br/>{{{Name}}}\n"
			+ "|--\n"
			+ "|}</includeonly>";

	/**
	 * Issue 86
	 */
	public static String TEST_INCLUDE2 = "<onlyinclude><includeonly>{| class=\"wikitable float-right\" style=\"width:30%; min-width:250px; max-width:400px; font-size:90%; margin-top:0px;\"\n"
			+ "|--\n"
			+ "! colspan=\"2\" style=\"background-color:Khaki; font-size:110%;\" | [[Asteroid]]<br/>{{{Name}}}\n"
			+ "|--\n"
			+ "|}</includeonly></onlyinclude>";

	/**
	 * Issue 86
	 */
	public static String TEST_INCLUDE3 = "<includeonly><noinclude>invisible</noinclude>text</includeonly>";

	/**
	 * Issue 86
	 */
	public static String TEST_INCLUDE4 = "Text <noinclude>invisible";

	/**
	 * http://www.mediawiki.org/wiki/Template:T1
	 */
	public static String T1 = "<noinclude>Used for demonstration purposes\n" + "----\n" + "</noinclude>start{{{1}}}end<noinclude>\n"
			+ "\n" + "[[Category:Demo templates]]</noinclude>";
	/**
	 * Issue 82
	 */
	public static String ORDINAL = "{{{1}}}{{{{{|safesubst:}}}#ifeq:{{{sup}}}|yes\n" + " |<sup>\n"
			+ "}}{{{{{|safesubst:}}}#switch:{{{{{|safesubst:}}}#expr:abs({{{1}}}) mod 100}}\n" + "  |11|12|13=th\n"
			+ "  |{{{{{|safesubst:}}}#switch:{{{{{|safesubst:}}}#expr:abs{{{1}}} mod 10}}\n" + "   |1=st\n"
			+ "   |2={{{{{|safesubst:}}}#ifeq:{{{2|}}}|d|d|nd}}\n" + "   |3={{{{{|safesubst:}}}#ifeq:{{{2|}}}|d|d|rd}}\n" + "   |th\n"
			+ "  }}\n" + " }}{{{{{|safesubst:}}}#ifeq:{{{sup}}}|yes\n" + " |</sup>\n" + "}}<noinclude>\n" + "{{documentation}}\n"
			+ "</noinclude>";
	/**
	 * Issue 77
	 */
	public static final String LAGEPLAN = "{{#if: {{{map|}}} | {{{!}} border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0 0 0 0; border-style:none; border-width:0px; border-collapse:collapse; empty-cells:show\"\n"
			+ "{{!}}<div style=\"position: relative;\"><div style=\"font-size: {{{markersize|5}}}px; position: absolute; display: block; left:{{#expr: ({{{mapsize_x|140}}}*{{{pos_x|3}}}/100  - {{{markersize|5}}} /2 ) round 0 }}px; top:{{#expr: ({{{mapsize_y|175}}}*{{{pos_y|3}}}/100 - {{{markersize|5}}} /2 ) round 0 }}px; padding:0;\">[[Bild:{{{marker|Reddot.svg}}}|{{{markersize|5}}}px|{{{markertext|}}}]]</div>[[Bild:{{{map|Karte Deutschland.png}}}|{{{mapsize_x|140}}}x{{{mapsize_y|175}}}px|{{{maptext|}}}]]</div>\n"
			+ "{{!}}} }}";
	/**
	 * Issue 77
	 */

	public static final String INFOBOX_ORT_IN_DEUTSCHLAND = "{| class=\"float-right\" style=\"width:290px; font-size:90%; background:#FAFAFA;  border:1px solid #bbb; margin:0px 0px 1em 1em; border-collapse:collapse;\" summary=\"Infobox\"\n"
			+ "|-\n"
			+ "| colspan=\"2\" style=\"background:#ffffff; text-align:center; font-size:135%;\" | '''{{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }}'''</font></br><small>{{{AndereNamen|}}}</small>\n"
			+ "|- class=\"hintergrundfarbe2\"\n"
			+ "| colspan=\"2\" style=\"font-weight:bold; padding-left:8px; border-top:solid 1px #bbb;\" |\n"
			+ "|- class=\"hintergrundfarbe2\" style=\"text-align: center;\"\n"
			+ "| style=\"width: 50%;\" | [[Bild:Sin escudo.svg|120px|Wappn fêîht]]\n"
			+ "| align=\"center\" style=\"width: 50%;\" | {{#if: {{{Karte|}}} | [[Bild:{{{Karte}}}|140x175px|Deitschlandkartn, Position vo {{#if: {{{Name|}}} | {{{Name}}} | {{PAGENAME}} }} heavoghobn]] | {{#if: {{{lat_deg|}}} |\n"
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
			+ "}}\n"
			+ "| [[Bild:Karte Deutschland.png|140px|Koordinatn san net da]] }}\n"
			+ "}}\n"
			+ "|-\n"
			+ "! colspan=\"2\" style=\"background-color:#ABCDEF; border:1px solid #bbb;\" | Basisdatn\n"
			+ "|- class=\"hintergrundfarbe2\"\n"
			+ "| '''[[Bundesland (Deutschland)|Bundesland]]''': || [[{{{Bundesland|}}}{{#if: {{{Bundesland im Dialekt|}}}|{{!}}{{{Bundesland im Dialekt}}}}}]]\n"
			+ "|- class=\"hintergrundfarbe2\"\n" + "|}";

	/**
	 * Issue 77
	 */
	public static final String MAIN_PAGE_PANEL = "{| style=\"width: 100%; height: auto; border: 1px solid #88A; background-color: #ACF; vertical-align: top; margin: 0em 0em 0.5em 0em; border-spacing: 0.6em;\" cellspacing=\"6\"\n"
			+ "|-\n"
			+ "{{{1}}}{{#if:{{{2|}}}|\n"
			+ "{{!}}-\n"
			+ "{{{2}}}{{#if:{{{3|}}}|\n"
			+ "{{!}}-\n"
			+ "{{{3}}}{{#if:{{{4|}}}|\n"
			+ "{{!}}-\n" + "{{{4}}}{{#if:{{{5|}}}|\n" + "{{!}}-\n" + "{{{5}}}}}}}}}}}\n" + "|}";
	/**
	 * Issue 77
	 */
	public static final String MAIN_PAGE_SUBPANEL = "| style=\"width: 100%; vertical-align:top; color:#000; border: 3px double #AAA; background-color: #ffffff; padding: 0.5em; margin: 0em;\" colspan=\"2\" |\n"
			+ "{| style=\"vertical-align: top; margin: 0em; width: 100% !important; width: auto; display: table !important; display: inline; background-color: transparent;\"\n"
			+ "{{#if:{{{title|}}}|\n"
			+ "! colspan=\"2\" style=\"background:#F0F0F0; margin: 0em; height: 1em; font-weight:bold; border:1px solid #AAA; text-align:left; color:#000;\" {{!}} <div style=\"float:right;\"></div><h1 style=\"text-align: left; font-size: 1.2em; border: none; margin: 0; padding: 1.5px 0 2px 4px;\">'''{{{title}}}'''</h1></div>}}\n"
			+ "|-\n" + "|\n" + "{{{1}}}\n" + "|}";

	public static final String YESNO = "{{#switch: {{lc: {{{1|¬}}} }}\n" + " |no\n" + " |n\n"
			+ " |0        = {{{no|<!-- null -->}}}\n" + " |         = {{{blank|{{{no|<!-- null -->}}}}}}\n" + " |¬        = {{{¬|}}}\n"
			+ " |yes\n" + " |y\n" + " |1        = {{{yes|yes}}}\n" + " |#default = {{{def|{{{yes|yes}}}}}}\n" + "}}";

	public static final String IN_THE_NEWS_IMAGE = "<div style=\"float:right;margin-left:0.5em;\">\n"
			+ "[[File:{{{image}}}|{{{size}}}|{{yesno|{{{border|}}}|yes=border}}|{{{title}}}|alt={{#if:{{{alt|}}}|{{{alt}}}|{{{title}}}}}|link=File:{{{image}}}]]\n"
			+ "</div>";

	public static final String IMAGE = "[[File:{{{image}}}|{{{title}}}|alt={{{title}}}]]";

	public static final String NOWRAP = "<span style=\"white-space:nowrap;\">{{{1}}}</span><noinclude>{{documentation}}<!--interwikis/categories go inside doc--></noinclude>";

	public static final String DOT_TEXT = "<span style=\"font-weight:bold;\">&nbsp;·</span> <noinclude>\n" + "\n"
			+ "{{pp-template|small=yes}}\n" + "{{documentation}}\n"
			+ "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	public static final String CITATION = "<includeonly>{{\n"
			+ "  #if: {{{inventor-surname|{{{inventor1-surname|{{{inventor-last|{{{inventor1-last|{{{inventor|}}}}}}}}}}}}}}}\n"
			+ "<!--\n"
			+ "    CITATIONS FOR PATENTS\n"
			+ "-->\n"
			+ "|{{Citation/patent\n"
			+ " |Surname1 = {{{inventor-surname|{{{inventor1-surname|{{{inventor-last|{{{inventor1-last|{{{inventor}}}}}}}}}}}}}}}\n"
			+ " |Surname2={{{inventor2-surname|{{{inventor2-last|{{{inventor2|}}}}}}}}}\n"
			+ " |Surname3={{{inventor3-surname|{{{inventor3-last|{{{inventor3|}}}}}}}}}\n"
			+ " |Surname4={{{inventor4-surname|{{{inventor4-last|{{{inventor4|}}}}}}}}}\n"
			+ " |Given1 = {{{inventor-given|{{{inventor1-given|{{{inventor-first|{{{inventor1-first|}}}}}}}}}}}}\n"
			+ " |Given2={{{inventor2-given|{{{inventor2-first|}}}}}}\n"
			+ " |Given3={{{inventor3-given|{{{inventor3-first|}}}}}}\n"
			+ " |Given4={{{inventor4-given|{{{inventor4-first|}}}}}}\n"
			+ " |Inventorlink1={{{inventorlink1|{{{inventorlink|}}}}}}\n"
			+ " |Inventorlink2={{{inventorlink2|}}}\n"
			+ " |Inventorlink3={{{inventorlink3|}}}\n"
			+ " |Inventorlink4={{{inventorlink4|}}}\n"
			+ " |Title={{{title|}}}\n"
			+ " |CountryCode={{{country-code}}}\n"
			+ " |PublicationNumber={{{publication-number|{{{patent-number}}}}}}\n"
			+ " |Description={{{description|}}}\n"
			+ " |PublicationDate={{{publication-date|}}}\n"
			+ " |IssueDate={{{issue-date|}}}\n"
			+ " |Year={{{year}}}\n"
			+ "}}<!--\n"
			+ "    CITATIONS FOR THINGS LIKE BOOKS AND PERIODICALS\n"
			+ "-->\n"
			+ "|{{Citation/core\n"
			+ "  |AuthorMask = {{{author-mask|{{{authormask|}}}}}}\n"
			+ "  |Surname1 = {{{last|{{{surname|{{{last1|{{{surname1|{{{author1|{{{author|{{{authors|{{{author|}}}}}}}}}}}}}}}}}}}}}}}}\n"
			+ "  |Surname2 = {{{last2|{{{surname2|{{{author2|{{{coauthor|{{{coauthors|}}}}}}}}}}}}}}}\n"
			+ "  |Surname3 = {{{last3|{{{surname3|{{{author3|}}}}}}}}}\n"
			+ "  |Surname4 = {{{last4|{{{surname4|{{{author4|}}}}}}}}}\n"
			+ "  |Surname5 = {{{last5|{{{surname5|{{{author5|}}}}}}}}}\n"
			+ "  |Surname6 = {{{last6|{{{surname6|{{{author6|}}}}}}}}}\n"
			+ "  |Surname7 = {{{last7|{{{surname7|{{{author7|}}}}}}}}}\n"
			+ "  |Surname8 = {{{last8|{{{surname8|{{{author8|}}}}}}}}}\n"
			+ "  |Surname9 = {{{last9|{{{surname9|{{{author9|}}}}}}}}}\n"
			+ "  |Given1 = {{{first1|{{{given1|{{{first|{{{given|}}}}}}}}}}}}\n"
			+ "  |Given2 = {{{first2|{{{given2|}}}}}}\n"
			+ "  |Given3 = {{{first3|{{{given3|}}}}}}\n"
			+ "  |Given4 = {{{first4|{{{given4|}}}}}}\n"
			+ "  |Given5 = {{{first5|{{{given5|}}}}}}\n"
			+ "  |Given6 = {{{first6|{{{given6|}}}}}}\n"
			+ "  |Given7 = {{{first7|{{{given7|}}}}}}\n"
			+ "  |Given8 = {{{first8|{{{given8|}}}}}}\n"
			+ "  |Given9 = {{{first9|{{{given9|}}}}}}\n"
			+ "  |Authorlink1 = {{{author-link|{{{author1-link|{{{authorlink|{{{authorlink1|}}}}}}}}}}}}\n"
			+ "  |Authorlink2 = {{{author2-link|{{{authorlink2|}}}}}}\n"
			+ "  |Authorlink3 = {{{author3-link|{{{authorlink3|}}}}}}\n"
			+ "  |Authorlink4 = {{{author4-link|{{{authorlink4|}}}}}}\n"
			+ "  |Authorlink5 = {{{author5-link|{{{authorlink5|}}}}}}\n"
			+ "  |Authorlink6 = {{{author6-link|{{{authorlink6|}}}}}}\n"
			+ "  |Authorlink7 = {{{author7-link|{{{authorlink7|}}}}}}\n"
			+ "  |Authorlink8 = {{{author8-link|{{{authorlink8|}}}}}}\n"
			+ "  |Authorlink9 = {{{author9-link|{{{authorlink9|}}}}}}\n"
			+ "  |Year={{{year|{{    <!-- attempt to derive year from date, if possible -->\n"
			+ "             #if: {{{date|}}}\n"
			+ "             |{{\n"
			+ "                #iferror:{{#time:Y|{{{date|}}} }}\n"
			+ "                |{{#iferror:{{#time:Y|{{{publication-date|einval}}} }}||{{#time:Y|{{{publication-date|}}} }}}}\n"
			+ "                |{{#time:Y|{{{date|}}} }}\n"
			+ "              }}\n"
			+ "             |{{{publication-date|}}} <!-- last resort -->\n"
			+ "           }}\n"
			+ "        }}}\n"
			+ "  |YearNote = {{{origyear|}}}\n"
			+ "  |Date = {{#if:{{{date|}}}|{{{date}}}|{{{day|}}} {{{month|}}} {{{year|{{{publication-date|}}}}}}}}\n"
			+ "  |DateFormat={{{dateformat|}}}\n"
			+ "  |Title={{{title|}}}\n"
			+ "  |URL={{#if:{{{archiveurl|}}}|{{{archiveurl|}}}|{{{url|}}}}}\n"
			+ "  |Series={{{series|{{{version|}}}}}}\n"
			+ "  |Periodical = {{{journal|{{{periodical|{{{newspaper|{{{magazine|{{{work|}}}}}}}}}}}}}}}\n"
			+ "  |Volume = {{{volume|}}}\n"
			+ "  |Issue = {{{issue|{{{number|}}}}}}\n"
			+ "  |At = {{\n"
			+ "          #if: {{{journal|{{{periodical|{{{newspaper|{{{magazine|}}}}}}}}}}}}\n"
			+ "          |{{{pages|{{{page|{{{at|}}}}}}}}}\n"
			+ "          |{{\n"
			+ "             #if: {{{page|}}}\n"
			+ "             |{{#if:{{{nopp|}}}||p.&nbsp;}}{{{page}}} \n"
			+ "             |{{\n"
			+ "                #if: {{{pages|}}}\n"
			+ "                |{{#if:{{{nopp|}}}||pp.&nbsp;}}{{{pages}}}\n"
			+ "                |{{{at|}}}\n"
			+ "              }}\n"
			+ "           }}\n"
			+ "        }}\n"
			+ "  |IncludedWorkTitle = {{{chapter|{{{contribution|}}}}}}\n"
			+ "  |IncludedWorkURL = {{{chapter-url|{{{chapterurl|{{{contribution-url|}}}}}}}}}\n"
			+ "  |Other = {{{others|}}}\n"
			+ "  |Edition = {{{edition|}}}\n"
			+ "  |Place = {{{place|{{{location|}}}}}}\n"
			+ "  |PublicationPlace = {{{publication-place|{{{place|{{{location|}}}}}}}}}\n"
			+ "  |Publisher = {{{publisher|}}}\n"
			+ "  |PublicationDate = {{{publication-date|}}}\n"
			+ "  |EditorSurname1 = {{{editor-last|{{{editor-surname|{{{editor1-last|{{{editor1-surname|{{{editor1|{{{editor|{{{editors|}}}}}}}}}}}}}}}}}}}}}\n"
			+ "  |EditorSurname2 = {{{editor2-last|{{{editor2-surname|{{{editor2|}}}}}}}}}\n"
			+ "  |EditorSurname3 = {{{editor3-last|{{{editor3-surname|{{{editor3|}}}}}}}}}\n"
			+ "  |EditorSurname4 = {{{editor4-last|{{{editor4-surname|{{{editor4|}}}}}}}}}\n"
			+ "  |EditorGiven1 = {{{editor-first|{{{editor-given|{{{editor1-first|{{{editor1-given|}}}}}}}}}}}}\n"
			+ "  |EditorGiven2={{{editor2-first|{{{editor2-given|}}}}}}\n"
			+ "  |EditorGiven3={{{editor3-first|{{{editor3-given|}}}}}}\n"
			+ "  |EditorGiven4={{{editor4-first|{{{editor4-given|}}}}}}\n"
			+ "  |Editorlink1={{{editor-link|{{{editor1-link|}}}}}}\n"
			+ "  |Editorlink2={{{editor2-link|}}}\n"
			+ "  |Editorlink3={{{editor3-link|}}}\n"
			+ "  |Editorlink4={{{editor4-link|}}}\n"
			+ "  |language = {{{language|{{{in|}}}}}}\n"
			+ "  |format = {{{format|}}}\n"
			+ "  |ID={{{id|{{{ID|}}}}}}\n"
			+ "  |ISBN={{{isbn|{{{ISBN|}}}}}}\n"
			+ "  |ISSN={{{issn|{{{ISSN|}}}}}}\n"
			+ "  |OCLC={{{oclc|{{{OCLC|}}}}}}\n"
			+ "  |PMID={{{pmid|{{{PMID|}}}}}}\n"
			+ "  |PMC={{{pmc|{{{PMC|}}}}}}\n"
			+ "  |Embargo={{{pmc-embargo-date|1010-10-10}}}\n"
			+ "  |Bibcode={{{bibcode|}}}\n"
			+ "  |DOI={{{doi|{{{DOI|}}}}}}\n"
			+ "  |DoiBroken={{{doi_brokendate|}}}\n"
			+ "  |AccessDate={{{access-date|{{{accessdate|}}}}}}\n"
			+ "  |laysummary = {{{laysummary|}}}\n"
			+ "  |quote = {{{quote|}}}\n"
			+ "  |laydate = {{{laydate|}}}\n"
			+ "  |Ref={{{ref|harv}}}\n"
			+ "  |Sep = {{#ifeq:{{{separator|{{{seperator}}}}}}|;|&#059;|{{{separator|{{{seperator|,}}}}}}}}\n"
			+ "  |PS = {{#if:{{{quote|}}}||{{{postscript|}}}}}\n"
			+ "  |AuthorSep = {{#ifeq:{{{author-separator|}}}|;|&#059;|{{{author-separator|&#059;}}}}}&#32;\n"
			+ "  |NameSep = {{{author-name-separator|,}}}&#32;\n"
			+ "  |amp = {{{lastauthoramp|}}}\n"
			+ "  |Trunc = {{#if:{{{display-authors|}}}|{{{display-authors}}}|8}}\n"
			+ "  |ArchiveURL= {{{archiveurl|}}}\n"
			+ "  |OriginalURL = {{{url|}}}|ArchiveDate= {{{archivedate|}}}\n"
			+ "}}}}{{#if:{{{accessdaymonth|}}}{{{accessmonthday|}}}{{{accessday|}}}{{{accessmonth|}}}{{{accessyear|}}}{{{day|}}}{{{access-date|}}}{{{dateformat|}}}|[[Category:Pages containing cite templates with deprecated parameters|{{NAMESPACE}} {{PAGENAME}}]]}}</includeonly><noinclude>\n"
			+ "{{Pp-template|small=yes}}\n" + "{{Documentation}}\n" + "</noinclude>";
	public final static String CITE_JOURNAL = "<includeonly>{{Citation/core\n"
			+ "  |Citation type=Journal\n"
			+ "  |AuthorMask = {{{author-mask|{{{authormask|}}}}}}\n"
			+ "  |Surname1 = {{{last|{{{surname|{{{last1|{{{surname1|{{{author1|{{{author|{{{authors|{{{author|}}}}}}}}}}}}}}}}}}}}}}}}\n"
			+ "  |Surname2 = {{{last2|{{{surname2|{{{author2|{{{coauthor|{{{coauthors|}}}}}}}}}}}}}}}\n"
			+ "  |Surname3 = {{{last3|{{{surname3|{{{author3|}}}}}}}}}\n"
			+ "  |Surname4 = {{{last4|{{{surname4|{{{author4|}}}}}}}}}\n"
			+ "  |Surname5 = {{{last5|{{{surname5|{{{author5|}}}}}}}}}\n"
			+ "  |Surname6 = {{{last6|{{{surname6|{{{author6|}}}}}}}}}\n"
			+ "  |Surname7 = {{{last7|{{{surname7|{{{author7|}}}}}}}}}\n"
			+ "  |Surname8 = {{{last8|{{{surname8|{{{author8|}}}}}}}}}\n"
			+ "  |Surname9 = {{{last9|{{{surname9|{{{author9|}}}}}}}}}\n"
			+ "  |Given1 = {{{first1|{{{given1|{{{first|{{{given|}}}}}}}}}}}}\n"
			+ "  |Given2 = {{{first2|{{{given2|}}}}}}\n"
			+ "  |Given3 = {{{first3|{{{given3|}}}}}}\n"
			+ "  |Given4 = {{{first4|{{{given4|}}}}}}\n"
			+ "  |Given5 = {{{first5|{{{given5|}}}}}}\n"
			+ "  |Given6 = {{{first6|{{{given6|}}}}}}\n"
			+ "  |Given7 = {{{first7|{{{given7|}}}}}}\n"
			+ "  |Given8 = {{{first8|{{{given8|}}}}}}\n"
			+ "  |Given9 = {{{first9|{{{given9|}}}}}}\n"
			+ "  |Authorlink1 = {{{author-link|{{{author1-link|{{{authorlink|{{{authorlink1|}}}}}}}}}}}}\n"
			+ "  |Authorlink2 = {{{author2-link|{{{authorlink2|}}}}}}\n"
			+ "  |Authorlink3 = {{{author3-link|{{{authorlink3|}}}}}}\n"
			+ "  |Authorlink4 = {{{author4-link|{{{authorlink4|}}}}}}\n"
			+ "  |Authorlink5 = {{{author5-link|{{{authorlink5|}}}}}}\n"
			+ "  |Authorlink6 = {{{author6-link|{{{authorlink6|}}}}}}\n"
			+ "  |Authorlink7 = {{{author7-link|{{{authorlink7|}}}}}}\n"
			+ "  |Authorlink8 = {{{author8-link|{{{authorlink8|}}}}}}\n"
			+ "  |Authorlink9 = {{{author9-link|{{{authorlink9|}}}}}}\n"
			+ "  |Year={{{year|{{    <!-- attempt to derive year from date, if possible -->\n"
			+ "             #if: {{{date|}}}\n"
			+ "             |{{\n"
			+ "                #iferror:{{#time:Y|{{{date|}}} }}\n"
			+ "                |{{#iferror:{{#time:Y|{{{publication-date|einval}}} }}||{{#time:Y|{{{publication-date|}}} }}}}\n"
			+ "                |{{#time:Y|{{{date|}}} }}\n"
			+ "              }}\n"
			+ "             |{{{publication-date|}}} <!-- last resort -->\n"
			+ "           }}\n"
			+ "        }}}\n"
			+ "  |Date = {{#if:{{{date|}}}|{{{date}}}|{{{day|}}} {{{month|}}} {{{year|{{{publication-date|}}}}}}}}\n"
			+ "  |Title={{{title|}}}\n"
			+ "  |TransTitle={{{trans_title|}}}\n"
			+ "  |URL={{#if:{{{archiveurl|}}}|{{{archiveurl}}}|{{{url|}}}}} \n"
			+ "  |Series={{{series|{{{version|}}}}}}\n"
			+ "  |Periodical = {{{journal|{{{periodical|{{{magazine|{{{work|}}}}}}}}}}}}\n"
			+ "  |Volume = {{{volume|}}}\n"
			+ "  |Issue = {{{issue|{{{number|}}}}}}\n"
			+ "  |At = {{\n"
			+ "          #if: {{{journal|{{{periodical|{{{magazine|{{{work|}}}}}}}}}}}}\n"
			+ "          |{{{pages|{{{page|{{{at|}}}}}}}}}\n"
			+ "          |{{\n"
			+ "             #if: {{{page|}}}\n"
			+ "             |p. {{{page}}}\n"
			+ "             |{{\n"
			+ "                #if: {{{pages|}}}\n"
			+ "                |pp. {{{pages}}}\n"
			+ "                |{{{at|}}}\n"
			+ "              }}\n"
			+ "           }}\n"
			+ "        }}\n"
			+ "  |IncludedWorkTitle = {{{chapter|{{{contribution|}}}}}}\n"
			+ "  |IncludedWorkURL = {{{chapter-url|{{{chapterurl|{{{contribution-url|}}}}}}}}}\n"
			+ "  |Edition = {{{edition|}}}\n"
			+ "  |Place = {{{place|{{{location|}}}}}}\n"
			+ "  |PublicationPlace = {{{publication-place|{{{place|{{{location|}}}}}}}}}\n"
			+ "  |Publisher = {{{publisher|}}}\n"
			+ "  |PublicationDate = {{{publication-date|}}}\n"
			+ "  |EditorSurname1 = {{{editor-last|{{{editor-surname|{{{editor1-last|{{{editor1-surname|{{{editor|{{{editors|}}}}}}}}}}}}}}}}}}\n"
			+ "  |EditorSurname2 = {{{editor2-last|{{{editor2-surname|}}}}}}\n"
			+ "  |EditorSurname3 = {{{editor3-last|{{{editor3-surname|}}}}}}\n"
			+ "  |EditorSurname4 = {{{editor4-last|{{{editor4-surname|}}}}}}\n"
			+ "  |EditorGiven1 = {{{editor-first|{{{editor-given|{{{editor1-first|{{{editor1-given|}}}}}}}}}}}}\n"
			+ "  |EditorGiven2={{{editor2-first|{{{editor2-given|}}}}}}\n"
			+ "  |EditorGiven3={{{editor3-first|{{{editor3-given|}}}}}}\n"
			+ "  |EditorGiven4={{{editor4-first|{{{editor4-given|}}}}}}\n"
			+ "  |Editorlink1={{{editor-link|{{{editor1-link|}}}}}}\n"
			+ "  |Editorlink2={{{editor2-link|}}}\n"
			+ "  |Editorlink3={{{editor3-link|}}}\n"
			+ "  |Editorlink4={{{editor4-link|}}}\n"
			+ "  |Other = {{{others|}}}\n"
			+ "  |language = {{{language|{{{in|}}}}}}\n"
			+ "  |format = {{{format|}}}\n"
			+ "  |ID={{{id|{{{ID|}}}}}}\n"
			+ "  |ISBN={{{isbn|{{{ISBN|}}}}}}\n"
			+ "  |ISSN={{{issn|{{{ISSN|}}}}}}\n"
			+ "  |OCLC={{{oclc|{{{OCLC|}}}}}}\n"
			+ "  |PMID={{{pmid|{{{PMID|}}}}}}\n"
			+ "  |PMC={{{pmc|{{{PMC|}}}}}}\n"
			+ "  |Embargo={{{pmc-embargo-date|1010-10-10}}}\n"
			+ "  |Bibcode={{{bibcode|}}}\n"
			+ "  |DOI={{{doi|{{{DOI|}}}}}}\n"
			+ "  |DoiBroken={{{doi_brokendate|}}}\n"
			+ "  |AccessDate={{{access-date|{{{accessdate|}}}}}}\n"
			+ "  |OriginalURL={{{url|}}}\n"
			+ "  |ArchiveURL={{{archiveurl|}}}\n"
			+ "  |ArchiveDate={{{archivedate|}}}\n"
			+ "  |laysource = {{{laysource|}}}\n"
			+ "  |laysummary = {{{laysummary|}}}\n"
			+ "  |laydate = {{{laydate|}}}\n"
			+ "  |quote = {{{quote|}}}\n"
			+ "  |Ref={{{ref|}}}\n"
			+ "  |Sep = {{#ifeq:{{{separator|{{{seperator}}} }}}|;|&#059;|{{{separator|{{{seperator|.}}} }}} }}\n"
			+ "  |PS = {{{postscript|.}}}\n"
			+ "  |AuthorSep = {{#ifeq:{{{author-separator|}}}|;|&#059;|{{{author-separator|&#059;}}}}}&#32;\n"
			+ "  |NameSep = {{{author-name-separator|,}}}&#32;\n"
			+ "  |Trunc = {{#if:{{{display-authors|}}}|{{{display-authors}}}|8}}\n"
			+ "  |amp = {{{use ampersand before last author|}}}\n"
			+ "}}{{#if:{{{accessdaymonth|}}}{{{accessmonthday|}}}{{{accessday|}}}{{{accessmonth|}}}{{{accessyear|}}}{{{day|}}}{{{access-date|}}}{{{dateformat|}}}|[[Category:Pages containing cite templates with deprecated parameters|{{NAMESPACE}} {{PAGENAME}}]]}}{{#if:{{{doilabel|}}}|[[Category:Citation templates using redundant parameters|{{NAMESPACE}} {{PAGENAME}}]]}}</includeonly><noinclude>\n"
			+ "{{Pp-template|small=yes}}\n" + "{{Documentation}}\n" + "</noinclude>";
	public final static String CITE_BOOK = "{{Citation/core\n"
			+ " |Citation class=book\n"
			+ "  |AuthorMask = {{{authormask|{{{author-mask|}}}}}}\n"
			+ "  |Surname1 = {{{last|{{{surname|{{{last1|{{{surname1|{{{author1|{{{author|{{{authors|{{{author|}}}}}}}}}}}}}}}}}}}}}}}}\n"
			+ "  |Surname2 = {{{last2|{{{surname2|{{{author2|{{{coauthor|{{{coauthors|}}}}}}}}}}}}}}}\n"
			+ "  |Surname3 = {{{last3|{{{surname3|{{{author3|}}}}}}}}}\n"
			+ "  |Surname4 = {{{last4|{{{surname4|{{{author4|}}}}}}}}}\n"
			+ "  |Surname5 = {{{last5|{{{surname5|{{{author5|}}}}}}}}}\n"
			+ "  |Surname6 = {{{last6|{{{surname6|{{{author6|}}}}}}}}}\n"
			+ "  |Surname7 = {{{last7|{{{surname7|{{{author7|}}}}}}}}}\n"
			+ "  |Surname8 = {{{last8|{{{surname8|{{{author8|}}}}}}}}}\n"
			+ "  |Surname9 = {{{last9|{{{surname9|{{{author9|}}}}}}}}}\n"
			+ "  |Given1 = {{{first1|{{{given1|{{{first|{{{given|}}}}}}}}}}}}\n"
			+ "  |Given2 = {{{first2|{{{given2|}}}}}}\n"
			+ "  |Given3 = {{{first3|{{{given3|}}}}}}\n"
			+ "  |Given4 = {{{first4|{{{given4|}}}}}}\n"
			+ "  |Given5 = {{{first5|{{{given5|}}}}}}\n"
			+ "  |Given6 = {{{first6|{{{given6|}}}}}}\n"
			+ "  |Given7 = {{{first7|{{{given7|}}}}}}\n"
			+ "  |Given8 = {{{first8|{{{given8|}}}}}}\n"
			+ "  |Given9 = {{{first9|{{{given9|}}}}}}\n"
			+ "  |Authorlink1 = {{{author-link|{{{author1-link|{{{authorlink|{{{authorlink1|}}}}}}}}}}}}\n"
			+ "  |Authorlink2 = {{{author2-link|{{{authorlink2|}}}}}}\n"
			+ "  |Authorlink3 = {{{author3-link|{{{authorlink3|}}}}}}\n"
			+ "  |Authorlink4 = {{{author4-link|{{{authorlink4|}}}}}}\n"
			+ "  |Authorlink5 = {{{author5-link|{{{authorlink5|}}}}}}\n"
			+ "  |Authorlink6 = {{{author6-link|{{{authorlink6|}}}}}}\n"
			+ "  |Authorlink7 = {{{author7-link|{{{authorlink7|}}}}}}\n"
			+ "  |Authorlink8 = {{{author8-link|{{{authorlink8|}}}}}}\n"
			+ "  |Authorlink9 = {{{author9-link|{{{authorlink9|}}}}}}\n"
			+ "  |Year={{{year|{{    <!-- attempt to derive year from date, if possible -->\n"
			+ "             #if: {{{date|}}}\n"
			+ "             |{{\n"
			+ "                #iferror:{{#time:Y|{{{date|}}} }}\n"
			+ "                |{{#iferror:{{#time:Y|{{{publication-date|einval}}} }}||{{#time:Y|{{{publication-date|}}} }}}}\n"
			+ "                |{{#time:Y|{{{date|}}} }}\n"
			+ "              }}\n"
			+ "             |{{{publication-date|}}} <!-- last resort -->\n"
			+ "           }}\n"
			+ "        }}}\n"
			+ "  |YearNote = {{{origyear|}}}\n"
			+ "  |Date = {{#if:{{{date|}}}|{{{date}}}|{{{day|}}} {{{month|}}} {{{year|{{{publication-date|}}}}}}}}\n"
			+ "  |Title={{{title|}}}\n"
			+ "  |TransTitle={{{trans_chapter|}}}\n"
			+ "  |TransItalic={{{trans_title|}}}\n"
			+ "  |URL={{{url|}}}\n"
			+ "  |TitleType={{{type|}}}\n"
			+ "  |Series={{{series|}}}\n"
			+ "  |Volume = {{{volume|}}}\n"
			+ "  |Issue = {{{issue|{{{number|}}}}}}\n"
			+ "  |At = {{\n"
			+ "          #if: {{{journal|{{{periodical|{{{newspaper|{{{magazine|}}}}}}}}}}}}\n"
			+ "          |{{{pages|{{{page|{{{at|}}}}}}}}}\n"
			+ "          |{{\n"
			+ "             #if: {{{page|}}}\n"
			+ "             |{{#if:{{{nopp|}}}||p.&nbsp;}}{{{page}}}\n"
			+ "             |{{\n"
			+ "                #if: {{{pages|}}}\n"
			+ "                |{{#if:{{{nopp|}}}||pp.&nbsp;}}{{{pages}}}\n"
			+ "                |{{{at|}}}\n"
			+ "              }}\n"
			+ "           }}\n"
			+ "        }}\n"
			+ "  |IncludedWorkTitle = {{{chapter|{{{contribution|}}}}}}\n"
			+ "  |IncludedWorkURL = {{{chapter-url|{{{chapterurl|{{{contribution-url|}}}}}}}}}\n"
			+ "  |Other = {{{others|}}}\n"
			+ "  |Edition = {{{edition|}}}\n"
			+ "  |Place = {{{place|{{{location|}}}}}}\n"
			+ "  |PublicationPlace = {{{publication-place|{{{place|{{{location|}}}}}}}}}\n"
			+ "  |Publisher = {{{publisher|}}}\n"
			+ "  |PublicationDate = {{{publication-date|}}}\n"
			+ "  |EditorSurname1 = {{{editor-last|{{{editor-surname|{{{editor1-last|{{{editor1-surname|{{{editor|{{{editors|}}}}}}}}}}}}}}}}}}\n"
			+ "  |EditorSurname2 = {{{editor2-last|{{{editor2-surname|}}}}}}\n"
			+ "  |EditorSurname3 = {{{editor3-last|{{{editor3-surname|}}}}}}\n"
			+ "  |EditorSurname4 = {{{editor4-last|{{{editor4-surname|}}}}}}\n"
			+ "  |EditorGiven1 = {{{editor-first|{{{editor-given|{{{editor1-first|{{{editor1-given|}}}}}}}}}}}}\n"
			+ "  |EditorGiven2={{{editor2-first|{{{editor2-given|}}}}}}\n"
			+ "  |EditorGiven3={{{editor3-first|{{{editor3-given|}}}}}}\n"
			+ "  |EditorGiven4={{{editor4-first|{{{editor4-given|}}}}}}\n"
			+ "  |Editorlink1={{{editor-link|{{{editor1-link|}}}}}}\n"
			+ "  |Editorlink2={{{editor2-link|}}}\n"
			+ "  |Editorlink3={{{editor3-link|}}}\n"
			+ "  |Editorlink4={{{editor4-link|}}}\n"
			+ "  |language = {{{language|{{{in|}}}}}}\n"
			+ "  |format = {{{format|}}}\n"
			+ "  |ID={{{id|{{{ID|}}}}}}\n"
			+ "  |ISBN={{{isbn|{{{ISBN|}}}}}}\n"
			+ "  |OCLC={{{oclc|{{{OCLC|}}}}}}\n"
			+ "  |Bibcode={{{bibcode|}}}\n"
			+ "  |DOI={{{doi|{{{DOI|}}}}}}\n"
			+ "  |DoiBroken={{{doi_brokendate|}}}\n"
			+ "  |AccessDate={{{access-date|{{{accessdate|}}}}}}\n"
			+ "  |DateFormat={{{dateformat|none}}}\n"
			+ "  |quote = {{{quote|}}}\n"
			+ "  |laysummary = {{{laysummary|}}}\n"
			+ "  |laydate = {{{laydate|}}}\n"
			+ "  |Ref={{{ref|}}}\n"
			+ "  |Sep = {{{separator|{{{seperator|.}}}}}}\n"
			+ "  |PS = {{#if:{{{quote|}}}||{{{postscript|.}}}}}\n"
			+ "  |AuthorSep = {{#ifeq:{{{author-separator|}}}|;|&#059;|{{{author-separator|&#059;}}}}}&#32;\n"
			+ "  |NameSep = {{{author-name-separator|,}}}&#32;\n"
			+ "  |Trunc = {{{display-authors|8}}}\n"
			+ "  |amp = {{{lastauthoramp|}}}\n"
			+ "}}{{#if:{{{accessdaymonth|}}}{{{accessmonthday|}}}{{{accessday|}}}{{{accessmonth|}}}{{{accessyear|}}}{{{day|}}}{{{access-date|}}}{{{dateformat|}}}|[[Category:Pages containing cite templates with deprecated parameters|{{NAMESPACE}} {{PAGENAME}}]]}}<noinclude>\n"
			+ "{{pp-template|small=yes}}\n" + "{{documentation}}\n" + "</noinclude>";
	public final static String CITATION_CORE = "<span class=\"citation {{{Citation class|{{{Citation type|}}}}}}\""
			+ "{{"
			+ "  #switch:{{{Ref|}}}"
			+ "  ||none ="
			+ "  |#default = id=\"{{anchorencode:{{{Ref}}}}}\""
			+ "  |harv = {{#if:{{{Surname1|}}}{{{EditorSurname1|}}}"
			+ "    |id=\"CITEREF{{anchorencode:{{#if:{{{Surname1|}}}"
			+ "      |{{{Surname1}}}{{{Surname2|}}}{{{Surname3|}}}{{{Surname4|}}}"
			+ "      |{{{EditorSurname1|}}}{{{EditorSurname2|}}}{{{EditorSurname3|}}}{{{EditorSurname4|}}}"
			+ "    }}{{{Year|{{{Date|}}}}}}}}\""
			+ "  }}"
			+ "}}>{{"
			+ "<!--============  Author or editor and date  ============-->"
			+ "  #if:{{{Surname1|}}}"
			+ "  |{{"
			+ "     #if: {{{AuthorMask|}}}"
			+ "     |{{"
			+ "        #iferror: {{ #expr: 1*{{{AuthorMask}}} }}"
			+ "        |{{{AuthorMask}}}"
			+ "        |<del>{{loop|{{{AuthorMask}}}|2=&emsp;}}</del>"
			+ "      }}"
			+ "     |{{"
			+ "        #if: {{{Authorlink1|}}}"
			+ "        |[[{{{Authorlink1}}} |{{{Surname1}}}{{"
			+ "          #if: {{{Given1|}}}"
			+ "          |{{{NameSep|,&#32;}}}{{{Given1}}}"
			+ "         }}]]"
			+ "        |{{{Surname1}}}{{"
			+ "           #if: {{{Given1|}}}"
			+ "           |{{{NameSep|,&#32;}}}{{{Given1}}}"
			+ "         }}"
			+ "      }}"
			+ "   }}{{"
			+ "     #if: {{{Surname2|}}}"
			+ "     |{{#ifexpr:{{{Trunc|8}}}<2"
			+ "       |&#32;''et al''."
			+ "       |{{"
			+ "          #iferror: {{ #expr: 1*0.0{{{AuthorMask|}}} }}"
			+ "          |&#32;<!-- then punctuation should be included in AuthorMask -->"
			+ "          |{{"
			+ "            #if: {{{Surname3|}}}"
			+ "            |{{{AuthorSep|&#059;&#32;}}}"
			+ "            |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "          }}"
			+ "        }}{{"
			+ "          #if: {{{Authorlink2|}}}"
			+ "          |[[{{{Authorlink2}}} |{{{Surname2}}}{{"
			+ "             #if: {{{Given2|}}}"
			+ "             |{{{NameSep|,&#32;}}}{{{Given2}}}"
			+ "           }}]]"
			+ "          |{{{Surname2}}}{{"
			+ "             #if: {{{Given2|}}}"
			+ "             |{{{NameSep|,&#32;}}}{{{Given2}}}"
			+ "           }}"
			+ "        }}{{"
			+ "          #if: {{{Surname3|}}}"
			+ "          |{{#ifexpr:{{{Trunc|8}}}<3"
			+ "            |&#32;''et al''."
			+ "            |{{"
			+ "               #if: {{{Surname4|}}}"
			+ "               |{{{AuthorSep|&#059;&#32;}}}"
			+ "               |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "             }}{{"
			+ "               #if: {{{Authorlink3|}}}"
			+ "               |[[{{{Authorlink3}}} |{{{Surname3}}}{{"
			+ "                  #if: {{{Given3|}}}"
			+ "                  |{{{NameSep|,&#32;}}}{{{Given3}}}"
			+ "                }}]]"
			+ "               |{{{Surname3}}}{{"
			+ "                  #if: {{{Given3|}}}"
			+ "                  |{{{NameSep|,&#32;}}}{{{Given3}}}"
			+ "                }}"
			+ "             }}{{"
			+ "               #if:{{{Surname4|}}}"
			+ "               |{{#ifexpr:{{{Trunc|8}}}<4"
			+ "                 |&#32;''et al''."
			+ "                 |{{"
			+ "                    #if: {{{Surname5|}}}"
			+ "                    |{{{AuthorSep|&#059;&#32;}}}"
			+ "                    |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "                  }}{{"
			+ "                    #if: {{{Authorlink4|}}}"
			+ "                    |[[{{{Authorlink4}}} |{{{Surname4}}}{{"
			+ "                       #if: {{{Given4|}}}"
			+ "                       |{{{NameSep|,&#32;}}}{{{Given4}}}"
			+ "                     }}]]"
			+ "                    |{{{Surname4}}}{{"
			+ "                       #if: {{{Given4|}}}"
			+ "                       |{{{NameSep|,&#32;}}}{{{Given4}}}"
			+ "                    }}"
			+ "                  }}{{"
			+ "                  #if:{{{Surname5|}}}"
			+ "                  |{{#ifexpr:{{{Trunc|8}}}<5"
			+ "                    |&#32;''et al''."
			+ "                    |{{"
			+ "                     #if: {{{Surname6|}}}"
			+ "                     |{{{AuthorSep|&#059;&#32;}}}"
			+ "                     |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "                    }}{{"
			+ "                     #if: {{{Authorlink5|}}}"
			+ "                     |[[{{{Authorlink5}}} |{{{Surname5}}}{{"
			+ "                        #if: {{{Given5|}}}"
			+ "                        |{{{NameSep|,&#32;}}}{{{Given5}}}"
			+ "                      }}]]"
			+ "                     |{{{Surname5}}}{{"
			+ "                        #if: {{{Given5|}}}"
			+ "                        |{{{NameSep|,&#32;}}}{{{Given5}}}"
			+ "                      }}"
			+ "                   }}{{"
			+ "                     #if:{{{Surname6|}}}"
			+ "                     |{{#ifexpr:{{{Trunc|8}}}<6"
			+ "                     |&#32;''et al''."
			+ "                      |{{"
			+ "                        #if: {{{Surname7|}}}"
			+ "                        |{{{AuthorSep|&#059;&#32;}}}"
			+ "                        |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "                      }}{{"
			+ "                        #if: {{{Authorlink6|}}}"
			+ "                        |[[{{{Authorlink6}}} |{{{Surname6}}}{{"
			+ "                           #if: {{{Given6|}}}"
			+ "                           |{{{NameSep|,&#32;}}}{{{Given6}}}"
			+ "                         }}]]"
			+ "                        |{{{Surname6}}}{{"
			+ "                           #if: {{{Given6|}}}"
			+ "                           |{{{NameSep|,&#32;}}}{{{Given6}}}"
			+ "                         }}"
			+ "                      }}{{"
			+ "                        #if:{{{Surname7|}}}"
			+ "                         |{{#ifexpr:{{{Trunc|8}}}<7"
			+ "                           |&#32;''et al''."
			+ "                        |{{"
			+ "                           #if: {{{Surname8|}}}"
			+ "                           |{{{AuthorSep|&#059;&#32;}}}"
			+ "                           |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "                         }}{{"
			+ "                           #if: {{{Authorlink7|}}}"
			+ "                           |[[{{{Authorlink7}}} |{{{Surname7}}}{{"
			+ "                              #if: {{{Given7|}}}"
			+ "                              |{{{NameSep|,&#32;}}}{{{Given7}}}"
			+ "                            }}]]"
			+ "                           |{{{Surname7}}}{{"
			+ "                              #if: {{{Given7|}}}"
			+ "                              |{{{NameSep|,&#32;}}}{{{Given7}}}"
			+ "                            }}"
			+ "                                    }}{{"
			+ "                                       #if:{{{Surname8|}}}"
			+ "                                       |{{#ifexpr:{{{Trunc|8}}}<8"
			+ "                                           |&#32;''et al''."
			+ "                                           |{{"
			+ "                                              #if: {{{Surname9|}}}"
			+ "                                              |{{{AuthorSep|&#059;&#32;}}}"
			+ "                                              |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "                                            }}{{"
			+ "                                              #if: {{{Authorlink8|}}}"
			+ "                                              |[[{{{Authorlink8}}} |{{{Surname8}}}{{"
			+ "                                                 #if: {{{Given8|}}}"
			+ "                                                 |{{{NameSep|,&#32;}}}{{{Given8}}}"
			+ "                                               }}]]"
			+ "                                              |{{{Surname8}}}{{"
			+ "                                                 #if: {{{Given8|}}}"
			+ "                                                 |{{{NameSep|,&#32;}}}{{{Given8}}}"
			+ "                                               }}"
			+ "                                            }}{{"
			+ "                                              #if:{{{Surname9|}}}"
			+ "                                              |&#32;''et al''."
			+ "                                            }}"
			+ "                                         }}"
			+ "                                      }}"
			+ "                                   }}"
			+ "                                }}"
			+ "                             }}"
			+ "                          }}"
			+ "                       }}"
			+ "                     }}"
			+ "                  }}"
			+ "               }}"
			+ "            }}"
			+ "         }}"
			+ "      }}"
			+ ""
			+ "   }}{{"
			+ "     #if: {{{Date|}}}"
			+ "     |&#32;({{{Date}}}){{"
			+ "       #if:{{{YearNote|}}}"
			+ "     |&#32;[{{{YearNote}}}]"
			+ "     }}"
			+ "   }}"
			+ "  |{{<!-- ============== No author: display editors first == -->"
			+ "     #if: {{{EditorSurname1|}}}"
			+ "     |{{"
			+ "        #if: {{{Editorlink1|}}}"
			+ "        |[[{{{Editorlink1}}} |{{{EditorSurname1}}}{{"
			+ "           #if: {{{EditorGiven1|}}}"
			+ "           |, {{{EditorGiven1}}}"
			+ "         }}]]"
			+ "        |{{{EditorSurname1}}}{{"
			+ "           #if: {{{EditorGiven1|}}}"
			+ "           |, {{{EditorGiven1}}}"
			+ "         }}"
			+ "      }}{{"
			+ "        #if: {{{EditorSurname2|}}}"
			+ "        |{{"
			+ "          #if: {{{EditorSurname3|}}}"
			+ "          |{{{AuthorSep|&#059;&#32;}}}"
			+ "          |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "        }}{{"
			+ "           #if: {{{Editorlink2|}}}"
			+ "           |[[{{{Editorlink2}}} |{{{EditorSurname2}}}{{"
			+ "              #if: {{{EditorGiven2|}}}"
			+ "              |, {{{EditorGiven2}}}"
			+ "            }}]]"
			+ "           |{{{EditorSurname2}}}{{"
			+ "              #if: {{{EditorGiven2|}}}"
			+ "              |, {{{EditorGiven2}}}"
			+ "            }}"
			+ "         }}{{"
			+ "           #if: {{{EditorSurname3|}}}"
			+ "           |{{"
			+ "              #if: {{{EditorSurname4|}}}"
			+ "              |{{{AuthorSep|&#059;&#32;}}}"
			+ "              |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "            }}{{"
			+ "              #if: {{{Editorlink3|}}}"
			+ "              |[[{{{Editorlink3}}} |{{{EditorSurname3}}}{{"
			+ "                 #if: {{{EditorGiven3|}}}"
			+ "                 |, {{{EditorGiven3}}}"
			+ "               }}]]"
			+ "              |{{{EditorSurname3}}}{{"
			+ "                 #if: {{{EditorGiven3|}}}"
			+ "                 |, {{{EditorGiven3}}}"
			+ "               }}"
			+ "            }}{{"
			+ "              #if:{{{EditorSurname4|}}}"
			+ "              |&#32;et al."
			+ "            }}"
			+ "         }}"
			+ "       }}, ed{{#if:{{{EditorSurname2|}}}|s}}{{#ifeq:{{{Sep|,}}}|.||.}}{{"
			+ "        #if: {{{Date|}}}"
			+ "        |&#32;({{{Date}}}){{"
			+ "         #if:{{{YearNote|}}}"
			+ "       |&#32;[{{{YearNote}}}]"
			+ "       }}"
			+ "      }}"
			+ "   }}"
			+ "}}{{"
			+ "<!--============  Title of included work  ============-->"
			+ "  #if: {{{IncludedWorkTitle|}}}{{#if:{{{Periodical|}}}||{{#if:{{{TransItalic|}}}||{{{TransTitle|}}}}}}}"
			+ "  |{{"
			+ "     #if:{{{Surname1|}}}{{{EditorSurname1|}}}"
			+ "     |{{{Sep|,}}}&#32;"
			+ "   }}{{Citation/make link"
			+ "     | 1={{"
			+ "           #if: {{{IncludedWorkURL|}}}"
			+ "           |{{{IncludedWorkURL}}}"
			+ "           |{{"
			+ "              #if: {{{URL|}}}"
			+ "              |{{{URL}}}"
			+ "<!-- Only link URL if to a free full text - as at PubMedCentral (PMC)-->"
			+ "              |{{#ifexpr:{{#time: U}} > {{#time: U | {{{Embargo|2001-10-10}}} }}"
			+ "                |{{"
			+ "                   #if: {{{PMC|}}}"
			+ "                   |http://www.pubmedcentral.nih.gov/articlerender.fcgi?tool=pmcentrez&artid={{{PMC}}}"
			+ "                 }}"
			+ "               }}"
			+ "            }}"
			+ "         }}"
			+ "     | 2={{"
			+ "           #if: {{{Periodical|}}}"
			+ "           |''<nowiki />{{{IncludedWorkTitle}}}<nowiki />''"
			+ "           |\"{{{IncludedWorkTitle|}}}{{"
			+ "             #if: {{{TransTitle|}}}"
			+ "             |{{"
			+ "                #if: {{{IncludedWorkTitle|}}}"
			+ "                |&#32;"
			+ "              }}&#91;{{{TransTitle}}}&#93;"
			+ "           }}\""
			+ "         }}"
			+ "   }}"
			+ "}}{{"
			+ "<!--============  Place (if different than PublicationPlace) ============-->"
			+ "  #if: {{{Place|}}}"
			+ "  |{{"
			+ "     #ifeq: {{{Place|}}} | {{{PublicationPlace|}}}"
			+ "     |"
			+ "     |{{"
			+ "        #if: {{{Surname1|}}}{{{EditorSurname1|}}}{{{IncludedWorkTitle|}}}"
			+ "        |{{{Sep|,}}}&#32;written at {{{Place}}}"
			+ "      }}"
			+ "   }}"
			+ "}}{{"
			+ "<!--============  Editor of compilation  ============-->"
			+ "  #if: {{{EditorSurname1|}}}"
			+ "  |{{"
			+ "     #if: {{{Surname1|}}}"
			+ "     |{{{Sep|,}}}&#32;{{"
			+ "        #if: {{{IncludedWorkTitle|}}}"
			+ "        |in&#32;"
			+ "      }}{{"
			+ "        #if: {{{Editorlink1|}}}"
			+ "        |[[{{{Editorlink1}}} |{{{EditorSurname1}}}{{"
			+ "           #if: {{{EditorGiven1|}}}"
			+ "           |, {{{EditorGiven1}}}"
			+ "         }}]]"
			+ "        |{{{EditorSurname1}}}{{"
			+ "           #if: {{{EditorGiven1|}}}"
			+ "           |, {{{EditorGiven1}}}"
			+ "         }}}}{{"
			+ "        #if: {{{EditorSurname2|}}}"
			+ "        |{{"
			+ "          #if: {{{EditorSurname3|}}}"
			+ "            |{{{AuthorSep|&#059;&#32;}}}"
			+ "            |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "          }}{{"
			+ "           #if: {{{Editorlink2|}}}"
			+ "           |[[{{{Editorlink2}}}|{{{EditorSurname2}}}{{"
			+ "              #if: {{{EditorGiven2|}}}"
			+ "              |, {{{EditorGiven2}}}"
			+ "            }}]]"
			+ "           |{{{EditorSurname2}}}{{"
			+ "              #if: {{{EditorGiven2|}}}"
			+ "              |, {{{EditorGiven2}}}"
			+ "            }}"
			+ "         }}{{"
			+ "           #if: {{{EditorSurname3|}}}"
			+ "           |{{"
			+ "              #if: {{{EditorSurname4|}}}"
			+ "              |&#059;&#32;"
			+ "              |{{#if:{{{amp|}}}|&#32;&amp;&#32;|{{{AuthorSep|&#059;&#32;}}}}}"
			+ "            }}{{"
			+ "              #if: {{{Editorlink3|}}}"
			+ "              |[[{{{Editorlink3}}}|{{{EditorSurname3}}}{{"
			+ "                 #if: {{{EditorGiven3|}}}"
			+ "                 |, {{{EditorGiven3}}}"
			+ "               }}]]"
			+ "              |{{{EditorSurname3}}}{{"
			+ "                 #if: {{{EditorGiven3|}}}"
			+ "                 |, {{{EditorGiven3}}}"
			+ "               }}"
			+ "            }}{{"
			+ "              #if:{{{EditorSurname4|}}}"
			+ "              |&#32;et al."
			+ "            }}"
			+ "         }}"
			+ "      }}{{"
			+ "        #if: {{{IncludedWorkTitle|}}}"
			+ "        |"
			+ "        |{{{Sep|,}}}&#32;ed{{#if:{{{EditorSurname2|}}}|s}}{{#ifeq:{{{Sep|,}}}|.||.}}"
			+ "      }}"
			+ "   }}"
			+ "}}{{"
			+ "  <!--============  Periodicals  ============-->"
			+ "  #if: {{{Periodical|}}}"
			+ "  |{{"
			+ "     #if: {{{Other|}}}"
			+ "     |{{{Sep|,}}}&#32;{{{Other|}}}"
			+ "   }}{{"
			+ "     #if: {{{Surname1|}}}{{{EditorSurname1|}}}{{{IncludedWorkTitle|}}}"
			+ "     |{{{Sep|,}}}&#32;}}{{"
			+ "     #if: {{{Title|}}}{{{TransTitle|}}}"
			+ "     |{{Citation/make link"
			+ "        | 1={{"
			+ "              #if: {{{IncludedWorkTitle|}}}"
			+ "              |{{"
			+ "                 #if: {{{IncludedWorkURL|}}}"
			+ "                 |{{"
			+ "                    #if: {{{URL|}}}"
			+ "                    |{{{URL}}}"
			+ "                    |{{"
			+ "                       #ifexpr: {{#time: U}} > {{#time: U | {{{Embargo|2001-10-10}}} }} | {{"
			+ "                         #if: {{{PMC|}}}"
			+ "                         |  http://www.pubmedcentral.nih.gov/articlerender.fcgi?tool=pmcentrez&artid={{{PMC}}}"
			+ "                       }}"
			+ "                     }}"
			+ "                  }}"
			+ "               }}"
			+ "              |{{"
			+ "                 #if: {{{URL|}}}"
			+ "                 |{{{URL}}}"
			+ "                 |{{#ifexpr:{{#time: U}} > {{#time: U | {{{Embargo|2001-10-10}}} }} |{{"
			+ "                       #if: {{{PMC|}}}"
			+ "                       |  http://www.pubmedcentral.nih.gov/articlerender.fcgi?tool=pmcentrez&artid={{{PMC}}}"
			+ "                    }}"
			+ "                 }}"
			+ "               }}"
			+ "            }}"
			+ "        | 2=\"{{{Title}}}{{"
			+ "          #if: {{{TransTitle|}}}"
			+ "          |{{"
			+ "             #if: {{{Title|}}}"
			+ "             |&#32;"
			+ "           }}&#91;{{{TransTitle}}}&#93;"
			+ "        }}\""
			+ "      }}{{"
			+ "        #if: {{{TitleNote|}}}"
			+ "        |{{{Sep|,}}}&#32;{{{TitleNote}}}"
			+ "      }}"
			+ "   }}"
			+ "}}{{"
			+ "  #if: {{{language|}}}"
			+ "  |&#32;(in {{{language}}})"
			+ "}}{{"
			+ "  #if: {{{format|}}}"
			+ "  |&#32;({{{format}}})"
			+ "}}{{"
			+ "   #if: {{{Periodical|}}}"
			+ "   |{{"
			+ "      #if:{{{IncludedWorkTitle|}}}{{{Title|}}}{{{TransTitle|}}}"
			+ "      |{{{Sep|,}}}&#32;"
			+ "    }}''<nowiki />{{{Periodical}}}<nowiki />''{{"
			+ "      #if: {{{Series|}}}"
			+ "      |{{{Sep|,}}}&#32;{{{Series}}}"
			+ "    }}{{"
			+ "      #if: {{{PublicationPlace|}}}"
			+ "      |{{"
			+ "         #if: {{{Publisher|}}}"
			+ "         |&#32;({{{PublicationPlace}}}<nowiki>: </nowiki>{{{Publisher}}})"
			+ "         |&#32;({{{PublicationPlace}}})"
			+ "       }}"
			+ "      |{{"
			+ "         #if: {{{Publisher|}}}"
			+ "         |&#32;({{{Publisher}}})"
			+ "       }}"
			+ "    }}{{"
			+ "      #if: {{{Volume|}}}"
			+ "      |&#32;'''<nowiki />{{{Volume}}}<nowiki />'''{{"
			+ "         #if: {{{Issue|}}}"
			+ "         |&#32;({{{Issue}}})"
			+ "       }}"
			+ "      |{{"
			+ "         #if: {{{Issue|}}}"
			+ "         |&#32;({{{Issue}}})"
			+ "       }}"
			+ "    }}{{"
			+ "      #if: {{{At|}}}"
			+ "      |<nowiki>: </nowiki> {{{At}}}"
			+ "    }}"
			+ "   |{{"
			+ "      <!--============ Anything else with a title, including books ============-->"
			+ "      #if: {{{Title|}}}{{{TransItalic|}}}"
			+ "      |{{"
			+ "         #if: {{{Surname1|}}}{{{EditorSurname1|}}}{{{IncludedWorkTitle|}}}{{{Periodical|}}}"
			+ "         |{{{Sep|,}}}"
			+ "       }}&#32;{{Citation/make link"
			+ "         | 1={{"
			+ "               #if: {{{IncludedWorkTitle|}}}"
			+ "               |{{"
			+ "                  #if: {{{IncludedWorkURL|}}}"
			+ "                  |{{"
			+ "                     #if: {{{URL|}}}"
			+ "                     |{{{URL}}}"
			+ "                     |{{#ifexpr:{{#time: U}} > {{#time: U | {{{Embargo|2001-10-10}}} }}|{{"
			+ "                        #if: {{{PMC|}}}"
			+ "                        |  http://www.pubmedcentral.nih.gov/articlerender.fcgi?tool=pmcentrez&artid={{{PMC}}}"
			+ "                      }}}}"
			+ "                   }}"
			+ "                }}"
			+ "               |{{"
			+ "                  #if: {{{URL|}}}"
			+ "                  |{{{URL}}}"
			+ "                                |{{#ifexpr:{{#time: U}} > {{#time: U | {{{Embargo|2001-10-10}}} }}|{{"
			+ "                     #if: {{{PMC|}}}"
			+ "                     |  http://www.pubmedcentral.nih.gov/articlerender.fcgi?tool=pmcentrez&artid={{{PMC}}}"
			+ "                   }}}}"
			+ "                }}"
			+ "             }}"
			+ "         | 2=''<nowiki />{{{Title|}}}{{"
			+ "            #if:{{{TransItalic|}}}|&#32;&#91;{{{TransItalic}}}&#93;"
			+ "          }}<nowiki />''"
			+ "       }}"
			+ "    }}{{"
			+ "      #if: {{{TitleType|}}}"
			+ "      |&#32;({{{TitleType}}})"
			+ "    }}{{"
			+ "      #if: {{{Series|}}}"
			+ "      |{{{Sep|,}}}&#32;{{{Series}}}"
			+ "    }}{{"
			+ "      #if: {{{Volume|}}}"
			+ "      |{{{Sep|,}}}&#32;'''<nowiki />{{{Volume}}}<nowiki />'''"
			+ "    }}{{"
			+ "      #if: {{{Other|}}}"
			+ "      |{{{Sep|,}}}&#32;{{{Other|}}}"
			+ "    }}{{"
			+ "      #if: {{{Edition|}}}"
			+ "      |&#32;({{{Edition}}} ed.)"
			+ "    }}{{"
			+ "      #if: {{{PublicationPlace|}}}"
			+ "      |{{{Sep|,}}}&#32;{{{PublicationPlace}}}"
			+ "    }}{{"
			+ "      #if: {{{Publisher|}}}"
			+ "      |{{"
			+ "         #if: {{{PublicationPlace|}}}"
			+ "         |<nowiki>:</nowiki>"
			+ "         |{{{Sep|,}}}"
			+ "       }}&#32;{{{Publisher}}}"
			+ "    }}"
			+ "}}{{"
			+ "<!--============ Date (if no author/editor) ============-->"
			+ "  #if: {{{Surname1|}}}{{{EditorSurname1|}}}"
			+ "  |"
			+ "  |{{"
			+ "     #if: {{{Date|}}}"
			+ "     |{{{Sep|,}}}&#32;{{{Date}}}{{"
			+ "       #if:{{{YearNote|}}}"
			+ "     |&#32;[{{{YearNote}}}]"
			+ "     }}"
			+ "   }}"
			+ "}}{{"
			+ "<!--============ Publication date ============-->"
			+ "  #if: {{{PublicationDate|}}}"
			+ "  |{{"
			+ "     #ifeq: {{{PublicationDate|}}} | {{{Date|}}}"
			+ "     |"
			+ "     |{{"
			+ "        #if: {{{EditorSurname1|}}}"
			+ "        |{{"
			+ "           #if: {{{Surname1|}}}"
			+ "           |{{{Sep|,}}}&#32;{{{PublicationDate}}}"
			+ "           |&#32;(published {{{PublicationDate}}})"
			+ "         }}"
			+ "        |{{"
			+ "           #if: {{{Periodical|}}}"
			+ "           |{{{Sep|,}}}&#32;{{{PublicationDate}}}"
			+ "           |&#32;(published {{{PublicationDate}}})"
			+ "         }}"
			+ "      }}"
			+ "   }}"
			+ "}}{{"
			+ "<!--============ Page within included work ============-->"
			+ "  #if: {{{Periodical|}}}"
			+ "  |"
			+ "  |{{"
			+ "     #if: {{{At|}}}"
			+ "     |{{{Sep|,}}}&#32;{{{At}}}"
			+ "   }}"
			+ "}}{{"
			+ "<!--===============DOI================-->"
			+ "#if:{{{DOI|}}}"
			+ "  |{{{Sep|,}}}&#32;[[Digital object identifier|doi]]:{{#if: {{{DoiBroken|}}}"
			+ "     | {{#tag:nowiki|{{{DOI}}}}} (inactive {{{DoiBroken|}}}) {{#ifeq: {{NAMESPACE}} | {{ns:0}} | [[Category:Pages with DOIs broken since {{#time: Y | {{{DoiBroken|}}} }}]] }}"
			+ "     | <span class=\"neverexpand\">[http://dx.doi.org/{{urlencode:{{{DOI}}}}} {{#tag:nowiki|{{{DOI}}}}}]</span>"
			+ "    }}"
			+ "}}{{"
			+ "<!--============ Misc. Identifier ============-->"
			+ "  #if: {{{ID|}}}"
			+ "  |{{"
			+ "     #if: {{{Surname1|}}}{{{EditorSurname1|}}}{{{IncludedWorkTitle|}}}{{{Periodical|}}}{{{Title|}}}{{{TransItalic|}}}"
			+ "     |{{{Sep|,}}}&#32;{{{ID}}}"
			+ "     |{{{ID}}}"
			+ "   }}"
			+ "}}{{"
			+ "<!--============ ISBN ============-->"
			+ "  #if: {{{ISBN|}}}"
			+ "  |{{{Sep|,}}}&#32;ISBN {{{ISBN}}}"
			+ "}}{{"
			+ "<!--============ ISSN ============-->"
			+ "  #if: {{{ISSN|}}}"
			+ "  |{{{Sep|,}}}&#32;[[International Standard Serial Number|ISSN]] [http://worldcat.org/issn/{{{ISSN}}} {{{ISSN}}}]"
			+ "}}{{"
			+ "<!--============ OCLC ============-->"
			+ "  #if: {{{OCLC|}}}"
			+ "  |{{{Sep|,}}}&#32;[[Online Computer Library Center|OCLC]] [http://worldcat.org/oclc/{{urlencode:{{{OCLC}}}}} {{{OCLC}}}]"
			+ "}}{{"
			+ "<!--============ PMID ============-->"
			+ "  #if: {{{PMID|}}}"
			+ "  |{{{Sep|,}}}&#32;[[PubMed Identifier|PMID]] [http://www.ncbi.nlm.nih.gov/pubmed/{{{PMID}}} {{{PMID}}}]"
			+ "}}{{"
			+ "<!--============ PMC ============-->"
			+ "  #if: {{{PMC|}}}"
			+ "  |{{"
			+ "     #if: {{{URL|}}}"
			+ "     |{{{Sep|,}}}&#32;[[PubMed Central|PMC]] [http://www.pubmedcentral.nih.gov/articlerender.fcgi?tool=pmcentrez&artid={{{PMC}}} {{{PMC}}}]"
			+ "   }}"
			+ "}}{{"
			+ "<!--============ BIBCODE ============-->"
			+ "  #if: {{{Bibcode|}}}"
			+ "  |{{{Sep|,}}}&#32;[[Bibcode]]:&nbsp;[http://adsabs.harvard.edu/abs/{{{Bibcode}}} {{{Bibcode}}}]"
			+ "}}{{"
			+ "<!--============ Archive data, etc ===========-->"
			+ "#if: {{{Archive|}}}"
			+ "|{{{Sep|,}}}&#32;{{{Archive}}}[[Category:Pages using deprecated citation archive parameters]]"
			+ "|{{"
			+ "  #if:{{{ArchiveURL|}}}{{{ArchiveDate|}}}"
			+ "  |{{{Sep|,}}}&#32;{{#ifeq:{{{Sep}}}|.|A|a}}rchived{{"
			+ "    #if:{{{OriginalURL|}}}{{{IncludedWorkURL|}}}"
			+ "    |{{#if:{{{ArchiveURL|}}}|&#32;from {{Citation/make link|{{{OriginalURL|{{{IncludedWorkURL|}}}}}}|the original}}}}"
			+ "    }}{{"
			+ "    #if:{{{ArchiveDate|}}}"
			+ "    |&#32;on {{{ArchiveDate}}}"
			+ "    }}{{"
			+ "    #if:{{#if:{{{ArchiveURL|}}}||A}}{{#if:{{{OriginalURL|}}}{{{IncludedWorkURL|}}}||B}}{{#if:{{{ArchiveDate|}}}||C}}"
			+ "    |. {{citation error"
			+ "       |If you specify <code>&#124;{{#if:{{{ArchiveURL|}}}|archiveurl|archivedate}}&#61;</code>, you must {{#if:{{{OriginalURL|}}}{{{IncludedWorkURL|}}}| also specify <code>&#124;{{#if:{{{ArchiveURL|}}}|archivedate|archiveurl}}&#61;</code>|first specify <code>&#124;url&#61;</code>}}}}"
			+ "    }}"
			+ "  }}"
			+ "}}{{"
			+ "<!--============ URL and AccessDate ============-->"
			+ "  #if: {{{URL|}}}{{{IncludedWorkURL|}}}"
			+ "  |{{"
			+ "     #if: {{{Title|}}}{{{IncludedWorkTitle|}}}{{{TransTitle|}}}"
			+ "     |<span class=\"printonly\">{{{Sep|,}}}&#32;{{"
			+ "                                      #if: {{{IncludedWorkURL|}}}"
			+ "                                      |{{{IncludedWorkURL}}}"
			+ "                                      |{{{URL}}}"
			+ "                                    }}</span>"
			+ "     |{{{Sep|,}}}&#32;{{"
			+ "              #if: {{{IncludedWorkURL|}}}"
			+ "              |{{{IncludedWorkURL}}}"
			+ "              |{{{URL}}}"
			+ "            }}"
			+ "   }}{{"
			+ "     #if: {{{AccessDate|}}}"
			+ "     | <span class=\"reference-accessdate\">{{#ifeq:{{{Sep|,}}}|,|,&#32;r|.&#32;R}}etrieved {{{AccessDate}}}</span>"
			+ "     }}"
			+ "}}{{#if:{{{laysummary|}}}"
			+ "  |{{{Sep|,}}}&#32;[{{{laysummary}}} Lay summary]{{#if: {{{laysource|}}}|&nbsp;&ndash;&nbsp;''<nowiki />{{{laysource}}}<nowiki />''}}"
			+ "}}{{#if:{{{laydate|}}}"
			+ "  | &#32;({{{laydate}}})"
			+ "}}{{#if:{{{quote|}}}"
			+ "  |{{{Sep|,}}}&#32;\"{{{quote}}}\""
			+ "}}{{{PS|}}}</span><!--"
			+ ""
			+ "=== This is a COinS tag (http://ocoins.info), which allows automated tools to parse the citation information: ==="
			+ ""
			+ "--><span"
			+ "    class=\"Z3988\""
			+ "    title=\"ctx_ver=Z39.88-2004&rft_val_fmt={{urlencode:info:ofi/fmt:kev:mtx:}}{{"
			+ "      #if: {{{Periodical|}}}"
			+ "      |journal&rft.genre=article&rft.atitle={{urlencode:{{{Title|}}}}}&rft.jtitle={{urlencode:{{{Periodical|}}}}}"
			+ "      |book{{"
			+ "         #if: {{{IncludedWorkTitle|}}}"
			+ "         |&rft.genre=bookitem&rft.btitle={{urlencode:{{{IncludedWorkTitle|}}}}}&rft.atitle={{urlencode:{{{Title|}}}}}"
			+ "         |&rft.genre=book&rft.btitle={{urlencode:{{{Title|}}}}}"
			+ "       }}"
			+ "    }}{{"
			+ "     #if: {{{Surname1|}}} |&rft.aulast={{urlencode:{{{Surname1}}}}}{{"
			+ "       #if: {{{Given1|}}} |&rft.aufirst={{urlencode:{{{Given1}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname1|}}} |&rft.au={{urlencode:{{{Surname1}}}}}{{"
			+ "       #if: {{{Given1|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given1}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname2|}}} |&rft.au={{urlencode:{{{Surname2}}}}}{{"
			+ "       #if: {{{Given2|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given2}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname3|}}} |&rft.au={{urlencode:{{{Surname3}}}}}{{"
			+ "       #if: {{{Given3|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given3}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname4|}}} |&rft.au={{urlencode:{{{Surname4}}}}}{{"
			+ "       #if: {{{Given4|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given4}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname5|}}} |&rft.au={{urlencode:{{{Surname5}}}}}{{"
			+ "       #if: {{{Given5|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given5}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname6|}}} |&rft.au={{urlencode:{{{Surname6}}}}}{{"
			+ "       #if: {{{Given6|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given6}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname7|}}} |&rft.au={{urlencode:{{{Surname7}}}}}{{"
			+ "       #if: {{{Given7|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given7}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname8|}}} |&rft.au={{urlencode:{{{Surname8}}}}}{{"
			+ "       #if: {{{Given8|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given8}}}}}"
			+ "     }}"
			+ "   }}{{"
			+ "     #if: {{{Surname9|}}} |&rft.au={{urlencode:{{{Surname9}}}}}{{"
			+ "       #if: {{{Given9|}}} |{{urlencode:{{{NameSep|,&#32;}}}{{{Given9}}}}}"
			+ "     }}"
			+ "    }}{{"
			+ "      #if: {{{Date|}}} |&rft.date={{urlencode:{{{Date}}}}}"
			+ "    }}{{"
			+ "      #if: {{{Series|}}} |&rft.series={{urlencode:{{{Series}}}}}"
			+ "    }}{{"
			+ "      #if: {{{Volume|}}} |&rft.volume={{urlencode:{{{Volume}}}}}"
			+ "    }}{{"
			+ "      #if: {{{Issue|}}} |&rft.issue={{urlencode:{{{Issue}}}}}"
			+ "    }}{{"
			+ "      #if: {{{At|}}} |&rft.pages={{urlencode:{{{At}}}}}"
			+ "    }}{{"
			+ "      #if: {{{Edition|}}} |&rft.edition={{urlencode:{{{Edition}}}}}"
			+ "    }}{{"
			+ "      #if: {{{PublicationPlace|}}}{{{Place|}}} |&rft.place={{urlencode:{{{PublicationPlace|{{{Place}}}}}}}}"
			+ "    }}{{"
			+ "      #if: {{{Publisher|}}} |&rft.pub={{urlencode:{{{Publisher}}}}}"
			+ "    }}{{"
			+ "      #if: {{{DOI|}}} |&rft_id=info:doi/{{urlencode:{{{DOI}}}}}"
			+ "    }}{{"
			+ "      #if: {{{PMID|}}} |&rft_id=info:pmid/{{urlencode:{{{PMID}}}}}"
			+ "    }}{{"
			+ "      #if: {{{Bibcode|}}} |&rft_id=info:bibcode/{{urlencode:{{{Bibcode}}}}}"
			+ "    }}{{"
			+ "      #if: {{{OCLC|}}} |&rft_id=info:oclcnum/{{urlencode:{{{OCLC}}}}}"
			+ "    }}{{"
			+ "      #if: {{{ISBN|}}} |&rft.isbn={{urlencode:{{{ISBN}}}}}"
			+ "    }}{{"
			+ "      #if: {{{ISSN|}}} |&rft.issn={{urlencode:{{{ISSN}}}}}"
			+ "    }}{{"
			+ "      #if: {{{URL|}}}{{{IncludedWorkURL|}}} |&rft_id={{urlencode:{{{URL|{{{IncludedWorkURL|}}}}}}}}"
			+ "    }}&rfr_id=info:sid/en.wikipedia.org:{{FULLPAGENAMEE}}\"><span style=\"display: none;\">&nbsp;</span></span><noinclude>"
			+ "{{Pp-template|small=yes}}" + "{{Documentation}}" + "</noinclude>";
	public final static String TC = "<noinclude>\n"
			+ "This template is used in various tutorial pages and should not be altered; it should say 'in' and nothing else.\n"
			+ "----\n" + "[[Category:Demo template]]\n" + "</noinclude>in\n" + "";
	public final static String BIRTH_DATE_AND_AGE = "<includeonly>{{#if:{{{df|}}}|{{#expr:{{{3|{{{day}}}}}}}} {{MONTHNAME|{{{2|{{{month}}}}}}}}|{{MONTHNAME|{{{2|{{{month}}}}}}}} {{#expr:{{{3|{{{day}}}}}}}},}} {{{1|{{{year}}}}}}<span style=\"display:none\"> (<span class=\"bday\">{{{1|{{{year}}}}}}-{{padleft:{{{2|{{{month}}}}}}|2|0}}-{{padleft:{{{3|{{{day}}}}}}|2|0}}</span>)</span><span class=\"noprint\"> (age&nbsp;{{age | {{{1|{{{year}}}}}} | {{{2|{{{month}}}}}} | {{{3|{{{day}}}}}} }})</span></includeonly><noinclude>\n"
			+ "{{pp-template|small=yes}}\n" + "{{documentation}}\n" + "</noinclude>";
	public final static String MONTHNAME = "<includeonly>{{#if:{{{1|}}}|{{#switch:{{MONTHNUMBER|{{{1}}}}}|1=January|2=February|3=March|4=April|5=May|6=June|7=July|8=August|9=September|10=October|11=November|12=December|Incorrect required parameter 1=''month''!}}|Missing required parameter 1=''month''!}}</includeonly><noinclude>\n"
			+ "\n"
			+ "{{pp-template|small=yes}}\n"
			+ "{{Documentation}}\n"
			+ "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";
	public final static String AGE = "<includeonly>{{#expr:({{{4|{{CURRENTYEAR}}}}})-({{{1}}})-(({{{5|{{CURRENTMONTH}}}}})<({{{2}}})or({{{5|{{CURRENTMONTH}}}}})=({{{2}}})and({{{6|{{CURRENTDAY}}}}})<({{{3}}}))}}</includeonly><noinclude>\n"
			+ "{{pp-template|small=yes}}\n" + "{{template doc}}\n" + "</noinclude>";
	public final static String MONTHNUMBER = "<includeonly>{{#if:{{{1|}}}\n" + " |{{#switch:{{lc:{{{1}}}}}\n" + "  |january|jan=1\n"
			+ "  |february|feb=2\n" + "  |march|mar=3\n" + "  |apr|april=4\n" + "  |may=5\n" + "  |june|jun=6\n" + "  |july|jul=7\n"
			+ "  |august|aug=8\n" + "  |september|sep=9\n" + "  |october|oct=10\n" + "  |november|nov=11\n" + "  |december|dec=12\n"
			+ "  |{{#ifexpr:{{{1}}}<0\n" + "   |{{#ifexpr:(({{{1}}})round 0)!=({{{1}}})\n"
			+ "    |{{#expr:12-(((0.5-({{{1}}}))round 0)mod 12)}}\n" + "    |{{#expr:12-(((11.5-({{{1}}}))round 0)mod 12)}}\n"
			+ "   }}\n" + "  |{{#expr:(((10.5+{{{1}}})round 0)mod 12)+1}}\n" + "  }}\n" + " }}\n"
			+ " |Missing required parameter 1=''month''!\n" + "}}</includeonly><noinclude>\n" + "{{pp-template|small=yes}}\n"
			+ "{{Documentation}}\n" + "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	public final static String BORN_DATA = "{{#if:{{{birthname|}}}|{{{birthname|}}}<br />}}{{#if:{{{birth_date|{{{birthdate|}}}}}}|{{{birth_date|{{{birthdate}}}}}}<br />}}{{{location|{{{birth_place|{{{birthplace|}}}}}}}}}";

	public final static String TL = "{{[[Template:{{{1}}}|{{{1}}}]]}}<noinclude>\n" + "{{pp-template|small=yes}}\n"
			+ "{{documentation}}\n" + "</noinclude>";
	public final static String PRON_ENG = "#REDIRECT [[Template:Pron-en]]";
	public final static String PRON_EN = "<onlyinclude>pronounced <span title=\"Pronunciation in the International Phonetic Alphabet (IPA)\" class=\"IPA\">[[WP:IPA for English|/{{{1}}}/]]</span></onlyinclude>\n"
			+ "\n"
			+ "==Example==\n"
			+ "\n"
			+ "This code:\n"
			+ "\n"
			+ ": <code><nowiki>A battleship, {{pron-en|ˈbætəlʃɪp}}, is ...</nowiki></code>\n"
			+ "\n"
			+ "will display:\n"
			+ "\n"
			+ ": A battleship, {{pron-en|ˈbætəlʃɪp}}, is ...\n"
			+ "\n"
			+ "==Usage==\n"
			+ "{{usage of IPA templates}}\n"
			+ "\n"
			+ "<!-- PLEASE ADD CATEGORIES BELOW THIS LINE, THANKS. -->\n"
			+ "\n"
			+ "[[Category:IPA templates|{{PAGENAME}}]]\n"
			+ "\n"
			+ "<!-- PLEASE ADD INTERWIKIS BELOW THIS LINE, THANKS. -->\n"
			+ "<noinclude>\n"
			+ "[[ar:PronEng]]\n"
			+ "[[tl:Template:PronEng]]\n" + "[[simple:Template:IPA-en]]\n" + "</noinclude>";

	public final static String HTML_START = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n"
			+ "   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
			+ "<head>\n" + "        <title>test</title>\n\n    </head>\n" + "    <body>";

	public final static String XHTML_START = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n"
			+ "   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n";

	public final static String XHTML_END = "</body>\n" + "</html>";

	public final static String PIPE_SYMBOL = "|<noinclude>{{template doc}}</noinclude>";
	public final static String DOUBLE_PARAMETER = "{{{1}}}{{{1}}}";
	public final static String REFLIST_TEXT = "<div class=\"references-small\" {{#if: {{{colwidth|}}}| style=\"-moz-column-width:{{{colwidth}}}; -webkit-column-width:{{{colwidth}}}; column-width:{{{colwidth}}};\" | {{#if: {{{1|}}}| style=\"-moz-column-count:{{{1}}}; -webkit-column-count:{{{1}}}; column-count:{{{1}}} }};\" |}}>\n"
			+ "<references /></div><noinclude>{{pp-template|small=yes}}{{template doc}}</noinclude>\n";

	public final static String CAT = "[[:Category:{{{1}}}]]<noinclude>\n"
			+ "{{Interwikitmp-grp ineligible|CAT|Commons|Wiktionary}}\n" + "Creates a link to the specified category.\n" + "\n"
			+ "\'\'\'Example\'\'\':\n" + "<pre>\n" + "{{Cat|stubs}}\n" + "</pre>\n" + "{{Cat|stubs}}\n" + "\n" + "==See also==\n"
			+ "* {{Tl|Cl}}\n" + "* {{Tl|Ccl}}\n" + "* {{Tl|Lcs}}\n" + "\n" + "[[sl:predloga:kat]]\n" + "</noinclude>\n" + "";

	public final static String CITE_WEB_TEXT = "<includeonly>{{\n"
			+ "#if: {{#if: {{{url|}}} | {{#if: {{{title|}}} |1}}}}\n"
			+ "  ||Fehler beim Aufruf der [[Vorlage:cite web]]: Die Parameter '''url''' und '''title''' müssen vorhanden sein.\n"
			+ "}}{{\n"
			+ "#if: {{{archiveurl|}}}{{{archivedate|}}}\n"
			+ "  | {{#if: {{#if: {{{archiveurl|}}}| {{#if: {{{archivedate|}}} |1}}}}\n"
			+ "    ||Fehler beim Aufruf der [[Vorlage:cite web]]: Die Parameter '''archiveurl''' und '''archivedate''' müssen beide vorhanden sein oder müssen beide fehlen.\n"
			+ "}}\n"
			+ "}}{{#if: {{{author|}}}{{{last|}}}\n"
			+ "  | {{#if: {{{authorlink|}}}\n"
			+ "    | [[{{{authorlink}}}|{{#if: {{{last|}}}\n"
			+ "      | {{{last}}}{{#if: {{{first|}}} | , {{{first}}} }}\n"
			+ "      | {{{author}}}\n"
			+ "    }}]]\n"
			+ "    | {{#if: {{{last|}}}\n"
			+ "      | {{{last}}}{{#if: {{{first|}}} | , {{{first}}} }}\n"
			+ "      | {{{author}}}\n"
			+ "    }}\n"
			+ "  }}\n"
			+ "}}{{#if: {{{author|}}}{{{last|}}}\n"
			+ "  | {{#if: {{{coauthors|}}}| <nowiki>;</nowiki>&#32;{{{coauthors}}} }}\n"
			+ "}}{{#if: {{{author|}}}{{{last|}}}|\n"
			+ "    {{#if: {{{date|}}}\n"
			+ "    | &#32;({{#iferror:{{FormatDate|{{{date}}}}}|{{{date}}}}})\n"
			+ "    | {{#if: {{{year|}}}\n"
			+ "      | {{#if: {{{month|}}}\n"
			+ "        | &#32;({{{month}}} {{{year}}})\n"
			+ "        | &#32;({{{year}}})\n"
			+ "      }}\n"
			+ "    }}\n"
			+ "  |}}\n"
			+ "}}{{#if: {{{last|}}}{{{author|}}}\n"
			+ "  | .&#32;}}{{#if: {{{archiveurl|}}}\n"
			+ "    | {{#if: {{{archiveurl|}}} | {{#if: {{{title|}}} | ''[{{{archiveurl}}} {{{title}}}]'' }}}}\n"
			+ "    | {{#if: {{{url|}}} | {{#if: {{{title|}}} | ''[{{{url}}} {{{title}}}]'' }}}}\n"
			+ "}}{{#if: {{{language|}}} | &#32;<span style=\"font-size: 0.90em; color:#555;\">({{{language}}})</span>\n"
			+ "}}{{#if: {{{format|}}}\n"
			+ "  | &#32;({{{format|}}})\n"
			+ "}}{{#if: {{{work|}}}\n"
			+ "  | .&#32;''{{{work}}}''\n"
			+ "}}{{#if: {{{pages|}}}\n"
			+ "  | &#32;S. {{{pages}}}\n"
			+ "}}{{#if: {{{publisher|}}}\n"
			+ "  | .&#32;{{{publisher}}}{{#if: {{{author|}}}{{{last|}}}\n"
			+ "    |\n"
			+ "    | {{#if: {{{date|}}}{{{year|}}}{{{month|}}} || }}\n"
			+ "  }}\n"
			+ "}}{{#if: {{{author|}}}{{{last|}}}\n"
			+ "  ||{{#if: {{{date|}}}\n"
			+ "    | &#32;({{#iferror:{{FormatDate|{{{date}}}}}|{{{date}}}}})\n"
			+ "    | {{#if: {{{year|}}}\n"
			+ "      | {{#if: {{{month|}}}\n"
			+ "        | &#32;({{{month}}} {{{year}}})\n"
			+ "        | &#32;({{{year}}})\n"
			+ "      }}\n"
			+ "    }}\n"
			+ "  }}\n"
			+ "}}.{{#if: {{{archivedate|}}}\n"
			+ "  | &#32;Archiviert vom [{{{url}}} Original] am {{#iferror: {{FormatDate|{{{archivedate}}} }} | {{{archivedate}}} }}.\n"
			+ "}}{{#if: {{{accessdate|}}}\n"
			+ "  | &#32;Abgerufen am {{#iferror: {{FormatDate|{{{accessdate}}} }} | {{{accessdate}}} }}.\n"
			+ "}}{{#if: {{{quote|}}}\n"
			+ "  | &nbsp;„{{{quote}}}“\n"
			+ "}}{{#if:{{{accessmonthday|}}}{{{accessyear|}}}|[[Kategorie:Seiten, die ignorierte Vorlagenargumente enthalten|{{PAGENAME}}]]}}</includeonly><noinclude>\n"
			+ "{{Dokumentation}}</noinclude>";

	public final static String INFOBOX_PROGRAMMIERSPRACHE = "{| class=\"prettytable float-right\" style=\"font-size:90%; width:21em;\"\n"
			+ "|- class=\"hintergrundfarbe6\"\n"
			+ "  ! colspan=\"2\" style=\"font-size:105%; text-align: center;\" | <big>{{{Name<includeonly>|{{PAGENAME}}</includeonly>}}}</big>\n"
			+ "|-\n"
			+ "  {{#if:{{{Logo<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} colspan=\"2\" style=\"text-align:center\" {{!}} {{{Logo}}}{{#if:{{{Beschreibung<includeonly>|</includeonly>}}}|<br />{{{Beschreibung}}}\n"
			+ "  }}\n"
			+ "  {{!~}} colspan=\"2\" class=\"hintergrundfarbe5\" style=\"font-size: 105%; text-align:center\" {{!}} Basisdaten\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Paradigma<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''[[Programmierparadigma|Paradigmen]]:'''\n"
			+ "  {{!}} {{{Paradigma}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Erscheinungsjahr<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Erscheinungsjahr:'''\n"
			+ "  {{!}} {{{Erscheinungsjahr}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Designer<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Designer:'''\n"
			+ "  {{!}} {{{Designer}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Entwickler<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Entwickler:'''\n"
			+ "  {{!}} {{{Entwickler}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{AktuelleVersion<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Aktuelle&nbsp;[[Versionierung|Version]]:'''\n"
			+ "  {{!}} style=\"white-space:nowrap;\" {{!}} {{{AktuelleVersion}}} &nbsp;<small>({{{AktuelleVersionFreigabeDatum}}})</small>\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{AktuelleVorabVersion<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Aktuelle&nbsp;Vorabversion:'''\n"
			+ "  {{!}} style=\"white-space:nowrap;\" {{!}} {{{AktuelleVorabVersion}}} &nbsp;<small>({{{AktuelleVorabVersionFreigabeDatum}}})</small>\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Typisierung<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''[[Typisierung (Informatik)|Typisierung]]:'''\n"
			+ "  {{!}} {{{Typisierung}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Implementierung<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''wichtige [[Implementierung]]en:'''\n"
			+ "  {{!}} {{{Implementierung}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Dialekte<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Dialekte:'''\n"
			+ "  {{!}} {{{Dialekte}}}\n"
			+ "  }}\n"
			+ "\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Standardisierungen<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Standardisierungen:'''\n"
			+ "  {{!}} {{{Standardisierungen}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Beeinflusst_von<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Einflüsse:'''\n"
			+ "  {{!}} {{{Beeinflusst_von}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Beeinflusste<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''Beeinflusste:'''\n"
			+ "  {{!}} {{{Beeinflusste}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Betriebssystem<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''[[Betriebssystem]]:'''\n"
			+ "  {{!}} {{{Betriebssystem}}}\n"
			+ "  }}\n"
			+ "|- valign=\"top\"\n"
			+ "  {{#if:{{{Lizenz<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} '''[[Lizenz]]:'''\n"
			+ "  {{!}} {{{Lizenz}}}\n"
			+ "  }}\n"
			+ "|- style=\"text-align:center;\" class=\"hintergrundfarbe5\"\n"
			+ "  {{#if:{{{Website<includeonly>|</includeonly>}}}|\n"
			+ "  {{!}} colspan=\"2\" {{!}} {{{Website}}}\n" + "  }}\n" + "|}<noinclude>{{Dokumentation}}</noinclude>";

	public final static String NESTED_TEMPLATE_TEST = "test a {{nested}} template";

	public final static String NESTED = "a nested template text";

	public final static String IFEQ_TEST = "{{#ifeq: {{{1}}}|{{{2}}} | {{{1}}} equals {{{2}}} | {{{1}}} is not equal {{{2}}}}}";

	public final static String ENDLESS_RECURSION_TEST = " {{recursion}} ";
	public final static String TNAVBAR_TEXT = "<includeonly>{{#if:{{{nodiv|}}}|<!--then:\n"
			+ "-->&nbsp;<span class=\"noprint plainlinksneverexpand\" style=\"white-space:nowrap; font-weight:normal; font-size:xx-small; {{{fontstyle|}}}; {{#if:{{{fontcolor|}}}|color:{{{fontcolor}}};}} {{{style|}}}\">|<!--else:\n"
			+ "--><div class=\"noprint plainlinksneverexpand\" style=\"background-color:transparent; padding:0; white-space:nowrap; font-weight:normal; font-size:xx-small; {{{fontstyle|}}}; {{#if:{{{fontcolor|}}}|color:{{{fontcolor}}};}} {{{style|}}}\"><!--\n"
			+ "-->}}<!--\n"
			+ "\n"
			+ "-->{{#ifeq:{{{mini|}}}{{{miniv|}}}{{{plain|}}}{{{viewplain|}}}|<!--equals:-->1|<!--then:\n"
			+ "-->|<!--else:\n"
			+ "-->This box:&nbsp;<!--\n"
			+ "-->}}<!--\n"
			+ "\n"
			+ "-->{{#ifeq:{{{miniv|}}}{{{viewplain|}}}|<!--equals:-->1|<!--then:\n"
			+ "-->[[Template:{{{1}}}|<span title=\"View this template\" style=\"{{{fontstyle|}}};{{#if:{{{fontcolor|}}}|color:{{{fontcolor}}};}}\">{{#if:{{{viewplain|}}}|view|v}}</span>]]|<!--else:\n"
			+ "-->[[Template:{{{1}}}|<span title=\"View this template\" style=\"{{{fontstyle|}}};{{#if:{{{fontcolor|}}}|color:{{{fontcolor}}};}}\">{{#if:{{{mini|}}}|v|view}}</span>]]&nbsp;<span style=\"font-size:80%;\">•</span>&nbsp;[[Template talk:{{{1}}}|<span style=\"color:#002bb8;{{{fontstyle|}}};{{#if:{{{fontcolor|}}}|color:{{{fontcolor}}};}}\" title=\"Discussion about this template\">{{#if:{{{mini|}}}|d|talk}}</span>]]&nbsp;<span style=\"font-size:80%;\">•</span>&nbsp;[{{fullurl:{{ns:10}}:{{{1}}}|action=edit}} <span style=\"color:#002bb8;{{{fontstyle|}}};{{#if:{{{fontcolor|}}}|color:{{{fontcolor}}};}}\" title=\"You can edit this template. Please use the preview button before saving.\">{{#if:{{{mini|}}}|e|edit}}</span>]<!--\n"
			+ "-->}}<!--\n" + "\n" + "-->{{#if:{{{nodiv|}}}|<!--then:\n" + "--></span>&nbsp;|<!--else:\n" + "--></div><!--\n"
			+ "-->}}</includeonly><noinclude>\n" + "\n" + "{{pp-template|small=yes}}\n"
			+ "<hr/><center>\'\'\'{{purge}}\'\'\' the Wikipedia cache of this template.<hr/></center><br/>\n" + "{{documentation}} \n"
			+ "<!--Note: Metadata (interwiki links, etc) for this template should be put on [[Template:Tnavbar/doc]]-->\n"
			+ "</noinclude>";
	public final static String NAVBOX_TEXT = "<!--\n" + 
			"\n" + 
			"Please do not edit without discussion first as this is a VERY complex template.\n" + 
			"\n" + 
			"-->{{#switch:{{{border|{{{1|}}}}}}|subgroup|child=</div>|none=|#default=<table cellspacing=\"0\" <!--\n" + 
			" -->class=\"navbox\" style=\"border-spacing:0;{{{bodystyle|}}};{{{style|}}}\"><tr><td style=\"padding:2px;\">}}<!--\n" + 
			"\n" + 
			"--><table cellspacing=\"0\" class=\"nowraplinks {{{bodyclass|}}} {{#if:{{{title|}}}|{{#switch:{{{state|}}}|<!--\n" + 
			" -->plain|off=|#default=collapsible {{#if:{{{state|}}}|{{{state}}}|autocollapse}}}}}} {{#switch:{{{border|{{{1|}}}}}}|<!--\n" + 
			" -->subgroup|child|none=navbox-subgroup\" style=\"border-spacing:0;{{{bodystyle|}}};{{{style|}}}|<!--\n" + 
			" -->#default=navbox-inner\" style=\"border-spacing:0;background:transparent;color:inherit}};{{{innerstyle|}}};\"><!--\n" + 
			"\n" + 
			"\n" + 
			"---Title and Navbar---\n" + 
			"-->{{#if:{{{title|}}}|<tr>{{#if:{{{titlegroup|}}}|<!--\n" + 
			" --><th scope=\"row\" class=\"navbox-group {{{titlegroupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{{groupstyle|}}};{{{titlegroupstyle|}}}\"><!--\n" + 
			" -->{{{titlegroup|}}}</th><th scope=\"col\" style=\"border-left:2px solid #fdfdfd;width:100%;|<!--\n" + 
			" --><th scope=\"col\" style=\"}}{{{basestyle|}}};{{{titlestyle|}}}\" class=\"navbox-title\" <!--\n" + 
			" -->colspan={{#expr:2{{#if:{{{imageleft|}}}|+1}}{{#if:{{{image|}}}|+1}}{{#if:{{{titlegroup|}}}|-1}}}}><!--\n" + 
			"\n" + 
			"-->{{#if:{{#switch:{{{navbar|}}}|plain|off=1}}<!--\n" + 
			" -->{{#if:{{{name|}}}||{{#switch:{{{border|{{{1|}}}}}}|subgroup|child|none=1}}}}|<!--\n" + 
			" -->{{#ifeq:{{{navbar|}}}|off|{{#ifeq:{{{state|}}}|plain|<span style=\"float:right;width:6em;\">&nbsp;</span>}}|<!--\n" + 
			" -->{{#ifeq:{{{state|}}}|plain||<span style=\"float:left;width:6em;\">&nbsp;</span>}}}}|<!--\n" + 
			" -->{{#if:{{{name|}}}|{{Navbar|{{{name}}}|mini=1|<!--\n" + 
			" -->fontstyle={{{basestyle|}}};{{{titlestyle|}}};background:none transparent;border:none;}}|<!--\n" + 
			" --><span class=\"error\" style=\"float:left;white-space:nowrap;\">Error: No name provided</span>}}<!--\n" + 
			" -->{{#ifeq:{{{state|}}}|plain|<span style=\"float:right;width:6em;\">&nbsp;</span>}}}}<!--\n" + 
			"\n" + 
			" --><div class=\"{{{titleclass|}}}\" style=\"font-size:110%;\">\n" + 
			"{{{title}}}</div></th></tr>}}<!--\n" + 
			"\n" + 
			"\n" + 
			"---Above---\n" + 
			"-->{{#if:{{{above|}}}|<!--\n" + 
			" -->{{#if:{{{title|}}}|<tr style=\"height:2px;\"><td></td></tr>}}<!--\n" + 
			" --><tr><td class=\"navbox-abovebelow {{{aboveclass|}}}\" style=\"{{{basestyle|}}};{{{abovestyle|}}}\" <!--\n" + 
			" -->colspan=\"{{#expr:2{{#if:{{{imageleft|}}}|+1}}{{#if:{{{image|}}}|+1}}}}\"><div>\n" + 
			"{{{above}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"\n" + 
			"---Body---\n" + 
			"\n" + 
			"---First group/list and images---\n" + 
			"-->{{#if:{{{list1|}}}|{{#if:{{{title|}}}{{{above|}}}|<tr style=\"height:2px;\"><td></td></tr>}}<tr><!--\n" + 
			"\n" + 
			"-->{{#if:{{{imageleft|}}}|<!--\n" + 
			" --><td class=\"navbox-image {{{imageclass|}}}\" style=\"width:0%;padding:0px 2px 0px 0px;{{{imageleftstyle|}}}\" <!--\n" + 
			" -->rowspan={{#expr:1{{#if:{{{list2|}}}|+2}}{{#if:{{{list3|}}}|+2}}{{#if:{{{list4|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list5|}}}|+2}}{{#if:{{{list6|}}}|+2}}{{#if:{{{list7|}}}|+2}}{{#if:{{{list8|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list9|}}}|+2}}{{#if:{{{list10|}}}|+2}}{{#if:{{{list11|}}}|+2}}{{#if:{{{list12|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list13|}}}|+2}}{{#if:{{{list14|}}}|+2}}{{#if:{{{list15|}}}|+2}}{{#if:{{{list16|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list17|}}}|+2}}{{#if:{{{list18|}}}|+2}}{{#if:{{{list19|}}}|+2}}{{#if:{{{list20|}}}|+2}}}}><div>\n" + 
			"{{{imageleft}}}</div></td>}}<!--\n" + 
			"\n" + 
			" -->{{#if:{{{group1|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group1style|}}}\"><!--\n" + 
			" -->{{{group1}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list1style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{list1padding|{{{listpadding|0em 0.25em}}}}}}\">\n" + 
			"{{{list1}}}</div></td><!--\n" + 
			"\n" + 
			"-->{{#if:{{{image|}}}|<!--\n" + 
			" --><td class=\"navbox-image {{{imageclass|}}}\" style=\"width:0%;padding:0px 0px 0px 2px;{{{imagestyle|}}}\" <!--\n" + 
			" -->rowspan={{#expr:1{{#if:{{{list2|}}}|+2}}{{#if:{{{list3|}}}|+2}}{{#if:{{{list4|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list5|}}}|+2}}{{#if:{{{list6|}}}|+2}}{{#if:{{{list7|}}}|+2}}{{#if:{{{list8|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list9|}}}|+2}}{{#if:{{{list10|}}}|+2}}{{#if:{{{list11|}}}|+2}}{{#if:{{{list12|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list13|}}}|+2}}{{#if:{{{list14|}}}|+2}}{{#if:{{{list15|}}}|+2}}{{#if:{{{list16|}}}|+2}}<!--\n" + 
			" -->{{#if:{{{list17|}}}|+2}}{{#if:{{{list18|}}}|+2}}{{#if:{{{list19|}}}|+2}}{{#if:{{{list20|}}}|+2}}}}><div>\n" + 
			"{{{image}}}</div></td>}}<!--\n" + 
			"\n" + 
			"--></tr>}}<!--\n" + 
			"\n" + 
			"\n" + 
			"---Remaining groups/lists---\n" + 
			"\n" + 
			"-->{{#if:{{{list2|}}}|<!--\n" + 
			" -->{{#if:{{{title|}}}{{{above|}}}{{{list1|}}}|<tr style=\"height:2px\"><td></td></tr>}}<tr><!--\n" + 
			" -->{{#if:{{{group2|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group2style|}}}\"><!--\n" + 
			" -->{{{group2}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list2style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list2}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list3|}}}|<!--\n" + 
			" -->{{#if:{{{title|}}}{{{above|}}}{{{list1|}}}{{{list2|}}}|<tr style=\"height:2px\"><td></td></tr>}}<tr><!--\n" + 
			" -->{{#if:{{{group3|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group3style|}}}\"><!--\n" + 
			" -->{{{group3}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list3style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list3}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list4|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group4|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group4style|}}}\"><!--\n" + 
			" -->{{{group4}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list4style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list4}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list5|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group5|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group5style|}}}\"><!--\n" + 
			" -->{{{group5}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list5style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list5}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list6|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group6|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group6style|}}}\"><!--\n" + 
			" -->{{{group6}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list6style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list6}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list7|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group7|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group7style|}}}\"><!--\n" + 
			" -->{{{group7}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list7style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list7}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list8|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group8|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group8style|}}}\"><!--\n" + 
			" -->{{{group8}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list8style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list8}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list9|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group9|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group9style|}}}\"><!--\n" + 
			" -->{{{group9}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list9style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list9}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list10|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group10|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group10style|}}}\"><!--\n" + 
			" -->{{{group10}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list10style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list10}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list11|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group11|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group11style|}}}\"><!--\n" + 
			" -->{{{group11}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list11style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list11}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list12|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group12|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group12style|}}}\"><!--\n" + 
			" -->{{{group12}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list12style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list12}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list13|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group13|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group13style|}}}\"><!--\n" + 
			" -->{{{group13}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list13style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list13}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list14|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group14|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group14style|}}}\"><!--\n" + 
			" -->{{{group14}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list14style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list14}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list15|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group15|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group15style|}}}\"><!--\n" + 
			" -->{{{group15}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list15style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list15}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list16|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group16|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group16style|}}}\"><!--\n" + 
			" -->{{{group16}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list16style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list16}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list17|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group17|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group17style|}}}\"><!--\n" + 
			" -->{{{group17}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list17style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list17}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list18|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group18|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group18style|}}}\"><!--\n" + 
			" -->{{{group18}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list18style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list18}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list19|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group19|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group19style|}}}\"><!--\n" + 
			" -->{{{group19}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{oddstyle|}}};{{{list19style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|even|{{{evenodd|odd}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list19}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{list20|}}}|<!--\n" + 
			" --><tr style=\"height:2px\"><td></td></tr><tr><!--\n" + 
			" -->{{#if:{{{group20|}}}|<th scope=\"row\" class=\"navbox-group {{{groupclass|}}}\" <!--\n" + 
			" -->style=\"{{{basestyle|}}};{{#if:{{{groupwidth|}}}|width:{{{groupwidth}}};}}{{{groupstyle|}}};{{{group20style|}}}\"><!--\n" + 
			" -->{{{group20}}}</th><td style=\"text-align:left;border-left-width:2px;border-left-style:solid;|<td colspan=2 style=\"}}<!--\n" + 
			" -->{{#if:{{{groupwidth|}}}||width:100%;}}padding:0px;{{{liststyle|}}};{{{evenstyle|}}};{{{list20style|}}}\" <!--\n" + 
			" -->class=\"navbox-list navbox-{{#ifeq:{{{evenodd|}}}|swap|odd|{{{evenodd|even}}}}} {{{listclass|}}}\"><!--\n" + 
			" --><div style=\"padding:{{{listpadding|0em 0.25em}}}\">\n" + 
			"{{{list20}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"\n" + 
			"---Below---\n" + 
			"-->{{#if:{{{below|}}}|<!--\n" + 
			" -->{{#if:{{{title|}}}{{{above|}}}{{{list1|}}}{{{list2|}}}{{{list3|}}}|<tr style=\"height:2px;\"><td></td></tr>}}<!--\n" + 
			" --><tr><td class=\"navbox-abovebelow {{{belowclass|}}}\" style=\"{{{basestyle|}}};{{{belowstyle|}}}\" <!--\n" + 
			" -->colspan=\"{{#expr:2{{#if:{{{imageleft|}}}|+1}}{{#if:{{{image|}}}|+1}}}}\"><div>\n" + 
			"{{{below}}}</div></td></tr>}}<!--\n" + 
			"\n" + 
			"\n" + 
			"--></table>{{#switch:{{{border|{{{1|}}}}}}|subgroup|child=<div>|none=|#default=</td></tr></table>}}<!--\n" + 
			"\n" + 
			"-->{{#ifeq:{{NAMESPACE}}|{{ns:10}}|{{#ifeq:{{{border|{{{1|}}}}}}|child||{{#ifeq:{{{border|{{{1|}}}}}}|subgroup||{{#switch:{{lc:{{SUBPAGENAME}}}}\n" + 
			" |doc\n" + 
			" |sandbox\n" + 
			" |testcases =\n" + 
			" |#default = {{#switch:{{{bodyclass|}}}\n" + 
			"  |plainlist\n" + 
			"  |hlist\n" + 
			"  |hlist hnum\n" + 
			"  |hlist vcard\n" + 
			"  |vcard hlist = \n" + 
			"  |#default = {{#switch:{{{listclass|}}}\n" + 
			"   |plainlist\n" + 
			"   |hlist\n" + 
			"   |hlist hnum\n" + 
			"   |hlist vcard\n" + 
			"   |vcard hlist = \n" + 
			"   |#default = {{#ifeq:{{{tracking|}}}|no||[[Category:Navigational boxes without horizontal lists]]}}\n" + 
			"   }}\n" + 
			"  }}\n" + 
			" }}\n" + 
			"}}}}}}<noinclude>\n" + 
			"\n" + 
			"{{documentation}}\n" + 
			"<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + 
			"</noinclude>";

	public final static String NAVBAR_TEXT = "<includeonly><div class=\"noprint plainlinks hlist navbar {{#if:{{{mini|}}}|mini}}\" style=\"{{{style|}}}\"><!--\n" + 
			"\n" + 
			"-->{{#if:{{{mini|}}}{{{plain|}}}|<!--nothing-->|<!--else:\n" + 
			"--><span style=\"word-spacing:0;{{{fontstyle|}}}\">{{{text|This box:}}} </span>}}<!--\n" + 
			"\n" + 
			"-->{{#if:{{{brackets|}}}|<span style=\"margin-right:-0.125em;{{{fontstyle|}}}\">&#91;</span>}}<!--\n" + 
			"\n" + 
			"--><ul><!--\n" + 
			"--><li class=\"nv-view\">[[{{transclude|{{{1}}}}}|<span title=\"View this template\" <!--\n" + 
			"-->style=\"{{{fontstyle|}}}\">{{#if:{{{mini|}}}|v|view}}</span>]]</li><!--\n" + 
			"\n" + 
			"--><li class=\"nv-talk\">[[{{TALKPAGENAME:{{transclude|{{{1}}}}}}}|<span title=\"Discuss this template\" <!--\n" + 
			"-->style=\"{{{fontstyle|}}}\">{{#if:{{{mini|}}}|t|talk}}</span>]]</li><!--\n" + 
			"\n" + 
			"-->{{#if:{{{noedit|}}}|<!--nothing-->|<!--else:\n" + 
			"--><li class=\"nv-edit\">[{{fullurl:{{transclude|{{{1}}}}}|action=edit}} <span title=\"Edit this template\" <!--\n" + 
			"-->style=\"{{{fontstyle|}}}\">{{#if:{{{mini|}}}|e|edit}}</span>]</li>}}<!--\n" + 
			"--></ul><!--\n" + 
			"\n" + 
			"-->{{#if:{{{brackets|}}}|<span style=\"margin-left:-0.125em;{{{fontstyle|}}}\">&#93;</span>}}<!--\n" + 
			"\n" + 
			"--></div></includeonly><noinclude>\n" + 
			"\n" + 
			"{{documentation}}\n" + 
			"</noinclude>";
	
	public final static String TRANSCLUDE = "{{#switch: {{NAMESPACE: {{{1}}} }}\r\n" + 
	" |#default = {{FULLPAGENAME: {{{1}}} }} <!-- eg \"User:Foo\" -->\r\n" + 
	" |{{ns:0}} = \r\n" + 
	"    {{#ifeq: {{NAMESPACE: {{{1}}} }} | {{NAMESPACE: Template{{{1}}} }}\r\n" + 
	"      | Template:{{{1}}}            <!-- no leading colon, eg \"Foo\" -->\r\n" + 
	"      | {{PAGENAME: {{{1}}} }}      <!-- leading colon, eg \":Foo\", so we want the article -->\r\n" + 
	"    }}\r\n" + 
	"}}<noinclude>\r\n" + 
	"{{documentation}}\r\n" + 
	"</noinclude>";
	public final static String INFOBOX_SOFTWARE_TEXT = "<includeonly>{| class=\"infobox\" cellspacing=\"5\" style=\"width: 21em; font-size: 90%; text-align: left;\"\n"
			+ "! colspan=\"2\" style=\"text-align: center; font-size: 130%;\" | {{{title|{{{name|{{PAGENAME}}}}}}}}\n"
			+ "|-\n"
			+ "{{#if:{{{logo|}}}|\n"
			+ "{{!}} colspan=\"2\" style=\"text-align: center;\" {{!}} {{{logo|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{collapsible|}}}|\n"
			+ "{{!}} colspan=\"2\" {{!}}\n"
			+ "{{hidden|Screenshot|{{{screenshot}}}{{#if:{{{caption|}}}|<br />{{{caption|}}}}}|bg1=#ccccff|ta2=center}}\n"
			+ "{{!}}-\n"
			+ "|\n"
			+ "{{#if:{{{screenshot|}}}|\n"
			+ "{{!}} colspan=\"2\" style=\"text-align: center;\" {{!}} {{{screenshot|}}}{{#if:{{{caption|}}}|<br />{{{caption|}}}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "}}\n"
			+ "{{#if:{{{author|}}}|\n"
			+ "! [[Software design|Design by]]\n"
			+ "{{!}} {{{author|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{developer|}}}|\n"
			+ "! [[Software developer|Developed by]]\n"
			+ "{{!}} {{{developer|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{released|}}}|\n"
			+ "! Initial release\n"
			+ "{{!}} {{{released|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{frequently updated|{{{frequently_updated|}}}}}}|<!-- then -->\n"
			+ "{{#ifexist:Template:Latest stable release/{{{name|{{PAGENAME}}}}}|\n"
			+ "! [[Software release|Stable release]]\n"
			+ "{{!}} {{Latest stable release/{{{name|{{PAGENAME}}}}}}} <sub class=\"plainlinks\">[[{{SERVER}}{{localurl:Template:Latest_stable_release/{{{name|{{PAGENAME}}}}}|action=edit&preload=Template:LSR/syntax}} +/−]]</sub>\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#ifexist:Template:Latest preview release/{{{name|{{PAGENAME}}}}}|\n"
			+ "! [[Software release|Preview release]]\n"
			+ "{{!}} {{Latest preview release/{{{name|{{PAGENAME}}}}}}} <sub class=\"plainlinks\">[[{{SERVER}}{{localurl:Template:Latest_preview_release/{{{name|{{PAGENAME}}}}}|action=edit&preload=Template:LPR/syntax}} +/−]]</sub>\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "|<!-- else -->\n"
			+ "{{#if:{{{latest release version|{{{latest_release_version|}}}}}}|\n"
			+ "! [[Software release life cycle|Latest release]]\n"
			+ "{{!}} {{{latest release version|{{{latest_release_version|}}}}}} {{#if:{{{latest release date|{{{latest_release_date|}}}}}}| / {{{latest release date|{{{latest_release_date|}}}}}}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{latest preview version|{{{latest_preview_version|}}}}}}|\n"
			+ "! [[Software release life cycle|Preview release]]\n"
			+ "{{!}} {{{latest preview version|{{{latest_preview_version|}}}}}} {{#if:{{{latest preview date|{{{latest_preview_date|}}}}}}| / {{{latest preview date|{{{latest_preview_date|}}}}}}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "}}\n"
			+ "{{#if:{{{programming language|{{{programming_language|}}}}}}|\n"
			+ "! [[Programming language|Written in]]\n"
			+ "{{!}} {{{programming language|{{{programming_language|}}}}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{operating system|{{{operating_system|}}}}}}|\n"
			+ "! [[Operating system|OS]]\n"
			+ "{{!}} {{{operating system|{{{operating_system}}}}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{platform|}}}|\n"
			+ "! [[Platform (computing)|Platform]]\n"
			+ "{{!}} {{{platform|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{size|}}}|\n"
			+ "! [[File size|Size]]\n"
			+ "{{!}} {{{size|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{language|}}}|\n"
			+ "! [[Language|Available in]]\n"
			+ "{{!}} {{{language|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{status|}}}|\n"
			+ "! Development status\n"
			+ "{{!}} {{{status|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{genre|}}}|\n"
			+ "! [[List of software categories|Genre]]\n"
			+ "{{!}} {{{genre|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{license|}}}|\n"
			+ "! [[Software license|License]]\n"
			+ "{{!}} {{{license|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{licence|}}}|\n"
			+ "! [[Software license|Licence]]\n"
			+ "{{!}} {{{licence|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{website|}}}|\n"
			+ "! [[Website]]\n"
			+ "{{!}} {{{website|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{standard|}}}|\n"
			+ "! [[Standard]](s)\n"
			+ "{{!}} {{{standard|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "{{#if:{{{AsOf|}}}|\n"
			+ "! As of\n"
			+ "{{!}} {{{AsOf|}}}\n"
			+ "{{!}}-\n"
			+ "}}\n"
			+ "|}</includeonly><noinclude>\n"
			+ "{{pp-template|small=yes}}\n"
			+ "{{documentation}}\n"
			+ "<!-- Add cats and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	final static String FURTHER = "<includeonly>:<span class=\"boilerplate further\"\n"
			+ ">''{{{altphrase|Further information}}}: {{#if:{{{1|}}} |<!--then:-->{{{1}}} |<!--\n"
			+ "else:-->'''Error: [[Template:Further|Template must be given at least one article name]]''' \n"
			+ "}}{{#if:{{{2|}}}|{{#if:{{{3|}}}|, |&#32;and }}  {{{2}}}\n" + "}}{{#if:{{{3|}}}|{{#if:{{{4|}}}|, |, and }} {{{3}}}\n"
			+ "}}{{#if:{{{4|}}}|{{#if:{{{5|}}}|, |, and }} {{{4}}}\n" + "}}{{#if:{{{5|}}}|, and {{{5}}}\n"
			+ "}}{{#if:{{{6|}}}| — '''<br/>Error: [[Template:Futher|Too many links specified (maximum is 5)]]'''\n"
			+ "}}''</span></includeonly><!-- includeonly block is needed, as otherwise the bare template gives error message \n"
			+ "\"Error: Template must be given at least one article name\"\n" + " ---><noinclude>\n" + "{{template doc}}</noinclude>";

	public final static String ANARCHISM_SIDEBAR = "{{Ideology\n"
			+ "|ideology = Anarchism \n"
			+ "|image    = [[File:Anarchy-symbol.svg|125px|\"Circle-A\" anarchy symbol]]\n"
			+ "\n"
			+ "|list1name  = Schools\n"
			+ "|list1title = [[Anarchist schools of thought|Schools]]\n"
			+ "|list1 = [[Agorism]]{{·}} [[Buddhist anarchism|Buddhist]]{{·}} [[Anarcho-capitalism|Capitalist]]<br/> [[Christian anarchism|Christian]]{{·}} [[Collectivist anarchism|Collectivist]]{{·}} [[Anarchist communism|Communist]]<br/> [[Crypto-anarchism]]{{·}} [[Anarcha-feminism|Feminist]]<br/> [[Free-market anarchism|Free market]]{{·}} [[Green anarchism|Green]]{{·}} [[Individualist anarchism|Individualist]]<br/> [[Infoanarchism]]{{·}} [[Insurrectionary anarchism|Insurrectionary]]<br/> [[Left anarchism|Leftist]]{{·}} [[Mutualism (economic theory)|Mutualist]]{{·}} [[Anarcho-pacifism|Pacifist]]<br/> [[Panarchism|Pananarchist]]{{·}} [[Philosophical anarchism|Philosophical]]<br/> [[Platformism|Platformist]]{{·}} [[Post-anarchism|Post-anarchist]]<br/> [[Post-colonial anarchism|Post-colonial]] {{·}}[[Post-left anarchy|Post-left]]<br/> [[Anarcho-primitivism|Primitivist]]{{·}} [[Social anarchism|Social]]{{·}} [[Anarcho-syndicalism|Syndicalist]]<br/>  [[Veganarchism|Vegan]]{{·}}[[Anarchism without adjectives|Without adjectives]]{{·}} [[Zenarchy|Zen]]\n"
			+ "\n"
			+ "|list2name  = Theory/Practice\n"
			+ "|list2title = Theory{{·}}Practice\n"
			+ "|list2 = [[Anarchy]]{{·}} [[Black bloc]]<br/> [[Class struggle]] {{·}} [[Commune (socialism)|Communes]]<br/> [[Consensus democracy]]<br/> [[Decentralization]]{{·}} [[Deep ecology]]<br/> [[Direct action]]{{·}} [[Direct democracy]]<br/> [[Dual power]]{{·}} [[Especifismo]]<br/> [[Horizontalidad]]{{·}} [[Illegalism]]<br/> [[Individual reclamation]]{{·}} [[Anarchist law|Law]]<br/> [[Participatory politics]]<br/> [[Permanent Autonomous Zone]]<br/> [[Prefigurative politics]]<br/> [[Private defense agency]]<br/> [[Propaganda of the deed]]<br/> [[Refusal of work]]{{·}} [[Rewilding (anarchism)|Rewilding]]<br/> [[Social ecology]]<br/> [[Spontaneous order]]\n"
			+ "\n"
			+ "|list3name  = Issues\n"
			+ "|list3title = [[Issues in anarchism|Issues]]\n"
			+ "|list3 = [[Anarchism and anarcho-capitalism|Anarcho-capitalism]]{{·}} [[Anarchism and animal rights|Animal rights]]<br/> [[Anarchism and capitalism|Capitalism]]{{·}} [[Criticisms of anarchism|Criticisms]]{{·}} [[Anarchism and Islam|Islam]]<br/> [[Lifestyle anarchism|Lifestylism]]{{·}} [[Anarchism and Marxism|Marxism]]{{·}} [[Anarchism and nationalism|Nationalism]]<br/> [[Anarchism and Orthodox Judaism|Orthodox Judaism]]{{·}} [[Anarchism and religion|Religion]]<br/> [[Anarchism and violence|Violence]]\n"
			+ "\n"
			+ "|list4name  = History\n"
			+ "|list4title = [[History of anarchism|History]]\n"
			+ "|list4 =  [[WTO Ministerial Conference of 1999 protest activity|1999 WTO Conference protest]]<br/>[[Amakasu Incident]]<br/> [[Anarchist Catalonia]]<br/> [[Anarchist Exclusion Act]]<br/> [[Anarchy in Somalia]]<br/> [[Australian Anarchist Centenary Celebrations|Australian Anarchist Centenary]]<br/> [[Barcelona May Days]]<br/> [[Carnival Against Capitalism]]<br/> [[Escuela Moderna]]{{·}} [[Hague Congress (1872)|Hague Congress]]<br/> [[Haymarket affair]]<br/> [[High Treason Incident]]<br/> [[International Anarchist Congress of Amsterdam|Congress of Amsterdam]]<br/> [[Kate Sharpley Library]]<br/> [[Kronstadt rebellion]]<br/> [[Labadie Collection]]{{·}} [[LIP (clockwork company)|LIP]]<br/> ''[[Manifesto of the Sixteen]]''<br/> [[May 1968]] {{·}} [[May Day]]<br/> [[Paris Commune]]<br/> [[Provo (movement)|Provo]]{{·}} [[Red inverted triangle]]<br/> {{longlink|[[Revolutionary Insurrectionary Army of Ukraine]]}} [[Spanish Revolution]]<br/> [[Left-wing uprisings against the Bolsheviks|Third Russian Revolution]]<br/> [[Tragic Week]]{{·}} [[Trial of the thirty]]\n"
			+ "\n"
			+ "|list5name  = Culture\n"
			+ "|list5title = Culture\n"
			+ "|list5 = [[Anarcho-punk]]{{·}} [[Anarchism and the arts|Arts]]<br/> [[Black anarchism]]{{·}} [[Culture jamming]]<br/> [[DIY culture]]{{·}} [[Freeganism]]<br/> [[Independent Media Center]]<br/> [[Infoshop]]{{·}} ''[[The Internationale]]''<br/> [[Jewish anarchism]]{{·}} [[Land and liberty (slogan)|Land and liberty]]<br/>  [[Lifestyle anarchism|Lifestylism]]{{·}} [[Popular education]]<br/>[[Property is theft!]]<br/> [[Radical cheerleading]]<br/> [[Radical environmentalism]]<br/> [[Squatting]]{{·}}[[Anarchist symbolism|Symbolism]]<br/> [[Anarchist terminology|Terminology]]{{·}}[[A las barricadas]] \n"
			+ "|list6name  = Economics\n"
			+ "|list6title = [[Anarchist economics|Economics]]\n"
			+ "|list6 = [[Agorism]]{{·}} [[Anarcho-capitalism|Capitalism]]{{·}} [[Collectivist anarchism|Collectivism]]<br/> [[Anarchist communism|Communism]]{{·}} [[Cooperative|Co-operatives]]<br/> [[Counter-economics]]{{·}} [[Free-market anarchism|Free market]]<br/> [[Free school]]{{·}} [[Give-away shop|Free store]]<br/> [[Geolibertarianism]]{{·}}[[Gift economy]]<br/> [[Market abolitionism]]{{·}}[[Mutual aid (politics)|Mutual aid]]<br/> [[Mutualism (economic theory)|Mutualism]]{{·}}[[Participatory economics]]<br/> [[Really Really Free Market]]<br/> [[Self-ownership]]{{·}}[[Anarcho-syndicalism|Syndicalism]]<br/> [[Wage slavery]]<br/> [[Workers' self-management]]\n"
			+ "\n"
			+ "|list7name  = By region\n"
			+ "|list7title = [[List of anarchist movements by region|By region]]\n"
			+ "|list7 = [[Anarchism in Africa|Africa]]{{·}} [[Anarchism in Austria-Hungary|Austria-Hungary]]{{·}} [[Anarchism in Brazil|Brazil]]<br/> [[Anarchism in Canada|Canada]]{{·}} [[Anarchism in China|China]]{{·}} [[Anarchism in Cuba|Cuba]]{{·}} [[Anarchism in England|England]]<br/> [[Anarchism in France|France]]{{·}} [[Anarchism in Greece|Greece]]{{·}} [[Anarchism in India|India]]{{·}} [[Anarchism in Ireland|Ireland]]<br/> [[Anarchism in Israel|Israel]]{{·}} [[Anarchism in Italy|Italy]]{{·}} [[Anarchism in Japan|Japan]]{{·}} [[Anarchism in Korea|Korea]]<br/> [[Anarchism in Mexico|Mexico]]{{·}} [[Anarchism in Poland|Poland]]{{·}} [[Anarchism in Russia|Russia]]{{·}} [[Anarchism in Spain|Spain]]<br/> [[Anarchism in Sweden|Sweden]]{{·}} [[Anarchism in Turkey|Turkey]]{{·}} [[Anarchism in Ukraine|Ukraine]]<br/> [[Anarchism in the United States|United States]]{{·}} [[Anarchism in Vietnam|Vietnam]]\n"
			+ "\n"
			+ "|list8name  = Lists\n"
			+ "|list8title = [[Lists of anarchism topics|Lists]]\n"
			+ "|list8 = [[List of anarcho-punk bands|Anarcho-punk bands]]{{·}} [[List of anarchist books|Books]]<br/> [[List of anarchist communities|Communities]]{{·}} [[List of fictional anarchists|Fictional characters]]<br/> [[List of Jewish anarchists|Jewish anarchists]]{{·}} [[List of anarchist musicians|Musicians]]<br/> [[List of anarchist organizations|Organizations]]{{·}} [[List of anarchist periodicals|Periodicals]]{{·}} [[List of anarchist poets|Poets]]<br/> [[List of Russian anarchists|Russian anarchists]]\n"
			+ "\n"
			+ "|list9name  = Related\n"
			+ "|list9title = Related topics\n"
			+ "|list9 = [[Anti-capitalism]]{{·}} [[Anti-communism#Anarchist anti-communism|Anti-communism]]<br/> [[Anti-consumerism]]{{·}} [[Anti-corporate activism|Anti-corporatism]]<br/> [[Anti-globalization]]{{·}} [[Antimilitarism]]<br/> [[Anti-statism]]{{·}} [[Anti-war]]{{·}} [[Autarchism]]<br/> [[Autonomism]]{{·}} [[Labour movement]]<br/> [[Left communism]]{{·}} [[Libertarianism]]<br/> {{longlink|[[Libertarian perspectives on revolution]]}} [[Libertarian socialism]]<br/> [[Situationist International]]\n"
			+ "}}<noinclude>\n" + "\n" + "[[Category:Anarchism templates| Anarchism]]\n" + "\n" + "[[ar:قالب:لاسلطوية]]\n"
			+ "[[ca:Plantilla:Anarquisme]]\n" + "[[da:Skabelon:Anarkisme]]\n" + "[[es:Plantilla:Anarquismo]]\n"
			+ "[[el:Πρότυπο:Αναρχισμός]]\n" + "[[he:תבנית:אנרכיזם]]\n" + "[[id:Templat:Anarkisme]]\n"
			+ "[[is:Snið:Stjórnleysisstefna]]\n" + "[[nl:Sjabloon:Anarchisme]]\n" + "[[pl:Szablon:Anarchizm]]\n"
			+ "[[pt:Predefinição:Anarquismo]]\n" + "[[ro:Format:Anarhism]]\n" + "[[ru:Шаблон:Анархизм]]\n"
			+ "[[sk:Šablóna:Anarchizmus]]\n" + "[[sl:Predloga:Anarhizem]]\n" + "[[sv:Mall:Anarkism]]\n" + "[[tr:Şablon:Anarşizm]]\n"
			+ "</noinclude>\n" + "";

	public final static String IDEOLOGY = "{{Sidebar with collapsible lists\n" + "|style      = width:18.0em;\n"
			+ "|pretitle   = Part of [[:Category:Politics|the Politics series]] on\n"
			+ "|title      = {{#if:{{{linkoverride|}}}|{{{linkoverride}}}|{{{ideology}}}}}\n"
			+ "|titlestyle = color:black; font-size:200%; font-weight:normal;\n" + "|image      = {{{image|}}}\n"
			+ "|imagestyle = {{{imagestyle|}}}\n"
			+ "|listtitlestyle = background:transparent; border-bottom:1px solid #aaa; text-align: center; padding-left:0.4em;\n" + "\n"
			+ "|list1name   = {{{list1name|}}}\n" + "|list1title  = {{{list1title|}}}\n" + "|list1       = {{{list1 |}}}\n"
			+ "|list2name   = {{{list2name|}}} \n" + "|list2title  = {{{list2title|}}}\n" + "|list2       = {{{list2|}}}\n"
			+ "|list3name   = {{{list3name|}}} \n" + "|list3title  = {{{list3title|}}}\n" + "|list3       = {{{list3 |}}} \n"
			+ "|list4name   = {{{list4name |}}}  \n" + "|list4title  = {{{list4title|}}} \n" + "|list4       = {{{list4 |}}}\n"
			+ "|list5name   = {{{list5name|}}}\n" + "|list5title  = {{{list5title|}}}\n" + "|list5       = {{{list5|}}}\n"
			+ "|list6name   = {{{list6name|}}}\n" + "|list6title  = {{{list6title|}}}\n" + "|list6       = {{{list6|}}}\n"
			+ "|list7name   = {{{list7name|}}}\n" + "|list7title  = {{{list7title|}}}  \n" + "|list7       = {{{list7|}}}\n"
			+ "|list8name   = {{{list8name|}}}\n" + "|list8title  = {{{list8title|}}}\n" + "|list8       = {{{list8|}}}\n"
			+ "|list9name   = {{{list9name|}}}\n" + "|list9title  = {{{list9title|}}}\n" + "|list9       = {{{list9 |}}}\n"
			+ "|list10name  = {{{list10name|}}}\n" + "|list10title = {{{list10title|}}}\n" + "|list10      = {{{list10|}}}\n" + "\n"
			+ "|belowstyle = text-align:center;\n" + "|below = '''[[:portal:{{{ideology}}}|{{{ideology}}} Portal]]'''\n"
			+ "}}<noinclude>\n" + "</noinclude>\n" + "";

	public final static String SIDEBAR_WITH_COLLAPSIBLE_LISTS = "<noinclude>{{start sidebar page}}\n"
			+ "</noinclude>{{Sidebar\n"
			+ "|name            = {{{name|{{PAGENAME}}}}}\n"
			+ "|class           = {{{class|{{{bodyclass|}}}}}}\n"
			+ "|style           = {{{style|{{{bodystyle|}}}}}} <noinclude>width:15.0em;</noinclude>\n"
			+ "|cellspacing     = {{{cellspacing|}}}\n"
			+ "|cellpadding     = {{{cellpadding|}}}\n"
			+ "\n"
			+ "|outertitle      = {{{outertitle<includeonly>|</includeonly>}}}\n"
			+ "|outertitleclass = {{{outertitleclass|}}}\n"
			+ "|outertitlestyle = {{{outerttitlestyle|}}}\n"
			+ "\n"
			+ "|topimage        = {{{topimage<includeonly>|</includeonly>}}}\n"
			+ "|topimageclass   = {{{topimageclass|}}}\n"
			+ "|topimagestyle   = {{{topimagestyle|}}}\n"
			+ "|topcaption      = {{{topcaption<includeonly>|</includeonly>}}}\n"
			+ "|topcaptionstyle = {{{topcaptionstyle|}}}\n"
			+ "\n"
			+ "|pretitle        = {{{pretitle<includeonly>|</includeonly>}}}\n"
			+ "|pretitlestyle   = {{{pretitlestyle|}}}\n"
			+ "|title           = {{{title<includeonly>|</includeonly>}}}\n"
			+ "|titleclass      = {{{titleclass|}}}\n"
			+ "|titlestyle      = {{{titlestyle|}}}\n"
			+ "\n"
			+ "|image           = {{{image<includeonly>|</includeonly>}}}\n"
			+ "|imageclass      = {{{imageclass|}}}\n"
			+ "|imagestyle      = {{{imagestyle|}}}\n"
			+ "|caption         = {{{caption<includeonly>|</includeonly>}}}\n"
			+ "|captionstyle    = {{{captionstyle|}}}\n"
			+ "\n"
			+ "|abovestyle = border-top:1px solid #aaa; border-bottom:1px solid #aaa; {{{abovestyle|}}}\n"
			+ "|above = {{{above<includeonly>|</includeonly>}}}\n"
			+ "\n"
			+ "|headingstyle    = {{{headingstyle|}}}\n"
			+ "|contentstyle    = {{{contentstyle|}}}\n"
			+ "\n"
			+ "|heading1style = {{{heading1style|}}}\n"
			+ "|heading1 = {{{heading1|}}}\n"
			+ "|content1style = {{{content1style|}}}\n"
			+ "|content1 = {{#if:{{{list1<includeonly>|</includeonly>}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list1framestyle|}}}\n"
			+ "                |title      = {{{list1title<includeonly>|</includeonly>}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list1titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list1name}}} |display:inline;}} {{{liststyle|}}}{{{list1style|}}}\n"
			+ "                | {{{list1}}}\n"
			+ "               }}\n"
			+ "             | {{{content1|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading2style = {{{heading2style|}}}\n"
			+ "|heading2 = {{{heading2|}}}\n"
			+ "|content2style = {{{content2style|}}}\n"
			+ "|content2 = {{#if:{{{list2<includeonly>|</includeonly>}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list2framestyle|}}}\n"
			+ "                |title      = {{{list2title<includeonly>|</includeonly>}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list2titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list2name}}} |display:inline;}} {{{liststyle|}}}{{{list2style|}}}\n"
			+ "                | {{{list2}}}\n"
			+ "               }}\n"
			+ "             | {{{content2|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading3style = {{{heading3style|}}}\n"
			+ "|heading3 = {{{heading3|}}}\n"
			+ "|content3style = {{{content3style|}}}\n"
			+ "|content3 = {{#if:{{{list3<includeonly>|</includeonly>}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list3framestyle|}}}\n"
			+ "                |title      = {{{list3title<includeonly>|</includeonly>}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list3titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list3name}}} |display:inline;}} {{{liststyle|}}}{{{list3style|}}}\n"
			+ "                | {{{list3}}}\n"
			+ "               }}\n"
			+ "             | {{{content3|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading4style = {{{heading4style|}}}\n"
			+ "|heading4 = {{{heading4|}}}\n"
			+ "|content4style = {{{content4style|}}}\n"
			+ "|content4 = <noinclude>''(......etc......)''</noinclude><!--\n"
			+ "         --><includeonly><!--\n"
			+ "          -->{{#if:{{{list4|}}}\n"
			+ "              | {{Collapsible list\n"
			+ "                 |framestyle = {{{listframestyle|}}}{{{list4framestyle|}}}\n"
			+ "                 |title      = {{{list4title|}}}\n"
			+ "                 |titlestyle = {{{listtitlestyle|}}}{{{list4titlestyle|}}}\n"
			+ "                 |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list4name}}} |display:inline;}} {{{liststyle|}}}{{{list4style|}}}\n"
			+ "                 | {{{list4}}}\n"
			+ "                }}\n"
			+ "              | {{{content4|}}}\n"
			+ "             }}<!--\n"
			+ "         --></includeonly>\n"
			+ "\n"
			+ "|heading5style = {{{heading5style|}}}\n"
			+ "|heading5 = {{{heading5|}}}\n"
			+ "|content5style = {{{content5style|}}}\n"
			+ "|content5 = {{#if:{{{list5|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list5framestyle|}}}\n"
			+ "                |title      = {{{list5title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list5titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list5name}}} |display:inline;}} {{{liststyle|}}}{{{list5style|}}}\n"
			+ "                | {{{list5}}}\n"
			+ "               }}\n"
			+ "             | {{{content5|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading6style = {{{heading6style|}}}\n"
			+ "|heading6 = {{{heading6|}}}\n"
			+ "|content6style = {{{content6style|}}}\n"
			+ "|content6 = {{#if:{{{list6|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list6framestyle|}}}\n"
			+ "                |title      = {{{list6title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list6titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list6name}}} |display:inline;}} {{{liststyle|}}}{{{list6style|}}}\n"
			+ "                | {{{list6}}}\n"
			+ "               }}\n"
			+ "             | {{{content6|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading7style = {{{heading7style|}}}\n"
			+ "|heading7 = {{{heading7|}}}\n"
			+ "|content7style = {{{content7style|}}}\n"
			+ "|content7 = {{#if:{{{list7|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list7framestyle|}}}\n"
			+ "                |title      = {{{list7title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list7titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list7name}}} |display:inline;}} {{{liststyle|}}}{{{list7style|}}}\n"
			+ "                | {{{list7}}}\n"
			+ "               }}\n"
			+ "             | {{{content7|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading8style = {{{heading8style|}}}\n"
			+ "|heading8 = {{{heading8|}}}\n"
			+ "|content8style = {{{content8style|}}}\n"
			+ "|content8 = {{#if:{{{list8|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list8framestyle|}}}\n"
			+ "                |title      = {{{list8title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list8titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list8name}}} |display:inline;}} {{{liststyle|}}}{{{list8style|}}}\n"
			+ "                | {{{list8}}}\n"
			+ "               }}\n"
			+ "             | {{{content8|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading9style = {{{heading9style|}}}\n"
			+ "|heading9 = {{{heading9|}}}\n"
			+ "|content9style = {{{content9style|}}}\n"
			+ "|content9 = {{#if:{{{list9|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list9framestyle|}}}\n"
			+ "                |title      = {{{list9title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list9titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list9name}}} |display:inline;}} {{{liststyle|}}}{{{list9style|}}}\n"
			+ "                | {{{list9}}}\n"
			+ "               }}\n"
			+ "             | {{{content9|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading10style = {{{heading10style|}}}\n"
			+ "|heading10 = {{{heading10|}}}\n"
			+ "|content10style = {{{content10style|}}}\n"
			+ "|content10 = {{#if:{{{list10|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list10framestyle|}}}\n"
			+ "                |title      = {{{list10title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list10titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list10name}}} |display:inline;}} {{{liststyle|}}}{{{list10style|}}}\n"
			+ "                | {{{list10}}}\n"
			+ "               }}\n"
			+ "             | {{{content10|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading11style = {{{heading11style|}}}\n"
			+ "|heading11 = {{{heading11|}}}\n"
			+ "|content11style = {{{content11style|}}}\n"
			+ "|content11 = {{#if:{{{list11|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list11framestyle|}}}\n"
			+ "                |title      = {{{list11title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list11titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list11name}}} |display:inline;}} {{{liststyle|}}}{{{list11style|}}}\n"
			+ "                | {{{list11}}}\n"
			+ "               }}\n"
			+ "             | {{{content11|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading12style = {{{heading12style|}}}\n"
			+ "|heading12 = {{{heading12|}}}\n"
			+ "|content12style = {{{content12style|}}}\n"
			+ "|content12 = {{#if:{{{list12|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list12framestyle|}}}\n"
			+ "                |title      = {{{list12title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list12titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list12name}}} |display:inline;}} {{{liststyle|}}}{{{list12style|}}}\n"
			+ "                | {{{list12}}}\n"
			+ "               }}\n"
			+ "             | {{{content12|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading13style = {{{heading13style|}}}\n"
			+ "|heading13 = {{{heading13|}}}\n"
			+ "|content13style = {{{content13style|}}}\n"
			+ "|content13 = {{#if:{{{list13|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list13framestyle|}}}\n"
			+ "                |title      = {{{list13title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list13titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list13name}}} |display:inline;}} {{{liststyle|}}}{{{list13style|}}}\n"
			+ "                | {{{list13}}}\n"
			+ "               }}\n"
			+ "             | {{{content13|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "|heading14style = {{{heading14style|}}}\n"
			+ "|heading14 = {{{heading14|}}}\n"
			+ "|content14style = {{{content14style|}}}\n"
			+ "|content14 = {{#if:{{{list14|}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list14framestyle|}}}\n"
			+ "                |title      = {{{list14title|}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list14titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list14name}}} |display:inline;}} {{{liststyle|}}}{{{list14style|}}}\n"
			+ "                | {{{list14}}}\n"
			+ "               }}\n"
			+ "             | {{{content14|}}}\n"
			+ "            }}\n"
			+ "\n"
			+ "\n"
			+ "|heading15style = {{{heading15style|}}}\n"
			+ "|heading15 = {{{heading15|}}}\n"
			+ "|content15style = {{{content15style|}}}\n"
			+ "|content15 = {{#if:{{{list15<includeonly>|</includeonly>}}}\n"
			+ "             | {{Collapsible list\n"
			+ "                |framestyle = {{{listframestyle|}}}{{{list15framestyle|}}}\n"
			+ "                |title      = {{{list15title<includeonly>|</includeonly>}}}\n"
			+ "                |titlestyle = {{{listtitlestyle|}}}{{{list15titlestyle|}}}\n"
			+ "                |liststyle  = padding:0.2em 0 0.4em; text-align:center; {{#ifeq:{{{expanded|}}}|all |display:inline;}} {{#ifeq:{{{expanded|}}}|{{{list15name}}} |display:inline;}} {{{liststyle|}}}{{{list15style|}}}\n"
			+ "                | {{{list15}}}\n" + "               }}\n" + "             | {{{content15|}}}\n" + "            }}\n"
			+ "\n" + "<!--Gap between sections above and any subsequent 'below' section:-->\n"
			+ "|content16style = padding:0; line-height:0.4em;\n"
			+ "|content16 = {{#if:{{{below<includeonly>|</includeonly>}}} |&nbsp;}}\n" + "\n"
			+ "|belowstyle = border-top:1px solid #aaa; border-bottom:1px solid #aaa; {{{belowstyle|}}}\n"
			+ "|below = {{{below<includeonly>|</includeonly>}}}\n" + "\n" + "|tnavbar        = {{{tnavbar|}}}\n"
			+ "|tnavbarstyle   = padding-top:0.6em; {{{tnavbarstyle|}}}\n" + "|tnavbaroptions = {{{tnavbaroptions|}}}\n" + "\n"
			+ "}}<noinclude>\n" + "{{end sidebar page}}\n" + "\n"
			+ "<!---Please add metadata (categories, interwikis) to the <includeonly> section at the\n"
			+ "     bottom of [[Template:Sidebar with collapsible lists/doc]] page, not here - thanks!--->\n" + "";

	public final static String IF_IMAGE_TEST = "{{#if:{{{image|}}}|[[File:{{{image|}}}|{{#if:{{{image_size|{{{imagesize|}}}}}}|{{{image_size|{{{imagesize|}}}}}}|220px}}|alt={{{alt|}}}]]}}";

	public final static String IPA_FR = "<onlyinclude><small>{{#if: {{{2|}}}|{{#switch: {{{2}}}|lang=French:&nbsp;|pron=pronounced:&nbsp;|IPA=IPA:&nbsp;|=|French pronunciation:&nbsp;}}|{{#if: {{{2}}}|French pronunciation:&nbsp;}}}}</small><span title=\"Pronunciation in IPA\" class=\"IPA\">[[WP:IPA for French|[{{{1}}}]]]</span><small>{{#if:{{{3|}}}|&nbsp; {{nowrap|([[File:Speaker Icon.svg|13px|link=|alt=]] [[:Media:{{{3|}}}|listen]])}}}}</small></onlyinclude>\n"
			+ "{{pp-template|small=yes}}\n"
			+ "\n"
			+ "==Usage==\n"
			+ "This template formats IPA transcriptions and links them to [[WP:IPA for French]]. The transcription should match the conventions of that key; for narrower dialect transcriptions, use {{tl|IPA-all}}, which links to a more complete IPA key. \n"
			+ "\n"
			+ "The first cell in the template is for the transcription, and a second optional cell is a switch that controls the lede. With no second value, the lede ''French pronunciation:'' appears: \n"
			+ "*<code><nowiki>{{IPA-fr|o}}</nowiki></code> → {{IPA-fr|o}}\n"
			+ "*<code><nowiki>{{IPA-fr|o|pron}}</nowiki></code> → {{IPA-fr|o|pron}}\n"
			+ "*<code><nowiki>{{IPA-fr|o|lang}}</nowiki></code> → {{IPA-fr|o|lang}}\n"
			+ "*<code><nowiki>{{IPA-fr|o|IPA}}</nowiki></code> → {{IPA-fr|o|IPA}}\n"
			+ "*<code><nowiki>{{IPA-fr|o|}}</nowiki></code> → {{IPA-fr|o|}}\n"
			+ "\n"
			+ "Any of these may be combined with a sound file in an optional third cell:\n"
			+ "*<code><nowiki>{{IPA-fr|o|IPA|Fr-eau.ogg}}</nowiki></code> → {{IPA-fr|o|IPA|Fr-eau.ogg}}\n"
			+ "\n"
			+ "For the default lede, however, the placeholder ‹-› is required in the second cell: \n"
			+ "*<code><nowiki>{{IPA-fr|o|-|Fr-eau.ogg}}</nowiki></code> → {{IPA-fr|o|-|Fr-eau.ogg}}\n"
			+ "\n"
			+ "{{usage of IPA templates}}\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "<!-- PLEASE ADD CATEGORIES BELOW THIS LINE, THANKS. -->\n"
			+ "\n"
			+ "[[Category:IPA templates|{{PAGENAME}}]]\n"
			+ "\n"
			+ "<!-- PLEASE ADD INTERWIKIS BELOW THIS LINE, THANKS. -->\n"
			+ "[[id:Templat:IPA-fr]]";

	private static String COMMONS = "{{sister\n"
			+ "| position = {{{position|}}}\n"
			+ "| project  = commons\n"
			+ "| text     = \n"
			+ "Wikimedia Commons has media related to: '''''{{sec link auto| commons | {{{1| Special:Search/{{PAGENAME}} }}} | {{{2| {{{1| {{PAGENAME}} }}} }}} }}''''' \n"
			+ "}}<noinclude>\n" + "\n" + "{{documentation}}\n"
			+ "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	private static String COMMONS_CATEGORY = "{{commons\n" + "| position = {{{position|}}}\n"
			+ "  <!-- {{#if:x|}} strips whitespace from parameter 1, \n"
			+ "       in case it is fed like this: {{Commons category| Some cat }}. -->\n"
			+ "| Category:{{#if:x| {{{1| {{PAGENAME}} }}} }}\n" + "| {{{2| {{{1| {{PAGENAME}} }}} }}}\n" + "}}<noinclude>\n" + "\n"
			+ "{{documentation}}\n" + "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	private static String SISTER = "{{Side box\n" + "| position = {{{position|}}}\n" + "| image    = \n"
			+ "  {{#switch: {{{image|}}}\n" + "  | none =    <!-- \"image=none\", do nothing -->\n"
			+ "  |      =    <!-- No image fed, select an image -->\n" + "    [[Image:{{#switch: {{lc: {{{project|}}} }}\n"
			+ "    | commons                = Commons-logo.svg\n" + "    | meta|metawiki|m        = Wikimedia Community Logo.svg\n"
			+ "    | wikibooks|wbk|wb|b     = Wikibooks-logo-en-noslogan.svg\n"
			+ "    | wikiquote|quote|wqt|q  = Wikiquote-logo-en.svg\n" + "    | wikipedia|wp|w         = Wikipedia-logo.png\n"
			+ "    | wikisource|source|ws|s = Wikisource-logo.svg\n" + "    | wiktionary|wkt|wdy|d   = Wiktionary-logo-en.svg\n"
			+ "    | wikinews|news|wnw|n    = Wikinews-logo.svg\n" + "    | wikispecies|species    = Wikispecies-logo.svg\n"
			+ "    | wikiversity|wvy|v      = Wikiversity-logo.svg\n" + "    | mediawiki|mw           = Mediawiki.png\n"
			+ "    | #default               = Wikimedia-logo.svg\n" + "    }}|40x40px|link=|alt=\n" + "    ]]\n"
			+ "  | #default = {{{image|}}}\n" + "  }}\n" + "| text       = {{{text}}}\n" + "| below      = {{{below|}}}\n"
			+ "| imageright = {{{imageright|}}}\n" + "| class      = plainlinks\n" + "}}<noinclude>\n" + "\n" + "{{Documentation}}\n"
			+ "\n" + "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	private static String SIDE_BOX = "<table class=\"metadata mbox-small{{#ifeq:{{lc:{{{position|}}}}}|left|-left}} {{{class|}}}\" style=\"border:1px solid #aaa; background-color:#f9f9f9; {{{style|}}}\">\n"
			+ "<tr>\n"
			+ "{{#switch:{{{image|}}}\n"
			+ "|<!--BLANK-->|none={{td}}\n"
			+ "|#default=<td class=\"mbox-image\">{{{image}}}</td>\n"
			+ "}}\n"
			+ "<td class=\"mbox-text\" style=\"{{{textstyle|}}}\"> {{{text}}} </td>\n"
			+ "{{#if:{{{imageright|}}}\n"
			+ "| <td class=\"mbox-imageright\">{{{imageright}}}</td>\n"
			+ "}}\n"
			+ "</tr>\n"
			+ "{{#if:{{{below|}}}\n"
			+ "| <tr><td colspan={{#if:{{{imageright|}}}|3|2}} class=\"mbox-text\" style=\"{{{textstyle|}}}\"> {{{below}}} </td></tr>\n"
			+ "}}\n"
			+ "</table><noinclude>\n"
			+ "\n"
			+ "{{Documentation}}\n"
			+ "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	private static String SEC_LINK_AUTO = "{{#ifeq: {{SERVERNAME}} | secure.wikimedia.org\n"
			+ "| {{#switch: {{lc: {{{padlock|}}} }}\n" + "  | yes    <!--Supress CSS icon, to not get double icon-->\n"
			+ "  | no = <span class=\"plainlinks\">\n" + "  }}[{{sec link/secure url\n" + "  | project = {{{1|}}}\n"
			+ "  | pagename = {{{2|}}}\n" + "  | lang = {{{lang|}}}\n" + "  | query = {{{query|}}}\n" + "  | anchor = {{{anchor|}}}\n"
			+ "  }} {{sec link/text\n" + "  | project = {{{1|}}}\n" + "  | pagename = {{{2|}}}\n" + "  | text = {{{3|}}}\n"
			+ "  | lang = {{{lang|}}}\n" + "  | query = {{{query|}}}\n" + "  | anchor = {{{anchor|}}}\n"
			+ "  }}]{{#switch: {{lc: {{{padlock|}}} }}\n" + "  | no = </span>\n"
			+ "  | yes = </span>[[File:Lock icon blue.gif|16x13px|link=]]\n" + "  }}\n" + "\n"
			+ "| <!--Not on the secure server, make a normal link-->\n" + "  {{#if: {{{query|}}} {{{lang|}}}\n"
			+ "  | <!--Make a full url link, needed when query or lang-->\n" + "    <span class=\"plainlinks\">[{{sec link/normal url\n"
			+ "    | project = {{{1|}}}\n" + "    | pagename = {{{2|}}}\n" + "    | lang = {{{lang|}}}\n"
			+ "    | query = {{{query|}}}\n" + "    | anchor = {{{anchor|}}}\n" + "    }} {{sec link/text\n"
			+ "    | project = {{{1|}}}\n" + "    | pagename = {{{2|}}}\n" + "    | text = {{{3|}}}\n" + "    | lang = {{{lang|}}}\n"
			+ "    | query = {{{query|}}}\n" + "    | anchor = {{{anchor|}}}\n" + "    }}]</span>\n" + "  | <!--Make a normal link-->\n"
			+ "    [[:{{sec link/normal link\n" + "    | project = {{{1|}}}\n" + "    | pagename = {{{2|}}}\n"
			+ "    | anchor = {{{anchor|}}}\n" + "    }}|{{sec link/text\n" + "    | project = {{{1|}}}\n"
			+ "    | pagename = {{{2|}}}\n" + "    | text = {{{3|}}}\n" + "    | anchor = {{{anchor|}}}\n" + "    }}]]\n" + "  }}\n"
			+ "}}<noinclude>\n" + "\n" + "{{documentation}}\n"
			+ "<!-- Add categories and interwikis to the /doc subpage, not here! -->\n" + "</noinclude>";

	public final static String FORMAT_DATE = "#REDIRECT [[Template:Date]]";

	public final static String DATE = "{{{{{|safesubst:}}}#switch:none\n"
			+ " |{{{{{|safesubst:}}}#iferror: {{{{{|safesubst:}}}#time:Y_M_d|{{{1|}}} }} | none }} <noinclude><!-- #time: can't handle --></noinclude>\n"
			+ " |{{{{{|safesubst:}}}#iferror: {{{{{|safesubst:}}}#expr: {{{1|}}}+0 }}\n"
			+ "    |<noinclude><!--not a pure number--></noinclude>\n"
			+ "    |{{{{{|safesubst:}}}#ifexpr: {{{1|}}}+0 > 10000000000000\n"
			+ "       |<noinclude><!-- a yyyymmddhhmmss timestamp --></noinclude>\n"
			+ "       |{{{{{|safesubst:}}}#ifeq: {{{{{|safesubst:}}}#expr:{{{1|}}}+0}} | {{{1|}}}\n"
			+ "          | none <noinclude><!-- pure number eg 123.456 --></noinclude>\n"
			+ "          | <noinclude><!-- assume yy-mm-dd --></noinclude>\n"
			+ "        }}\n"
			+ "     }}\n"
			+ "  }}\n"
			+ " |{{{{{|safesubst:}}}#switch:  {{lc:{{{2|}}}}} | none | asis=none }}\n"
			+ " |{{{{{|safesubst:}}}#ifexpr:  {{{{{|safesubst:}}}#time:Y|{{{1|}}} }} < 1000 | none }}\n"
			+ " |{{{{{|safesubst:}}}#switch:  {{{{{|safesubst:}}}#time:Ynj|{{{1|}}} }}|100031|110031|130031|140031|150031=none}}\n"
			+ " |= {{{1|}}}<noinclude><!-- error or \"none\", so no formatting --></noinclude>\n"
			+ " |<noinclude><!-- continue with formatting --></noinclude>\n"
			+ "  {{{{{|safesubst:}}}#ifeq:<noinclude><!--\n"
			+ "    --></noinclude>{{{{{|safesubst:}}}#time:Y|{{{1}}} 2008}}<noinclude><!--\n"
			+ "    --></noinclude>{{{{{|safesubst:}}}#iferror: {{{{{|safesubst:}}}#ifexpr: {{{1}}}>10000000000000 | no }} | }}<noinclude><!--\n"
			+ "    --></noinclude>{{{{{|safesubst:}}}#time:Y|{{{1}}} 2004}}\n"
			+ "  |20082004\n"
			+ "  |<noinclude><!-- no year --></noinclude>\n"
			+ "    {{{{{|safesubst:}}}#ifeq:{{{{{|safesubst:}}}#time:d|{{{1}}} 2036}}|{{{{{|safesubst:}}}#time:d|{{{1}}} }}\n"
			+ "    |<noinclude><!-- month+day --></noinclude>{{{{{|safesubst:}}}#time:\n"
			+ "      {{{{{|safesubst:}}}#switch: {{lc: {{{{{|safesubst:}}}#ifeq:{{{3|}}}|y|L}}{{{2|}}} }}\n"
			+ "      | lmdy | liso | lymd      = [[:F j]]\n"
			+ "      | mdy  | iso  | ymd       = F j\n"
			+ "      | ldmy | l                = [[:j F]]\n"
			+ "      | #default                = j F\n"
			+ "      }}|{{{1}}} 2000 }}<noinclude><!-- default='dmy' or null or \"\" or unsupported option --></noinclude>\n"
			+ "    |<noinclude><!-- month only --></noinclude>{{{{{|safesubst:}}}#time:\n"
			+ "      {{{{{|safesubst:}}}#switch: {{lc: {{{{{|safesubst:}}}#ifeq:{{{3|}}}|y|L}}{{{2|}}} }}\n"
			+ "      | lmdy | liso | lymd \n"
			+ "      | ldmy | l                = [[F]]\n"
			+ "      | #default                = F\n"
			+ "      }}|{{{1}}} 2000 }}<noinclude><!-- default='dmy'/'mdy'/'ymd'/'iso'/null/\"\"/unsupported opt --></noinclude>\n"
			+ "    }}\n"
			+ "  |<noinclude><!-- with year--></noinclude>\n"
			+ "    {{{{{|safesubst:}}}#if: {{{{{|safesubst:}}}#iferror:{{{{{|safesubst:}}}#time:j|2 {{{1|}}}}}|*D*|{{{{{|safesubst:}}}#iferror:{{{{{|safesubst:}}}#time:j|2000 {{{1|}}}}}|*D*| }}}}\n"
			+ "    |<noinclude><!-- day+month+year --></noinclude>{{{{{|safesubst:}}}#time:\n"
			+ "      {{{{{|safesubst:}}}#switch: {{lc: {{{{{|safesubst:}}}#ifeq:{{{3|}}}|y|L}}{{{2|}}} }}\n"
			+ "      | lmdy                    = [[:F j]], [[Y]]\n" + "      | mdy                     = F j, Y\n"
			+ "      | liso                    = [[Y|Y-]][[F j|m-d]]<noinclude><!-- i.e. [[Y-m-d]] --></noinclude>\n"
			+ "      | iso                     = Y-m-d\n" + "      | lymd                    = [[Y]] [[:F j]]\n"
			+ "      | ymd                     = Y F j\n" + "      | ldmy | l                = [[:j F]] [[Y]]\n"
			+ "      | #default                = j F Y\n"
			+ "      }}|{{{1|}}} }}<noinclude><!-- #default='dmy' or null or \"\" or unsupported option --></noinclude>\n"
			+ "    |<noinclude><!-- month+year --></noinclude>{{{{{|safesubst:}}}#time:\n"
			+ "      {{{{{|safesubst:}}}#switch: {{lc: {{{{{|safesubst:}}}#ifeq:{{{3|}}}|y|L}}{{{2|}}} }}\n"
			+ "      | lmdy | liso | lymd | ldmy | l  = [[:F Y]]\n" + "      | #default                = F Y\n"
			+ "      }}|{{{1|}}} }}<noinclude><!-- default='dmy'/'iso'/'mdy'/null/\"\"/unsupported option --></noinclude>\n" + "    }}\n"
			+ "  }}    \n" + "}}<noinclude>\n" + "{{documentation}}\n" + "</noinclude>";

	boolean fSemanticWebActive;

	static {
		TagNode.addAllowedAttribute("style");
		Configuration.DEFAULT_CONFIGURATION.addUriScheme("tel");
		Configuration.DEFAULT_CONFIGURATION.addInterwikiLink("intra", "/${title}");
		Configuration.DEFAULT_CONFIGURATION.addTokenTag("chart", new ChartTag());
		Configuration.DEFAULT_CONFIGURATION.addTokenTag("inputbox", new IgnoreTag("inputbox"));
		Configuration.DEFAULT_CONFIGURATION.addTokenTag("imagemap", new IgnoreTag("imagemap"));
	}

	public WikiTestModel(String imageBaseURL, String linkBaseURL) {
		this(Locale.ENGLISH, imageBaseURL, linkBaseURL);
	}

	/**
	 * Add German namespaces to the wiki model
	 * 
	 * @param imageBaseURL
	 * @param linkBaseURL
	 */
	public WikiTestModel(Locale locale, String imageBaseURL, String linkBaseURL) {
		super(Configuration.DEFAULT_CONFIGURATION, locale, imageBaseURL, linkBaseURL);
		// set up a simple cache mock-up for JUnit tests. HashMap is not usable for
		// production!
		Configuration.DEFAULT_CONFIGURATION.setTemplateCallsCache(new HashMap());

		fSemanticWebActive = false;
	}

	/**
	 * Add templates: &quot;Test&quot;, &quot;Templ1&quot;, &quot;Templ2&quot;,
	 * &quot;Include Page&quot;
	 * 
	 */
	@Override
	public String getRawWikiContent(String namespace, String articleName, Map<String, String> map) {
		String result = super.getRawWikiContent(namespace, articleName, map);
		if (result != null) {
			// found magic word template
			return result;
		}
		String name = encodeTitleToUrl(articleName, true);
		if (isTemplateNamespace(namespace)) {
			// if (MagicWord.isMagicWord(articleName)) {
			// return MagicWord.processMagicWord(articleName, this);
			// }
			if (name.equals("Reflist")) {
				return REFLIST_TEXT;
			} else if (name.equals("Citation")) {
				return CITATION;
			} else if (name.equals("Cite_book")) {
				return CITE_BOOK;
			} else if (name.equals("Cite_journal")) {
				return CITE_JOURNAL;
			} else if (name.equals("Citation/core")) {
				return CITATION_CORE;
			} else if (name.equals("Commons")) {
				return COMMONS;
			} else if (name.equals("Commons_category")) {
				return COMMONS_CATEGORY;
			} else if (name.equals("Date")) {
				return DATE;
			} else if (name.equals("FormatDate")) {
				return FORMAT_DATE;
			} else if (name.equals("Sister")) {
				return SISTER;
			} else if (name.equals("Sec_link_auto")) {
				return SEC_LINK_AUTO;
			} else if (name.equals("Side_box")) {
				return SIDE_BOX;
			} else if (name.equals("!")) {
				return PIPE_SYMBOL;
			} else if (name.equals("2x")) {
				return DOUBLE_PARAMETER;
			} else if (name.equals("Cat")) {
				return CAT;
			} else if (name.equals("!")) {
				return "|<noinclude>{{template doc}}</noinclude>";
			} else if (name.equals("Infobox_Software")) {
				return INFOBOX_SOFTWARE_TEXT;
			} else if (name.equals("Cite_web")) {
				return CITE_WEB_TEXT;
			} else if (name.equals("Infobox_Programmiersprache")) {
				return INFOBOX_PROGRAMMIERSPRACHE;
			} else if (name.equals("Navbox")) {
				return NAVBOX_TEXT;
			} else if (name.equals("Navbar")) {
				return NAVBAR_TEXT;
			} else if (name.equals("Transclude")) {
				return TRANSCLUDE;
			} else if (name.equals("Tnavbar")) {
				return TNAVBAR_TEXT;
			} else if (name.equals("Nested_tempplate_test")) {
				return NESTED_TEMPLATE_TEST;
			} else if (name.equals("Nested")) {
				return NESTED;
			} else if (name.equals("Nowrap")) {
				return NOWRAP;
				// } else if (name.equals("·")) {
			} else if (name.equals("%C2%B7")) {
				return DOT_TEXT;
			} else if (name.equals("Recursion")) {
				return ENDLESS_RECURSION_TEST;
			} else if (name.equals("Test")) {
				return "a) First: {{{1}}} Second: {{{2}}}";
			} else if (name.equals("Templ1")) {
				return "b) First: {{{a}}} Second: {{{1}}}";
			} else if (name.equals("Templ2")) {
				return "c) First: {{{1}}} Second: {{{2}}}";
			} else if (name.equals("Ifeq")) {
				return IFEQ_TEST;
			} else if (name.equals("Further")) {
				return FURTHER;
			} else if (name.equals("Tc")) {
				return TC;
			} else if (name.equals("Tl")) {
				return TL;
			} else if (name.equals("PronEng")) {
				return PRON_ENG;
			} else if (name.equals("Pron-en")) {
				return PRON_EN;
			} else if (name.equals("Anarchism_sidebar")) {
				return ANARCHISM_SIDEBAR;
			} else if (name.equals("Ideology")) {
				return IDEOLOGY;
			} else if (name.equals("Sidebar_with_collapsible_lists")) {
				return SIDEBAR_WITH_COLLAPSIBLE_LISTS;
			} else if (name.equals("If_image_test")) {
				return IF_IMAGE_TEST;
			} else if (name.equals("Birth_date_and_age")) {
				return BIRTH_DATE_AND_AGE;
			} else if (name.equals("MONTHNAME")) {
				return MONTHNAME;
			} else if (name.equals("MONTHNUMBER")) {
				return MONTHNUMBER;
			} else if (name.equals("Age")) {
				return AGE;
			} else if (name.equals("Born_data")) {
				return BORN_DATA;
			} else if (name.equals("IPA-fr")) {
				return IPA_FR;
			} else if (name.equals("TestRedirect1")) {
				return " \n #REDIRECT [[Template:TestRedirect2]] \n";
			} else if (name.equals("TestRedirect2")) {
				return "Hello World!";
			} else if (name.equals("Image")) {
				return IMAGE;
			} else if (name.equals("Yesno")) {
				return YESNO;
			} else if (name.equals("In_the_news/image")) {
				return IN_THE_NEWS_IMAGE;
			} else if (name.equals("Main_Page_panel")) {
				return MAIN_PAGE_PANEL;
			} else if (name.equals("Main_Page_subpanel")) {
				return MAIN_PAGE_SUBPANEL;
			} else if (name.equals("Ordinal")) {
				return ORDINAL;
			} else if (name.equals("Lageplan")) {
				return LAGEPLAN;
			} else if (name.equals("Infobox_Ort_in_Deutschland")) {
				return INFOBOX_ORT_IN_DEUTSCHLAND;
			} else if (name.equals("TestInclude")) {
				return TEST_INCLUDE;
			} else if (name.equals("TestInclude2")) {
				return TEST_INCLUDE2;
			} else if (name.equals("TestInclude3")) {
				return TEST_INCLUDE3;
			} else if (name.equals("TestInclude4")) {
				return TEST_INCLUDE4;
			} else if (name.equals("OnlyicludeDemo")) {
				return ONLYINCLUDE_DEMO;
			} else if (name.equals("Sortname")) {
				return SORTNAME;
			} else if (name.equals("Rndfrac")) {
				return RNDFRAC;
			} else if (name.equals("Rndfrac/out")) {
				return RNDFRAC_OUT;
			} else if (name.equals("T1")) {
				return T1;
			} else if (name.equals("Rellink")) {
				return RELLINK;
			} else if (name.equals("Main")) {
				return MAIN;
			} else if (name.equals("See_also")) {
				return SEE_ALSO;
			}
		} else {
			if (name.equals("Include_Page")) {
				return "an include page";
			}
		}
		return null;
	}

	/**
	 * Set the German image namespace
	 */
	@Override
	public String getImageNamespace() {
		return "Bild";
	}

	@Override
	public boolean isImageNamespace(String name) {
		return super.isImageNamespace(name) || name.equals(getImageNamespace());
	}

	@Override
	public boolean isSemanticWebActive() {
		return fSemanticWebActive;
	}

	@Override
	public void setSemanticWebActive(boolean semanticWeb) {
		this.fSemanticWebActive = semanticWeb;
	}

	public boolean showSyntax(String tagName) {
		return true;
	}

	@Override
	public void appendExternalLink(String uriSchemeName, String link, String linkName, boolean withoutSquareBrackets) {
		if (uriSchemeName.equalsIgnoreCase("tel")) {
			// example for a telephone link
			link = Utils.escapeXml(link, true, false, false);
			TagNode aTagNode = new TagNode("a");
			aTagNode.addAttribute("href", link, true);
			aTagNode.addAttribute("class", "telephonelink", true);
			aTagNode.addAttribute("title", link, true);
			if (withoutSquareBrackets) {
				append(aTagNode);
				aTagNode.addChild(new ContentToken(linkName));
			} else {
				String trimmedText = linkName.trim();
				if (trimmedText.length() > 0) {
					pushNode(aTagNode);
					WikipediaParser.parseRecursive(trimmedText, this, false, true);
					popNode();
				}
			}
			return;
		}
		super.appendExternalLink(uriSchemeName, link, linkName, withoutSquareBrackets);
	}

	/**
	 * Test for <a
	 * href="http://groups.google.de/group/bliki/t/a0540e27f27f02a5">Discussion:
	 * Hide Table of Contents (toc)?</a>
	 */
	// public ITableOfContent createTableOfContent(boolean isTOCIdentifier) {
	// if (fToCSet == null) {
	// fToCSet = new HashSet<String>();
	// fTableOfContent = new ArrayList<Object>();
	// }
	// fTableOfContentTag = new TableOfContentTag("div") {
	// public void setShowToC(boolean showToC) {
	// // do nothing
	// }
	// };
	// return fTableOfContentTag;
	// }
}
