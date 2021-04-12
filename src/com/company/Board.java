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
            System.out.println("Primera fila no vàlida, ha de ser un número entre 1 i " + (this.getNumRows()-1) + " ambdos inclosos");
            selectFirstRow();
        }
    }

    private void checkFirstColumn(int answerColumn){
        if (answerColumn >= 0 && answerColumn < this.getNumColumns()) {
            return;
        } else {
            System.out.println("Primera columna no vàlida, ha de ser un número entre 1 i " + (this.getNumColumns()-1) + " ambdos inclosos");
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
            //this.board[randomRow][randomColumn].setState(BoxRepresentation.BOMB);
            this.board[randomRow][randomColumn].putBomb(true);
        } else {
            createBomb(numberOfBombs-1);
        }
    }

    public void printBoard() {
        System.out.println("    0    1    2    3    4    5    6    7");
        for (int i = 0; i < this.board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j].getState());
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
        /*Box upLeft = this.board[firstBoxPlace[0]-1][firstBoxPlace[1]-1];
        Box upCenter = this.board[firstBoxPlace[0]-1][firstBoxPlace[1]];
        Box upRight = this.board[firstBoxPlace[0]-1][firstBoxPlace[1]+1];
        Box left = this.board[firstBoxPlace[0]][firstBoxPlace[1]-1];
        Box right = this.board[firstBoxPlace[0]][firstBoxPlace[1]+1];
        Box downLeft = this.board[firstBoxPlace[0]+1][firstBoxPlace[1]-1];
        Box downCenter = this.board[firstBoxPlace[0]+1][firstBoxPlace[1]];
        Box downRight = this.board[firstBoxPlace[0]+1][firstBoxPlace[1]+1];*/

        boolean cornerUpLeft = (firstBoxPlace[0] == 0 && firstBoxPlace[1] == 0);
        boolean borderLeft = (firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] == 0);
        boolean cornerDownLeft = (firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] == 0);
        boolean borderUp = (firstBoxPlace[0] == 0 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1);
        boolean center = (firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1);
        boolean borderDown = (firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1);
        boolean cornerUpRight = (firstBoxPlace[0] == 0 && firstBoxPlace[1] == this.numColumns-1);
        boolean borderRight = (firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] == this.numColumns-1);
        boolean cornerDownRight = (firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] == this.numColumns-1);

        if /* 1 */ (cornerUpLeft) {
            Box right = this.board[firstBoxPlace[0]][firstBoxPlace[1] + 1];
            Box downCenter = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1]];
            Box downRight = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] + 1];

            right.cover(false);
            downCenter.cover(false);
            downRight.cover(false);
        } else if /* 2 */ (borderLeft) {
            Box upCenter = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1]];
            Box upRight = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] + 1];
            Box right = this.board[firstBoxPlace[0]][firstBoxPlace[1] + 1];
            Box downCenter = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1]];
            Box downRight = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] + 1];

            upCenter.cover(false);
            upRight.cover(false);
            right.cover(false);
            downCenter.cover(false);
            downRight.cover(false);
        } else if /* 3 */ (cornerDownLeft) {
            Box upCenter = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1]];
            Box upRight = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] + 1];
            Box right = this.board[firstBoxPlace[0]][firstBoxPlace[1] + 1];

            upCenter.cover(false);
            upRight.cover(false);
            right.cover(false);
        } else if /* 4 */ (borderUp) {
            Box left = this.board[firstBoxPlace[0]][firstBoxPlace[1] - 1];
            Box right = this.board[firstBoxPlace[0]][firstBoxPlace[1] + 1];
            Box downLeft = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] - 1];
            Box downCenter = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1]];
            Box downRight = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] + 1];

            left.cover(false);
            downLeft.cover(false);
            downCenter.cover(false);
            downRight.cover(false);
            right.cover(false);
        } else if /* 5 */ (center) {
            Box upLeft = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] - 1];
            Box upCenter = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1]];
            Box upRight = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] + 1];
            Box left = this.board[firstBoxPlace[0]][firstBoxPlace[1] - 1];
            Box right = this.board[firstBoxPlace[0]][firstBoxPlace[1] + 1];
            Box downLeft = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] - 1];
            Box downCenter = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1]];
            Box downRight = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] + 1];

            upLeft.cover(false);
            upCenter.cover(false);
            upRight.cover(false);
            left.cover(false);
            right.cover(false);
            downLeft.cover(false);
            downCenter.cover(false);
            downRight.cover(false);
        } else if /* 6 */ (borderDown) {
            Box upLeft = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] - 1];
            Box upCenter = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1]];
            Box upRight = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] + 1];
            Box left = this.board[firstBoxPlace[0]][firstBoxPlace[1] - 1];
            Box right = this.board[firstBoxPlace[0]][firstBoxPlace[1] + 1];

            upLeft.cover(false);
            upCenter.cover(false);
            upRight.cover(false);
            left.cover(false);
            right.cover(false);
        } else if /* 7 */ (cornerUpRight) {
            Box left = this.board[firstBoxPlace[0]][firstBoxPlace[1] - 1];
            Box downLeft = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] - 1];
            Box downCenter = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1]];

            left.cover(false);
            downLeft.cover(false);
            downCenter.cover(false);
        } else if /* 8 */ (borderRight) {
            Box upLeft = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] - 1];
            Box upCenter = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1]];
            Box left = this.board[firstBoxPlace[0]][firstBoxPlace[1] - 1];
            Box downLeft = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1] - 1];
            Box downCenter = this.board[firstBoxPlace[0] + 1][firstBoxPlace[1]];

            upLeft.cover(false);
            upCenter.cover(false);
            left.cover(false);
            downLeft.cover(false);
            downCenter.cover(false);
        } else if /* 9 */ (cornerDownRight) {
            Box upLeft = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1] - 1];
            Box upCenter = this.board[firstBoxPlace[0] - 1][firstBoxPlace[1]];
            Box left = this.board[firstBoxPlace[0]][firstBoxPlace[1] - 1];

            upLeft.cover(false);
            upCenter.cover(false);
            left.cover(false);
        }
    }

    private void uncoverAroundZero(int[] boxWithZero) {
        /*Box upLeft = this.board[boxWithZero[0]-1][boxWithZero[1]-1];
        Box upCenter = this.board[boxWithZero[0]-1][boxWithZero[1]];
        Box upRight = this.board[boxWithZero[0]-1][boxWithZero[1]+1];
        Box left = this.board[boxWithZero[0]][boxWithZero[1]-1];
        Box right = this.board[boxWithZero[0]][boxWithZero[1]+1];
        Box downLeft = this.board[boxWithZero[0]+1][boxWithZero[1]-1];
        Box downCenter = this.board[boxWithZero[0]+1][boxWithZero[1]];
        Box downRight = this.board[boxWithZero[0]+1][boxWithZero[1]+1];*/

        boolean cornerUpLeft = boxWithZero[0] == 0 && boxWithZero[1] == 0;
        boolean borderLeft = boxWithZero[0] > 0 && boxWithZero[0] < this.numRows-1 && boxWithZero[1] == 0;
        boolean cornerDownLeft = boxWithZero[0] == this.numRows-1 && boxWithZero[1] == 0;
        boolean borderUp = boxWithZero[0] == 0 && boxWithZero[1] > 0 && boxWithZero[1] < this.numColumns-1;
        boolean center = boxWithZero[0] > 0 && boxWithZero[0] < this.numRows-1 && boxWithZero[1] > 0 && boxWithZero[1] < this.numColumns-1;
        boolean borderDown = boxWithZero[0] == this.numRows-1 && boxWithZero[1] > 0 && boxWithZero[1] < this.numColumns-1;
        boolean cornerUpRight = boxWithZero[0] == 0 && boxWithZero[1] == this.numColumns-1;
        boolean borderRight = boxWithZero[0] > 0 && boxWithZero[0] < this.numRows-1 && boxWithZero[1] == this.numColumns-1;
        boolean cornerDownRight = boxWithZero[0] > 0 && boxWithZero[0] < this.numRows-1 && boxWithZero[1] == this.numColumns-1;

        if (this.board[boxWithZero[0]][boxWithZero[1]].getBombsAround() == 0) {
            if /* 1 */(cornerUpLeft) {
                Box right = this.board[boxWithZero[0]][boxWithZero[1]+1];
                Box downCenter = this.board[boxWithZero[0]+1][boxWithZero[1]];
                Box downRight = this.board[boxWithZero[0]+1][boxWithZero[1]+1];

                right.cover(false);
                downCenter.cover(false);
                downRight.cover(false);
            } else if /* 2 */(borderLeft){
                Box upCenter = this.board[boxWithZero[0]-1][boxWithZero[1]];
                Box upRight = this.board[boxWithZero[0]-1][boxWithZero[1]+1];
                Box right = this.board[boxWithZero[0]][boxWithZero[1]+1];
                Box downCenter = this.board[boxWithZero[0]+1][boxWithZero[1]];
                Box downRight = this.board[boxWithZero[0]+1][boxWithZero[1]+1];

                upCenter.cover(false);
                upRight.cover(false);
                right.cover(false);
                downCenter.cover(false);
                downRight.cover(false);
            } else if /* 3 */(cornerDownLeft) {
                Box upCenter = this.board[boxWithZero[0]-1][boxWithZero[1]];
                Box upRight = this.board[boxWithZero[0]-1][boxWithZero[1]+1];
                Box right = this.board[boxWithZero[0]][boxWithZero[1]+1];

                upCenter.cover(false);
                upRight.cover(false);
                right.cover(false);
            } else if /* 4 */(borderUp) {
                Box left = this.board[boxWithZero[0]][boxWithZero[1]-1];
                Box right = this.board[boxWithZero[0]][boxWithZero[1]+1];
                Box downLeft = this.board[boxWithZero[0]+1][boxWithZero[1]-1];
                Box downCenter = this.board[boxWithZero[0]+1][boxWithZero[1]];
                Box downRight = this.board[boxWithZero[0]+1][boxWithZero[1]+1];

                left.cover(false);
                right.cover(false);
                downLeft.cover(false);
                downCenter.cover(false);
                downRight.cover(false);
            } else if /* 5 */(center) {
                Box upLeft = this.board[boxWithZero[0]-1][boxWithZero[1]-1];
                Box upCenter = this.board[boxWithZero[0]-1][boxWithZero[1]];
                Box upRight = this.board[boxWithZero[0]-1][boxWithZero[1]+1];
                Box left = this.board[boxWithZero[0]][boxWithZero[1]-1];
                Box right = this.board[boxWithZero[0]][boxWithZero[1]+1];
                Box downLeft = this.board[boxWithZero[0]+1][boxWithZero[1]-1];
                Box downCenter = this.board[boxWithZero[0]+1][boxWithZero[1]];
                Box downRight = this.board[boxWithZero[0]+1][boxWithZero[1]+1];

                upLeft.cover(false);
                upCenter.cover(false);
                upRight.cover(false);
                left.cover(false);
                right.cover(false);
                downLeft.cover(false);
                downCenter.cover(false);
                downRight.cover(false);
            } else if /* 6 */(borderDown) {
                Box upLeft = this.board[boxWithZero[0]-1][boxWithZero[1]-1];
                Box upCenter = this.board[boxWithZero[0]-1][boxWithZero[1]];
                Box upRight = this.board[boxWithZero[0]-1][boxWithZero[1]+1];
                Box left = this.board[boxWithZero[0]][boxWithZero[1]-1];
                Box right = this.board[boxWithZero[0]][boxWithZero[1]+1];

                upLeft.cover(false);
                upCenter.cover(false);
                upRight.cover(false);
                left.cover(false);
                right.cover(false);
            } else if /* 7 */(cornerUpRight) {
                Box left = this.board[boxWithZero[0]][boxWithZero[1]-1];
                Box downLeft = this.board[boxWithZero[0]+1][boxWithZero[1]-1];
                Box downCenter = this.board[boxWithZero[0]+1][boxWithZero[1]];

                left.cover(false);
                downLeft.cover(false);
                downCenter.cover(false);
            } else if /* 8 */(borderRight) {
                Box upLeft = this.board[boxWithZero[0]-1][boxWithZero[1]-1];
                Box upCenter = this.board[boxWithZero[0]-1][boxWithZero[1]];
                Box left = this.board[boxWithZero[0]][boxWithZero[1]-1];
                Box downLeft = this.board[boxWithZero[0]+1][boxWithZero[1]-1];
                Box downCenter = this.board[boxWithZero[0]+1][boxWithZero[1]];

                upLeft.cover(false);
                upCenter.cover(false);
                left.cover(false);
                downLeft.cover(false);
                downCenter.cover(false);
            } else if /* 9 */(cornerDownRight) {
                Box upLeft = this.board[boxWithZero[0]-1][boxWithZero[1]-1];
                Box upCenter = this.board[boxWithZero[0]-1][boxWithZero[1]];
                Box left = this.board[boxWithZero[0]][boxWithZero[1]-1];

                upLeft.cover(false);
                upCenter.cover(false);
                left.cover(false);
            }
        }
    }

    private void uncoverAllAroundZero(int trys) {
        if (trys > 0) {
            for (int row = 0; row < this.board.length; row++) {
                for( int column = 0; column < this.board[row].length; column++) {
                    if(!this.board[row][column].isCovered()) {
                        int[] position = {row, column};
                        uncoverAroundZero(position);
                    }
                }
            }
            uncoverAllAroundZero(trys-1);
        } else {
            return;
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

        uncoverAllAroundZero(this.numRows);

        printBoard();
    }

    private int checkRows (int rows) {
        Scanner sc = new Scanner(System.in);

        if (rows <= 0 && rows > this.numRows-1) {
            System.out.println("Has d'introduir un número de files superior o igual a 0 e inferior a " + (this.numRows-1));
            int answer = sc.nextInt();
            return checkRows(answer);
        }
        return rows;
    }

    private int checkColumns (int column) {
        Scanner sc = new Scanner(System.in);

        if (column <= 0 && column > this.numColumns-1) {
            System.out.println("Has d'introduir un número de columnes superior o igual a 0 e inferior a " + (this.numColumns-1));
            int answer = sc.nextInt();
            return checkColumns(answer);
        }
        return column;
    }

    private int [] checkBox (int [] box) {
        if (!this.board[box[0]][box[1]].isCovered()) {
            System.out.println("Has fet una acció amb una casella destapada, torna a introduir la fila i la columna");
            nextMove();
        }
        return box;
    }

    private char checkAction (char action) {
        Scanner sc = new Scanner(System.in);

        if (action != 'd' && action != 'b') {
            System.out.println("Has d'introduir la lletra 'd' per destapar o la lletra 'b' per posar una bandera (ambdues sense cometes)");
            char answer = sc.next().charAt(0);
            return checkAction(answer);
        }
        return action;
    }

    public void nextMove() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Dim la fila de la casella que vols modificar:");
        int row = sc.nextInt();
        int checkedRow = checkRows(row);

        System.out.println("Dim la columna de la casella que vols modificar");
        int column = sc.nextInt();
        int checkedColumn = checkColumns(column);

        int [] box = {checkedRow, checkedColumn};
        int [] checkedBox = checkBox(box);

        System.out.println("Dim si vols destapar (d) o posar una bandera (b)");
        char action = sc.next().charAt(0);
        char checkedAction = checkAction(action);

        if (checkedAction == 'd') {
            this.board[checkedBox[0]][checkedBox[1]].cover(false);
            if (this.board[checkedBox[0]][checkedBox[1]].hasBomb()) {
                printBoard();
                System.out.println("Has perdut! LOSER");
            } else {
                /*if (this.board[row][column].getBombsAround() == 0) {
                    this.board[row][column].setState(BoxRepresentation.ZERO);
                }
                if (this.board[row][column].getBombsAround() == 1) {
                    this.board[row][column].setState(BoxRepresentation.ONE);
                }
                if (this.board[row][column].getBombsAround() == 2) {
                    this.board[row][column].setState(BoxRepresentation.TWO);
                }
                if (this.board[row][column].getBombsAround() == 3) {
                    this.board[row][column].setState(BoxRepresentation.THREE);
                }
                if (this.board[row][column].getBombsAround() == 4) {
                    this.board[row][column].setState(BoxRepresentation.FOUR);
                }
                if (this.board[row][column].getBombsAround() == 5) {
                    this.board[row][column].setState(BoxRepresentation.FIVE);
                }
                if (this.board[row][column].getBombsAround() == 6) {
                    this.board[row][column].setState(BoxRepresentation.SIX);
                }
                if (this.board[row][column].getBombsAround() == 7) {
                    this.board[row][column].setState(BoxRepresentation.SEVEN);
                }
                if (this.board[row][column].getBombsAround() == 8) {
                    this.board[row][column].setState(BoxRepresentation.EIGHT);
                }*/
                printBoard();
                nextMove();
            }
        }
        if (checkedAction == 'b'){
            this.board[checkedBox[0]][checkedBox[1]].putFlag(true);
            printBoard();
            nextMove();
        }
    }
}
