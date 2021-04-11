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
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.appointment;

/**
 * FXML Controller class
 *
 * @author yamif
 */



public class AddAppointmentController implements Initializable {
    
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label locationLable;
    @FXML
    private Label typeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label contactLabel;
    @FXML
    private Label customerLabel;
    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;
    @FXML
    private Label appointmentIDLabel;
    
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField typeField;
    @FXML
    private DatePicker selectDateBox;
    
    @FXML
    private ComboBox contactBox;
    @FXML
    private ComboBox customerBox;
    @FXML
    private ComboBox startHourCombo;
    @FXML
    private ComboBox startMinuteCombo;
    @FXML
    private ComboBox endHourCombo;
    @FXML
    private ComboBox endMinuteCombo;
    @FXML
    private ComboBox locationBox;
    @FXML
    private ComboBox typeBox;
    
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    
    Parent root;
    Stage stage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTimeBoxes();
        fillLocationBox();
        fillTypeBox();
        // TODO
    }    
    
    public void fillTimeBoxes() {
        for (int i = 0; i <= 23; i++){
        startHourCombo.getItems().addAll(i);
        endHourCombo.getItems().addAll(i);
    }
        for (int i =0; i <= 59; i++) {
            startMinuteCombo.getItems().addAll(i);
            endMinuteCombo.getItems().addAll(i);
        }
                
    }
    
    public void fillLocationBox() {
        locationBox.getItems().addAll(
        "McDonalds",
        "Park",
        "Lunchroom",
        "Office",
        "Virtual",
        "Laundromat",
        "location"
        );         
    }
    
    public void fillTypeBox() {
        typeBox.getItems().addAll(
        "Meeting",
        "Lunch",
           "Planning",
           "type ");
    }
    
    @FXML
    private void contactBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void customerBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void selectDateBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void saveButtonHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void locationBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void typeBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void cancelButtonHandler (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/appointmentsscreen.fxml"));
        stage = (Stage) cancelButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
        
    }
    

