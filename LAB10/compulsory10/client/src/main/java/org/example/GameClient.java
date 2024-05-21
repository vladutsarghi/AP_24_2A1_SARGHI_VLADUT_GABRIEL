package org.example;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String command;
            System.out.println("Connected to the game server. Type commands:");

            while ((command = consoleReader.readLine()) != null) {
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }

                writer.println(command);
                String response = reader.readLine();
                System.out.println(response);

                if (command.equalsIgnoreCase("stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
