package org.example;

import java.net.PasswordAuthentication;
import java.util.*;

public class Algorithm {
    List<Person> driver = new ArrayList<>();
    Set<Person> passanger = new TreeSet<>();

    Algorithm(List<Person> driver, Set<Person> passanger) {
        this.driver = driver;
        this.passanger = passanger;

        alg();
    }

    public void alg() {
        Map<String, Boolean> hasDriver = new HashMap<>();

        List<Pair> pairs= new ArrayList<>();
        for (Person driver : driver) {
            System.out.println("Numele meu este " + driver.getFirstName() + " si pot sa sofez pe: ");
            for (Person passanger : passanger) {
                int index1 = -1;
                int index2 = -1;

                if (driver.getStops().containsKey(passanger.getListPassanger().getFirst())) {
                    index1 = driver.getId(passanger.getListPassanger().getFirst());
                }

                if (driver.getStops().containsKey(passanger.getListPassanger().getLast())) {
                    index2 = driver.getId(passanger.getListPassanger().getLast());
                }

                if (index1 != -1 && index2 != -1) {
                    if (index1 < index2) {
                        pairs.add(new Pair(passanger.getFirstName(), index1, index2));
                    }
                }
            }
            int currentPosition = 0;
            while (true) {
                int highestUnavailable = 9999;
                int iLeave = 0;
                String name = null;

                for (Pair pair : pairs) {
                    if (pair.index2 < highestUnavailable && pair.index1 >= currentPosition && !hasDriver.containsKey(pair.name)) {
                        highestUnavailable = pair.index2;
                        iLeave = pair.index1;;
                        name = pair.name;
                    }
                }

                if(highestUnavailable == 9999){
                    break;
                } else {
                    hasDriver.put(name, true);
                    System.out.println(name + " plec de la " + iLeave + " si ajung la: " + highestUnavailable);
                    currentPosition = highestUnavailable;
                }
            }
        }

    }


}

class Pair{
    public String name;
    public int index1;
    public int index2;

    Pair(String name, int index1, int index2){
        this.name = name;
        this.index1 = index1;
        this.index2 = index2;
    }
}
