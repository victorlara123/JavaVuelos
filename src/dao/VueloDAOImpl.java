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
import model.Vuelo;

public class VueloDAOImpl implements RepositorioDAO<Vuelo, Integer> {

    //Para preparar consultas con parametros
    PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    ResultSet rs;
    //Para poder almacenar la conexion
    Connection conexion;
    //Para obtener la conexion
    AccesoDB acceso;

    public VueloDAOImpl() {
        acceso = new AccesoDB();
    }

    @Override
    public boolean create(Vuelo e) {
        boolean resultado = true;
        try {
            //Obtener la conexion a la BD
            conexion = acceso.getConexion();
            //Preparamos la consulta a ejecutar
            ps = conexion.prepareStatement("INSERT INTO VUELO VALUES(?,?,?,?,?,?,?,?)");
            //Indicamos los parametros para la consulta
            ps.setInt(1, e.getCodigo());
            ps.setString(2, e.getOrigen());
            ps.setString(3, e.getDestino());
            ps.setInt(4, e.getCapacidad());
            ps.setInt(5, e.getVacante());
            ps.setString(6, e.getHpartida());
            ps.setString(7, e.getHllegada());
            ps.setFloat(8, e.getPrecio());

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
    public boolean update(Vuelo actualizado) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE vuelo SET origen = ?, destino = ?, capacidad = ?, vacante = ?, hsalida = ?, hllegada = ?, precio = ?"
                    + "WHERE codigo = ?");            
            
            ps.setString(1, actualizado.getOrigen());
            ps.setString(2, actualizado.getDestino());
            ps.setInt(3, actualizado.getCapacidad());
            ps.setInt(4, actualizado.getVacante()+(actualizado.getCapacidad()-actualizado.getVacante()));
            ps.setString(5, actualizado.getHpartida());
            ps.setString(6, actualizado.getHllegada());
            ps.setFloat(7, actualizado.getPrecio());
            ps.setInt(8, actualizado.getCodigo());
            
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }
    
    public boolean updateN(Vuelo actualizado, int ncompras) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE vuelo SET origen = ?, destino = ?, capacidad = ?, vacante = ?, hsalida = ?, hllegada = ?, precio = ?"
                    + "WHERE codigo = ?");            
            
            ps.setString(1, actualizado.getOrigen());
            ps.setString(2, actualizado.getDestino());
            ps.setInt(3, actualizado.getCapacidad());
            ps.setInt(4, (actualizado.getVacante() - ncompras));
            ps.setString(5, actualizado.getHpartida());
            ps.setString(6, actualizado.getHllegada());
            ps.setFloat(7, actualizado.getPrecio());
            ps.setInt(8, actualizado.getCodigo());
            
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }
    
    public boolean updateV(Vuelo actualizado) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE vuelo SET origen = ?, destino = ?, capacidad = ?, vacante = ?, hsalida = ?, hllegada = ?, precio = ?"
                    + "WHERE codigo = ?");            
            
            ps.setString(1, actualizado.getOrigen());
            ps.setString(2, actualizado.getDestino());
            ps.setInt(3, actualizado.getCapacidad());
            ps.setInt(4, actualizado.getVacante()-1);
            ps.setString(5, actualizado.getHpartida());
            ps.setString(6, actualizado.getHllegada());
            ps.setFloat(7, actualizado.getPrecio());
            ps.setInt(8, actualizado.getCodigo());
            
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }

    public List<Vuelo> consultarVuelo(String Origen, String Destino) {
        List<Vuelo> listaVuelo = new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM VUELO WHERE ORIGEN = ? AND DESTINO = ?");
            ps.setString(1, Origen);
            ps.setString(2, Destino);
            rs = ps.executeQuery();

            while (rs.next()) {
                 Vuelo vuelo = new Vuelo();
                //Leeemos de la bd
                int codigo = rs.getInt("codigo");
                String origen = rs.getString("origen"); 
                String destino = rs.getString("destino"); 
                int capacidad = rs.getInt("capacidad");                
                int vacante = rs.getInt("vacante");                
                String hsalida = rs.getString("hsalida");                
                String hllegada = rs.getString("hllegada");
                float precio = rs.getFloat("precio");

                //Seteamos los valores
                vuelo.setCodigo(codigo);
                vuelo.setOrigen(origen);
                vuelo.setDestino(destino);
                vuelo.setCapacidad(capacidad);
                vuelo.setVacante(vacante);
                vuelo.setHpartida(hsalida);
                vuelo.setHllegada(hllegada);
                vuelo.setPrecio(precio); 
                listaVuelo.add(vuelo);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return listaVuelo;

    }

    @Override
    public Vuelo read(Integer id) {
        Vuelo vuelo = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM VUELO WHERE CODIGO = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                vuelo = new Vuelo();
                //Leeemos de la bd
                int codigo = rs.getInt("codigo");
                String origen = rs.getString("origen"); 
                String destino = rs.getString("destino"); 
                int capacidad = rs.getInt("capacidad");                
                int vacante = rs.getInt("vacante");                
                String hsalida = rs.getString("hsalida");                
                String hllegada = rs.getString("hllegada");
                float precio = rs.getFloat("precio");

                //Seteamos los valores
                vuelo.setCodigo(codigo);
                vuelo.setOrigen(origen);
                vuelo.setDestino(destino);
                vuelo.setCapacidad(capacidad);
                vuelo.setVacante(vacante);
                vuelo.setHpartida(hsalida);
                vuelo.setHllegada(hllegada);
                vuelo.setPrecio(precio);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return vuelo;

    }

    @Override
    public List<Vuelo> readAll() {
        List<Vuelo> listaVuelos = new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM VUELO");
            rs = ps.executeQuery();

            while (rs.next()) {
                Vuelo vuelo = new Vuelo();
                //Leeemos de la bd
                int codigo = rs.getInt("codigo");
                String origen = rs.getString("origen"); 
                String destino = rs.getString("destino"); 
                int capacidad = rs.getInt("capacidad");                
                int vacante = rs.getInt("vacante");                
                String hsalida = rs.getString("hsalida");                
                String hllegada = rs.getString("hllegada");
                float precio = rs.getFloat("precio");

                //Seteamos los valores
                vuelo.setCodigo(codigo);
                vuelo.setOrigen(origen);
                vuelo.setDestino(destino);
                vuelo.setCapacidad(capacidad);
                vuelo.setVacante(vacante);
                vuelo.setHpartida(hsalida);
                vuelo.setHllegada(hllegada);
                vuelo.setPrecio(precio);                       
                
                listaVuelos.add(vuelo);
            }

        } catch (SQLException ex) {

        }
        return listaVuelos;

    }
    
    @Override
    public boolean delete(Integer id) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("DELETE FROM VUELO WHERE CODIGO = ?");
            ps.setInt(1, id);
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
            ps = conexion.prepareStatement("DELETE FROM VUELO WHERE CODIGO = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }
}
