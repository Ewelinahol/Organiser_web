/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.lab.model.Task;


/**
 * Class testing method from Task class
 * @author Ewelina
 * @version 3.0
 */
public class TaskTest {
    private Task task= new Task();



    //@Test
    // public void testValidate()// testing exception in function validate
    //  {
    //      try{
    //          task.vvalidate("tt");

    //        fail("Exception should be thrown in this place");
    //     }catch(OwnException e)
    //     {
    //     }
    //     try{
    //         task.vvalidate("2020-13-12");
    //       fail("Exception should be thrown in this place");
    //    }catch(OwnException e)
    //    {
    //     }
    //    try{
    //         task.vvalidate("2020-10-42");
    //       fail("Exception should be thrown in this place");
    //    }catch(OwnException e)
    //    {
    //     }
    // }

    @ParameterizedTest
    @ValueSource(strings={"2020-10","2020-13-10","2020-12-45","rrrr"})
    public void testSetStartDate(String sample)// testing wrong start date value
    {
        task.setStartDate(sample);
        assertEquals(task.getStartDate(),"");// compare value with default""
    }
    @ParameterizedTest
    @ValueSource(strings={"2020-10","2020-13-10","2020-12-45","rrrr"})
    public void testSetEndDate(String sample)// testing wrong end date value
    {
        task.setEndDate(sample);
        assertTrue(task.getEndDate().isEmpty());

    }
    @Test
    public void testSetNullEndDate()
    {
        try{
            task.setEndDate(null);
        }catch(NullPointerException e)
        {
        }
    }
    @Test
    public void testSetNullStartDate()
    {
        try{
            task.setStartDate(null);
        }catch(NullPointerException e)
        {
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
