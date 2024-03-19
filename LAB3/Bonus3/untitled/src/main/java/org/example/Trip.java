package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

import java.util.Random;


public class Trip {
    Map<LocalDate, TimeInterval<LocalTime, LocalTime>> map = new HashMap<>();
    Map<String, TimeIntervalAndDate<LocalDate, LocalTime, LocalTime>> map2 = new HashMap<>();

    Map<KeyPair, TimeInterval<LocalTime, LocalTime>> map3 = new HashMap<>();

    String cityName;
    LocalDate come;
    LocalDate leave;

    List<Object> places = new ArrayList<>();

    Statue statue1 = new Statue("Ion Creanga");
    Statue statue2 = new Statue("Ion Alex", LocalTime.of(12, 0), LocalTime.of(20, 0));
    Statue statue3 = new Statue("Ion Gheorghe");
    Statue statue4 = new Statue("Statuie4");
    Statue statue5 = new Statue("Statuie5");

    Statue statue6 = new Statue("Statuie6");

    Statue statue7 = new Statue("Statuie7");

    Statue statue8 = new Statue("Statuie8");


    Concert concert1 = new Concert("Forta Zuu", 30, LocalDate.of(2024, 6, 13), LocalTime.of(20, 0), LocalTime.of(23, 0));
    Concert concert2 = new Concert("Concert", 40, LocalDate.of(2024, 6, 14), LocalTime.of(20, 0), LocalTime.of(2, 0));

    Concert concert3 = new Concert("Concer3", 40, LocalDate.of(2024, 6, 14), LocalTime.of(20, 0), LocalTime.of(1, 0));

    Concert concert4 = new Concert("Concer3", 40, LocalDate.of(2024, 6, 15), LocalTime.of(15, 0), LocalTime.of(17, 0));

    Concert concert5 = new Concert("Concer3", 40, LocalDate.of(2024, 6, 15), LocalTime.of(16, 0), LocalTime.of(20, 0));

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
        places.add(statue4);
        places.add(statue5);
        places.add(statue6);
        places.add(statue7);
        places.add(statue8);
        places.add(concert1);
        places.add(concert2);
        places.add(concert3);
        places.add(concert4);
        places.add(concert5);
        places.add(church1);

        getTodayOpenActivities();

    }

    private void getTodayOpenActivities() {
        List<Visitable> todayActivities = new ArrayList<>();
        LocalDate startDate = come;
        LocalDate endDate = leave;

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            for (Object i : places) {
                if (i instanceof Visitable visitable) {
                    if (visitable.isOpen(currentDate)) {
                        todayActivities.add(visitable);
                    }
                }
            }
            makeVisitDecision(todayActivities, currentDate);
            todayActivities.clear();
            currentDate = currentDate.plusDays(1); // Trecem la următoarea zi
        }
    }

    public void makeVisitDecision(List<Visitable> visitable, LocalDate today) {
        System.out.print('\n');
        List<Pair> todayActivities = new ArrayList<>();
        Random random = new Random();
        int getThere;
        int leave;
        int id = 0;
        for (Visitable i : visitable) {

            if (i instanceof Concert) {
                getThere = i.getOpenTime().getHour();
                leave = i.getCloseTime().getHour();
            } else {
                getThere = random.nextInt(i.getCloseTime().getHour() - i.getOpenTime().getHour()) + i.getOpenTime().getHour();
                leave = getThere + 2;
                if (leave > i.getCloseTime().getHour()) {
                    leave = i.getCloseTime().getHour();
                }
            }
            Pair temp = new Pair(i, LocalTime.of(getThere, 0), LocalTime.of(leave, 0), id);
            id++;
            todayActivities.add(temp);
        }

        for (Pair i : todayActivities) {
            System.out.println(i.getVisitable().getName() + " ajung la: " + i.getStart() + " si plec la: " + i.getFinsih());
        }

        makeMatrix(todayActivities, id);

    }

    public void makeMatrix(List<Pair> todayActivities, int length) {
        int[][] graph = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j) {
                    if ((todayActivities.get(i).getStart().isBefore(todayActivities.get(j).getFinsih()) &&
                            todayActivities.get(i).getStart().isAfter(todayActivities.get(j).getStart()))
                            || (todayActivities.get(i).getFinsih().isBefore(todayActivities.get(j).getFinsih()) &&
                            todayActivities.get(i).getFinsih().isAfter(todayActivities.get(j).getStart())) ||
                            (todayActivities.get(i).getStart().equals(todayActivities.get(j).getStart()))) {
                        graph[i][j] = 1;
                        graph[j][i] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.print('\n');
        }

        greedyColoring(graph);
        largestDegreeFirst(graph);
    }

    public static void greedyColoring(int[][] graph) {
        int length = graph.length;
        int[] result = new int[length];
        Arrays.fill(result, -1);

        result[0] = 0;

        boolean[] available = new boolean[length];
        Arrays.fill(available, true);

        for (int v = 1; v < length; v++) {
            for (int i = 0; i < length; i++) {
                if (graph[v][i] == 1 && result[i] != -1) {
                    available[result[i]] = false;
                }
            }

            int color;
            for (color = 0; color < length; color++) {
                if (available[color]) {
                    break;
                }
            }
            result[v] = color;

            Arrays.fill(available, true);
        }

        for (int v = 0; v < length; v++) {
            System.out.println("Vârful " + v + " este colorat cu culoarea " + result[v]);
        }

        System.out.print('\n');
    }

    public void largestDegreeFirst(int[][] graph) {
        int length = graph.length;
        int[] result = new int[length];
        Arrays.fill(result, -1);

        boolean[] available = new boolean[length];
        Arrays.fill(available, true);

        int[] degree = new int[length];

        for (int i = 0; i < length; i++) {
            int deg = 0;
            for (int j = 0; j < length; j++) {
                if (graph[i][j] == 1) {
                    deg++;
                }
            }
            degree[i] = deg;
        }

        for (int i = 0; i < length; i++) {
            int maxDegreeVertex = findMaxDegreeVertex(degree, result, available);

            int color;
            for (color = 0; color < length; color++) {
                if (available[color]) {
                    break;
                }
            }
            result[maxDegreeVertex] = color;

            for (int j = 0; j < length; j++) {
                if (graph[maxDegreeVertex][j] == 1 && result[j] == -1) {
                    available[result[maxDegreeVertex]] = false;
                }
            }
        }

        for (int v = 0; v < length; v++) {
            System.out.println("Vârful " + v + " este colorat cu culoarea " + result[v]);
        }
    }

    private int findMaxDegreeVertex(int[] degree, int[] result, boolean[] available) {
        int maxDegree = -1;
        int maxDegreeVertex = -1;

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] > maxDegree && result[i] == -1) {
                maxDegree = degree[i];
                maxDegreeVertex = i;
            }
        }

        return maxDegreeVertex;
    }


    public Map<KeyPair, TimeInterval<LocalTime, LocalTime>> getMap() {
        return map3;
    }

    public LocalDate getCome() {
        return come;
    }

    public LocalDate getLeave() {
        return leave;
    }
}

class Pair {
    private final Visitable visitable;
    private final LocalTime start;
    private final LocalTime finsih;

    int id;

    public Pair(Visitable visitable, LocalTime start, LocalTime finish, int id) {
        this.visitable = visitable;
        this.start = start;
        this.finsih = finish;
        this.id = id;
    }

    // Getters and setters
    public Visitable getVisitable() {
        return visitable;
    }


    public LocalTime getStart() {
        return this.start;
    }

    public LocalTime getFinsih() {
        return this.finsih;
    }

    public int getId() {
        return this.id;
    }

}

