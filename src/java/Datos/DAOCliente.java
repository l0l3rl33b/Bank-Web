package Datos;

import Model.Cliente;
import Model.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class DAOCliente {

    public boolean AgregarCliente(Cliente cli) {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR_CLIENTE)) {
            stm.clearParameters();

            stm.setString(1, cli.getId_cliente());
            stm.setString(2, cli.getUser().getId_usuario());
            stm.setString(3, cli.getApellido());
            stm.setString(4, cli.getNombre());
            stm.setString(5, cli.getTelefono());

            exito = stm.executeUpdate() == 1;

        } catch (InstantiationException | IOException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }

    public boolean ConsultarIDCliente(String idCliente) {

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_CONSULTAR_ID_CLIENTE);) {
            stm.clearParameters();
            stm.setString(1, idCliente);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.absolute(1)) {
                    //Si encuentra un linea se posiciona en ella y eso significa que encontro el dato
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
//
  public List<String> devolverIdClientes()
  {
      List<String> listica= new ArrayList();
              try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_ID_CLIENTES);) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    listica.add(rs.getString("id_cliente"));
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
    public String DevolverNombreCliente(String user) {
        String nombre = null;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_CONSULTAR_USUARIO_PARA_NOMBRE);) {
            stm.clearParameters();
            stm.setString(1, user);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre") + " " + rs.getString("apellidos");
                }

            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return nombre;
    }

    //
    public Cliente DevolverCliente(String id_cliente) {
        Cliente cliente = null;
        Usuario user= new Usuario();
        try (Connection cnx = obtenerConexion();
                
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_CLIENTE);) {
            stm.clearParameters();
            stm.setString(1, id_cliente);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    user=usuario.RecuperarUsuario(rs.getString("usuario_id_usuario"));
                    cliente = new Cliente(rs.getString("id_cliente"),user ,
                            rs.getString("apellidos"), rs.getString("nombre"), rs.getString("telefono"));
                }

            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return cliente;
    }
    //

    public String DevolverIDCliente(String user) {
        String nombre = null;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_ID_CLIENTE);) {
            stm.clearParameters();
            stm.setString(1, user);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("id_cliente");
                }

            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return nombre;
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

    public static DAOCliente obtenerInstancia() {
        if (instancia == null) {
            instancia = new DAOCliente();
        }
        return instancia;
    }
    private static final DaoUsuario usuario = DaoUsuario.obtenerInstancia();
    private static DAOCliente instancia = obtenerInstancia();
    private static final String CMD_CONSULTAR_USUARIO_PARA_NOMBRE
            = "SELECT apellidos, nombre FROM cliente WHERE usuario_id_usuario=?; ";
    private static final String CMD_CONSULTAR_ID_CLIENTE
            = "SELECT id_cliente FROM cliente WHERE id_cliente=?; ";
    private static final String CMD_AGREGAR_CLIENTE
            = "INSERT INTO cliente (id_cliente, usuario_id_usuario, apellidos, nombre, telefono) "
            + "VALUES (?, ?, ?, ?, ?); ";
    private static final String CMD_DEVOLVER_ID_CLIENTE
            = "SELECT id_cliente FROM cliente WHERE usuario_id_usuario=?; ";
    private static final String CMD_DEVOLVER_CLIENTE
            = "SELECT * FROM cliente WHERE id_cliente=?; ";
        private static final String CMD_DEVOLVER_ID_CLIENTES
            = "SELECT id_cliente FROM cliente; ";
}
