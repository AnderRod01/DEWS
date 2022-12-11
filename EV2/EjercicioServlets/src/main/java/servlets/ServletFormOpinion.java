package servlets;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Stream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletFormOpinion
 */
@WebServlet(urlPatterns = {"/servletFormOpinion"})
public class ServletFormOpinion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final ServletContext context = getServletContext();

        response.setContentType("text/html;charset=UTF-8");        
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("enviar") != null){
                
                String name = request.getParameter("txtNombre");
                String surname = request.getParameter("txtApellido");
                String rating = request.getParameter("opinion");
                String comentarios = request.getParameter("comentarios");
                String[] sections = request.getParameterValues("chkSecciones"); 
                String error = null;
                
                if(name == null || name.trim().length() == 0)
                    error = "Introduzca un nombre";
                else  if(rating == null) 
                    error = "Selecciona una valoración";
                else if(rating.equals("B") && sections != null){
                    String line = name + " ";
                    if(surname != null && surname.length() > 0)
                        line += surname + " ";
                    
                    line += ": " + String.join(",", sections);
                    writeLine(context, line);
                }
                drawMainPage(request, response, out, error);
            } else drawMainPage(request, response, out, null);
        }
    }
	
	private void drawMainPage(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String error){
        
        final ServletContext context = getServletContext();
        final String baseUrl = baseUrl(request);
        
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("</head>");
        out.print("<body>");
        
        if(error != null)
           out.print("<p style='color:red;'>" + error + "</p>");
            
        out.print("<form action='" + baseUrl + "servletFormOpinion' method='post'>");
        out.print("<h2>Encuesta de opinión</h2>");
        out.print("<label><b>Nombre:</b> <input type='text' name='txtNombre'></label><br><br>");
        out.print("<label><b>Apellidos:</b> <input type='text' name='txtApellido'></label>");
        out.print("<h2>Opinion que le ha merecido este sitio web</h2>");
        out.print("<label><input type='radio' name='opinion' value='B'> Buena</label><br>");
        out.print("<label><input type='radio' name='opinion' value='R'> Regular</label><br>");
        out.print("<label><input type='radio' name='opinion' value='M'> Mala</label>");
        out.print("<h3>Comentarios</h3>");
        out.print("<textarea name='comentarios'></textarea>");
        out.print("<h3>Tus secciones favoritas</h3>");
        
        final Optional<Stream<String>> lines = getSections(context);
        if(lines.isPresent()){
            lines.get().forEach(line -> {
                out.print("<br><label><input type='checkbox' name='chkSecciones' value='" + line + "'>" + line + "</label>");
            });

            lines.get().close();
        } else {
            out.print("<p id='error'>No se encontraron secciones</p>");
        } 
        
        out.print("<br><input type='submit' name='enviar' value='Enviar opinion'>");
        out.print("</form>"); 
        out.print("</body>");
        out.print("</html>");
    }
    
    private static String baseUrl(HttpServletRequest req){
        StringBuffer url = req.getRequestURL();
        String uri = req.getRequestURI();
        String context = req.getContextPath();
        return url.substring(0, url.length() - uri.length() + context.length()) + "/";
    }
    
    
    private static void writeLine(final ServletContext context, final String content) {

        String realPath = context.getRealPath("/files/seccionesfavoritas.txt");
        
        
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(realPath,true));
            writer.write(content);
            writer.newLine();
            writer.close();
        }catch(IOException | NullPointerException e){
            System.err.println(e);
        }
    }
    
    
    private static Optional<Stream<String>> getSections(final ServletContext context) {
        final String realPath = context.getRealPath("/files/secciones.txt");
        Optional<Stream<String>> lines = Optional.empty();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(realPath));
            lines = Optional.ofNullable(reader.lines());
        } catch (IOException | NullPointerException e) {
            System.err.println(e);
        }

        return lines;
    }
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
