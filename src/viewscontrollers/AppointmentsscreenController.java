
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewscontrollers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.appointment;

/**
 * FXML Controller class
 *
 * @author Francisco Trigo
 */
public class AppointmentsscreenController implements Initializable {
    Parent root;
    Stage stage;
    
    @FXML
    private Button BackButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button AddButton;
    
    @FXML
    private Label Titlelabel;
    
    @FXML
    private RadioButton weekRadio;
    @FXML
    private RadioButton monthRadio;
    
    @FXML
    private TableView<appointment> appTable;
    @FXML
    private TableColumn<appointment, String> AppIDColumn;
    @FXML
    private TableColumn<appointment, String> TitleColumn;
    @FXML
    private TableColumn<appointment, String> DescriptionColumn;
        @FXML
    private TableColumn<appointment, String> LocationColumn;
        @FXML
    private TableColumn<appointment, String> ContactColumn;
        @FXML
    private TableColumn<appointment, String> TypeColumn;
        @FXML
    private TableColumn<appointment, String> StartColumn;
            @FXML
    private TableColumn<appointment, String> EndColumn;
                @FXML
    private TableColumn<appointment, String> CustomerIDColumn;
    
                
    ObservableList<appointment> appointmentsOL = FXCollections.observableArrayList();
   // private static appointment selectedAppointment = new appointment();
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            updateApptTable();
                    
                    // TODO
                    } catch (Exception ex) {
            Logger.getLogger(AppointmentsscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    
    public void updateApptTable() throws SQLException, Exception {
        System.out.println("Updating Appt table");
        PreparedStatement ps;
        ps = DBConnection.startConnection().prepareStatement(""
                + "SELECT Appointment_ID, Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID "
                + "FROM appointments");
        ResultSet rs = ps.executeQuery();
        System.out.println("SQL Worked!");
        appointmentsOL.clear();
        
        while(rs.next()) {
            System.out.println("Fill it"
                    + "Create appointment obj");
            appointment appt = new appointment();
            
            // try different way, maybe its easier
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            int appointmentContactID = rs.getInt("Contact_ID");
            String appointmentType = rs.getString("Type");
            int appointmentCustomerID = rs.getInt("Customer_ID");
            
            int appointmentUserID = 1;
            
            String appointmentStart = rs.getString("Start");
            String appointmentEnd = rs.getString("end");
            
            // Add to the table, lets try!
            
            appointmentsOL.add(new appointment(appointmentID, appointmentCustomerID, appointmentUserID, appointmentCustomerID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd));
            appTable.setItems(appointmentsOL);
        }
            
        //}
        
        
    }

    
    /////////////////////////////////////////////////////////////////////
    
    @FXML
    private void AddAppointmentHandler (ActionEvent event){
        
    }
    
    @FXML
    private void DeleteHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void UpdateHandler (ActionEvent event){
        
    }
    
    @FXML
    private void weekRadioHandler (ActionEvent event){
        
    }
    
    @FXML
    private void monthRadioHandler (ActionEvent event) {
        
    }
    
    
    @FXML
    private void BackHandler (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/MainMenu.fxml"));
        stage = (Stage) BackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
    }
    
