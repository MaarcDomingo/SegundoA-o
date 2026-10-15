import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class buscadorArchivos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del archivo: ");
        String nombreArchivo = sc.nextLine();

        System.out.print("Que quieres buscar: ");
        String cadenaBusqueda = sc.nextLine();

        // 1. Controlar que el archivo exista
        File archivo = new File(nombreArchivo);
        while (!archivo.exists() || !archivo.isFile()) {
            System.out.println("Error: El archivo no existe en el directorio actual o el nombre es incorrecto.");
            nombreArchivo = sc.nextLine();
            archivo = new File ((nombreArchivo));
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 1;
            boolean coincidencia = false;

            // 2. Bucle de lectura
            while ((linea = br.readLine()) != null) {
                // 3. Buscar la cadena de texto dentro del archivo
                if (linea.contains(cadenaBusqueda)) {
                    // 4. Imprimir cada línea que contenga la cadena junto con el número de línea
                    System.out.println("Línea " + numeroLinea + ": " + linea);
                    coincidencia = true;
                }
                numeroLinea++;
            }

            if (!coincidencia) {
                System.out.println("La cadena '" + cadenaBusqueda + "' no se encontró en el archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error de lectura:");
        }

        sc.close();
    }
}