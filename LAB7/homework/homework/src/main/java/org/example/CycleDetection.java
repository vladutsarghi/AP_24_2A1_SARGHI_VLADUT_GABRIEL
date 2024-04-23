package org.example;

import java.util.*;

public class CycleDetection {
    int size;
    static boolean find;
    public static List<Integer> findCycleSizes(List<Pair> pairs, int n) {
        find = false;
        List<Integer> sizes = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        for (Pair pair : pairs) {
            if (!visited.contains(pair.getFirst())) {
                findCycles(n, pair.getFirst(), pair.getFirst(), pairs, visited, path, sizes);
            }
        }

        return sizes;
    }

    private static void findCycles(int n, int start, int current, List<Pair> pairs, Set<Integer> visited, List<Integer> path, List<Integer> sizes) {
        path.add(current);
        visited.add(current);

        for (Pair pair : pairs) {
            if (pair.getFirst() == current) {
                if (pair.getSecond() == start && path.size() > 1) {
                    sizes.add(path.size());
                    if(path.size() == n) {
                        find = true;
                        break;
                    }
                } else if (!visited.contains(pair.getSecond())) {
                    findCycles(n, start, pair.getSecond(), pairs, visited, path, sizes);
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(current);
    }

    public boolean isFind(){
        return find;
    }




    public static List<Integer> findCycleSizes2(List<Pair> pairs) {
        List<Integer> cycleSizes = new ArrayList<>();
        while (!pairs.isEmpty()) {
            Set<Pair> visited = new HashSet<>();
            boolean found = findAndRemoveCycle(pairs, visited, pairs.get(0), pairs.get(0), new ArrayList<>(), cycleSizes);
            if (!found) {
                break;
            }
        }

        return cycleSizes;
    }

    private static boolean findAndRemoveCycle(List<Pair> pairs, Set<Pair> visited, Pair start, Pair current, List<Pair> path, List<Integer> cycleSizes) {
        path.add(current);
        visited.add(current);

        for (Pair next : pairs) {
            if (current.getSecond() == next.getFirst()) {
                if (next.equals(start) && path.size() > 1) {
                    cycleSizes.add(path.size());
                    pairs.removeAll(visited);
                    return true;
                } else if (!visited.contains(next)) {
                    if (findAndRemoveCycle(pairs, visited, start, next, path, cycleSizes)) {
                        return true;
                    }
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(current);
        return false;
    }
}
