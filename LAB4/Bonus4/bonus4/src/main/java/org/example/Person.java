package org.example;

import javax.print.DocFlavor;
import java.util.*;

public class Person {
    private final String firstName;
    private final String lastName;
    Location location;
    Map<Integer, MapValue> stops = new HashMap<>();
    List<Integer> currentDestination = new ArrayList<>();
    private final int age;

    private boolean hasACar;

    private boolean iDrive;

    int myNumber;

    String leaving;
    String destination;

    Person(String firstName, String lastName, int age, boolean hasACar, boolean iDrive, Location location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hasACar = hasACar;
        this.iDrive = iDrive;
        this.location = location;
        this.myNumber = myNumber;

        condition(age, hasACar, iDrive);
        myRoadLocations();
    }

    private void condition(int age, boolean hasACar, boolean iDrive) {
        if (age < 18) {
            this.hasACar = false;
            this.iDrive = false;
        } else if (!hasACar) {
            this.iDrive = false;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean getHasACar() {
        return hasACar;
    }

    public boolean getiDrive() {
        return iDrive;
    }

    public int getMyNumber(){
        return myNumber;
    }

    private void myRoadLocations() {
        Random random = new Random();

        int numberOfLocations;

        if (getiDrive()) {
            numberOfLocations = random.nextInt(location.getNumberOfLocations() - 2) + 2;
        } else {
            numberOfLocations = 2;
        }

        // as putea sa fac un vector de visitable sa nu merg prin aceeasi locatie de 2 ori
        // momentan presupun ca soferul a uitat chesti pe drum si se intoarce, haotic
        int[] visited = new int[location.getNumberOfLocations() + 1];
        int nr = 0;
        for (int i = 0; i < numberOfLocations; i++) {
            int id = random.nextInt(location.getNumberOfLocations());
            if (getiDrive()) {
                while (visited[id] == 1) {
                    id = random.nextInt(location.getNumberOfLocations());
                }

                stops.put(id,new MapValue(location.getLocationName(id),nr));
                destination = location.getLocationName(id);
                visited[id] = 1;
                nr++;
            } else {
                while (visited[id] == 1) {
                    id = random.nextInt(location.getNumberOfLocations());
                }
                currentDestination.add(id);
                destination = location.getLocationName(id);
                visited[id] = 1;

            }
        }
    }

    public void outputAllStops() {

        System.out.println("merg pe aici ca asa vreau: ");
        if (getiDrive()) {
            for (Map.Entry<Integer, MapValue> entry : stops.entrySet()) {
                Integer key = entry.getKey();
                MapValue values = entry.getValue();

                System.out.print(values.getName() + " id=" + values.getId() + " ");
            }
        } else {
            System.out.print(location.getLocationName(currentDestination.getFirst()) + " ");
            System.out.println(location.getLocationName(currentDestination.getLast()));

        }


        System.out.print('\n');
    }

    public Map<Integer, MapValue> getStops() {
        return stops;
    }

    public List<Integer> getListPassanger() {
        return currentDestination;
    }

    public int getId(Integer id) {
        MapValue values = stops.get(id);

        return values.getId();
    }

    public String getDestination(){
        return this.destination;
    }

    public void setMyNumber(int myNumber){
        this.myNumber = myNumber;
    }
}

