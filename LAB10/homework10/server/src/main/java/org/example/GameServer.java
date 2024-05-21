package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private static final int PORT = 12345;
    private boolean running = true;
    private List<ClientThread> clients = new ArrayList<>();
    private static List<Game> games = new ArrayList<>();
    private static Boolean turn = true;

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("GameServer is listening on port " + PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientThread clientThread = new ClientThread(this, socket);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopServer() {
        running = false;
        System.out.println("Server stopped");
    }

    public void createTable(ClientThread clientThread) {

        StringBuilder sb = clientThread.matrix.printMatrix();
        sb.append("Ai 20 de patratele de ales\n" +
                "Introdu de la tastaura coloana si linia de la care vrei sa incepi\n" +
                "Apoi coloana si linia la care vrei sa terimini\n" +
                "Exemplu: 1A 3B\n");
        sb.append("END_OF_MESSAGE");

        clientThread.sendMessage(sb.toString());
    }

    public int addShip(ClientThread clientThread, String from, String to) {
        int rowStart = from.charAt(0) - 'A';
        int colStart = from.charAt(1) - '0';
        int rowFinish = to.charAt(0) - 'A';
        int colFinish = to.charAt(1) - '0';

        int putShip = clientThread.matrix.setFromTo(rowStart, colStart, rowFinish, colFinish);
        if (putShip > 0) {
            StringBuilder sb = clientThread.matrix.printMatrix();
            String str1 = String.valueOf(clientThread.matrix.getDisponibleShips());
            sb.append("mai aveti ").append(str1).append(" blocuri disponibile\n");
            sb.append("END_OF_MESSAGE");
            clientThread.sendMessage(sb.toString());

        } else if (putShip == 0) {
            clientThread.sendMessage("Coordonate gresite, incercati din nou: \n" + "END_OF_MESSAGE");
        } else if (putShip == -1) {
            StringBuilder sb =
                    clientThread.matrix.printMatrix();
            String msg = "wait for opponent\n" + "END_OF_MESSAGE";
            clientThread.sendMessage(sb.toString() + msg);

            //clientThread.sendMessage("wait for opponent\n" + "END_OF_MESSAGE");

            // TODO
        }

        return clientThread.matrix.getDisponibleShips();
    }

    public void addPlayer(ClientThread clientThread) {
        boolean existMatch = false;
        for (Game game : games) {
            if (game.getPlayer1() == null) {
                game.setPlayer1(clientThread);
                existMatch = true;
            } else if (game.getPlayer2() == null) {
                game.setPlayer2(clientThread);
                existMatch = true;
                // TODO
                System.out.println("AVEM UN MECI PREGATIT PENTRU START");
            }
        }

        if (!existMatch) {
            Game newGame = new Game();
            newGame.setPlayer1(clientThread);
            games.add(newGame);
        }
    }

    public Game waitForPlayer(ClientThread clientThread) {
        System.out.println("sunt aici asteptand\n");
        Game myGame = null;
        boolean exit = false;
        while (!exit) {
            for (Game game : games) {
                if ((game.getPlayer1() == clientThread && game.getPlayer2() != null) || (game.getPlayer2() == clientThread)) {
                    game.setTurn(true);
                    clientThread.sendMessage("Match starts\nEND_OF_MESSAGE");
                    exit = true;
                    myGame = game;
                    break;
                }
            }
        }
        return myGame;
    }

    public void startsGame(ClientThread clientThread, InputStream input, BufferedReader reader, OutputStream output) throws IOException {
        int index = 0;
        for(Game mygame : games){

           if(mygame.getPlayer1() == clientThread || mygame.getPlayer2() == clientThread)
           { break;}
           index++;
       }

        if (clientThread == games.get(index).getPlayer1()) {
            while (true) {
                if (games.get(index).getTurn()) {
                    games.get(index).getPlayer1().sendMessage("It's your turn, choose the block you want\nEND_OF_MESSAGE");
                    String move = games.get(index).getPlayer1().waitForMessage(input, reader, output);
                    if (move != null) {
                        System.out.println("Player 1 made a move: " + move);
                        games.get(index).getPlayer1().sendMessage("ok player 1, ai facut aceasta miscare =, esti bun\nEND_OF_MESSAGE");
                        //SAASDF checkMove(game, move, game.getPlayer1());
                        games.get(index).setTurn(false);
                    }
                }
            }
        }

        if (clientThread == games.get(index).getPlayer2()) {
            while (true) {
                if (!games.get(index).getTurn()) {
                    games.get(index).getPlayer2().sendMessage("It's your turn, choose the block you want\nEND_OF_MESSAGE");
                    String move = games.get(index).getPlayer2().waitForMessage(input, reader, output);
                    if (move != null) {
                        System.out.println("Player 2 made a move: " + move);
                        games.get(index).getPlayer2().sendMessage("ok player 2, ai facut aceasta miscare =, esti bun\nEND_OF_MESSAGE");

                        //checkMove(game, move, game.getPlayer2());
                        games.get(index).setTurn(true);
                    }
                }
            }
        }
    }


    private void checkMove(Game game, String move) {

    }
}
