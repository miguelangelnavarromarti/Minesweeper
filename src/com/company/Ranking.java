package src.com.company;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ranking {
    private static Map<String, Double> nameTime = new HashMap<String, Double>();
    private static Map<Integer, Double> positionTime = new HashMap<Integer, Double>();

    private static void orderRecords() {
        Map<Integer, Double> auxiliar = new HashMap<Integer, Double>();

        ArrayList<Double> valueList = new ArrayList<Double>(positionTime.values());

        double aux;

        for (int i = 0; i < valueList.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (valueList.get(j) < valueList.get(j - 1)) {
                    aux = valueList.get(j);
                    valueList.set(j, valueList.get(j - 1));
                    valueList.set(j - 1, aux);
                }
            }
        }
        for (int x = 0; x < valueList.size(); x++) {
            auxiliar.put(x, valueList.get(x));
        }
        positionTime = auxiliar;
    }

    public static void addRecord(String name, double time) {

        if (!nameTime.containsKey(name)) {
            nameTime.put(name, time);
            positionTime.put(positionTime.size()+1, time);
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

