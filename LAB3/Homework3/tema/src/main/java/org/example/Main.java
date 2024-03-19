package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList to store integers
        Trip trip = new Trip("Bucuresti", LocalDate.of(2024, 6, 13), LocalDate.of(2024, 6, 16));
        trip.populate();

        trip.getVisitable();

        TravelPlan travelPlan= new TravelPlan(trip.getMap(), trip.getCome(), trip.getLeave());

        travelPlan.outputTravelPlan();
    }
}

;

