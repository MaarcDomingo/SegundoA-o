import java.util.Scanner;

public class InvertirPalabra2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // creamos el scanner para que leera el teclado
        String entrada;
        do {
            System.out.print("Introduce una palabra: ");
            entrada = scanner.nextLine().trim(); // trim recorta automáticamente los espacios sobrantes de los extremos
        } while (entrada.isEmpty() || entrada.contains(" "));
        // condicional que pregunta si tiene espacios o no tiene contenido

        System.out.println("Palabra invertida: " + new StringBuilder(entrada).reverse()); 
        // StringBuilder coge la palabra validada por el bucle anterior, .reverse() le da la vuelta y lo imprime 
        scanner.close(); // cerramos el lector del teclado
    }
}