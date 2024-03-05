package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void createGraph() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introdu un număr întreg ce reprezinta numarul de noduri: ");
        int n = scanner.nextInt();

        int[][] adiacenta = new int[n + 1][n + 1];

        for (int i = 0; i < n - 1; i++) {
            adiacenta[i][i + 1] = 1;
            adiacenta[i][n - 1] = 1;
            adiacenta[i + 1][i] = 1;
            adiacenta[n - 1][i] = 1;
        }
        adiacenta[n - 2][0] = 1;
        adiacenta[0][n - 2] = 1;

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (adiacenta[i][j] == 1) {
//                    System.out.println("Muchie de la " + i + " la " + j);
//                }
//            }
//        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adiacenta[i][j] + " ");
            }
            System.out.print("\n");
        }

        int numCycles = countCycles(adiacenta, n);
        System.out.println("Numarul de cicluri: " + numCycles);
    }

    private static int countCycles(int[][] graph, int n) {
        boolean[] visited = new boolean[n];
        int result = 0;

        for (int i = 0; i < n; i++) {
            result += countCyclesUtil(graph, visited, i, i, 0, n);
            visited[i] = true;
        }

        return result / 2;
    }

    private static int countCyclesUtil(int[][] graph, boolean[] visited, int start, int currentNode, int pos, int n) {
        if (visited[currentNode]) {
            return 0;
        }

        visited[currentNode] = true;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (graph[currentNode][i] == 1) {
                count += countCyclesUtil(graph, visited, start, i, pos + 1, n);
            }
        }

        if (pos > 1 && graph[currentNode][start] == 1) {
            count++;
        }

        visited[currentNode] = false;

        return count;
    }

    public static void main(String[] args) {
        createGraph();
    }
}