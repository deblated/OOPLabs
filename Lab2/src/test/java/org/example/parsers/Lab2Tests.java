package org.example.parsers;

import org.example.classes.*;
import org.example.utils.PathBuilder;
import org.example.utils.XSDValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lab2Tests {
    private static final String PATH_TO_XML = PathBuilder.getPath(".", "src", "main", "resources", "input.xml");
    private static final String PATH_TO_XSD = PathBuilder.getPath(".", "src", "main", "resources", "schema.xsd");
    private static final String PATH_TO_INVALID_XSD = PathBuilder.getPath(".", "src", "main", "resources", "schema_invalid.xsd");
    private static final List<Gem> EXPECTED_SORTED_PAVILION;
    private static final Comparator<Gem> COMPARATOR = Comparator.comparingDouble(Gem::getValue);

    static {
        EXPECTED_SORTED_PAVILION = new ArrayList<>();

        EXPECTED_SORTED_PAVILION.add(new Gem(
                0,
                "Diamond",
                Preciousness.PRECIOUS,
                "China",
                new VisualParameters("White", 14, 14),
                14
        ));

        EXPECTED_SORTED_PAVILION.add(new Gem(
                1,
                "Ruby",
                Preciousness.SEMIPRECIOUS,
                "Spain",
                new VisualParameters("Red", 46, 4),
                15.92
        ));

        EXPECTED_SORTED_PAVILION.add(new Gem(
                4,
                "Garnet",
                Preciousness.PRECIOUS,
                "Morocco",
                new VisualParameters("Garnet", 14, 14),
                35.3
        ));

        EXPECTED_SORTED_PAVILION.add(new Gem(
                3,
                "Sapphire",
                Preciousness.SEMIPRECIOUS,
                "Ukraine",
                new VisualParameters("Blue", 93, 8),
                65.14
        ));

        EXPECTED_SORTED_PAVILION.add(new Gem(
                2,
                "Amethyst",
                Preciousness.PRECIOUS,
                "Japan",
                new VisualParameters("Purple", 24, 15),
                92.15
        ));
    }

    private static void testParsing(Parser parser) {
        List<Gem> orangery = parser.parse(PATH_TO_XML);
        orangery.sort(COMPARATOR);
        assertEquals(EXPECTED_SORTED_PAVILION, orangery);
    }

    @Test
    void testSAXParser() {
        testParsing(new CustomSAXParser());
    }

    @Test
    void testDOMParser() {
        testParsing(new CustomDOMParser());
    }

    @Test
    void testStAXParser() {
        testParsing(new CustomStAXParser());
    }

    @Test
    void testValidation() {
        XSDValidator validator = new XSDValidator();
        assertTrue(validator.validate(PATH_TO_XML, PATH_TO_XSD));
        assertFalse(validator.validate(PATH_TO_XML, PATH_TO_INVALID_XSD));
    }
}