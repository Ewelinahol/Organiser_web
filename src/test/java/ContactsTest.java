
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.lab.model.Contact;
import pl.polsl.lab.model.OwnException;

import org.junit.jupiter.api.Test;
/**
 * Class testing method from Contact class
 * @author Ewelina
 * @version 2.0
 */
public class ContactsTest {
    private Contact contact= new Contact();
    private Pattern nnosz;
    public ContactsTest() {
    }

    @Test
    public void testSetTelphone()// testing negative number
    {
        contact.setTelephone("-8");
        assertEquals(contact.getTelephone(),0);
    }
    @ParameterizedTest
    @ValueSource(strings={"qqq","qqq@","qq@dddd","WW@ffg."})
    public void testEmail(String sample)// testing wrong email
    {
        contact.setEmail(sample);
        assertEquals(contact.getEmail(),"_");// compare value with default""
    }
    // @Test
    //public void testValidate()// testing exception in function validate
    //{
    //    try{
    //        contact.validate("tt",nnosz);
    //      fail("Exception should be thrown in this place");
    //  }catch(OwnException e)
    //   {
    //   }
    //   try{
    //        contact.validate("tt@",nnosz);
    //       fail("Exception should be thrown in this place");
    //    }catch(OwnException e)
    //    {
    //    }
    //   try{
    //        contact.validate("t@.sd",nnosz);
    //       fail("Exception should be thrown in this place");
    //    }catch(OwnException e)
    //   {
    //     }
    //  }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


}
