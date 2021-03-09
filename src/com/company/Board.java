package com.company;

import java.util.Random;

public class Board {
    private int numRows;
    private int numColumns;
    private Box[][] board;

    public Board (int numRows, int numColumns) {
        // HE DE CREAR ES TABLERO AMB UNA MATRIU DE BOXES

        this.board = new Box[numRows][numColumns];
        this.numRows = numRows;
        this.numColumns = numColumns;


        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Box();
            }
        }
    }

    private void createBomb() {

        int randomRow = new Random().nextInt(this.numRows);
        int randomColumn = new Random().nextInt(this.numColumns);

        if (!this.board[randomRow][randomColumn].getState().equals(BoxState.BOMB.getValue())) {
            this.board[randomRow][randomColumn].setState(BoxState.BOMB);
        } else {
            createBomb();
        }
    }

    private boolean checkNumBombs(int numberOfBombs) {

        if (numberOfBombs < (this.numRows * this.numColumns)){
            return true;
        } else {
            return false;
        }
    }

    public void printBoard(int numberOfBombs) {

        if(!checkNumBombs(numberOfBombs)) {
            System.out.println("No pots posar tantes bombes");
        }else {
            for (int i = 0; i < numberOfBombs; i++) {
                createBomb();
            }

            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board[i].length; j++) {
                    if (this.board[i][j].getState().equals(BoxState.BOMB.getValue())){
                        System.out.print(Color.RED + this.board[i][j].getState() + " " + Color.RESET);
                    } else {
                        System.out.print(this.board[i][j].getState() + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
