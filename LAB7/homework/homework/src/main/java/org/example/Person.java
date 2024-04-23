package org.example;

import java.util.ArrayList;
import java.util.List;

public class Person implements Runnable{
    CycleDetection cd = new CycleDetection();
    static private final Object lock = new Object();
    static int numberOfPlayers = 1;
    static int higgestScore;
    static String winnerName;

    int numberOfTokens;
    int playerNumber;
    int myScore;

    private static int nextPlayer = 1;
    String name;
    Bag bag;
    List<Pair> myBag = new ArrayList<>();

    public Person(String name, Bag bag){
        this.name = name;
        this.bag = bag;
        this.numberOfTokens = bag.getNumberOfTokens();
        playerNumber = numberOfPlayers;
        System.out.println(playerNumber + " aici");
        numberOfPlayers++;
    }

    public void extractedToken(Pair tokenExtracted){
        myBag.add(tokenExtracted);
    }

    @Override
    public void run() {

        synchronized (lock) {
            for (int i = 0; i < numberOfTokens; i++) {
                while (playerNumber != nextPlayer) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                Pair number = bag.extractToken();
                if (number.getFirst() != -1) {
                    myBag.add(number);
                    System.out.println(name + " got number: " + number.getFirst() + " " + number.getSecond());
                } else {
                    nextPlayer = (nextPlayer % 3) + 1;
                }

                CycleDetection.findCycleSizes(myBag, 100);
                if(cd.isFind() == true)
                {
                    System.out.println(name + "a castigat");
                }

                if(i >= numberOfTokens - 1){
                    System.out.println(name);
                    for(Integer sizes : CycleDetection.findCycleSizes2(myBag)){
                        System.out.println(sizes + " ");
                        myScore += sizes.intValue();
                    }
                    System.out.println();


                    if(myScore > higgestScore){
                        higgestScore = myScore;
                        winnerName = name;
                    }
                }

                nextPlayer = (nextPlayer % 3) + 1;
                lock.notifyAll(); // Notify all waiting threads
            }
        }
    }

    static public void printWinner(){
        System.out.println("a castigat: " + winnerName + " cu scorul " + higgestScore);
    }
}
