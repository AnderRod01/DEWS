package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cuenta;

/**
 * Servlet implementation class ServletMovimientos
 */
@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
        } catch (NumberFormatException excepcion) {
            return false;
        }

        return true;
    }
	

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);

		if (request.getParameter("procesar") != null) {

			String titular = (String) session.getAttribute("titular");
			int saldo = Integer.parseInt((String) session.getAttribute("saldo"));
			String cantidad = request.getParameter("cantidad");
			
			Cuenta cuenta = new Cuenta(titular, saldo);

			if (isNumeric(cantidad) == true) {
				
				if (request.getParameter("procesar").equals("ingresar")) {
					cuenta.ingresar(Integer.parseInt(cantidad));
					double saldoActual = cuenta.getSaldo();
					session.setAttribute("saldo", saldoActual);
					request.getRequestDispatcher("movimientos.jsp").forward(request, response);
				}

				if (request.getParameter("procesar").equals("gastar")) {
					boolean gastado = cuenta.gastar(Integer.parseInt(cantidad));

					if (gastado) {
						double saldoActual = cuenta.getSaldo();
						session.setAttribute("saldo", saldoActual);
						request.getRequestDispatcher("movimientos.jsp").forward(request, response);
					} else {
						String mensajeError = "La cuenta s�lo dispone de " + cuenta.getSaldo() + "�";
						request.setAttribute("mensajeError", mensajeError);
						request.getRequestDispatcher("movimientos.jsp").forward(request, response);
					}
				}
			} else {
				String mensajeError = "La cantidad que se ha introducido no es v�lida";
				request.setAttribute("mensajeError", mensajeError);
				request.getRequestDispatcher("movimientos.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
