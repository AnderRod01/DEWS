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

/**
 * Servlet implementation class ServletJuego
 */
@WebServlet(urlPatterns= "/ServletJuego", name = "ServletJuego")
public class ServletJuego extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
		
		String palabra = AlmacenPalabras.getPalabra();
		
		
		response.setContentType("text/html;charset=UTF-8");        
		PrintWriter out = response.getWriter();
		
		drawMainPage(request, response, out, session);
	}
	
	private void drawMainPage (HttpServletRequest request, HttpServletResponse response, PrintWriter out, HttpSession session) {
		
		String baseUrl = baseUrl(request);
		
		out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("</head>");
        out.print("<body>");
        
        
        out.print("<form action='ServletComprobar' method='post'>");
        out.print("<table><tr>");
        for ()
        {
        	out.print("<td><a href=''></a></td>");
        }


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
