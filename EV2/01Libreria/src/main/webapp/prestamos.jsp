<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DEVOLUCIONES</title>
</head>
<body>
	<c:choose>
	 	<c:when test="${librosPrestados == null}">
            <jsp:forward page="ServletDevolver"/>
        </c:when>
        <c:when test="${librosPrestados.size() > 0 }">
        	<h1>Lista de Prestamos</h1>
        	<table>
        	<c:forEach items="${librosPrestados }" var="libro" varStatus="cont">
        		<tr>
        			<td><c:out value="${cont.count }"/>.-</td>
        			<td>${libro.titulo }, <c:out value="${mapaDiasPrestados[libro.idLibro] }"/> dias prestados</td>
        			<c:choose>
        				<c:when test="">
        					<td><a href='<%=getServletContext().getContextPath()%>/ServletDevoluciones?libroDevuelto=${libro.idLibro}'>MARCAR DEVOLUCION</a></td>
        				</c:when>
        				<c:otherwise>
        					
        				</c:otherwise>
        			</c:choose>
        		</tr>
        	</c:forEach>
        	</table>
        	<p><a href="">GRABAR DEVOLUCIONES</a> ( libros)</p>
        </c:when>
	</c:choose>
</body>
</html>