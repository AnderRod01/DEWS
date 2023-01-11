package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    	 LinkedList<Autor> autores = bd.getAutores();
         RequestDispatcher dispatcher = request.getRequestDispatcher("/autores.jsp");
         request.setAttribute("autores", autores);
    	 
    	 if(request.getParameter("autor") != null){
             try {
                 
                 int id = Integer.parseInt(request.getParameter("autor"));
                 LinkedList<Libro> librosAutor = bd.librosAutor(id);
                 
                 request.setAttribute("autor", bd.nombreAutor(id));
                 request.setAttribute("librosAutor", librosAutor);
             
             } catch(NumberFormatException e){
             
                 request.setAttribute("error", "Autor inválido.");
                 dispatcher.forward(request, response);
                 return;
             
             }
             
         }
    	 
    	 if(request.getParameter("nuevoAutor") != null) {
             
             String nombre = request.getParameter("nombre");
             String fechaNacString = request.getParameter("fechanac");
             String nacionalidad = request.getParameter("nacionalidad");
             
             if(nombre == null || "".equals(nombre)){
                 request.setAttribute("error", "El nombre no puede estar vacío.");       
                 dispatcher.forward(request, response);  
                 return;
             }
             
             if(fechaNacString == null || "".equals(fechaNacString)){
                 request.setAttribute("error", "La fecha de nacimiento no puede estar vacía.");
                 dispatcher.forward(request, response);
                 return;
             }
             
             Date fecha = null;
             
             try {
                 SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
                 fecha = formateador.parse(fechaNacString);
             } catch (ParseException e) {
                 request.setAttribute("error", "La fecha de nacimiento con formato incorrecto (use yyyy/MM/dd).");
                 dispatcher.forward(request, response);
                 return;
             }
             
             if(nacionalidad == null || "".equals(nacionalidad)) {
                 request.setAttribute("error", "La nacionalidad no puede estar vacía.");
                 dispatcher.forward(request, response);
                 return;
             }
             
             Autor autor = new Autor(-1, nombre, fecha, nacionalidad);    
             
             if(bd.existeAutor(nombre)){
                 request.setAttribute("error", "El autor ya existe.");
                 dispatcher.forward(request, response);
                 return;
             }
             
             
             bd.insertarAutor(autor);
             request.setAttribute("message", "Autor creado correctamente.");
         }
    	 if(request.getParameter("prestar") != null) {
             try {
                 
                 final int id = Integer.parseInt(request.getParameter("prestar"));                
                 if(bd.prestarLibro(id)) {
                     request.setAttribute("message", "El libro ha sido prestado.");
                 } else {
                     request.setAttribute("error", "El libro no se pudo prestar.");
                 }
                 
                 
             } catch (NumberFormatException ex) {
                 request.setAttribute("error", "Libro inválido, el préstamo es imposible.");
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
