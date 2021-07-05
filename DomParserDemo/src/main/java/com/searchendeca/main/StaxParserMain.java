package com.searchendeca.main;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StaxParserMain {

	private void createStudentElements(XMLStreamWriter xmlStreamWriter) {
		try {
			createStreamWriter("FirstName", "Syed", xmlStreamWriter);
			createStreamWriter("LastName", "Ghouse", xmlStreamWriter);
			createStreamWriter("City", "Salem", xmlStreamWriter);
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createStreamWriter(String key, Object value, XMLStreamWriter xmlStreamWriter)
			throws XMLStreamException {
		if (value != null && value != "") {
			if (value instanceof String) {
				xmlStreamWriter.writeStartElement(key);
				xmlStreamWriter.writeCharacters((String) value);
				xmlStreamWriter.writeEndElement();
			}
		} else {
			xmlStreamWriter.writeEmptyElement(key);
		}

	}

	public static void main(String args[]) {
		StringWriter stringwriter = new StringWriter();
		XMLOutputFactory xmloutputfactory = XMLOutputFactory.newInstance();
		StaxParserMain stax = new StaxParserMain();
		try {
			XMLStreamWriter xmlStreamWriter = xmloutputfactory.createXMLStreamWriter(stringwriter);
			xmlStreamWriter.writeStartDocument();
			xmlStreamWriter.writeStartElement("Student");
			stax.createStudentElements(xmlStreamWriter);
			xmlStreamWriter.writeEndElement();
			xmlStreamWriter.writeEndDocument();
			xmlStreamWriter.flush();
			xmlStreamWriter.close();
			String xmlString= stringwriter.getBuffer().toString();
			System.out.println(xmlString);

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
