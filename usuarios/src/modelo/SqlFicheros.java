/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.PreparedStatement;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zorro
 */
public class SqlFicheros extends Conexion{
    public String[] getIndicadores()
    {      String [] Result = null;
        ArrayList<String> Sa=new  ArrayList<String>();
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT EvidenciaFolder FROM evidencias ";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            
            rs=ps.executeQuery();
            
            while (rs.next())
            {
                Sa.add(rs.getString("EvidenciaFolder"));
            }
            Result=new String[Sa.size()];
            for(int i=0;i<Sa.size(); i++)
                Result[i]=Sa.get(i);
            return Result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return Result;
        }  
    }
    public String[] getArchivos(String archivo)
    {   String [] Result = null;
        ArrayList<String> Sa=new  ArrayList<String>();
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT NombreArch FROM archivos where EvidenciaFolder = ?";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, archivo);
            rs=ps.executeQuery();
            
            while (rs.next())
            {
                Sa.add(rs.getString("NombreArch"));
            }
            Result=new String[Sa.size()];
            for(int i=0;i<Sa.size(); i++)
                Result[i]=Sa.get(i);
            return Result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return Result;
        }  
    }

    public void SendFile(File f1, String nombre, String folder) throws FileNotFoundException {
        PreparedStatement ps = null; 
        Connection con = getConexion();
        
        String sql= "INSERT INTO archivos (NombreArch,Autor,EvidenciaFolder,Archivo)VALUES(?,?,?,?)";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, f1.getName());
            ps.setString(2, nombre);
            ps.setString(3, folder);
            
            FileInputStream fis = new FileInputStream(f1);
            ps.setBinaryStream(4, fis, (int) f1.length());
            ps.execute();
           
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
    }

    public String obtenerAutor(String elegido, String categoria) {
        String Result = null;
        ArrayList<String> Sa=new  ArrayList<String>();
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT Autor FROM archivos where  NombreArch = ? and EvidenciaFolder = ?";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, elegido);
            ps.setString(2, categoria);
            rs=ps.executeQuery();
            
            while (rs.next())
            {
                Sa.add(rs.getString("Autor"));
            }
            
            //for(int i=0;i<Sa.size(); i++)
                Result=Sa.get(0);
            return Result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return Result;
        }
    }

    public String obtenerComment(String elegido, String categoria) {
        String Result = null;
        ArrayList<String> Sa=new  ArrayList<String>();
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT observaciones FROM archivos where  NombreArch = ? and EvidenciaFolder = ?";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, elegido);
            ps.setString(2, categoria);
            rs=ps.executeQuery();
            
            while (rs.next())
            {
                Sa.add(rs.getString("observaciones"));
            }
            
            
                Result=Sa.get(0);
                if(Result==null)Result="";
            return Result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            if(Result==null)Result="";
            return Result;
        }
    }

    public boolean obtenerAprovado(String elegido, String categoria) {
        boolean Result = false;
        int Sa=0;
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT Validado FROM archivos where  NombreArch = ? and EvidenciaFolder = ?";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, elegido);
            ps.setString(2, categoria);
            rs=ps.executeQuery();
            
            while (rs.next())
            {
                Sa=rs.getInt("Validado");
            }
            
           
                if(Sa!=1)Result=false;
            return Result;
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            
            return Result;
        } //To change body of generated method
    }
    public void ejecutar_archivoPDF(String elegido, String categoria) throws IOException {
        
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
         byte[] b = null;
        String sql= "SELECT archivo FROM archivos where  NombreArch = ? and EvidenciaFolder = ?";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, elegido);
            ps.setString(2, categoria);
            rs=ps.executeQuery();
           
            
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("new.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            
          
        } //To change body of generated method
    }
    
    
    
}
