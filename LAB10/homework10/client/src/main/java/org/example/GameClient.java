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

            boolean inGame = false;

            while (true) {
                if (!inGame) {
                    // Read command from the console
                    command = consoleReader.readLine();

                    // Send command to the server
                    writer.println(command);

                    // Break the loop if the command is "exit"
                    if (command.equalsIgnoreCase("exit")) {
                        break;
                    }
                }

                // Wait for the server response
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("END_OF_MESSAGE")) {
                        break;
                    }
                    responseBuilder.append(line).append("\n");
                }

                // Print the server response
                String response = responseBuilder.toString();
                System.out.println(response);

                // Check for specific responses to update game state
                if (response.contains("Match starts")) {
                    inGame = true;
                }

                if (inGame) {
                    // Handle game moves
                    System.out.println("Introdu coordonata pe care crezi ca se afla un bloc a oponentului\n" +
                            "Mesajul sa fie de forma [A-J][1-9]: \n");

                    // Read move from the console
                    command = consoleReader.readLine();

                    // Send move to the server
                    writer.println(command);
                }

                if (response.contains("wait for opponent")) {
                    // Continue waiting for the opponent
                    inGame = true;
                }

                if (response.contains("Server stopped")) {
                    break;
                }

                if (response.contains("ok player 1, ai facut aceasta miscare") ||
                        response.contains("ok player 2, ai facut aceasta miscare")) {
                    inGame = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
