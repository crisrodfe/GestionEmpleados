
package GestionSAX;

import MisClases.Empleado;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author CrisRodFe
 */
public class GestionContenido extends DefaultHandler{
    
     
     private ArrayList<Empleado> miArray = new ArrayList<>();
     private Empleado e;
     private String texto = null;
     private String nombreEtiqueta = null;

    public ArrayList<Empleado> getMiArray() {
        return miArray;
    }
     
     @Override
     public void startDocument() throws SAXException 
     {         
        System.out.println("\nLeyendo datos de Empleado.xml :\nPrincipio del documento XML\n"); 
     }
    
     @Override
     public void endDocument() throws SAXException 
     { 
         System.out.println("Fin del Documento\n");
     }
     
     @Override
     public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException 
     {  
        nombreEtiqueta = localName;
        if (localName.equals("empleado"))
        {    
            e = new Empleado();
        }    
     }
     
     @Override
     public void characters(char[] ch, int start, int length)throws SAXException 
     {
        texto = String.valueOf(ch, start, length).trim();        
        switch (nombreEtiqueta)
        {
            case "id":
                e.setId(Integer.valueOf(texto));
                break;
            case "nombre":
                e.setNombre(texto);
                break;
            case "apellido1":
                e.setApellido1(texto);
                break;
            case "apellido2":
                e.setApellido2(texto);
                break;
            case "salario":
                e.setSalario(Float.valueOf(texto));
                break;
        }        
     }
     
     @Override
     public void endElement(String uri, String localName, String name)throws SAXException 
     {   
        if(localName.equals("empleado"))
        {        
            miArray.add(e); 
            System.out.println("\tNuevo empleado leido: "+e.toString());
        }    
     } 
}
