package com.searchendeca.main;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DomParserMain {

	private Element createStudentElement(Document doc, Element rootElement) {
		rootElement.appendChild(createElements(doc, "FirstName", "Syed"));
		rootElement.appendChild(createElements(doc, "LastName", "Ghouse"));
		rootElement.appendChild(createElements(doc, "City", "Salem"));
		return rootElement;
	}

	private Node createElements(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		if (value != null && !value.isEmpty()) {
			node.appendChild((doc.createTextNode(value)));
		}
		return node;
	}

	public static void main(String args[]) throws ParserConfigurationException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		String sampleXml;
		DomParserMain domParser = new DomParserMain();
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element rootElement = doc.createElement("Student");
		doc.appendChild(rootElement);
		rootElement = domParser.createStudentElement(doc, rootElement);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = factory.newTransformer();
			StringWriter writer = new StringWriter();
			try {
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.transform(new DOMSource(doc), new StreamResult(writer));
				sampleXml = writer.getBuffer().toString();
				System.out.println(sampleXml);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
