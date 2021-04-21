package pl.polsl.lab.model;


import java.sql.*;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.polsl.provider.DatabaseProvider;

/**
 * Class storer list of task
 * @author Ewelina
 * @version 2.0
 */
public class Tasks {
    private ObservableList<Task>tasks;
    public Tasks() {
        this.tasks= FXCollections.observableArrayList();
    }
    /**
     * Function adding new elemnt to list of tasks
     * @param task new task added to list
     */
    public void add(Task task)
    {
        long id = 0;
        //insert into list data
        try(Connection connection= DatabaseProvider.getConnections();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TASKS(DESCRIPTION,START_DATE, END_DATE,START_TIME,END_TIME,PERSON) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1,task.getDescription());
            pstmt.setString(2,task.getStartDate());
            pstmt.setString(3,task.getEndDate());
            pstmt.setString(4,task.getStartTime());
            pstmt.setString(5,task.getEndTime());
            pstmt.setString(6,task.getPerson());

            int affectedRows = pstmt.executeUpdate();
            connection.commit();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                        task.setId(id);
                        pstmt.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        this.tasks.add(task);
    }
    /**
     * Function getting sinlge task from tasks list
     * @return task element of list
     */
    public Task getTask(int index)
    {

        return tasks.get(index);
    }
    /**
     * Function getting all list
     * @return tasks list of task
     */
    public List<Task> getTasks() {
        ObservableList<Task> taskss = FXCollections.observableArrayList();
        try {

            ResultSet set = DatabaseProvider.query("SELECT * FROM TASKS");
            while (set.next()) {
                Task task=new Task(set.getString("description"), set.getString("start_date"),
                        set.getString("end_date"), set.getString("start_time"),set.getString("end_time"), set.getString("person"));
                task.setId(set.getInt("ID"));
                taskss.add(task);
               // taskss.add(new Task(set.getString("description"), set.getString("start_date"),
                    //    set.getString("end_date"), set.getString("start_time"),set.getString("end_time")));
            }
        }
        catch(ClassNotFoundException|SQLException ex)
        {
            ex.getMessage();
        }
        tasks=taskss;
        return tasks;
    }
    /**
     * Function removing single task from tasks list
     * @param task object which will be removed from list
     */
    public void deleteTask(Task task)
    {

        tasks.remove(task);
    }
    /**
     * Function returning size of tasks list
     * @return tasks size
     */
    public int size()
    {
        return tasks.size();
    }

    /**
     *
     * @param description
     */
    public void deleteTask(int description){


        try(Connection connection= DatabaseProvider.getConnections();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM TASKS WHERE ID=?");) {

            pstmt.setInt(1,description);

            int affectedRows = pstmt.executeUpdate();
            connection.commit();
            // check the affected rows
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

