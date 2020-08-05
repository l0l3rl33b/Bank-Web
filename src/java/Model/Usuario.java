package Model;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Usuario  implements Serializable{

    public Usuario(String id_usuario, String clave_acceso, boolean clave_vencida, int rol) { /*Con parametros*/
        this.id_usuario = id_usuario;
        this.clave_acceso = clave_acceso;
        this.clave_vencida = clave_vencida;
        this.rol = rol;
    }
    
    public Usuario() {    /*Sin parametros*/
        this.id_usuario = "";
        this.clave_acceso = "";
        this.clave_vencida = false;
        this.rol = 0;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getClave_acceso() {
        return clave_acceso;
    }

    public void setClave_acceso(String clave_acceso) {
        this.clave_acceso = clave_acceso;
    }

    public boolean isClave_vencida() {
        return clave_vencida;
    }

    public void setClave_vencida(boolean clave_vencida) {
        this.clave_vencida = clave_vencida;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }



    String id_usuario; //No es el numero de cedula
    String clave_acceso; //Se supone esta clave se da aleatoriamente
    boolean clave_vencida;
    int rol; // 0: Cliente regular, 1:Cajero
    //Todos los cajerons son clientes del banco
}
