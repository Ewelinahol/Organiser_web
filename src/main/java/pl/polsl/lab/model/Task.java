package pl.polsl.lab.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class storer details of single task
 * @author Ewelina
 * @version 3.0
 */
public class Task {
    private SimpleStringProperty person;
    private long id;
    private SimpleStringProperty startTime;
    private SimpleStringProperty endTime;
    private SimpleStringProperty startDate;
    private SimpleStringProperty endDate;
    private SimpleStringProperty description;
    private static String regex="^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";// string representing correct regex for date format
    //e.g 2020-12-30
    private static Pattern pattern=Pattern.compile(regex);
    private Matcher matcher;
    /**
     * Constructor with parameters
     * @param startTime SimpleStringProperty representing start time of task
     * @param endTime  SimpleStringProperty representing end time of task
     * @param startDate SimpleStringProperty representing start date of task
     * @param endDate SimpleStringProperty representing end date of task
     * @param description SimpleStringProperty representing descritpion of task
     */

    public Task(String description, String startDate, String endDate, String startTime, String endTime, String person) {
        this.person=new SimpleStringProperty(person);
        this.description= new SimpleStringProperty(description);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime =new SimpleStringProperty(endTime);
        this.startDate=new SimpleStringProperty("_");
        this.endDate =new SimpleStringProperty ("_");
        boolean startDatee=false;
        boolean endDatee=false;
        try{
            this.vvalidate(startDate);// chceck if date SimpleStringProperty is in correct format
        }catch(OwnException e)// if not catch exception
        {
            e.getMessage();
            this.startDate = new SimpleStringProperty("_");
            startDatee=true;// this uncorrect value doesn't be inserted into single task
        }
        if(!startDatee)
            this.startDate=new SimpleStringProperty(startDate);
        try{
            this.vvalidate(endDate);// chceck if date SimpleStringProperty is in correct format
        }catch(OwnException e)// if not catch exception
        {
            e.getMessage();
            this.endDate = new SimpleStringProperty("_");
            endDatee=true;// this uncorrect value doesn't be inserted into single task
        }
        if(!endDatee)
        this.endDate =new SimpleStringProperty (endDate);

    }
    /**
     * Default contructor
     */
    public Task() {
        this.id=0;
        this.person =new SimpleStringProperty("_");
        this.description =new SimpleStringProperty("_");
        this.startTime = new SimpleStringProperty("_");
        this.endTime = new SimpleStringProperty("_");
        this.startDate = new SimpleStringProperty("_");
        this.endDate = new SimpleStringProperty("_");

    }

    /**
     * Method setting person name
     * @param person String representing person name
     */
    public void setPerson(String person)
    {
        this.person.set(person);
    }

    /**
     * Method return person name
     * @return person name
     */
    public String getPerson(){
        return this.person.get();
}
    /**
     * Method sets task id
     * @param id long representing id
     */
    public void setId(long id)
    {
        this.id=id;
    }

    /**
     * Method returns id
     * @return
     */
    public long getId()
    {
        return this.id;
    }
    /**
     * Function setting start time of task
     * @param startTime SimpleStringProperty representing start time
     *
     */
    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }
    /**
     * Function setting end time of task
     * @param endTime SimpleStringProperty representing end time
     */
    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }
    /**
     * Function setting start date of task
     * @param startDate SimpleStringProperty representing start date
     */
    public void setStartDate(String startDate) {
        try{
            this.vvalidate(startDate);// chceck if date SimpleStringProperty is in correct format
        }catch(OwnException e)// if not catch exception
        {
            e.getMessage();
            this.startDate = new SimpleStringProperty("_");
            return;// this uncorrect value doesn't be inserted into single task
        }
        this.startDate.set(startDate);
    }
    /**
     * Function setting end date of task
     * @param endDate SimpleStringProperty representing end date
     */
    public void setEndDate(String endDate) {
        try{
            this.vvalidate(endDate);
        }catch(OwnException e)
        {
            e.getMessage();
            this.endDate = new SimpleStringProperty("_");
            return;
        }
        this.endDate.set(endDate);
    }
    /**
     * Function setting description of task
     * @param description SimpleStringProperty representing task description
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
    /**
     * Function getting start time of task
     * @return startTime SimpleStringProperty representing start time
     */
    public String getStartTime() {
        return startTime.get();
    }
    /**
     * Function getting end time of task
     * @return endTime SimpleStringProperty representing end time
     */
    public String getEndTime() {
        return endTime.get();
    }
    /**
     *  Function getting start date of task
     * @return startDate SimpleStringProperty representing start time
     */
    public String getStartDate() {
        return startDate.get();
    }
    /**
     * Function getting end date of task
     * @return endDate SimpleStringProperty representing end time
     */
    public String getEndDate() {
        return endDate.get();
    }
    /**
     * Function getting description of task
     * @return description
     */
    public String getDescription() {
        return description.get();
    }
    /**
     * Function check correctness of date inserted into task
     * @param check SimpleStringProperty that will be check
     * @throws OwnException exception displaying some message
     */
    public void vvalidate(String check) throws OwnException
    {
        matcher=pattern.matcher(check);
        if(!matcher.matches())// if SimpleStringProperty doesn't match to regex pattern exception will be served
            throw new OwnException();
    }
}

