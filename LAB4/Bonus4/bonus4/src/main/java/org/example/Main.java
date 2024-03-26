package org.example;

import com.github.javafaker.Faker;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.util.*;
import java.util.stream.Collectors;

import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Graph g = GraphBuilder.empty().buildGraph();
        Location location = new Location();
        Faker faker = new Faker();
        Random random = new Random();

        String firstName;
        String lastName;

        List<Person> listOfPersons = new ArrayList<>();

        int forPassagers = 0;
        int forDrivers = 0;

        for(int i = 0 ; i < 5000; i++){
            firstName = faker.name().fullName(); // Miss Samanta Schmidt
            lastName = faker.name().firstName(); // Emory
            int age = random.nextInt(54) + 18;
            boolean car = random.nextBoolean();
            boolean drive = random.nextBoolean();
            listOfPersons.add(new Person(firstName, lastName, age, true, true, location));
            g.addVertex(i);
        }

        for(int i = 0 ; i < 5000; i++){
            firstName = faker.name().fullName(); // Miss Samanta Schmidt
            lastName = faker.name().firstName(); // Emory
            int age = random.nextInt(54) + 18;
            boolean car = random.nextBoolean();
            boolean drive = random.nextBoolean();
            listOfPersons.add(new Person(firstName, lastName, age, false, false, location));
            g.addVertex(i + 5001);
        }

//        for (int i = 0; i < 20; i++) {
//            firstName = faker.name().fullName(); // Miss Samanta Schmidt
//            lastName = faker.name().firstName(); // Emory
//            int age = random.nextInt(54) + 16;
//            boolean car = random.nextBoolean();
//            boolean drive = random.nextBoolean();
//            listOfPersons.add(new Person(firstName, lastName, age, car, drive, location));
//            g.addVertex(i);
//
//        }

        List<Person> drivers = new LinkedList<>();
        drivers = listOfPersons.stream().filter(e -> e.getiDrive()).sorted(Comparator.comparingInt(Person::getAge)).toList();

//        System.out.println("Acestia sunt soferi: ");
//        for (Person person : drivers) {
//            person.setMyNumber(forDrivers);
//            forDrivers++;
//            System.out.println(person.getFirstName() + " si are varsta de: " + person.getAge() + " cu id - ul: " + person.myNumber);
//            person.outputAllStops();
//        }


        List<Person> passangers = new LinkedList<>();
        passangers = listOfPersons.stream().filter(e -> !e.getiDrive()).sorted(Comparator.comparingInt(Person::getAge)).toList();
//        System.out.println("\nAcestia sunt pasageri: ");
//        for (Person person : passangers) {
//            person.setMyNumber(forPassagers);
//            forPassagers++;
//            System.out.println(person.getFirstName() + " si are varsta de: " + person.getAge() + " cu id - ul: " + person.myNumber);
//            person.outputAllStops();
//        }

        long t0 = System.currentTimeMillis();
        GreedyCarpool alg = new GreedyCarpool(drivers, passangers);
        long t1 = System.currentTimeMillis();

        long t2 = System.currentTimeMillis();
        GraphAlgorithm alg2 = new GraphAlgorithm(drivers, passangers, g);
        long t3 = System.currentTimeMillis();

        System.out.println("pentru alg greedy: " + (t1 - t0));
        System.out.println("pentru alg graf: " + (t3 - t2));
        //StabilityNumber alg3 = new StabilityNumber(drivers, passangers, 20);
    }
}

