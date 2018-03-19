
package boletin.pkg20;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Metodos {
    
    File fich;
    Scanner sc;
    
    String[]lista;
    String linea;
    
    PrintWriter escribir;
    
    Libreria lib;
    
    boolean marca=false;
    
    public void engadir(){
        //Le damos valores al constructor de la clase Libreria.
        String titu=JOptionPane.showInputDialog("Introducir titulo: ");
        String aut=JOptionPane.showInputDialog("Introducir nome do autor: ");
        float prec=Float.parseFloat(JOptionPane.showInputDialog("Introducir prezo do libro: "));
        int uni=Integer.parseInt(JOptionPane.showInputDialog("Introducir unidades disponibles: "));
        
        fich=new File("libreria.txt");
        
        try {
            escribir=new PrintWriter(new FileWriter(fich,true));
            escribir.println(lib=new Libreria(titu,aut,prec,uni));

        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            escribir.close();
        }
    }
    
    public void consultar(){
        fich=new File("libreria.txt");
        
        try {
            String buscar=JOptionPane.showInputDialog("Titulo del libro a buscar: ");
            //Buffer para ler o ficheiro libreria.txt
            final BufferedReader reader=new BufferedReader(new FileReader("libreria.txt"));
            
            //Mientras la linea que le metes el valor reader.readLine(), sea distinto a null. Ejecuta el if.
            while((linea=reader.readLine())!=null){
                marca=false;
                
                //indexOf -> si no encuentra el valor que introduces (buscar), devuelve un -1.
                //Si la busqueda es distinto de -1:
                if(linea.indexOf(buscar)!=-1){
                    marca=true;
                    String[]lista=linea.split("\\s*,\\s*");
                    JOptionPane.showMessageDialog(null, "Titulo: "+lista[0]+", precio: "+lista[2]);
//                    System.out.println("Titulo: "+lista[0]+", precio: "+lista[2]);
                    break;
                }  
            }
            
            if(marca==false){
                JOptionPane.showMessageDialog(null, "** LIBRO NON ATOPADO **");
            }
            reader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void visualizar(){
        fich=new File("libreria.txt");
        
        try {
            //Buffer en modo lectura
            final BufferedReader reader=new BufferedReader(new FileReader("libreria.txt"));
            
            //Mientras la linea que le metes el valor reader.readLine(), sea distinto a null. Que visualice cada linea del fichero.
            while((linea=reader.readLine())!=null){
                System.out.println(linea);
//                JOptionPane.showMessageDialog(null, linea);
            }
            reader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrar(){
        //Creamos dos ficheros
        fich=new File("libreria.txt");
        File fich2=new File("libreria2.txt");
        
        try {
            //Abrimos el fichero en modo lectura
            BufferedReader reader=new BufferedReader(new FileReader("libreria.txt"));
            //Creamos un PrintWriter del fichero 2
            PrintWriter escribir2=new PrintWriter(new FileWriter(fich2,true));
            
            //Mientras haya lineas, la variable lineasSalvadas toma las lineas que queremos guardar.
            while((linea=reader.readLine())!=null){
                String lineasSalvadas=linea;
                
                //Si la linea salvada no contiene "unidades: 0" escribimos esa linea en el nuevo fichero.
                if(lineasSalvadas.contains("unidades: 0")!=true){
                    escribir2.println(lineasSalvadas);
                }
            }
            reader.close();
            escribir2.close();
            fich.delete();
            
            //Renomemos o ficheiro:
            boolean correcto=fich2.renameTo(fich);
            if(correcto){
                System.out.println("ficheiro renomeado");
            }else{
                System.out.println("* ficheiro non renomeado *");
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificar(){
        fich=new File("libreria.txt");
        File fich2=new File("libreria2.txt");
        
        String libroMod=JOptionPane.showInputDialog("Introducir titulo do libro: ");
        float prezoMod=Float.parseFloat(JOptionPane.showInputDialog("Introducir o novo prezo: "));
        
        try {
            //Abrimos en modo lectura
            BufferedReader reader=new BufferedReader(new FileReader("libreria.txt"));
            PrintWriter escribir2=new PrintWriter(new FileWriter(fich2,true));
            
            //Mientras la linea que le metes el valor reader.readLine(), sea distinto a null. Que ejecute el if.
            while((linea=reader.readLine())!=null){
                String lineasSalvadas=linea;
                
                if(lineasSalvadas.contains(libroMod)!=true){
                    escribir2.println(lineasSalvadas);
                }else{
                    
                    //Separamos la linea por comas
                    String[]sep=linea.split("\\s*,\\s*");
                    
                    //Añadimos el precio a un String
                    String precio=sep[2];
                    
                    //Separamos la palabra "precio" del numero.
                    String[]precioSeparado=precio.split("\\s*:\\s*");
                    //A la posición del precio (numero) le damos el valor del nuevo precio que introducimos
                    precioSeparado[1]=String.valueOf(prezoMod);
                    //A la linea le añadimos la cadena entera
                    lineasSalvadas=sep[0]+", "+sep[1]+", "+precioSeparado[0]+": "+precioSeparado[1]+", "+sep[3];
                    escribir2.println(lineasSalvadas);
                    lineasSalvadas="";
                    precio=""; 
                }
            }
            reader.close();
            escribir2.close();
            fich.delete();
            
            //Renomemos o ficheiro:
            boolean correcto=fich2.renameTo(fich);
            if(correcto){
                System.out.println("ficheiro renomeado");
            }else{
                System.out.println("* ficheiro non renomeado *");
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
