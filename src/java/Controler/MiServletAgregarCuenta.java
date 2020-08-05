package Controler;

import Datos.DAOCliente;
import Datos.DaoUsuario;
import Model.Cliente;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author David
 */
@WebServlet(name = "MiServletAgregarCuenta", urlPatterns = {"/AgregarCuenta"})
public class MiServletAgregarCuenta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String aux, aux1;
        Usuario user = null;
        Cliente client = null;
        aux = request.getParameter("devolver");
        aux1 = request.getParameter("IngresarCuenta");
        try {

            /*La persona decidio seguir adelante*/
 /* Valido que el nombre de usuario no exista ya en la base de datos ni el id del cliente 
                para que no tenga varias cuentas de ingreso al sistema*/
            if (!daousuario.ConsultarNombreUsuario(request.getParameter("nombreUsuarioText")) || !daoCliente.ConsultarIDCliente(request.getParameter("cedulatext"))) {
                /*Si pasa las validaciones agregue el usuario nuevo*/
                user = new Usuario(request.getParameter("nombreUsuarioText"), request.getParameter("passwordText"),
                        false, 0);
                daousuario.agregarUsuario(user);
                /*Si pasa las validaciones agregue el Cliente nuevo*/
                client = new Cliente(request.getParameter("cedulatext"), user, request.getParameter("apellidotext"),
                        request.getParameter("nombretext"), (request.getParameter("telefonotext")));
                daoCliente.AgregarCliente(client);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

            } else {
                //En caso que el nombre de usuario exista haga esto **Modificar**
                request.getRequestDispatcher("AgregarCuenta.jsp").forward(request, response);
            }

        } catch (IOException | ServletException e) {
            System.out.print("Sucedio un error al agregar un usuario");
        } catch (SQLException ex) {
            Logger.getLogger(MiServletAgregarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiServletAgregarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MiServletAgregarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private final DaoUsuario daousuario = DaoUsuario.obtenerInstancia();
    private final DAOCliente daoCliente = DAOCliente.obtenerInstancia();
}
