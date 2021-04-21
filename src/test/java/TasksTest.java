/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import org.junit.jupiter.api.Test;
import pl.polsl.lab.model.Task;
import pl.polsl.lab.model.Tasks;

import static org.junit.jupiter.api.Assertions.*;





/**
 * Class testing method from Tasks class
 * @author Ewelina
 * @version 3.0
 */
public class TasksTest {
    private Tasks tasks=new Tasks();
    public TasksTest() {
    }
    @Test
    public void testAddNullTask()
    {
        tasks=new Tasks();
        tasks.add(null);
        assertNull(tasks.getTask(0));
    }
    @Test
    public void testAddTask()
    {
        tasks=new Tasks();
        tasks.add(new Task("desc","zle","zle","rano","wieczor","err"));
        assertEquals(tasks.getTask(0).getStartDate(),"_");// wrong start date won't be write in tasks object
        assertEquals(tasks.getTask(0).getEndDate(),"_");//wrong end date won't be write too
    }
    @Test
    public void testDeleteTask()
    {
        tasks.add(new Task("des","2020-11-18","2020-11-18","8 rano","10","wer"));
        Task temp=tasks.getTask(0);
        tasks.deleteTask(temp);
        assertNotNull(tasks.getTasks());//after correct delete there no task, but list doesn't be null

    }
    @Test
    public void testDeleteNullTask()
    {
        tasks.add(new Task("ooo","2020-11-18","2020-11-19","8 rano","10","wee"));
        //tasks.deleteTask(Task(null));
        assertNotNull(tasks.getTasks());// in list still is one task
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}
