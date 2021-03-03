package com.company;

import java.util.Random;

public class Board {
    private Box[][] board;

    public Board (int row, int column) {
        // HE DE CREAR ES TABLERO AMB UNA MATRIU DE BOXES

        /*for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j] = new Box(i, j);
            }
        }*/
    }


    /*private void putBomb() {

        int randomRow = new Random().nextInt(row + 1);
        int randomColumn = new Random().nextInt(this.column + 1);

        this.board[randomRow][randomColumn] = "0";

    }*/

    public void printBoard(int numberOfBombs) {

        /*for (int i = 0; i < numberOfBombs; i++) {
            putBomb();
        }*/

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j].getState() + " ");
            }
            System.out.println();
        }
    }
}
