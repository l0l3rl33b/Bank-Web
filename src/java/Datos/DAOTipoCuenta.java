
package Datos;

import static Datos.DAOCuenta.obtenerInstancia;
import Model.Tipo_cuenta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author David
 */
public class DAOTipoCuenta {
    
    public Tipo_cuenta RecuperarTipoCuenta(int tipo_cuenta) {
        /*RECUPERA EL TIPO DE CUENTA  POR MEDIO DE SU NUM_CUENTA */
        Tipo_cuenta tp=null;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_DATOS_TIPO_CUENTA);) {
            stm.clearParameters();
            stm.setInt(1, tipo_cuenta);
            try (ResultSet res = stm.executeQuery()) {
                if (res.next()) {
                    tp= new Tipo_cuenta(res.getInt("id_tipo_cuenta"),res.getString("descripción"),res.getDouble("tasa_interés"));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return tp;
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
     public static DAOTipoCuenta obtenerInstancia() {
        if (instancia == null) {
            instancia = new DAOTipoCuenta();
        }
        return instancia;
    }
     private static DAOTipoCuenta instancia = obtenerInstancia();
     private static final String CMD_RECUPERAR_DATOS_TIPO_CUENTA
             ="SELECT * FROM tipo_cuenta WHERE id_tipo_cuenta=?; ";
     
}
