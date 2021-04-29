package src.com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RankingBeginner extends Ranking{

    private static Map<String, Double> nameTime = new HashMap<String, Double>();

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

    public static void createFile() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("Minesweeper/src/com/company/RankingBeginners.txt", true));
        BufferedReader br = new BufferedReader(new FileReader("Minesweeper/src/com/company/RankingBeginners.txt"));


        String document;
        if ((document = br.readLine()) != null) {
            String[] players = document.split("\n");
            for (int i = 0; i < players.length; i++){
                addRecord(players[i].split(", ")[1],Double.parseDouble(players[i].split(", ")[2]));
            }
        }

        String rankingBeginners = "";
        for (String key : nameTime.keySet()){
            rankingBeginners = rankingBeginners + key + ", " + nameTime.get(key) + "\n";
        }
        System.out.println(rankingBeginners);
        bw.write(rankingBeginners);
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


        /*FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("Minesweeper/src/com/company/RankingBeginners.txt",true);
            //Es true es per un arxiu que ja existeix i no crea un nou
            pw = new PrintWriter(fichero);
            for (int i = 0; i < nameTime.size(); i++) {
                pw.println((i+1) + " " + nameTime.keySet().toArray()[i] + " " + nameTime.get(i));
            }
            pw.println("hola123");

        } catch (Exception e) {
            e.printStackTrace();
        } finally { // Amb aquest finally mos aseguram que queda tancat s'arxiu
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }*/
    }
}
