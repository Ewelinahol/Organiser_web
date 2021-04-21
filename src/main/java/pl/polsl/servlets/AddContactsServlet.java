package pl.polsl.servlets;

import org.apache.derby.database.Database;
import pl.polsl.lab.model.Contact;
import pl.polsl.lab.model.Contacts;
import pl.polsl.provider.DatabaseProvider;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "AddContactsServlet", urlPatterns = "/addContacts")
/**
 * Servlet class handling adding new contact to table
 */
public class AddContactsServlet extends HttpServlet {
    private Contacts contacts;
    @Override
    public void init()
    {
        contacts= new Contacts();
        try {
            DatabaseProvider.query2("CREATE TABLE CONTACTS(IDC INTEGER, " +
                    "TELEPHONE LONG VARCHAR," +
                    "ADDRESS LONG VARCHAR, EMAIL LONG VARCHAR, " +
                    "PERSON_NAME LONG VARCHAR, PRIMARY KEY(IDC))");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Function adding new contact to contacts list. It also printing out table
     * @param request contains all client request information
     * @param response adapting wishing from the Servlet
     * @throws ServletException when are some problem with Servelt
     * @throws IOException when are some problem with IO
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{


        response.setContentType("text/html;charset=UTF-8");
        String telephone=request.getParameter("telephone");
        String address= request.getParameter("address");
        String email=request.getParameter("email");
        String person=request.getParameter("person");

        contacts.add(new Contact(telephone,address,email,person));// adding new contact to contacts list



        try{
            PrintWriter out=response.getWriter();

            for(Contact contact:contacts.getContacts())//printing contacts list
            {

                out.println("<tr>");

                out.println("<td>"+contact.getId()+"</td>");

                out.println("<td>"+contact.getTelephone()+"</td>");

                out.println("<td>"+contact.getAddress()+"</td>");
                // information about data format
                if(contact.getEmail().equals("_")) {
                    response.setStatus(200);
                    out.println("<td>"+"Wrong format of email! Value will be default"+"</td>");
                }
                else
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
        request.getSession().setAttribute("myModel", contacts);// session
        processRequest(request,response);// calling method
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
        request.getSession().setAttribute("myModel", contacts);//session
        processRequest(request,response);//calling method
    }
}
