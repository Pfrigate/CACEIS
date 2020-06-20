/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author daniel
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;



public class Servidor{
    int port=6969;
    TreeModel ARBOL;
    public Servidor() {
    
    } //Se usa el constructor para servidor de Conexion
    public void setModelo(TreeModel a1){
        ARBOL=a1;
    }
    public TreeModel getModelo(TreeModel a1){
       return ARBOL;
    }
        
    public void startServer()//Método para iniciar el servidor
    {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
 
                System.out.println("New client connected");
 
                ObjectOutputStream salidaObjeto = new ObjectOutputStream(socket.getOutputStream());
            	salidaObjeto.writeObject(ARBOL);
            	System.out.println("objeto enviado");
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
            

            
            //salidaCliente = new DataOutputStream(cs.getOutputStream());
            

    }

            //salidaCliente = new DataOutputStream(cs.getOutputStream());
            

    }
