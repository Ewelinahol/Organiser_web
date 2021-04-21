package pl.polsl.servlets;

import pl.polsl.lab.model.Task;
import pl.polsl.lab.model.Tasks;
import pl.polsl.provider.DatabaseProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "AddTasksServlet", urlPatterns = "/addTasks")
/**
 * Servlet class handling adding new task to table
 */
public class AddTasksServlet extends HttpServlet {
    private Tasks tasks;
    @Override
    public void init()
    {
        tasks= new Tasks();
        try {
            DatabaseProvider.query("CREATE TABLE TASKS(ID INTEGER," +
                    " DESCRIPTION LONG VARCHAR," +
                    "START_DATE LONG VARCHAR, END_DATE LONG VARCHAR," +
                    " START_TIME LONG VARCHAR, END_TIME LONG VARCHAR, " +
                    "PERSON VARCHAR(20), IDC INTEGER," +
                    "FOREIGN KEY(IDC) REFERENCES CONTACTS(IDC))");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Function adding new task to tasks list. It also printing out table
     * @param request contains all client request information
     * @param response adapting wishing from the Servlet
     * @throws ServletException when are some problem with Servelt
     * @throws IOException when are some problem with IO
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");// download data from js file
        String description=request.getParameter("description");
        String startDate= request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        String person=request.getParameter("person");
        tasks.add(new Task(description,startDate,endDate,startTime,endTime,person));


        try{
            PrintWriter out=response.getWriter();

            for(Task task:tasks.getTasks())// printing every cell of task table
            {
                out.println("<tr>");

                out.println("<td>"+task.getId()+"</td>");

                out.println("<td>"+task.getDescription()+"</td>");

                if(task.getStartDate().equals("_")) {
                    response.setStatus(200);
                    out.println("<td>"+"Wrong format of date! Value will be default"+"</td>");
                }
                else
                out.println("<td>"+task.getStartDate()+"</td>");
                if(task.getEndDate().equals("_")) {
                    response.setStatus(200);
                    out.println("<td>"+"Wrong format of date! Value will be default"+"</td>");
                }
                else
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
        request.getSession().setAttribute("myModel", tasks);//session
        processRequest(request,response);//calling function
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
        request.getSession().setAttribute("myModel", tasks);//session
        processRequest(request,response);//calling function
    }
}
