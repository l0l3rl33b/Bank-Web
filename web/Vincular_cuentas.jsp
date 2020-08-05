<%-- 
    Document   : Vincular_cuentas
    Created on : 31/03/2020, 02:36:27 PM
    Author     : David
--%>

<%@page import="Datos.DAOCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vinculacion de Cuentas</title>
        <link href="estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            /*Cuando entra agarra el user de la persona que ingreso*/
            HttpSession objSesion = request.getSession(false);
            String nombre = (String) objSesion.getAttribute("nombreUser");
        %>
        <h1 id="titulo_primario" >Sistema de vinculacion de cuentas favoritas! de <%=nombre%> </h1>
        <a href="PrincipalHome.jsp">Volver</a>
        <div id="Vinculacion_cuenta_primer_div">
            <form id="form_vinculacion_cuentas" action="favorita" method="POST">
                <table id="Tabla_vinculacion_cuenta" border="1">
                    <thead>
                        <tr>
                            <th >Cuenta a vincular</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="text" name="Cuenta_fav" placeholder="Inserte la cuenta"id="text_cuenta_fav">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Agregar cuenta favorita">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
