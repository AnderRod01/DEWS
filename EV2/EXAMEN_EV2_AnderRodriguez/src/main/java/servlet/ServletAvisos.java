package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Alumno;
import beans.Impartidor;
import dao.ActividadesDao;

/**
 * Servlet implementation class ServletAvisos
 */
@WebServlet("/ServletAvisos")
public class ServletAvisos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAvisos() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	Impartidor impartidor = (Impartidor) request.getSession().getAttribute("impartidor");
    	
    	
        
        
        cargarActividadesImpartidor(request, impartidor);
        
        if(request.getParameter("asistencia")!=null) {
        	cargarAsistencia(request,response, impartidor);
        }
        
        request.getRequestDispatcher("impartidor.jsp").include(request, response);
    }
    
    //metodo para cargar las asistencias de una actividad
	private void cargarAsistencia(HttpServletRequest request,HttpServletResponse response, Impartidor impartidor) throws IOException {
//		Map<Alumno, Date> mapaAsistencia = ActividadesDao.mapaAsistenciaActividad(Integer.parseInt(request.getParameter("asistencia")));
//		response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        
//        out.println("<table><tr><th>NOMBRE</th><th>Ultima asistencia</th><th>Tipo de aviso</th>");
		
		
 	}

	private void cargarActividadesImpartidor(HttpServletRequest request, Impartidor impartidor) {
		request.getSession().setAttribute("actividadesImpartidor", ActividadesDao.actividadesImpartidor(impartidor.getId()));
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
