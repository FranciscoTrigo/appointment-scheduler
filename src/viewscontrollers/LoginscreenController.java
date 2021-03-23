/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewscontrollers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import utils.DBConnection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yamif
 */
public class LoginscreenController implements Initializable {
    @FXML
    private TextField UsernameTextField;
    @FXML 
    private PasswordField PasswordTextField;
    
    @FXML
    private Label titleLabel;
    @FXML
    private Label locationLabel;
    
    @FXML
    private Button LoginButton;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Getting resource bundle...");
        try {
            rb = ResourceBundle.getBundle("Properties.login", Locale.getDefault());
            System.out.println("Bundle adquired");
            titleLabel.setText(rb.getString("title"));
            UsernameTextField.setPromptText(rb.getString("username"));
            PasswordTextField.setPromptText(rb.getString("password"));
            LoginButton.setText(rb.getString("login"));
        } catch (MissingResourceException e) {
            System.out.println("Where is the resource file?");
        }
    }
    
    @FXML
    private void UsernameTextFieldHandler(ActionEvent event) {
    
    }
    
    @FXML
    private void PasswordTextFieldHandler(ActionEvent event) {
        
    }
    
    @FXML
    private void LogginButtonHandler(ActionEvent event) throws SQLException, IOException {
        String usernameInput = UsernameTextField.getText();
        String passwordInput = PasswordTextField.getText();
        Parent root;
        Stage stage;
        User user = new User();
        
        if (isValidPassword(usernameInput, passwordInput)) {
            user.setUsername(usernameInput);
           
            System.out.println("Log in complete!");
            
            // call the main menu because log in is good!
            
            root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            stage = (Stage) LoginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WRONG");
            alert.setHeaderText("Incorrect username or password, or both");
            alert.setContentText("Enter valid username or password");
            Optional<ButtonType> result = alert.showAndWait();            
        }
    }
    
    
    private boolean isValidPassword( String usernameInput, String passwordInput) throws SQLException {
            Statement statement = DBConnection.conn.createStatement();
            String sqlStatement = "SELECT password FROM users WHERE User_Name ='" + usernameInput + "'";;
            ResultSet result = statement.executeQuery(sqlStatement);
            
            while (result.next()) {
                if (result.getString("password").equals(passwordInput)) {
                    return true;
                }
            }
            return false;            
        }
        
    }
    
    
        
        
        
        // TODO
       
    
 
