package org.example;

import java.time.LocalTime;
import java.util.Objects;

public class Client {
    private String nameClient;
    private LocalTime minTime;
    private LocalTime maxTime;
    private int id;

    enum typeOfClient {
        REGULAR,
        PREMIUM
    }

    public Client(String nameClient, LocalTime minTime, LocalTime maxTime) {
        this.nameClient = nameClient;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public LocalTime getMinTime() {
        return minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nameClient='" + nameClient + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(nameClient, client.nameClient) && Objects.equals(minTime, client.minTime) && Objects.equals(maxTime, client.maxTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameClient, minTime, maxTime);
    }

    public boolean verifyUniqueness(Client[] clients, Client client) {
        for (Client i : clients) {
            if (i!=null && i.equals(client)) {
                return false;
            }
        }
        return true;
    }
}

