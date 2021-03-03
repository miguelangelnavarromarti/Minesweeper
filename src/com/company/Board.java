package com.company;

import java.util.ArrayList;

public class Board {
    private int row;
    private int column;
    private String[][] board;

    public Board (int row, int column) {
        this.row = row;
        this.column = column;
        this.board = new String[row][column];
    }

    private void createBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = "#";
            }
        }
    }

    public void printBoard() {

        createBoard();

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
