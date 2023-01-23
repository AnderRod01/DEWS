package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletNuevaCuenta
 */
@WebServlet("/ServletNuevaCuenta")
public class ServletNuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titular = request.getParameter("titular");
		String saldo = request.getParameter("saldo");
		
		ArrayList<String> baneados = new ArrayList<String>();
		baneados.add("miguel");
		baneados.add("pepa");
		baneados.add("pablo");
		
		HttpSession session = request.getSession(true);
		String mensaje = "";
		
		if (titular.equals("") || saldo.equals("")) {
			mensaje = "Debes rellenar ambos campos";
			request.setAttribute("mensajeError", mensaje);
			request.getRequestDispatcher("nuevacuenta.jsp").forward(request, response);
		}else {
			if (baneados.contains(titular.toLowerCase())) {
				mensaje = "Usuario baneado";
				request.setAttribute("mensajeError", mensaje);
				request.getRequestDispatcher("nuevacuenta.jsp").forward(request, response);
			}else {
				if (Integer.parseInt(saldo) < 0) {
					mensaje = "El saldo debe ser mayor que 0";
					request.setAttribute("mensajeError", mensaje);
					request.getRequestDispatcher("nuevacuenta.jsp").forward(request, response);
				} else {
					session.setAttribute("titular", titular);
					session.setAttribute("saldo", saldo);
					request.getRequestDispatcher("movimientos.jsp").forward(request, response);
				}
			}
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
