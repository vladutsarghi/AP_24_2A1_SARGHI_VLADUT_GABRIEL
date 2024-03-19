package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Visitable {
    Object[][] openDays = new Object[7][3];


    boolean isOpen(LocalDate today);

    LocalTime getOpenTime();
    LocalTime getCloseTime();

    String getName();

    default LocalTime getOpeningHourForADate(LocalDate localDate){

        return LocalTime.of(0 ,0 );
    }
}
