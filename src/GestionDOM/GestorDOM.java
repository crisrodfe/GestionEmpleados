/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDOM;

import MisClases.AlmacenDeDatos;
import MisClases.Empleado;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author CrisRodFe
 */
public class GestorDOM {
    
    private Empleado em = new Empleado();
    private Scanner sc = new Scanner(System.in);

    
    public void escribirDOM(AlmacenDeDatos ad) throws ParserConfigurationException, TransformerException
    {
        //Nos aseguramos que la coleccion no esta vacía. Si es así, introducimos datos aleatorios.
        if (ad.getMiArray().isEmpty())
        {   System.out.println("El array está vacío.");
            System.out.print("¿Cuántos elementos quere introducir en el array?: ");
            int elementos = sc.nextInt();
            for(int i = 0; i<elementos; i++)
            {
                
                ad.getMiArray().add(em.generarEmpleadoAleatorio());
            }
            System.out.println("\nDatos cargados en un array correctamente.\n");
        }   
        
        Document doc = crearDoc();

        for(int i = 0; i < ad.getMiArray().size(); i++)
        //Bucle que creará una estructura por cada uno de los elementos empleado de nuestra coleccion.    
        {
            Element raiz = doc.createElement("empleado");
            doc.getDocumentElement().appendChild(raiz);

            //Creamos las etiquetas para cada propiedaded de cada objeto empleado de la coleccion.
            Element id = doc.createElement("id");
            Element nombre = doc.createElement("nombre");
            Element apellido1 = doc.createElement("apellido1");
            Element apellido2 = doc.createElement("apellido2");
            Element salario = doc.createElement("salario");

            //Añadimos las etiquetas al elemento raiz de nuestro documento.
            raiz.appendChild(id);
            raiz.appendChild(nombre);
            raiz.appendChild(apellido1);
            raiz.appendChild(apellido2);
            raiz.appendChild(salario);
            
            //Añadimos a cada una de las etiquetas creadas un texto, su valor.
            nombre.appendChild(doc.createTextNode(ad.getMiArray().get(i).getNombre()));
            apellido1.appendChild(doc.createTextNode(ad.getMiArray().get(i).getApellido1()));
            apellido2.appendChild(doc.createTextNode(ad.getMiArray().get(i).getApellido2()));
            salario.appendChild(doc.createTextNode(String.valueOf(ad.getMiArray().get(i).getSalario())));
            id.appendChild(doc.createTextNode(String.valueOf(ad.getMiArray().get(i).getId())));
        } 
        crearFicheroXML(doc);
        System.out.println("Datos escritos en fichero XML correctamente.");  
        
    }
    
    public void leerDOM(AlmacenDeDatos ad) throws ParserConfigurationException, IOException, SAXException
    {  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("EmpleadosDom.xml");
        document.getDocumentElement().normalize();
        
        NodeList listaempleados = document.getElementsByTagName("empleado");//Obtenemos todos los nodos cuya etiqueta sea empleado.
       
        ad.getMiArray().clear();
        
        //Por cada nodo empleado iremos extrayendo cada uno de sus nodos hijo.
        //Tras extraer todos los datos, con ellos creamos un nuevo empleado y lo añadimos a la coleccion introducida como argumento.
        for (int i = 0; i < listaempleados.getLength(); i++)
        {
             Node empleado = listaempleados.item(i); 
             Element elemento = (Element) empleado; 
             
             int id = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent());
             String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
             String apellido1 = elemento.getElementsByTagName("apellido1").item(0).getTextContent();
             String apellido2 = elemento.getElementsByTagName("apellido2").item(0).getTextContent();
             float salario = Float.parseFloat(elemento.getElementsByTagName("salario").item(0).getTextContent()); 
             
             Empleado e = new Empleado(id, nombre, apellido1, apellido2, salario);
             ad.getMiArray().add(e);
        }  
        System.out.println("\nLos datos han sido leidos y cargados en el array correctamente:\n");
    }     
    
    
    //Los siguientes métodos serán usados para crear un documento xml con DOM.
    public Document crearDoc() throws ParserConfigurationException
    {   
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        DOMImplementation implementation = builder.getDOMImplementation();
        Document doc = implementation.createDocument(null, "Empleados", null);
        doc.setXmlVersion("1.0");
     
        return doc;
    }
    
    public void crearFicheroXML(Document doc) throws TransformerConfigurationException, TransformerException
    {        
    Source source = new DOMSource(doc);//fuente xml
    Result result = new StreamResult(new java.io.File("EmpleadosDom.xml"));//fichero xml
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.transform(source, result);
    }
    
}
