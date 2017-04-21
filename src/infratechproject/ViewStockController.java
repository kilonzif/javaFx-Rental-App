/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import Model.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author study
 */
@SuppressWarnings("unchecked")
public class ViewStockController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> itemBox;
    private ListView<String> ItemList;
    DatabaseConnection objDbClass = new DatabaseConnection();
    @FXML
    private TableView<Record> itemsTable;
    @FXML
    private TableColumn<Record, String> nameColumn;
    @FXML
    private TableColumn<Record, String> quantity;
    @FXML
    private TableColumn<Record, String> dateColumn;
    @FXML
    private TextArea totalsField;
    private ObservableList<Record> data;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemBox.getItems().addAll("chairs", "tables", "canopies");
        itemBox.getSelectionModel().select("chairs");
    }

    /**
     * Navigates back to the main page.
     *
     * @param event
     */
    @FXML
    private void navigateBack(ActionEvent event) {
        Stage prev = (Stage) backButton.getScene().getWindow();
        prev.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
        }
    }

    /**
     * Confirms viewing of the selected item.
     *
     * @param event
     */
    @FXML
    private void viewSelectedItem(ActionEvent event) throws SQLException {
        if (itemBox.getValue().equalsIgnoreCase("Tables")) {
            viewTable();
        } else if (itemBox.getValue().equalsIgnoreCase("chairs")) {
            viewChairs();
        } else if (itemBox.getValue().equalsIgnoreCase("canopies")) {
            viewCanopies();
        } else {
            System.out.println("no item selected!");
        }
    }

    /**
     * Displays the number of tables bought in total and the object customer
     * that bought it Uses the sql to select all the required information from
     * the database.
     *
     * @throws SQLException
     */
    public void viewTable() throws SQLException {
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(
                new PropertyValueFactory<>("tables"));
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("dateEntry"));
        data = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT SUM(tableQty) AS TotalBought from customerDetails";
            Connection con = objDbClass.Connector();
            ResultSet rs = con.createStatement().executeQuery(SQL);
            rs.next();
            String sum = " " + rs.getString(1) + " ";
            System.out.println(sum);

            String sql = "SELECT customerName,tableQty,rentDate AS details FROM customerDetails";

         /*   ResultSet resultTwo = con.createStatement().executeQuery(sql);
            while (resultTwo.next()) {
                Record record = new Record();
                record.name.set(rs.getString("customerName"));
                record.address.set(rs.getString("tableQty"));
                record.phone.set(rs.getString("rentDate"));
                data.add(record);
            }
            */
            totalsField.setText(sum);
           // itemsTable.setItems(data);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ViewStockController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Displays the number of chairs bought in total and the object customer
     * that bought it Uses the sql to select all the required information from
     * the database.
     *
     * @throws SQLException
     */
    public void viewChairs() throws SQLException {
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(
                new PropertyValueFactory<>("chairs"));
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("dateEntry"));
        data = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT SUM(chairs) AS TotalBought from customerDetails";
            Connection con = objDbClass.Connector();
            ResultSet rs = con.createStatement().executeQuery(SQL);
            rs.next();
            String sum = " " + rs.getString(1) + " ";

       /*     String sql = "SELECT customerName,chairs,rentDate AS details FROM customerDetails";
            ResultSet resultTwo = con.createStatement().executeQuery(sql);
            while (resultTwo.next()) {
                Record record = new Record();
                record.name.set(rs.getString("customerName"));
                record.address.set(rs.getString("chairs"));
                record.phone.set(rs.getString("rentDate"));
                data.add(record);
            }*/
            totalsField.setText(sum);
            //itemsTable.setItems(data);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ViewStockController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Displays the number of canopies bought in total and the object customer
     * that bought it Uses the sql to select all the required information from
     * the database.
     *
     * @throws SQLException
     */
    public void viewCanopies() throws SQLException {
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(
                new PropertyValueFactory<>("canopies"));
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("dateEntry"));
        data = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT SUM(canopy) AS TotalBought from customerDetails";
            Connection con = objDbClass.Connector();
            ResultSet rs = con.createStatement().executeQuery(SQL);
            rs.next();
            String sum = " " + rs.getString(1) + " ";
/*
            String sql = "SELECT customerName,canopy,rentDate AS details FROM customerDetails";
            ResultSet resultTwo = con.createStatement().executeQuery(sql);
            while (resultTwo.next()) {
                Record record = new Record();
                record.name.set(rs.getString("customerName"));
                record.address.set(rs.getString("canopy"));
                record.phone.set(rs.getString("rentDate"));
                data.add(record);
            }
*/
            totalsField.setText(sum);
           // itemsTable.setItems(data);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ViewStockController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
