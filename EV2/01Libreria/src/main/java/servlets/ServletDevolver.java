package servlets;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Libro;
import beans.Prestamo;
import dao.GestorBD;

/**
 * Servlet implementation class ServletDevolver
 */
@WebServlet(name = "ServetDevolver", urlPatterns = { "/ServletDevolver"})
public class ServletDevolver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GestorBD bd;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }
    
    private HashMap<Integer,Long> mapaDiasPrestados(LinkedList<Libro> librosPrestados){
    	HashMap<Integer,Long> mapaDiasPrestados = new HashMap<Integer,Long>();
    	
    	for (int i=0; i<librosPrestados.size(); i++) {
    		long fechaActual = new java.util.Date().getTime();
    		int idlibro = librosPrestados.get(i).getIdLibro();
    		long timeDia = 24*60*60*1000;
    		mapaDiasPrestados.put(idlibro , (fechaActual - bd.fechaPrestamo(idlibro).getTime())/timeDia);
    	}
    	return mapaDiasPrestados;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
    	if ()
    	doGet(request, response);
    }
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Libro> librosPrestados = bd.librosPrestados();
		request.getSession().setAttribute("librosPrestados", librosPrestados);
		request.getSession().setAttribute("mapaDiasPrestados", mapaDiasPrestados(librosPrestados));
		
		if (request.getParameter("libroDevuelto") != null) {
			
		}
		
		request.getRequestDispatcher("prestamos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
