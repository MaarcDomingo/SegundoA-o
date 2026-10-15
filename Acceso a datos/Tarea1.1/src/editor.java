import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class editor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Como quieres llamar el archivo?");
        String nombreArchivo = sc.nextLine();
        File archivo = new File ((nombreArchivo));
        try {
            if (archivo.createNewFile()){ // crea el archivo si no existe (true), si existe manda false
                System.out.println("Archivo guardado");
            } else {
                System.out.println("El archivo ya existe");}
                // ahora hacemos otro scanner para preguntar que opcion quiere hacer
                Scanner sc2 = new Scanner(System.in);
                System.out.println("1 - Sobrescribir");
                System.out.println("2 - Añadir contenido nuevo");
                System.out.println("3 - Salir");
                String comprobar = sc2.nextLine();{
            } switch (comprobar) {
                case "1":
                    System.out.println("Escribe los datos que quieres sobrescribir (Pulsa enter en una linea vacia para terminar)");

                    try (FileWriter fw = new FileWriter((nombreArchivo));
                         PrintWriter pw = new PrintWriter(fw)) {

                        while (true){
                            String linea = sc.nextLine();
                            if (linea.isEmpty()){ // si la linea esta vacia, break
                                break;
                            }
                            pw.println(linea);
                        }
                        System.out.println("Datos sobrescritos");


                    } catch (IOException e) {
                        System.out.println("Error al escribir el archivo");
                    }
                    break;
                case "2":
                    System.out.println("Escribe los datos que quieres añadir al documento (Pulsa enter en una linea vacia para terminar) ");


                    try (FileWriter fw = new FileWriter((nombreArchivo), true); // al poner true hacemos que se añada en ver de sobrescribir
                         PrintWriter pw = new PrintWriter(fw)) {

                        while (true){
                            String linea = sc.nextLine();
                            if (linea.isEmpty()){
                                break;

                            }
                            pw.println(linea);

                        }
                        System.out.println("Datos Añadidos");


                    } catch (IOException e) {
                        System.out.println("Error al escribir el archivo");
                    }
                    break;
                case "3":
                    System.out.println("Saliendo"); // salir del programa
                    break;

                default:
                    System.out.println("Opción inválida"); // salta cuando el usuario pone algo que no sea 1,2,3
                    break;
            } while (!comprobar.equals("1") && !comprobar.equals("2") && !comprobar.equals("3"));
        }catch (IOException e) {
            System.out.println("El archivo no se pudo crear");
        }
        sc.close();
    }
}