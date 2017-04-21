/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Performs the addition operation into the system program.
 * @author Faith Kilonzi
 */
@SuppressWarnings("unchecked")
public class AboutInfratechController implements Initializable {

    @FXML
    private AnchorPane helpPage;
    @FXML
    private TextArea aboutTxtField;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Navigates back to the main page.
     *
     * @param event is the click of the
     */
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

}
