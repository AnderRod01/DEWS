package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ConversionCF;

/**
 * Servlet implementation class Ejercicio1
 */
@WebServlet(name="convertirtemperatura", urlPatterns = {"/conversion"})
public class ServletConversor extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private HashSet<String> locales;
	
	@Override
    public void init() throws ServletException {
        super.init(); 
        locales = new HashSet<>();
    }
	
	protected void doGet(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        final String locale = request.getLocale().toString(); 
        locales.add(locale);
        
        if(request.getParameter("celfah") != null){
        	try{
        		if (request.getParameter("txtCel") != ""){
        			ConversionCF conv = new ConversionCF(Float.parseFloat(request.getParameter("txtCel")), 'c');
        			
	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title>Resultado</title>");
	                out.println("</head>");
	                out.println("<body>");
	                out.println("<h1>Resultado de la conversion:</h1>");
	                out.println("<p><strong>Valor en celsius:</strong>" + conv.getCel() + " </p>");
	                out.println("<p><strong>Valor en fahrenheit:</strong>" + conv.getFah() + " </p>");
	                out.println("<p>Se han establecido conexiones desde " + locales.size() + " distintos locale's</p>");
	                out.println("<a href='conversorCF.html'><p>Enlace para volver al formulario</p></a>");
	                out.println("</body>");
	                out.println("</html>");
        		}else {
        			out.println("<p><strong>ERROR: </strong> Debes indicar los grados Celsius</p>");
 	                out.println("<a href='conversorCF.html'><p>Enlace para volver al formulario</p></a>");
        		}
            }finally{
                out.close();
            }
        	
        }else {
        	if(request.getParameter("fahcel") != null){
            	try{
            		if (request.getParameter("txtFah") != ""){
            			ConversionCF conv = new ConversionCF(Float.parseFloat(request.getParameter("txtFah")), 'f');
            			
    	                out.println("<html>");
    	                out.println("<head>");
    	                out.println("<title>Resultado</title>");
    	                out.println("</head>");
    	                out.println("<body>");
    	                out.println("<h1>Resultado de la conversion:</h1>");
    	                out.println("<p><strong>Valor en celsius:</strong>" + conv.getCel() + " </p>");
    	                out.println("<p><strong>Valor en fahrenheit:</strong>" + conv.getFah() + " </p>");
    	                out.println("<p>Se han establecido conexiones desde " + locales.size() + " distintos locale's</p>");
    	                out.println("<a href='conversorCF.html'><p>Enlace para volver al formulario</p></a>");
    	                out.println("</body>");
    	                out.println("</html>");
            		}else {
            			out.println("<p><strong>ERROR: </strong> Debes indicar los grados Fahrenheit</p>");
     	                out.println("<a href='conversorCF.html'><p>Enlace para volver al formulario</p></a>");
            		}
                }finally{
                    out.close();
                }
            	
            }
        }
        
	}
}
