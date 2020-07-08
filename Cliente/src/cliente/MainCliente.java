/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

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
public class MainCliente extends JFrame{
      public MainCliente() throws IOException{
    super("Cliente");
//-- CONFIGURAMOS LA VENTANA
    JPanel panelArbol=new JPanel();
    
    //jTree1.setModel(new FileSystemModel(new File("/CACEI")));
    Cliente cli = new Cliente();
    cli.startClient();
    //panelArbol.
    //JTree jTree1=new JTree();
    //jTree1.setModel(cli.getModelo());
    //System.out.println(""+cli.arbol);
    //panelArbol.add(jTree1);
    panelArbol.setSize(200, 400);
    add(panelArbol);
// LE PONEMOS UN TITULO


//-- LE DAMOS UN TAMAÑO A LA VENTANA
    this.setSize(600,400);
 
//-- HACEMOS QUE LA VENTANA SE CIERRE POR DEFAULT
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
    public static void main(String[] args) throws IOException {
        MainCliente cli = new MainCliente(); //Se crea el cliente
        cli.setVisible(true);
        //System.out.println("Iniciando cliente\n");
        //cli.startClient(); //Se inicia el cliente
    }
}
