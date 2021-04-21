package pl.polsl.lab.model;


/**
 * Class handling exception
 * @author Ewelina
 * verion 1.0
 */
public class OwnException extends Exception{
    /**
     * Default constructor
     */
    public OwnException() {
    }
    /**
     * Function displaying exception message
     * @param message
     */
    public OwnException(String message) {
        super(message);
    }

}