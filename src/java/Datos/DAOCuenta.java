package Datos;

import Model.Cliente;
import Model.Cuenta;
import Model.Moneda;
import Model.Tipo_cuenta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class DAOCuenta {

    public double ConsultarsSaldoFinal(String num_cuenta) {
        double saldo = -1;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_SALDO_FINAL);) {
            stm.clearParameters();
            stm.setString(1, num_cuenta);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.absolute(1)) {
                    saldo = rs.getDouble("saldo_final");
                }

            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return saldo;

    }
//    
//    

    public boolean insertarUltimaFecha(Timestamp fecha, String numcuenta) {
        //INSERTA LA ULTIMA FECHA QUE SE MODIFICO LA CUENTA
        boolean exito = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR_ULTIMA_FECHA);) {
            stm.clearParameters();
            stm.setTimestamp(1, fecha);
            stm.setString(2, numcuenta);
            exito = stm.executeUpdate() == 1;
            return true;
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException | IOException ex) {
            Logger.getLogger(DAOCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //
    public boolean realizarTranferencia(String num_cuenta_origen, double monto, String num_cuenta_destino, boolean accion) {
        /*LA TRANSFERENCIA SE REALIZARA LO CUAL MODIFICARA LOS MONTOS DE LAS CUENTAS Y SUS FECHAS DE MODIFICACION
        ADEMAS DE REGISTRAR ESE MOVIMIENTO
        accion: true=sumar, false= restar */
        return modificarSaldos(num_cuenta_destino, monto, accion) == modificarSaldos(num_cuenta_origen, monto, false);

    }
//

    public boolean modificarSaldos(String num_cuenta, double monto, boolean accion) {

        /*accion: true=sumar, false= restar*/
        boolean exito = false;
        if (accion) {
            //sumar
            try (Connection cnx = obtenerConexion();
                    PreparedStatement stm = cnx.prepareStatement(CMD_MODIFICAR_SALDOS);) {
                stm.clearParameters();
                stm.setDouble(1, monto);
                stm.setString(2, num_cuenta);
                exito = stm.executeUpdate() == 1;
            } catch (IOException
                    | ClassNotFoundException
                    | IllegalAccessException
                    | InstantiationException
                    | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
            }
        } else {
            //resta
            monto = monto * -1;
            try (Connection cnx = obtenerConexion();
                    PreparedStatement stm = cnx.prepareStatement(CMD_MODIFICAR_SALDOS);) {
                stm.clearParameters();
                stm.setDouble(1, monto);
                stm.setString(2, num_cuenta);

                exito = stm.executeUpdate() == 1;

            } catch (IOException
                    | ClassNotFoundException
                    | IllegalAccessException
                    | InstantiationException
                    | SQLException ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
            }
        }

        return exito;
    }

    //
    public boolean RecuperarNumCuenta(String num_cuenta) {
        /*Corrobora que exista la cuenta por medio del cliente*/
 /*CORROBORA QUE EL NUMERO DE CUENTA EXISTA */
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_CORROBORA_CUENTA);) {
            stm.clearParameters();
            stm.setString(1, num_cuenta);
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

    //
    public boolean RealizarDeposito(String num_cuenta, double monto) {
        boolean exito = false;
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_REALIZAR_DEPOSITO);) {
            stm.clearParameters();
            stm.setString(1, num_cuenta);
            stm.setDouble(2, monto);
           
                exito = stm.executeUpdate() == 1;
            
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return exito;
    }

    //
    public boolean ComprobarCuentaXClienteID(String cliente_id) {
        /*Corrobora que exista la cuenta por medio del cliente*/
 /*CORROBORA QUE EL NUMERO DE CUENTA EXISTA */
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_CUENTA_POR_CLIENTE);) {
            stm.clearParameters();
            stm.setString(1, cliente_id);
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

    //
    public String DevuelveNumCuenta(String cliente_id) {

        /*DEVUELVE EL NUMERO DE CUENTA TOMANDO COMO CONDICION EL ID DEL CLIENTE */
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_NUM_CUENTA);) {
            stm.clearParameters();
            stm.setString(1, cliente_id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("num_cuenta");
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return " ";
    }

    //
    public String RecuperarClienteID(String id_cuenta) {
        /*CORROBORA QUE EL NUMERO DE CUENTA EXISTA */
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_CLIENTEID);) {
            stm.clearParameters();
            stm.setString(1, id_cuenta);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cliente_id_cliente");
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return " ";
    }
//

    public Double RecuperarSaldoFinal(String num_cuenta) {
        /*RECUPERA EL SALDO FINAL DE LA CUENTA POR MEDIO DE SU NUM_CUENTA */
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_SALDO_FINAL);) {
            stm.clearParameters();
            stm.setString(1, num_cuenta);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("Saldo_final");
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return -1.0;
    }
//

    public List<String> DevolverCuentasXIdCliente(String idCliente) {
        List<String> arregloCuentas = new ArrayList();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_CUENTA_POR_CLIENTE);) {
            stm.clearParameters();
            stm.setString(1, idCliente);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    arregloCuentas.add(rs.getString("num_cuenta"));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return arregloCuentas;
    }

    //
    public List<String> DevolverCuentasFavoritas() {
        List<String> arregloCuentas = new ArrayList();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_DEVOLVER_FAVORITAS);) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    arregloCuentas.add(rs.getString("cuenta_num_cuenta"));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return arregloCuentas;
    }

    //
    public List<Cuenta> DevolverCuentasDeCliente(String clienteid) {
        List<Cuenta> arregloCuentas = new ArrayList();
        Tipo_cuenta tipo = new Tipo_cuenta();
        Cliente cli = new Cliente();
        Moneda mone = new Moneda();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_CUENTA_POR_CLIENTE);) {
            stm.clearParameters();
            stm.setString(1, clienteid);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    cli = cliente.DevolverCliente(rs.getString("cliente_id_cliente"));
                    tipo = tipoCuenta.RecuperarTipoCuenta(rs.getInt("tipo_cuenta_id_tipo_cuenta"));

                    mone = moneda.devolverMoneda(rs.getString("moneda_nombre"));
                    arregloCuentas.add(new Cuenta(rs.getString("num_cuenta"), tipo, cli,
                            mone, rs.getTimestamp("Fecha_creacion"),
                            rs.getDouble("Limite_transferencia_diaria"), rs.getBoolean("Activa"),
                            rs.getDouble("Saldo_inicial"), rs.getTimestamp("Fecha_ultima_aplicacion"),
                            rs.getDouble("Saldo_final")));
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return arregloCuentas;
    }

    //
    public int RecuperarTipoCuenta(String num_cuenta) {
        /*RECUPERA EL TIPO DE CUENTA  POR MEDIO DE SU NUM_CUENTA */
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR_CLIENTEID);) {
            stm.clearParameters();
            stm.setString(1, num_cuenta);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("tipo_cuenta_id_tipo_cuenta");
                }
            }
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return -1;
    }
    //

    public boolean agregarFavorita(String clienteID, String cuentaNUM) throws SQLException,
            ClassNotFoundException, IllegalAccessException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAFAVORITA)) {
            stm.clearParameters();
            stm.setString(1, clienteID);
            stm.setString(2, cuentaNUM);

            exito = stm.executeUpdate() == 1;

        } catch (InstantiationException | IOException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }
//

    public boolean agregarCuenta(Cuenta cue) throws SQLException,
            ClassNotFoundException, IllegalAccessException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGACUENTA)) {
            stm.clearParameters();
            stm.setString(1, cue.getNum_cuenta());
            stm.setInt(2, cue.getTipo_cuenta_id2());
            stm.setString(3, cue.getCliente_cuenta2());
            stm.setString(4, cue.getMoneda2());
            stm.setTimestamp(5, new Timestamp(cue.getFecha_creacion().getTime()));
            stm.setDouble(6, 1000000);
            stm.setBoolean(7, true);
            stm.setDouble(8, 0.0);
            stm.setTimestamp(9, new Timestamp(cue.getFecha_ultima_aplicacion().getTime()));
            stm.setDouble(10, cue.getSaldo_final());

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

    public static DAOCuenta obtenerInstancia() {
        if (instancia == null) {
            instancia = new DAOCuenta();
        }
        return instancia;
    }
    private static DAOCuenta instancia = obtenerInstancia();
    private static final DAOTipoCuenta tipoCuenta = DAOTipoCuenta.obtenerInstancia();
    private static final DAOMoneda moneda = DAOMoneda.obtenerInstancia();
    private static final DAOCliente cliente = DAOCliente.obtenerInstancia();
    private static final String CMD_CORROBORA_CUENTA
            = "SELECT * FROM cuenta WHERE num_cuenta=?; ";
    private static final String CMD_RECUPERAR_CLIENTEID //Recupera la cuenta por medio del numero de cuenta
            = "SELECT * FROM cuenta WHERE num_cuenta=?; ";
    private static final String CMD_AGREGAFAVORITA
            = "INSERT INTO favorita (cliente_id_cliente, cuenta_num_cuenta) "
            + "VALUES (?, ?); ";
    private static final String CMD_DEVOLVER_NUM_CUENTA
            = "SELECT num_cuenta FROM cuenta WHERE cliente_id_cliente=?; ";
    private static final String CMD_DEVOLVER_FAVORITAS
            = "SELECT * FROM favorita; ";
    private static final String CMD_DEVOLVER_SALDO_FINAL
            = "SELECT saldo_final FROM cuenta WHERE num_cuenta=?; ";
    private static final String CMD_MODIFICAR_SALDOS
            = "UPDATE cuenta SET saldo_final = saldo_final+? "
            + "WHERE num_cuenta=?; ";

    private static final String CMD_AGREGAR_ULTIMA_FECHA
            = "UPDATE cuenta SET fecha_ultima_aplicacion=? WHERE num_cuenta=?; ";
    private static final String CMD_AGREGACUENTA
            = "INSERT INTO cuenta (num_cuenta, tipo_cuenta_id_tipo_cuenta, cliente_id_cliente, moneda_nombre, fecha_creacion, limite_transferencia_diaria, "
            + "activa, saldo_inicial, fecha_ultima_aplicacion, saldo_final) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
    private static final String CMD_RECUPERAR_CUENTA_POR_CLIENTE
            = "SELECT * FROM cuenta WHERE cliente_id_cliente=?; ";
    private static final String CMD_REALIZAR_DEPOSITO
            = "UPDATE cuenta SET saldo_final=? WHERE num_cuenta=?; ";
}
