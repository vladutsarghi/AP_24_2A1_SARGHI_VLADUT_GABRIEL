package org.example;

import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;

import java.net.PasswordAuthentication;
import java.util.*;

public class GraphAlgorithm {
    List<Person> driver = new ArrayList<>();
    List<Person> passanger = new ArrayList<>();
    Graph g = GraphBuilder.empty().buildGraph();


    GraphAlgorithm(List<Person> driver, List<Person> passanger, Graph g) {
        this.driver = driver;
        this.passanger = passanger;
        this.g = g;
        algGraph4J();
        //outputGraph();
    }

    public void algGraph4J() {

        for (Person driver : driver) {
            for (Person passanger : passanger) {
                int index1 = -1;
                int index2 = -1;

                if (driver.getStops().containsKey(passanger.getListPassanger().getFirst())) {
                    index1 = driver.getId(passanger.getListPassanger().getFirst());
                }

                if (driver.getStops().containsKey(passanger.getListPassanger().getLast())) {
                    index2 = driver.getId(passanger.getListPassanger().getLast());
                }

                if (index1 != -1 && index2 != -1 & index1 <= index2) {
                    g.addEdge(driver.getMyNumber(), passanger.getMyNumber());
                }
            }
        }

        HopcroftKarpMaximumMatching alg = new HopcroftKarpMaximumMatching(g);

//        System.out.println(alg.toString());
        Matching m = alg.getMatching();
        int[][] matchingEdges = new int[20][2];
        matchingEdges = m.edges();

        for(int i = 0 ; i < m.edges().length; i++){
            //System.out.println("i : " + matchingEdges[i][0] + " -> " + matchingEdges[i][1]);
        }
    }

    public void outputGraph(){
        for (int i = 0; i < g.vertices().length; i++) {
            //System.out.println(Arrays.toString(g.edgesOf(i)));

        }
    }
}