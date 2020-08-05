package Datos;

import Model.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUsuario {

    public boolean ConsultarUsuario(String id, String pass) {
        Optional<Usuario> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_PARA_CONSULTA);) {
            stm.clearParameters();
            stm.setString(1, id);
            stm.setString(2, pass);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.absolute(1)) {
                    return true;
                }

            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return false;

    }

    public boolean ConsultarNombreUsuario(String id) {
        Optional<Usuario> r = Optional.empty();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_PARA_CONSULTA);) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.absolute(1)) {
                    return true;
                }

            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return false;
    }

    public Usuario RecuperarUsuario(String id) {
        Usuario r = null;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_USUARIO);) {
            stm.clearParameters();
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    r = new Usuario(
                            rs.getString("id_usuario"),
                            rs.getString("clave_acceso"),
                            rs.getBoolean("clave_vencida"),
                            rs.getInt("rol")
                    );
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return r;
    }

    public boolean agregarUsuario(Usuario user) throws SQLException, ClassNotFoundException, IllegalAccessException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGARUSUARIO)) {
            stm.clearParameters();

            stm.setString(1, user.getId_usuario());
            stm.setString(2, user.getClave_acceso());
            stm.setBoolean(3, user.isClave_vencida());
            stm.setInt(4, user.getRol());

            exito = stm.executeUpdate() == 1;

        } catch (InstantiationException | IOException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }
//

    public int devolverRol(String userID) throws SQLException, ClassNotFoundException  {
        int  r = 0;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_ROL)) {
            stm.clearParameters();

            stm.setString(1, userID);
            try(ResultSet rs=stm.executeQuery())
            {
                if(rs.next())
                {
                    r=rs.getInt("rol");
                    /*TRUE ES QUE ES CAJERO FALSE ES QUE ES CLIENTE NORMAL*/
                }
            }

        } catch (InstantiationException | IOException | IllegalAccessException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
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


    public static DaoUsuario obtenerInstancia() {
        if (instancia == null) {
            instancia = new DaoUsuario();
        }
        return instancia;
    }
    private static DaoUsuario instancia = obtenerInstancia();

    private static final String CMD_RECUPERAR_USUARIO
            = "SELECT id_usuario, clave_acceso, clave_vencida, rol FROM usuario WHERE id_usuario=?; ";
    private static final String CMD_RECUPERAR_PARA_CONSULTA
            = "SELECT id_usuario, clave_acceso FROM usuario WHERE id_usuario=? AND clave_acceso=?; ";
    private static final String CMD_LISTAR
            = "SELECT id, apellidos, nombre FROM estudiante ORDER BY apellidos; ";
    private static final String CMD_AGREGARUSUARIO
            = "INSERT INTO usuario (id_usuario, clave_acceso, clave_vencida, rol) "
            + "VALUES (?, ?, ?, ?); ";
    private static final String CMD_DEVOLVER_ROL
            = "SELECT rol FROM usuario WHERE id_usuario=?; ";
}
