/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;

/**
 *
 * @author daniel
 */
public class MainServer extends JFrame{
    public MainServer() throws IOException{
    super("MI INTERFAZ CON JFRAME...");
//-- CONFIGURAMOS LA VENTANA
    JPanel panelArbol=new JPanel();
    JTree jTree1=new JTree();
    TreeModel modelo=new FileSystemModel(new File("/CACEI"));
    jTree1.setModel(modelo);
    System.out.println(""+modelo);
    //panelArbol.
    
    panelArbol.add(jTree1);
    panelArbol.setSize(200, 400);
    add(panelArbol);
// LE PONEMOS UN TITULO


//-- LE DAMOS UN TAMAÑO A LA VENTANA
    this.setSize(600,400);
 
//-- HACEMOS QUE LA VENTANA SE CIERRE POR DEFAULT
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    Servidor serv = new Servidor();
    serv.setModelo(modelo);
    serv.startServer();

}
 
//-- HACEMOS NUESTRO METODO MAIN
public static void main(String[] ARGS) throws IOException{

//-- HACEMOS QUE SE INICIALIZE NUESTRA VENTANA JFRAME
MainServer MI_INTERFAZ = new MainServer();

 
//-- HACEMOS QUE NUESTRA VENTANA SE VISIBLE

    
   
}
}
