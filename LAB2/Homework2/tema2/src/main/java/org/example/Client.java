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

    /**
     *
     * It checks the unicity of a client to be sure that a client is not attribuited to the same car twice
     * It return true if the client is can be a new client
     * It returns false if the client is already programmed
     * @param clients lista de clienti a vehicului
     * @param client clientul ce se doreste a fi adaugat
     * @return
     */
    public boolean verifyUniqueness(Client[] clients, Client client) {
        for (Client i : clients) {
            if (i!=null && i.equals(client)) {
                return false;
            }
        }
        return true;
    }
}

