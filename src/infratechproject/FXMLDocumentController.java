/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import Model.addModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
@SuppressWarnings("unchecked")
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
    private MenuItem navigateTransaction;
    @FXML
    private MenuItem navigateStock;
    @FXML
    private MenuItem navigateHelp;
    @FXML
    private AnchorPane rooter;
    @FXML
    private MenuItem logOut;
    EditRecordDialogController editC;

    /**
     * Initializes the controller.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chairTxt.setText("0");
        canopyTxt.setText("0");
        tableTxt.setText("0");
        durationTxt.setText("0");

    }

    /**
     * adds transaction and inserts it in the database. calculates the cost
     * payment by calling the calculatePayment method.
     *
     * @param event is the click of the add button.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @FXML
    private void addTransaction(ActionEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        editC = new EditRecordDialogController();
        String name = nameTxt.getText();
        String add = addressTxt.getText();
        String phone = phoneTxt.getText();
        String chair = chairTxt.getText();
        String table = tableTxt.getText();
        String canopy = canopyTxt.getText();
        LocalDate dater = datePicker.getValue();
        String date = dater.toString();
        if (editC.isInputValid()) {
            double cost = calculateCost();
            addModel mod = new addModel();
            mod.addCustomer(name, add, phone, chair, table, canopy, date);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Payment Totals");
            alert.setHeaderText("Payment Totals");
            alert.setContentText(cost + "USD");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Added SUCCESSFULLY!!!");

            }

        }
    }

    /**
     * Closes the whole program. shows a confirmation alert box to log out.
     *
     * @param event is the click of close file menu item.
     */
    @FXML
    private void closeFile(ActionEvent event) {
        if (confirmEntry()) {
            System.exit(0);
        }

    }

    /**
     * Navigates to the transaction viewer table page.
     *
     * @param event the click of the view function.
     * @throws IOException
     */
    @FXML
    private void viewTransaction(ActionEvent event) throws IOException {
        Stage prev = (Stage) canopyTxt.getScene().getWindow();
        prev.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewTransaction.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Infratech Rentals ");
            stage.getIcons().add(new Image("file:myLogo.png"));
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Navigates to the viewStock page.
     *
     * @param event is the click of the view menu item action.
     */
    @FXML
    private void viewStock(ActionEvent event) {
        Stage prev = (Stage) canopyTxt.getScene().getWindow();
        prev.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewStock.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Infratech Rentals ");
            stage.getIcons().add(new Image("file:myLogo.png"));
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * navigates the user to the help page. Shows how the program is used.
     *
     * @param event
     */
    @FXML
    private void helpUser(ActionEvent event) {
        Stage prev = (Stage) canopyTxt.getScene().getWindow();
        prev.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutInfratech.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Infratech Rentals ");
            stage.getIcons().add(new Image("file:myLogo.png"));
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Logs the user out of the main page and navigates back to the Home page
     * shows a confirmation alert box to log out.
     *
     * @param event
     */
    @FXML
    private void logOutFile(ActionEvent event) {
        if (confirmEntry()) {
            Stage prev = (Stage) canopyTxt.getScene().getWindow();
            prev.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Infratech Rentals ");
                stage.getIcons().add(new Image("file:myLogo.png"));
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * calculates the cost of the payments based on the quantity of each item
     * and the duration.
     *
     * @return void.
     */
    public double calculateCost() {
        double chairCost = (Integer.parseInt(chairTxt.getText())) * 20;
        double tableCost = (Integer.parseInt(tableTxt.getText())) * 15;
        double canopyCost = (Integer.parseInt(canopyTxt.getText())) * 30;
        int days = Integer.parseInt(durationTxt.getText());
        double cost = 0;
        if (days < 10) {
            cost = 100 + chairCost + tableCost + canopyCost;
        } else if (days > 10 && days > 20) {
            cost = 500 + chairCost + tableCost + canopyCost;
        }
        return cost;

    }

    /**
     * Confirms a users entry in an alert type.
     *
     * @return true if user confirms the action.
     */
    public boolean confirmEntry() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Page Close");
        alert.setContentText("Sure to Close?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

}
