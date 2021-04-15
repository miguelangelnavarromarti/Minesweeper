package src.com.company;

import java.util.Scanner;

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

    public static void menu() {
        Scanner sc = new Scanner(System.in);

        int opcio;

        System.out.println("Benvingut al 'Buscaminas'! \n" +
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

    private static void start(int answer) {
        Scanner sc = new Scanner(System.in);

        int[] firstBox;

        switch (answer) {
            case 1:
                Board principiant = new Board(8, 8);
                principiant.printBoard();
                firstBox = principiant.selectFirstBox();
                principiant.printBoardFirstMove(10, firstBox);
                principiant.nextMove();
                break;
            case 2:
                Board normal = new Board (16, 16);
                normal.printBoard();
                firstBox = normal.selectFirstBox();
                normal.printBoardFirstMove(40, firstBox);
                normal.nextMove();
                break;
            case 3:
                Board dificil = new Board (16, 32);
                dificil.printBoard();
                firstBox = dificil.selectFirstBox();
                dificil.printBoardFirstMove(99, firstBox);
                dificil.nextMove();
                break;
            case 4:
                // Comprova les files
                System.out.println("Quantes files vols que tengui el taulell?");
                int answerRows = sc.nextInt();
                int checkedRows = checkRows(answerRows);

                // Comprova les columnes
                System.out.println("Quantes columnes vols que tengui el taulell?");
                int answerColumns = sc.nextInt();
                int checkedColumns = checkColumns(answerColumns);

                //Falta comprovar si el número de bombes introduit es vàlid
                System.out.println("Quantes bombes vols que tengui el taulell?");
                int answerBombs = sc.nextInt();
                int checkedBombs = checkBombs(answerBombs, checkedRows, checkedColumns);
                Board personalitzat = new Board(checkedRows, checkedColumns);

                //Aquí cream el taulell (sense bombes)
                personalitzat.printBoard();

                firstBox = personalitzat.selectFirstBox();
                personalitzat.printBoardFirstMove(checkedBombs, firstBox);
                personalitzat.nextMove();
                break;
            case 5:
                // Falta afegir tota la funcionalitat
                break;
            case 6:
                System.out.println("Gràcies per jugar al Buscaminas");
            default:
                System.out.println("La opció introduida no existeix. \n");
                break;
        }
        menu();
    }
}
