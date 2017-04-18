/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import Model.DatabaseConnection;
import Model.Operations;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author study
 */
@SuppressWarnings("unchecked")
public class ViewTransactionController implements Initializable {

    @FXML
    private AnchorPane transactRoot;
    @FXML
    private TableView<Record> transact_Table;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Record, String> nameColumn;
    @FXML
    private TableColumn<Record, String> addressColumn;
    @FXML
    private TableColumn<Record, String> phoneColumn;
    @FXML
    private TableColumn<Record, String> chairColumn;
    @FXML
    private TableColumn<Record, String> canopyColumn;
    @FXML
    private TableColumn<Record, String> tableColumn;
    @FXML
    private TableColumn<Record, String> dateColumn;
    Connection con = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nameColumn.setCellValueFactory(
                    new PropertyValueFactory<>("name"));
            addressColumn.setCellValueFactory(
                    new PropertyValueFactory<>("address"));
            phoneColumn.setCellValueFactory(
                    new PropertyValueFactory<>("phone"));
            chairColumn.setCellValueFactory(
                    new PropertyValueFactory<>("chairs"));
            canopyColumn.setCellValueFactory(
                    new PropertyValueFactory<>("canopies"));
            tableColumn.setCellValueFactory(
                    new PropertyValueFactory<>("tables"));
            dateColumn.setCellValueFactory(
                    new PropertyValueFactory<>("dateEntry"));
            DatabaseConnection objDbClass = new DatabaseConnection();
            con = objDbClass.Connector();
            buildData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private ObservableList<Record> data;

    public void buildData() {
        data = FXCollections.observableArrayList();
        try {
            String SQL = "Select * from customerDetails Order By id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
                    //createStatement().executeQuery(SQL);
            while (rs.next()) {
                Record record = new Record();
                record.name.set(rs.getNString("customerName"));//set(rs.getString(name));
                record.address.set(rs.getString("address"));
                record.phone.set(rs.getString("phone"));
                record.chairs.set(rs.getString("chairs"));
                record.tables.set(rs.getString("tableQty"));
                record.canopies.set(rs.getString("canopy"));
                record.dateEntry.set(rs.getString("rentDate"));
                data.add(record);
            }
            transact_Table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        Stage prev = (Stage) backButton.getScene().getWindow();
        prev.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            //stage.setTitle("Infratech Rentals " );
            //stage.getIcons().add(new Image("myLogo.png"));
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteRecord(ActionEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
         int selectedIndex = transact_Table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Operations delOp=new Operations();
                delOp.deleteRecord(selectedIndex);
                transact_Table.getItems().remove(selectedIndex);
            } 

        }
        
    }

    @FXML
    private void editButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            transact_Table.setEditable(true);
            transact_Table.setEditable(true);
            nameColumn.setCellFactory(TextFieldTableCell.<Record>forTableColumn());
            nameColumn.setOnEditCommit(
                    (TableColumn.CellEditEvent<Record, String> t) -> {
                        ((Record) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    });
            String name=nameColumn.getText();
            int selectedIndex = transact_Table.getSelectionModel().getSelectedIndex();
            Operations operationsObject=new Operations();
            operationsObject.updateRecord(selectedIndex, name);//updateRecord(selectedIndex,nameColumn);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
