/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author study
 */
public class DatabaseConnection {
       public Connection Connector() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Connection conn;
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/IcpFaith?" + "user=root&password=faith1");
        Class.forName("com.mysql.jdbc.Driver").newInstance();        
        System.out.println("Connection Established");

        return conn;
        }catch(SQLException e){
       
        System.out.println("Something went wrong");
    
        } 
        return null;
    }
       
       
       
       
       
       
}
