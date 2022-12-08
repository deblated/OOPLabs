package org.example.vacation;

import org.example.transport.Transport;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class AbstractVacation {
    protected final String description;
    protected final BigDecimal price;
    protected final Transport transport;
    protected final LocalDate startDate;
    protected final int duration;
    protected final boolean isNutritionIncluded;

    public AbstractVacation(String description,
                            BigDecimal price,
                            Transport transport,
                            LocalDate startDate,
                            int duration,
                            boolean isNutritionIncluded) {
        this.description = description;
        this.price = price;
        this.transport = transport;
        this.startDate = startDate;
        this.duration = duration;
        this.isNutritionIncluded = isNutritionIncluded;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTransport() {
        return transport.getModel();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isNutritionIncluded() {
        return isNutritionIncluded;
    }

    @Override
    public String toString() {
        return String.format("Description : %s%n" +
                        "Price : %s%n" +
                        "Transport : %s%n" +
                        "Start date : %s%n" +
                        "Duration(days) : %d%n" +
                        "Nutrition included : %s%n",
                description,
                price,
                transport,
                startDate,
                duration,
                isNutritionIncluded
        );
    }
}
