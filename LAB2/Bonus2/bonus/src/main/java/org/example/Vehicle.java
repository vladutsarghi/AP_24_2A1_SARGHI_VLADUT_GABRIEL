package org.example;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


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
     * @param client adaugam client
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

    private void populate(int length) {
        Random random = new Random();
        int numNodes = length;
        int minimumGridLength = 1;
        while (minimumGridLength * minimumGridLength < length) {
            minimumGridLength += 1;
        }

        int[][] gridMatrix = new int[minimumGridLength][minimumGridLength];
        int[][] costMatrix = new int[length + 1][length + 1];
        int[] visited = new int[length + 1];

        for (int i = 0; i < minimumGridLength; i++) {
            for (int j = 0; j < minimumGridLength; j++) {
                int randomNumber = random.nextInt(numNodes);
                if (length > 0) {
                    while (visited[randomNumber] == 1) {
                        randomNumber = random.nextInt(numNodes);
                    }
                }
                visited[randomNumber] = 1;
                gridMatrix[i][j] = randomNumber;
                length--;
                if (length < 0) {
                    gridMatrix[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < minimumGridLength; i++) {
            for (int j = 0; j < minimumGridLength; j++) {
                System.out.print(gridMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }

        for (int i = 0; i < minimumGridLength; i++) {
            for (int j = 0; j < minimumGridLength; j++) {
                if (gridMatrix[i][j] != -1) {
                    if (i - 1 >= 0) {
                        int randomNumber = random.nextInt(20) + 1;
                        if (gridMatrix[i - 1][j] != -1 && costMatrix[gridMatrix[i][j]][gridMatrix[i - 1][j]] == 0) {
                            costMatrix[gridMatrix[i][j]][gridMatrix[i - 1][j]] = randomNumber;
                            costMatrix[gridMatrix[i - 1][j]][gridMatrix[i][j]] = randomNumber;
                        }
                    }
                    if (i + 1 < minimumGridLength) {
                        int randomNumber = random.nextInt(20) + 1;
                        if (gridMatrix[i + 1][j] != -1 && costMatrix[gridMatrix[i][j]][gridMatrix[i + 1][j]] == 0) {
                            costMatrix[gridMatrix[i][j]][gridMatrix[i + 1][j]] = randomNumber;
                            costMatrix[gridMatrix[i + 1][j]][gridMatrix[i][j]] = randomNumber;
                        }
                    }
                    if (j - 1 >= 0) {
                        int randomNumber = random.nextInt(20) + 1;
                        if (gridMatrix[i][j - 1] != -1 && costMatrix[gridMatrix[i][j]][gridMatrix[i][j - 1]] == 0) {
                            costMatrix[gridMatrix[i][j]][gridMatrix[i][j - 1]] = randomNumber;
                            costMatrix[gridMatrix[i][j - 1]][gridMatrix[i][j]] = randomNumber;
                        }
                    }
                    if (j + 1 < minimumGridLength) {
                        int randomNumber = random.nextInt(20) + 1;
                        if (gridMatrix[i][j + 1] != -1 && costMatrix[gridMatrix[i][j]][gridMatrix[i][j + 1]] == 0) {
                            costMatrix[gridMatrix[i][j]][gridMatrix[i][j + 1]] = randomNumber;
                            costMatrix[gridMatrix[i][j + 1]][gridMatrix[i][j]] = randomNumber;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix.length; j++) {
                System.out.print(costMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }


        int[][] distances = new int[numNodes][numNodes];

        // Initializare
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                distances[i][j] = costMatrix[i][j];
                if (distances[i][j] == 0) distances[i][j] = 999999;
            }
        }


        System.out.println("Distanta minima intre toate: \n\n");
        // Calcul drumuri minime
        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }

        // Afisare rezultat
        for (int i = 0; i < numNodes; ++i) {
            for (int j = 0; j < numNodes; ++j) {
                System.out.print(distances[i][j] + " ");
            }
            System.out.print("\n");
        }
    }


    public void start(LocalTime startingTime) {
        int matrixLength = 1 + currentClient;
        populate(matrixLength);

    }

    public boolean verifyUniqueness(Vehicle[] vehicles, Vehicle vehicle) {
        for (Vehicle i : vehicles) {
            if (i != null && i.equals(vehicle)) {
                return false;
            }
        }
        return true;
    }
}

