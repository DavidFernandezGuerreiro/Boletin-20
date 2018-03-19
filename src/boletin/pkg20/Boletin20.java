
package boletin.pkg20;

import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Boletin20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Metodos obx=new Metodos();
        
        boolean opc=true;
        
        while(opc==true){
            int op=Integer.parseInt(JOptionPane.showInputDialog("0.-Sair do menú."
                    + "\n1.-Engadir libros."
                    + "\n2.-Consultar libro."
                    + "\n3.-Visualizar ficheiro."
                    + "\n4.-Borrar os libros sen unidades dispoñibles."
                    + "\n5.-Modificar o prezo dun libro."));
            switch(op){
                case 0:
                    opc=false;
                    break;
                case 1:
                    obx.engadir();
                    break;
                case 2:
                    obx.consultar();
                    break;
                case 3:
                    obx.visualizar();
                    break;
                case 4:
                    obx.borrar();
                    break;
                case 5:
                    obx.modificar();
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "** OPCION INCORRECTA **");
            }
        }
    }
    
}
