package org.example.parsers;

import org.example.classes.Gem;
import org.example.handlers.PavilionHandler;
import org.example.utils.PathBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class CustomSAXParser implements Parser {
    @Override
    public List<Gem> parse(String pathToXML) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            PavilionHandler handler = new PavilionHandler();
            saxParser.parse(pathToXML, handler);

            return handler.getPavilion();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Parser parser = new CustomSAXParser();
        System.out.println(parser.parse(PathBuilder.getPath(".", "src", "main", "resources", "input.xml")));
    }
}
