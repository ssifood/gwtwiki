package info.bliki.wiki.filter;

import info.bliki.wiki.model.Reference;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

public class RefFilterTest extends FilterTestSupport {
  public RefFilterTest(String name) {
    super(name);
  }

  public static Test suite() {
    return new TestSuite(RefFilterTest.class);
  }
  
  public void testRef01() {
    assertEquals(
        "\n"
            + "<p>A <sup id=\"_ref-1\" class=\"reference\"><a href=\"#_note-1\" title=\"\">[1]</a></sup> Test</p><ol class=\"references\">\n"
            + "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> Reference</li></ol>",
        wikiModel.render("A <ref>Reference</ref> Test\n\n<references/>"));
    List<Reference> list = wikiModel.getReferences();
    Reference ref = list.get(0);
    assertTrue(ref.getRefString().equals("Reference"));
  }

  public void testRef02() {
    assertEquals(
        "\n"
            + "<p>A <sup id=\"_ref-1\" class=\"reference\"><a href=\"#_note-1\" title=\"\">[1]</a></sup> and a <sup id=\"_ref-2\" class=\"reference\"><a href=\"#_note-2\" title=\"\">[2]</a></sup> Test</p><ol class=\"references\">\n"
            + "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> first reference</li><li id=\"_note-2\"><b><a href=\"#_ref-2\" title=\"\">&#8593;</a></b> second reference</li></ol>",
        wikiModel
            .render("A <ref>first reference</ref> and a <ref>second reference</ref> Test\n\n<references/>"));
    List<Reference> list = wikiModel.getReferences();
    Reference ref = list.get(0);
    assertTrue(ref.getRefString().equals("first reference"));
    ref = list.get(1);
    assertTrue(ref.getRefString().equals("second reference"));
  }

  public void testRef03() {
    assertEquals(
        "\n"
            + "<p>aaa <sup id=\"_ref-Freitag_a\" class=\"reference\"><a href=\"#_note-Freitag\" title=\"\">[1]</a></sup> bbb<sup id=\"_ref-Arndt_a\" class=\"reference\"><a href=\"#_note-Arndt\" title=\"\">[2]</a></sup> <sup id=\"_ref-3\" class=\"reference\"><a href=\"#_note-3\" title=\"\">[3]</a></sup> </p><ol class=\"references\">\n"
            + "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> </li><li id=\"_note-2\"><b><a href=\"#_ref-2\" title=\"\">&#8593;</a></b> </li><li id=\"_note-3\"><b><a href=\"#_ref-3\" title=\"\">&#8593;</a></b> ccc</li></ol>",
        wikiModel
            .render("aaa <ref name=\"Freitag\"/> bbb<ref name=\"Arndt\"/> <ref>ccc</ref> <references/>"));
    List<Reference> list = wikiModel.getReferences();
    Reference ref = list.get(0);
    assertTrue(ref.getRefString().equals(""));
    ref = list.get(1);
    assertTrue(ref.getRefString().equals(""));
    ref = list.get(2);
    assertTrue(ref.getRefString().equals("ccc"));
  }

  public void testRef04() {
    assertEquals(
        "\n"
            + "<p>aaa <sup id=\"_ref-Freitag_a\" class=\"reference\"><a href=\"#_note-Freitag\" title=\"\">[1]</a></sup> bbb<sup id=\"_ref-Arndt_a\" class=\"reference\"><a href=\"#_note-Arndt\" title=\"\">[2]</a></sup> <sup id=\"_ref-3\" class=\"reference\"><a href=\"#_note-3\" title=\"\">[3]</a></sup> </p><ol class=\"references\">\n"
            + "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> </li><li id=\"_note-2\"><b><a href=\"#_ref-2\" title=\"\">&#8593;</a></b> arn</li><li id=\"_note-3\"><b><a href=\"#_ref-3\" title=\"\">&#8593;</a></b> ccc</li></ol>",
        wikiModel
            .render("aaa <ref name=Freitag></ref> bbb<ref	name=Arndt>arn</ref> <ref>ccc</ref> <references/>"));
    List<Reference> list = wikiModel.getReferences();
    Reference ref = list.get(0);
    assertTrue(ref.getRefString().equals(""));
    ref = list.get(1);
    assertTrue(ref.getRefString().equals("arn"));
    ref = list.get(2);
    assertTrue(ref.getRefString().equals("ccc"));
  }

  public void testRef05() {
    assertEquals(
        "\n"
            + "<p>aaa <sup id=\"_ref-Freitag_a\" class=\"reference\"><a href=\"#_note-Freitag\" title=\"\">[1]</a></sup> bbb<sup id=\"_ref-Arndt_a\" class=\"reference\"><a href=\"#_note-Arndt\" title=\"\">[2]</a></sup> <sup id=\"_ref-3\" class=\"reference\"><a href=\"#_note-3\" title=\"\">[3]</a></sup> </p><ol class=\"references\">\n"
            + "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> </li><li id=\"_note-2\"><b><a href=\"#_ref-2\" title=\"\">&#8593;</a></b> </li><li id=\"_note-3\"><b><a href=\"#_ref-3\" title=\"\">&#8593;</a></b> ccc</li></ol>",
        wikiModel
            .render("aaa <ref name=Freitag/> bbb<ref name=Arndt /> <ref>ccc</ref> <references/>"));
    List<Reference> list = wikiModel.getReferences();
    Reference ref = list.get(0);
    assertTrue(ref.getRefString().equals(""));
    ref = list.get(1);
    assertTrue(ref.getRefString().equals(""));
    ref = list.get(2);
    assertTrue(ref.getRefString().equals("ccc"));
  }

  public void testRef06() {
    assertEquals(
        "\n"
            + "<p>aaa <sup id=\"_ref-Freitag_a\" class=\"reference\"><a href=\"#_note-Freitag\" title=\"\">[1]</a></sup> bbb<sup id=\"_ref-A.26B_a\" class=\"reference\"><a href=\"#_note-A.26B\" title=\"\">[2]</a></sup> <sup id=\"_ref-3\" class=\"reference\"><a href=\"#_note-3\" title=\"\">[3]</a></sup> </p><ol class=\"references\">\n"
            + "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> </li><li id=\"_note-2\"><b><a href=\"#_ref-2\" title=\"\">&#8593;</a></b> </li><li id=\"_note-3\"><b><a href=\"#_ref-3\" title=\"\">&#8593;</a></b> ccc</li></ol>",
        wikiModel
            .render("aaa <ref name=Freitag/> bbb<ref name=A&B /> <ref>ccc</ref> <references/>"));
    List<Reference> list = wikiModel.getReferences();
    Reference ref = list.get(0);
    assertTrue(ref.getRefString().equals(""));
    ref = list.get(1);
    assertTrue(ref.getRefString().equals(""));
    ref = list.get(2);
    assertTrue(ref.getRefString().equals("ccc"));
  }

  public void testRef07() {
  	assertEquals(
        "\n" + 
        "<p><sup id=\"_ref-1\" class=\"reference\"><a href=\"#_note-1\" title=\"\">[1]</a></sup></p>\n" + 
        "<ol class=\"references\">\n" + 
        "<li id=\"_note-1\"><b><a href=\"#_ref-1\" title=\"\">&#8593;</a></b> <span class=\"citation book\">Malins, Steve (2001). {{Citation/make link}}. Andre Deutsch. pp. 82. <a class=\"external text\" href=\"http://www.amazon.com/exec/obidos/ASIN/9780233994307\" rel=\"nofollow\" title=\"http://www.amazon.com/exec/obidos/ASIN/9780233994307\">ISBN 978-0233994307</a>.</span><span class=\"Z3988\" title=\"ctx_ver=Z39.88-2004&amp;rft_val_fmt=info%3Aofi%2Ffmt%3Akev%3Amtx%3Abook&amp;rft.genre=book&amp;rft.btitle=Depeche+Mode%3A+A+Biography&amp;rft.aulast=Malins&amp;rft.aufirst=Steve&amp;rft.au=Malins%2C%26%2332%3BSteve&amp;rft.date=2001&amp;rft.pages=pp.%26nbsp%3B82&amp;rft.pub=Andre+Deutsch&amp;rft.isbn=978-0233994307&amp;rfr_id=info:sid/en.wikipedia.org:FULLPAGENAMEE\"><span style=\"display: none;\"> </span></span></li></ol>",
        wikiModel
            .render("<ref>{{cite book |last=Malins|first=Steve|title=Depeche Mode: A Biography|year=2001|publisher=Andre Deutsch|chapter=|pages=82|isbn=978-0233994307}}</ref>\n\n\n<references/>"));
    
  }
  
}