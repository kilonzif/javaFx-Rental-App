/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import Model.addModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Faith Mueni Kilonzi.
 */
@SuppressWarnings("unchecked")
public class AccountController implements Initializable {

    @FXML
    private TextField userNameTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Button accountBtn;

    EditRecordDialogController viewObject;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Creates the account for the user, validating different fields.
     *
     * @param event is the click on the click button.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    @FXML
    private void createBtn(ActionEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        String user = userNameTxt.getText();
        String pass = passwordTxt.getText();
        String Cpass = confirmPassword.getText();
        if (isInputValid()) {
            try {
                addModel objectAdd = new addModel();
                objectAdd.addAccount(user, pass, Cpass);

                alert.setContentText("Account Created Successfuly!");
                alert.showAndWait();

                Stage prev = (Stage) accountBtn.getScene().getWindow();
                prev.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Infratech Rentals ");
                stage.getIcons().add(new Image("file:myLogo.png"));
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.show();
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void cancelOperation(ActionEvent event) throws IOException {
        
        Stage prev = (Stage) accountBtn.getScene().getWindow();
                prev.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Infratech Rentals ");
                stage.getIcons().add(new Image("file:myLogo.png"));
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.show();
    }
    
    public boolean isInputValid() {
        String errorMessage = "";
        if (userNameTxt.getText().equals("")
                || confirmPassword.getText().equals("") || passwordTxt.getText().equals("") ) {
            errorMessage += "Please fill all fields!\n";

        }else if (passwordTxt.getText().length() < 6) {
            errorMessage += "password shld be 6 xters\n";
        }else if(!passwordTxt.getText().equals(confirmPassword.getText()))
            errorMessage += "passwords shld be the same \n";
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
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(alertMsg);
        alert.show();

    }

}
