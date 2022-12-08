package org.example.manager;

import org.example.transport.Bus;
import org.example.transport.Plane;
import org.example.transport.Ship;
import org.example.vacation.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VacationManagerTest {
    private boolean isSorted(List<AbstractVacation> list, Comparator<AbstractVacation> comparator) {
        for(int i = 0; i < list.size() - 1; i++) {
            if(comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }

        return true;
    }
    private VacationManager initTestInput() {
        AbstractVacation[] dataToTest = {
                new Cruise("Cruise to Spain",
                        BigDecimal.valueOf(1428),
                        new Ship("Ivan Ivanov"),
                        LocalDate.now().plusDays(25),
                        14,
                        false),

                new Excursion("Excursion to Japan",
                        BigDecimal.valueOf(2813),
                        new Bus("Bohdan"),
                        LocalDate.now().plusDays(65),
                        15,
                        false),

                new Shopping("Dream town",
                        BigDecimal.valueOf(450),
                        new Bus("metro"),
                        LocalDate.now().plusDays(92),
                        92,
                        false),

                new SickLeave("Sanatorium Krym",
                        BigDecimal.valueOf(5000),
                        new Plane("flying bird"),
                        LocalDate.now().plusDays(15),
                        65,
                        false),

                new Vacation("Ordinary vacation to ukrainian Moscow",
                        BigDecimal.valueOf(5),
                        null,
                        LocalDate.now().plusDays(14),
                        35,
                        true),

                new Excursion("Excursion to burnt moscow",
                        BigDecimal.valueOf(36),
                        new Plane("An-225 Mriya"),
                        LocalDate.now().plusDays(3),
                        3,
                        true),

                new Shopping("Dream town 2",
                        BigDecimal.valueOf(390),
                        null,
                        LocalDate.now().plusDays(1),
                        5,
                        true),
        };

        return new VacationManager(Arrays.asList(dataToTest));
    }

    @Test
    void testLab() {
        VacationManager manager = initTestInput();

        AbstractVacation expectedCruise = manager.buyVacation(0);

        assertTrue(expectedCruise instanceof Cruise);
        assertEquals("Ivan Ivanov", expectedCruise.getTransport());

        try {
            manager.buyVacation(7);
            fail();
        } catch (IllegalArgumentException e) {}

        System.out.println("====================NOT SORTED====================\n\n");
        manager.print();

        List<AbstractVacation> vacationsList = manager.getVacationStorage();

        System.out.println("====================SORTED BY DATE====================\n\n");
        manager.sort(SortBy.DATE);
        assertTrue(isSorted(vacationsList, Comparator.comparing(AbstractVacation::getStartDate)));
        manager.print();

        System.out.println("====================SORTED BY DURATION====================\n\n");
        manager.sort(SortBy.DURATION);
        assertTrue(isSorted(vacationsList, Comparator.comparing(AbstractVacation::getDuration)));
        manager.print();

        System.out.println("====================SORTED BY PRICE====================\n\n");
        manager.sort(SortBy.PRICE);
        assertTrue(isSorted(vacationsList, Comparator.comparing(AbstractVacation::getPrice)));
        manager.print();

        System.out.println("====================SORTED BY NUTRITION====================\n\n");
        manager.sort(SortBy.NUTRITION);
        assertTrue(isSorted(vacationsList, Comparator.comparing(AbstractVacation::isNutritionIncluded)));
        manager.print();

    }
}