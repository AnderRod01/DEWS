<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>LOGIN</title>
<link rel="stylesheet" type="text/css" href="css/estilos.css" />
</head>
<body>
	<div id="header">
		<h1>APLICACIÓN ACTIVIDADES</h1>
	</div>
	<div id=menu>
		<h2>ALUMNOS</h2>
	</div>
	<div id="container">
		<p>
			SOCIO:
			<c:out value="${alumno.nombre}" />
			<c:out value=" ${alumno.apellidos}" />
		</p>
		<!-- Introduce el contenido de la pantalla de Alumnos -->
		<table>
			<tr>
				<th>ACTIVIDAD</th>
				<th>PRECIO</th>
				<th>IMPARTIDOR</th>
			</tr>
			<c:forEach items="${actividadesAlumno}" var="actividad">
				<tr>
					<td>${actividad.nombre }</td>
					<td>${actividad.coste_mensual}</td>
					<td>${actividad.impartidor.nombre}
						${actividad.impartidor.apellido }</td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<th>ACTIVIDAD</th>
				<th>IMPARTIDOR</th>
				<th>APUNTARSE</th>
			</tr>
			<c:forEach items="${actividadesLibres}" var="actividad">
				<c:choose>
					<c:when test="${inscripciones.contains(actividad.id)}">
						<tr style="background-color: yellow;">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
				<td>${actividad.nombre }</td>
				<td>${actividad.impartidor.nombre}
					${actividad.impartidor.apellido }</td>
				<c:choose>
					<c:when test="${inscripciones.contains(actividad.id)}">
						<td style="background-color: yellow;"><a
							href="ServletInscripcion?anular=${actividad.id}">ANULAR</a></td>
					</c:when>
					<c:otherwise>
						<td><a href="ServletInscripcion?inscribirse=${actividad.id}">APUNTARSE</a></td>
					</c:otherwise>
				</c:choose>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${nuevasInscripciones > 0 }">
			<a href="ServletInscripcion?guardar=true">GUARDAR LAS ${nuevasInscripciones} NUEVAS INSCRIPCIONES</a>
		</c:if>
	</div>
</body>
</html>