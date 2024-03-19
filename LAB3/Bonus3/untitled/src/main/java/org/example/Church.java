package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class Church
        implements Visitable {

    TimeIntervalAndDate<LocalDate, LocalTime, LocalTime> timeInterval;

    String churchName;
    int rating = 0;
    int nrOfRatings = 0;


    Church(String churchName){
        this.churchName = churchName;
        setTimeInterval();
    }

    private void setTimeInterval() {
        timeInterval = new TimeIntervalAndDate<>(LocalTime.of(8, 0), LocalTime.of(12, 0));
    }


    @Override
    public boolean isOpen(LocalDate today) {
        return today.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public void giveRating(int rating){
        this.rating += rating;
        nrOfRatings++;
    }

    public float getRating(){
        return (float) rating /nrOfRatings;
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
        return churchName;
    }

    @Override
    public LocalTime getOpeningHourForADate(LocalDate localDate) {
        if(localDate.getDayOfWeek() == DayOfWeek.SUNDAY){
            return timeInterval.getStart();
        }
        else{
            System.out.println("In acesta zi nu este deschis");
        }

        return LocalTime.of(0, 0);
    }
}
