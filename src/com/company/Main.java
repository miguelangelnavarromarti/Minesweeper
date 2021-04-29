package src.com.company;

import java.io.*;
import java.lang.*;

import java.nio.file.Files;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws IOException {

        Play.menu();
        /*BufferedWriter bw = new BufferedWriter(new FileWriter("testFile.txt", true));
        BufferedReader br = new BufferedReader(new FileReader("testFile.txt"));

        String name = "My name";
        bw.write(name);
        bw.newLine();
        bw.write("adeu");
        bw.flush();

        String line;
        String path = "testFile.txt";
        while ((line = br.readLine()) != null) {

            System.out.println(line);
        }

        bw.close();
        br.close();

        File file = new File("testFile.txt");

        if(file.delete()) {
            System.out.println("Funciona");
        } else {
            System.out.println("No funciona");
        }*/

        /*File file = new File("Minesweeper/src/com/company/RankingBeginners.txt");
        if(file.delete()) {
            System.out.println("Funciona");
        } else {
            System.out.println("No funciona");
        }*/
    }
}