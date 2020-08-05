
package Model;

import java.sql.Date;

/**
 *
 * @author David
 */
public class Movimiento {

    public Movimiento(int id_movimiento, String cuenta_num_cuenta, double monto, Date fecha, boolean aplicado, String movimientocol) {
        this.id_movimiento = id_movimiento;
        this.cuenta_num_cuenta = cuenta_num_cuenta;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
        this.movimientocol = movimientocol;
    }

    public Movimiento() {
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public String getCuenta_num_cuenta() {
        return cuenta_num_cuenta;
    }

    public void setCuenta_num_cuenta(String cuenta_num_cuenta) {
        this.cuenta_num_cuenta = cuenta_num_cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isAplicado() {
        return aplicado;
    }

    public void setAplicado(boolean aplicado) {
        this.aplicado = aplicado;
    }

    public String getMovimientocol() {
        return movimientocol;
    }

    public void setMovimientocol(String movimientocol) {
        this.movimientocol = movimientocol;
    }
    
    
    int id_movimiento;
    String cuenta_num_cuenta;
    double monto;
    Date fecha;
    boolean aplicado; //?
    String movimientocol;
}
