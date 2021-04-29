package src.com.company;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ranking {
    private static Map<String, Double> nameTime = new HashMap<String, Double>();

    protected static void orderRecords() {
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
        nameTime = auxiliar;
    }

    public static void addRecord(String name, double time) {

        if (!nameTime.containsKey(name)) {
            nameTime.put(name, time);
        }
        orderRecords();
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

