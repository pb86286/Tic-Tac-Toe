package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        GameLog gameLog = new GameLog();
        OpportunisticComputerPlayer ai = new OpportunisticComputerPlayer();

        System.out.println("Welcome to Tic-Tac-Toe!");

        do {
            System.out.println("\nWhat kind of game would you like to play?\n");
            System.out.println("1. Human vs. Human");
            System.out.println("2. Human vs. Computer");
            System.out.println("3. Computer vs. Human");
            System.out.print("\nWhat is your selection? ");

            int mode = Integer.parseInt(scanner.nextLine().trim());
            boolean vsComputer = (mode == 2 || mode == 3);
            String computerPlayer = null;
            if (vsComputer) {
                computerPlayer = (mode == 2) ? "O" : "X";
            }

            String currentPlayer = "X";
            String lastLoser = null;
            Board board = new Board();

            while (true) {
                board.display();
                if (vsComputer && currentPlayer.equals(computerPlayer)) {
                    int move = ai.selectMove(board, computerPlayer);
                    System.out.println("\nComputer selects: " + move);
                    board.makeMove(move, computerPlayer);
                } else {
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
                }

                if (board.checkWin(currentPlayer)) {
                    board.display();
                    System.out.println("\nPlayer " + currentPlayer + " wins!");
                    gameLog.recordWin(currentPlayer);
                    lastLoser = currentPlayer.equals("X") ? "O" : "X";
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