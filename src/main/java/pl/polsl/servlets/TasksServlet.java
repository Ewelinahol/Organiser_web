package pl.polsl.servlets;

import pl.polsl.lab.model.Task;
import pl.polsl.lab.model.Tasks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TasksServlet", urlPatterns = "/tasks")
/**
 * Servlet class handling showing task table
 */
public class TasksServlet extends HttpServlet {
    private Tasks tasks;
    @Override
    public void init()
    {
        tasks= new Tasks();


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
        String description=request.getParameter("description");


            try{
                PrintWriter out=response.getWriter();//printing table
                for(Task task:tasks.getTasks())
                {
                    out.println("<tr>");

                    out.println("<td>"+task.getId()+"</td>");

                    out.println("<td>"+task.getDescription()+"</td>");

                    out.println("<td>"+task.getStartDate()+"</td>");

                    out.println("<td>"+task.getEndDate()+"</td>");

                    out.println("<td>"+task.getStartTime()+"</td>");

                    out.println("<td>"+task.getEndTime()+"</td>");

                    out.println("<td>"+task.getPerson()+"</td>");

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
            tasks =  (Tasks)request.getSession().getAttribute("myModel");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { if(request.getSession().getAttribute("myModel")!=null)//session
    tasks =  (Tasks)request.getSession().getAttribute("myModel");
    processRequest(request,response);
        }
    }

