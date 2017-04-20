/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Faith Kilonzi.
 */
@SuppressWarnings("unchecked")
public class DatabaseConnection {

    /**
     * Creates a connection with the database.
     *
     * @return a database connection.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Connection Connector() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3300/IcpFaith", "root", "faith1");
            //System.out.println("Connected");
        } catch (SQLException e) {

            System.out.println(e.getCause() + " " + e.getMessage());

        }
        return null;
    }
}
