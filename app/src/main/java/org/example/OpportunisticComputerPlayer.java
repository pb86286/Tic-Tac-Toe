package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpportunisticComputerPlayer {
    private final Random random = new Random();

    public int selectMove(Board board, String player) {
        String opponent = player.equals("X") ? "O" : "X";
        List<Integer> availableMoves = board.getAvailableMoves();

        
        if (availableMoves.size() == 9) {
            int[] corners = {1, 3, 7, 9};
            return corners[random.nextInt(corners.length)];
        }

        
        if (availableMoves.size() == 8 && availableMoves.contains(5)) {
            return 5;
        }

        
        for (int move : availableMoves) {
            Board clone = board.clone();
            clone.makeMove(move, player);
            if (clone.checkWin(player)) {
                return move;
            }
        }

        
        for (int move : availableMoves) {
            Board clone = board.clone();
            clone.makeMove(move, opponent);
            if (clone.checkWin(opponent)) {
                return move;
            }
        }

        
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }
}