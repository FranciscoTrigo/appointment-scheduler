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
 * @author yamif
 */
public class AppointmentsscreenController implements Initializable {
    Parent root;
    Stage stage;
    
    @FXML
    private Button BackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }  
    
    
    public void updateApptTable() throws SQLException, Exception {
        System.out.println("Updating Appt table");
        PreparedStatement ps;
        ps = DBConnection.startConnection().prepareStatement(""
                + "SELECT Appointment_ID, Title, Description, Location, Contact_ID, Start, End, Customer_ID "
                + "FROM appointments");
        ResultSet rs = ps.executeQuery();
        System.out.println("SQL Worked!");
        appointmentsOL.clear();
        
        while(rs.next()) {
            
        }
        
        
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
    

