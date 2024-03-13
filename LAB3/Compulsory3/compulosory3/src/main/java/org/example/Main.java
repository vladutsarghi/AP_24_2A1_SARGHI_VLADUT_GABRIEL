package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList to store integers
       Trip trip = new Trip("Bucuresti", LocalDate.of(2024, 7, 12), LocalDate.of(2024, 7, 13));
       trip.populate();
    }
}
