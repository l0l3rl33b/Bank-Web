<%-- 
    Document   : AgregarCuenta
    Created on : 20/03/2020, 10:37:40 AM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banco El Quebrado</title>
    </head>
    <body>
        <form id="Form_agregar_cuenta" method="POST" action="AgregarCuenta">
            <div id="Agregar_Cliente_Wrapper">
                <table  cellpadding="1" style="border-bottom:1px solid #000000">
                    <thead>
                        <tr>
                            <th>
                                <label for="nombreUsuarioText">Nombre de usuario: </label>
                                <input type="text" name="nombreUsuarioText" placeholder="Usuario" id="nombreUsuarioText">
                            </th>
                            <th>
                                <label for="passwordText">Contraseña: </label>
                                <input type="password" name="passwordText" placeholder="Contraseña" id="passwordText">
                            </th>
                            <th>
                                <label for="cedulatext">ID: </label>
                                <input type="text" name="cedulatext" placeholder="ID" id="cedulatext">
                            </th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <label for="nombretext">Nombre: </label>
                                <input type="text" name="nombretext" placeholder="Nombre" id="nombretext">
                            </td>
                            <td>
                                <label for="apellidotext">Apellido:  </label>
                                <input type="text" name="apellidotext" placeholder="Apellido" id="apellidotext">
                            </td>
                            <td>
                                <label for="telefonotext">Telefono: </label>
                                <input type="text" name="telefonotext" placeholder="Telefono" id="telefonotext" maxlength="8">
                            </td>
                            <td>
                                
                            </td>
                            <td></td>
                        </tr>

                    </tbody>
                </table>
                <input type="submit" name="IngresarCuenta"  id="IngresarCuenta" value="Ingresar datos">
                <button type="submit" formaction="index.jsp">Volver</button>

            </div>
        </form>
    </body>
</html>
