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
public class Productos {
    private String codigo;
    private String nombre;
    private String existencias;
    private String marca;
    private String fecha_vencimiento;
    private String id_categoria;
    private String id_subcategoria;

    public Productos(String codigo, String nombre, String existencias, String marca, String fecha_vencimiento, String id_categoria, String id_subcategoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.existencias = existencias;
        this.marca = marca;
        this.fecha_vencimiento = fecha_vencimiento;
        this.id_categoria = id_categoria;
        this.id_subcategoria = id_subcategoria;
    }

    public Productos(String codigo) {
        String[][] datos=getBuscar(codigo);
        if (datos!=null){
            this.codigo=codigo;
            this.nombre = datos[0][1];
            this.existencias = datos[0][2];
            this.marca = datos[0][3];
            this.fecha_vencimiento = datos[0][4];
            this.id_categoria = datos[0][5];
            this.id_subcategoria = datos[0][6];
        }

    } 
   
   
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExistencias() {
        return existencias;
    }

    public void setExistencias(String existencias) {
        this.existencias = existencias;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(String id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public boolean grabar(){
        String cadenaSQL="INSERT INTO productos(codigo,nombre,existencias,marca,fecha_vencimiento,id_categoria,id_subcategoria) VALUES ('"+this.codigo+"','"+this.nombre+"','"+this.existencias+"','"+this.marca+"','"+this.fecha_vencimiento+"','"+this.id_categoria+"','"+this.id_subcategoria+"')";
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
    public static int getCantidadDatos(){
        int n=0;
        Conector conector=new Conector();
        Connection conexion=conector.conectar();    
        String cadenaSQL="select count(id) as cantidad from productos;";
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
        String[][] datos=new String[Productos.getCantidadDatos()][7];
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
        String cadenaSQL="SELECT productos.codigo as cp,productos.nombre as np,productos.existencias as ep,productos.marca as mp,"
                + "productos.fecha_vencimiento as fp, categoria.nombre as nc, subcategoria.nombre as nsb "
                + "FROM productos,categoria,subcategoria WHERE productos.id_categoria=categoria.id and productos.id_subcategoria=subcategoria.id; ";    
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            int i=0;
            while (resultado.next()) {                
                datos[i][0]=resultado.getString("cp");
                datos[i][1]=resultado.getString("np");
                datos[i][2]=resultado.getString("ep");
                datos[i][3]=resultado.getString("mp");
                datos[i][4]=resultado.getString("fp");
                datos[i][5]=resultado.getString("nc");
                datos[i][6]=resultado.getString("nsb");
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
    
      public static Productos[] getListaEnObjetos(){
        String[][] datos=Productos.getLista();
        Productos[] productos= new Productos[datos.length];
        for (int i = 0; i < datos.length; i++) {
            productos[i]=new Productos(datos[i][0], datos[i][1], datos[i][2], datos[i][3], datos[i][4], datos[i][5], datos[i][6]);
        }
        return productos;
    }
    
      
    public static ArrayList<String> llenar_combocategoria (){
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
       ArrayList<String> lista = new ArrayList<String> ();
        String cadenaSQL = "Select * FROM categoria;"  ;
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            while (resultado.next()) {                
                lista.add(resultado.getString("id")+"-"+resultado.getString("nombre"));
            }
            conector.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return lista;
        }
 
    }
    
     public static ArrayList<String> llenar_combosubcategoria (String id){
        Conector conector=new Conector();
         Connection conexion=conector.conectar();
       ArrayList<String> lista = new ArrayList<String> ();
        String cadenaSQL = "Select * FROM subcategoria where id_categoria='"+id+"';";
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            while (resultado.next()) {                
                lista.add(resultado.getString("id")+"-"+resultado.getString("nombre"));
            }
            conector.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return lista;
        } 
    } 

    private String[][] getBuscar(String codigo) {
        String[][] datos=new String[getCantidadDatos()][7];
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
        String cadenaSQL="SELECT productos.codigo as cp,productos.nombre as np,productos.existencias as ep,"
                + "productos.marca as mp, productos.fecha_vencimiento as fp, concat_ws(\"-\",categoria.id,categoria.nombre) "
                + " as nc, concat_ws(\"-\",subcategoria.id,subcategoria.nombre) as nsb FROM productos,categoria,subcategoria WHERE productos.id_categoria=categoria.id"
                + " and productos.id_subcategoria=subcategoria.id and productos.codigo='"+codigo+"'";    
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            int i=0;
            while (resultado.next()) {                
                datos[i][0]=resultado.getString("cp");
                datos[i][1]=resultado.getString("np");
                datos[i][2]=resultado.getString("ep");
                datos[i][3]=resultado.getString("mp");
                datos[i][4]=resultado.getString("fp");
                datos[i][5]=resultado.getString("nc");
                datos[i][6]=resultado.getString("nsb");
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
    
    public boolean modificar() {
        String cadenaSQL="update productos set codigo='" + this.codigo + "', nombre='" + this.nombre + "', existencias='" + this.existencias + "', marca='" + this.marca + "', fecha_vencimiento='" + this.fecha_vencimiento + "', id_categoria='" + this.id_categoria + "', id_subcategoria='" + this.id_subcategoria+ "' where codigo=" + this.codigo + ";";
        Conector conector=new Conector();
        Connection conexion= conector.conectar();
        try {
            PreparedStatement sentencia= conexion.prepareStatement(cadenaSQL);
            sentencia.execute();
            conector.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ejecutar"+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return false;
        }
    }
    
    
    public boolean modificarExistencia(String id) {
        String cadenaSQL="update productos set existencias='" + this.existencias + "' where id=" + id + ";";
        Conector conector=new Conector();
        Connection conexion= conector.conectar();
        try {
            PreparedStatement sentencia= conexion.prepareStatement(cadenaSQL);
            sentencia.execute();
            conector.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println("Error al ejecutar"+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return false;
        }
    }
    
    
    public boolean eliminar() {
        String cadenaSQL="delete from productos where codigo="+this.codigo+";";
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
    
    
    
    
}
