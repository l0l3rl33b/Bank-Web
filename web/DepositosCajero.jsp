<%-- 
    Document   : DepositosCajero
    Created on : 26/04/2020, 10:12:19 AM
    Author     : David
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Datos.DAOCuenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Depositos</title>
        <link href="estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1 id="titulo_primario" >Sistema de depositos por caja! </h1>
        <div id="Wrapper_deposito">
            <form action="Deposito" method="POST">

                <table  style="margin: auto "border="1">
                    <thead>
                        <tr>
                            <th>Numero de Cedula</th>
                            <th>Cuentas</th>
                            <th>Monto a depositar</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="text" name="numCedula" placeholder="Cedula">
                            </td>
                            <td>

                                <select name="Selecion de cuentas">
                                    <%
                                        try {
                                            String cedulaID = String.valueOf(request.getAttribute("CedulaID"));
                                            DAOCuenta cuenta = DAOCuenta.obtenerInstancia();
                                            List<String> listaDeCuentas = new ArrayList();
                                            listaDeCuentas = cuenta.DevolverCuentasXIdCliente(cedulaID);

                                            for (int i = 0; i < listaDeCuentas.size(); i++) {
                                    %>
                                    <option value=<%= listaDeCuentas.get(i)%>  > <%= listaDeCuentas.get(i)%> </option>
                                    <%
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    %>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="Monto_a_depositar" id="Monto_a_depositar" placeholder="Monto de depositar"
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Depositar" name="depositar" >
                            </td>
                            <td>
                                <input type="submit" value="Buscar" name="buscar">
                            </td>
                            <td>
                                <button type="submit" formaction="PrincipalHome.jsp">Volver/Cancelar</button></td>
                            </td>

                        </tr>

                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
