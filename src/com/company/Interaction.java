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
        int answerRow;
        int answerColumn;

        System.out.println("Selecciona la casella.");

        do {
            System.out.println("Fila:");
            while (!sc.hasNextInt()) {
                System.out.println("Això no es un número! Tria un número");
                sc.next();
            }
            answerRow = sc.nextInt();
        } while (answerRow <= 0);

        answer[0] = answerRow;

        do {
            System.out.println("Columna:");
            while (!sc.hasNextInt()) {
                System.out.println("Això no es un número! Tria un número");
                sc.next();
            }
            answerColumn = sc.nextInt();
        } while (answerColumn <= 0);

        answer[1] = answerColumn;

        return answer;
    }
}
