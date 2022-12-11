package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AlmacenMatrices;

/**
 * Servlet implementation class ServletGuardaMatriz
 */
@WebServlet("/servletGuardaMatriz")
public class ServletGuardaMatriz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {

	            int filas = 0;
	            int columnas = 0;

	            /*
	             * Get rows and columns or redirect to index 
	             */
	            try {
	                filas = Integer.parseInt(request.getParameter("numFilas"));
	                columnas = Integer.parseInt(request.getParameter("numColumnas"));
	            } catch (NumberFormatException e) {
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
	                dispatcher.forward(request, response);
	            }

	            /*
	             * create a matrix and show menu
	             * if fails redirects to IntroCeldas with an error
	             */
	            try {
	                int[][] matrix = new int[filas][columnas];
	                for (int i = 0; i < filas; i++) {
	                    for (int j = 0; j < columnas; j++) {
	                        int num = Integer.parseInt(request.getParameter("celda" + (i + 1) + "-" + (j + 1)));
	                        matrix[i][j] = num;
	                    }
	                }

	                AlmacenMatrices.aniadirMatriz(matrix);
	                String baseUrl = baseUrl(request);
	                mostrarMensaje(baseUrl, out, filas, columnas);
	            } catch (NumberFormatException e) {
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/celdas");
	                request.setAttribute("error", "Debes rellenar correctamente la matriz");
	                request.setAttribute("rows", filas);
	                request.setAttribute("columns", columnas + "");
	                dispatcher.forward(request, response);
	            }
	        }
	    }
	  
	  private void mostrarMensaje(String baseUrl, PrintWriter out, int filas, int columnas) {        
	        out.println("<!DOCTYPE html>"); 
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Matrices.com - Matriz guardada</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<h1>Matriz de " + filas + " x " + columnas +" guardada correctamente</h1>");
	        out.println("<p>Numero de matrices guardadas: " + AlmacenMatrices.getMatrices().size() + "</p>");
	        out.println("<a href='" + baseUrl + "'>Añadir nueva matriz</a>");
	        out.println("<a href='" + baseUrl + "ServletVisorMatrices'>Ver matrices</a>");
	        out.println("</body>");
	        out.println("</html>");
	    }

	  private static String baseUrl(HttpServletRequest req){
	        StringBuffer url = req.getRequestURL();
	        String uri = req.getRequestURI();
	        String context = req.getContextPath();
	        return url.substring(0, url.length() - uri.length() + context.length()) + "/";
	    }
	  
	  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
