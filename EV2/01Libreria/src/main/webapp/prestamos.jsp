<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
	 	<c:when test="${listaPrestamos == null}">
            <jsp:forward page="ServletDevolver"/>
        </c:when>
        <c:when test="${listaPrestamos > 0 }">
        	<h1>Lista de Prestamos</h1>
        	<c:forEach items="${listaPretamos }" var="prestamo" varStatus="cont">
        		<tr>
        			<td><c:out value="${cont.count }"/>.-</td>
        			<td>${prestamo.titulo }
        		</tr>
        	</c:forEach>
        </c:when>
	</c:choose>


</body>
</html>