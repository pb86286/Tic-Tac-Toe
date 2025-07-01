package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        System.out.println("Welcome to Tic-Tac-Toe!");

        do {
            Board board = new Board();
            String currentPlayer = "X";

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
                    break;
                }

                if (board.isDraw()) {
                    board.display();
                    System.out.println("\nIt's a draw!");
                    break;
                }

                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }

            // Ask to play again
            while (true) {
                System.out.print("\nWould you like to play again (yes/no)? ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("yes")) {
                    playAgain = true;
                    break;
                } else if (answer.equals("no")) {
                    playAgain = false;
                    System.out.println("\nGoodbye!");
                    break;
                } else {
                    System.out.println("\nThat is not a valid entry!");
                }
            }

        } while (playAgain);

        scanner.close();
    }
}