package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {
    private ArrayList<String> keyList = new ArrayList<String>();
    private ArrayList<Double> valueList = new ArrayList<Double>();

    private void orderRecords() {

        double auxTime;
        String auxName;

        for (int i = keyList.size(); i > 0; i--) {
            for (int j = 0; j < keyList.size() - 1; j++) {
                if (valueList.get(j) > valueList.get(j + 1)) {
                    auxTime = valueList.get(j);
                    auxName = keyList.get(j);
                    valueList.set(j, valueList.get(j + 1));
                    keyList.set(j, keyList.get(j + 1));
                    valueList.set(j + 1, auxTime);
                    keyList.set(j + 1, auxName);
                }
            }
        }
    }

    public void addRecord(String name, double time) {
        keyList.add(name);
        valueList.add(time);
    }

    public void clean() {
        this.keyList = new ArrayList<String>();
        this.valueList = new ArrayList<Double>();
    }

    public long startTimer() {
        return System.nanoTime();
    }

    public double stopTimer(long start) {
        return (double) ((System.nanoTime() - start) / 1000000000);
    }

    public void printTimer(double time) {
        System.out.println("Has tardat " + time + " segons\n");
    }

    public void printRanking() {
        for (int i = 0; i < keyList.size(); i++) {
            System.out.println(keyList.get(i) + " - " + valueList.get(i));
        }
    }

    public void createFile(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));

        String rankingBeginners = "";

        for (int i = 0; i < keyList.size(); i++) {
            rankingBeginners = rankingBeginners + keyList.get(i) + " - " + valueList.get(i) + "\n";
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));

        bw.write(rankingBeginners);
        bw.flush();

        bw.close();
        br.close();
    }


    public void readFile(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));

        String document;
        String playerList = "";
        while ((document = br.readLine()) != null) {

            System.out.println(document);

            /*playerList = document + "\n" + playerList;
            String[] players = playerList.split("\n");
            for (int i = 0; i < players.length; i++){
                addRecord(players[i].split(" - ")[0],Double.parseDouble(players[i].split(" - ")[1]));
            }*/
        }
    }
}

        /*File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("Minesweeper/src/com/company/RankingBeginners.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null)
                System.out.println(linea);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }*/



