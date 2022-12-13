package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Catalogo;

/**
 * Servlet implementation class ServletLibros
 */
@WebServlet(name= "ServletLibros", urlPatterns = "/ServletLibros")
public class ServletLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String err ="";
		
		
		
		ArrayList <String> listaSel = new ArrayList<String>();
		HttpSession session = request.getSession(false);
		
		if (request.getParameter("reiniciar")!= null) {
			session.invalidate();
			session = null;
		}
		if (session == null) {
			session = request.getSession(true);
			session.setAttribute("seleccionados", listaSel);
		}else {
			
			listaSel = (ArrayList<String>) session.getAttribute("seleccionados");
			String libroSel = request.getParameter("libro");
			
			if(listaSel.contains(libroSel)){
				err ="Ya has elegido " + libroSel; 
			}else {
				listaSel.add(libroSel);
			}
		}
		
		response.setContentType("text/html;charset=UTF-8");        
		PrintWriter out = response.getWriter();
        
	
		
		drawMainPage(request, response, out, session, err);
	}
       
	
	
	
	private void drawMainPage(HttpServletRequest request, HttpServletResponse response, PrintWriter out, HttpSession session, String error){
		
		final String baseUrl = baseUrl(request);
		String[] libros = Catalogo.getLibros();
		
		out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("</head>");
        out.print("<body>");
        
        
        out.print("<form action='" + baseUrl + "ServletLibros' method='post'>");
        out.print("<select name='libro'>");
        
        for (String libro:libros) {
        	out.print("<option value ='"+libro+"'>" + libro + "</option>");
        }
        out.print("</select>");
        out.print("<input type='submit' value='AGREGAR'>");
        
        ArrayList <String> listaSel = (ArrayList<String>) session.getAttribute("seleccionados");
        
        if (listaSel.size()==0) {
        	out.print("<h1>No se han elegido libros </h1>");
        }else {
        	out.print("<h1>TU ELECCIÓN:</h1>");
        	out.print("<ul>");
        	for (String titulo:listaSel) {
        		out.print("<li>" + titulo + "</li>");
        	}
        	out.print ("</ul>");
        }
        
        out.print("<p style='color:red;'>"+error+"</p>");
        
        out.print("<input type='submit' name='reiniciar' value='REINICIAR'>");
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
		doGet(request, response);
	}

}
