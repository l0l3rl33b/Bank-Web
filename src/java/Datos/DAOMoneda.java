package Datos;

import Model.Moneda;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author David
 */
public class DAOMoneda {

    //
    public List<String> devolverNombreMonedas() {
        List<String> listica = new ArrayList();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_MONEDA_NOMBRE);) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    listica.add(rs.getString("nombre"));
                }

            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return listica;
    }

    //
    public Moneda devolverMoneda(String nom) {
        Moneda moneda = null;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_MONEDA);) {
            stm.clearParameters();
            stm.setString(1, nom);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    moneda = new Moneda(rs.getString("nombre"), rs.getString("descripcion"), rs.getString("simbolo"),
                            rs.getDouble("tipo_cambio_compra"), rs.getDouble("tipo_cambio_venta"));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return moneda;
    }

    public Connection obtenerConexion() throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            IOException,
            SQLException {
        BaseDatos bd = BaseDatos.obtenerInstancia();
        Properties cfg = bd.obtenerConfiguracion();
        Connection cnx = bd.obtenerConexion(
                cfg.getProperty("database"),
                cfg.getProperty("user"),
                cfg.getProperty("password")
        );
        return cnx;
    }

    public static DAOMoneda obtenerInstancia() {
        if (instancia == null) {
            instancia = new DAOMoneda();
        }
        return instancia;
    }
    private static DAOMoneda instancia = obtenerInstancia();
    private static final String CMD_DEVOLVER_MONEDA
            = "SELECT * FROM moneda WHERE nombre=?; ";
    private static final String CMD_DEVOLVER_MONEDA_NOMBRE
            = "SELECT nombre FROM moneda; ";
}
