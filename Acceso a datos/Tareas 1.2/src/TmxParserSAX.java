import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TmxParserSAX {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                int contador = 0;
                String idioma1 = null;
                String idioma2 = null;
                String idiomaActual = "";
                StringBuilder textoActual = new StringBuilder();
                String texto1 = "";
                String texto2 = "";
                PrintWriter out;
                boolean cabeceraEscrita = false;

                public void startDocument() throws SAXException {
                    try {
                        out = new PrintWriter(new FileWriter("salida.txt"));
                    } catch (IOException e) {
                        throw new SAXException(e);
                    }
                }

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (contador >= 100) return;

                    if (qName.equalsIgnoreCase("tuv")) {
                        idiomaActual = attributes.getValue("xml:lang");
                        if (idioma1 == null) {
                            idioma1 = idiomaActual;
                        } else if (idioma2 == null && !idiomaActual.equals(idioma1)) {
                            idioma2 = idiomaActual;
                        }
                    } else if (qName.equalsIgnoreCase("seg")) {
                        textoActual.setLength(0);
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (contador < 100) {
                        textoActual.append(new String(ch, start, length));
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (contador >= 100) return;

                    if (qName.equalsIgnoreCase("seg")) {
                        if (idiomaActual.equals(idioma1)) {
                            texto1 = textoActual.toString().trim();
                        } else if (idiomaActual.equals(idioma2)) {
                            texto2 = textoActual.toString().trim();
                        }
                    } else if (qName.equalsIgnoreCase("tu")) {
                        contador++;

                        if (!cabeceraEscrita && idioma1 != null && idioma2 != null) {
                            out.println("Lengua original: " + idioma1);
                            out.println("Lengua a la que se traduce: " + idioma2);
                            out.println("-----");
                            cabeceraEscrita = true;
                        }

                        if (cabeceraEscrita) {
                            out.println("[" + contador + "]");
                            out.println("Texto original (" + idioma1 + ") => " + texto1);
                            out.println("Traducción (" + idioma2 + ") => " + texto2);
                            out.println("-----");
                        }

                        texto1 = "";
                        texto2 = "";
                    }
                }

                public void endDocument() throws SAXException {
                    if (out != null) {
                        out.close();
                    }
                    System.out.println("Proceso completado. Revisa el archivo salida.txt");
                }
            };


            saxParser.parse("en-es.tmx", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}