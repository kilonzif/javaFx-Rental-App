/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 *
 * @author study
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button addBtn;
    @FXML
    private MenuItem close;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField chairTxt;
    @FXML
    private TextField canopyTxt;
    @FXML
    private TextField tableTxt;
    @FXML
    private TextField durationTxt;
    @FXML
    private MenuItem searchNav;
    @FXML
    private MenuItem navigateTransaction;
    @FXML
    private MenuItem navigateStock;
    @FXML
    private MenuItem navigateHelp;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addTransaction(ActionEvent event) {
    }

    @FXML
    private void closeFile(ActionEvent event) {
    }

    @FXML
    private void navigateSearch(ActionEvent event) {
    }

    @FXML
    private void viewTransaction(ActionEvent event) {
    }

    @FXML
    private void viewStock(ActionEvent event) {
    }

    @FXML
    private void helpUser(ActionEvent event) {
    }
    
}
