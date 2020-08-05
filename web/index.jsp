<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <link href="default.css" rel="stylesheet" type="text/css"/>
        <title>Banco El Quebrado</title>
    </head>
    <body>
        <div class="Wrapper">
            <h1>Login &#128125;</h1>
            <form id="form_principal" action="banco" method="POST">  
                <input type="text" name="username" placeholder="&#128128; Usuario" >
                <input type="password" name="password" placeholder="&#128272; Contraseña">
                <input type="submit" value="Ingresar">
                <a href="Recuperar_pass.jsp">Recuperar mi contraseña</a> <br>
                <a href="AgregarCuenta.jsp">No tengo una cuenta</a>
            </form>
        </div> 
    </body>
</html>
