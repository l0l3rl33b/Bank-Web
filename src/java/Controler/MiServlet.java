package Controler;

import Datos.DAOCliente;
import Datos.DaoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
@WebServlet(name = "MiServlet", urlPatterns = {"/banco"})
public class MiServlet extends HttpServlet {

    private void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    /*Valida que el id y la contraseña sean correctos para dejarlo entrar*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("username");
        String pass = request.getParameter("password");
        /*Obtiene una sesion*/
        HttpSession objuser = request.getSession(true);

        if (id != null && pass != null) {
            if (daousuario.ConsultarUsuario(id, pass)) {
                acceso = visCliente;
                objuser.setAttribute("user", id);
                objuser.setAttribute("nombreUser", daoCliente.DevolverNombreCliente(id));
                RequestDispatcher dispatcher = request.getRequestDispatcher(acceso);
                dispatcher.forward(request, response);
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    String visCliente = "PrincipalHome.jsp";
    String acceso;
    private final DaoUsuario daousuario = DaoUsuario.obtenerInstancia();
    DAOCliente daoCliente = DAOCliente.obtenerInstancia();
}
