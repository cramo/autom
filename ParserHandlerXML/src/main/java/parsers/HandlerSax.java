package parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerSax extends DefaultHandler {

	public void startDocument() throws SAXException {
		System.out.println("start document");
	}

	public void endDocument() throws SAXException {
		System.out.println("end document");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("start element -> localName = "  + localName + " qName = " + qName);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("end elements -> localName = " + localName + " qName = " + qName);
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		StringBuilder sb = new StringBuilder();
		sb.append(ch);
		start = start - 80;
		String str = sb.substring(0, length);
		System.out.println("characters -> taille = " + length + " start = " + start + " ch = " + str);
	}
}
