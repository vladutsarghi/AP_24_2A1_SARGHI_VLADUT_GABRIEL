package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Bag bag = new Bag(10);
        Person player1 = new Person("Player 1", bag);
        Person player2 = new Person("Player 2", bag);
        Person player3 = new Person("Player 3", bag);

        System.out.println(player1.playerNumber + " : " + player2.playerNumber);

        Thread thread1 = new Thread(player1);
        Thread thread2 = new Thread(player2);
        Thread thread3 = new Thread(player3);

        thread1.start();
        thread2.start();
        thread3.start();
        

        thread1.join();
        thread2.join();
        thread3.join();

    }
}



