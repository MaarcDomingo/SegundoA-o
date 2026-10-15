import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class gestorClientes {

    // Variables globales
    private final File ficheroXML;
    private final Document documento;

    // CONSTRUCTOR
    public gestorClientes(String ruta) throws Exception {
        this.ficheroXML = new File(ruta);

        if (!this.ficheroXML.exists() || !this.ficheroXML.isFile()) {
            throw new Exception("Error: El fichero especificado no existe o no es adecuado.");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Constructor de parser
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Lee el archivo y lo guarda en memoria
        this.documento = builder.parse(this.ficheroXML);
        this.documento.getDocumentElement().normalize();
    }

    // Metodo para introducir los clientes
    public void introducirClientes() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("---Añadir Nuevo Cliente---");
            System.out.print("Escribe el DNI: ");
            String dni = sc.nextLine();

            // Comprobamos si el DNI existe
            if (comprobarDniExiste(dni)) {
                System.err.println("Error: El DNI " + dni + " ya existe en el fichero.");
            } else {
                System.out.print("Introduce los apellidos: ");
                String apellidos = sc.nextLine();

                System.out.print("Introduce el código postal (CP): ");
                String cp = sc.nextLine();

                // Añadimos el cliente al DOM en memoria
                agregarClienteDOM(dni, apellidos, cp);

                System.out.println("Cliente añadido temporalmente");
            }

            System.out.print("¿Quieres añadir otro cliente? (s/n): ");
            String respuesta = sc.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
    }

    // COMPROBAR DNI
    private boolean comprobarDniExiste(String dniBuscado) {
        // Buscamos todas las etiquetas <cliente>
        NodeList listaClientes = this.documento.getElementsByTagName("cliente");

        for (int i = 0; i < listaClientes.getLength(); i++) {
            Element cliente = (Element) listaClientes.item(i);

            // Leemos el atributo "DNI" de cada cliente
            String dniEnMemoria = cliente.getAttribute("DNI").trim();

            if (dniBuscado.equalsIgnoreCase(dniEnMemoria)) {
                return true;
            }
        }
        return false;
    }

    // AÑADIR AL DOM
    private void agregarClienteDOM(String dni, String apellidos, String cp) {
        // Creamos la caja principal <cliente>
        Element nuevoCliente = this.documento.createElement("cliente");
        nuevoCliente.setAttribute("DNI", dni);

        // 3. Creamos y metemos las etiquetas
        Element nodoApellidos = this.documento.createElement("apellidos");
        nodoApellidos.setTextContent(apellidos);
        nuevoCliente.appendChild(nodoApellidos);

        Element nodoCp = this.documento.createElement("CP");
        nodoCp.setTextContent(cp);
        nuevoCliente.appendChild(nodoCp);

        // 5. Enganchamos el cliente entero a la raíz
        Element raiz = this.documento.getDocumentElement();
        raiz.appendChild(nuevoCliente);
    }

    // BACKUPS Y GUARDAR
    public void guardarSobrescribir() throws Exception {
        // Crear la carpeta 'backups' y hacer la copia .bak
        Path origen = this.ficheroXML.toPath();
        Path destino = Paths.get("backups", this.ficheroXML.getName() + ".bak");

        Files.createDirectories(destino.getParent()); // Crea la carpeta si no existe
        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Copia de seguridad guardada con éxito en la carpeta 'backups'.");

        // Sobrescribir el archivo original
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Esto hace que el xml quede bien tabulado
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(this.documento);
        StreamResult result = new StreamResult(this.ficheroXML);

        transformer.transform(source, result);
        System.out.println("Archivo XML original sobrescrito con los nuevos clientes.");
    }
}