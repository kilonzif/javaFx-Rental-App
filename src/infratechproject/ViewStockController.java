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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author study
 */
public class ViewStockController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button addButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void navigateBack(ActionEvent event) {
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
    private void addStock(ActionEvent event) {
    }
    
}
