<%@page import="java.io.File"%>
<%@page import="beans.Imagen"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%!
	final String CARPETA_IMG = "img";	

	private ArrayList<Imagen> imagenesDeCarpeta (String nombreCarpeta){
		final File directorio = new File(getServletContext().getRealPath(nombreCarpeta));
		ArrayList <Imagen> listaImagenes = new ArrayList();
		
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            for (File imagen : archivos) {
                listaImagenes.add(new Imagen(nombreCarpeta + "/" + imagen.getName(),imagen.getName(), imagen.length()));
            }
        } else {
            directorio.mkdirs();
        }
		
		return listaImagenes;
	}
	
%>

<%
	final ArrayList<Imagen> LISTA_IMAGENES = imagenesDeCarpeta(CARPETA_IMG);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visor de imagenes</title>
</head>
<body>
	<form action="http://localhost:8080<%= getServletConfig().getServletContext().getContextPath()%>" method ="get">
		<label for="imagenes">Imagen: </label>
		<select name="imagenes">
		<%
			for(int i=0; i<LISTA_IMAGENES.size(); i++){
				
				out.print("<option value='"+i+"'>");
				out.print(LISTA_IMAGENES.get(i).getNombre());
				out.print("</option>");
			}
		%>
		</select>
        <span>
            Tamaño:
            <label><input type="radio" name="tamanio" value="300" checked>300px</label>
            <label><input type="radio" name="tamanio" value="400">400px</label>
            <label><input type="radio" name="tamanio" value="500">500px</label>
        </span>
		<input type="submit" name="verImagen" value="VER IMAGEN">
		<br>
		<%
			
			if(request.getParameter("verImagen") != null){
				Imagen imagen =  LISTA_IMAGENES.get(Integer.parseInt(request.getParameter("imagenes")));
				out.print("<p><b>TAMAÑO:	</b>" + imagen.tamanioDesglosado() + "</p>");
				
				String imagenSeleccionada = imagen.getRuta();
				out.print("<img src='" + imagenSeleccionada +"' width='" + request.getParameter("tamanio") +"'>");
			}
		%>
	</form>
</body>
</html>