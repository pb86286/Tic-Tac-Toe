package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    private int xWins;
    private int oWins;
    private int ties;

    public void recordWin(String player) {
        if (player.equals("X")) {
            xWins++;
        } else if (player.equals("O")) {
            oWins++;
        }
    }

    public void recordTie() {
        ties++;
    }

    public void display() {
        System.out.println("\nThe current log is:\n");
        System.out.printf("Player X Wins   %d%n", xWins);
        System.out.printf("Player O Wins   %d%n", oWins);
        System.out.printf("Ties            %d%n", ties);
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Game Log:\n\n");
            writer.write(String.format("Player X Wins   %d%n", xWins));
            writer.write(String.format("Player O Wins   %d%n", oWins));
            writer.write(String.format("Ties            %d%n", ties));
        } catch (IOException e) {
            System.out.println("Error writing game log to file: " + e.getMessage());
        }
    }
}