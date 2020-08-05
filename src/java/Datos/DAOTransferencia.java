package Datos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class DAOTransferencia {
//

    public boolean agregarTranferencia(String cuenta_origen, String cuenta_destino, String monto, Timestamp fecha, boolean estado) throws SQLException,
            ClassNotFoundException, IllegalAccessException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR_TRANSFERENCIA)) {
            stm.clearParameters();
            stm.setString(1, null);
            stm.setString(2, cuenta_origen);
            stm.setString(3, cuenta_destino);
            stm.setString(4, monto);
            stm.setTimestamp(5, fecha);
            stm.setBoolean(6, estado);

            exito = stm.executeUpdate() == 1;

        } catch (InstantiationException | IOException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }

    //
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

    public static DAOTransferencia obtenerInstancia() {
        if (instancia == null) {
            instancia = new DAOTransferencia();
        }
        return instancia;
    }
    private static DAOTransferencia instancia = obtenerInstancia();
    private static final DAOTransferencia tranferencia = DAOTransferencia.obtenerInstancia();

    private static final String CMD_INSERTAR_TRANSFERENCIA
            = "INSERT INTO transferencia (id_transferencia, cuenta_origen, cuenta_destino, monto, fecha, aplicado) "
            + "VALUES (?, ?, ?, ?, ?, ?); ";
}
