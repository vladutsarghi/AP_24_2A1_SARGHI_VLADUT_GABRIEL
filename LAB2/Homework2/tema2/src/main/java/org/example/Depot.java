package org.example;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Depot {
    String nameDepot;
    private Vehicle[] vehicles;
    int numberOfVehicles = 0;

    public String getNameDepot() {
        return nameDepot;
    }

    public Depot(String nameDepot, int maxNrVehicles) {
        this.nameDepot = nameDepot;
        vehicles = new Vehicle[maxNrVehicles];
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        if(vehicle.verifyUniqueness(vehicles,vehicle)){
            vehicles[numberOfVehicles] = vehicle;
            numberOfVehicles++;
        }else{
            System.out.println("Sorry bro car already exists ;(");
        }

    }

    public void addClientToVehicle(String nameVehicle, Client client) {
        for (Vehicle i : vehicles) {
            if (i.nameVehicle.equals(nameVehicle)) {
                i.addClient(client);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Depot{" +
                "nameDepot='" + nameDepot + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                ", numberOfVehicles=" + numberOfVehicles +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return numberOfVehicles == depot.numberOfVehicles && Objects.equals(nameDepot, depot.nameDepot) && Arrays.equals(vehicles, depot.vehicles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nameDepot, numberOfVehicles);
        result = 31 * result + Arrays.hashCode(vehicles);
        return result;
    }

    public void start(LocalTime start, String nameVehicle) {
        for (Vehicle i : vehicles) {
            if (i.nameVehicle.equals(nameVehicle)) {
                i.start(start);
                break;
            }

        }

    }

    public boolean verifyUniqueness(Depot[] depots, Depot depot) {
        for (Depot i : depots) {
            if (i!=null && i.equals(depot)) {
                return false;
            }
        }
        return true;
    }
}