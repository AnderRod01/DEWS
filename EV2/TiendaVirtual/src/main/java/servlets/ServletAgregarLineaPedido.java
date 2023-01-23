package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CarroCompra;
import beans.Item;
import beans.LineaPedido;
import conex.ConnectionPool;
import dao.PedidoDAO;

/**
 * Servlet implementation class ServletAgregarLineaPedido
 */
@WebServlet(name = "ServletAgregarLineaPedido", urlPatterns = {"/ServletAgregarLineaPedido"})
public class ServletAgregarLineaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<Integer, Item> listaItems = PedidoDAO.todosItems();
		request.getSession().setAttribute("listaItems", listaItems);
		
		if(request.getSession().getAttribute("carrocompra") == null) {
			request.getSession().setAttribute("carrocompra", new CarroCompra());
		}
		
		if (request.getParameter("aniadir") != null) {
			Item item = PedidoDAO.buscaItemPorId(Integer.parseInt(request.getParameter("aniadir")));
			LineaPedido  lp = new LineaPedido();
			lp.setItem(item);
			lp.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
			
			CarroCompra cp = (CarroCompra) request.getSession().getAttribute("carrocompra");
			cp.aniadeLinea(lp);
		}
		
		request.getRequestDispatcher("tienda.jsp").forward(request, response);
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
