package org.example.app;

import org.example.com.DisplayLocales;
import org.example.com.Info;
import org.example.com.SetLocale;

import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.print("Input command: ");
            String command = scanner.nextLine().trim();
            switch (command) {
                case "display":
                    DisplayLocales.execute();
                    break;
                case "set":
                    System.out.print("Enter language tag (e.g., en-US): ");
                    String tag = scanner.nextLine().trim();
                    SetLocale.execute(tag);
                    break;
                case "info":
                    Info.execute(SetLocale.getCurrentLocale());
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
        scanner.close();
    }
}
