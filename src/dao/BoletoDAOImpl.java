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
import model.Boleto;

/**
 *
 * @author sonia
 */
public class BoletoDAOImpl implements RepositorioDAO<Boleto, Integer> {

    //Para preparar consultas con parametros
    PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    ResultSet rs;
    //Para poder almacenar la conexion
    Connection conexion;
    //Para obtener la conexion
    AccesoDB acceso;

    public BoletoDAOImpl() {
        acceso = new AccesoDB();
    }

    @Override
    public boolean create(Boleto e) {
        boolean resultado = true;
        try {
            //Obtener la conexion a la BD
            conexion = acceso.getConexion();
            //Preparamos la consulta a ejecutar
            ps = conexion.prepareStatement("INSERT INTO BOLETO VALUES(?,?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getUsuariocliente());
            ps.setInt(2, e.getCodvuelo());

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
    public boolean update(Boleto actualizado) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE boleto "
                    + "WHERE cliente = ?, vuelo = ?");
            
            ps.setString(1, actualizado.getUsuariocliente());
            ps.setInt(2, actualizado.getCodvuelo());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }

    public Boleto readCliente(String id) {
        Boleto user = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM BOLETO WHERE CLIENTE = ?, VUELO = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new Boleto();
                //Leeemos de la bd
                String cliente = rs.getString("cliente");
                int vuelo = rs.getInt("vuelo");
                
                //Seteamos los valores
                user.setUsuariocliente(cliente);
                user.setCodvuelo(vuelo);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return user;

    }
    @Override
    public Boleto read(Integer id) {
        Boleto user = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM BOLETO WHERE CLIENTE = ?, VUELO = ?");
            ps.setInt(2, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new Boleto();
                //Leeemos de la bd
                String cliente = rs.getString("cliente");
                int vuelo = rs.getInt("vuelo");
                
                //Seteamos los valores
                user.setUsuariocliente(cliente);
                user.setCodvuelo(vuelo);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return user;

    }

    @Override
    public List<Boleto> readAll() {
        List<Boleto> listaClientes = new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM BOLETO");
            rs = ps.executeQuery();

            while (rs.next()) {
                Boleto nuevouser = new Boleto();
                //Leeemos de la bd
                String cliente = rs.getString("cliente");
                int vuelo = rs.getInt("vuelo");
                
                //Seteamos los valores
                nuevouser.setUsuariocliente(cliente);
                nuevouser.setCodvuelo(vuelo);
                listaClientes.add(nuevouser);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaClientes;

    }

    public boolean deleteC(String id) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("DELETE FROM BOLETO WHERE CLIENTE = ?, VUELO = ?");
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
    
    public boolean deleteC(int id) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("DELETE FROM BOLETO WHERE CLIENTE = ?, VUELO = ?");
            ps.setInt(2, id);
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
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
