/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Datos.DAOCuenta;
import Model.Cuenta;
import java.io.IOException;
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
@WebServlet(name = "MiServletCrearCuenta", urlPatterns = {"/CrearCuenta"})
public class MiServletCrearCuenta extends HttpServlet {

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
        String num_cuenta = request.getParameter("numCuenta");

        int Tipo_cuenta = 0;
        if (request.getParameter("Tipo de cuenta").equals("Corriente")) {
            Tipo_cuenta = 0;
        } else {
            Tipo_cuenta = 1;
        }
        Cuenta cue;
        String Cliente = request.getParameter("seleccion_de_cuentas_portador");
        String moneda = request.getParameter("seleccion_de_moneda");
        double saldo = Double.parseDouble(request.getParameter("saldo final"));
        //----------fin bloque de variables------------
        if (!num_cuenta.isEmpty() && !Cliente.isEmpty() && !moneda.isEmpty()) {
            cue = new Cuenta(num_cuenta, Tipo_cuenta, Cliente, moneda, new Timestamp(new Date().getTime()),
                    0.0, true, 0.0, new Timestamp(new Date().getTime()), saldo);

            try {
                daocuenta.agregarCuenta(cue);
                RequestDispatcher dispatcher = request.getRequestDispatcher("CrearCuenta.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException | ClassNotFoundException | IllegalAccessException ex) {
                Logger.getLogger(MiServletCrearCuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("CrearCuenta.jsp");
            dispatcher.forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    final DAOCuenta daocuenta = DAOCuenta.obtenerInstancia();
}
