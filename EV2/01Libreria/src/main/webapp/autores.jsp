<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.LinkedList"%>
<%@page import="beans.Autor"%>
<%@page import="beans.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	LinkedList<Autor> autores = (LinkedList<Autor>) request.getAttribute("autores");
	
	if (autores == null) {
	    response.sendRedirect(getServletContext().getContextPath() + "/ServletAutores");
	}

	pageContext.setAttribute("autores", autores);
	
	LinkedList<Libro> librosAutor = (LinkedList<Libro>) request.getAttribute("librosAutor");
    String autor = (String) request.getAttribute("autor");
    if (librosAutor == null) {
        librosAutor = new LinkedList();
    }

    pageContext.setAttribute("librosAutor", librosAutor);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autores</title>
</head>
<body>
<c:if test="${autores.size() > 0}">
            <table>
                <tr>
                    <th>Nombre</th>
                    <th>Fecha de nacimiento</th>
                    <th>Nacionalidad</th>
                    <th>Ver libros</th>
                </tr>

                <c:forEach items="${autores}" var="autor">
                    <tr>
                        <td>${autor.getNombre()}</td>
                        <td><fmt:formatDate value="${autor.getFechanac()}" pattern='yyyy/MM/dd'/></td>
                        <td>${autor.getNacionalidad()}</td>
                        <td><a href='<%=getServletContext().getContextPath()%>/control?autor=${autor.getId()}'>Ver</a></td>
                    </tr>
                </c:forEach>

            </table>
        </c:if>

	<%-- <c:choose>
		<c:when test="${autores.size() > 0}">
			<h1>Lista de Autores</h1>
	        <table>
	            <tr>
	                <th>Nombre</th>
	                <th>Fecha de nacimiento</th>
	                <th>Nacionalidad</th>
	                <th>Ver libros</th>
	            </tr>
	            <c:forEach items="${autores}" var="autor">
	                <tr>
	                    <td>${autor.getNombre()}</td>
	                    <td><fmt:formatDate value="${autor.getFechanac()}" pattern='yyyy/MM/dd'/></td>
	                    <td>${autor.getNacionalidad()}</td>
	                    <td><a href='<%=getServletContext().getContextPath()%>/ServletAutores?autor=${autor.getId()}'>Ver</a></td>
	                </tr>
	            </c:forEach>
	        </table>
		</c:when>
		<c:otherwise>
			<h3>No existe ningun autor</h3>
		</c:otherwise>
	</c:choose> --%>
	<h1>Añadir Autor</h1>
	<form>
		Nombre: <input type="text" name="nombre"> <br>
        Fecha de nacimiento: <input type="text" name="fechanac"><br>
        Nacionalidad: <input type="text" name="nacionalidad"><br>
		<input type="submit" value="Añadir" name="aniadir">
	</form>
	
	<c:if test="${librosAutor.size() > 0}">
            <h1>Libros de ${autor}</h1>
            <ul>
                <c:forEach items="${librosAutor}" var="libro">
                    <li><a href="<%=getServletContext().getContextPath()%>/ServletAutores?prestar=${libro.getId()}">${libro.getTitulo()}</a></li>
                </c:forEach>
            </ul>
    </c:if>
	
</body>
</html>