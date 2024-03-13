package org.example;

import java.time.LocalTime;

public class Church
        implements Visitable {

    String churchName;
    int rating = 0;
    int nrOfRatings = 0;

    Church(String churchName){
        this.churchName = churchName;
    }
    @Override
    public void setOpenDays() {
        for (int i = 0; i < 7; i++) {
            if(i == 6){
                openDays[i][0] = "Sunday";
                openDays[i][1] = LocalTime.of(8, 0);
                openDays[i][2] = LocalTime.of(12, 0);
            }
        }
    }

    public boolean isOpen(String day, LocalTime time){
        LocalTime becomeOpen = (LocalTime) openDays[6][1];
        LocalTime becomeUnOpen = (LocalTime) openDays[6][2];
        return day.equals(openDays[6][0]) && time.isAfter(becomeOpen) && time.isBefore(becomeUnOpen);
    }

    public void giveRating(int rating){
        this.rating += rating;
        nrOfRatings++;
    }

    public float getRating(){
        return (float) rating /nrOfRatings;
    }

}
