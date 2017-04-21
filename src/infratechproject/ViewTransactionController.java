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
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    public static Record selectedItem;
    @FXML
    private ComboBox<String> selectBox;
    private FilteredList<Record> filteredData;
    private ObservableList<Record> data;
    @FXML
    private TextField searchField;

    /**
     * Initializes the controller class. sets the combo box items initializes
     * the filter-list
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buildData();
            selectBox.getItems().removeAll(selectBox.getItems());
            selectBox.getItems().addAll("Name", "Phone#");
            selectBox.getSelectionModel().select("Select");
            filteredData = new FilteredList<>(data, p -> true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Initializes table view of Record Class object and sets relevant column
     * attributes calls the database display result set to read from the
     * database to the external table view
     */
    public void buildData() throws ClassNotFoundException {

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
        try {
            con = objDbClass.Connector();
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        data = FXCollections.observableArrayList();
        try {
            String SQL = "Select * from customerDetails Order By id";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while (rs.next()) {
                Record record = new Record();
                record.name.set(rs.getString("customerName"));
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

    /**
     * navigates to the previous page of adding transactions
     *
     * @param event is the click of the back button to navigate to the previous
     * page
     * @return void.
     */
    @FXML
    private void goBack(ActionEvent event) {
        Stage prev = (Stage) backButton.getScene().getWindow();
        prev.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Infratech Rentals ");
            //stage.getIcons().add(new Image("myLogo.png"));
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes the record of the selected item from the database and the table
     * view calls the alert box message dialog to confirm item deletion
     *
     * @param event is the click of the delete button to navigate to the
     * previous page
     * @return void.
     */
    @FXML
    private void deleteRecord(ActionEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int selectedIndex = transact_Table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (confirmAlert()) {
                Operations delOp = new Operations();
                if (delOp.deleteRecord(selectedIndex)) {
                    transact_Table.getItems().remove(selectedIndex);
                }
            }

        }

    }

    /**
     * takes the selected item to be edited in the next dialog controller Calls
     * a method that Opens a dialog to edit details for the specified record.
     *
     * @param event is the click of the edit button
     * @return void.
     */
    @FXML
    private void editButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        selectedItem = transact_Table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            EditRecordDialogController controller = new EditRecordDialogController();
            showRecordEditDialog(selectedItem);
            buildData();

        }

    }

    /**
     * Opens a dialog to edit details for the specified record. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param Record is the instance of the record Item selected to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showRecordEditDialog(Record Record) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditRecordDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            EditRecordDialogController controller = fxmlLoader.getController();
            controller.setDialogStage(stage);
            controller.setRecordDetails(Record);
            stage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException ex) {
            Logger.getLogger(ViewTransactionController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Searches for the information based on phone number or name selected in
     * the combo box. If the user selects phone number/name, the table view is
     * filtered and sorted based on the input if input in the text-field matches
     * the attribute content in the table view, it returns true
     *
     * @param event is selection on the combo box items
     * @return true if the user input matches table column selected.
     */
    @FXML
    private void searchItem(ActionEvent event) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((record) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (selectBox.getValue().equalsIgnoreCase("Name")) {
                    if (record.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                } else if (selectBox.getValue().equalsIgnoreCase("Phone#")) {
                    if (record.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                }
                return false;
            });
        });
        SortedList<Record> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(transact_Table.comparatorProperty());
        transact_Table.setItems(sortedData);
    }

    /**
     * displays a confirmation dialog for deletion
     *
     * @return true or false based on whether the user selects OK or Cancel
     */
    public boolean confirmAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;

    }

}
