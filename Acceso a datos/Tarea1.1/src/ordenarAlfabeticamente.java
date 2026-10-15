import java.io.*;
import java.util.*;

public class ordenarAlfabeticamente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // primer archivo
        System.out.println("Escribe el nombre del primer archivo:");
        String nombreArchivo1 = sc.nextLine();
        File archivo1 = new File ((nombreArchivo1));

        // comprovacion de que el segundo archivo existe
        while (!archivo1.exists()){
            System.out.println("El nombre del archivo no existe. Vuelve a intentarlo");
            nombreArchivo1 = sc.nextLine();
            archivo1 = new File ((nombreArchivo1));
        }

        // segundo archivo
        System.out.println("Escribe el nombre del segundo archivo:");
        String nombreArchivo2 = sc.nextLine();
        File archivo2 = new File ((nombreArchivo2));

        // comprovacion de que el segundo archivo existe
        while (!archivo2.exists()){
            System.err.println("El nombre del archivo no existe. Vuelve a intentarlo");
            nombreArchivo2 = sc.nextLine();
            archivo2 = new File ((nombreArchivo2));
        }

        ArrayList<String> listaNombres = new ArrayList<>();

        try {
            BufferedReader br1 = new BufferedReader(new FileReader(archivo1));
            String Linea;

            while ((Linea = br1.readLine()) != null) {
                listaNombres.add(Linea);
            }
            System.out.println("Datos de " + archivo1 + " Leidos correctamente");

        } catch (IOException e){
            System.err.println("Error al leer el nombre del primer archivo");
        }

        try {
            BufferedReader br2 = new BufferedReader(new FileReader(archivo2));
            String Linea;

            while ((Linea = br2.readLine()) !=null){
                listaNombres.add(Linea);
            }
            System.out.println("Datos de " + archivo2 + " Leidos correctamente");

            listaNombres.sort(String.CASE_INSENSITIVE_ORDER);

        } catch (IOException e){
            System.err.println("Error al leer el nombre del segundo archivo");
        }
        String txtSalida = "ordenado.txt";

        try (FileWriter fw = new FileWriter(txtSalida);
            BufferedWriter pw = new BufferedWriter(fw)){

            for (String Linea : listaNombres) {
                pw.write(Linea);
                pw.newLine();
            }
            System.out.println("Los datos se han guardado en ordenado.txt");

        } catch (IOException e) {
            System.err.println("Error al crear el archivo");
        }
    }

}
