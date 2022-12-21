/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JUAN CARLOS ASTAIZA
 */

//Usuario= Empleados y Administradores
public class Usuario {
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String cargo;
    private String usuario;
    private String clave;
    private String rol;

    public Usuario(String cedula, String nombres, String apellidos, String direccion, String telefono, String cargo, String usuario, String clave, String rol) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cargo = cargo;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
    }

       

    public Usuario(String dato,String tipo) {
        if(tipo=="cedula"){
            String[][] datos=getBuscar(dato);
           if (datos!=null){
               this.cedula = dato;
               this.nombres = datos[0][1];
               this.apellidos = datos[0][2];
               this.direccion = datos[0][3];
               this.telefono = datos[0][4];
               this.cargo = datos[0][5];
               this.usuario = datos[0][6];
               this.clave = datos[0][7];
               this.rol = datos[0][8];
           } 
        }else{
            String[][] datos=getBuscarDatos(dato);
            if (datos!=null){
                this.cedula = datos[0][0];
                this.nombres = datos[0][1];
                this.apellidos = datos[0][2];
                this.direccion = datos[0][3];
                this.telefono = datos[0][4];
                this.cargo = datos[0][5];
                this.usuario = dato;
                this.clave = datos[0][7];
                this.rol = datos[0][8];
            } 
        }
        
    }
    

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
       
    
   
 
    public boolean grabar(){
        String cadenaSQL="INSERT INTO empleado(cedula,nombres,apellido,direccion,telefono,cargo,usuario,clave, rol) VALUES ('"+this.cedula+"','"+this.nombres+"','"+this.apellidos+"','"+this.direccion+"','"+this.telefono+"','"+this.cargo+"','"+this.usuario+"','"+this.clave+"','"+this.rol+"')";
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

    
    
    public static boolean validar(String usuario,String clave){
        boolean valido=false;
        Conector conector=new Conector();
        Connection conexion=conector.conectar();    
        String cadenaSQL="select nombres from empleado where usuario='"+usuario+"' and clave='"+clave+"';";
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            if (resultado.next()) {//Mueve el puntero en el siguiente registro,si no hay devuelve false
                valido=true;
            } 
            conector.desconectar();
            return valido;
        } catch (Exception e) {
            System.out.println("Ocurrio un error en: "+cadenaSQL+"\n"+e.getMessage());
            conector.desconectar();
            return valido;
        }
    }     
      
    public static int getCantidadDatos(){
        int n=0;
        Conector conector=new Conector();
        Connection conexion=conector.conectar();    
        String cadenaSQL="select count(id) as cantidad from empleado;";
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
        String[][] datos=new String[Usuario.getCantidadDatos()][9];
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
        String cadenaSQL="select * from empleado";    
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            int i=0;
            while (resultado.next()) {                
                datos[i][0]=resultado.getString("cedula");
                datos[i][1]=resultado.getString("nombres");
                datos[i][2]=resultado.getString("apellido");
                datos[i][3]=resultado.getString("direccion");
                datos[i][4]=resultado.getString("telefono");
                datos[i][5]=resultado.getString("cargo");
                datos[i][6]=resultado.getString("usuario");
                datos[i][7]=resultado.getString("clave");
                datos[i][8]=resultado.getString("rol");
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
    
    public static Usuario[] getListaEnObjetos(){
        String[][] datos=Usuario.getLista();
        Usuario[] usuarios= new Usuario[datos.length];
        for (int i = 0; i < datos.length; i++) {
            usuarios[i]=new Usuario(datos[i][0], datos[i][1], datos[i][2], datos[i][3], datos[i][4], datos[i][5], datos[i][6], datos[i][7], datos[i][8]);
        }
        return usuarios;
    }
    
    
    
    public static String[][] getBuscarDatos(String usuario){
        String[][] datos=new String[getCantidadDatos()][9];
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
        String cadenaSQL="select cedula,nombres,apellido,direccion,telefono,cargo,usuario,clave,rol from empleado where usuario='"+usuario+"';";
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            int i=0;
            while (resultado.next()) {                
                datos[i][0]=resultado.getString("cedula");
                datos[i][1]=resultado.getString("nombres");
                datos[i][2]=resultado.getString("apellido");
                datos[i][3]=resultado.getString("direccion");
                datos[i][4]=resultado.getString("telefono");
                datos[i][5]=resultado.getString("cargo");
                datos[i][6]=resultado.getString("usuario");
                datos[i][7]=resultado.getString("clave");
                datos[i][8]=resultado.getString("rol");
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
    
    
     public static String[][] getBuscar(String cedula){
        String[][] datos=new String[getCantidadDatos()][9];
        Conector conector=new Conector();
        Connection conexion=conector.conectar();
        String cadenaSQL="select cedula,nombres,apellido,direccion,telefono,cargo,usuario,clave,rol from empleado where cedula='"+cedula+"';";    
        try {
            PreparedStatement sentencia=conexion.prepareStatement(cadenaSQL);
            ResultSet resultado=sentencia.executeQuery();
            int i=0;
            while (resultado.next()) {                
                datos[i][0]=resultado.getString("cedula");
                datos[i][1]=resultado.getString("nombres");
                datos[i][2]=resultado.getString("apellido");
                datos[i][3]=resultado.getString("direccion");
                datos[i][4]=resultado.getString("telefono");
                datos[i][5]=resultado.getString("cargo");
                datos[i][6]=resultado.getString("usuario");
                datos[i][7]=resultado.getString("clave");
                datos[i][8]=resultado.getString("rol");
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
        String cadenaSQL="update empleado set nombres='" + this.nombres + "', apellido='" + this.apellidos + "', direccion='" + this.direccion + "', telefono='" + this.telefono + "', cargo='" + this.cargo + "', usuario='" + this.usuario + "', clave='" + this.clave + "', rol='" + this.rol + "' where cedula=" + this.cedula + ";";
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
        String cadenaSQL="delete from empleado where cedula="+this.cedula+";";
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
    
     
     
     
    
    public static void main(String[] args) {
       //Configuracion Para poder comprobar si esta correcta la sintaxis grabar(); 
       // Usuario usuario=new Usuario("ppa","123");
       // usuario.grabar();
       //____________________________________________________________________
        //Sirve para comprobar la validación
        System.out.println(Usuario.validar("juanca", "123"));
    }
    
}
