package org.example.parsers;

import org.example.classes.Gem;

import java.util.List;

public interface Parser {
    List<Gem> parse(String pathToXML);
}
