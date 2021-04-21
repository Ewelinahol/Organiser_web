package pl.polsl.servlets;

import pl.polsl.lab.model.Contact;
import pl.polsl.lab.model.Contacts;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ContactServlet", urlPatterns = "/contacts")
/**
 * Servlet class showing contacts table
 */
public class ContactServlet extends HttpServlet {
    private Contacts contacts;
    @Override
    public void init()
    {
        contacts= new Contacts();


    }
    /**
     * Function showing contacts table
     * @param request contains all client request information
     * @param response adapting wishing from the Servlet
     * @throws ServletException when are some problem with Servelt
     * @throws IOException when are some problem with IO
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String telephone=request.getParameter("telephone");


        try{
            PrintWriter out=response.getWriter();
            for(Contact contact:contacts.getContacts())// printing every cell of contact table
            {
                out.println("<tr>");

                out.println("<td>"+contact.getId()+"</td>");

                out.println("<td>"+contact.getTelephone()+"</td>");

                out.println("<td>"+contact.getAddress()+"</td>");

                out.println("<td>"+contact.getEmail()+"</td>");

                out.println("<td>"+contact.getPerson()+"</td>");

                out.println("</tr>");


            }
        }catch(Exception ex)
        {
            response.sendError(response.SC_BAD_REQUEST,"We have some problem");

        }

    }
    /**
     * Function called when POST method is used
     * @param request contains all client request information
     * @param response adapting wishing from the Servlet
     * @throws ServletException when are some problem with Servelt
     * @throws IOException when are some problem with IO
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("myModel")!=null)//session
            contacts =  (Contacts)request.getSession().getAttribute("myModel");
        processRequest(request,response);
    }
    /**
     * Function called when GET method is used
     * @param request contains all client request information
     * @param response adapting wishing from the Servlet
     * @throws ServletException when are some problem with Servelt
     * @throws IOException when are some problem with IO
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("myModel")!=null)//session
            contacts =  (Contacts)request.getSession().getAttribute("myModel");
        processRequest(request,response);
    }
}
