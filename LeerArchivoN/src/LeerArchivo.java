
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LeerArchivo {

    public void leerArchivo() {

        try {

            FileReader fr = new FileReader("Tripletas.txt");
            BufferedReader br = new BufferedReader(fr);
            String fila = null;
            String columna = null;
            String valor = null;
            String linea;

            int cont = 1;

            while ((linea = br.readLine()) != null) {

                if (!linea.equals(";")) {
                    fila = linea;
                    Start.hileras.add(linea);
                }
                System.out.println(fila);
            }
            Start.m = Start.hileras.size();

        } catch (Exception e) {
            System.err.println("No se encontro el archivo");

        }

    }
    
    public static void registrarTripleta(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("TripletasSolver.txt");
            pw = new PrintWriter(fichero);
            pw.print(Ventana.f-2 + ",");
            pw.print(Ventana.c-2 + ",");
            pw.print((Ventana.f-2)*(Ventana.c-2)+","); 
            pw.print(Start.tripleta.length-2);
            pw.println("");
                       
            for (int i =0; i < Start.tablero.length;i++) {
                for (int j =0; j < Start.tablero[i].length;j++){
                   
                   if (Start.tablero[i][j]!=-1){
                       pw.print((i-1)+",");
                       pw.print((j-1)+",");
                       pw.print(Start.tablero[i][j]+";");
                       pw.println("");
                   }                                
                }                               
            }
            
            pw.print(Ventana.tiempoCompilacion);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
