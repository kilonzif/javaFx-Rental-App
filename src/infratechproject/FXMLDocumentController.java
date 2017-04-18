/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private AnchorPane rooter;
    @FXML
    private MenuItem logOut;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        chairTxt.setText("0");
        canopyTxt.setText("0");
        tableTxt.setText("0");
        durationTxt.setText("0");
      
    }    

    @FXML
    private void addTransaction(ActionEvent event) throws IOException {
        String name=nameTxt.getText();
        String add=addressTxt.getText();
        String phone=phoneTxt.getText();
        String chair=chairTxt.getText();
        String table=tableTxt.getText();
        String canopy=canopyTxt.getText();
        LocalDate dater=datePicker.getValue();
            
        
        
        
        
        
        
        
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Payment Totals");
            alert.setHeaderText("Payment Totals");
            alert.setContentText("...");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               Alert a=new Alert(Alert.AlertType.INFORMATION) ;
               a.setContentText("aDDED SUCCESSFULLY!!!");
                
            }
      
    }

    @FXML
    private void closeFile(ActionEvent event) {
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Page Close");
            alert.setContentText("Sure to Close?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               System.exit(0); 
                
            }
    }

    @FXML
    private void navigateSearch(ActionEvent event) {
              Stage prev = (Stage) canopyTxt.getScene().getWindow();
                prev.close();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchPage.fxml"));
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

    @FXML
    private void viewTransaction(ActionEvent event) throws IOException {
         Stage prev = (Stage) canopyTxt.getScene().getWindow();
                prev.close();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewTransaction.fxml"));
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

    @FXML
    private void viewStock(ActionEvent event) {
           Stage prev = (Stage) canopyTxt.getScene().getWindow();
                prev.close();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewStock.fxml"));
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

    @FXML
    private void helpUser(ActionEvent event) {
          Stage prev = (Stage) canopyTxt.getScene().getWindow();
                prev.close();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutInfratech.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Infratech Rentals " );
                    stage.getIcons().add(new Image("file:myLogo.png"));
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        
    }

    @FXML
    private void logOutFile(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setContentText("Sure to Log Out?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Stage prev = (Stage) canopyTxt.getScene().getWindow();
                prev.close();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Infratech Rentals " );
                    stage.getIcons().add(new Image("file:myLogo.png"));
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
     }
              
        
   
}
    

