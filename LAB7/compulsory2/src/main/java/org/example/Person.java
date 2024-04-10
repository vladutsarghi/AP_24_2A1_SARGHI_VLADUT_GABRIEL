package org.example;

import java.util.ArrayList;
import java.util.List;

public class Person extends Thread{
    String name;
    List<Pair> myBag = new ArrayList<>();

    public Person(String name){
        this.name = name;
    }

    public void extractedToken(Pair tokenExtracted){
        myBag.add(tokenExtracted);
    }
}
