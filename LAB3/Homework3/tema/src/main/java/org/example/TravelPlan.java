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

public class TravelPlan {
    List<TravelDetails> travelPlans = new ArrayList<>();
    Map<KeyPair, TimeInterval<LocalTime, LocalTime>> map = new HashMap<>();
    LocalDate come;
    LocalDate leave;

    TravelPlan(Map<KeyPair, TimeInterval<LocalTime, LocalTime>> map, LocalDate come, LocalDate leave) {
        this.map = map;
        this.come = come;
        this.leave = leave;

        makeTravelPlan();
    }

    private void makeTravelPlan() {
        LocalDate startDate = come;
        LocalDate endDate = leave;

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            LocalTime currentTime = LocalTime.of(8, 0);

            for (Map.Entry<KeyPair, TimeInterval<LocalTime, LocalTime>> entry : map.entrySet()) {
                KeyPair key = entry.getKey();
                LocalDate keyDate = key.getLocalDate();
                TimeInterval<LocalTime, LocalTime> value = entry.getValue();
                if (keyDate.equals(currentDate)) {
                    if (currentTime.isBefore(value.getFinish())) {
                        if (currentTime.isBefore(value.getStart())) {
                            currentTime = value.getStart();
                        }
                        currentTime = currentTime.plusHours(1);
                        TravelDetails temp = new TravelDetails(key.getEventName(), key.getLocalDate(), value.getStart(), value.getFinish(), currentTime);
                        travelPlans.add(temp);
                    }
                }
            }

            currentDate = currentDate.plusDays(1); // Trecem la următoarea zi
        }
    }

    public void outputTravelPlan() {
        System.out.println("Acesta este travel plan ul: ");
        for (TravelDetails plan : travelPlans) {
            System.out.println(plan.getName() + " incepe la " + plan.getStart() + " si se termina la " + plan.getEnd() +
                    " in data de " + plan.getLocalDate() + "si pana la: " + plan.getiLeave());
        }
    }

    public void outputTravelPlanForASpecificDay(LocalDate date) {
        for (TravelDetails plan : travelPlans) {
            if (plan.getLocalDate().isEqual(date)) {
                System.out.println(plan.getName() + " incepe la " + plan.getStart() + " si se termina la " + plan.getEnd() +
                        " in data de " + plan.getLocalDate() + "si pana la: " + plan.getiLeave());
            }
        }
    }

}


class TravelDetails {
    private final String name;
    private final LocalDate localDate;
    private final LocalTime start;
    private final LocalTime end;

    private final LocalTime iLeave;

    public TravelDetails(String name, LocalDate localDate, LocalTime start, LocalTime end, LocalTime leave) {
        this.name = name;
        this.localDate = localDate;
        this.start = start;
        this.end = end;
        this.iLeave = leave;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public LocalTime getiLeave() {
        return iLeave;
    }
}
