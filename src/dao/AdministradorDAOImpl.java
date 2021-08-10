/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import estructuras.ListaCircularDoble;
import estructuras.ListaDoble;

/**
 *
 * @author Lab02-Pc11
 */
public class AdministradorDAOImpl implements RepositorioDAO<Administrador, Integer> {

    //Para preparar consultas con parametros
    PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    ResultSet rs;
    //Para poder almacenar la conexion
    Connection conexion;
    //Para obtener la conexion
    AccesoDB acceso;

    public AdministradorDAOImpl() {
        acceso = new AccesoDB();
    }

    @Override
    public boolean create(Administrador e) {
        boolean resultado = true;
        try {
            //Obtener la conexion a la BD
            conexion = acceso.getConexion();
            //Preparamos la consulta a ejecutar
            ps = conexion.prepareStatement("INSERT INTO ADMINISTRADORES VALUES(?,?,?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getUsuario());
            ps.setString(2, e.getContrasena());
            ps.setString(3, e.getNombres());
            ps.setString(4, e.getApellidos());
            ps.setInt(5, e.getEdad());
            ps.setString(6, e.getSexo());
            ps.setString(7, e.getEmail());

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
    public boolean update(Administrador actualizado) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE administradores SET nombre = ?,contrasenia = ?, apellido = ?, edad = ?, sexo = ?, email = ?"
                    + "WHERE usuario = ?");
            
            ps.setString(1, actualizado.getNombres());
            ps.setString(2, actualizado.getContrasena());
            ps.setString(3, actualizado.getApellidos());
            ps.setInt(4, actualizado.getEdad());
            ps.setString(5, actualizado.getSexo());
            ps.setString(6, actualizado.getEmail());
            ps.setString(7, actualizado.getUsuario());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }
    
    
   public boolean updateC(Administrador actualizado, String nuevacontra) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE administradores SET contrasenia = ?"
                    + "WHERE usuario = ?");
            
            ps.setString(1, nuevacontra);
            ps.setString(2, actualizado.getUsuario());
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
    public Administrador read(Integer id) {
        Administrador admin = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM ADMINISTRADORES WHERE USUARIO = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Administrador();
                //Leeemos de la bd
                String usuario = rs.getString("usuario");
                String contrasenia = rs.getString("contrasenia");
                String nombre = rs.getString("nombre");                
                String apellido = rs.getString("apellido");                
                int edad = rs.getInt("edad");                
                String sexo = rs.getString("sexo");                
                String email = rs.getString("email");

                //Seteamos los valores
                admin.setUsuario(usuario);
                admin.setContrasena(contrasenia);
                admin.setNombres(nombre);
                admin.setApellidos(apellido);
                admin.setEdad(edad);
                admin.setSexo(sexo);
                admin.setEmail(email);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return admin;

    }

    public List<Administrador> leerTodo() {
        //ListaDoble<Administrador> listaAdministradores = new ListaDoble<>();
        List<Administrador> listaAdministradores =new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM ADMINISTRADORES");
            rs = ps.executeQuery();

            while (rs.next()) {
                Administrador nuevoAdmin = new Administrador();
                //Leeemos de la bd
                String usuario = rs.getString("usuario");
                String contrasenia = rs.getString("contrasenia");
                String nombre = rs.getString("nombre");                
                String apellido = rs.getString("apellido");                
                int edad = rs.getInt("edad");                
                String sexo = rs.getString("sexo");                
                String email = rs.getString("email");

                //Seteamos los valores
                nuevoAdmin.setUsuario(usuario);
                nuevoAdmin.setContrasena(contrasenia);
                nuevoAdmin.setNombres(nombre);
                nuevoAdmin.setApellidos(apellido);
                nuevoAdmin.setEdad(edad);
                nuevoAdmin.setSexo(sexo);
                nuevoAdmin.setEmail(email);

                listaAdministradores.add(nuevoAdmin);
            }

        } catch (SQLException ex) {

        }
        return listaAdministradores;

    }

    public boolean deleteA(String id) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("DELETE FROM ADMINISTRADORES WHERE USUARIO = ?");
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

    public Administrador consultarConUsuario(String id) {
        Administrador adminBuscado = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("select usuario,nombre,apellido,edad,sexo,email from administradores where usuario = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                adminBuscado = new Administrador();
                //Leeemos de la bd
                String usuario = rs.getString("usuario");
                String nombre = rs.getString("nombre");                
                String apellido = rs.getString("apellido");                
                int edad = rs.getInt("edad");                
                String sexo = rs.getString("sexo");                
                String email = rs.getString("email");

                //Seteamos los valores
                adminBuscado.setUsuario(usuario);
                adminBuscado.setNombres(nombre);
                adminBuscado.setApellidos(apellido);
                adminBuscado.setEdad(edad);
                adminBuscado.setSexo(sexo);
                adminBuscado.setEmail(email);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return adminBuscado;

    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Administrador> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
