package Model;

/**
 *
 * @author David
 */
public class Moneda {

    public Moneda(String nombre, String descripcion, String simbolo, double tipo_cambio_compra, double tipo_cambio_venta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.simbolo = simbolo;
        this.tipo_cambio_compra = tipo_cambio_compra;
        this.tipo_cambio_venta = tipo_cambio_venta;
    }

    public Moneda() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getTipo_cambio_compra() {
        return tipo_cambio_compra;
    }

    public void setTipo_cambio_compra(double tipo_cambio_compra) {
        this.tipo_cambio_compra = tipo_cambio_compra;
    }

    public double getTipo_cambio_venta() {
        return tipo_cambio_venta;
    }

    public void setTipo_cambio_venta(double tipo_cambio_venta) {
        this.tipo_cambio_venta = tipo_cambio_venta;
    }

    String nombre;
    String descripcion;
    String simbolo;
    double tipo_cambio_compra;
    double tipo_cambio_venta;
}
