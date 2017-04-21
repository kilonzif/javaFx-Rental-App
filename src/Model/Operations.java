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
    ResultSet rs = null;
    PreparedStatement statement = null;
/**
 * 
 * @param id is the id of the selected item to delete.
 * @return true if Record deletion is successful.
 * @throws SQLException
 * @throws ClassNotFoundException
 * @throws InstantiationException
 * @throws IllegalAccessException 
 */
    
    public boolean deleteRecord(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "DELETE FROM customerDetails WHERE id=?";
        conn = createConnection();
        statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        return statement.execute();
    }

    /**
     * updates the edited data to the database
     *
     * @param oldname the initial name before editing.
     * @param name the new detail.
     * @param add the email address after editing.
     * @param phone the phone number after editing.
     * @param chair the number of chair after editing.
     * @param table the number of tables after editing.
     * @param canopy the number of canopy after editing.
     * @param date the date.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void updateRecord(String oldname, String name, String add, String phone, String chair, String table, String canopy, String date) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String updateStmt = "UPDATE customerDetails SET customerName=?,address=?,phone=?,chairs=?,tableQty=?,canopy=?,rentDate=? WHERE customerName = '" + oldname + "'";
        conn = createConnection();
        statement = conn.prepareStatement(updateStmt);
        statement.setString(1, name);
        statement.setString(2, add);
        statement.setString(3, phone);
        statement.setString(4, chair);
        statement.setString(5, table);
        statement.setString(6, canopy);
        statement.setString(7, date);
        statement.execute();
        statement.close();
        conn.close();

    }

    /**
     * creates an instance of the database connection.
     *
     * @return Connection between the database Connection class.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Connection createConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseConnection connecta = new DatabaseConnection();
        return connecta.Connector();

    }

}
