package org.example;

import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;

import java.net.PasswordAuthentication;
import java.util.*;

public class StabilityNumber {
    List<Person> driver = new ArrayList<>();
    List<Person> passanger = new ArrayList<>();
    Graph g = GraphBuilder.empty().buildGraph();
    boolean[][] matrix;


    private int maxCount = 0; // Tracks the maximum size of independent set found so far
    private List<Integer> maxSet = new ArrayList<>();


    StabilityNumber(List<Person> driver, List<Person> passanger, int size) {
        this.driver = driver;
        this.passanger = passanger;
        matrix = new boolean[size][size];
        algGraph4J();
    }

    public void algGraph4J() {

        for (Person driver : driver) {
            for (Person passanger : passanger) {
                if (driver.getDestination().compareTo(passanger.getDestination()) == 0) {
                    matrix[driver.getMyNumber()][passanger.getMyNumber()] = true;
                    matrix[passanger.getMyNumber()][driver.getMyNumber()] = true;
                }
            }
        }

        for (int i = 0; i < driver.size(); i++) {
            for (int j = i + 1; j < driver.size(); j++) {
                Person driver1 = driver.get(i);
                Person driver2 = driver.get(j);

                if (driver1.getDestination().compareTo(driver2.getDestination()) == 0) {
                    matrix[driver1.getMyNumber()][driver2.getMyNumber()] = true;
                    matrix[driver2.getMyNumber()][driver1.getMyNumber()] = true;
                }
            }
        }

        for (int i = 0; i < passanger.size(); i++) {
            for (int j = i + 1; j < passanger.size(); j++) {
                Person passanger1 = passanger.get(i);
                Person passanger2 = passanger.get(j);

                if (passanger1.getDestination().compareTo(passanger2.getDestination()) == 0) {

                    matrix[passanger1.getMyNumber()][passanger2.getMyNumber()] = true;
                    matrix[passanger2.getMyNumber()][passanger1.getMyNumber()] = true;
                }
            }
        }

        List<Integer> maxStableSet = findMaximumStableSet(matrix);
        System.out.println("Maximum Stable Set: " + maxStableSet);
        System.out.println("Size of Maximum Stable Set: " + maxStableSet.size());
    }

    private List<Integer> findMaximumStableSet(boolean[][] graph) {
        findMaximumStableSet(graph, new ArrayList<>(), 0);
        return maxSet;
    }

    private void findMaximumStableSet(boolean[][] graph, List<Integer> currentSet, int start) {
        if (currentSet.size() > maxCount) {
            maxCount = currentSet.size();
            maxSet = new ArrayList<>(currentSet);
        }

        for (int i = start; i < graph.length; i++) {
            if (isSafe(graph, currentSet, i)) {
                currentSet.add(i);

                findMaximumStableSet(graph, currentSet, i + 1);

                currentSet.remove(currentSet.size() - 1);
            }
        }
    }

    private boolean isSafe(boolean[][] graph, List<Integer> set, int vertex) {
        for (int s : set) {
            if (graph[vertex][s]) {
                return false;
            }
        }
        return true;
    }


}