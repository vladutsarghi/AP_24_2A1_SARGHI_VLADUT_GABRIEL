package org.example;


import java.time.LocalDate;
import java.time.LocalTime;

public class Concert
        implements  Visitable, Payable{
    double ticketPrice;
    String nameOfConcert;

    TimeIntervalAndDate<LocalDate, LocalTime, LocalTime> timeIntervalAndDate;
    Concert(String nameOfConcert, double ticketPrice, LocalDate date, LocalTime start, LocalTime finish){
        this.nameOfConcert = nameOfConcert;
        this.ticketPrice = ticketPrice;

        timeIntervalAndDate = new TimeIntervalAndDate<>(date, start, finish);
    }
    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }



    @Override
    public boolean isOpen(LocalDate today) {
        return today.isEqual(timeIntervalAndDate.getDate());
    }

    @Override
    public LocalTime getOpenTime() {
        return timeIntervalAndDate.getStart();
    }

    @Override
    public LocalTime getCloseTime() {
        return timeIntervalAndDate.getFinish();
    }

    @Override
    public String getName() {
        return nameOfConcert;
    }
}

