/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;

/**
 *
 * @author study
 */
@SuppressWarnings("unchecked")
public class Operations {
        Connection conn = null;
    ResultSet rs= null;
    PreparedStatement statement = null;

    public boolean deleteRecord(int index) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "DELETE FROM customerDetails WHERE id=?";
        conn=createConnection();
        statement = conn.prepareStatement(query);
        statement.setInt(1, index);
        return statement.execute();
    }
     public void updateRecord (int index,String name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        String updateStmt ="   UPDATE customerDetails" +"      SET EMAIL = '" + name + "'\n" +
                        "    WHERE id = " + index +"END;";
        conn=createConnection();
        statement = conn.prepareStatement(updateStmt);
        statement.setInt(1, index);
        statement.setString(2, name);
         statement.execute();
     
    }
    
    
    
    
    
     public Connection createConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        DatabaseConnection connecta = new DatabaseConnection();
        return connecta.Connector();

    }
    
}
