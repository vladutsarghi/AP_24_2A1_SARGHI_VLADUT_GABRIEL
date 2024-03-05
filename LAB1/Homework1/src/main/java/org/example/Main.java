package org.example;

import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    static String reductibile(int a, int b, int k) {
        StringBuilder output = new StringBuilder();
        if (a > b) {
            return "interval gresit";
        }
        if (k < 0) {
            return "k negativ";
        }


        for (int i = a; i <= b; i++) {
            int toCheck = i;

            while (toCheck >= 0) {
                int newCheck = 0;
                while (toCheck != 0) {
                    newCheck += ((toCheck % 10) * (toCheck % 10));
                    toCheck /= 10;
                }

                toCheck = newCheck;
                if (toCheck == k) {
                    // adaugam la string
                    output.append(String.valueOf(i));
                    output.append(" ");
                    break;
                } else if (toCheck == 4) {
                    break;
                } else if (toCheck == 1) {
                    break;
                }
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introdu un număr întreg: ");
        int a = scanner.nextInt();

        System.out.print("Introdu un număr întreg: ");
        int b = scanner.nextInt();

        System.out.print("Introdu un număr întreg: ");
        int k = scanner.nextInt();

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        long t1 = System.currentTimeMillis();
        System.out.println(reductibile(a, b, k));
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

    }
}