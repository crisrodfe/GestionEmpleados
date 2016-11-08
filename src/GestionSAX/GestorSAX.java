/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionSAX;

import MisClases.AlmacenDeDatos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author CrisRodFe
 */
public class GestorSAX extends InputSource
{   
    public void leerSAX(AlmacenDeDatos ad) throws SAXException, FileNotFoundException, IOException
    {       
        XMLReader reader = XMLReaderFactory.createXMLReader();
        GestionContenido gc = new GestionContenido();
        reader.setContentHandler(gc);        
        reader.parse(new InputSource(new FileInputStream("EmpleadosSax.xml")));
       
        ad.getMiArray().clear();
        ad.setMiArray(gc.getMiArray());
        System.out.println("Los datos han sido cargados correctamente a la colección:\n");
    }
    
    public void escribirSAX(AlmacenDeDatos ad) throws TransformerException
    {
        Scanner sc = new Scanner(System.in);
        if (ad.getMiArray().isEmpty())
        {   System.out.println("El array está vacío.");
            System.out.print("¿Cuántos elementos quere introducir en el array?: ");
            int numElementos = sc.nextInt();
            ad.setMiArray(ad.cargarDatosAleatorios(numElementos));
            System.out.println("\nDatos cargados en un array correctamente.\n");
        }
        
        XMLReader datosReader = new DatosReader();
        
        InputSource datosSource = ad;//"ad" Corresponderá a una instancia de la clase que contiene una coleccion de elementos.
        Source source = new SAXSource(datosReader, datosSource); 
       
        File f = new File("EmpleadosSax.xml");
        Result resultado = new StreamResult(f);
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
               
        transformer.transform(source, resultado); 
        
        sc.close();
    } 
}
