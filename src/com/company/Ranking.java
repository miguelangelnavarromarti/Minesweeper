package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {
    private ArrayList<String> keyList = new ArrayList<>();
    private ArrayList<Double> valueList = new ArrayList<>();

    public void addRecord(String name, double time) {
        keyList.add(name);
        valueList.add(time);
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
        }
    }
}



