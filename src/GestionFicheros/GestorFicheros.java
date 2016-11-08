/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFicheros;

import MisClases.AlmacenDeDatos;
import MisClases.Empleado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author CrisRodFe
 */
public class GestorFicheros {
    private Scanner sc = new Scanner(System.in);
    
    @SuppressWarnings("unchecked")
	public void escribirDelimitado(String separador,AlmacenDeDatos ad) throws FileNotFoundException, IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("ficheroDelimitado.txt"));
        String  s;        
        
        if(ad.getMiArray().isEmpty())
        {         
            System.out.print("El array está vacío.\nIntroduzca nº de elementos que quiere crear: ");
            int numElementos = sc.nextInt();
            ad.setMiArray(ad.cargarDatosAleatorios(numElementos));        
        }  
        //Creamos un unico string separado por un caracter introducido como argumento con los datos de cada objeto empleado.
        for(int i = 0; i < ad.getMiArray().size(); i++)
        {
          s = (ad.getMiArray().get(i).getId()+separador+
               ad.getMiArray().get(i).getNombre()+separador+
               ad.getMiArray().get(i).getApellido1()+separador+
               ad.getMiArray().get(i).getApellido2()+separador+
               ad.getMiArray().get(i).getSalario()+separador);
          bw.write(s);
        }
        bw.close();
        System.out.println("\nDatos escritos en archivo correctamente.\n");
    }
    

    public void leerDelimitado(String separador,AlmacenDeDatos ad) throws FileNotFoundException, IOException
    {
        ad.getMiArray().clear();
        
        BufferedReader br = new BufferedReader(new FileReader("ficheroDelimitado.txt"));
        
        //El archivo lo compone una sole línea, de la que iremos extrayendo de manera secuencial cada uno de los datos en formato de cadena de caracteres..
        String linea = br.readLine();
        String datos[] = linea.split(separador);
        for(int i = 0; i < datos.length; i++)
        {               
            int id = Integer.parseInt(datos[i]);  
            i++;
            String n = datos[i]; 
            i++;
            String a1 = datos[i]; 
            i++;
            String a2 = datos[i]; 
            i++;
            float s = Float.parseFloat(datos[i]);
            Empleado e = new Empleado(id,n,a1,a2,s);
            ad.getMiArray().add(e);
        }
        br.close();
        System.out.println("\nLos datos han sido leidos de disco y  cargados en el array:\n ");
    }
    

    public void escribirEncolumnado(int[] dimensiones,AlmacenDeDatos ad) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("ficheroEncolumnado.txt"));
        //El '-' formatea el texto alineandolo a la derecha.
        //%-dimesionTipoDato --> es la manera de formatear
        String s = "%-"+dimensiones[0]+"d%-"+dimensiones[1]+"s%-"+dimensiones[2]+"s%-"+dimensiones[3]+"s%"+dimensiones[4]+".2f%n";
        String conFormato; //Almacenara String.format(micadenadeformateo,misdatos);
     
        if(ad.getMiArray().isEmpty())
        {         
            System.out.print("El array está vacío.\nIntroduzca nº de elementos que quiere crear: ");
            int numElementos = sc.nextInt();
            ad.setMiArray(ad.cargarDatosAleatorios(numElementos));        
        }     
        
        for (int i = 0; i < ad.getMiArray().size(); i++)
        {
            conFormato = String.format(s, ad.getMiArray().get(i).getId(),ad.getMiArray().get(i).getNombre(), ad.getMiArray().get(i).getApellido1(),
                    ad.getMiArray().get(i).getApellido2(),ad.getMiArray().get(i).getSalario());
            bw.write(conFormato);
        }

        bw.close();
        System.out.println("\nDatos escritos en archivo correctamente.\n");
    }
    

    public void leerEncolumnado(int[] d,AlmacenDeDatos ad) throws FileNotFoundException, IOException, ParseException
    {
        ad.getMiArray().clear();
        
        BufferedReader br = new BufferedReader(new FileReader("ficheroEncolumnado.txt"));//Dimesiones iniciales:2-10-10-10-5
        String linea;
        while ((linea = br.readLine()) != null)
        {   
            int i = Integer.valueOf(linea.substring(0,d[0]).trim());//Desde 0 hasta la primera dimension
            String n = linea.substring(d[0],d[0]+d[1]).trim();//desde la primera hasta primera+segunda dimension...etc...
            String a1 = linea.substring(d[0]+d[1],d[0]+d[1]+d[2]).trim();
            String a2 = linea.substring(d[0]+d[1]+d[2],d[0]+d[1]+d[2]+d[3]).trim();
            
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            Number number = format.parse(linea.substring(d[0]+d[1]+d[2]+d[3],linea.length()).trim());
            float s = number.floatValue();
          
            Empleado e = new Empleado(i,n,a1,a2,s);
            ad.getMiArray().add(e); 
           
        }   
        br.close();
        System.out.println("\nLos datos han sido leidos de disco y cargados en el array:\n ");
    }
    

    public void exportarABinario(int numElementos,AlmacenDeDatos ad) throws IOException
    {
        ad.getMiArray().clear();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ficheroBinario.bin"));
        ad.setMiArray(ad.cargarDatosAleatorios(numElementos)); 
        oos.writeObject(ad.getMiArray());
        System.out.println("Datos creados y exportados al archivo correctamente.");  
        oos.close();
    }
    
    public void importarDeBinario(AlmacenDeDatos ad) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ficheroBinario.bin"));
        ad.getMiArray().clear();
        ad.setMiArray((ArrayList<Empleado>) ois.readObject());
        System.out.println("Datos leidos de disco correctamente: ");
        System.out.println(ad.getMiArray().toString());
        
        ois.close();

    }
}
