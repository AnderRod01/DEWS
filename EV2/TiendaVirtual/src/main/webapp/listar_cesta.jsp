<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cesta</title>
</head>
<body>
	<form action="ServletUpdateLineaPedido" method="post">
		<table>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Cantidad</th>
				<th>Añadir</th>
			</tr>
			<c:forEach items="${carrocompra}" var="lineapedido">
				<tr>
					<td>${lineapedido.item.id}</td>
					<td>${lineapedido.item.nombre}</td>
					<td>${lineapedido.item.precio}</td>
					<td><input type="number" name="txtCantidad" value="0"></td>
					<td><button type="submit" value="${item.key}" name="aniadir">Añadir al carro</button></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>