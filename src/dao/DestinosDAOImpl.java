package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import estructuras.ListaDoble;
import model.Destino;


public class DestinosDAOImpl implements RepositorioDAO<Destino, Integer> {

    //Para preparar consultas con parametros
    PreparedStatement ps;
    //Para obtener el resultado de la ejecucion de la consulta
    ResultSet rs;
    //Para poder almacenar la conexion
    Connection conexion;
    //Para obtener la conexion
    AccesoDB acceso;

    public DestinosDAOImpl() {
        acceso = new AccesoDB();
    }

    @Override
    public boolean create(Destino e) {
        boolean resultado = true;
        try {
            //Obtener la conexion a la BD
            conexion = acceso.getConexion();
            //Preparamos la consulta a ejecutar
            ps = conexion.prepareStatement("INSERT INTO DESTINOS VALUES(?)");
            //Indicamos los parametros para la consulta
            ps.setString(1, e.getPunto());

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
    public boolean update(Destino actualizado) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE DESTINOS WHERE LUGAR = ?");
            
            ps.setString(1, actualizado.getPunto());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }
    
    public boolean updateL(String lugar) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            ps = conexion.prepareStatement("UPDATE DESTINOS WHERE LUGAR = ?");
            
            ps.setString(1, lugar);
            ps.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resultado = false;
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return resultado;
    }

    public Destino readLugar(String id) {
        Destino lugar = null;
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM DESTINOS WHERE LUGAR = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                lugar = new Destino();
                //Leeemos de la bd
                String destino = rs.getString("lugar");
                
                //Seteamos los valores
                lugar.setPunto(destino);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            acceso.cerrarConexion(conexion, ps);
        }
        return lugar;

    }
   
    @Override
    public List<Destino> readAll() {
        List<Destino> listaLugares = new ArrayList<>();
        try {
            //Obtenemos la conexion
            conexion = acceso.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM DESTINOS");
            rs = ps.executeQuery();

            while (rs.next()) {
                Destino lugar = new Destino();
                //Leeemos de la bd
                String destino = rs.getString("lugar");
                
                //Seteamos los valores
                lugar.setPunto(destino);
                listaLugares.add(lugar);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaLugares;

    }

    public boolean deleteD(String id) {
        boolean resultado = true;
        conexion = acceso.getConexion();
        try {
            //Para un procedimiento almacenado
            //conexion.prepareCall("{call sp_delete_producto(?)}");
            ps = conexion.prepareStatement("DELETE FROM DESTINOS WHERE LUGAR = ?");
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
  

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Destino read(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}