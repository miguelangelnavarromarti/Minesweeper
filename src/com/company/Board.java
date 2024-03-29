package com.company;

import com.company.Box;
import com.company.Play;

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

    private int selectFirstRow() {

        int answerRow;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nSelecciona la primera fila ha de ser un número entre 1 i " + (this.getNumRows()-1) + " ambdos inclosos:");
            while (!sc.hasNextInt()) {
                System.out.println("Això no es un número! Tria un número, ha de ser un número entre 0 i " + (this.getNumRows()-1) + " ambdos inclosos");
                sc.nextLine();
            }
            answerRow = sc.nextInt();
        } while (answerRow < 0 || answerRow > this.numRows);
        return answerRow;
    }

    private int selectFirstColumn() {
        Scanner sc = new Scanner(System.in);

        int answerColumn;

        do {
            System.out.println("Selecciona la primera columna, ha de ser un número entre 1 i " + (this.getNumColumns()-1) + " ambdos inclosos:");
            while (!sc.hasNextInt()) {
                System.out.println("Això no es un número! Tria un número, ha de ser un número entre 1 i " + (this.getNumColumns()-1) + " ambdos inclosos");
                sc.next();
            }
            answerColumn = sc.nextInt();
        } while (answerColumn < 0 || answerColumn > this.numColumns);
        return answerColumn;
    }

    private void createBomb(int numberOfBombs) {

        int randomRow = new Random().nextInt(this.numRows);
        int randomColumn = new Random().nextInt(this.numColumns);

        if (numberOfBombs == 0){
            return;
        }

        if (!this.board[randomRow][randomColumn].hasBomb() && this.board[randomRow][randomColumn].isCovered()) {
            this.board[randomRow][randomColumn].putBomb(true);
            createBomb(numberOfBombs-1);
        } else {
            createBomb(numberOfBombs);
        }
    }

    public void printBoard() {

        System.out.println("");

        for (int i = 0; i < this.numColumns; i++) {
            if (i == 0) {
                System.out.print("     " + i + "  ");
            } else {
                if (i < 10) {
                    System.out.print("  " + i + "  ");
                } else {
                    System.out.print("  " + i + " ");
                }
            }
        }

        System.out.println("");

        for (int i = 0; i < this.board.length; i++) {
            if (i < 10) {
                System.out.print(" " + i + " ");
            } else {
                System.out.print(i + " ");
            }
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j].getState());
            }
            System.out.println();
        }
        System.out.println("\nTe queden " + flagsLeft() + " banderes per col·locar");
    }

    private int numberOfBombs () {

        int bombs = 0;

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].hasBomb()) {
                    bombs++;
                }
            }
        }
        return bombs;
    }

    private int flagsLeft() {

        int flagsLeft = numberOfBombs();

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].hasFlag()) {
                    flagsLeft--;
                }
            }
        }
        return flagsLeft;
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

        boolean cornerUpLeft = (firstBoxPlace[0] == 0 && firstBoxPlace[1] == 0);
        boolean borderLeft = (firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] == 0);
        boolean cornerDownLeft = (firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] == 0);
        boolean borderUp = (firstBoxPlace[0] == 0 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1);
        boolean center = (firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1);
        boolean borderDown = (firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] > 0 && firstBoxPlace[1] < this.numColumns-1);
        boolean cornerUpRight = (firstBoxPlace[0] == 0 && firstBoxPlace[1] == this.numColumns-1);
        boolean borderRight = (firstBoxPlace[0] > 0 && firstBoxPlace[0] < this.numRows-1 && firstBoxPlace[1] == this.numColumns-1);
        boolean cornerDownRight = (firstBoxPlace[0] == this.numRows-1 && firstBoxPlace[1] == this.numColumns-1);

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

        boolean cornerUpLeft = boxWithZero[0] == 0 && boxWithZero[1] == 0;
        boolean borderLeft = boxWithZero[0] > 0 && boxWithZero[0] < this.numRows-1 && boxWithZero[1] == 0;
        boolean cornerDownLeft = boxWithZero[0] == this.numRows-1 && boxWithZero[1] == 0;
        boolean borderUp = boxWithZero[0] == 0 && boxWithZero[1] > 0 && boxWithZero[1] < this.numColumns-1;
        boolean center = boxWithZero[0] > 0 && boxWithZero[0] < this.numRows-1 && boxWithZero[1] > 0 && boxWithZero[1] < this.numColumns-1;
        boolean borderDown = boxWithZero[0] == this.numRows-1 && boxWithZero[1] > 0 && boxWithZero[1] < this.numColumns-1;
        boolean cornerUpRight = boxWithZero[0] == 0 && boxWithZero[1] == this.numColumns-1;
        boolean borderRight = boxWithZero[0] > 0 && boxWithZero[0] < this.numRows-1 && boxWithZero[1] == this.numColumns-1;
        boolean cornerDownRight = boxWithZero[0] == this.numRows-1 && boxWithZero[1] == this.numColumns-1;

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
        while (trys > 0) {
            for (int row = 0; row < this.board.length; row++) {
                for(int column = 0; column < this.board[row].length; column++) {
                    if(!this.board[row][column].isCovered() && this.board[row][column].getBombsAround() == 0) {
                        int[] position = {row, column};
                        uncoverAroundZero(position);
                    }
                }
            }
            trys--;
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

        createBomb(numberOfBombs);

        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[row].length; column++) {
                int[] placeBox = {row, column};
                checkBombsAround(placeBox);
            }
        }

        uncoverAllAroundZero(this.numRows);

        printBoard();
    }

    private int [] checkBox (int [] box) {
        if (!this.board[box[0]][box[1]].isCovered()) {
            System.out.println("Has intentat fer una acció amb una casella destapada, torna a introduir la fila i la columna");
            nextMove();
        }
        return box;
    }

    private char checkAction (char action) {
        Scanner sc = new Scanner(System.in);

        if (action != 'd' && action != 'b' && action != 'D' && action != 'B' && sc.next().length() == 1) {
            System.out.println("Has d'introduir la lletra 'd' per destapar o la lletra 'b' per posar una bandera (ambdues sense cometes)");
            char answer = sc.next().charAt(0);
            return checkAction(answer);
        }
        return action;
    }

    public boolean checkWin () {

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].isCovered() && !this.board[i][j].hasBomb()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void nextMove() {

        int row;
        int column;

        if (checkWin()) {
            System.out.println("\n" + "Tu guanyes! " + Color.GREEN + "WINNER" + Color.RESET);
            return;
        } else {
            Scanner sc = new Scanner(System.in);

            do {
                System.out.println("\nDim la fila de la casella que vols modificar:");
                while (!sc.hasNextInt()) {
                    System.out.println("Això no es un número! Tria un número");
                    sc.next();
                }
                row = sc.nextInt();
            } while (row < 0 || row > this.numRows);

            do {
                System.out.println("Dim la columna de la casella que vols modificar:");
                while (!sc.hasNextInt()) {
                    System.out.println("Això no es un número! Tria un número");
                    sc.next();
                }
                column = sc.nextInt();
            } while (column < 0 || column > this.numColumns);

            int [] box = {row, column};
            int [] checkedBox = checkBox(box);

            System.out.println("Dim si vols destapar (d) o posar una bandera (b)");
            char action = sc.next().charAt(0);
            System.out.println("Has triat '" + action + "'");
            char checkedAction = checkAction(action);

            if (checkedAction == 'd' || checkedAction == 'D') {
                this.board[checkedBox[0]][checkedBox[1]].cover(false);
                if (this.board[checkedBox[0]][checkedBox[1]].hasBomb()) {
                    printBoard();
                    System.out.println("\n" + "Has perdut! " + Color.RED + "LOSER" + Color.RESET);
                    return;
                } else {
                    if (this.board[checkedBox[0]][checkedBox[1]].getBombsAround() == 0) {
                        uncoverAroundZero(checkedBox);
                        uncoverAllAroundZero(this.numRows);
                    }
                    printBoard();
                    nextMove();
                }
            }
            if (checkedAction == 'b' || checkedAction == 'B'){
                if (!this.board[checkedBox[0]][checkedBox[1]].hasFlag()) {
                    if (flagsLeft() == 0) {
                        System.out.println("No pots posar més banderes.");
                    } else {
                        this.board[checkedBox[0]][checkedBox[1]].putFlag(true);
                    }
                } else {
                    this.board[checkedBox[0]][checkedBox[1]].putFlag(false);
                }
                printBoard();
                nextMove();
            }
        }
    }
}
