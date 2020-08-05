package Controler;

import Datos.DaoUsuario;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "MiServletRecoverPass", urlPatterns = {"/pass"})
public class MiServletRecoverPass extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("recover_pass_text");
        String contras;
        Usuario e = null;
        if (id != null) {

            e = daousuario.RecuperarUsuario(id);
            if (e != null) {
                contras = e.getClave_acceso();

                request.setAttribute("pass", contras);
                request.getRequestDispatcher("Recuperar_pass.jsp").forward(request, response);
            } else {
                request.setAttribute("pass", "Contraseña no encontrada");
                request.getRequestDispatcher("Recuperar_pass.jsp").forward(request, response);
            }

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private final DaoUsuario daousuario = DaoUsuario.obtenerInstancia();
}
