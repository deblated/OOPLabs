package org.example.handlers;

import org.example.classes.*;
import org.example.utils.XMLTags;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PavilionHandler extends DefaultHandler {
    private List<Gem> pavilion;
    private String elementValue;

    public PavilionHandler() {
        this.pavilion = new ArrayList<>();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        this.pavilion = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("gem")) {
            pavilion.add(new Gem());
        }

        if(attributes != null) {
            for(int i = 0; i < attributes.getLength(); i++) {
                setField(attributes.getQName(i), attributes.getValue(i));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        setField(qName, elementValue);
    }

    public void addDataByDOMElement(Element element) {
        createEmptyGem();

        NamedNodeMap attributes = element.getAttributes();

        for (int i = 0; i < attributes.getLength(); i++) {
            Attr currentAttribute = (Attr) attributes.item(i);
            setField(currentAttribute.getName(), currentAttribute.getValue());
        }

        setField("name", element.getElementsByTagName("name").item(0).getTextContent());
        setField("preciousness", element.getElementsByTagName("preciousness").item(0).getTextContent());
        setField("origin", element.getElementsByTagName("origin").item(0).getTextContent());

        setField("color", element.getElementsByTagName("color").item(0).getTextContent());
        setField("transparency", element.getElementsByTagName("transparency").item(0).getTextContent());
        setField("branches", element.getElementsByTagName("branches").item(0).getTextContent());

        setField("value", element.getElementsByTagName("value").item(0).getTextContent());
    }

    public void setField(String qName, String value) {
        switch (qName) {
            case XMLTags.ID ->
                    getLast().setId(Integer.parseInt(value));

            case XMLTags.NAME ->
                    getLast().setName(value);

            case XMLTags.PRECIOUSNESS ->
                    getLast().setPreciousness(Preciousness.valueOf(value));

            case XMLTags.ORIGIN ->
                    getLast().setOrigin(value);

            case XMLTags.COLOR ->
                    getLast().getVisualParameters().setColor(value);

            case XMLTags.TRANSPARENCY ->
                    getLast().getVisualParameters().setTransparency(Integer.parseInt(value));

            case XMLTags.BRANCHES ->
                    getLast().getVisualParameters().setBranches(Integer.parseInt(value));

            case XMLTags.VALUE ->
                    getLast().setValue(Double.parseDouble(value));
        }
    }

    public void createEmptyGem() {
        if(pavilion == null) {
            pavilion = new ArrayList<>();
        }

        pavilion.add(new Gem());
    }

    public List<Gem> getPavilion() {
        return new ArrayList<>(pavilion);
    }

    private Gem getLast() {
        return pavilion.get(pavilion.size() - 1);
    }
}
