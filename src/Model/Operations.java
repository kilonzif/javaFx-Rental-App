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
     public void updateRecord (int index,String name, String add, String phone, String chair, String table, String canopy, String date) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("hghj   :" +index);
        String updateStmt ="UPDATE customerDetails SET customerName=?,address=?,phone=?,chairs=?,tableQty=?,canopy=?,rentDate=? WHERE id = ?";
        conn=createConnection();
        statement = conn.prepareStatement(updateStmt);
        //statement.setString(1, "0");
        statement.setString(1, name);
        statement.setString(2, add);
        statement.setString(3, phone);
        statement.setString(4, chair);
        statement.setString(5, table);
        statement.setString(6, canopy);
        statement.setString(7, date);
        statement.setInt(8, index);
        
        

         statement.execute();
     
    }
    
    
    
    
    
     public Connection createConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        DatabaseConnection connecta = new DatabaseConnection();
        return connecta.Connector();

    }
    
}
