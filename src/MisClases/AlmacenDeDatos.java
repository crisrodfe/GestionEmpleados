package MisClases;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.xml.sax.InputSource;

/**
 * @author CrisRodFe
 */

/*Cada vez que una método recupere datos de disco, éstos serán cargados en el array miArray. 
 * Los datos que contenga serán borrados/sobreescritos(excepto si queremos añadir un elemento).
*/

public class AlmacenDeDatos extends InputSource implements Serializable
{
    private ArrayList<Empleado> miArray= new ArrayList<>();
    private Empleado em = new Empleado();
    private Scanner sc = new Scanner(System.in);
    
    public ArrayList<Empleado> getMiArray() 
    {
        return miArray;
    }

    public void setMiArray(ArrayList<Empleado> miArray) 
    {
        this.miArray = miArray;
    }
    
    public void mostrarArray()
    {
        if(miArray.isEmpty())
        {
            System.out.println("El array está vacío.");
        }
        else
        {    
            System.out.println(miArray.toString());
        }
    }
    
    public ArrayList cargarDatosAleatorios(int elementos)
    {
        miArray.clear();//Eliminamos datos del array antes de cargar una nueva colecccion.
        Empleado.setIdentificador(1);//Reiniciamos la variable estatica Identificador para que comience a asignar Id desde el numero 1
        for(int i = 0; i<elementos; i++)
        {
            miArray.add(em.generarEmpleadoAleatorio());
        }     
        
        return miArray;
    }
    
    public void añadirAlArray()
    {
        //Añade un nuevo empleado a partir de los datos introducidos por teclado, el id se asigna automaticamente.
       System.out.print("Introduzca el nombre: ");
       String nombre = sc.next();
       System.out.print("Introduzca el primer apellido: ");
       String apellido1 = sc.next();
       System.out.print("Introduzca el segundo apellido: ");
       String apellido2 = sc.next();
       System.out.print("Introduzca el salario: ");
       float salario = sc.nextFloat();

       Empleado nuevo = new Empleado(nombre,apellido1,apellido2,salario);
       miArray.add(nuevo);
       System.out.print("\nEmpleado dado de alta correctamente:\n"+nuevo.toString());
    } 
 
}
 
