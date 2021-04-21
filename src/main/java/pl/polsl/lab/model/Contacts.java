package pl.polsl.lab.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.polsl.provider.DatabaseProvider;

import static java.lang.Integer.parseInt;

/**
 * Class storer list of contact
 * @author Ewelina
 * @version 2.0
 */
public class Contacts {
    private ObservableList<Contact>contacts;
    /**
     * Default contructor
     */
    public Contacts() {
        this.contacts = FXCollections.observableArrayList();
    }
    /**
     * Function returning contact list
     * @return list of contact
     */
    public ObservableList<Contact> getContacts() throws SQLException, ClassNotFoundException {
        ObservableList<Contact> contactss = FXCollections.observableArrayList();
       try {

           ResultSet set = DatabaseProvider.query("SELECT * FROM CONTACTS");
           while (set.next()) {
                Contact contact= new Contact(set.getString("telephone"), set.getString("address"), set.getString("email"), set.getString("person_name"));
                contact.setId(set.getInt("IDC"));
                //  contactss.add(new Contact(set.getString("telephone"), set.getString("address"), set.getString("email")));
                    contactss.add(contact);
           }
          set.close();
       }
       catch(ClassNotFoundException|SQLException ex)
       {
        ex.getMessage();
       }
       contacts=contactss;

        return contacts;
    }

    /**
     * Method deleting contact and task at specifying index
     * @return index deleted contact
     */
    public int cascadeDelete()
    {
        int deleted=0;
        ObservableList<Contact> contactss = FXCollections.observableArrayList();
        try {

            ResultSet set = DatabaseProvider.query("SELECT * FROM CONTACTS AS C JOIN TASKS AS T ON C.PERSON_NAME=T.PERSON");
            while (set.next()) {
                deleted=set.getInt("IDC");
            }

        }

        catch(ClassNotFoundException|SQLException ex)
        {
            ex.getMessage();
        }
        //updating foreign key at task table
        try {Connection connection= DatabaseProvider.getConnections();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE TASKS SET IDC=(SELECT IDC FROM CONTACTS C WHERE TASKS.PERSON=C.PERSON_NAME)");


            int affectedRows = pstmt.executeUpdate();
            connection.commit();
            this.deleteContact(deleted);
        }

        catch(ClassNotFoundException|SQLException ex)
        {
            ex.getMessage();
        }
        contacts=contactss;
        return deleted;
    }
    /**
     * Function adding new contact to list
     * @param contact object added to list
     */
    public void add(Contact contact)
    {

        long id = 0;
        try(Connection connection= DatabaseProvider.getConnections();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CONTACTS(TELEPHONE, ADDRESS, EMAIL,PERSON_NAME) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1,contact.getTelephone());
            pstmt.setString(2,contact.getAddress());
            pstmt.setString(3,contact.getEmail());
            pstmt.setString(4,contact.getPerson());

            int affectedRows = pstmt.executeUpdate();
            connection.commit();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                        contact.setId(id);
                        pstmt.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        this.contacts.add(contact);
    }
    /**
     * Function removing specifying element of list
     * @param contact object removed from list
     */
    public void deleteContact(Contact contact)
    {

        this.contacts.remove(contact);
    }
    /**
     * Function returning single contact from specifying place of list
     * @param index index of single contact
     * @return contact from list
     */
    public Contact getContact(int index)
    {
        return contacts.get(index);
    }
    /**
     * Function returning number of element in list
     * @return size of contacts list
     */
    public int size()
    {
        return contacts.size();
    }

    /**
     * Method delete from contact table and task table
     * @param deleted index deleted contact
     */
    public void deleteContact(int deleted)
    {

        try(Connection connection= DatabaseProvider.getConnections();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM CONTACTS WHERE IDC=?");) {

            pstmt.setInt(1,deleted);

            int affectedRows = pstmt.executeUpdate();
            connection.commit();
            // check the affected rows
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Function for searching contact by phone number
     * @param number integer representing phone number by which function search contact
     * @return new list which contains filtrated contact
     */
    public List<Contact> ssearch(String number)
    {
        List<Contact> temp=new ArrayList<>();
        contacts.stream()//stream
                .filter(g->g.getTelephone().equals(number))// filtration condition
                .forEach(temp::add);//adding contact to list

        return temp;

    }
}
