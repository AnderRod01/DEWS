<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autores</title>
</head>
<body>
<%-- El par�metro autores se encuentran el ambito de sesi�n --%>
    <c:if test="${autores == null}">
        <jsp:forward page="ServletAutores"/>
    </c:if>

	<h1>Lista de Autores</h1>
	<table>
		
	</table>
	
	<h1>A�adir Autor</h1>
	<form>
		Nombre: <input type="text" name="nombre"> <br>
        Fecha de nacimiento: <input type="text" name="fechanac"><br>
        Nacionalidad: <input type="text" name="nacionalidad"><br>
		<input type="submit" value="A�adir" name="aniadir">
	</form>
	
</body>
</html>