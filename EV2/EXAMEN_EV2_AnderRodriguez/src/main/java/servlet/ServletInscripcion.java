package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Actividad;
import beans.Alumno;
import dao.ActividadesDao;

/**
 * Servlet implementation class ServletInscripcio
 */
@WebServlet("/ServletInscripcion")
public class ServletInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscripcion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Alumno alumno = (Alumno) request.getSession().getAttribute("alumno");
    	
    	if(request.getParameter("guardar") != null) {
    		guardarEnBBDD(request, alumno);
    	}
    	
    	//Recogemos datos de BBDD y los introducimos en la sesion
    	ArrayList<Actividad> actividadesAlumno = ActividadesDao.obtenerActividadesParticipa(alumno.getDni());
    	request.getSession().setAttribute("actividadesAlumno", actividadesAlumno);
    	
    	ArrayList<Actividad> actividadesLibres = ActividadesDao.obtenerActividadesLibresNoParticipa(alumno.getDni());
    	request.getSession().setAttribute("actividadesLibres", actividadesLibres);
    	
    	
    	
    	if (request.getSession().getAttribute("nuevasInscripciones") == null)
    		request.getSession().setAttribute("nuevasInscripciones", 0);
    	int nuevas =  (int) request.getSession().getAttribute("nuevasInscripciones");
    	
    	inscribirse(request, nuevas);
    	anular(request, nuevas);
    	
    	

    	
    	request.getRequestDispatcher("alumno.jsp").include(request, response);
    }


	private void guardarEnBBDD(HttpServletRequest request, Alumno alumno) {
		ArrayList<Integer> inscripciones  = (ArrayList<Integer>) request.getSession().getAttribute("inscripciones");
		for (Integer id : inscripciones) {
			ActividadesDao.inscribir(alumno, ActividadesDao.getActividad(id));
		}
		
		inscripciones.clear();
		request.getSession().setAttribute("nuevasInscripciones", 0);
	}


	private void anular(HttpServletRequest request, int nuevas) {
		//Si se clica en anular, se quita de la lista de inscripciones nuevas
    	if(request.getParameter("anular") != null) {
    		ArrayList<Integer> inscripciones = (ArrayList<Integer>) request.getSession().getAttribute("inscripciones");
    		int i = inscripciones.indexOf(Integer.parseInt(request.getParameter("anular")));
    		inscripciones.remove(i);
    		
    		request.getSession().setAttribute("nuevasInscripciones", nuevas--);
    	}
	}


	private void inscribirse(HttpServletRequest request, int nuevas) {
		//Si se clica en inscribirse, se añade a la lista de inscripciones nuevas
    	if (request.getParameter("inscribirse") != null) {
    		if (request.getSession().getAttribute("inscripciones") == null)
    			request.getSession().setAttribute("inscripciones", new ArrayList<Integer>());
    		
    		ArrayList<Integer> inscripciones = (ArrayList<Integer>) request.getSession().getAttribute("inscripciones");
    		inscripciones.add(Integer.parseInt(request.getParameter("inscribirse")));
    		

    		request.getSession().setAttribute("nuevasInscripciones", nuevas++);

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
		doGet(request, response);
	}

}
