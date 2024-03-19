package org.example;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // Create an ArrayList to store integers
        Trip trip = new Trip("Bucuresti", LocalDate.of(2024, 6, 13), LocalDate.of(2024, 6, 16));
        trip.populate();

    }
}



