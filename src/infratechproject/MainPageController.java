/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author study
 */
public class MainPageController implements Initializable {

    @FXML
    private Button logInBtn;
    @FXML
    private PasswordField pinTxt;
    @FXML
    private Label dateLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       Date date = new Date();
       System.out.println(dateFormat.format(date));
       dateLabel.setText(dateFormat.format(date));
       
       
        
        
    }    

    @FXML
    private void logIn(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root=(Parent)fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
 
      
        
        stage.show();
    }
    
}
