package command;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View implements Command {

    public View(String fileLocation) throws IOException {
        checkFileLocation(fileLocation);
    }

    @Override
    public void checkFileLocation(String fileLocation) {
        File file = new File(fileLocation);
        if (file.exists()) {
            implementCommand(file);
        }
    }

    @Override
    public void implementCommand(File file) {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException e) {
            // Handle IOException
            System.err.println("An IO exception occurred while opening the file: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("An unexpected exception occurred while opening the file: " + e.getMessage());
        }
    }
}
