package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ranking {
    private Map<String, Double> nameTime = new HashMap<String, Double>();

    private void orderRecords() {
        Map<String, Double> auxiliar = new HashMap<String, Double>();

        ArrayList<Double> valueList = new ArrayList<Double>(nameTime.values());
        ArrayList<String> keyList = new ArrayList<String>(nameTime.keySet());

        double auxTime;
        String auxName;

        for (int i = 0; i < valueList.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (valueList.get(j) < valueList.get(j - 1)) {
                    auxTime = valueList.get(j);
                    auxName = keyList.get(j);
                    valueList.set(j, valueList.get(j - 1));
                    keyList.set(j, keyList.get(j-1));
                    valueList.set(j - 1, auxTime);
                    keyList.set(j-1, auxName);
                }
            }
        }
        for (int x = 0; x < valueList.size(); x++) {
            auxiliar.put(keyList.get(x), valueList.get(x));
        }
        nameTime.clear();
        nameTime = auxiliar;
    }

    public void addRecord(String name, double time) {
        nameTime.put(name, time);
    }

    public long startTimer(){
        return System.nanoTime();
    }

    public double stopTimer(long start) {
        return (double) ((System.nanoTime()-start)/1000000000);
    }

    public void printTimer(double time) {
        System.out.println("Has tardat " + time + " segons\n");
    }

    public void printRanking () {
        for (String key : nameTime.keySet()) {
            System.out.println(key + " " + nameTime.get(key));
        }
    }

    public void createFile(String path) throws IOException {


        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        BufferedReader br = new BufferedReader(new FileReader(path));

        String document;
        String playerList = "";
        while ((document = br.readLine()) != null) {
            playerList = document + "\n" + playerList;
            String[] players = playerList.split("\n");
            for (int i = 0; i < players.length; i++){
                addRecord(players[i].split(" - ")[0],Double.parseDouble(players[i].split(" - ")[1]));
            }
        }
        // ORDENA CORRECTAMENT PERÒ ES PUT DES MAP HO DESORDENA UN ALTRE PIC
        orderRecords();

        String rankingBeginners = "";
        for (String key : nameTime.keySet()){
            rankingBeginners = rankingBeginners + key + " - " + nameTime.get(key) + "\n";
        }
        //CONTINUAR AQUÍ!!!!
        bw.write(rankingBeginners);
        bw.flush();

        String line;
        //String path = "testFile.txt";
        while ((line = br.readLine()) != null) {

            System.out.println(line);
        }

        bw.close();
        br.close();
        }
    }

    /*public static void readFile() {
        File archivo = null;
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
        }
    }*/


