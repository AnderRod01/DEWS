package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.Autor;
import beans.Libro;
import dao.GestorBD;

/**
 * Servlet implementation class ServletAutores
 */
@WebServlet(name = "ServetAutores", urlPatterns = { "/ServletAutores"})
public class ServletAutores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GestorBD bd;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        if(request.getParameter("aniadir") == null){
            doGet(request, response);
        }else {
        	if(request.getParameter("nombre").equals("") || 
                    request.getParameter("fechanac").equals("") || 
                    request.getParameter("nacionalidad").equals("")){
                request.setAttribute("errorinsercion", "Hay que rellenar todos los datos");
        	}else {
        		try{
                    //Recuperamos los datos del formulario y creamos un objeto de tipo libro.
                    //Este objeto no tendrá ID hasta que se inserte en la base de datos
                    String nombre = request.getParameter("nombre");
                    
       
                    Date fechanac= new Date((request.getParameter("fechanac")));
                    
                    String nacionalidad = request.getParameter("nacionalidad"); 

                    
                    Autor autor = new Autor(1, nombre, fechanac, nacionalidad);
                    
                    //Si el libro ya existe no se inserta en la base de datos
                    boolean existe = bd.existeAutor(autor.getNombre());
                    if(existe){
                        request.setAttribute("errorinsercion", "El autor " 
                                + autor.getNombre() + " ya existe");
                    }else{
                        int id = bd.insertarAutor(autor);
                        
                        if(id == -1){
                            request.setAttribute("errorinsercion", "No se ha podido insertar el autor");
                            request.setAttribute("autorerroneo", autor);
                        }else{
                            //Si no ha habido ningún problema se añade el ID al 
                            //al objeto libro y se añadae el nuevo libro a la 
                            //seión
                            autor.setIdAutor(id);
                            ((ArrayList<Autor>)request.getSession().getAttribute("autores")).add(autor);
                        }
                    }
                }catch(NumberFormatException e){
                    request.setAttribute("errorinsercion", "Numero de páginas erroneo");
                }
        		doGet(request, response);
        	}
        }
    	
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        request.getSession().setAttribute("listaAutores", bd.getAutores());
        
        if (request.getParameter("autor") != null) {
        	int autor = Integer.parseInt(request.getParameter("autor"));
        	request.getSession().setAttribute("librosAutor", bd.librosAutor(autor));
        }
        if (request.getParameter("prestar") != null) {
        	int idLibro = Integer.parseInt(request.getParameter("prestar"));
        	bd.prestarLibro(idLibro);
        	request.getSession().setAttribute("libroPrestado", bd.getTituloLibro(idLibro));
        }
        
        request.getRequestDispatcher("autores.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
