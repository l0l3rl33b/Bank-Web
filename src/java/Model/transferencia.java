package Model;

import java.sql.Date;

/**
 *
 * @author David
 */
public class transferencia {

    public transferencia(int id_tranferecia, String cuenta_origen, String cuenta_destino, String monto, Date fecha, boolean aplicado) {
        this.id_tranferecia = id_tranferecia;
        this.cuenta_origen = cuenta_origen;
        this.cuenta_destino = cuenta_destino;
        this.monto = monto;
        this.fecha = fecha;
        this.aplicado = aplicado;
    }

    public transferencia() {
    }

    public int getId_tranferecia() {
        return id_tranferecia;
    }

    public void setId_tranferecia(int id_tranferecia) {
        this.id_tranferecia = id_tranferecia;
    }

    public String getCuenta_origen() {
        return cuenta_origen;
    }

    public void setCuenta_origen(String cuenta_origen) {
        this.cuenta_origen = cuenta_origen;
    }

    public String getCuenta_destino() {
        return cuenta_destino;
    }

    public void setCuenta_destino(String cuenta_destino) {
        this.cuenta_destino = cuenta_destino;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
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
    
    
    int id_tranferecia;
    String cuenta_origen;
    String cuenta_destino;
    String monto;  //En caso de hacer algo con esto debo convertirlo
    Date fecha;
    boolean aplicado; //?
}
