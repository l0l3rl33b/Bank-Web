
package Controler;

import Datos.DAOCuenta;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
@WebServlet(name = "MiServletFavorita", urlPatterns = {"/favorita"})
public class MiServletFavorita extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    /*Corrobora que la cuenta exista para poder agregarla*/
                HttpSession objSesion = request.getSession(false);    
            String num_cuenta= request.getParameter("Cuenta_fav");
            if(daocuenta.RecuperarNumCuenta(num_cuenta))
            {
                
                try {
                    daocuenta.agregarFavorita(daocuenta.RecuperarClienteID(num_cuenta), num_cuenta);
                } catch (SQLException | ClassNotFoundException | IllegalAccessException ex) {
                    Logger.getLogger(MiServletFavorita.class.getName()).log(Level.SEVERE, null, ex);
                }
               RequestDispatcher dispatcher = request.getRequestDispatcher("Vincular_cuentas.jsp");
               dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("Vincular_cuentas.jsp");
                dispatcher.forward(request, response);
            }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    final DAOCuenta daocuenta= DAOCuenta.obtenerInstancia();
}
