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
        buildData();
       /* try {
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
        */
    }
    private ObservableList<Record> data;

    public void buildData() {
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
           // buildData();
            data = FXCollections.observableArrayList();
        try {
            String SQL = "Select * from customerDetails Order By id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Record record = new Record();
                record.name.set(rs.getNString("customerName"));
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
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
                Operations delOp = new Operations();
                delOp.deleteRecord(selectedIndex);
                transact_Table.getItems().remove(selectedIndex);
            }

        }

    }

    public Record returnModel() {
        return transact_Table.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void editButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        Record selectedItem= transact_Table.getSelectionModel().getSelectedItem();
        int id = transact_Table.getSelectionModel().getSelectedIndex();
        
        if (selectedItem != null) {
             EditRecordDialogController controller = new EditRecordDialogController();//.getController();
            //controller.callOk();
            //if(controller.getIsOkay(event))
            showRecordEditDialog(selectedItem);
            if(controller.isOkClicked()){
                System.out.println(controller.editName.getText());
                Operations update= new Operations();
                System.out.println(id);
                buildData();
            }
            else{
                System.out.println("trying edit");
            }
            

        }

    }



/**
 * Opens a dialog to edit details for the specified record. If the user clicks
 * OK, the changes are saved into the provided person object and true is
 * returned.
 *
 * @param item is instance of the record object to be edited
 * @return true if the user clicked OK, false otherwise.
 */
public boolean showRecordEditDialog(Record Record) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditRecordDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            
             // Set the Record into the controller
            EditRecordDialogController controller = fxmlLoader.getController();
            controller.setDialogStage(stage);
            controller.setRecordDetails(Record);
       
            // Show the dialog and wait until the user closes it
           
            stage.showAndWait();
            
            return controller.isOkClicked();
            

        

} catch (IOException ex) {
            Logger.getLogger(ViewTransactionController.class
.getName()).log(Level.SEVERE, null, ex);
           return false;
        }

    }

    /**
     * Refreshes the table. This is only necessary if an item that is already in
     * the table is changed. New and deleted items are refreshed automatically.
     */
//   private void updateRecord() {
//       int selectedIndex=transact_Table.getSelectionModel().getSelectedIndex();
//        transact_Table.layout();
//        transact_Table.getSelectionModel().select(selectedIndex);
//        
//        transact_Table.setItems(data);
//        transact_Table.getSelectionModel().select(selectedIndex);
//    }


}
