package org.example;

import org.example.GameServer;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;

    public ClientThread(GameServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String command;
            while ((command = reader.readLine()) != null) {
                if (command.equalsIgnoreCase("stop")) {
                    server.stopServer();
                    writer.println("Server stopped");
                    break;
                } else {
                    writer.println("Server received the request: " + command);
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
