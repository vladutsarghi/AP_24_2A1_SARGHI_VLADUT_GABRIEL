package org.example;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Person> listOfPersons = new ArrayList<>();
        listOfPersons.add(new Person("Mara", 21, false, false));
        listOfPersons.add(new Person("Vladut", 31, true, true));
        listOfPersons.add(new Person("Andrei", 20, true, true));
        listOfPersons.add(new Person("Mario", 15, true, true));
        listOfPersons.add(new Person("Popi", 40, false, false));
        listOfPersons.add(new Person("Anton", 21, true, false));
        listOfPersons.add(new Person("Cret", 50, false, false));
        listOfPersons.add(new Person("Pizza", 60, true, false));
        listOfPersons.add(new Person("Saorma", 70, true, true));

        List<Person> drivers =  new LinkedList<>();
        drivers = listOfPersons.stream().filter(e -> e.getiDrive()).sorted(Comparator.comparingInt(Person::getAge)).toList();

        System.out.println("Acestia sunt soferi: ");
        for(Person person : drivers){
            System.out.println(person.getName() + " si are varsta de: " + person.getAge());
        }


        TreeSet<Person> passangers = listOfPersons.stream()
                .filter(e -> !e.getiDrive())
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))));

        System.out.println("\nAcestia sunt pasageri: ");
        for(Person person : passangers){
            System.out.println(person.getName() + " si are varsta de: " + person.getAge());
        }
    }
}

