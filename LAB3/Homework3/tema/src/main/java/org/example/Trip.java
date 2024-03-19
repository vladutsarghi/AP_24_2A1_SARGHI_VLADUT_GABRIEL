package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

import java.util.Collections;
import java.util.Comparator;

public class Trip {
    Map<LocalDate, TimeInterval<LocalTime, LocalTime>> map = new HashMap<>();
    Map<String, TimeIntervalAndDate<LocalDate, LocalTime, LocalTime>> map2 = new HashMap<>();

    Map<KeyPair, TimeInterval<LocalTime, LocalTime>> map3 = new HashMap<>();

    String cityName;
    LocalDate come;
    LocalDate leave;

    List<Visitable> places = new ArrayList<>();

    Statue statue1 = new Statue("Ion Creanga");
    Statue statue2 = new Statue("Ion Alex", LocalTime.of(12, 0), LocalTime.of(20, 0));
    Statue statue3 = new Statue("Ion Gheorghe");

    Concert concert1 = new Concert("Forta Zuu", 30, LocalDate.of(2024, 6, 13), LocalTime.of(20, 0), LocalTime.of(23, 0));
    Concert concert2 = new Concert("Concert", 40, LocalDate.of(2024, 6, 14), LocalTime.of(20, 0), LocalTime.of(2, 0));

    Church church1 = new Church("bisericute");

    Trip(String cityName, LocalDate come, LocalDate leave) {
        this.cityName = cityName;
        this.come = come;
        this.leave = leave;
    }

    public void populate() {
        places.add(statue1);
        places.add(statue2);
        places.add(statue3);
        places.add(concert1);
        places.add(church1);

        makeVisitingTimetable();
        outputTimetable();

    }

    private void makeVisitingTimetable() {
        LocalDate startDate = come;
        LocalDate endDate = leave;

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            for (Visitable i : places) {
                if (i.isOpen(currentDate)) {
                    TimeInterval temp = new TimeInterval<>(i.getOpenTime(), i.getCloseTime());
                    TimeIntervalAndDate temp2 = new TimeIntervalAndDate(currentDate, i.getOpenTime(), i.getCloseTime());
                    map.put(currentDate, temp);
                    map2.put(i.getName(), temp2);
                    map3.put(new KeyPair(currentDate, i.getName()), temp);

                }
            }
            currentDate = currentDate.plusDays(1); // Trecem la următoarea zi
        }
    }

    public void outputTimetable() {
//        for (Map.Entry<String, TimeIntervalAndDate<LocalDate, LocalTime, LocalTime>> entry : map2.entrySet()) {
//            String key = entry.getKey();
//            TimeIntervalAndDate<LocalDate, LocalTime, LocalTime> value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + value.getDate() + "   " + value.getStart() + "     " + value.getFinish());
//        }

//        for (Map.Entry<LocalDate, TimeInterval<LocalTime, LocalTime>> entry : map.entrySet()) {
//            LocalDate key = entry.getKey();
//            TimeInterval<LocalTime, LocalTime> value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + "   " + value.getStart() + "     " + value.getFinish());
//        }

        for (Map.Entry<KeyPair, TimeInterval<LocalTime, LocalTime>> entry : map3.entrySet()) {
            KeyPair key = entry.getKey();
            TimeInterval<LocalTime, LocalTime> value = entry.getValue();
            System.out.println("Key: " + key.getLocalDate() + ", Value: " + key.getEventName() + "   " + value.getStart() + "     " + value.getFinish());
        }

    }

    public void getVisitable() {
        List<Visitable> justVisitable = new ArrayList<Visitable>();
        for (Visitable obj : places) {
            // Verificăm dacă obiectul curent implementează MyInterface
            if (obj instanceof Visitable) {
                if (!(obj instanceof Payable )) {
                    justVisitable.add(obj);
                }
            }
        }

        justVisitable.sort(new Comparator<Visitable>() {
            @Override
            public int compare(Visitable obj1, Visitable obj2) {

                return obj1.getOpenTime().compareTo(obj2.getOpenTime());
            }
        });


        for (Visitable obj : justVisitable) {
            System.out.println(obj.getName() + " incepe la " + obj.getOpenTime());
        }

        System.out.println("soasiasdisiadi");


    }

    public Map<KeyPair, TimeInterval<LocalTime, LocalTime>> getMap(){
        return map3;
    }

    public LocalDate getCome() {
        return come;
    }

    public LocalDate getLeave() {
        return leave;
    }
}
