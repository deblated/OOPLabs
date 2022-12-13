package org.example.parsers;

import org.example.classes.Gem;
import org.example.handlers.PavilionHandler;
import org.example.utils.PathBuilder;
import org.example.utils.XMLTags;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

public class CustomStAXParser implements Parser {
    @Override
    public List<Gem> parse(String pathToXML) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(pathToXML));
            PavilionHandler handler = new PavilionHandler();

            while(reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if(!event.isStartElement()) {
                    continue;
                }

                StartElement startElement = event.asStartElement();
                String qName = startElement.getName().getLocalPart();

                Iterator<Attribute> attributeIterator = startElement.getAttributes();
                event = reader.nextEvent();

                if(qName.equals(XMLTags.GEM)) {
                    handler.createEmptyGem();
                } else {
                    String value = event.asCharacters().getData();
                    handler.setField(qName, value);
                }

                while (attributeIterator.hasNext()) {
                    Attribute attribute = attributeIterator.next();
                    handler.setField(attribute.getName().getLocalPart(), attribute.getValue());
                }
            }

            return handler.getPavilion();
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Parser parser = new CustomStAXParser();
        System.out.println(parser.parse(PathBuilder.getPath(".", "src", "main", "resources", "input.xml")));
    }
}
