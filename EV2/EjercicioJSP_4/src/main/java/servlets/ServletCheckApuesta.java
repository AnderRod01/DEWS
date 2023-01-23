package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Apuesta;
import beans.Primitiva;
import exception.BadBetException;

/**
 * Servlet implementation class ServletCheckApuesta
 */
@WebServlet("/ServletCheckApuesta")
public class ServletCheckApuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 if(request.getParameter("check-bets") == null){
	            sendError(request, response, "");
	            return;
	        }
	        
	        final String nombre = request.getParameter("name");
	        if(nombre == null || "".equals(nombre)) {
	            sendError(request, response, "Rellene nombre.");
	            return;
	        }
	        
	        final String adminParam = request.getParameter("administration");
	        if(adminParam == null || "".equals(adminParam)){
	            sendError(request, response, "Rellene administracion.");
	            return;
	        }
	        
	        final String[] numbersParam = request.getParameterValues("numbers");
	        if(numbersParam == null){
	            sendError(request, response, "Rellene todos los numeros.");
	            return;
	        }        
	        
	        final String drawbackParam = request.getParameter("drawback");
	        if(drawbackParam == null || "".equals(drawbackParam)) {
	            sendError(request, response, "Rellene reintegro.");
	            return;
	        }

	        int administracion = 0;        
	        try {
	            administracion = Integer.parseInt(adminParam);
	        } catch (NumberFormatException e) {
	            sendError(request, response, "Administracion invalida.");
	            return;
	        }
	        
	        ArrayList<Integer> numeros = new ArrayList<>();
	        try {
	            for (String number : numbersParam) {
	                numeros.add(Integer.parseInt(number));
	            }
	        } catch (NumberFormatException e) {
	            sendError(request, response, "Numeros invalidos.");
	            return;
	        }
	        
	        int reintegro = 0;
	        try {
	            reintegro = Integer.parseInt(drawbackParam);
	        } catch (NumberFormatException e) {
	            sendError(request, response, "Reintegro invalido.");
	            return;
	        }

	        try {        
	            final HttpSession session = request.getSession(true);
	            final Primitiva lottery = (Primitiva) session.getAttribute("primitiva");
	            final Apuesta bet = new Apuesta(nombre, administracion, numeros.toArray(new Integer[6]) , reintegro);
	              
	            final RequestDispatcher dispatcher = request.getRequestDispatcher("/check_apuestas.jsp");
	            request.setAttribute("result", lottery.result(bet));
	            dispatcher.forward(request, response);        
	        } catch(BadBetException e) { 
	            sendError(request, response, "Apuesta invalida. " + e.getMessage()); 
	        }
	      
	    }
	    

	    private void sendError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
	        final RequestDispatcher dispatcher = request.getRequestDispatcher("/check_apuestas.jsp");
	        request.setAttribute("error", message);
	        dispatcher.forward(request, response);
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
