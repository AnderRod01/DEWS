<%@page import="beans.Primitiva"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page import="beans.Primitiva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    final Primitiva primitiva = (Primitiva) session.getAttribute("primitiva");
    
    if(primitiva == null) {
        response.sendRedirect(getServletContext().getContextPath());
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apuestas</title>
        <link rel="stylesheet" href="./styles/bubbleUI-min.css"/>
    </head>
    <body>
        <h1>Comprueba apuestas del <c:out value="${primitiva.getFecha()}"/></h1>
        <form action="<%= getServletContext().getContextPath() + "/fecha" %>" method="post">
            <table>
                <tr>
                    <td><label for="name">Nombre</label></td>
                    <td><input type="text" name="name" id="name"></td>
                </tr>
                <tr>
                    <td><label for="administration">Administración nº</label></td>
                    <td><input type="text" name="administracion" id="administracion"></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td colspan="6"><label for="n-0" class="bold">Introduce 6 números</label></td>
                </tr>
                <tr>
                    <td><input type="text" name="numbers" id="n-0" class="mini"></td>
                    <td><input type="text" name="numbers" class="mini"></td>
                    <td><input type="text" name="numbers" class="mini"></td>
                    <td><input type="text" name="numbers" class="mini"></td>
                    <td><input type="text" name="numbers" class="mini"></td>
                    <td><input type="text" name="numbers" class="mini"></td>
                </tr>
                <tr>
                    <td colspan="3"><label for="drawback" class="bold">Introduce reintegro</label></td>
                    <td colspan="3"><input type="text" name="drawback" id="drawback"></td>
                </tr>
                <tr>
                    <td colspan="6">
                        <input type="submit" name="check-bets" value="Comprobar">
                    </td>
                </tr>
            </table>
            <c:if test="${result != null}">  
                <p class="text-info"> <c:out value="${result}"/></p>
            </c:if> 

            <c:if test="${error != null}">  
                <p class="text-error"> <c:out value="${error}"/></p>
            </c:if> 

        </form>
    </body>
</html>
