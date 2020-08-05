package Controler;

import Datos.DAOCuenta;
import Datos.DAOMovimiento;
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
@WebServlet(name = "MiServletDeposito", urlPatterns = {"/Deposito"})
public class MiServletDeposito extends HttpServlet {

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
        String buscar = request.getParameter("buscar");
        String depositar = request.getParameter("depositar");
        if(buscar != null)
        {
            System.out.println(buscar);
        }
        if(depositar != null)
        {
            System.out.print(depositar);
        }
//---------------BLOQUE DE BUSCAR-----------------
        try {
            if (!buscar.isEmpty() && !request.getParameter("numCedula").isEmpty()) {
                if (daocuenta.ComprobarCuentaXClienteID(request.getParameter("numCedula"))) {
                    request.setAttribute("CedulaID", request.getParameter("numCedula"));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DepositosCajero.jsp");
                    dispatcher.forward(request, response);

                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("DepositosCajero.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {

        }
        //-------------------------------------------------------
        //-------------------BLOQUE DE AGREGAR-------------
        try {

            String cuenta = request.getParameter("Selecion de cuentas");
            if (!depositar.isEmpty() && !request.getParameter("Monto a depositar").isEmpty()) {
                try {
                    daocuenta.insertarUltimaFecha(new Timestamp(new Date().getTime()), cuenta);
                    transferenciasdao.agregarTranferencia(cuenta, cuenta, request.getParameter("Monto a depositar"), new Timestamp(new Date().getTime()),
                            true);
                    daocuenta.RealizarDeposito(cuenta, Double.parseDouble(request.getParameter("Monto a depositar")));
                } catch (SQLException | ClassNotFoundException | IllegalAccessException ex) {
                    Logger.getLogger(MiServletDeposito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //---------------------------------------------------
        } catch (Exception e) {

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    final DAOCuenta daocuenta = DAOCuenta.obtenerInstancia();
    final DAOMovimiento daomovimiento = DAOMovimiento.obtenerInstancia();
    final DAOTransferencia transferenciasdao = DAOTransferencia.obtenerInstancia();
}
