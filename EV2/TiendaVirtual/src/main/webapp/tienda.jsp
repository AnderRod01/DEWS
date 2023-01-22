<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LAMINAS</title>
</head>
<body>
	<h1>LAMINAS</h1>
	<c:if test="${listaItems == null}">
		<jsp:forward page="ServletAgregarLineaPedido"></jsp:forward>
	</c:if>

	<table>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Precio</th>
			<th>Cantidad</th>
			<th>Añadir</th>
		</tr>
		<c:forEach items="listaItems" var="item">
			<tr>
				<td>${item.id}</td>
				<td>${item.nombre}</td>
				<td>${item.precio}</td>
				<td>cantidad</td>
				<td><input type="submit" value="Añadir al carro" name="aniadir"></td>
			</tr>
		</c:forEach>
	</table>

	<form action="listar_cesta.jsp">
		<input type="submit" value="Ver cesta" />
	</form>
	<form action="pedir.jsp">
		<input type="submit" value="Hacer pedido" />
	</form>
	<form action="ServletListarPedidos">
		<input type="submit" value="Mis pedidos" />
	</form>
</body>
</html>