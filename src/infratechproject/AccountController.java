/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import Model.addModel;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author study
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createBtn(ActionEvent event) throws SQLException, ClassNotFoundException, InstantiationException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
        String user=userNameTxt.getText();
        String pass=passwordTxt.getText();
        String Cpass=confirmPassword.getText();
         if (user.equals("") || pass.equals("") || Cpass.equals("")) {
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        } else if (!Cpass.equals(pass)) {
           // alert.setContentText("passwords are not the same");
           // alert.showAndWait();
           System.out.println("dashkjsa");
            
        } else if(pass.length() < 6)
            //alert.setContentText("password should be atleast 6 characters");
            System.out.println("Xters");
           
        else{
            try {     
                insert();
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
           // alert.setContentText("Account Created Successfuly!");
            //alert.showAndWait();
            System.out.println("succeeeess");
          
        
          Stage prev = (Stage) accountBtn.getScene().getWindow();
                prev.close();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Infratech Rentals " );
                    stage.getIcons().add(new Image("file:myLogo.png"));
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
        }
    public void insert() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/IcpFaith?user=root&password=faith1");
        String username=userNameTxt.getText();
        String passwad=passwordTxt.getText();
        String C_passwad=confirmPassword.getText(); 
             addModel mod = new addModel();
       mod.addAccount(username,passwad,C_passwad);
    
    
    
    }
    
    
}
