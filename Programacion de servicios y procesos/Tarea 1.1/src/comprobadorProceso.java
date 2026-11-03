import java.io.IOException;

public class comprobadorProceso {

    public static void main(String[] args) {

        try {
            ProcessBuilder constructorProceso = new ProcessBuilder("cmd.exe", "/c", "script.bat");

            System.out.println("Iniciando el script...");
            Process proceso = constructorProceso.start();

            while (proceso.isAlive() == true) {
                System.out.println("Estado del proceso: En ejecución...");
                Thread.sleep(3000);
            }

            System.out.println("Estado del proceso: Terminado.");
            System.out.println("Saliendo del programa principal.");

        } catch (IOException e) {
            // Este bloque captura errores si el archivo script.bat no se encuentra o no se puede abrir.
            System.out.println("Error: No se pudo ejecutar el script. Comprueba que el archivo existe.");

        } catch (InterruptedException e) {
            System.out.println("Error: El programa fue interrumpido de forma inesperada.");
        }
    }
}