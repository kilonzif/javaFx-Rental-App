/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import Model.Operations;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author study
 */
public class EditRecordDialogController implements Initializable {

    @FXML
    public TextField editEmail;
    @FXML
    public TextField editPhone;
    @FXML
    public TextField editChair;
    @FXML
    public TextField editName;
    @FXML
    public TextField editCanopy;
    @FXML
    public TextField EditTable;
    @FXML
    public DatePicker dateP;
    @FXML
    public Button okButton;
    @FXML
    private AnchorPane editDialog;
    private Stage dialogStage;
    private Record record;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the record to be edited in the dialog.
     *
     * @param record is the instance of the record object selected in the
     *
     */
    public void setRecordDetails(Record record) {
        this.record = record;
        editName.setText(record.getName());
        editEmail.setText(record.getAddress());
        editPhone.setText(record.getPhone());
        editChair.setText(record.getChairs());
        EditTable.setText(record.getTables());
        editCanopy.setText(record.getCanopies());
        dateP.setPromptText(record.getDateEntry());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return true;
    }

    /**
     * Called when the user clicks OK after editing. Calls the update method
     * from the database to update the edited Record Calls the build data method
     * to refresh the table after editing
     *
     * @param event confirms the click on the OK button..
     */
    @FXML
    private void okClicked(ActionEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        okClicked = true;
        Record record = new Record();
        String datey = dateP.getValue().toString();
        if (isInputValid()) {

            Stage prev = (Stage) editDialog.getScene().getWindow();
            Operations update = new Operations();
            update.updateRecord(ViewTransactionController.selectedItem.getName(), editName.getText(), editEmail.getText(), editPhone.getText(), editChair.getText(), EditTable.getText(), editCanopy.getText(), datey);

            prev.hide();

        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    public boolean isInputValid() {
        String errorMessage = "";
        if (editPhone.getText().equals("")
                || editName.getText().equals("") || editEmail.getText().equals("") || editChair.getText().equals("") || EditTable.getText().equals("") || editCanopy.getText().equals("") || dateP.getValue().equals("")) {
            errorMessage += "Please fill all fields!\n";

        } else if (!(editEmail.getText().contains(".") || editEmail.getText().contains("@"))) {
            errorMessage += "Incorrect email\n";
        } else if (editPhone.getText().length() < 10 || editPhone.getText().length() > 10) {
            errorMessage += "phone number shld be 10 xters\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            String alertMsg = errorMessage + " ";
            showAlert(alertMsg);

            return false;
        }

    }

    /**
     * Alerts the user on the input mismatch
     *
     * @param alertMsg contains the message alert to be displayed
     */
    public void showAlert(String alertMsg) {
        Alert alert;
        alert = new Alert(AlertType.ERROR);
        alert.setContentText(alertMsg);
        alert.show();

    }

}
