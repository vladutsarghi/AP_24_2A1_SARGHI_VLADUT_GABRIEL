package org.example;

public class Statue
implements Visitable{

    String nameOfStatue;
    Statue(String nameOfStatue){
        this.nameOfStatue = nameOfStatue;
    }

    @Override
    public void setOpenDays() {
        // is always open
    }

}
