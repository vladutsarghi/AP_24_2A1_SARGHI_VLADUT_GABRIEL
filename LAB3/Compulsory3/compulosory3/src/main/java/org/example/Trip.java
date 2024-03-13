package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Trip{
    String cityName;
    LocalDate come;
    LocalDate leave;

    List<Object> places = new ArrayList<>();

    Trip(String cityName, LocalDate come, LocalDate leave){
        this.cityName = cityName;
        this.come = come;
        this.leave = leave;

    }


    Statue statue1 = new Statue("Ion Creanga");
    Statue statue2 = new Statue("Ion Alex");
    Statue statue3 = new Statue("Ion Gheorghe");

    Concert concert1 = new Concert("fozaZu", 12.23);
    Concert concert2 = new Concert("Foza fii", 12.23);
    Concert concert3 = new Concert("fii concert", 12.23);

    Church church1 = new Church("bisericute");

    public void populate(){
        places.add(statue1);
        places.add(statue2);
        places.add(statue3);
        places.add(concert1);
        places.add(church1);
    }



}
