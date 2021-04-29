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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
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
    boolean noApp;
    boolean yesApp = false;
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
     * @param url url 
     * @param rb resource bundle
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
    private void LoggingButtonHandler(ActionEvent event) throws SQLException, IOException, Exception {
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
    
    /**
     * Checks if there is an appointment scheduled in the next 15 minutes or less and gives a popup notice if true
     * @throws SQLException --
     * @throws Exception  -- 
     */
    private void appointmentPopup() throws SQLException, Exception {
        // Do nothing if current user is test user
        if (User.getUserID() == 1) {
            return;
        } else {
            // Checks the Start time of all appointments and dows a while loop
            System.out.println("Checking to see if there are appointments drawing near...");
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select Start,Appointment_ID, Location FROM appointments");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                // Checks if appointment is close...
               // System.out.println(result.getString("Start"));
                if (checkIfNear(result.getString("Start"))) {
                    System.out.println("Go to appointment!"); 
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment notice");
                    alert.setHeaderText("You have an upcoming appointment!!!");
                    alert.setContentText("You have an appointment scheduled within 15 minutes.\n"
                            + "Appointment ID: " + result.getString("Appointment_ID") + "\n"
                            + "Appointment starts at: " + result.getString("Start") + "\n"
                            + "Appointment takes place at: " + result.getString("Location"));
                    Optional<ButtonType> result2 = alert.showAndWait();
                    yesApp = true;
                    break;
                    
                }
                
                if (checkIfNear(result.getString("Start")) == false) {
                    //noAppPopup();
                    noApp = true;
                    
                    
                }
            }
            if (noApp == true && yesApp == false) { 
                noAppPopup();
                // no app popup 
                
            }
            
        }
    }
    
   private void noAppPopup() {
                           System.out.println("No appointment");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No appointment notice");
                    alert.setHeaderText("You do not have any upcoming appointments");
                    alert.setContentText("You do not have any appointments in the next 15 minutes");
                    Optional<ButtonType> result3 = alert.showAndWait();
   }
    /**
     * This function checks if the provided date and time happens within 15 minutes from right now
     * @param apptDate This is the date taken from the Start column in the appointments table
     * @return true or false depending on if within 15 minutes or not
     * @throws ParseException  -- 
     */
    private boolean checkIfNear(String apptDate) throws ParseException {
        SimpleDateFormat aDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        aDateFormat.setLenient(false);
        
        Date date = aDateFormat.parse(apptDate);
        
        Calendar after15 = Calendar.getInstance();
        after15.add(Calendar.MINUTE, 15);
        
        Calendar before15 = Calendar.getInstance();
        before15.add(Calendar.MINUTE, -15);
        
        if (date.before(after15.getTime()) && date.after(before15.getTime())) {
            return true;
        } else { return false; }
        
    }
    /**
     * This is an appointment test popup for the test user only. It will always activate
     */
    private void testPopup() {
        if (User.getUserID() == 1) {
            System.out.println("Automatic appointment pop up for test user");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment notification");
            alert.setHeaderText("Appointment is drawing near");
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 13);
            alert.setContentText("You have an appointment within 15 minutes. "
                    + "\n Your appointment is at: " + dateFormat.format((cal.getTime())));
            Optional<ButtonType> result = alert.showAndWait();
   
        }
    }
    
    /**
     * Checks If the input username and password are correct and a pair, will return true if true
     * @param usernameInput username taken from the username text field
     * @param passwordInput password taken from the password text field
     * @return returns true is pair is good
     * @throws SQLException --
     * @throws IOException --
     * @throws Exception --
     */
    private boolean isValidPassword( String usernameInput, String passwordInput) throws SQLException, IOException, Exception {
        /// This function checks if the password is right for the user

            Statement statement = DBConnection.conn.createStatement();
            // statement to select user_ID, password and user_name where username = input username, if adequate
            String sqlStatement = "SELECT password, User_ID, User_Name FROM users WHERE User_Name ='" + usernameInput + "'";;
            ResultSet result = statement.executeQuery(sqlStatement);

            Dummy dummy = new Dummy();
      

            
            while (result.next()) {
                // checks if the input password is correct for the user
                if (result.getString("password").equals(passwordInput)) {
                    User user = new User();
                    user.setUsername(result.getString("User_Name"));
                    user.setUserID(result.getInt("User_ID"));
                    textLog(result.getString("User_Name"));
                    testPopup();
                    appointmentPopup();
                    return true;
                } 
                   
                
               // int currentUser = result.getInt("User_ID");
            }
            return false;            
        }
    
    /**
     * Logs in a txt file every time a bad log in is attempted.
     * @param user Username that had attempt to log in. To save it.
     * @throws IOException 
     */
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
    /**
     * Log into txt file when logins are successful
     * @param user username that attempted log in
     * @throws IOException --
     */
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
      
 
       
