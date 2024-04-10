package org.example;

import java.util.ArrayList;
import java.util.List;

public class Person implements Runnable{
    static int numberOfPlayers = 1;
    int playerNumber;
    private static int nextPlayer = 1;
    String name;
    Bag bag;
    List<Pair> myBag = new ArrayList<>();

    public Person(String name, Bag bag){
        this.name = name;
        this.bag = bag;
        playerNumber = numberOfPlayers;
        System.out.println(playerNumber + " aici");
        numberOfPlayers++;
    }

    public void extractedToken(Pair tokenExtracted){
        myBag.add(tokenExtracted);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bag) {
                if (playerNumber == nextPlayer) {
                    Pair number = bag.extractToken();
                    if (number.getFirst() != -1) {
                        System.out.println(name + " got number: " + number.getFirst() + " " + number.getSecond());
                    } else {
                        nextPlayer = (nextPlayer % 3) + 1;
                        break; // Break the loop if the bag is empty

                    }

                    nextPlayer = (nextPlayer % 3) + 1;
                }
            }
        }
    }
}
