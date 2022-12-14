package org.example.parsers;

import org.example.classes.Gem;
import org.example.handlers.PavilionHandler;
import org.example.utils.PathBuilder;
import org.example.utils.XMLTags;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomDOMParser implements Parser {
    @Override
    public List<Gem> parse(String pathToXML) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(pathToXML));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(XMLTags.GEM);
            PavilionHandler handler = new PavilionHandler();

            for(int i = 0; i < nodeList.getLength(); i++) {
                Node currentNode = nodeList.item(i);

                if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) currentNode;
                    handler.addDataByDOMElement(element);
                }
            }

            return handler.getPavilion();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Parser parser = new CustomDOMParser();
        System.out.println(parser.parse(PathBuilder.getPath(".", "src", "main", "resources", "input.xml")));
    }
}
