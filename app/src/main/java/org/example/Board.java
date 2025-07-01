package org.example;

public class Board {
    private final String[] cells;

    public Board() {
        cells = new String[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = String.valueOf(i + 1);
        }
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("  %s  |  %s  |  %s%n", cells[i], cells[i + 1], cells[i + 2]);
            if (i < 6) System.out.println("-----+-----+-----");
        }
    }

    public boolean isValidInput(String input) {
        try {
            int val = Integer.parseInt(input);
            return val >= 1 && val <= 9;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean makeMove(int position, String player) {
        int index = position - 1;
        if (!cells[index].equals("X") && !cells[index].equals("O")) {
            cells[index] = player;
            return true;
        }
        return false;
    }

    public boolean checkWin(String player) {
        int[][] winCombos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
            {0, 4, 8}, {2, 4, 6}             
        };

        for (int[] combo : winCombos) {
            if (cells[combo[0]].equals(player) &&
                cells[combo[1]].equals(player) &&
                cells[combo[2]].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (String cell : cells) {
            if (!cell.equals("X") && !cell.equals("O")) {
                return false;
            }
        }
        return true;
    }
}