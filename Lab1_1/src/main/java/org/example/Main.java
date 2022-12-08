package org.example;

import org.example.vacation.*;
import org.example.transport.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Vacation MyVacation = new Vacation("ordinary description of ordinary cruise for ordinary person", BigDecimal.valueOf(1328), new Plane("flying metal bird"), LocalDate.now().plusDays(28), 13, false);
        System.out.println(MyVacation);
    }
}
