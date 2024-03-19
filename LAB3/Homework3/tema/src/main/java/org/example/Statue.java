package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Statue
        implements Visitable{

    TimeIntervalAndDate<LocalDate, LocalTime, LocalTime> timeInterval;
    String nameOfStatue;
    Statue(String nameOfStatue){
        this.nameOfStatue = nameOfStatue;
        timeInterval = new TimeIntervalAndDate<>(LocalTime.of(8, 0), LocalTime.of(20, 0));
    }

    Statue(String nameOfStatue, LocalTime start, LocalTime end){
        this.nameOfStatue = nameOfStatue;
        timeInterval = new TimeIntervalAndDate<>(start, end);
    }


    @Override
    public boolean isOpen(LocalDate today) {
        return !(today.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    @Override
    public LocalTime getOpenTime() {
        return timeInterval.getStart();
    }

    @Override
    public LocalTime getCloseTime() {
        return timeInterval.getFinish();
    }

    @Override
    public String getName() {
        return nameOfStatue;
    }
}
