
package Model;

/**
 *
 * @author David
 */
public class Cliente {

    public Cliente(String id_cliente, Usuario user, String apellido, String nombre, String telefono) {
        this.id_cliente = id_cliente;
        this.user = user;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Cliente() {
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
     
    
    
    
    String id_cliente;
    Usuario user;
    String apellido;
    String nombre;
    String telefono;
}
