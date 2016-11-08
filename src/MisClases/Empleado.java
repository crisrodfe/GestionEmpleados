package MisClases;

import java.io.Serializable;
import org.xml.sax.InputSource;
/**
 * @author CrisRodFe
 */
public class Empleado extends InputSource implements Serializable
{
    private static String nombres[] = {"Maria","Juan","Luis","Laura","Carla","Manuel","Javier","Alba","Arturo","Juan","Ana","Lucas"};
    private static String apellidos[] = {"Perez","Lopez","Garcia","Alvarez","Gomez","Sanchez","Fernandez","Delgado","Campos","Franco","Saenz"};
    private static float salarios[] = {1245,1231,985,980,1354,1524,897,987,1435,1543,1200,1300};
    
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float salario;
    private static int identificador = 1;
    
//Constructor con parámetros
    public Empleado(int id, String nombre, String apellido1, String apellido2, float salario) 
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.salario = salario;
    }
//Constructor que automaticamente añade un identificador unico y consecutivo a cada objeto creado    
    public Empleado(String nombre, String apellido1, String apellido2, float salario) 
    {
        this.id = identificador++;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.salario = salario;
    }
    
    //Genera un objeto Empleado de manera aleatoria.Como indice del array donde tenemos guardados diferentes datos he usado un generador de numero aleatorio.
    public Empleado generarEmpleadoAleatorio()
    {
        String n = nombres[(int) (Math.random()*nombres.length)];
        String a1 = apellidos[(int) (Math.random()*apellidos.length)];
        String a2 = apellidos[(int) (Math.random()*apellidos.length)];
        float s = salarios[(int) (Math.random()*salarios.length)];
        Empleado nuevoEmpleado = new Empleado(n,a1,a2,s);
        return nuevoEmpleado;
    }
    
    public Empleado()
    {    
    }

    //Getters y setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public static int getIdentificador() {
        return identificador;
    }

    public static void setIdentificador(int identificador) {
        Empleado.identificador = identificador;
    }
    
    
    @Override
    //Devuelve los datos de los objetos contenidos en el array.
    public String toString() 
    {
        return "Id=" + id + ", Nombre=" + nombre + ", Apellidos=" + apellido1 +" "+ apellido2 + ", Salario=" + salario+"\n";
    }
    
    
}
