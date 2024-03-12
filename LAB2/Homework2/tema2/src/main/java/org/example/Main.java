package org.example;

import java.time.LocalTime;

import java.util.Arrays;

/**
 *
 */

public class Main {

    public static void main(String[] args) {
        Depot[] depots= new Depot[20];
        int nrDepots = 0;
        Depot depot = new Depot("Dani", 3);
        if(depot.verifyUniqueness(depots,depot)){
            depots[nrDepots++] = depot;
        }else{
            System.out.println("Sorry bro the depot already exists");
        }
        Vehicle tesla = new Truck("Tesla", 10, 100);
        Vehicle dacia = new Drone("Dacia", 13, LocalTime.of(0, 1, 0));
        depot.addVehicle(tesla);
        depot.addVehicle(dacia);
        Client costel = new Client("Costel", LocalTime.of(8,0), LocalTime.of(10, 0));
        Client marian = new Client("Marian", LocalTime.of(9, 0), LocalTime.of(12, 0));
        Client cleo = new Client("Cleo", LocalTime.of(9, 0), LocalTime.of(10, 0));
        Client sorin = new Client("Sorin", LocalTime.of(10, 0), LocalTime.of(10, 30));
        Client buji = new Client("Buji", LocalTime.of(10, 0), LocalTime.of(10, 30));
        Client jojo = new Client("Jojo", LocalTime.of(15, 0), LocalTime.of(20, 0));
        depot.addClientToVehicle("Tesla", costel);
        depot.addClientToVehicle("Tesla", marian);
        depot.addClientToVehicle("Tesla", cleo);
        depot.addClientToVehicle("Tesla", sorin);
        depot.addClientToVehicle("Tesla", buji);
        depot.addClientToVehicle("Tesla", jojo);
        System.out.println(depot);
        depot.start(LocalTime.of(7, 0), "Tesla");

        Problem problem = new Problem();

        Vehicle[] allVehicles = new Vehicle[100];

        allVehicles = problem.getVehicles(depots);

        for(Vehicle i : allVehicles){
            System.out.println(i.getNameVehicle());
        }
    }
}

