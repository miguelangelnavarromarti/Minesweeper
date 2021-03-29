package com.company;

import java.util.ArrayList;
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

    private void createBomb(int numberOfBombs) {

        int randomRow = new Random().nextInt(this.numRows);
        int randomColumn = new Random().nextInt(this.numColumns);

        if (numberOfBombs == 0){
            return;
        }

        if (!this.board[randomRow][randomColumn].hasBomb() && this.board[randomRow][randomColumn].isCovered()) {
            this.board[randomRow][randomColumn].setState(BoxRepresentation.BOMB);
            this.board[randomRow][randomColumn].putBomb(true);
        } else {
            createBomb(numberOfBombs-1);
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

    private void uncoverFirstBox(int[] firstBoxPlace) {
        this.board[firstBoxPlace[0]][firstBoxPlace[1]].cover(false);
    }

    /**
     * Taulell de condicions depenent de la posicio de la primera casella destapada
     * 1 - 4 - 7
     * - - - - -
     * 2 - 5 - 8
     * - - - - -
     * 3 - 6 - 9
     */

    private void uncoverAroundFirstBox(int[] firstBoxPlace) {

        if /* 1 */(firstBoxPlace[0] == 0 && firstBoxPlace[1] == 0) {
            this.board[firstBoxPlace[0]][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]+1].cover(false);
        } else if /* 2 */(firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] == 0){
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]+1].cover(false);
        } else if /* 3 */(firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] == 0) {
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]+1].cover(false);
        } else if /* 4 */(firstBoxPlace[0] == 0 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1) {
            this.board[firstBoxPlace[0]][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]+1].cover(false);
        } else if /* 5 */(firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1) {
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]+1].cover(false);
        } else if /* 6 */(firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1) {
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]+1].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]+1].cover(false);
        } else if /* 7 */(firstBoxPlace[0] == 0 && firstBoxPlace[1] == this.numColumns-1) {
            this.board[firstBoxPlace[0]][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]].cover(false);
        } else if /* 8 */(firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] == this.numColumns-1) {
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]+1][firstBoxPlace[1]].cover(false);
        } else if /* 9 */(firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] == this.numColumns-1) {
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]-1].cover(false);
            this.board[firstBoxPlace[0]-1][firstBoxPlace[1]].cover(false);
            this.board[firstBoxPlace[0]][firstBoxPlace[1]-1].cover(false);
        }
    }

    private void checkBombsAround(int[] boxPlace) {

        int counter = 0;

        if (!this.board[boxPlace[0]][boxPlace[1]].hasBomb()) {
            if /* 1 */(boxPlace[0] == 0 && boxPlace[1] == 0) {
                if (this.board[boxPlace[0]][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if(this.board[boxPlace[0]+1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 2 */(boxPlace[0] > 0 && boxPlace[0] < this.numRows-1 && boxPlace[1] == 0){
                if (this.board[boxPlace[0]-1][boxPlace[1]].hasBomb()){
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 3 */(boxPlace[0] == this.numRows-1 && boxPlace[1] == 0) {
                if (this.board[boxPlace[0]-1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 4 */(boxPlace[0] == 0 && boxPlace[1] > 0 && boxPlace[1] < this.numColumns-1) {
                if (this.board[boxPlace[0]][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 5 */(boxPlace[0] > 0 && boxPlace[0] < this.numRows-1 && boxPlace[1] > 0 && boxPlace[1] < this.numColumns-1) {
                if (this.board[boxPlace[0]-1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 6 */(boxPlace[0] == this.numRows-1 && boxPlace[1] > 0 && boxPlace[1] < this.numColumns-1) {
                if (this.board[boxPlace[0]-1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]+1].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 7 */(boxPlace[0] == 0 && boxPlace[1] == this.numColumns-1) {
                if (this.board[boxPlace[0]][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 8 */(boxPlace[0] > 0 && boxPlace[0] < this.numRows-1 && boxPlace[1] == this.numColumns-1) {
                if (this.board[boxPlace[0]-1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]+1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);

            } else if /* 9 */(boxPlace[0] == this.numRows-1 && boxPlace[1] == this.numColumns-1) {
                if (this.board[boxPlace[0]-1][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]-1][boxPlace[1]].hasBomb()) {
                    counter++;
                }
                if (this.board[boxPlace[0]][boxPlace[1]-1].hasBomb()) {
                    counter++;
                }
                this.board[boxPlace[0]][boxPlace[1]].setBombsAround(counter);
            }
        }
    }

    public void printBoardFirstMove(int numberOfBombs, int[] firstBox) {
        /**
         * destapar primera casella
         * distribuir mines
         * completar destapar
         * imprimir tauler
         */

        uncoverFirstBox(firstBox);
        uncoverAroundFirstBox(firstBox);

        for (int i = 0; i < numberOfBombs; i++) {
            createBomb(numberOfBombs);
        }

        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[row].length; column++) {
                int[] placeBox = {row, column};
                checkBombsAround(placeBox);
            }
        }

        printBoard();
    }

    // FUNCIONALITAT POSAR BOMBES I DIR SES BOMBES QUE HI HA AL VOLTANT, CONTROLADA
    /**
     * Tapar taulell
     * Demanar següent Box i quin tipo d'acció
     */
}
