<%-- 
    Document   : Transfer_remota
    Created on : 11/04/2020, 10:57:32 PM
    Author     : David
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Datos.DAOCuenta"%>
<%@page import="Datos.DAOCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TRANFERENCIA REMOTA</title>
        <link href="estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1 id="titulo_de_tranfer_principal">Sistema de tranferencia remota del banco El Quebrado</h1>
        <div id="Principal de tranfer">
            <%
                /*VARIABLES*/
                HttpSession objSesion = request.getSession(false);
                final DAOCliente cliente = DAOCliente.obtenerInstancia();
                final DAOCuenta cuenta = DAOCuenta.obtenerInstancia();
                String id_cliente;
                List<Cuenta> listaDeCuentas = new ArrayList();
                List<String> listaFavoritas = new ArrayList();
                /*DEVUELVE LAS CUENTAS DEL PORTADOR*/

                id_cliente = cliente.DevolverIDCliente((String) objSesion.getAttribute("user"));
                listaFavoritas = cuenta.DevolverCuentasFavoritas();
                listaDeCuentas = new ArrayList<>(cuenta.DevolverCuentasDeCliente(id_cliente));
            %>
            <form action="Tranferencia" method="POST">
                <table id="tabla_principal_tranfer" border="1">
                    <thead>
                        <tr>
                            <th>Cuenta Origen</th>
                            <th>Cuenta Destino</th>
                            <th>Monto a tranferir</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <select name="seleccion_de_cuentas_portador"  >
                                    <%
                                        for (int i = 0; i < listaDeCuentas.size(); i++) {
                                    %>
                                    <option value=<%= listaDeCuentas.get(i).getNum_cuenta()%> > <%= listaDeCuentas.get(i).getNum_cuenta()%></option>

                                    <%
                                        }
                                    %>   
                                </select>
                            </td>
                            <td>
                                <select name="seleccion_de_cuentas_destino"  >
                                    <%
                                        for (int i = 0; i < listaFavoritas.size(); i++) {
                                    %>
                                    <option value=<%= listaFavoritas.get(i)%> > <%= listaFavoritas.get(i)%></option>

                                    <%
                                        }
                                    %>   
                                </select>
                            </td>
                            <td>
                                <input type="text" placeholder="Inserte monto" id="textmonto" name="textmonto">
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="tranferir monto" name="subtranferir">
                            </td>
                            <td style="text-align:center;" colspan="2">
                                <button type="submit" formaction="PrincipalHome.jsp">Volver/Cancelar</button></td>

                        </tr>

                    </tbody>
                </table>

            </form>
        </div>
    </body>
</html>
