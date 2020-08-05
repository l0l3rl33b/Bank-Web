package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author David
 */
public class Cuenta {

    /* Cada cliente puede tener ninguna,o varias cuentas */

    public Cuenta(String num_cuenta, Tipo_cuenta tipo_cuenta_id, Cliente cliente_cuenta, Moneda moneda, Timestamp fecha_creacion, double limite_tranferencia_diaria, boolean activa, double saldo_inicial, Timestamp fecha_ultima_aplicacion, double saldo_final) {
        this.num_cuenta = num_cuenta;
        this.tipo_cuenta_id = tipo_cuenta_id;
        this.cliente_cuenta = cliente_cuenta;
        this.moneda = moneda;
        this.fecha_creacion = fecha_creacion;
        this.limite_tranferencia_diaria = limite_tranferencia_diaria;
        this.activa = activa;
        this.saldo_inicial = saldo_inicial;
        this.fecha_ultima_aplicacion = fecha_ultima_aplicacion;
        this.saldo_final = saldo_final;
    }
    public Cuenta(String num_cuenta, int tipo_cuenta_id, String cliente_cuenta, String moneda, Timestamp fecha_creacion, double limite_tranferencia_diaria, boolean activa, double saldo_inicial, Timestamp fecha_ultima_aplicacion, double saldo_final) {
        this.num_cuenta = num_cuenta;
        this.tipo_cuenta_id2 = tipo_cuenta_id;
        this.cliente_cuenta2 = cliente_cuenta;
        this.moneda2 = moneda;
        this.fecha_creacion = fecha_creacion;
        this.limite_tranferencia_diaria = limite_tranferencia_diaria;
        this.activa = activa;
        this.saldo_inicial = saldo_inicial;
        this.fecha_ultima_aplicacion = fecha_ultima_aplicacion;
        this.saldo_final = saldo_final;
    }
    public Cuenta() {  // ?
    }





    public String getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public Tipo_cuenta getTipo_cuenta_id() {
        return tipo_cuenta_id;
    }

    public void setTipo_cuenta_id(Tipo_cuenta tipo_cuenta_id) {
        this.tipo_cuenta_id = tipo_cuenta_id;
    }

    public Cliente getCliente_cuenta() {
        return cliente_cuenta;
    }

    public void setCliente_cuenta(Cliente cliente_cuenta) {
        this.cliente_cuenta = cliente_cuenta;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }



    public double getLimite_tranferencia_diaria() {
        return limite_tranferencia_diaria;
    }

    public void setLimite_tranferencia_diaria(double limite_tranferencia_diaria) {
        this.limite_tranferencia_diaria = limite_tranferencia_diaria;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public Timestamp getFecha_ultima_aplicacion() {
        return fecha_ultima_aplicacion;
    }

    public int getTipo_cuenta_id2() {
        return tipo_cuenta_id2;
    }

    public void setTipo_cuenta_id2(int tipo_cuenta_id2) {
        this.tipo_cuenta_id2 = tipo_cuenta_id2;
    }

    public String getCliente_cuenta2() {
        return cliente_cuenta2;
    }

    public void setCliente_cuenta2(String cliente_cuenta2) {
        this.cliente_cuenta2 = cliente_cuenta2;
    }

    public String getMoneda2() {
        return moneda2;
    }

    public void setMoneda2(String moneda2) {
        this.moneda2 = moneda2;
    }



    public double getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(double saldo_final) {
        this.saldo_final = saldo_final;
    }

    String num_cuenta;
    Tipo_cuenta tipo_cuenta_id; //Cuentas corrientes o de ahorro
    int tipo_cuenta_id2;
    Cliente cliente_cuenta;
    String cliente_cuenta2;
    Moneda moneda;
    String moneda2;
    Timestamp fecha_creacion;
    double limite_tranferencia_diaria;
    boolean activa;
    double saldo_inicial;
    Timestamp fecha_ultima_aplicacion;
    double saldo_final;
    /*Las cuentas de ahorro devengan un interes sobre saldos mensuales*/
}
