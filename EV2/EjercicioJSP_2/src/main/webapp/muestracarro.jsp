<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 		<br>
        <h1>Tu carro: </h1>
        <br>
        <%
            HashMap<String,Integer> comprado = (HashMap<String,Integer>)session.getAttribute("comprado");
            for (String producto : comprado.keySet()) {
                
                out.print("<li>");
                out.print("<b>" + producto + "</b>");
                out.print("&nbsp;&nbsp;&nbsp; " + comprado.get(producto) + " unidades ");
                out.print("</li>");
            }
        %>
</body>
</html>