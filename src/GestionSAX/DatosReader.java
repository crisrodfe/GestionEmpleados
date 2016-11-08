
package GestionSAX;

import MisClases.AlmacenDeDatos;
import MisClases.Empleado;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author CrisRodFe
 */
public class DatosReader implements XMLReader 
{ 
    private ContentHandler handler;
    private final AttributesImpl atts = new AttributesImpl(); //métodos que serán usados por el transformer
    
    @Override
    public void parse(InputSource input) throws IOException, SAXException 
    {
        if (!(input instanceof AlmacenDeDatos)) 
        {
            String m = "El único parámetro para el parser es un Alm_datos"; throw new SAXException(m); 
        }
        if (handler == null) 
        {
            throw new SAXException("Sin ContentHandler"); 
        }
        
        
        AlmacenDeDatos source = (AlmacenDeDatos) input;
        ArrayList<Empleado> empleados = source.getMiArray();
    
        //Creamos una estructura para el documento xml.Iremos rellenando los datos de cada uno de los objetos empleado con el bucle for-in
        handler.startDocument();
        handler.startElement("", "Empleados", "Empleados", atts);
        for (Empleado empl : empleados) 
        {
            handler.startElement("", "empleado", "empleado", atts);
            
            handler.startElement("", "id", "id", atts);
            char[] id = String.valueOf(empl.getId()).toCharArray(); 
            handler.characters(id, 0, id.length); 
            handler.endElement("", "id", "id");

            handler.startElement("", "nombre", "nombre", atts);
            char[] nombre = empl.getNombre().toCharArray(); 
            handler.characters(nombre, 0, nombre.length); 
            handler.endElement("", "nombre", "nombre");
            
            handler.startElement("", "apellido1", "apellido1", atts);
            char[] apellido1 = empl.getApellido1().toCharArray(); 
            handler.characters(apellido1, 0, apellido1.length); 
            handler.endElement("", "apellido1", "apellido1");
            
            handler.startElement("", "apellido2", "apellido2", atts);
            char[] apellido2 = empl.getApellido2().toCharArray(); 
            handler.characters(apellido2, 0, apellido2.length); 
            handler.endElement("", "apellido2", "apellido2");
            
            
            
            handler.startElement("", "salario", "salario", atts);            
            char[] salario = String.valueOf(empl.getSalario()).toCharArray(); 
            handler.characters(salario, 0, salario.length); 
            handler.endElement("", "salario", "salario");
            
            handler.endElement("", "empleado", "empleado");
            /*
            //El id, se añade como atributo para ver el uso de atributos, podría ser un elemento más
            //Primero añadimos a atts el numero de atributo que vaya a tener la etiqueta,despues creamos la etiqueta con startElement, y limpiamos
            la variable atts porque si no seguira añadiendo esos atributos al resto de etiquetas.
            atts.addAttribute("", "id", "id", "", String.valueOf(empl.getId())); 
            handler.startElement("", "empleado", "empleado", atts); 
            atts.clear();
            handler.startElement("", "nombre", "nombre", atts); 
            char[] nombre = empl.getNombre().toCharArray(); 
            handler.characters(nombre, 0, nombre.length); 
            handler.endElement("", "nombre", "nombre");
            */        
        }    
        handler.endElement("", "Empleados", "Empleados");
        handler.endDocument();
    }  

    @Override
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return null;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEntityResolver(EntityResolver resolver) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntityResolver getEntityResolver() {
        return null;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDTDHandler(DTDHandler handler) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DTDHandler getDTDHandler() {
        return null;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContentHandler(ContentHandler handler) {
        this.handler = handler;
    }

    @Override
    public ContentHandler getContentHandler() {
        return handler;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setErrorHandler(ErrorHandler handler) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parse(String systemId) throws IOException, SAXException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}    