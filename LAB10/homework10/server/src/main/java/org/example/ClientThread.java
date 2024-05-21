package org.example;


import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;

public class ClientThread extends Thread {
    private final Socket socket;
    private final GameServer server;
    private Game myGame;
    PrintWriter writer;

    public Matrix matrix = new Matrix();
    boolean inGame = false;
    boolean chooseShips = false;


    public ClientThread(GameServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream()
             ) {

            writer = new PrintWriter(output, true);
            String command;
            while ((command = reader.readLine()) != null) {
                System.out.println("\n\nCOMANDA ESTE: " + command + "\n\n");
                if (command.equalsIgnoreCase("stop")) {
                    server.stopServer();
                    writer.println("Server stopped");
                    break;
                }
                else if (chooseShips == false && command.equalsIgnoreCase("create")){
                    server.createTable(this);
                    chooseShips = true;
                }
                else if(chooseShips && !inGame){
                    if (command.length() >= 4) {
                        String firstTwo = command.substring(0, 2);
                        String lastTwo = command.substring(command.length() - 2);

                        int shipsD = server.addShip(this, firstTwo, lastTwo);
                        System.out.println("sunt aici cu " + shipsD + " nave");
                        if(shipsD == 0){
                            server.addPlayer(this);
                            myGame = server.waitForPlayer(this);
                            inGame = true;
                            chooseShips = false;
                        }
                    } else {
                        writer.println("The string is too short!");
                    }
                }
                else if (inGame) {

                    server.startsGame(this, input, reader, output);
                    // TODO
                }
                else {
                    writer.println("Server received the request: " + command + "\nEND_OF_MESSAGE");

                }
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void sendMessage(String message) {
        System.out.println(message);
        if (writer != null) {
            writer.println(message);
        }
    }

    public String waitForMessage(InputStream input, BufferedReader reader, OutputStream output) throws IOException {
        String command;
        while ((command = reader.readLine()) == null) {
            // This loop will wait until a message is received
        }
        System.out.println("Received command: " + command);
        return command;
    }
}
