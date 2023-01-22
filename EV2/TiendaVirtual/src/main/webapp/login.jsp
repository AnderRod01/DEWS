<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	
	<form action="ServletLogin" method="post">
	<table>
		<tr>
			<td><label>Usuario:</label></td>
			<td><input name="txtUser" type="text"></td>
		</tr>
		<tr>
			<td><label>Contraseña:</label></td>
			<td><input name="txtPwd" type="password"></td>
		</tr>
	</table>
	
	<c:if test="${mensaje != null}">
		 <p style="color:red;">${mensaje}</p>
	</c:if>
	<c:if test="${mensajeSuccess != null}">
		 <p style="color:lime;">${mensajeSuccess}</p>
	</c:if>
	<div>
		<input name="btnLogin" type="submit" value="Login">
		<input name="btnReset" type="reset" value="Reset">
		<a href="registro.jsp">REGISTRARSE</a>
	</div>		
	</form>
</body>
</html>