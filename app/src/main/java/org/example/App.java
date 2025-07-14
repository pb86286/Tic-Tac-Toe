package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        GameLog gameLog = new GameLog();

        System.out.println("Welcome to Tic-Tac-Toe!");

        String currentPlayer = "X"; // track who goes first
        String lastLoser = null;    // track for loser-goes-first

        do {
            Board board = new Board();

            if (lastLoser != null) {
                currentPlayer = lastLoser;
                System.out.println("\nGreat! This time " + currentPlayer + " will go first!");
            }

            while (true) {
                board.display();
                System.out.print("\nWhat is your move? ");
                String input = scanner.nextLine().trim();

                if (!board.isValidInput(input)) {
                    System.out.println("\nThat is not a valid move! Try again.");
                    continue;
                }

                int move = Integer.parseInt(input);
                if (!board.makeMove(move, currentPlayer)) {
                    System.out.println("\nThat is not a valid move! Try again.");
                    continue;
                }

                if (board.checkWin(currentPlayer)) {
                    board.display();
                    System.out.println("\nPlayer " + currentPlayer + " wins!");
                    gameLog.recordWin(currentPlayer);
                    lastLoser = currentPlayer.equals("X") ? "O" : "X"; // loser goes first
                    break;
                }

                if (board.isDraw()) {
                    board.display();
                    System.out.println("\nIt's a draw!");
                    gameLog.recordTie();
                    lastLoser = null; 
                    break;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }

            gameLog.display();

            // Ask to play again
            while (true) {
                System.out.print("\nWould you like to play again (yes/no)? ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("yes")) {
                    playAgain = true;
                    break;
                } else if (answer.equals("no")) {
                    playAgain = false;
                    System.out.println("\nFinal Game Log:");
                    gameLog.display();
                    System.out.println("\nWriting the game log to disk as 'game.txt'...");
                    gameLog.saveToFile("game.txt");
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("\nThat is not a valid entry!");
                }
            }

        } while (playAgain);

        scanner.close();
    }
}