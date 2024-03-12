package org.example;

public class Truck extends Vehicle{
    int capacity;

    public Truck(String nameVehicle, int nrOfClients, int capacity) {
        super(nameVehicle, nrOfClients);
        this.capacity = capacity;
    }
}