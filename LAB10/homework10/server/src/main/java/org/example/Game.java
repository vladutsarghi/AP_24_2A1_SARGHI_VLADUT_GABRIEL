package org.example;

public class Game {
    private ClientThread player1;
    private ClientThread player2;
    Boolean havePassword;
    Boolean turn;

    public Game(ClientThread pl1, ClientThread pl2){
        player1 = pl1;
        player2 = pl2;
    }

    public Game() {

    }

    public ClientThread getPlayer1() {
        return player1;
    }

    public ClientThread getPlayer2() {
        return player2;
    }

    public void setPlayer1(ClientThread player1) {
        this.player1 = player1;
    }

    public void setPlayer2(ClientThread player2) {
        this.player2 = player2;
    }

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }
}
