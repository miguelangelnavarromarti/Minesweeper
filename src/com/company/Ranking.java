package src.com.company;

import java.util.HashMap;
import java.util.Map;

public class Ranking {
    private static Map<String, Double> nameTime = new HashMap<String, Double>();
    private static Map<Integer, Double> positionTime = new HashMap<Integer, Double>();

    public static void addRecord(String name, double time) {

        if (!nameTime.containsKey(name)) {
            nameTime.put(name, time);
            positionTime.put(positionTime.size()+1, time);
        }
    }

    public static long startTimer(){
        return System.nanoTime();
    }

    public static double stopTimer(long start) {
        return (double) ((System.nanoTime()-start)/1000000000);
    }

    public static void printTimer(double time) {
        System.out.println("Has tardat " + time + " segons\n");
    }
}

