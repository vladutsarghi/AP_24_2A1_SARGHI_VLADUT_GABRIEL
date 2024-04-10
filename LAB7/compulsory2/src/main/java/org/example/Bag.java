package org.example;

import java.util.ArrayList;
import java.util.List;

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
    }

    public Pair extractToken(){

    }
}

