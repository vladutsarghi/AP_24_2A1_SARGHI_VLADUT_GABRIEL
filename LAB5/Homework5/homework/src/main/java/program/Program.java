package program;

import command.Export;
import command.Report;
import command.View;
import freemarker.template.TemplateException;
import myException.IllegalCommand;
import myException.NonExistentCommand;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.Scanner;

public class Program {

    public void run() throws IllegalCommand, NonExistentCommand, IOException, TemplateException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("comanda: ");
        String command = scanner.nextLine(); // nextLine() citește o linie întreagă

        checkCommand(command);
        scanner.close();

    }

    private void checkCommand(String command) throws IllegalCommand, NonExistentCommand, IOException, TemplateException {
        int index = command.indexOf(' ');

        if (index == -1) {
            throw new IllegalCommand("Input must have a command and a location");
        }
        String firstWord = index != -1 ? command.substring(0, index) : command;
        String restOfString = index != -1 ? command.substring(index + 1) : "";

        checkAndRunCommand(firstWord, restOfString);
    }

    private void checkAndRunCommand(String command, String filePath) throws NonExistentCommand, IOException, TemplateException {
        if (command.compareTo("view") == 0) {
            View view = new View(filePath);
        } else if (command.compareTo("report") == 0) {
            Report report = new Report(filePath);
        } else if (command.compareTo("export") == 0) {
            Export export = new Export(filePath);
        } else {
            throw new NonExistentCommand("your command does not exist");
        }
    }
}
