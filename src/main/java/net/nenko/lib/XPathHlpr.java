package net.nenko.lib;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

/**
 * XPathHlpr generic processing of Strings, XML and XPath
 *
 * @author alex.nenko
 */
public final class XPathHlpr {

	private XPathHlpr() {}

	public static String formatWithXPath(String template, String xmlData) {
		StringBuilder sb = new StringBuilder();
		XPathFactory factory = XPathFactory.newInstance();

		InputSource inputXml = new InputSource( new StringReader(xmlData));

		for(int index = 0; index < template.length(); ) {
			int curlya = template.indexOf('{', index);	// opening curly brace
			if(curlya < 0) {
				sb.append(template.substring(index));
				break;
			}
			sb.append(template.substring(index, curlya));
			index = curlya + 1;

			curlya = template.indexOf('}', index);	// closing curly brace
			if(curlya < 0) {
				sb.append("ERROR: no matching closing curly brace !");
				break;
			} else {
				try {
					String xPathSource = template.substring(index, curlya);
					XPath xPath = factory.newXPath();
					XPathExpression xpression = xPath.compile(xPathSource);
					String value = xpression.evaluate(inputXml);
					sb.append(value);
				} catch(XPathExpressionException x) {
					sb.append("{PARSE ERROR: ").append(x.getMessage()).append('}');
				} catch(Exception e) {
					sb.append("{EXCEPTION: ").append(e).append('}');
				}
				index = curlya + 1;
			}
		}

		return sb.toString();
	}
}
