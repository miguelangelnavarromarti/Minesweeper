package com.company;

import java.util.Random;
import java.util.Scanner;

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

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    private void checkFirstRow(int answerRow){
        if (answerRow >= 0 && answerRow < this.getNumRows()) {
            return;
        } else {
            System.out.println("Primera fila no vàlida, ha de ser un número entre 1 i " + this.getNumRows() + " ambdos inclosos");
            selectFirstRow();
        }
    }

    private void checkFirstColumn(int answerColumn){
        if (answerColumn >= 0 && answerColumn < this.getNumColumns()) {
            return;
        } else {
            System.out.println("Primera columna no vàlida, ha de ser un número entre 1 i " + this.getNumColumns() + " ambdos inclosos");
            selectFirstColumn();
        }
    }

    private int selectFirstRow() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona la primera fila: ");
        int answerRow = sc.nextInt();
        checkFirstRow(answerRow);
        return answerRow;
    }

    private int selectFirstColumn() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecciona la primera columna: ");
        int answerColumn = sc.nextInt();
        checkFirstColumn(answerColumn);
        return answerColumn;
    }

    private void createBomb() {

        int randomRow = new Random().nextInt(this.numRows);
        int randomColumn = new Random().nextInt(this.numColumns);

        if (!this.board[randomRow][randomColumn].hasBomb() && this.board[randomRow][randomColumn].isCovered()){
            this.board[randomRow][randomColumn].setState(BoxRepresentation.BOMB);
        } else {
            createBomb();
        }
    }

    public void printBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].hasBomb()){
                    System.out.print(Color.RED + this.board[i][j].getState() + " " + Color.RESET);
                } else {
                    System.out.print(this.board[i][j].getState() + " ");
                }
            }
            System.out.println();
        }
    }

    public int[] selectFirstBox(){

        int[] firstBox = new int[2];

        firstBox[0] = selectFirstRow();
        firstBox[1] = selectFirstColumn();

        return firstBox;
    }

    //DESTAPAR LES 8 CASELLES VOLTANTS DE LA SELECCIONADA

    private void uncoverFirstBox(int[] firstBoxPlace) {
        this.board[firstBoxPlace[0]][firstBoxPlace[1]].setState(BoxRepresentation.EMPTY);
    }

    /*private void uncoverAroundFirstBox(int[] firstBoxPlace) {

    }*/

    public void printBoardFirstMove(int numberOfBombs, int[] firstBox) {
        /**
         * destapar primera casella
         * distribuir mines
         * completar destapar
         * imprimir tauler
         */

        uncoverFirstBox(firstBox);

        for (int i = 0; i < numberOfBombs; i++) {
            createBomb();
        }

        printBoard();
    }
}
