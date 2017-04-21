/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles Addition and insertion into the database.
 * @author Faith
 */
@SuppressWarnings("unchecked")
public class addModel {

    Connection conn = null;

    PreparedStatement statement = null;

    /**
     * Inserts the new customer into the database in relevant attributes. The
     * parameters are customer attribute and details.
     *
     * @param name
     * @param add
     * @param phone
     * @param chair
     * @param table
     * @param canopy
     * @param date
     * @return true if insertion is successful
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public boolean addCustomer(String name, String add, String phone, String chair, String table, String canopy, String date) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "INSERT INTO customerDetails VALUES(?,?,?,?,?,?,?,?)";
        conn = createConnection();
        statement = conn.prepareStatement(query);
        statement.setString(1, "0");
        statement.setString(2, name);
        statement.setString(3, add);
        statement.setString(4, phone);
        statement.setString(5, chair);
        statement.setString(6, table);
        statement.setString(7, canopy);
        statement.setString(8, date);

        return statement.execute();
    }

    /**
     * Inserts a new account user into the database and table accountUsers after
     * a successful sign up.
     *
     * @param username
     * @param passwad
     * @param C_passwad
     * @return true if the insertion is successful.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public boolean addAccount(String username, String passwad, String C_passwad) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "Insert Into accountUsers set username=?,passwd =  ? ,C_passwd =  ?";
        conn = createConnection();
        statement = conn.prepareStatement(query);

        statement.setString(1, username);
        statement.setString(2, passwad);
        statement.setString(3, C_passwad);
        return statement.execute();

    }

    
  
    /**
     * Creates an instance of the database connection class to establish a
     * connection.
     *
     * @return Connection with the database
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Connection createConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseConnection connecta = new DatabaseConnection();
        return connecta.Connector();

    }
}
