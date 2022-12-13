package org.example.manager;

import org.example.vacation.AbstractVacation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VacationManager {
    private final List<AbstractVacation> vacationStorage;

    public VacationManager(List<AbstractVacation> vacationStorage) {
        this.vacationStorage = vacationStorage;
    }

    public VacationManager() {
        vacationStorage = new ArrayList<>();
    }

    public List<AbstractVacation> getVacationStorage() {
        return vacationStorage;
    }

    public void addVacation(AbstractVacation vacation) {
        vacationStorage.add(vacation);
    }

    public void print() {
        for(int i = 0; i < vacationStorage.size(); i++) {
            System.out.printf("Variant : %d%n%s", i, vacationStorage.get(i));
            System.out.println("------------------------------------");
        }
    }

    public void sort(SortBy filter) {
        vacationStorage.sort(getComparator(filter));
    }

    public AbstractVacation buyVacation(int index) {
        if(index < 0 || index > vacationStorage.size() - 1) {
            throw new IllegalArgumentException("Wrong variant provided");
        }

        return vacationStorage.get(index);
    }

    private Comparator getComparator(SortBy filter) {
        switch (filter) {
            case PRICE -> {
                return Comparator.comparing(AbstractVacation::getPrice);
            }

            case DATE -> {
                return Comparator.comparing(AbstractVacation::getStartDate);
            }

            case DURATION -> {
                return Comparator.comparing(AbstractVacation::getDuration);
            }

            case NUTRITION -> {
                return Comparator.comparing(AbstractVacation::isNutritionIncluded);
            }
        }

        return null;
    }
}
