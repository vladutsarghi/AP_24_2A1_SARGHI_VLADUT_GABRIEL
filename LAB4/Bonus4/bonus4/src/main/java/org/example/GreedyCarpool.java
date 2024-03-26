package org.example;

import java.util.*;

public class GreedyCarpool {

    List<Person> drivers = new ArrayList<>();
    List<Person> passengers = new ArrayList<>();

    public GreedyCarpool(List<Person> drivers, List<Person> passengers) {
        this.drivers = drivers;
        this.passengers = passengers;

        carpool();
    }

    public void carpool() {
        Map<Person, Integer> passengerDriverCounts = new HashMap<>();
        boolean[] taken = new boolean[10001];

        for (Person passenger : passengers) {
            int count = 0;
            for (Person driver : drivers) {
                int index1 = -1;
                int index2 = -1;

                if (driver.getStops().containsKey(passenger.getListPassanger().get(0))) {
                    index1 = driver.getId(passenger.getListPassanger().get(0));
                }

                if (driver.getStops().containsKey(passenger.getListPassanger().get(1))) {
                    index2 = driver.getId(passenger.getListPassanger().get(1));
                }

                if ((index1 != -1 && index2 != -1) && index1 < index2) {
                    count++;
                }
            }
            passengerDriverCounts.put(passenger, count);
        }


        List<Map.Entry<Person, Integer>> sortedPassengers = new ArrayList<>(passengerDriverCounts.entrySet());
        sortedPassengers.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Person, Integer> entry : sortedPassengers) {
            Person passenger = entry.getKey();
            int driverCount = entry.getValue();

            if (driverCount > 0) {
                //System.out.println("Passenger: " + passenger.getFirstName() + ", Potential Drivers: " + driverCount);

                for (Person driver : drivers) {
                    int index1 = -1;
                    int index2 = -1;

                    if (driver.getStops().containsKey(passenger.getListPassanger().get(0))) {
                        index1 = driver.getId(passenger.getListPassanger().get(0));
                    }

                    if (driver.getStops().containsKey(passenger.getListPassanger().get(1))) {
                        index2 = driver.getId(passenger.getListPassanger().get(1));
                    }


                    if ((index1 != -1 && index2 != -1) && index1 < index2 && !taken[driver.getMyNumber()]) {

                        //System.out.println("Assigned Driver: " + driver.getFirstName() + " to Passenger: " + passenger.getFirstName());
                        taken[driver.getMyNumber()] = true;
                        break;
                    }
                }
            }
        }
    }
}