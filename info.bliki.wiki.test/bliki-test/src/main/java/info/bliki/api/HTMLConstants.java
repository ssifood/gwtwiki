package info.bliki.api;
 
public class HTMLConstants {

	/**
		 * Test CSS manipulations for the PDF file
		 */
		public final static String CSS_SCREEN_STYLE = "<style type=\"text/css\">\n" + 
				"\n" + 
				"#interwiki-completelist {\n" + 
				"    font-weight: bold;\n" + 
				"}\n" + 
				"\n" + 
				"body.page-Main_Page #ca-delete {\n" + 
				"    display: none !important;\n" + 
				"}\n" + 
				"\n" + 
				"#toolbar {\n" + 
				"    margin-bottom: 6px;\n" + 
				"}\n" + 
				"\n" + 
				"ol.references {\n" + 
				"    font-size: 100%;\n" + 
				"}\n" + 
				"\n" + 
				".references-small { font-size: 90%;}\n" + 
				"\n" + 
				".references-2column {\n" + 
				"    font-size: 90%;\n" + 
				"    -moz-column-count: 2;\n" + 
				"    -webkit-column-count: 2;\n" + 
				"    column-count: 2;\n" + 
				"}\n" + 
				"\n" + 
				".same-bg { background: none; }\n" + 
				"\n" + 
				"ol.references > li:target {\n" + 
				"    background-color: #DEF;\n" + 
				"}\n" + 
				"\n" + 
				"sup.reference:target { \n" + 
				"    background-color: #DEF;\n" + 
				"}\n" + 
				"\n" + 
				"cite {\n" + 
				"    font-style: normal;\n" + 
				"    word-wrap: break-word;\n" + 
				"}\n" + 
				"\n" + 
				"cite:target { \n" + 
				"    background-color: #DEF;\n" + 
				"}\n" + 
				"\n" + 
				"@media print {\n" + 
				"    #content cite a.external.text:after {\n" + 
				"        display: none;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"@media screen, handheld, projection {\n" + 
				"    cite *.printonly {\n" + 
				"        display: none;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"table.wikitable,\n" + 
				"table.prettytable {\n" + 
				"    margin: 1em 1em 1em 0;\n" + 
				"    background: #f9f9f9;\n" + 
				"    border: 1px #aaa solid;\n" + 
				"    border-collapse: collapse;\n" + 
				"}\n" + 
				"\n" + 
				"table.wikitable th, table.wikitable td,\n" + 
				"table.prettytable th, table.prettytable td {\n" + 
				"    border: 1px #aaa solid;\n" + 
				"    padding: 0.2em;\n" + 
				"}\n" + 
				"\n" + 
				"table.wikitable th,\n" + 
				"table.prettytable th {\n" + 
				"    background: #f2f2f2;\n" + 
				"    text-align: center;\n" + 
				"}\n" + 
				"\n" + 
				"table.wikitable caption,\n" + 
				"table.prettytable caption {\n" + 
				"    margin-left: inherit;\n" + 
				"    margin-right: inherit;\n" + 
				"    font-weight: bold;\n" + 
				"}\n" + 
				"\n" + 
				"table.prettytable code,\n" + 
				"table.wikitable code {\n" + 
				"    background-color: transparent;\n" + 
				"}\n" + 
				"\n" + 
				"table.navbox {            \n" + 
				"  border:1px solid #aaa;\n" + 
				"  width:100%; \n" + 
				"  margin:auto;\n" + 
				"  clear:both;\n" + 
				"  font-size:88%;\n" + 
				"  text-align:center;\n" + 
				"  padding:1px;\n" + 
				"}\n" + 
				"table.navbox + table.navbox {\n" + 
				"  margin-top:-1px;    \n" + 
				"}\n" + 
				".navbox-title, .navbox-abovebelow, table.navbox th {\n" + 
				"  text-align:center;   \n" + 
				"  padding-left:1em;\n" + 
				"  padding-right:1em;\n" + 
				"}\n" + 
				".navbox-group {         \n" + 
				"  white-space:nowrap;\n" + 
				"  text-align:right;\n" + 
				"  font-weight:bold;\n" + 
				"  padding-left:1em;\n" + 
				"  padding-right:1em;\n" + 
				"}\n" + 
				".navbox, .navbox-subgroup {\n" + 
				"  background:#fdfdfd;    \n" + 
				"}\n" + 
				".navbox-list {\n" + 
				"  border-color:#fdfdfd; \n" + 
				"}\n" + 
				".navbox-title, table.navbox th {\n" + 
				"  background:#ccccff;   \n" + 
				"}\n" + 
				".navbox-abovebelow, .navbox-group, .navbox-subgroup .navbox-title {\n" + 
				"  background:#ddddff;    \n" + 
				"}\n" + 
				".navbox-subgroup .navbox-group, .navbox-subgroup .navbox-abovebelow {\n" + 
				"  background:#e6e6ff;  \n" + 
				"}\n" + 
				".navbox-even {\n" + 
				"  background:#f7f7f7;   \n" + 
				"}\n" + 
				".navbox-odd {\n" + 
				"  background:transparent; \n" + 
				"}\n" + 
				" \n" + 
				"@media print {\n" + 
				"    .navbox {\n" + 
				"        display: none;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				".infobox {\n" + 
				"    border: 1px solid #aaa;\n" + 
				"    background-color: #f9f9f9;\n" + 
				"    color: black;\n" + 
				"    margin: 0.5em 0 0.5em 1em;\n" + 
				"    padding: 0.2em;\n" + 
				"    float: right;\n" + 
				"    clear: right;\n" + 
				"}\n" + 
				".infobox td,\n" + 
				".infobox th {\n" + 
				"    vertical-align: top;\n" + 
				"}\n" + 
				".infobox caption {\n" + 
				"    font-size: larger;\n" + 
				"    margin-left: inherit;\n" + 
				"}\n" + 
				".infobox.bordered {\n" + 
				"    border-collapse: collapse;\n" + 
				"}\n" + 
				".infobox.bordered td,\n" + 
				".infobox.bordered th {\n" + 
				"    border: 1px solid #aaa;\n" + 
				"}\n" + 
				".infobox.bordered .borderless td,\n" + 
				".infobox.bordered .borderless th {\n" + 
				"    border: 0;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.sisterproject {\n" + 
				"    width: 20em;\n" + 
				"    font-size: 90%;\n" + 
				"}\n" + 
				"\n" + 
				"@media print {\n" + 
				"    .infobox.sisterproject {\n" + 
				"        display: none;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				".infobox.standard-talk {\n" + 
				"    border: 1px solid #c0c090;\n" + 
				"    background-color: #f8eaba;\n" + 
				"}\n" + 
				".infobox.standard-talk.bordered td,\n" + 
				".infobox.standard-talk.bordered th {\n" + 
				"    border: 1px solid #c0c090;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.bordered .mergedtoprow td,\n" + 
				".infobox.bordered .mergedtoprow th {\n" + 
				"    border: 0;\n" + 
				"    border-top: 1px solid #aaa;\n" + 
				"    border-right: 1px solid #aaa;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.bordered .mergedrow td,\n" + 
				".infobox.bordered .mergedrow th {\n" + 
				"    border: 0;\n" + 
				"    border-right: 1px solid #aaa;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.geography {\n" + 
				"    text-align: left;\n" + 
				"    border-collapse: collapse;\n" + 
				"    line-height: 1.2em; \n" + 
				"    font-size: 90%;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.geography  td,\n" + 
				".infobox.geography  th {\n" + 
				"    border-top: solid 1px #aaa;\n" + 
				"    padding: 0.4em 0.6em 0.4em 0.6em;\n" + 
				"}\n" + 
				".infobox.geography .mergedtoprow td,\n" + 
				".infobox.geography .mergedtoprow th {\n" + 
				"    border-top: solid 1px #aaa;\n" + 
				"    padding: 0.4em 0.6em 0.2em 0.6em;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.geography .mergedrow td,\n" + 
				".infobox.geography .mergedrow th {\n" + 
				"    border: 0;\n" + 
				"    padding: 0 0.6em 0.2em 0.6em;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.geography .mergedbottomrow td,\n" + 
				".infobox.geography .mergedbottomrow th {\n" + 
				"    border-top: 0;\n" + 
				"    border-bottom: solid 1px #aaa;\n" + 
				"    padding: 0 0.6em 0.4em 0.6em;\n" + 
				"}\n" + 
				"\n" + 
				".infobox.geography .maptable td,\n" + 
				".infobox.geography .maptable th {\n" + 
				"    border: 0;\n" + 
				"    padding: 0;\n" + 
				"}\n" + 
				"\n" + 
				".notice {\n" + 
				"    margin: 1em;\n" + 
				"    padding: 0.2em;\n" + 
				"}\n" + 
				"\n" + 
				"#disambig {\n" + 
				"    border-top: 1px solid #ccc; \n" + 
				"    border-bottom: 1px solid #ccc;\n" + 
				"}\n" + 
				"\n" + 
				".spoiler {\n" + 
				"    border-top: 2px solid #ddd;\n" + 
				"    border-bottom: 2px solid #ddd;\n" + 
				"}\n" + 
				"\n" + 
				".Talk-Notice  {\n" + 
				"    border: 1px solid #C0C090;\n" + 
				"    background-color: #F8EABA;\n" + 
				"    margin-bottom: 3px;\n" + 
				"    width: 85%;\n" + 
				"    border-spacing: 3px;\n" + 
				"    margin-left: auto;\n" + 
				"    margin-right: auto;\n" + 
				"}\n" + 
				"\n" + 
				".Talk-Notice:after {\n" + 
				"  content: \"The CSS for this template should be changed. See [[Wikipedia:Template Standardisation]].\";\n" + 
				"}\n" + 
				"\n" + 
				".Talk-Notice td {\n" + 
				"    background: inherit;\n" + 
				"}\n" + 
				"\n" + 
				"\n" + 
				"table.InChI,\n" + 
				"table.persondata {\n" + 
				"    border: 1px solid #aaa;\n" + 
				"    display: none;\n" + 
				"    speak: none;\n" + 
				"}\n" + 
				".InChI-label, \n" + 
				".persondata-label {\n" + 
				"    color: #aaa;\n" + 
				"}\n" + 
				"\n" + 
				".redirect-in-category, .allpagesredirect {\n" + 
				"    font-style: italic;\n" + 
				"}\n" + 
				"\n" + 
				".audiolink a {\n" + 
				"    background: url(\"http://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Loudspeaker.svg/11px-Loudspeaker.svg.png\") center left no-repeat !important;\n" + 
				"    padding-left: 16px !important;\n" + 
				"    padding-right: 0 !important;\n" + 
				"}\n" + 
				"\n" + 
				"div.listenlist {\n" + 
				"    background: url(\"http://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Gnome-speakernotes.png/30px-Gnome-speakernotes.png\");\n" + 
				"    padding-left: 40px;\n" + 
				"}\n" + 
				"\n" + 
				"div.videolist, div.multivideolist {\n" + 
				"    background: url(\"http://upload.wikimedia.org/wikipedia/en/thumb/2/20/Tango-video-x-generic.png/40px-Tango-video-x-generic.png\");\n" + 
				"    padding-left: 50px;\n" + 
				"}\n" + 
				"\n" + 
				"div.medialist {\n" + 
				"    min-height: 50px;\n" + 
				"    margin: 1em;\n" + 
				"    background-position: top left;\n" + 
				"    background-repeat: no-repeat;\n" + 
				"}\n" + 
				"\n" + 
				"div.medialist ul {\n" + 
				"    list-style-type: none; \n" + 
				"    list-style-image: none;\n" + 
				"    margin: 0;\n" + 
				"}\n" + 
				"\n" + 
				"div.medialist ul li {\n" + 
				"    padding-bottom: 0.5em;\n" + 
				"}\n" + 
				"\n" + 
				"div.medialist ul li li {\n" + 
				"    font-size: 91%;\n" + 
				"    padding-bottom: 0;\n" + 
				"}\n" + 
				"\n" + 
				"#bodyContent a[href$=\".pdf\"].external, \n" + 
				"#bodyContent a[href*=\".pdf?\"].external, \n" + 
				"#bodyContent a[href*=\".pdf#\"].external,\n" + 
				"#bodyContent a[href$=\".PDF\"].external, \n" + 
				"#bodyContent a[href*=\".PDF?\"].external, \n" + 
				"#bodyContent a[href*=\".PDF#\"].external,\n" + 
				"#mw_content a[href$=\".pdf\"].external, \n" + 
				"#mw_content a[href*=\".pdf?\"].external, \n" + 
				"#mw_content a[href*=\".pdf#\"].external,\n" + 
				"#mw_content a[href$=\".PDF\"].external, \n" + 
				"#mw_content a[href*=\".PDF?\"].external, \n" + 
				"#mw_content a[href*=\".PDF#\"].external {\n" + 
				"    background: url(http://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Icons-mini-file_acrobat.gif/15px-Icons-mini-file_acrobat.gif) center right no-repeat;\n" + 
				"    padding-right: 16px;\n" + 
				"}\n" + 
				"\n" + 
				"span.PDFlink a {\n" + 
				"    background: url(http://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Icons-mini-file_acrobat.gif/15px-Icons-mini-file_acrobat.gif) center right no-repeat !important;\n" + 
				"    padding-right: 17px !important;\n" + 
				"}\n" + 
				"\n" + 
				"span.geolink a {\n" + 
				"    background: url(http://upload.wikimedia.org/wikipedia/en/a/a7/Monobook-globe.png) center right no-repeat !important;\n" + 
				"    padding-right: 11px !important;\n" + 
				"}\n" + 
				"\n" + 
				"div.columns-2 div.column {\n" + 
				"    float: left;\n" + 
				"    width: 50%;\n" + 
				"    min-width: 300px;\n" + 
				"}\n" + 
				"\n" + 
				"div.columns-3 div.column {\n" + 
				"    float: left;\n" + 
				"    width: 33.3%;\n" + 
				"    min-width: 200px;\n" + 
				"}\n" + 
				"\n" + 
				"div.columns-4 div.column {\n" + 
				"    float: left;\n" + 
				"    width: 25%;\n" + 
				"    min-width: 150px;\n" + 
				"}\n" + 
				"\n" + 
				"div.columns-5 div.column {\n" + 
				"    float: left;\n" + 
				"    width: 20%;\n" + 
				"    min-width: 120px;\n" + 
				"}\n" + 
				"\n" + 
				".plainlinksneverexpand {\n" + 
				"    background: none ! important;\n" + 
				"    padding: 0 ! important;\n" + 
				"}\n" + 
				"\n" + 
				".plainlinksneverexpand .urlexpansion {\n" + 
				"    display: none ! important;\n" + 
				"}\n" + 
				"\n" + 
				".plainlinksneverexpand a {\n" + 
				"    background: none !important;\n" + 
				"    padding: 0 !important;\n" + 
				"}\n" + 
				"\n" + 
				".plainlinksneverexpand a.external.text:after {\n" + 
				"    display: none !important;\n" + 
				"}\n" + 
				".plainlinksneverexpand a.external.autonumber:after {\n" + 
				"    display: none !important;\n" + 
				"}\n" + 
				"\n" + 
				"/* Messagebox templates */\n" + 
				".messagebox {\n" + 
				"    border: 1px solid #aaa;\n" + 
				"    background-color: #f9f9f9;\n" + 
				"    width: 80%;\n" + 
				"    margin: 0 auto 1em auto;\n" + 
				"    padding: .2em;\n" + 
				"}\n" + 
				".messagebox.merge {\n" + 
				"    border: 1px solid #c0b8cc;\n" + 
				"    background-color: #f0e5ff;\n" + 
				"    text-align: center;\n" + 
				"}\n" + 
				".messagebox.cleanup {\n" + 
				"    border: 1px solid #9f9fff;\n" + 
				"    background-color: #efefff;\n" + 
				"    text-align: center;\n" + 
				"}\n" + 
				".messagebox.standard-talk {\n" + 
				"    border: 1px solid #c0c090;\n" + 
				"    background-color: #f8eaba;\n" + 
				"}\n" + 
				".messagebox.nested-talk {\n" + 
				"    border: 1px solid #c0c090;\n" + 
				"    background-color: #f8eaba;\n" + 
				"    width: 100%;\n" + 
				"    margin: 2px 0 0 0;\n" + 
				"    padding: 2px;\n" + 
				"}\n" + 
				".messagebox.small {\n" + 
				"    width: 238px;\n" + 
				"    font-size: 85%;\n" + 
				"    float: right;\n" + 
				"    clear: both;\n" + 
				"    margin: 0 0 1em 1em;\n" + 
				"    line-height: 1.25em; \n" + 
				"}\n" + 
				".messagebox.small-talk {\n" + 
				"    width: 238px;\n" + 
				"    font-size: 85%;\n" + 
				"    float: right;\n" + 
				"    clear: both;\n" + 
				"    margin: 0 0 1em 1em;\n" + 
				"    line-height: 1.25em; \n" + 
				"    background: #F8EABA;\n" + 
				"}\n" + 
				"\n" + 
				"table.ambox {           \n" + 
				"    margin: -1px 10%;   \n" + 
				"    border: 1px solid #aaa; \n" + 
				"    border-left: 10px solid #1e90ff;   \n" + 
				"    background: #fbfbfb; \n" + 
				"}\n" + 
				"th.ambox-text, td.ambox-text {    \n" + 
				"    border: none; \n" + 
				"    padding: 0.25em 0.5em;        \n" + 
				"    width: 100%;                 \n" + 
				"}\n" + 
				"td.ambox-image {               \n" + 
				"    border: none; \n" + 
				"    padding: 2px 0 2px 0.5em;     \n" + 
				"    text-align: center; \n" + 
				"}\n" + 
				"td.ambox-imageright {            \n" + 
				"    border: none; \n" + 
				"    padding: 2px 0.5em 2px 0;     \n" + 
				"    text-align: center; \n" + 
				"}\n" + 
				" \n" + 
				"table.ambox-notice {\n" + 
				"    border-left: 10px solid #1e90ff;   \n" + 
				"}\n" + 
				"table.ambox-speedy {\n" + 
				"    border-left: 10px solid #b22222; \n" + 
				"    background: #fee;               \n" + 
				"}\n" + 
				"table.ambox-delete,\n" + 
				"table.ambox-serious {\n" + 
				"    border-left: 10px solid #b22222;  \n" + 
				"}\n" + 
				"table.ambox-content {\n" + 
				"    border-left: 10px solid #f28500;   \n" + 
				"}\n" + 
				"table.ambox-style {\n" + 
				"    border-left: 10px solid #f4c430;   \n" + 
				"}\n" + 
				"table.ambox-move,\n" + 
				"table.ambox-merge {\n" + 
				"    border-left: 10px solid #9932cc;   \n" + 
				"}\n" + 
				"table.ambox-protection {\n" + 
				"    border-left: 10px solid #bba;     \n" + 
				"}\n" + 
				"table.ambox.ambox-mini {   \n" + 
				"    float: right;\n" + 
				"    clear: right;\n" + 
				"    margin: 0 0 0 1em;\n" + 
				"    width: 25%;\n" + 
				"}\n" + 
				"@media print {          \n" + 
				"    .ambox {\n" + 
				"        display: none;\n" + 
				"    }\n" + 
				"}\n" + 
				"\n" + 
				"table.imbox {\n" + 
				"    margin: 4px 10%; \n" + 
				"    border-collapse: collapse; \n" + 
				"    border: 3px solid #1e90ff;  \n" + 
				"    background: #fbfbfb;\n" + 
				"}\n" + 
				".imbox-text .imbox {      \n" + 
				"    margin: 0 -0.5em;    \n" + 
				"}\n" + 
				".mbox-inside .imbox {    \n" + 
				"    margin: 4px;\n" + 
				"}\n" + 
				"th.imbox-text, td.imbox-text {   \n" + 
				"    border: none; \n" + 
				"    padding: 0.25em 0.9em;       \n" + 
				"    width: 100%;\n" + 
				"}\n" + 
				"td.imbox-image {                \n" + 
				"    border: none; \n" + 
				"    padding: 2px 0 2px 0.9em;    \n" + 
				"    text-align: center; \n" + 
				"}\n" + 
				"td.imbox-imageright {         \n" + 
				"    border: none;\n" + 
				"    padding: 2px 0.9em 2px 0;     \n" + 
				"    text-align: center; \n" + 
				"}\n" + 
				" \n" + 
				"table.imbox-notice {\n" + 
				"    border: 3px solid #1e90ff;  \n" + 
				"}\n" + 
				"table.imbox-speedy {\n" + 
				"    border: 3px solid #b22222;  \n" + 
				"    background: #fee;            \n" + 
				"}\n" + 
				"table.imbox-delete {\n" + 
				"    border: 3px solid #b22222;    \n" + 
				"}\n" + 
				"table.imbox-content {\n" + 
				"    border: 3px solid #f28500;   \n" + 
				"}\n" + 
				"table.imbox-style {\n" + 
				"    border: 3px solid #f4c430;  \n" + 
				"}\n" + 
				"table.imbox-move {\n" + 
				"    border: 3px solid #9932cc;   \n" + 
				"}\n" + 
				"table.imbox-protection {\n" + 
				"    border: 3px solid #bba;      \n" + 
				"}\n" + 
				"table.imbox-license {\n" + 
				"    border: 3px solid #88a;     \n" + 
				"    background: #f7f8ff;        \n" + 
				"}\n" + 
				"table.imbox-featured {\n" + 
				"    border: 3px solid #cba135;   \n" + 
				"}\n" + 
				"\n" + 
				"table.cmbox {\n" + 
				"    margin: -1px 10%;\n" + 
				"    border: 1px solid #aaa; \n" + 
				"    background: #DFE8FF;           \n" + 
				"}\n" + 
				"th.cmbox-text, td.cmbox-text {   \n" + 
				"    border: none; \n" + 
				"    padding: 0.25em 0.5em;        \n" + 
				"    width: 100%;\n" + 
				"}\n" + 
				"td.cmbox-image {                  \n" + 
				"    border: none; \n" + 
				"    padding: 2px 0 2px 0.5em;     \n" + 
				"    text-align: center; \n" + 
				"}\n" + 
				"td.cmbox-imageright {             \n" + 
				"    border: none;\n" + 
				"    padding: 2px 0.8em 2px 0;     \n" + 
				"    text-align: center; \n" + 
				"}\n" + 
				" \n" + 
				"table.cmbox-notice {\n" + 
				"    background: #DFE8FF; \n" + 
				"}\n" + 
				"table.cmbox-speedy {\n" + 
				"    margin-top: 1px;\n" + 
				"    margin-bottom: 1px;\n" + 
				"    border: 4px solid #b22222;  \n" + 
				"    background: #FFDBDB;        \n" + 
				"}\n" + 
				"table.cmbox-delete {\n" + 
				"    background: #FFDBDB;  \n" + 
				"}\n" + 
				"table.cmbox-content {\n" + 
				"    background: #FFE7CE;    \n" + 
				"}\n" + 
				"table.cmbox-style {\n" + 
				"    background: #FFF9DB;  \n" + 
				"}\n" + 
				"table.cmbox-move {\n" + 
				"    background: #F1D0FF;   \n" + 
				"}\n" + 
				"table.cmbox-protection {\n" + 
				"    background: #EFEFE1;   \n" + 
				"}\n" + 
				"\n" + 
				"#file img {\n" + 
				"    background: url(\"http://upload.wikimedia.org/wikipedia/commons/5/5d/Checker-16x16.png\") repeat;\n" + 
				"}\n" + 
				"\n" + 
				".IPA {\n" + 
				"    font-family: \"Charis SIL\", \"Doulos SIL\", Gentium, GentiumAlt, \"DejaVu Sans\", Code2000, \"TITUS Cyberbit Basic\", \"Arial Unicode MS\", \"Lucida Sans Unicode\", \"Chrysanthi Unicode\";\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				".Unicode {\n" + 
				"    font-family: Code2000, Code2001, \"Free Serif\", \"TITUS Cyberbit Basic\", \"Doulos SIL\", \"Chrysanthi Unicode\", \"Bitstream Cyberbit\", \"Bitstream CyberBase\", Thryomanes, Gentium, GentiumAlt, \"Lucida Grande\", \"Free Sans\", \"Arial Unicode MS\", \"Microsoft Sans Serif\", \"Lucida Sans Unicode\";\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				".latinx {\n" + 
				"    font-family: Code2000, Code2001, \"TITUS Cyberbit Basic\", \"Microsoft Sans Serif\";\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				".polytonic {\n" + 
				"    font-family: \"Athena Unicode\", Gentium, \"Palatino Linotype\", \"Arial Unicode MS\", \"Lucida Sans Unicode\", \"Lucida Grande\", Code2000; \n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				".mufi {\n" + 
				"    font-family: Alphabetum, Cardo, LeedsUni, Junicode, \"TITUS Cyberbit Basic\", ALPHA-Demo;\n" + 
				"}\n" + 
				"\n" + 
				"\n" + 
				":lang(he) {\n" + 
				"    font-family: \"SBL Hebrew\", \"Ezra SIL SR\", \"Ezra SIL\", Cardo, \"Chrysanthi Unicode\", \"TITUS Cyberbit Basic\", \"Arial Unicode MS\", Narkisim, \"Times New Roman\";\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(fa) {\n" + 
				"    font-family: \"Nafees Nastaleeq\", \"Pak Nastaleeq\", PDMS_Jauhar;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(ps) {\n" + 
				"    font-family: \"Nafees Nastaleeq\", \"Pak Nastaleeq\", PDMS_Jauhar;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(ur) {\n" + 
				"    font-family: \"Nafees Nastaleeq\", \"Pak Nastaleeq\", PDMS_Jauhar;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(sux-Xsux) {\n" + 
				"    font-family: Akkadian;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(ja) {\n" + 
				"       font-family: Code2000, \"Arial Unicode MS\", \"Bitstream Cyberbit\", \"Bitstream CyberCJK\", IPAGothic, IPAPGothic, IPAUIGothic, \"Kochi Gothic\", IPAMincho, IPAPMincho;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(ko) {\n" + 
				"    font-family: \"Adobe Myungjo Std M\", AppleMyungjo, \"Baekmuk Batang\", \"Baekmuk Gulim\", Batang, Dotum, DotumChe, Gulim, GulimChe, HYGothic-Extra, HYMyeongJo-Extra, \"New Gulim\", UnBatang, UnDotum, UnYetgul, UWKMJF;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(zh-Hans) {\n" + 
				"    font-family: \"Adobe Song Std L\", \"AR PL ShanHeiSun Uni\", \"AR PL ShanHeiSun Uni MBE\", \"MS Hei\", \"MS Song\", SimHei;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(zh-Hant) {\n" + 
				"    font-family: \"Adobe Ming Std L\", \"AR PL New Sung\", \"AR PL ZenKai Uni\", \"AR PL ZenKai Uni MBE\", MingLiU, PMingLiU;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				":lang(grc) {\n" + 
				"    font-family: \"Athena Unicode\", Gentium, \"Palatino Linotype\", \"Arial Unicode MS\", \"Lucida Sans Unicode\", \"Lucida Grande\", Code2000;\n" + 
				"    font-family /**/:inherit;\n" + 
				"}\n" + 
				"\n" + 
				"#wpSave {\n" + 
				"    font-weight: bold;\n" + 
				"}\n" + 
				"\n" + 
				".hiddenStructure {\n" + 
				"    display: inline ! important;\n" + 
				"    color: #f00; \n" + 
				"    background-color: #0f0;\n" + 
				"}\n" + 
				"\n" + 
				".check-icon a.new {\n" + 
				"    display: none; \n" + 
				"    speak:none;\n" + 
				"}\n" + 
				"\n" + 
				".nounderlines a { \n" + 
				"    text-decoration: none;\n" + 
				"}\n" + 
				"\n" + 
				".IPA a:link, .IPA a:visited {\n" + 
				"    text-decoration: none;\n" + 
				"}\n" + 
				"\n" + 
				"@media print {\n" + 
				"    #privacy, #about, #disclaimer {display:none;}\n" + 
				"}\n" + 
				"\n" + 
				"#EnWpMpBook { background-image: url(http://upload.wikimedia.org/wikipedia/en/7/7e/MP-open-book.png); }\n" + 
				"#EnWpMpSearch { background: url(http://upload.wikimedia.org/wikipedia/en/a/ae/MP-magnifying-glass.png) no-repeat top right; }\n" + 
				"#EnWpMpSearchInner { float: right; width: 20em; text-align: center; }\n" + 
				"#EnWpMpBook2 { background-image: url(http://upload.wikimedia.org/wikipedia/commons/8/8e/MP-open-book2.png); }\n" + 
				"\n" + 
				"div.Boxmerge,\n" + 
				"div.NavFrame {\n" + 
				"    margin: 0;\n" + 
				"    padding: 4px;\n" + 
				"    border: 1px solid #aaa;\n" + 
				"    text-align: center;\n" + 
				"    border-collapse: collapse;\n" + 
				"    font-size: 95%;\n" + 
				"}\n" + 
				"div.Boxmerge div.NavFrame {\n" + 
				"    border-style: none;\n" + 
				"    border-style: hidden;\n" + 
				"}\n" + 
				"div.NavFrame + div.NavFrame {\n" + 
				"    border-top-style: none;\n" + 
				"    border-top-style: hidden;\n" + 
				"}\n" + 
				"div.NavPic {\n" + 
				"    background-color: #fff;\n" + 
				"    margin: 0;\n" + 
				"    padding: 2px;\n" + 
				"    float: left;\n" + 
				"}\n" + 
				"div.NavFrame div.NavHead {\n" + 
				"    height: 1.6em;\n" + 
				"    font-weight: bold;\n" + 
				"    background-color: #ccf;\n" + 
				"    position:relative;\n" + 
				"}\n" + 
				"div.NavFrame p {\n" + 
				"    font-size: 100%;\n" + 
				"}\n" + 
				"div.NavFrame div.NavContent {\n" + 
				"    font-size: 100%;\n" + 
				"}\n" + 
				"div.NavFrame div.NavContent p {\n" + 
				"    font-size: 100%;\n" + 
				"}\n" + 
				"div.NavEnd {\n" + 
				"    margin: 0;\n" + 
				"    padding: 0;\n" + 
				"    line-height: 1px;\n" + 
				"    clear: both;\n" + 
				"}\n" + 
				"a.NavToggle {\n" + 
				"    position: absolute;\n" + 
				"    top: 0;\n" + 
				"    right: 3px;\n" + 
				"    font-weight: normal;\n" + 
				"    font-size: 90%;\n" + 
				"}\n" + 
				"\n" + 
				".mw-plusminus-pos {\n" + 
				"    color: #006400;\n" + 
				"}\n" + 
				"\n" + 
				".mw-plusminus-neg {\n" + 
				"    color: #8B0000; \n" + 
				"}\n" + 
				"\n" + 
				".dablink {\n" + 
				"    font-style: italic;\n" + 
				"    padding-left: 2em;\n" + 
				"}\n" + 
				"\n" + 
				".dablink i {\n" + 
				"    font-style: normal;\n" + 
				"}\n" + 
				"\n" + 
				".horizontal ul {\n" + 
				"    padding: 0;\n" + 
				"    margin: 0;\n" + 
				"}\n" + 
				"\n" + 
				".horizontal li { \n" + 
				"    padding: 0 0.6em 0 0.4em;\n" + 
				"    display: inline;\n" + 
				"    border-right: 1px solid;\n" + 
				"}\n" + 
				"\n" + 
				".horizontal li:last-child {\n" + 
				"    border-right: none;\n" + 
				"    padding-right: 0;\n" + 
				"}\n" + 
				"\n" + 
				".geo-default { display: inline; }\n" + 
				".geo-nondefault { display: none; }\n" + 
				".geo-dms { display: inline; }\n" + 
				".geo-dec { display: inline; }\n" + 
				".geo-multi-punct { display: none; }\n" + 
				"\n" + 
				".longitude, .latitude {\n" + 
				"    white-space: nowrap;\n" + 
				"}\n" + 
				"\n" + 
				".nonumtoc .tocnumber { display:none; }\n" + 
				".nonumtoc #toc ul,\n" + 
				".nonumtoc .toc ul {\n" + 
				"    line-height: 1.5em;\n" + 
				"    list-style: none;\n" + 
				"    margin: .3em 0 0;\n" + 
				"    padding: 0;\n" + 
				"}\n" + 
				".nonumtoc #toc ul ul, \n" + 
				".nonumtoc .toc ul ul { \n" + 
				"    margin: 0 0 0 2em; \n" + 
				"}\n" + 
				"\n" + 
				".toclimit-2 .toclevel-2 {display:none;}\n" + 
				".toclimit-3 .toclevel-3 {display:none;}\n" + 
				".toclimit-4 .toclevel-4 {display:none;}\n" + 
				".toclimit-5 .toclevel-5 {display:none;}\n" + 
				".toclimit-6 .toclevel-6 {display:none;}\n" + 
				".toclimit-7 .toclevel-7 {display:none;}\n" + 
				"\n" + 
				".listify td {display:list-item;}\n" + 
				".listify tr {display:block;}\n" + 
				".listify table {display:block;}\n" + 
				"\n" + 
				"\n" + 
				"blockquote.templatequote { margin-top: 0; }\n" + 
				"\n" + 
				"blockquote.templatequote div.templatequotecite { \n" + 
				"    line-height: 1em;\n" + 
				"    text-align: left;\n" + 
				"    padding-left: 2em;\n" + 
				"    margin-top: 0;\n" + 
				"}\n" + 
				"\n" + 
				"blockquote.templatequote div.templatequotecite cite {\n" + 
				"    font-size: 85%;\n" + 
				"}\n" + 
				"\n" + 
				"div.user-block {\n" + 
				"    padding: 5px;\n" + 
				"    border: 1px solid #A9A9A9;\n" + 
				"    background-color: #FFEFD5;\n" + 
				"}\n" + 
				"\n" + 
				".nowraplinks a {\n" + 
				"    white-space: nowrap;\n" + 
				"}\n" + 
				"\n" + 
				".template-documentation {\n" + 
				"    clear: both;\n" + 
				"    margin: 1em 0 0 0;\n" + 
				"    border: 1px solid #aaa; \n" + 
				"    background-color: #ecfcf4; \n" + 
				"    padding: 5px;\n" + 
				"}\n" + 
				"\n" + 
				".thumbinner {\n" + 
				"    min-width: 100px;\n" + 
				"}\n" + 
				"\n" + 
				".imagemap-inline div {\n" + 
				"    display: inline;\n" + 
				"}\n" + 
				"\n" + 
				"#wpUploadDescription {\n" + 
				"    height: 13em;\n" + 
				"}\n" + 
				"\n" + 
				"sup, sub {\n" + 
				"    line-height: 1em;\n" + 
				"}\n" + 
				"</style>\n" + 
				"";
		
		public final static String CSS_PRINTER_STYLE = "<style type=\"text/css\">\n" + 
		 "a.stub,\n" + 
		 "a.new{ color:#ba0000; text-decoration:none; }\n" + 
		 "\n" + 
		 "#toc { \n" + 
		 "    /*border:1px solid #2f6fab;*/\n" + 
		 "    border:1px solid #aaaaaa;\n" + 
		 "    background-color:#f9f9f9;\n" + 
		 "    padding:5px;\n" + 
		 "}\n" + 
		 ".tocindent {\n" + 
		 "	margin-left: 2em;\n" + 
		 "}\n" + 
		 ".tocline {\n" + 
		 "	margin-bottom: 0px;\n" + 
		 "}\n" + 
		 "\n" + 
		 "/* images */\n" + 
		 "div.floatright { \n" + 
		 "    float: right;\n" + 
		 "    clear: right;\n" + 
		 "    margin: 0;\n" + 
		 "    position:relative;\n" + 
		 "    border: 0.5em solid White;\n" + 
		 "    border-width: 0.5em 0 0.8em 1.4em;\n" + 
		 "}\n" + 
		 "div.floatright p { font-style: italic;} \n" + 
		 "div.floatleft { \n" + 
		 "    float: left; \n" + 
		 "    margin: 0.3em 0.5em 0.5em 0;\n" + 
		 "    position:relative;\n" + 
		 "    border: 0.5em solid White;\n" + 
		 "    border-width: 0.5em 1.4em 0.8em 0;\n" + 
		 "}\n" + 
		 "div.floatleft p { font-style: italic; } \n" + 
		 "/* thumbnails */\n" + 
		 "div.thumb {\n" + 
		 "    margin-bottom: 0.5em;\n" + 
		 "    border-style: solid; border-color: White;\n" + 
		 "    width: auto;\n" + 
		 "    overflow: hidden;\n" + 
		 "}\n" + 
		 "div.thumb div {\n" + 
		 "    border:1px solid #cccccc;\n" + 
		 "    padding: 3px !important;\n" + 
		 "    background-color:#f9f9f9;\n" + 
		 "    font-size: 94%;\n" + 
		 "    text-align: center;\n" + 
		 "}\n" + 
		 "div.thumb div a img {\n" + 
		 "    border:1px solid #cccccc;\n" + 
		 "}\n" + 
		 "div.thumb div div.thumbcaption {\n" + 
		 "    border: none;\n" + 
		 "    padding: 0.3em 0 0.1em 0;\n" + 
		 "}\n" + 
		 "div.magnify { display: none; }\n" + 
		 "div.tright {\n" + 
		 "    float: right;\n" + 
		 "    clear: right;\n" + 
		 "    border-width: 0.5em 0 0.8em 1.4em;\n" + 
		 "}\n" + 
		 "div.tleft {\n" + 
		 "    float: left;\n" + 
		 "    margin-right:0.5em;\n" + 
		 "    border-width: 0.5em 1.4em 0.8em 0;\n" + 
		 "}\n" + 
		 "img.thumbborder {\n" + 
		 "	border: 1px solid #dddddd;\n" + 
		 "}\n" + 
		 "\n" + 
		 "/* table standards */\n" + 
		 "table.rimage {\n" + 
		 "    float:right; \n" + 
		 "    width:1pt; \n" + 
		 "    position:relative;\n" + 
		 "    margin-left:1em; \n" + 
		 "    margin-bottom:1em;\n" + 
		 "    text-align:center;\n" + 
		 "}\n" + 
		 "\n" + 
		 "body {\n" + 
		 "    background: White;\n" + 
		 "    /*font-size: 11pt !important;*/\n" + 
		 "    color: Black;\n" + 
		 "    margin: 0;\n" + 
		 "    padding: 0;\n" + 
		 "}\n" + 
		 "\n" + 
		 ".noprint,\n" + 
		 "div#jump-to-nav,\n" + 
		 "div.top,\n" + 
		 "div#column-one,\n" + 
		 "#colophon,\n" + 
		 ".editsection,\n" + 
		 ".toctoggle,\n" + 
		 ".tochidden,\n" + 
		 "div#f-poweredbyico,\n" + 
		 "div#f-copyrightico,\n" + 
		 "li#viewcount,\n" + 
		 "li#about,\n" + 
		 "li#disclaimer,\n" + 
		 "li#privacy {\n" + 
		 "    /* Hides all the elements irrelevant for printing */\n" + 
		 "    display: none;\n" + 
		 "}\n" + 
		 "\n" + 
		 "ul { \n" + 
		 "    list-style-type: square;\n" + 
		 "}\n" + 
		 "\n" + 
		 "#content {\n" + 
		 "    background: none;\n" + 
		 "    border: none ! important;\n" + 
		 "    padding: 0 ! important;\n" + 
		 "    margin: 0 ! important;\n" + 
		 "}\n" + 
		 "#footer {\n" + 
		 "	background : white;\n" + 
		 "	color : black;\n" + 
		 "    border-top: 1px solid black;\n" + 
		 "}\n" + 
		 "\n" + 
		 "h1, h2, h3, h4, h5, h6 {\n" + 
		 "	font-weight: bold;\n" + 
		 "}\n" + 
		 "\n" + 
		 "p, .documentDescription {\n" + 
		 "    margin: 1em 0 ! important;\n" + 
		 "    line-height: 1.2em;\n" + 
		 "}\n" + 
		 "\n" + 
		 ".tocindent p {\n" + 
		 "	margin: 0 0 0 0 ! important;\n" + 
		 "}\n" + 
		 "\n" + 
		 "pre {\n" + 
		 "    border: 1pt dashed black;\n" + 
		 "    white-space: pre;\n" + 
		 "    font-size: 8pt;\n" + 
		 "    overflow: auto;\n" + 
		 "    padding: 1em 0;\n" + 
		 "	background : white;\n" + 
		 "	color : black;\n" + 
		 "}\n" + 
		 "\n" + 
		 "table.listing,\n" + 
		 "table.listing td {\n" + 
		 "    border: 1pt solid black;\n" + 
		 "    border-collapse: collapse;\n" + 
		 "}\n" + 
		 "\n" + 
		 "a {\n" + 
		 "    color: Black !important;\n" + 
		 "    background: none !important;\n" + 
		 "    padding: 0 !important;\n" + 
		 "}\n" + 
		 "\n" + 
		 "a:link, a:visited {\n" + 
		 "    color: #520;\n" + 
		 "    background: transparent;\n" + 
		 "    text-decoration: underline;\n" + 
		 "}\n" + 
		 "\n" + 
		 "#content a.external.text:after, #content a.external.autonumber:after {\n" + 
		 "    /* Expand URLs for printing */\n" + 
		 "    content: \" (\" attr(href) \") \";\n" + 
		 "}\n" + 
		 "\n" + 
		 "#globalWrapper {\n" + 
		 "    width: 100% !important;\n" + 
		 "    min-width: 0 !important;\n" + 
		 "}\n" + 
		 "\n" + 
		 "#content {\n" + 
		 "	background : white;\n" + 
		 "	color : black;\n" + 
		 "}\n" + 
		 "\n" + 
		 "#column-content {\n" + 
		 "    margin: 0 !important;\n" + 
		 "}\n" + 
		 "\n" + 
		 "#column-content #content {\n" + 
		 "    padding: 1em;\n" + 
		 "    margin: 0 !important;\n" + 
		 "}\n" + 
		 "/* MSIE/Win doesn\'t understand \'inherit\' */\n" + 
		 "a, a.external, a.new, a.stub {\n" + 
		 "	color: black ! important;\n" + 
		 "	text-decoration: none ! important;\n" + 
		 "}\n" + 
		 "\n" + 
		 "/* Continue ... */\n" + 
		 "a, a.external, a.new, a.stub {\n" + 
		 "	color: inherit ! important;\n" + 
		 "	text-decoration: inherit ! important;\n" + 
		 "}\n" + 
		 "\n" + 
		 "img { border: none; }\n" + 
		 "img.tex { vertical-align: middle; }\n" + 
		 "span.texhtml { font-family: serif; }\n" + 
		 "\n" + 
		 "div.townBox {\n" + 
		 "    position:relative;\n" + 
		 "    float:right;\n" + 
		 "    background:White;\n" + 
		 "    margin-left:1em;\n" + 
		 "    border: 1px solid gray;\n" + 
		 "    padding:0.3em;\n" + 
		 "    width: 200px;\n" + 
		 "    overflow: hidden;\n" + 
		 "    clear: right;\n" + 
		 "}\n" + 
		 "div.townBox dl {\n" + 
		 "    padding: 0;\n" + 
		 "    margin: 0 0 0.3em 0; \n" + 
		 "    font-size: 96%;\n" + 
		 "}\n" + 
		 "div.townBox dl dt {\n" + 
		 "    background: none;\n" + 
		 "    margin: 0.4em 0 0 0;\n" + 
		 "}\n" + 
		 "div.townBox dl dd {\n" + 
		 "    margin: 0.1em 0 0 1.1em;\n" + 
		 "    background-color: #f3f3f3;\n" + 
		 "}\n" + 
		 "\n" + 
		 "#siteNotice { display: none; }\n" + 
		 "\n" + 
		 "table.gallery {\n" + 
		 "        border:  1px solid #cccccc;\n" + 
		 "        margin:  2px;\n" + 
		 "        padding: 2px;\n" + 
		 "        background-color:#ffffff;\n" + 
		 "}\n" + 
		 "\n" + 
		 "table.gallery tr { \n" + 
		 "        vertical-align:top;\n" + 
		 "}\n" + 
		 "\n" + 
		 "div.gallerybox {\n" + 
		 "        border: 1px solid #cccccc;\n" + 
		 "        margin: 2px;\n" + 
		 "        background-color:#f9f9f9;\n" + 
		 "        width:  150px;\n" + 
		 "}\n" + 
		 "\n" + 
		 "div.gallerybox div.thumb {\n" + 
		 "        text-align: center;\n" + 
		 "        border: 1px solid #cccccc;\n" + 
		 "        margin: 2px;\n" + 
		 "}       \n" + 
		 "\n" + 
		 "div.gallerytext {\n" + 
		 "        font-size: 94%;\n" + 
		 "        padding: 2px 4px;\n" + 
		 "}       \n" + 
		 "\n" + 
		 "/*\n" + 
		 "** Diff rendering\n" + 
		 "*/\n" + 
		 "table.diff { background:white; }\n" + 
		 "td.diff-otitle { background:#ffffff; }\n" + 
		 "td.diff-ntitle { background:#ffffff; }\n" + 
		 "td.diff-addedline {\n" + 
		 "    background:#ccffcc;\n" + 
		 "    font-size: smaller;\n" + 
		 "    border: solid 2px black;\n" + 
		 "}\n" + 
		 "td.diff-deletedline {\n" + 
		 "    background:#ffffaa;\n" + 
		 "    font-size: smaller;\n" + 
		 "    border: dotted 2px black;\n" + 
		 "}\n" + 
		 "td.diff-context {\n" + 
		 "    background:#eeeeee;\n" + 
		 "    font-size: smaller;\n" + 
		 "}\n" + 
		 ".diffchange {\n" + 
		 "    color: silver;\n" + 
		 "    font-weight: bold;\n" + 
		 "    text-decoration: underline;\n" + 
		 "}\n" + 
				"</style>\n" + 
				"";
		
	//	public final static String CSS_STYLE = "<style type=\"text/css\">\n" + "h1 { color: maroon; }\n" + "h2 { color: orange; }\n"
	//			+ "b  { color: green; }\n" + "@page { \n" + "margin: 0.25in; \n" + "-fs-flow-top: header; \n" + "-fs-flow-bottom: footer; \n"
	//			+ "-fs-flow-left: left; \n" + "-fs-flow-right: right; \n" + "padding: 1em; \n" + "} \n" + "</style>\n";
	/**
	 * With a <code>wikipedia.css</code> file you can set the CSS of the
	 * rendered HTML data:
	 */
	public final static String HTML_HEADER1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\" dir=\"ltr\">\n"
			+ "	<head>\n";
	
	public final static String HTML_HEADER2 = 
	  "	</head>\n"
		+ "\n"
		+ "<body>";
	
	public final static String HTML_FOOTER = "</body></html>\n";

}
