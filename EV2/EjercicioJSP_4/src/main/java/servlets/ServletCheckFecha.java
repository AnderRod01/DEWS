
package servlets;


import beans.Fecha;
import beans.Primitiva;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletCheckFecha extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        final HttpSession session = request.getSession(true);
       
        if(request.getParameter("nueva-primitiva") == null){
            request.setAttribute("error", "Fecha incorrecta");
            dispatcher.forward(request, response);
            return;
        }
        
        final String dayParam   = request.getParameter("day");
        final String monthParam = request.getParameter("month");
        final String yearParam  = request.getParameter("year");
        
        if("".equals(dayParam) || "".equals(monthParam) || "".equals(yearParam)){
            request.setAttribute("error", "Rellene todos los campos antes de continuar.");
            dispatcher.forward(request, response);
            return;
        }   
        
        try {
            final Integer day   = Integer.parseInt(dayParam);
            final Integer month = Integer.parseInt(monthParam);
            final Integer year  = Integer.parseInt(yearParam);    
            
            final Fecha date = new Fecha(day, month, year);
            
            if(!date.correct()) {
                request.setAttribute("error", "Fecha incorrecta");
                dispatcher.forward(request, response);
                return;
            }
            
            Primitiva primitiva = new Primitiva(date);
            session.setAttribute("primitiva", primitiva);
            dispatcher.forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Fecha incorrecta");
            dispatcher.forward(request, response);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }


}
