package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AlmacenPalabras;
import beans.Juego;

/**
 * Servlet implementation class ServletJuego
 */
@WebServlet(urlPatterns= "/ServletJuego", name = "ServletJuego")
public class ServletJuego extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String baseUrl;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
		String error = null;
		baseUrl = baseUrl(request);
		String palabra = AlmacenPalabras.getPalabra();
		HttpSession session = request.getSession(true);
		
		Juego juego = (session.getAttribute("juego") != null) ? (Juego) session.getAttribute("juego") : new Juego(AlmacenPalabras.getPalabra());
		
		if (juego.getVidas() <= 0) {
			error =  "Has fallado! La palabra era " + juego.getPalabra();
			session.invalidate();
		}
		
		
		
		
		
		response.setContentType("text/html;charset=UTF-8");        
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("win") != null) {
			drawWinPage(out);
		}else {
			drawMainPage(out, session, error, juego);
		}
		
		session.setAttribute("juego", juego);
	
	}
	
	
	
	private void drawMainPage (PrintWriter out, HttpSession session, String error, Juego juego) {
		

		
		out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("</head>");
        out.print("<body>");
        
        
        out.print("<form action='ServletComprobar' method='post'>");
        out.print("<table><tr>");
        juego.getPalabra().chars().forEach(charcode -> {
            if (error != null || juego.puedeMostrarse(((char) charcode) + "")) {
            	out.print("<td class='letter'>" + (char) charcode + "</td>");
            } else {
            	out.print("<td class='letter'>");
            	out.print("<a href='" + baseUrl + "ServletComprobar?letra=" + (char) charcode + "'>Ver</a>");
            	out.print("</td>");
            }
        });
        out.print("</table></tr>");

        if (error != null) {
            out.print("<p class='text-error'>" + error + "</p>");
            out.print("<a href='" + baseUrl + "ServletJuego'>Reintentar</p>");
        } else {
        	out.print("<form action='" + baseUrl + "ServletComprobar' method='post'>");
        	out.print("<table style='margin-top: 2rem;'>");
        	out.print("<tr><td>" + juego.getVidas() + " vidas </td></tr>");
        	out.print("<tr>");
        	out.print("<td>Tu respuesta: </td>");
        	out.print("<td><input name='palabra' type='text'></td>");
        	out.print("<td><input name='check' value='Comprobar' type='submit'></td>");
        	out.print("</tr>");
        	out.print("</table>");
        	out.print("</form>");
        }

        out.print("</body>");
        out.print("</html>");

	}
	
	private void drawWinPage (PrintWriter out) {
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>HAS GANADO!</h1>");
		out.print("<a href='" + baseUrl + "'>Jugar de nuevo</a>");
        out.print("</body>");
        out.print("</html>");
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
