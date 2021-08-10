/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import estructuras.Cola;
import model.Cliente;

/**
 *
 * @author sonia
 */
public class ClienteDAOImpl implements RepositorioDAO<Cliente, Integer> {

    //Para preparar consultas con parametros
    PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    ResultSet rs;
    //Para poder almacenar la conexion
    Connection conexion;
    //Para obtener la conexion
    AccesoDB acceso;

    public ClienteDAOImpl() {
        acceso = new AccesoDB();
    }

    @Override
    public boolean create(Cliente e) {
        boolean resultado = true;
        try {
            //Obtener la conexion a la BD
            conexion = acceso.getConexion();
            //Preparamos la consulta a ejecutar
            ps = conexion.prepareStatement("INSERT INTO CLIENTES VALUES(?,?,?,?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getUsuario());
            ps.setString(2, e.getContrasena());
            ps.setString(3, e.getNombres());
            ps.setString(4, e.getApellidos());
            ps.setInt(5, e.getEdad());
            ps.setString(6, e.getSexo());
            ps.setString(7, e.getEmail());
            ps.setInt(8, e.getNumerocuenta());

            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }

    @Override
    public boolean update(Cliente actualizado) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE clientes SET contrasenia = ?, usuario = ?, nombre = ?, apellido = ?, edad = ?, sexo = ?, email = ?, numcuenta = ?"
                    + "WHERE usuario = ?");
            
            ps.setString(1, actualizado.getContrasena());        
            ps.setString(2, actualizado.getNombres());
            ps.setString(3, actualizado.getApellidos());
            ps.setInt(4, actualizado.getEdad());
            ps.setString(5, actualizado.getSexo());
            ps.setString(6, actualizado.getEmail());            
            ps.setInt(7, actualizado.getNumerocuenta());
            ps.setString(8, actualizado.getUsuario());
            
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }
    
    
   public boolean updateC(Cliente modificado, String nuevacontra) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE clientes SET contrasenia = ?"
                    + "WHERE usuario = ?");
                       
            ps.setString(1, nuevacontra);
            ps.setString(2, modificado.getUsuario());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }
    public List<Cliente> consultarUsuarios() {
      //  Cola<Cliente> colaClientes = new Cola<>();
        List<Cliente> clientes =new ArrayList<>();
        Cliente user;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("select usuario,contrasenia,nombre,apellido,edad,sexo,email from clientes where usuario = ?");

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new Cliente();
                //Leeemos de la bd
                String usuario = rs.getString("usuario");   
                String contrasenia = rs.getString("contraseña");
                String nombre = rs.getString("nombre");                
                String apellido = rs.getString("apellido");                
                int edad = rs.getInt("edad");                
                String sexo = rs.getString("sexo");                
                String email = rs.getString("email");
                int numc = rs.getInt("numcuenta");
                //Seteamos los valores
                user.setUsuario(usuario);
                user.setNombres(nombre);
                user.setContrasena(contrasenia);
                user.setApellidos(apellido);
                user.setEdad(edad);
                user.setSexo(sexo);
                user.setEmail(email);
                user.setNumerocuenta(numc);
                
                clientes.add(user);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return clientes;

    }
  
    @Override
    public Cliente read(Integer id) {
        Cliente user = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM CLIENTES WHERE USUARIO = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new Cliente();
                //Leeemos de la bd
                String usuario = rs.getString("usuario");
                String contrasenia = rs.getString("contrasenia");
                String nombre = rs.getString("nombre");                
                String apellido = rs.getString("apellido");                
                int edad = rs.getInt("edad");                
                String sexo = rs.getString("sexo");                
                String email = rs.getString("email");
                int numc = rs.getInt("numcuenta");
                //Seteamos los valores
                user.setUsuario(usuario);
                user.setContrasena(contrasenia);
                user.setNombres(nombre);
                user.setApellidos(apellido);
                user.setEdad(edad);
                user.setSexo(sexo);
                user.setEmail(email);
                user.setNumerocuenta(numc);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return user;

    }

    public List<Cliente> leerTodo() {
       // Cola<Cliente> colaClientes = new Cola<>();
       List<Cliente> clientes =new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM CLIENTES");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente nuevouser = new Cliente();
                //Leeemos de la bd
                String usuario = rs.getString("usuario");
                String contrasenia = rs.getString("contrasenia");
                String nombre = rs.getString("nombre");                
                String apellido = rs.getString("apellido");                
                int edad = rs.getInt("edad");                
                String sexo = rs.getString("sexo");                
                String email = rs.getString("email");
                int numc = rs.getInt("numcuenta");
                //Seteamos los valores
                nuevouser.setUsuario(usuario);
                nuevouser.setContrasena(contrasenia);
                nuevouser.setNombres(nombre);
                nuevouser.setApellidos(apellido);
                nuevouser.setEdad(edad);
                nuevouser.setSexo(sexo);
                nuevouser.setEmail(email);
                nuevouser.setNumerocuenta(numc);

                clientes.add(nuevouser);
            }

        } catch (SQLException ex) {

        }
        return clientes;

    }

    public boolean deleteA(String id) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("DELETE FROM CLIENTES WHERE USUARIO = ?");
            ps.setString(1, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }

    public Cliente consultarConUsuario(String id) {
        Cliente clienteBuscado = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("select usuario,nombre,contrasenia,apellido,edad,sexo,email,numcuenta from clientes where usuario = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                clienteBuscado = new Cliente();
                //Leeemos de la bd
                String usuario = rs.getString("usuario");                
                String nombre = rs.getString("nombre");
                String contrasenia = rs.getString("contrasenia");                     
                String apellido = rs.getString("apellido");                
                int edad = rs.getInt("edad");                
                String sexo = rs.getString("sexo");                
                String email = rs.getString("email");
                int numc = rs.getInt("numcuenta");
                //Seteamos los valores
                clienteBuscado.setUsuario(usuario);
                clienteBuscado.setNombres(nombre);
                clienteBuscado.setContrasena(contrasenia);
                clienteBuscado.setApellidos(apellido);
                clienteBuscado.setEdad(edad);
                clienteBuscado.setSexo(sexo);
                clienteBuscado.setEmail(email);
                clienteBuscado.setNumerocuenta(numc);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return clienteBuscado;

    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
