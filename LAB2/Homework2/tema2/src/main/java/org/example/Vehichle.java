package org.example;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

abstract class Vehicle {
    String nameVehicle;
    Client[] clients;

    int currentClient = 0;

    public Vehicle(String nameVehicle, int nrOfClients) {
        this.nameVehicle = nameVehicle;
        clients = new Client[nrOfClients];
    }

    public String getNameVehicle() {
        return nameVehicle;
    }

    public int getCurrentClient() {
        return currentClient;
    }

    /**
     *
     * @param client adaugam client
     *
     */
    public void addClient(Client client) {
        if (client.verifyUniqueness(clients, client)) {
            clients[currentClient] = client;
            clients[currentClient].setId(currentClient + 1);
            currentClient++;
        } else {
            System.out.println("Sorry bro the client already exists");
        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "nameVehicle='" + nameVehicle + '\'' +
                ", clients=" + Arrays.toString(clients) +
                ", currentClient=" + currentClient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return currentClient == vehicle.currentClient && Objects.equals(nameVehicle, vehicle.nameVehicle) && Arrays.equals(clients, vehicle.clients);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nameVehicle, currentClient);
        result = 31 * result + Arrays.hashCode(clients);
        return result;
    }

    private void populate(LocalTime[][] timeMatrix, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int randomMinute = ThreadLocalRandom.current().nextInt(0, 60);
                int randomHour = ThreadLocalRandom.current().nextInt(0, 4);
                timeMatrix[i][j] = LocalTime.of(randomHour, randomMinute);
                timeMatrix[j][i] = LocalTime.of(randomHour, randomMinute);
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(timeMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void start(LocalTime startingTime) {
        int currentLocation = 0;
        int visited[] = new int[currentClient];
        LocalTime[][] timeMatrix = new LocalTime[currentClient + 1][currentClient + 1];
        populate(timeMatrix, currentClient + 1);
        while (startingTime.compareTo(LocalTime.of(20, 0)) < 0) {

            int whichOne = -1;
            LocalTime checkTime = LocalTime.of(21, 0);
            int j = 0;
            for (Client i : clients) {
                if (i == null) {
                    break;
                } else {
                    if (i.getMaxTime().compareTo(checkTime) < 0 && startingTime.compareTo(i.getMaxTime()) < 0 && visited[j] == 0) {
                        checkTime = i.getMaxTime();
                        whichOne = j;
                    }
                }
                j++;
            }
            if(timeMatrix[currentLocation][whichOne+1] == null){
                break;
            }
            if (whichOne != -1) {
                LocalTime timeToTest = startingTime;
                timeToTest = timeToTest.plusHours(timeMatrix[currentLocation][whichOne + 1].getHour());
                timeToTest = timeToTest.plusMinutes(timeMatrix[currentLocation][whichOne + 1].getMinute());

                System.out.println(" time to test: " + timeToTest);

                if (timeToTest.compareTo(clients[whichOne].getMaxTime()) > 0) {
                    visited[whichOne] = 2;
                } else if (timeToTest.compareTo(clients[whichOne].getMinTime()) < 0) {
                    startingTime = clients[whichOne].getMinTime();
                    startingTime = startingTime.plusMinutes(30);
                    visited[whichOne] = 1;
                    currentLocation = whichOne;
                } else {
                    startingTime = timeToTest;
                    visited[whichOne] = 1;
                    currentLocation = whichOne;
                }
            } else {
                break;
            }

        }

        for(int i = 0; i < currentClient; i++){
            if (visited[i] == 1) {
                System.out.println("Clientul vizitat este: " + clients[i].getNameClient());
            }
        }

    }
    /**
     *
     * It checks the unicity of a vehicle to be sure that a vehicle is not attribuited to the same depot twice
     * It return true if the vehicle is not already assigned
     * It returns false if the vehicle is already assigned
     * @param vehicles lista de vehicule a depoului
     * @param vehicle vehiculul ce se doreste a fi adaugat
     * @return
     */
    public boolean verifyUniqueness(Vehicle[] vehicles, Vehicle vehicle) {
        for (Vehicle i : vehicles) {
            if (i != null && i.equals(vehicle)) {
                return false;
            }
        }
        return true;
    }
}

