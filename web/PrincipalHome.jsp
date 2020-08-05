<%-- 
    Document   : PrincipalHome
    Created on : 30/03/2020, 02:06:13 PM
    Author     : David
            USARE BOOTSTRAP
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Cuenta"%>
<%@page import="Datos.DaoUsuario"%>
<%@page import="Datos.DAOCuenta"%>
<%@page import="Datos.DAOCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INICIO</title>
        <link href="estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            /*Cuando entra agarra el user de la persona que ingreso*/
                /*MANTIENE LA SESIÓN*/
            HttpSession objSesion = request.getSession(false);
            String nombre = (String) objSesion.getAttribute("nombreUser");
        %>
        <div  id="Referecias_a_paginas" >
            <table id="tabla_referencia" border="1"  >
                <thead >
                    <tr>
                        <th><a style="color: #000" href="PrincipalHome.jsp"> Home </a></th>
                        <th><a style="color: #000" href="#"  > Consultar Cuentas </a></th>
                        <th><a style="color: #000" href="Vincular_cuentas.jsp"  > Vincular Cuentas externas </a></th>
                        <th><a style="color: #000" href="Transfer_remota.jsp" > Transferencia remota </a></th>
                            <%
                                DaoUsuario usuario = DaoUsuario.obtenerInstancia();
                                if (usuario.devolverRol((String) objSesion.getAttribute("user")) == 1) {
                            %>
                        <th><a style="color: #000" href="CrearCuenta.jsp" > Apertura de cuentas </a></th>
                        <th><a style="color: #000" href="DepositosCajero.jsp" > Depositos </a></th>
                        <th><a style="color: #000" href=#" > Retiros </a></th>
                        <th><a style="color: #000" href="#" > Transferencia en cajas </a></th>
                        <th><a style="color: #000" href="#" > Aceditación de intereses </a></th>

                        <%}
                        %>
                        <th><a style="color: #000" href="index.jsp" > Cerrar Sesion </a></th>
                        <th> <label id="cliente_label">Cliente: <%=nombre%> </label></th>
                    </tr>
                </thead>

            </table>

        </div>
        <div id="Cuenta_principal">
            <table border="1" id="Tabla_de_cuenta">
                <thead >
                    <tr>
                        <th>TITULAR</th>
                        <th>N°CUENTA</th>
                        <th>TIPO DE CUENTA</th>
                        <th>SALDO</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        /*VARIABLES*/
                        final DAOCliente cliente = DAOCliente.obtenerInstancia();
                        final DAOCuenta cuenta = DAOCuenta.obtenerInstancia();
                        String id_cliente;
                        List<Cuenta> listaDeCuentas= new ArrayList();
                        /**/
                       
                       
                        id_cliente=cliente.DevolverIDCliente((String) objSesion.getAttribute("user"));
                        listaDeCuentas = new ArrayList<>(cuenta.DevolverCuentasDeCliente(id_cliente));
                        for(int i=0;i< listaDeCuentas.size();i++)
                        {
                    %>
                    <tr>
                        <td><%=nombre%></td>
                        <td><%= listaDeCuentas.get(i).getNum_cuenta() %></td>

                        <%
                            switch (listaDeCuentas.get(i).getTipo_cuenta_id().getId_tipo_cuenta()) {
                                case 0:
                        %>
                        <td>Corriente</td>
                        
                        <%
                                break;
                            case 1:
                        %>
                        <td>Ahorros</td>
                        <%
                                break;
                            default:
                        %>
                        <td>ERROR</td>
                        <%
                                    break;
                            }
                        %>
                        <td><%=listaDeCuentas.get(i).getSaldo_final() %></td>
                        <%}%>
                    </tr>
                </tbody>
            </table>

        </div>

    </body>
</html>
