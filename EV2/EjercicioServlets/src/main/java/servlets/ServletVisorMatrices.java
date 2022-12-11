package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AlmacenMatrices;

/**
 * Servlet implementation class ServletVisorMatrices
 */
@WebServlet("/ServletVisorMatrices")
public class ServletVisorMatrices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Matrices.com - Guardadas</title>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Matrices guardadas</h1>");
            
            for (int[][] matrix : AlmacenMatrices.getMatrices()) {
                out.print("<table class='matrix-view' >");
                for (int[] row : matrix) {
                    out.print("<tr>");
                    for (int number : row) {
                      out.print("<td>" + number + "</td>");  
                    }
                    out.print("</tr>");
                }
                out.print("</table>");
            }
            
            
            out.println("</body>");
            out.println("</html>");
        }
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
