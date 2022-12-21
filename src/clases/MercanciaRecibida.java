/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JUAN CARLOS ASTAIZA
 */
public class MercanciaRecibida {
    private String fecha;
    private String hora;
    private int cantidad;
    private String id_prod;
    private String id_prov;
    private String id_emple;

    public MercanciaRecibida(String fecha, String hora, int cantidad, String id_prod, String id_prov, String id_emple) {
        this.fecha = fecha;
        this.hora = hora;
        this.cantidad = cantidad;
        this.id_prod = id_prod;
        this.id_prov = id_prov;
        this.id_emple = id_emple;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getId_prod() {
        return id_prod;
    }

    public void setId_prod(String id_prod) {
        this.id_prod = id_prod;
    }

    public String getId_prov() {
        return id_prov;
    }

    public void setId_prov(String id_prov) {
        this.id_prov = id_prov;
    }

    public String getId_emple() {
        return id_emple;
    }

    public void setId_emple(String id_emple) {
        this.id_emple = id_emple;
    }
    
    
    public boolean grabar(){
        String cadenaSQL="INSERT INTO entrada_mercancias(fecha,hora,cantidad,id_prod,id_prov,id_emp) VALUES ('"+this.fecha+"','"+this.hora+"','"+this.cantidad+"','"+this.id_prod+"','"+this.id_prov+"','"+this.id_emple+"')";
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            sentencia.execute();
            conector.desconectar();
            return true; 
        } catch (Exception e) {
            System.out.println("Error al ejecutar "+cadenaSQL+"/n"+e.getMessage());
            conector.desconectar();
            return false;
        }        
    }
     
    public static ArrayList<String> llenar_comboProductos (){
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
       ArrayList<String> lista = new ArrayList<String> ();
        String cadenaSQL = "Select * FROM productos;"  ;
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            while (resultado.next()) {                
                lista.add(resultado.getString("id")+"-"+resultado.getString("codigo")+"-"+resultado.getString("nombre"));
            }
            conector.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return lista;
        }
 
    }
    
    public static ArrayList<String> llenar_comboProvedores (){
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
       ArrayList<String> lista = new ArrayList<String> ();
        String cadenaSQL = "Select * FROM proveedor;"  ;
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            while (resultado.next()) {                
                lista.add(resultado.getString("id")+"-"+" ("+resultado.getString("nit")+") "+resultado.getString("empresa"));
            }
            conector.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return lista;
        }
 
    }
    
    
    public static ArrayList<String> llenar_comboEmpleados(){
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
       ArrayList<String> lista = new ArrayList<String> ();
        String cadenaSQL = "Select * FROM empleado;"  ;
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            while (resultado.next()) {                
                lista.add(resultado.getString("id")+"-"+resultado.getString("nombres")+" "+resultado.getString("apellido"));
            }
            conector.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return lista;
        }
 
    }
    
    public static int getCantidadDatos(){
        int n=0;
        Conector conector=new Conector();
        Connection conexion=conector.conectar();    
        String cadenaSQL="select count(id) as cantidad from entrada_mercancias;";
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            resultado.next();
            n=resultado.getInt("cantidad");
            return n;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return 0;
        }
    }
    
    
    public static String[][] getLista(){
        String[][] datos=new String[MercanciaRecibida.getCantidadDatos()][6];
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
        String cadenaSQL="SELECT mer.fecha,mer.hora,mer.cantidad,prod.nombre as producto, \n" +
            "concat_ws(' ', prove.nombre, prove.apellido) as proveedor,\n" +
            "concat_ws(' ', emple.nombres, emple.apellido) as empleado\n" +
            "FROM entrada_mercancias as mer,productos as prod,proveedor as prove,empleado as emple\n" +
            "WHERE mer.id_prod=prod.id and mer.id_prov=prove.id and\n" +
            "mer.id_emp=emple.id;";    
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            int i=0;
            while (resultado.next()) {                
                datos[i][0]=resultado.getString("fecha");
                datos[i][1]=resultado.getString("hora");
                datos[i][2]=resultado.getString("cantidad");
                datos[i][3]=resultado.getString("producto");
                datos[i][4]=resultado.getString("proveedor");
                datos[i][5]=resultado.getString("empleado");
                i++;
            }
            conector.desconectar();
            return datos;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return datos;
        }
    }
    
    public static MercanciaRecibida[] getListaEnObjetos(){
        String[][] datos=MercanciaRecibida.getLista();
        MercanciaRecibida[] mercancia= new MercanciaRecibida[datos.length];
        for (int i = 0; i < datos.length; i++) {
            mercancia[i]=new MercanciaRecibida(datos[i][0], datos[i][1], Integer.parseInt(datos[i][2]), datos[i][3], datos[i][4], datos[i][5]);
        }
        return mercancia;
    }
    
     
     
     
     
    
}
