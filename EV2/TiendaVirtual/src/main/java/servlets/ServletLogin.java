package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;
import dao.ClientesDAO;
import dao.PedidoDAO;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }	
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session!= null) {
			session.invalidate();
			session = null;
		}
		
		session = request.getSession(true);
		
		
		boolean userValido = false;
		
		String user = (String) request.getParameter("txtUser");
		
		String pwd = (String) request.getParameter("txtPwd");
		
		ClientesDAO clientesDAO = new ClientesDAO();
		
		Cliente cliente = clientesDAO.buscaCliente(user, pwd);
		
		if (cliente != null) {
			userValido = true;
			request.getSession().setAttribute("user", cliente);
			request.getSession().setAttribute("productos", PedidoDAO.todosItems());
		
		}else {
			request.getSession().setAttribute("mensaje", "Usuario no valido");
		}
		
		
		if (userValido) {
			request.getRequestDispatcher("tienda.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
