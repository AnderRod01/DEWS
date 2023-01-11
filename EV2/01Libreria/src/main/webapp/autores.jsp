<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.LinkedList"%>
<%@page import="beans.Autor"%>
<%@page import="beans.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autores</title>
</head>
<body>
<%-- El parámetro autores se encuentran el ambito de sesión --%>
    <c:if test="${autores == null}">
        <jsp:forward page="ServletAutores"/>
    </c:if>

	<h1>Lista de Autores</h1>
	<table>
		
	</table>
	
	<h1>Añadir Autor</h1>
	<form>
		Nombre: <input type="text" name="nombre"> <br>
        Fecha de nacimiento: <input type="text" name="fechanac"><br>
        Nacionalidad: <input type="text" name="nacionalidad"><br>
		<input type="submit" value="Añadir" name="aniadir">
	</form>
	
	<c:if test="${librosAutor.size() > 0}">
            <h1>Libros de ${autor}</h1>
            <ul>
                <c:forEach items="${librosAutor}" var="libro">
                    <li><a href="<%=getServletContext().getContextPath()%>/control?prestar=${libro.getId()}">${libro.getTitulo()}</a></li>
                </c:forEach>
            </ul>
    </c:if>
	
</body>
</html>