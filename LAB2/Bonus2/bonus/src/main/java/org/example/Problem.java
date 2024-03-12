package org.example;

public class Problem {

    public Vehicle[] getVehicles(Depot[] depots) {
        int totalSize = 0;
        Vehicle[] allVehicles;
        for (Depot i : depots) {
            if (i != null) {
                totalSize += i.getNumberOfVehicles();
            }
        }
        allVehicles = new Vehicle[totalSize];
        totalSize = 0;
        for (Depot i : depots) {
            if (i != null) {
                System.arraycopy(i.getVehicles(), 0, allVehicles, totalSize, i.getNumberOfVehicles());
                totalSize += i.getNumberOfVehicles();
            }
        }

        return allVehicles;

    }
}