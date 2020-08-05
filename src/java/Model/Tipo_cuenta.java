
package Model;

/**
 *
 * @author David
 */
public class Tipo_cuenta {

    public Tipo_cuenta(int id_tipo_cuenta, String descripcion, double tasa_interes) {
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.descripcion = descripcion;
        this.tasa_interes = tasa_interes;
    }

    public Tipo_cuenta() {
    }

    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public void setId_tipo_cuenta(int id_tipo_cuenta) {
        this.id_tipo_cuenta = id_tipo_cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTasa_interes() {
        return tasa_interes;
    }

    public void setTasa_interes(double tasa_interes) {
        this.tasa_interes = tasa_interes;
    }
    

    int id_tipo_cuenta; //1 ahorro 0 corriente
    String descripcion;
    double tasa_interes;
}
