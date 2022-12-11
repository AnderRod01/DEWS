package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class ServletIntroCeldas
 */
@WebServlet(urlPatterns = {"/servletIntroCeldas"})
public class ServletIntroCeldas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                
                String filas = request.getParameter("txtFilas") != null ? request.getParameter("txtFilas") : request.getAttribute("txtFilas") + "";
                String columnas = request.getParameter("txtColumnas") != null ? request.getParameter("txtColumnas") : request.getAttribute("txtColumnas") + "";
                
                int numFilas = Integer.parseInt(filas);
                int numColumnas = Integer.parseInt(columnas);
                boolean fondo = Boolean.parseBoolean(request.getParameter("chkFondo"));


                String error = (String) request.getAttribute("error");
                drawMatrix(out, numFilas, numColumnas, fondo, error);
                
            } catch (NumberFormatException e) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
                dispatcher.forward(request, response);
            }

        }
    }
	
	
	private void drawMatrix(PrintWriter out, int numFilas, int numColumnas, boolean fondo,String error) {
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Matrices</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='/EjercicioServlets/servletGuardaMatriz' method='post'>");
        out.println("<h1>Introduce valores: </h1>");
        
        out.print("<table style='background:" + (fondo ? "#f1f1f1" : "none") + "'>");
        for (int i = 1; i <= numFilas; i++) {
            out.print("<tr>"); 
            for (int j = 1; j <= numColumnas; j++) {
                out.print("<td>");
                out.print("<input type='text' name='celda" + i + "-" + j + "'>");
                out.print("</td>");
            }
            out.print("</tr>");
        }
        out.print("</table>");
        
        if(error != null)
            out.print("<p class='error'>" + error +"</p>");
        
        out.print("<input type='hidden' name='numFilas' value='" + numFilas + "'>");
        out.print("<input type='hidden' name='numColumnas' value='" + numColumnas + "'>");
        out.print("<input type='hidden' name='fondo' value='" + fondo + "'>");
        out.print("<input type='submit' name='guardar' value='Guardar matriz'>");
        out.print("<input type='reset' value='restablecer'>");
        out.println("</form>");
        out.println("</body>");

        out.println("</html>");
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
