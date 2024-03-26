package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Location location = new Location();
        Faker faker = new Faker();
        Random random = new Random();


        String firstName;
        String lastName;

        List<Person> listOfPersons = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            firstName = faker.name().fullName(); // Miss Samanta Schmidt
            lastName = faker.name().firstName(); // Emory
            int age = random.nextInt(54) + 16;
            boolean car = random.nextBoolean();
            boolean drive = random.nextBoolean();
            listOfPersons.add(new Person(firstName, lastName, age, car, drive, location));
        }

        List<Person> drivers = new LinkedList<>();
        drivers = listOfPersons.stream().filter(e -> e.getiDrive()).sorted(Comparator.comparingInt(Person::getAge)).toList();

        System.out.println("Acestia sunt soferi: ");
        for (Person person : drivers) {
            System.out.println(person.getFirstName() + " si are varsta de: " + person.getAge());
            person.outputAllStops();
        }


        TreeSet<Person> passangers = listOfPersons.stream()
                .filter(e -> !e.getiDrive())
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getFirstName))));

        System.out.println("\nAcestia sunt pasageri: ");
        for (Person person : passangers) {
            System.out.println(person.getFirstName() + " si are varsta de: " + person.getAge());
            person.outputAllStops();
        }

        Algorithm alg = new Algorithm(drivers ,passangers);

    }
}

