<%-- 
    Document   : CrearCuenta
    Created on : 24/04/2020, 10:49:07 AM
    Author     : David
--%>

<%@page import="Datos.DAOMoneda"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Datos.DAOCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creacion de cuenta</title>
        <link href="estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="Titulo_de_Creacion_cuentas">
            <h1>BIENVENIDO AL SISTEMA DE CREACION DE CUENTAS</h1>
        </div>
        <div >
            <%
                /*VARIABLES*/
                HttpSession objSesion = request.getSession(false);
                final DAOCliente cliente = DAOCliente.obtenerInstancia();
                final DAOMoneda moneda = DAOMoneda.obtenerInstancia();
                List<String> listaIdClientes = new ArrayList();
                List<String> listaMonedas = new ArrayList();
                /*DEVUELVE LOS ID DE LOS CLIENTES*/
                listaIdClientes = cliente.devolverIdClientes();
                listaMonedas = moneda.devolverNombreMonedas();
            %>
            <form action="CrearCuenta" method="POST">

                <table id="Tabla_creacion_cuentas"  border="1">
                    <thead>
                        <tr>
                            <th>Numero de cuenta</th>
                            <th>Tipo de cuenta</th>
                            <th>Cliente</th>
                            <th>Moneda de la cuenta</th>
                            <th>Saldo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="text" maxlength="15" placeholder="Inserte num de cuenta" name="numCuenta">
                            </td>
                            <td>
                                <select name="Tipo de cuenta">
                                    <option value="Ahorros">Ahorros</option>
                                    <option value="Corriente">Corriente</option>
                                </select>
                            </td>
                            <td>
                                <select name="seleccion_de_cuentas_portador"  >
                                    <%     for (int i = 0; i < listaIdClientes.size(); i++) {
                                    %>
                                    <option value=<%= listaIdClientes.get(i)%> > <%= listaIdClientes.get(i)%></option>

                                    <%
                                        }
                                    %>   
                                </select>
                            </td>
                            <td>
                                <select name="seleccion_de_moneda"  >
                                    <%     for (int i = 0; i < listaMonedas.size(); i++) {
                                    %>
                                    <option value=<%= listaMonedas.get(i)%> > <%= listaMonedas.get(i)%></option>

                                    <%
                                        }
                                    %>   
                                </select>
                            </td>
                            <td>
                                <input  type="text" name="saldo final" placeholder="Inserte el monto inicial">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Crear cuenta">
                            </td>
                            <td>
                                <button type="submit" formaction="PrincipalHome.jsp">Volver</button>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>

                    </tbody>
                </table>
            </form>


        </div>
    </body>
</html>
