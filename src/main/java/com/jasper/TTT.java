package com.jasper;

import org.jetbrains.annotations.NotNull;
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

    protected void doTurn(@NotNull Player currentPlayer) {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println(currentPlayer.getName() + ", choose row (1-3)");
            int xPos = scan.nextInt() - 1;
            System.out.println(currentPlayer.getName() + ", choose column (1-3)");
            int yPos = scan.nextInt() - 1;
            if ("-".equals(board[xPos][yPos])) {
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

        String p1Name = createNewPlayer();
        String p2Name = createNewPlayer();
        String p1Mark = createMarkForPlayer(p1Name);
        Player player1 = new Player(p1Name, p1Mark, true);
        Player player2 = new Player(p2Name, player1, false);
        System.out.println(player2.getName() + ", you play with " + player2.getMark()+ ".");
        player1.setOtherPlayer(player2);
        addPlayersToGame(player1, player2);
    }

    private void addPlayersToGame(Player player1, Player player2) {
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

    }

    private String createMarkForPlayer(String player) {
        Scanner scanner = new Scanner(System.in);
        String p1Mark = "";
        while (!"X".equalsIgnoreCase(p1Mark) && !"O".equalsIgnoreCase(p1Mark)){
            System.out.print(player + ", what mark do you wish to play with? (X or O)");
            p1Mark = scanner.next();
        }
        return p1Mark;
    }

    protected String createNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player, what is your name?");
        return scanner.next();
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
        return (!"-".equals(c1)) && (c1.equals(c2)) && (c2.equals(c3));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ("-".equals(board[i][j])) {
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