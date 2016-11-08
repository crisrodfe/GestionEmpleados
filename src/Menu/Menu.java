package Menu;

import GestionEstilos.GestorXSL;
import GestionFicheros.GestorFicheros;
import GestionSAX.GestorSAX;
import GestionDOM.GestorDOM;
import GestionXStream.GestorXStream;
import MisClases.AlmacenDeDatos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 * @author CrisRodFe
 */
public class Menu {
    
   private static final AlmacenDeDatos ad = new AlmacenDeDatos();
   private static final GestorSAX sax = new GestorSAX();
   private static final GestorDOM xml = new GestorDOM();
   private static final GestorFicheros gf = new GestorFicheros();
   private static final GestorXStream xStream = new GestorXStream();
   private static final GestorXSL xsl = new GestorXSL();
   private static final Scanner sc = new Scanner(System.in);
     
   public static void main (String [] args) throws IOException, ClassNotFoundException, FileNotFoundException, ParseException, ParserConfigurationException, TransformerException, SAXException 
   {
        int opcion = 0;
        
       do
       {
           System.out.println("\n¿Qué acción desea realizar?");
           System.out.println("1.Mostrar contenido del array.");
           System.out.println("2.Cargar array.");
           System.out.println("3.Dar de alta un nuevo empelado.");
           System.out.println("4.Escribir fichero delimitado.");
           System.out.println("5.Escribir fichero encolumnado.");
           System.out.println("6.Leer fichero delimitado.");
           System.out.println("7.Leer fichero encolumnado.");
           System.out.println("8.Exportar a fichero binario.");
           System.out.println("9.Importar de fichero binario.");
           System.out.println("10.Escribir con DOM.");
           System.out.println("11.Leer con DOM.");
           System.out.println("12.Escribir con SAX.");
           System.out.println("13.Leer  con SAX.");
           System.out.println("14.Escribir con XStream.");
           System.out.println("15.Leer con XStream.");
           System.out.println("16.Generar página HTML.");
           System.out.println("17.Salir.");
           System.out.println("Introduzca el nº de opción elegida: ");
           opcion = sc.nextInt();
           
           switch (opcion)
           {
               case 1:
                   ad.mostrarArray();                  
                   break;
               case 2:
                   System.out.print("¿Cuántos elementos quere introducir en el array?: ");
                   ad.cargarDatosAleatorios(sc.nextInt());
                   System.out.println("\nDatos cargados en un array correctamente.\n");
                   break;
               case 3:
                   ad.añadirAlArray();
                   break; 
               case 4:
                   System.out.print("Introduzca el caracter separador: ");
                   gf.escribirDelimitado(sc.next(),ad);
                   break;
               case 5:                   
                   gf.escribirEncolumnado(pedirDimensiones(),ad);
                   break;
               case 6:
                   System.out.print("Introduzca el caracter separador: ");
                   gf.leerDelimitado(sc.next(),ad);                 
                   break;
               case 7:
                   gf.leerEncolumnado(pedirDimensiones(),ad);
                   break;
               case 8:
                   System.out.print("Introduzca nº de elementos: ");
                   gf.exportarABinario(sc.nextInt(),ad);
                   break;
               case 9:
                   gf.importarDeBinario(ad);
                   break;
               case 10:
                   xml.escribirDOM(ad);
                   break;
               case 11:  
                   xml.leerDOM(ad);
                   break;
               case 12:
                   sax.escribirSAX(ad);
                   break;
               case 13:
                   sax.leerSAX(ad);
                   break;
               case 14:
                   xStream.escribirXStream(ad);
                   break;
               case 15:
                   xStream.leerXStream(ad);
                   break;
               case 16:
                   xsl.crearHTML();
                   break;
               case 17:
                   System.exit(0);
                   break;
               default:
                   System.out.println("Opción incorrecta.");
           }
       } while (opcion != 17);
       
    }   
    
   public static int[] pedirDimensiones()
   {
       int dimensiones[] = new int[5];
       System.out.println("Dimension columna 1:");
       dimensiones[0] = sc.nextInt();
       System.out.println("Dimension columna 2:");
       dimensiones[1] = sc.nextInt();
       System.out.println("Dimension columna 3:");
       dimensiones[2] = sc.nextInt();
       System.out.println("Dimension columna 4:");
       dimensiones[3] = sc.nextInt();
       System.out.println("Dimension columna 5:");
       dimensiones[4] = sc.nextInt();

       return dimensiones;
   }
     
}
