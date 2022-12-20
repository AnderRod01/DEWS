package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AlmacenPalabras;
import beans.Juego;

/**
 * Servlet implementation class ServletComprobar
 */
@WebServlet(name = "ServletComprobar",urlPatterns = "/ServletComprobar")
public class ServletComprobar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String baseUrl = baseUrl(request);
		
		
		HttpSession session = request.getSession(true);
		
		System.out.println(request.getParameter("check"));
		System.out.println(request.getParameter("palabra"));
		
		Juego juego = (session.getAttribute("juego") != null) ? (Juego) session.getAttribute("juego") : new Juego(AlmacenPalabras.getPalabra());
		response.getWriter().append(baseUrl);
		if  (request.getParameter("check") != null && request.getParameter("palabra") != null) {
			String palabra = request.getParameter("palabra");
			if (palabra.equalsIgnoreCase(juego.getPalabra())) {
				response.sendRedirect(baseUrl + "ServletJuego?win"); 
				return;
			} else {
				juego.fallo();
			}
		}
		
		if (request.getParameter("letra") != null) {
			juego.mostrar(request.getParameter("letra"));
			juego.fallo();
		}
		
		response.sendRedirect(baseUrl + "ServletJuego");
		
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
