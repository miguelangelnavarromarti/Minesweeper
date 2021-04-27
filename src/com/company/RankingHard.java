package src.com.company;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class RankingHard extends Ranking{
    private static Map<String, Double> nameTime = new HashMap<String, Double>();
    private static Map<Integer, Double> positionTime = new HashMap<Integer, Double>();


    /*public static void readFile() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("Minesweeper/src/com/company/RankingHard.txt");
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

    public static void createFile() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("Minesweeper/src/com/company/RankingHard.txt",true);
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
        }
    }
}
