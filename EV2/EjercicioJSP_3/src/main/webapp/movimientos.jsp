<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	session = request.getSession(false);
	
	if (session == null) {
		response.sendRedirect("nuevacuenta.jsp");
	}
	
	String titular = (String) session.getAttribute("titular");
	String saldo = (String) session.getAttribute("saldo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movimientos</title>
</head>
<body>
	<form action="ServletMovimientos" method="post">
		<table>
			<tr>
				<td>MOVIMIENTOS</td>
			</tr>
			<tr>
				<td>Titular</td>
				<td><c:out value="${titular}" /></td>
			</tr>
			<tr>
				<td>Saldo actual</td>
				<td><c:out value="${saldo}" /></td>
			</tr>
			<tr>
				<td>CANTIDAD</td>
				<td><input type="text" name="cantidad"></td>
			</tr>
			<tr>
				<td>
					<button type="submit" name="procesar" value="ingresar">INGRESAR</button>
					<button type="submit" name="procesar" value="gastar">GASTAR</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>