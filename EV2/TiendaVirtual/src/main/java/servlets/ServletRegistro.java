package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cliente;
import dao.ClientesDAO;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet(name = "ServletRegistro", urlPatterns = {"/ServletRegistro"})
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 @Override
	    public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	    }	
	
	 protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.getSession().setAttribute("mensaje", "");
		 request.getSession().setAttribute("mensajeSuccess", "");
		 
		 String user = request.getParameter("txtUser");
		 String pwd = request.getParameter("txtPwd");
		 String domicilio = request.getParameter("txtDomicilio");
		 String cp = request.getParameter("txtCP");
		 String tlf = request.getParameter("txtTlf");
		 String email = request.getParameter("txtEmail");
		 
		 
		 if (!ClientesDAO.buscaCliente(user)) {
			 
			 
			 if (ClientesDAO.guardarCliente(new Cliente(0, user, pwd, domicilio, cp, tlf, email))) {
				 request.getSession().setAttribute("mensajeSuccess", "Usuario registrado correctamente");
				 request.getRequestDispatcher("login.jsp").forward(request, response);
			 }else {
				 request.getSession().setAttribute("mensaje", "Datos no válidos");
				 request.getRequestDispatcher("registro.jsp").forward(request, response);
			 }
			
		 }else {
			 request.getSession().setAttribute("mensaje", "Usuario existente");
			 request.getRequestDispatcher("registro.jsp").forward(request, response);
			 
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
