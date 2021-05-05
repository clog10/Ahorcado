<%-- 
    Document   : acceder
    Created on : 4/05/2021, 08:36:02 PM
    Author     : Carlos Loaeza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jugar</title>
    </head>
    <body>
    <center>
        <h1>Hola</h1>
        <h3>
            <form method="post" action="Ahorcado">
                <table with="50%">
                    <tr>
                        <td align="right">Participante:</td>
                        <td><input type="text" name="nombre" maxlength="20"></td>
                    </tr>
                    <tr>
                        <td align="right">Num. Intentos: </td>
                        <td><input type="number" name="nintento" min="1" max="3" required="true"></td>
                    </tr>
                    <tr>
                        <td align="right">Grado de Dificultad (1, 2, 3): </td>
                        <td><input type="number" name="grado" min="1" max="3" required="true"></td>
                    </tr>
                </table>
                <input type="submit" value="Continuar">
            </form>
        </h3>
    </center>
    </body>
</html>
