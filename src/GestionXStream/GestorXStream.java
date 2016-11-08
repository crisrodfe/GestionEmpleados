/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionXStream;

import MisClases.*;
import com.thoughtworks.xstream.XStream;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author CrisRodFe
 */
public class GestorXStream 
{
    public void leerXStream(AlmacenDeDatos ad) throws FileNotFoundException  
    {
        XStream xStream = new XStream();
        
        xStream.alias("empleado", Empleado.class);
        xStream.alias("Empleados", List.class);//Si no, por defecto el elemento raiz queda nombrado como 'list'.
        xStream.addImplicitCollection(AlmacenDeDatos.class, "miArray");
        
        ad.setMiArray((ArrayList<Empleado>) xStream.fromXML(new FileInputStream("EmpleadosXStream.xml")));
        System.out.println("Los datos han sido leidos de disco y añadidos a la colección correctamente.");
    }

    public void escribirXStream(AlmacenDeDatos ad) throws FileNotFoundException 
    {
        if(ad.getMiArray().isEmpty())
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("El array está vacío.\nIntroduzca número de elementos a añadir:");
            ad.setMiArray(ad.cargarDatosAleatorios(sc.nextInt()));
        }

        XStream xStream = new XStream();

        xStream.alias("empleado", Empleado.class);
        xStream.alias("Empleados", List.class);
        xStream.addImplicitCollection(AlmacenDeDatos.class, "miArray");
        
        //El primer argumento es nuestra coleccion de datos, el segundo es el archivo xml que crearemos.
        xStream.toXML(ad.getMiArray(),new FileOutputStream("EmpleadosXStream.xml"));
        
        System.out.println("Los datos se han escrito correctamente en el fichero.");

    } 
}
