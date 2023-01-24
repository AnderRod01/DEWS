package servlet;

import java.io.IOException;

import javax.enterprise.context.spi.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Alumno;
import beans.Impartidor;
import dao.AlumnosDao;
import dao.ImpartidoresDao;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//    super.init(config);
//    try {
//     InitialContext ctx = new InitialContext();
//     Context env = (Context) ctx.lookup("java:comp/env");
//     // nombre del recurso en el context.xml
//     ds = (DataSource) env.lookup("jdbc/poolSubastasDB");
//     } catch (NamingException e) {
//     e.printStackTrace();
//     }
//     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session!= null) {
			session.invalidate();
			session = null;
		}
		
		session = request.getSession(true);
		

		String botonAlumno = request.getParameter("btnLoginAlumno");
		String botonImpartidor = request.getParameter("btnLoginImpartidor");

		if (botonAlumno != null && !botonAlumno.isBlank()) {

			Alumno alumno = loginAlumno(request.getParameter("usuario"), request.getParameter("password"));
			request.getSession().setAttribute("alumno", alumno);
			if (alumno != null) {
				request.getRequestDispatcher("ServletInscripcion").include(request, response);
			} else {
				request.getRequestDispatcher("index.jsp").include(request, response);
			}

		} else if (botonImpartidor != null && !botonImpartidor.isBlank()) {
			Impartidor impartidor = loginImpartidor(request.getParameter("usuario"), request.getParameter("password"));
			request.getSession().setAttribute("impartidor", impartidor);
			if (impartidor != null) {
				request.getRequestDispatcher("ServletAvisos").include(request, response);
			} else {
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
		} else {
			request.getSession().setAttribute("error",
					"El usuario o la contraseña no son correctas, vuelva a intentarlo");
			request.getRequestDispatcher("index.jsp").include(request, response);
		}

	}

	/**
	 * Método loginAlumno, destinado al login de alumnos: recibe un dni y una clave
	 * y devuelve el Alumno correspondiente a dicha combinación. Los alumnos se
	 * loguean usando como usuario su dni y como password las 3 primeras letras de
	 * sus apellidos concatenadas minúscula (Marta Gómez Aguirre tiene la clave
	 * “gomagu”)
	 * 
	 * @param dni
	 * @param password
	 * @return
	 * @throws NamingException
	 */
	private Alumno loginAlumno(String dni, String password) {

		String passwordAlumno;
		Alumno alumno = AlumnosDao.getAlumno(dni);
		if (alumno != null) {
			String[] apellidos = alumno.getApellidos().toLowerCase().split(" ");
			passwordAlumno = apellidos[0].substring(0, 3) + apellidos[1].substring(0, 3);

			if (passwordAlumno.compareTo(password) == 0) {
				return alumno;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Método loginImpartidor, destinado al login de profesores. Los impartidores se
	 * loguean tomando como usuario la clave primaria de la tabla impartidores, y
	 * como contraseña “damocles". Devuelve el impartidor correspondiente a dicho
	 * id.
	 * 
	 * @param usuario
	 * @param password
	 * @return
	 * @throws NamingException
	 */
	private Impartidor loginImpartidor(String usuario, String password) {

		String passwordImpartidor = "damocles";
		try {
			Impartidor impartidor = ImpartidoresDao.getImpartidor(Integer.parseInt(usuario));
			if (impartidor != null) {

				if (passwordImpartidor.compareTo(password) == 0) {
					return impartidor;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
