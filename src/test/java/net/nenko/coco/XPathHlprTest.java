package net.nenko.coco;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.nenko.lib.XPathHlpr;

public class XPathHlprTest {

	private static final String doc = "<Table>" + 
			"<Row id=\"21\"><Cell id=\"99\">ABC99</Cell></Row>" + 
			"<Row id=\"22\"><Cell id=\"77\">DEF77</Cell></Row>" + 
			"<Row id=\"23\"><Cell id=\"66\">ABC66</Cell><Cell id=\"55\">ZZZ55</Cell></Row>" + 
			"</Table>";

	@Test
	public void testGood1() {
		String template = "TEST: {/Table/Row/@id} must be 21";
		String actual = XPathHlpr.formatWithXPath(template, doc);
		assertEquals("TEST: 21 must be 21", actual);

		template = "zzz{/Table/Row[@id=\"22\"]/Cell/text()}zzz";
		actual = XPathHlpr.formatWithXPath(template, doc);
		assertEquals("zzzDEF77zzz", actual);
	}

	@Test
	public void testBadXml() {
		String template = "TEST{/Table/Row/@id}TEST";
		String actual = XPathHlpr.formatWithXPath(template, "<AAA><BBB>");
		assertEquals("TEST{PARSE ERROR: org.xml.sax.SAXParseException; lineNumber: 1; columnNumber: 11; XML document structures must start and end within the same entity.}TEST", actual);
	}

	@Test
	public void testBadXPath() {
		String template = "TEST: {/Table/Row\\@id} must be 21";
		String actual = XPathHlpr.formatWithXPath(template, doc);
		assertEquals("TEST: {PARSE ERROR: javax.xml.transform.TransformerException: Extra illegal tokens: '\\', '@', 'id'} must be 21", actual);
	}


}
