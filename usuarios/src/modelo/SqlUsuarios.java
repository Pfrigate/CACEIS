/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import vista.Usuarios;


/**
 *
 * @author jua_c
 */
public class SqlUsuarios extends Conexion {
        
    public boolean registrar(usuarios usr)
    {
        PreparedStatement ps = null; 
        Connection con = getConexion();
        
        String sql= "INSERT INTO usuarios (usuario,password,nombre,correo,id_tipo)VALUES(?,?,?,?,?)";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getCorreo());
            ps.setInt(5, usr.getId_tipo());
            ps.execute();
            return true;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
             
    }
    
     public int existeUsuario(String usuario)
    {
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT count(id) FROM usuarios WHERE usuario = ? ";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                return rs.getInt(1);
            }
          return 1;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        
            
    }
     public boolean login(Usuarios usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT id, usuario, password, nombre, id_tipo FROM usuarios WHERE usuario = ? LIMIT 1";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);///podria haber un error
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getPassword().equals(rs.getString(3))) {
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setIdTipo(rs.getInt(5));
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
     public boolean esEmail(String correo){
     Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
     Matcher mather = pattern.matcher(correo);
        return mather.find();
         }
    public String[] getProfe(){
        String [] Result = null;
        ArrayList<String> Sa=new  ArrayList<String>();
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT nombre FROM usuarios where id_tipo = ?";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, 1);
            rs=ps.executeQuery();
            
            while (rs.next())
            {
                Sa.add(rs.getString("nombre"));
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
    public void EliminarProfe(String Nombre){
        
        
        PreparedStatement ps = null; 
        ResultSet rs = null;
        Connection con = getConexion();
        System.out.println(""+Nombre);
        
        String sql= "delete  FROM usuarios where nombre = ?";
        
        try {
            ps =(PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, Nombre);
            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
