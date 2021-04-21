package pl.polsl.provider;

import java.sql.*;

public class DatabaseProvider {
    private static DatabaseProvider instance;
    private static Connection connection ;
    private Statement statement;

    /**
     * Method initializing Driver Manager
     * @throws SQLException
     */
    private void init() throws SQLException
    {
        try {

            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/organiser", "app", "app");

        }catch(SQLException sl)
        {
            sl.getMessage();
        }
        connection.setAutoCommit(false);
        statement=connection.createStatement();

    }

    /**
     * Private constructor
     * @throws ClassNotFoundException
     * @throws SQLException
     */

    private DatabaseProvider() throws ClassNotFoundException, SQLException
    {
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch(ClassNotFoundException cll)
        {
            cll.getMessage();
        }
        init();
    }

    /**
     * Method handling sql query
     * @param sqlQuery
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ResultSet query(String sqlQuery) throws ClassNotFoundException, SQLException
    {
        if(instance==null)
        {
            instance= new DatabaseProvider();
        }
        if(instance.connection.isClosed())
        {
            instance.init();
        }
        return instance.statement.executeQuery(sqlQuery);
    }

    /**
     * Method used when creating table
     * @param sqlQuery
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int query2(String sqlQuery) throws ClassNotFoundException, SQLException
    {
        if(instance==null)
        {
            instance= new DatabaseProvider();
        }
        if(instance.connection.isClosed())
        {
            instance.init();
        }
        return instance.statement.executeUpdate(sqlQuery);
    }

    /**
     * Method for predefining statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnections() throws SQLException, ClassNotFoundException {
        if(instance==null)
        {
            instance= new DatabaseProvider();
        }
        if(instance.connection.isClosed())
        {
            instance.init();
        }
        return connection;
    }

}
