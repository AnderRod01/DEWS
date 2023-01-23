<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nueva Cuenta</title>
</head>
<body>
	<h1>Nueva Cuenta</h1>
	<form action="ServletNuevaCuenta" method="post">
		<table>
			<tr>
				<td>Titular</td>
				<td><input type="text" name="titular"></td>
			</tr>
			<tr>
				<td>Saldo inicial</td>
				<td><input type="text" name="saldo"></td>
			</tr>
			<tr>
				<td>
					<button type="submit" name="crear">Crear Cuenta Corriente</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>