
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class Start {

    static ArrayList<String> hileras = new ArrayList();
    static int m, filas, col, x, y, primerCx, primerCy;
    static Tripleta[] tripleta;
    static int tablero[][];

    static int[] pist, start;

    
    /*stringToMatriz crea una instancia de un objeto de la clase LeerArchivo para obtener un arrayList con cada  
      linea del archivo que comprende la primera: dimensiones de la matriz y numero de datos y las siguientes tripletas
      que almacenan valor y posicion de una pista en el tablero de Hidato, la ultima linea es el tiempo de compilacion*/
    
    public static void stringToMatriz() {

        LeerArchivo la = new LeerArchivo();

        la.leerArchivo();

        System.out.println(m);
        tripleta = new Tripleta[m - 2];

        int f, c, v, continua, continua2;
        /*Aqui obtenemos dimensiones de la matriz*/
        x = Integer.parseInt(hileras.get(0).substring(0, hileras.get(0).indexOf(",")));
        continua = hileras.get(0).indexOf(",") + 1;
        y = Integer.parseInt(hileras.get(0).substring(continua, hileras.get(0).indexOf(",", continua)));

        /*--*/
        
        /*Se obtienen los valores enteros que comprende cada linea del archivo fila, columna y valor
          esto se hace por medio de metodos de la clase String*/
        for (int i = 1; i < m - 1; i++) {

            f = Integer.parseInt(hileras.get(i).substring(0, hileras.get(i).indexOf(",")));
            continua = hileras.get(i).indexOf(",") + 1;
            c = Integer.parseInt(hileras.get(i).substring(continua, hileras.get(i).indexOf(",", continua)));
            continua2 = hileras.get(i).indexOf(",", continua) + 1;
            v = Integer.parseInt(hileras.get(i).substring(continua2, hileras.get(i).indexOf(";")));

            tripleta[i - 1] = new Tripleta(f, c, v);
            System.out.println("Tripleta " + (i - 1) + ": " + tripleta[i - 1].getFila() + ";" + tripleta[i - 1].getColumna() + ";" + tripleta[i - 1].getValor());

        }
        /*--*/
        
        /*Se crea un ArrayList con el fin de albergar en este las pistas y ademas se definen 
          las dimensiones del tablero para el Hidato*/
        List<Integer> list = new ArrayList<>(x * y);
        tablero = new int[x + 2][y + 2];
         /*--*/
        
         /*Se llenan las posiciones del tablero con -1*/
        for (int[] row : tablero) {
            for (int i = 0; i < y + 2; i++) {
                row[i] = -1;
            }
        }
        /*--*/
        
        /*Recorremos el vector de pistas y a cada posicion del tablero vacio 
         que deberia contener una pista le asignamos el valor de esa pista, guardando ademas 
         la posicion del primer dato*/
        for (int i = 0; i < tripleta.length; i++) {

            tablero[tripleta[i].getFila() + 1][tripleta[i].getColumna() + 1] = tripleta[i].getValor();
            list.add(tripleta[i].getValor());
            if (tripleta[i].getValor() == 1) {
                primerCx = tripleta[i].getFila();
                primerCy = tripleta[i].getColumna();
                start = new int[]{primerCx + 1, primerCy + 1};
            }
        }
        /*--*/
        
        /*Ordenamos el arrayList con el metodo sort y ademas cada pista almacenada sera guardada
         en el pist*/
        Collections.sort(list);
        pist = new int[list.size()];
        for (int i = 0; i < pist.length; i++) {
            pist[i] = list.get(i);
        }
        /*--*/
        
        /*Se llenaran las posiciones que contengan -1 con ceros sin considerar los datos que "enmarcan" a la matriz
          asi podremos trabajar con posiciones desde 1 y no desde 0 como se acostumbra en java*/
        for (int i = 1; i < tablero.length - 1; i++) {
            for (int j = 1; j < tablero[i].length - 1; j++) {
                if (tablero[i][j] == -1) {
                    tablero[i][j] = 0;
                }
            }

        }
       /*--*/
    }
 
    
    /*Este metodo resolvera el hidato, buscando el unico camino posible para su solucion en base
      a las pistas previamente recibidas por medio del archivo, se analizaran cada uno de los valores
      que se encuentran almacenados en la matriz "tablero" y luego este se modificara si es posible encontrar una solucion
    */
    public static boolean zolucion(int r, int c, int n, int next) {
        if (n > pist[pist.length - 1]) {
            return true;
        }

        if (tablero[r][c] != 0 && tablero[r][c] != n) {
            return false;
        }

        if (tablero[r][c] == 0 && pist[next] == n) {
            return false;
        }

        int back = tablero[r][c];
        if (back == n) {
            next++;
        }

        tablero[r][c] = n;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (zolucion(r + i, c + j, n + 1, next)) {
                    return true;
                }
            }
        }

        tablero[r][c] = back;
        return false;
    }
    /*--*/
    
    /*tieneSolucion recorrera la matriz ya modifcada por el metodo zolucion y preguntara en cada iteracion 
    si existe algun valor 0 en la matriz si es asi retornara que no tiene solucion*/
      public static boolean tieneSolucion(){
        for (int i = 1; i < tablero.length - 1; i++) {
            for (int j = 1; j < tablero[i].length - 1; j++) {
                if (tablero[i][j] == 0) {
                JOptionPane.showMessageDialog(null, "El hidato esta mal construido: No tiene solucion");
                return false;
                }
                
            }

        }
      return true;
    }
    /*--*/
    
    /*Imprime el tablero antes o despues de su modificacion*/
      public static void printBoard() {
        for (int[] row : tablero) {
            for (int c : row) {
                if (c == -1) {
                System.out.print(" . ");
                } else {
                System.out.printf(c > 0 ? "%2d " : "__ ", c);
                }
                }
                System.out.println();
                }
      }
    /*--*/
    
    
  
    

}
