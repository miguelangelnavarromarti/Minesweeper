package com.company;

import java.util.Scanner;

public class Interaction {

    private static boolean checkSelectRow (int answerRow, Board board) {
        if (answerRow >= 0 && answerRow < board.getNumRows()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkSelectColumn (int answerColumn, Board board) {
        if (answerColumn >= 0 && answerColumn < board.getNumColumns()) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] selectBox() {
        Scanner sc = new Scanner(System.in);

        int[] answer = new int[2];

        System.out.println("Selecciona la casella. \n" +
                "Fila: ");

        int answerRow = sc.nextInt();
        answer[0] = answerRow;

        //while (checkSelectColumn(answerRow, tablero))

        System.out.println("Columna: ");

        int answerColumn = sc.nextInt();
        answer[1] = answerColumn;

        return answer;
    }

    /*public static int[] selectAction() {
        Scanner sc = new Scanner(System.in);

        int[]
    }*/


}
