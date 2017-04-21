/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infratechproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;//Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *Facilitates Login into the program.
 * @author Faith Kilonzi
 */
@SuppressWarnings("unchecked")
public class MainPageController implements Initializable {

    @FXML
    private Button logInBtn;
    @FXML
    private Hyperlink signUpLink;
    @FXML
    private PasswordField passTxt;
    @FXML
    private TextField userTxt;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Validates the login event then goes to the next main page if true.
     *
     * @param event is the click to the login button.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @FXML
    private void logIn(ActionEvent event) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            validateLogIn();
        } catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Links the user to a page to create an account to use the program.
     *
     * @param event is the click on the account creation link.
     */

    @FXML
    private void createAccount(ActionEvent event) {
        Stage prev = (Stage) logInBtn.getScene().getWindow();
        prev.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Infratech Rentals ");
            stage.getIcons().add(new Image("file:myLogo.png"));
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
        }
    }

    /**
     * validates the user input for login details. Checks whether the input data
     * is in the database.
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public void validateLogIn() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            String sql = "SELECT username, passwd from accountUsers WHERE username = ? AND passwd = ?";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn;
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/IcpFaith?user=root&password=faith1");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userTxt.getText());
            ps.setString(2, userTxt.getText());
            ResultSet rs = ps.executeQuery("SELECT * FROM accountUsers");

            while (rs.next()) {

                if ((userTxt.getText().equalsIgnoreCase(rs.getString("username")))
                        && (passTxt.getText().equalsIgnoreCase(rs.getString("passwd")))) {
                    Stage prev = (Stage) logInBtn.getScene().getWindow();
                    prev.close();
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Infratech Rentals ");
                        stage.getIcons().add(new Image("file:myLogo.png"));
                        stage.setScene(new Scene(root1));
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException e) {
                    }

                } else {
                    System.out.println("Check Input!");
                }

            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
