package Datos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author David
 */
public class DAOMovimiento {

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

    public static DAOMovimiento obtenerInstancia() {
        if (instancia == null) {
            instancia = new DAOMovimiento();
        }
        return instancia;
    }
    private static DAOMovimiento instancia = obtenerInstancia();
    private static final DAOMovimiento tranferencia = DAOMovimiento.obtenerInstancia();
    
    
    private static final String CMD_INSERTAR_MOVIMIENTO
            = "INSERT INTO movimiento (id_movimiento, cuenta_num_cuenta, monto, fecha, aplicado) "
            + "VALUES (?, ?, ?, ?, ?); ";
}
