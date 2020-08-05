
package Model;

/**
 *
 * @author David
 */
public class Favorita {

    public Favorita(String cliente_id_cliente, String cuenta_num_cuenta) {
        this.cliente_id_cliente = cliente_id_cliente;
        this.cuenta_num_cuenta = cuenta_num_cuenta;
    }

    public Favorita() {
    }
    
    public String getCliente_id_cliente() {
        return cliente_id_cliente;
    }

    public void setCliente_id_cliente(String cliente_id_cliente) {
        this.cliente_id_cliente = cliente_id_cliente;
    }

    public String getCuenta_num_cuenta() {
        return cuenta_num_cuenta;
    }

    public void setCuenta_num_cuenta(String cuenta_num_cuenta) {
        this.cuenta_num_cuenta = cuenta_num_cuenta;
    }

    String cliente_id_cliente;
    String cuenta_num_cuenta;
}
