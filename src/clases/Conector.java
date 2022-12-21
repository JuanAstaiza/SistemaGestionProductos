/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author adsi
 */
public class Conector { 
    private String servidor;
    private String puerto;
    private String usuario;
    private String clave;
    private String bd;
    private Connection conexion;   
    
    public  Conector(){
        servidor="localhost";//127.0.0.1
        puerto="3306";
        usuario="root";
        clave="";
        bd="management_system";
    }
    
    public Connection conectar(){
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           conexion=DriverManager.getConnection("jdbc:mysql://"+servidor+":"+puerto+"/"+bd,usuario,clave);
           System.out.println("Conectado a la BASE DE DATOS: "+bd);
           return conexion;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de Conectar a la BASE DE DATOS: "+bd); 
            return null;
        }
    }
    
    public void desconectar(){
        try {
            conexion.close();
            System.out.println("Desconectado de la BASE DE DATOS: "+bd);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al Desconectar la BASE DE DATOS: "+bd);
        }    
    }
    
    public static void main(String[] args){
        Conector conector= new Conector();
        conector.conectar();
        conector.desconectar();   
    }
}

