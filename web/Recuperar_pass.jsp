<%-- 
    Document   : Recuperar_pass
    Created on : 19/03/2020, 10:25:51 AM
    Author     : David
--%>

<%@page import="Datos.DaoUsuario"%>
<%@page import="Model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banco El Quebrado</title>
    </head>
    <body>
        <div id="Wrapper">  
            <div id="Recover_pass_principal">
                <form id="form_recover_pass" method="POST" action="pass"> 

                    <h1>SISTEMA DE RECUPERACION DE CONTRASEÑA</h1>
                    <h3>FAVOR DIGITE EL NOMBRE DE USUARIO</h3>
                    <input type="text" name="recover_pass_text">
                    <input  type="submit"  class="Recover_pass_botton" value="RecuperarContraseña"> 
                    <button type="submit" formaction="index.jsp">Volver</button>
                </form>  
            </div>
                    
            <div class="overlay" id="overlay">
                <div class="popup" id="popup">
                    <a href="#" id="btn-cerrar-popup" class="btn-cerrar-popup"><i class="fas fa-times"></i></a>
                    <h3>Tu contraseña es: </h3> ${pass}
                </div>
            </div>   
                

        </div>
        
    </body>
</html>
