public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Argumentos incorrectos, Falta la ruta del fichero XML");
            return;
        }

        String fichero = args[0];

        try {

            gestorClientes gestor = new gestorClientes(fichero);
            gestor.introducirClientes();
            gestor.guardarSobrescribir();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}