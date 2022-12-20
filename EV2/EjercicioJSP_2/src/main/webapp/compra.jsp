<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compras</title>
</head>
<body>
    <%
    	ArrayList <String> productos = (ArrayList<String>) session.getAttribute("productos");
	    HashMap<String, Integer> comprados = (HashMap<String, Integer>) session.getAttribute("comprados");
	    if (comprados == null) {
	    	comprados = new HashMap();
	    }
	    if (request.getParameter("compra") != null) {
	        String producto = request.getParameter("compra");
	        if (!comprados.containsKey(producto)) {
	        	comprados.put(producto, 1);
	        } else {
	        	comprados.put(producto, comprados.get(producto)  + 1);
	        }
	    }
	    session.setAttribute("comprados", comprados);
	%>
	
	<table>
		<tr>
			<th>PRODUCTO</th>
			<th>PEDIR</th>
		</tr>
		<%
			for (String producto:productos){
				 out.print("<tr>");
                 out.print("<td>" + producto + "</td>");
                 out.print("<td>");
                 out.print("<form action='" + getServletContext().getContextPath() + "/compra.jsp' method='get'>");
                 out.print("<input type='submit' value='Adquirir unidad'>");
                 out.print("<input type='hidden' name='compra' value='" + producto + "'>");
                 out.print("</form>");
                 out.print("</td>");
                 
                 if (comprados.containsKey(producto)){
                	 out.print("<td>" +comprados.get(producto) + " unidades</td>");
                     
                     out.print("</tr>");
                 }
			}
		%>
	</table>
	
	<jsp:include page="/muestracarro.jsp"></jsp:include>
</body>
</html>