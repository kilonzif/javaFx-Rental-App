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
 * @author study
 */
@SuppressWarnings("unchecked")
public class DatabaseConnection {
    public Connection Connector() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection("jdbc:mysql://localhost:3300/IcpFaith?" + "user=root&password=faith1");
        }catch(SQLException e){
       
        System.out.println(e.getCause()+" "+e.getMessage());
    
        } 
        return null;
    }    
}
