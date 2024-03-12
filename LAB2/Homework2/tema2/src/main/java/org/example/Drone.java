package org.example;

import java.time.LocalTime;

public class Drone extends Vehicle{
    private LocalTime maxDuration;

    public Drone(String nameVehicle, int nrOfClients, LocalTime maxDuration) {
        super(nameVehicle, nrOfClients);
        this.maxDuration = maxDuration;
    }
}