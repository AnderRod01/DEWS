<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
</head>
<body>
	<form action="ServletRegistro" method="post">
		<h1>Registro</h1>
		<table>
			<tr>
				<td><label>Usuario:</label></td>
				<td><input name="txtUser" type="text"></td>
			</tr>
			<tr>
				<td><label>Password:</label></td>
				<td><input name="txtPwd" type="password"></td>
			</tr>
			<tr>
				<td><label>Domicilio:</label></td>
				<td><input name="txtDomicilio" type="text"></td>
			</tr>
			<tr>
				<td><label>Zip:</label></td>
				<td><input name="txtCP" type="number"></td>
			</tr>
			<tr>
				<td><label>Telefono:</label></td>
				<td><input name="txtTlf" type="text"></td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><input name="txtEmail" type="email"></td>
			</tr>
		</table>
		<c:if test="${mensaje != null}">
			<p style="color: red;">${mensaje}</p>
		</c:if>
		<div>
			<input name="btnRegistrar" type="submit" value="Registrarse">
			<input name="btnReset" type="reset" value="Reset">
		</div>
	</form>
</body>
</html>