package com.company;

import java.util.ArrayList;

public class Board {
    private int high;
    private int large;
    private ArrayList<Box> board;

    public Board (int high, int large) {
        this.high = high;
        this.large = large;
    }

    public static ArrayList<Box> createBoard (int high, int large) {

        ArrayList<Box> board = new ArrayList<Box>();

        for (int i = 0; i < high; i++) {
            for (int j = 0; j < large; j++) {
                board.add(new Box(i, j, "·"));
            }
        }
        return board;
    }
}
