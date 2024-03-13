package org.example;


import java.time.LocalTime;

public class Concert
implements  Visitable, Payable{
    double ticketPrice;
    String nameOfConcert;
    Concert(String nameOfConcert, double ticketPrice){
        this.nameOfConcert = nameOfConcert;
        this.ticketPrice = ticketPrice;
    }
    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }


    @Override
    public void setOpenDays() {
        openDays[2][0] = "Wednesday";
        openDays[2][1] = LocalTime.of(20, 0);
        openDays[2][2] = LocalTime.of(2, 0);

        openDays[5][0] = "Saturday";
        openDays[5][1] = LocalTime.of(20, 0);
        openDays[5][2] = LocalTime.of(2, 0);
    }

    public boolean isOpen(String day, LocalTime time){
        LocalTime becomeOpen = (LocalTime) openDays[6][1];
        LocalTime becomeUnOpen = (LocalTime) openDays[6][2];
        return day.equals(openDays[2][0]) && day.equals(openDays[5][0]) && time.isAfter(becomeOpen) && time.isBefore(becomeUnOpen);
    }

}
