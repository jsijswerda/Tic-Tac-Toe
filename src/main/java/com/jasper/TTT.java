package com.jasper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TTT {

    private List<Player> players;
    private String[][] board = new String[3][3];

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        TTT game = new TTT();
        game.initializeBoard();
        game.initializePlayers();
        game.printBoard();

        while (!game.weHaveAWinner() && !game.isBoardFull()) {
            Player currentPlayer = game.findCurrentPlayer();
            if (currentPlayer != null) {
                game.doTurn(currentPlayer);
                game.printBoard();
                if (!game.weHaveAWinner()) {
                    currentPlayer.changeTurn();
                }
            }
        }
        game.endGame();
    }

    public String [][] getBoard(){
        return board;
    }

    private Player findCurrentPlayer() {
        for (Player player : players){
            if (player.getHasTurn()){
                return player;
            }
        }
        return null;
    }

    protected void doTurn(Player currentPlayer) {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println(currentPlayer.getName() + ", choose row (1-3)");
            int xPos = scan.nextInt() - 1;
            System.out.println(currentPlayer.getName() + ", choose column (1-3)");
            int yPos = scan.nextInt() - 1;
            if (board[xPos][yPos].equals("-")) {
                board[xPos][yPos] = currentPlayer.getMark();
            }
            else {
                System.out.println("Position taken. Try again please.");
                doTurn(currentPlayer);
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid entry. Try again please.");
            doTurn(currentPlayer);
        }
    }

    protected void initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1, what is your name?");
        String p1Name = scanner.next();
        String p1Mark = "";
        while (!p1Mark.equalsIgnoreCase("X") && !p1Mark.equalsIgnoreCase("O")){
            System.out.print(p1Name + ", what mark do you wish to play with? (X or O)");
            p1Mark = scanner.next();
        }
        System.out.println("Player 2, what is your name?");
        String p2Name = scanner.next();
        Player player1 = new Player(p1Name, p1Mark, true);
        Player player2 = new Player(p2Name, player1, false);
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        System.out.println(player2.getName() + ", you play with " + player2.getMark()+ ".");
        player1.setOtherPlayer(player2);
    }

    protected void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "-";
            }
        }
    }

    protected void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean weHaveAWinner() {
        return checkForRows() || checkForColumns() || checkForDiagonals();
    }

    private boolean checkForDiagonals() {
        return checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[2][0], board[1][1], board[0][2]);
    }

    private boolean checkForColumns() {
        for (int i=0;i<3;i++){
            if (checkRowCol(board[i][0], board[i][1], board[i][2])){
                return true;
            }
        }
        return false;
    }

    private boolean checkForRows() {
        for (int i=0;i<3;i++){
            if (checkRowCol(board[0][i], board[1][i], board[2][i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkRowCol(String c1, String c2, String c3){
        return (!c1.equals("-")) && (c1.equals(c2)) && (c2.equals(c3));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }
    private void endGame() {
        if (weHaveAWinner()){
            Player currentPlayer = findCurrentPlayer();
            System.out.println(currentPlayer.getName() + ", you have won! Congratulations!");
        }
        if (isBoardFull()){
            System.out.println("Game has tied!");
        }
    }
}