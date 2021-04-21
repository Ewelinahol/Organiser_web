package pl.polsl.lab.model;


import javafx.beans.property.SimpleStringProperty;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class storer details about stored contact
 * @author Ewelina
 * @version 3.0
 */
public class Contact {
    private long id;
    private SimpleStringProperty person;
    private SimpleStringProperty telephone;
    private SimpleStringProperty address;
    private SimpleStringProperty email;
    private Pattern patternTelephone = Pattern.compile("^\\+?\\d{1,10}$");
    private static Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private Matcher matcher;


    /**
     * Default constructor
     */
    public Contact() {
        this.id=0;
        this.person = new SimpleStringProperty("_");
        this.telephone = new SimpleStringProperty("_");
        this.address = new SimpleStringProperty("_");
        this.email = new SimpleStringProperty("_");
    }

    /**
     * Constructor with parameters
     *
     * @param telephone String storer telephone number
     * @param address   String storer address
     * @param email     String storer email
     * @pram person     String storer person name
     */
    public Contact(String telephone, String address, String email, String person) {
        this.person=new SimpleStringProperty(person);
        this.telephone= new SimpleStringProperty("_");
        this.address = new SimpleStringProperty(address);
        this.email= new SimpleStringProperty("_");
        boolean newTelephone=false;
        boolean newEmail=false;
        try {
            this.validate(telephone, this.patternTelephone);// chceck if date SimpleStringProperty is in correct format
        } catch (OwnException e)// if not catch exception
        {
            e.getMessage();
             this.telephone= new SimpleStringProperty("_");
            newTelephone=true;// this uncorrect value doesn't be inserted into single contact
        }
        if(!newTelephone)
        this.telephone= new SimpleStringProperty(telephone);
        try {
            this.validate(email, this.pattern);// chceck if date SimpleStringProperty is in correct format
        } catch (OwnException e)// if not catch exception
        {
            e.getMessage();
             this.email= new SimpleStringProperty("_");
            newEmail=true;// this uncorrect value doesn't be inserted into single contact
        }
        if(!newEmail)
        this.email = new SimpleStringProperty(email);

    }

    /**
     * Method sets id
     * @param id long
     */
    public void setId(long id)
    {
    this.id= id;
    }

    /**
     * Method return id
     * @return id
     */
    public long getId()
    {
        return this.id;
    }
    /**
     * Method sets person name
     * @param person String representing name
     */
    public void setPerson(String person)
    {
        this.person.set(person);
    }

    /**
     *
     * @return
     */
    public String getPerson()
    {
        return this.person.get();
    }

    /**
     * Function setting new value of telephone number
     *
     * @param telephone new telephone number
     */
    public void setTelephone(String telephone) {
        try {
            this.validate(telephone, this.patternTelephone);// chceck if date SimpleStringProperty is in correct format
        } catch (OwnException e)// if not catch exception
        {
            e.getMessage();
            // this.telephone.set("_");
            return;// this uncorrect value doesn't be inserted into single contact
        }
        this.telephone.set(telephone);

    }

    /**
     * Function setting new adress
     *
     * @param address new address
     */
    public void setAddress(String address) {
        this.address.set(address);
    }

    /**
     * Function setting new e-mail address
     *
     * @param email new e-mail
     */
    public void setEmail(String email) {
        try {
            this.validate(email, this.pattern);// chceck if date SimpleStringProperty is in correct format
        } catch (OwnException e)// if not catch exception
        {
            e.getMessage();
            // this.email.set("_");
            return;// this uncorrect value doesn't be inserted into single contact
        }
        this.email.set(email);
    }

    /**
     * Function returning telephone number
     *
     * @return telephone number
     */
    public String getTelephone() {
        return telephone.get();
    }

    /**
     * Function returning address
     *
     * @return SimpleStringProperty represent address
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * Function returning e-mail
     *
     * @return SimpleStringProperty represent e-mail
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Function check correctness of data inserted into contact
     *
     * @param check    String that will be check
     * @param patternn Pattern which indicates match
     * @throws OwnException exception displaying some message
     */
    public void validate(String check, Pattern patternn) throws OwnException {
        matcher = patternn.matcher(check);
        if (!matcher.matches())// if SimpleStringProperty doesn't match to regex pattern exception will be served
            throw new OwnException();
    }
}

