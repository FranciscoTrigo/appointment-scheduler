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
 * @author Francisco Trigo
 */


public class MainMenuController implements Initializable {
    //int currentUser;
    //String thetext;
    
    /**
     * Initializes the controller class.
     * 
     */
    @FXML
    private Label MainMenuLabel;
    @FXML
    private Button CustomersButton;
    @FXML
    private Button AppointmentsButton;
    @FXML
    private Button ReportsButton;
    @FXML
    private Button ExitButton;
    
    Parent root;
    Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //System.out.println("in the mainmenu this is the number  "+ thetext);
    }
        
    @FXML
    private void CustomersButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Customer database");
      //  System.out.println(thetext);
        root = FXMLLoader.load(getClass().getResource("/views/customerscreen.fxml"));
        stage = (Stage) CustomersButton.getScene().getWindow();
        //CustomerscreenController.setCurrentUser(currentUser);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
            }
        
    @FXML
    private void AppointmentsButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Appointment Database");
        root = FXMLLoader.load(getClass().getResource("/views/appointmentsscreen.fxml"));
        stage = (Stage) AppointmentsButton.getScene().getWindow();
        //AppointmentsscreenController.setCurrentUser(currentUser);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
    
    @FXML
    private void ReportsButtonHandler(ActionEvent event) throws IOException {
        System.out.print("REPORTS");
        root = FXMLLoader.load(getClass().getResource("/views/customerscreen.fxml"));
        stage = (Stage) CustomersButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
    
    @FXML
    private void ExitButtonHandler(ActionEvent event) throws IOException {
        System.out.println("Time to go");
        System.out.println("thanks for using me");
        System.exit(0);
            }
    
//    public static void setCurrentUser(String thetext) {
//        thetext = thetext;
//        System.out.println("Current user is: " + thetext);
//        
//    }
    
    
    }    
    

