package Controler;

import Datos.DAOCuenta;
import Datos.DAOTransferencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
@WebServlet(name = "MiServletTranferencia", urlPatterns = {"/Tranferencia"})
public class MiServletTranferencia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MiServletTranferencia</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MiServletTranferencia at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String num_cuenta_origen = request.getParameter("seleccion_de_cuentas_portador");
            String num_cuenta_destino = request.getParameter("seleccion_de_cuentas_destino");
            Date fecha = new Date();
            Timestamp fechaBD = new Timestamp(fecha.getTime());

            Double monto = Double.parseDouble(request.getParameter("textmonto"));
            double saldo = cuenta.RecuperarSaldoFinal(num_cuenta_origen);
            /*TAMBIEN SE DEBE VALIDAR EL LIMITE! DE TRANSFERENCIA*/
            if (saldo > 0 && monto != null) {
                /*LA PERSONA PUEDE HACER LA TRANFERENCIA DEBIDO A QUE SI TIENE DINERO*/

 /*-------------------BLOQUE DE TRANSFERENCIA EN LAS CUENTAS -----------------*/
                cuenta.realizarTranferencia(num_cuenta_origen, monto, num_cuenta_destino, true);
                cuenta.insertarUltimaFecha(fechaBD, num_cuenta_destino);
                cuenta.insertarUltimaFecha(fechaBD, num_cuenta_origen);
                /*-----------------------------FIN DE BLOQUE ---------------------------------*/

 /*---------BLOQUE QUE AGREGA LA TRANSFERENCIA EN LA BD----------*/
                try {
                    transferencias.agregarTranferencia(num_cuenta_origen, num_cuenta_destino, String.valueOf(monto), fechaBD, true);
                } catch (SQLException | ClassNotFoundException | IllegalAccessException ex) {
                    Logger.getLogger(MiServletTranferencia.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*------------FIN DE BLOQUE--------------------------------------*/

            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("PrincipalHome.jsp");
                dispatcher.forward(request, response);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("PrincipalHome.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("PrincipalHome.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private final DAOCuenta cuenta = DAOCuenta.obtenerInstancia();
    private final DAOTransferencia transferencias = DAOTransferencia.obtenerInstancia();
}
