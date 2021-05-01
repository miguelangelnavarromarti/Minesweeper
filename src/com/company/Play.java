package com.company;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Play {

    private static int checkRows (int rows) {
        Scanner sc = new Scanner(System.in);

        if (rows < 4) {
            System.out.println("Has d'introduir un número de files superior o igual a 4");
            int answer = sc.nextInt();
            return checkRows(answer);
        }
        return rows;
    }

    private static int checkColumns (int column) {
        Scanner sc = new Scanner(System.in);

        if (column < 4) {
            System.out.println("Has d'introduir un número de columnes superior o igual a 4");
            int answer = sc.nextInt();
            return checkColumns(answer);
        }
        return column;
    }

    private static int checkBombs (int bomb, int rows, int columns) {
        Scanner sc = new Scanner(System.in);

        if (bomb >= (rows * columns)-8) {
            System.out.println("Has d'introduir un número de bombes inferior a " + (rows * columns - 8));
            int answer = sc.nextInt();
            return checkBombs(answer, rows, columns);
        }
        return bomb;
    }

    private static String giveName () {
        Scanner sc = new Scanner(System.in);

        String initialsRegex = "^.{3}$";
        boolean matches;
        String name;

        Pattern pattern = Pattern.compile(initialsRegex);

        do {
            System.out.println("Escriu les teves tres inicials (qualsevol caràcter) per guardar la puntuació:");
            name = sc.nextLine();
            Matcher matcher = pattern.matcher(name);
            matches = matcher.matches();
        } while (!matches);
        return name;
    }

    public static void menu() throws IOException {
        Scanner sc = new Scanner(System.in);

        int opcio;

        System.out.println("-------------------------------\n" +
                "Benvingut al 'Buscaminas'! \n" +
                "\n" +
                "Tenim les següents opcions: \n" +
                "1. Principiant (8x8) amb 10 mines.\n" +
                "2. Normal (16x16) amb 40 mines.\n" +
                "3. Difícil (16x30) amb 99 mines. \n" +
                "4. Personalitzat. Tria la mida del taulell i el número de mines amb les que vols jugar. \n" +
                "5. Rècords. \n" +
                "6. Surt. \n");

        do {
            System.out.println("Quina selecciones?");
            while (!sc.hasNextInt()) {
                System.out.println("Això no es un número! Tria un número");
                sc.next();
            }
            opcio = sc.nextInt();
        } while (opcio < 0);

        start(opcio);
    }

    private static void start(int answer) throws IOException {
        Scanner sc = new Scanner(System.in);

        Ranking rankingBeginner = new Ranking();
        Ranking rankingNormal = new Ranking();
        Ranking rankingHard = new Ranking();

        int[] firstBox;

        switch (answer) {
            case 1:
                String nameBeginner = giveName();
                Board beginner = new Board(8, 8);
                beginner.printBoard();
                firstBox = beginner.selectFirstBox();
                long startBeginner = rankingBeginner.startTimer();
                beginner.printBoardFirstMove(10, firstBox);
                beginner.nextMove();
                double stopBeginner = rankingBeginner.stopTimer(startBeginner);
                rankingBeginner.printTimer(stopBeginner);
                if (beginner.checkWin()){
                    rankingBeginner.addRecord(nameBeginner, stopBeginner);
                    rankingBeginner.createFile("src/com/company/RankingBeginners.txt");
                }
                break;
            case 2:
                String nameNormal = giveName();
                Board normal = new Board (16, 16);
                normal.printBoard();
                firstBox = normal.selectFirstBox();
                long startNormal = rankingNormal.startTimer();
                normal.printBoardFirstMove(40, firstBox);
                normal.nextMove();
                double stopNormal = rankingNormal.stopTimer(startNormal);
                rankingNormal.printTimer(stopNormal);
                if (!normal.checkWin()) {
                    rankingNormal.addRecord(nameNormal, stopNormal);
                    rankingNormal.createFile("src/com/company/RankingNormal.txt");
                }
                break;
            case 3:
                String nameHard = giveName();
                Board hard = new Board (16, 32);
                hard.printBoard();
                firstBox = hard.selectFirstBox();
                long startHard = rankingHard.startTimer();
                hard.printBoardFirstMove(99, firstBox);
                hard.nextMove();
                double stopHard = rankingHard.stopTimer(startHard);
                rankingHard.printTimer(stopHard);
                if (!hard.checkWin()) {
                    rankingHard.addRecord(nameHard, stopHard);
                    rankingHard.createFile("src/com/company/RankingHard.txt");
                }
                break;
            case 4:
                System.out.println("Quantes files vols que tengui el taulell?");
                int answerRows = sc.nextInt();
                int checkedRows = checkRows(answerRows);

                System.out.println("Quantes columnes vols que tengui el taulell?");
                int answerColumns = sc.nextInt();
                int checkedColumns = checkColumns(answerColumns);

                System.out.println("Quantes bombes vols que tengui el taulell?");
                int answerBombs = sc.nextInt();
                int checkedBombs = checkBombs(answerBombs, checkedRows, checkedColumns);
                Board customized = new Board(checkedRows, checkedColumns);

                customized.printBoard();

                firstBox = customized.selectFirstBox();
                customized.printBoardFirstMove(checkedBombs, firstBox);
                customized.nextMove();
                break;
            case 5:
                System.out.println("\nRanking Principiant\n----------------------------");
                rankingBeginner.readFile("src/com/company/RankingBeginners.txt");
                System.out.println("\nRanking Normal\n----------------------------");
                rankingBeginner.readFile("src/com/company/RankingNormal.txt");
                System.out.println("\nRanking Dificil\n----------------------------");
                rankingBeginner.readFile("src/com/company/RankingHard.txt");
                break;
            case 6:
                System.out.println("\nGràcies per jugar al Buscaminas");
                return;
            default:
                System.out.println("La opció introduida no existeix. \n");
                break;
        }
        menu();
    }
}
