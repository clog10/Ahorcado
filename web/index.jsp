<%-- 
    Document   : index
    Created on : 4/05/2021, 06:58:10 PM
    Author     : Carlos Loaeza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acceso</title>
    </head>
    <body>
    <center>
        <h1>Inicio del juego</h1>
        <h2>Ahorcado</h2>
        <h3>
            <form method="get" action="acceder.jsp">
                <table with="50%">
                    <tr>
                        <td align="right">Participante:</td>
                        <td><input type="text" name="nombre" maxlength="20" required="true"></td>
                    </tr>
                    <tr>
                        <td align="right">Num. Intentos: </td>
                        <td><input type="number" name="nintento" min="1" max="3" required="true"></td>
                    </tr>
                </table>
                <input type="submit" value="Continuar">
            </form>
        </h3>
    </center>
</body>
</html>
