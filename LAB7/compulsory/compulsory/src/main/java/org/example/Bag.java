package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Bag {
    int numberOfTokens;
    List<Pair> bagOfTokens = new ArrayList<>();

    public Bag(int numberOfTokens){
        this.numberOfTokens = numberOfTokens;
        generate();
    }

    private void generate(){

        for(int i = 0; i < numberOfTokens; i++){
            for(int j = 0; j < numberOfTokens; j++){
                if(i != j){
                    bagOfTokens.add(new Pair(i, j));
                }
            }
        }

        numberOfTokens = numberOfTokens * (numberOfTokens - 1);
    }

    public synchronized Pair extractToken(){
        if(bagOfTokens.isEmpty()){
            return new Pair(-1, -1);
           // return null;
        }
        Random random = new Random();
        int randomNumber = random.nextInt(numberOfTokens);

        Pair pair = bagOfTokens.get(randomNumber);
        numberOfTokens--;
        bagOfTokens.remove(randomNumber);

        return pair;
    }
}

