<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elige producto</title>
</head>
<body>
	 <form action="<%= getServletContext().getContextPath() + "/productos" %>" method="get">
	      <select name="categ">
	          <option value="-">todos</option>
	          <option value="100">100</option>
	          <option value="200">200</option>
	          <option value="300">300</option>
	      </select>
	      <input type="submit" value="Ver productos">
      </form>
</body>
</html>