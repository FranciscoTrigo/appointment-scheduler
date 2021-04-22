/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewscontrollers;

import model.User;
import model.Customer;
import model.Dummy;
import model.User;
import model.Appointment;
import utils.DBConnection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francisco Trigo
 */

public class LoginscreenController implements Initializable {
    
    String currentUser = "0";
    
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
       // currentUser = 0;
        
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
    private void UsernameTexFieldHandler(ActionEvent event) {
    
    }
    
    @FXML
    private void PasswordTextFieldHandler(ActionEvent event) {
        
    }
    
    @FXML
    private void LoggingButtonHandler(ActionEvent event) throws SQLException, IOException {
        //System.out.println("ASD");ooo
        String usernameInput = UsernameTextField.getText();
        String passwordInput = PasswordTextField.getText();
        
 
        //Parent root;
        Stage stage;

        // see if password is correct
        if (isValidPassword(usernameInput, passwordInput)) {
//            User user = new User();
//            user.setUsername(UsernameTextField.getText());
            
          
           
            System.out.println("Log in complete!");
            
            
            // call the main menu because log in is good!
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/MainMenu.fxml"));
            Parent root = loader.load();
            stage = (Stage) LoginButton.getScene().getWindow();
           
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //MainMenuController MainMenuController = loader.getController();
            //MainMenuController.setCurrentUser(currentUser);
            //MainMenuController.setLabelText(currentUser);
            stage.show();            
        } else {
            textLogBad(UsernameTextField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WRONG");
            alert.setHeaderText("Incorrect username or password, or both");
            alert.setContentText("Enter valid username or password");
            Optional<ButtonType> result = alert.showAndWait();            
        }
    }
    
    private void testPopup() {
        //System.out.println(User.getUsername() + "sdfsdfsdfsdfsdfsdfsdfsdfs");
        if (User.getUserID() == 1) {
            System.out.println(User.getUsername() + "sdfsdfsdfsdfsdfsdfsdfsdfs");
            System.out.println("Automatic appointment pop up for test user");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment notification");
            alert.setHeaderText("CASASDASDASD");
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 15);
            alert.setContentText("You have an appointment whithin 15 minutes. "
                    + "\n Your appointment is at: " + dateFormat.format((cal.getTime())));
            Optional<ButtonType> result = alert.showAndWait();
            
            
            
            
        }
    }
    
    
    private boolean isValidPassword( String usernameInput, String passwordInput) throws SQLException, IOException {
        /// This function checks if the password is right for the user
     //       System.out.println("cas");poo
            Statement statement = DBConnection.conn.createStatement();
            String sqlStatement = "SELECT password, User_ID, User_Name FROM users WHERE User_Name ='" + usernameInput + "'";;
            ResultSet result = statement.executeQuery(sqlStatement);
           // Appointment appointment = new Appointment();
           // Create dummy object to keep appt info later on...
            Dummy dummy = new Dummy();
      

            
            while (result.next()) {
                if (result.getString("password").equals(passwordInput)) {
                   // currentUser = result.getString("User_ID");
                   // create user object that will be used thru the app
                    User user = new User();
                    user.setUsername(result.getString("User_Name"));
                    user.setUserID(result.getInt("User_ID"));
                    textLog(result.getString("User_Name"));
                    testPopup();
                    return true;
                } 
                   
                
               // int currentUser = result.getInt("User_ID");
            }
            return false;            
        }
    
    
        private void textLogBad(String user) throws IOException {
            // Logs to a txt file if the login is unsucessful. It logs in attempted username, result of the operation and timestamp
        try {
            String fileName = "login_activity.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            writer.append("\nWRONG login: " + user + " at "+ ts);
            System.out.println("Updating the log!");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);           
        }; 
    };
    private void textLog(String user) throws IOException {
        // logs to txt file when log in is successful. logs username, result, and timestamp
        try {
            String fileName = "login_activity.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            writer.append("\nSuccesful login: " + user + " at "+ ts);
            System.out.println("Updating the log!");
            writer.flush();
            writer.close();
        } catch  (IOException e) {
            System.out.println("Error: " + e);
        };
    };
}
      
 
       
