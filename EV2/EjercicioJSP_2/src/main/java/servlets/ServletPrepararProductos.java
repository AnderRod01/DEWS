package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletPrepararProductos
 */
@WebServlet("/ServletPrepararProductos")
public class ServletPrepararProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		final String categoria = request.getParameter("categ");
		ArrayList<String> productos = productosPorCategoria(categoria);
		session.setAttribute("productos", productos);
		session.setAttribute("categoria", categoria);
		
		final RequestDispatcher dispacher = request.getRequestDispatcher("/compra.jsp");
        dispacher.forward(request, response);
	}

	@SuppressWarnings("finally")
	public ArrayList<String> productosPorCategoria (String cat){
		ArrayList<String> productos = new ArrayList<String>();
		String realPath = this.getServletContext().getRealPath("files/productos.txt");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(realPath))) {
			
			String linea = reader.readLine();
			while (linea != null){
				String params[] = linea.split(";");
				if (!params[0].equals("-") && cat != null) {
					if (cat.equals(params[0])) {
						productos.add(params[1]);
					}
				}else 
					productos.add(params[1]);
				
			}
			
		} catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return productos;
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
