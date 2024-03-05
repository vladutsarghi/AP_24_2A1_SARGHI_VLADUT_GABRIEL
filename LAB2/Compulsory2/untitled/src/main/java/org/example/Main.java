import java.time.LocalTime;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Depot {
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

    public void addVehicle(String nameVehicle, int nrOfClients) {
        vehicles[numberOfVehicles] = new Vehicle(nameVehicle, nrOfClients);
        numberOfVehicles++;
    }

    public void addClientToVehicle(String nameVehicle, String nameClient, LocalTime minTime, LocalTime maxTime) {
        for (Vehicle i : vehicles) {
            if (i.nameVehicle.equals(nameVehicle)) {
                i.addClient(nameClient, minTime, maxTime);
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
                '}';
    }

    public void start(LocalTime start, String nameVehicle) {
        for (Vehicle i : vehicles) {
            if (i.nameVehicle.equals(nameVehicle)) {
                i.start(start);
                break;
            }
        }

    }
}

class Vehicle {
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

    public void addClient(String nameClient, LocalTime minTime, LocalTime maxTime) {
        clients[currentClient] = new Client(nameClient, minTime, maxTime);
        currentClient++;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "nameVehicle='" + nameVehicle + '\'' +
                ", clients=" + Arrays.toString(clients) +
                ", currentClient=" + currentClient +
                '}';
    }

    public void start(LocalTime startingTime) {
        int visited[] = new int[currentClient];
        while (startingTime.compareTo(LocalTime.of(20, 0)) < 0) {
            startingTime = startingTime.plusMinutes(30);
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
            if (whichOne != -1) {
                if (startingTime.compareTo(clients[whichOne].getMinTime()) < 0) {
                    startingTime = clients[whichOne].getMinTime();
                    startingTime = startingTime.plusMinutes(30);
                }
                else{
                    startingTime = startingTime.plusMinutes(30);
                }
                visited[whichOne] = 1;
                System.out.println("Clientul vizitat este: " + clients[whichOne].getNameClient() + " si este ora: " + startingTime);
            } else {
                break;
            }

        }
    }
}

class Client {
    private String nameClient;
    private LocalTime minTime;
    private LocalTime maxTime;

    enum typeOfClient {
        regular,
        premium
    }

    public Client(String nameClient, LocalTime minTime, LocalTime maxTime) {
        this.nameClient = nameClient;
        this.minTime = minTime;
        this.maxTime = maxTime;
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
}


public class Main {

    public static void main(String[] args) {
        Depot depot = new Depot("Depou1", 3);
        depot.addVehicle("Dacia 1300", 10);
        depot.addVehicle("Tesla ModelS", 30);
        depot.addClientToVehicle("Tesla ModelS", "Costel", LocalTime.of(8, 0), LocalTime.of(10, 0));
        depot.addClientToVehicle("Tesla ModelS", "Marian", LocalTime.of(9, 0), LocalTime.of(12, 0));
        depot.addClientToVehicle("Tesla ModelS", "Cleo", LocalTime.of(9, 0), LocalTime.of(10, 0));
        depot.addClientToVehicle("Tesla ModelS", "Sorin", LocalTime.of(10, 0), LocalTime.of(15, 0));
        depot.addClientToVehicle("Tesla ModelS", "Buji", LocalTime.of(11, 0), LocalTime.of(15, 30));
        depot.addClientToVehicle("Tesla ModelS", "Jojo", LocalTime.of(15, 0), LocalTime.of(20, 0));
        System.out.println(depot);
        depot.start(LocalTime.of(7, 0), "Tesla ModelS");
    }
}