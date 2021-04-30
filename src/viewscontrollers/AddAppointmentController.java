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
import model.User;
import model.Appointment;

/**
 * FXML Controller class
 * This is the controller for the Add appointment screen
 * It is used to add appointments to the appointment table thru the use of text fields, combo boxes, and date picker
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
    private DatePicker selectDateBox;
    
    @FXML
    private ComboBox<String> contactBox;
    @FXML
    private ComboBox<String> customerBox;
    @FXML
    private ComboBox<String> startHourCombo;
    @FXML
    private ComboBox<String> startMinuteCombo;
    @FXML
    private ComboBox<String> endHourCombo;
    @FXML
    private ComboBox<String> endMinuteCombo;
    @FXML
    private ComboBox<String> locationBox;
    @FXML
    private ComboBox<String> typeBox;
   
    
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
        System.out.println(User.getUserID());
        try {
            // call the functions to fill the combo boxes
            fillTimeBoxes();
            fillLocationBox();
            fillTypeBox();
            fillCustomerBox();
            fillContactBox();
         
        } catch (Exception ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     *  This function will fill out the four combo Boxes dedicated to set up start time and end time
     *  It works in a 24 hour system. There are separate combo box for hour and for minute, and it repeats for end time.
     * 
     */
    public void fillTimeBoxes() {
        for (int i = 0; i <= 23; i++){
            if (i < 10) {
                String leadzero = String.format("%02d", i);
                startHourCombo.getItems().addAll(leadzero);
                endHourCombo.getItems().addAll(leadzero);
            } else {
        startHourCombo.getItems().addAll(String.valueOf(i));
        endHourCombo.getItems().addAll(String.valueOf(i));
            }
    }
        for (int i =0; i <= 59; i++) {
               if (i < 10) {
                String leadzero = String.format("%02d", i);
                startMinuteCombo.getItems().addAll(leadzero);
                endMinuteCombo.getItems().addAll(leadzero);
            } else {
        startMinuteCombo.getItems().addAll(String.valueOf(i));
        endMinuteCombo.getItems().addAll(String.valueOf(i));
            }
               
                
        }}
    
    /**
     * Function to go back to the main appointment screen
     * @throws IOException --
     */
    
    public void goBack() throws IOException {
                root = FXMLLoader.load(getClass().getResource("/views/appointmentsscreen.fxml"));
        stage = (Stage) cancelButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
    
    
    /**
     * Fills out the location comboBox.
     * This is done so we have a finite amount of possible locations, which makes it easier to manage because there are no typos or very specific locations now.
     */
    
    public void fillLocationBox() {
        locationBox.getItems().addAll(
        "McDonalds",
        "Park",
        "Lunchroom",
        "Office",
        "Virtual",
        "Laundromat",
        "location",
        "Other"
        );         
    }
    
    /**
     * Fills out the type comboBox.
     * Its done so there are a finite amount to available types
     */
    
    public void fillTypeBox() {
        typeBox.getItems().addAll(
        "Meeting",
        "Lunch",
       "Planning",
        "type ",
        "Other");
    }
    
    
    /**
     * Fills the contact box with contact names. It will get available contacts from the contacts table
     * @throws SQLException --
     * @throws Exception --
     */
    
    public void fillContactBox() throws SQLException, Exception {
        //statement creation
        Statement stmt = DBConnection.getConnection().createStatement();
        String sqlStatement = "SELECT Contact_Name FROM contacts";
        ResultSet result = stmt.executeQuery(sqlStatement);
        
        while (result.next()) {
            contactBox.getItems().add(result.getString("Contact_name"));
            }
        stmt.close();
        result.close();
    }
    
    /**
     * Fills the customer contact box with customer names. Gets names from customers table
     * @throws SQLException --
     * @throws Exception --
     */
    
    public void fillCustomerBox() throws SQLException, Exception {
                //statement creation
        Statement stmt = DBConnection.getConnection().createStatement();
        String sqlStatement = "SELECT Customer_Name FROM customers";
        ResultSet result = stmt.executeQuery(sqlStatement);
        
        while (result.next()) {
            customerBox.getItems().add(result.getString("Customer_Name"));
            }
        stmt.close();
        result.close();
    }
    
    /**
     * Saves the information currently written/selected on the fields into a new appointment using sql.
     * It makes 3 different queries to the db.
     * 
     * It first will use the Contact Name from the contact comboBox to get the contact Name, using the contacts table.
     * It does the same with the Customer Name.
     * 
     * It also creates two strings, for the Start and End time, it generates the strings by
     * combining the date from the datePicker and the hour comboBox.
     * 
     * Once it has all the information, it will insert a new appointment into the appointment table. 
     * 
     * Once the appointment is saved, it automatically returns to main appointments screen
     * 
     * @throws SQLException --
     * @throws Exception --
     */
    
    public void saveAppointment() throws SQLException, Exception {
        System.out.println("Saving the appointment...");
        
        // Getting the Customer_ID
        PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("SELECT Customer_ID "
                    + "FROM customers "
                    + "WHERE Customer_Name = ?");
        ps1.setString(1, customerBox.getValue());
        int CustID = 0;
        ResultSet result = ps1.executeQuery();
            while (result.next()) {
                CustID = result.getInt("Customer_ID");
            }
            //System.out.println("Customer ID IS: " + CustID);
            
        // Getting Contact ID
                PreparedStatement ps2 = DBConnection.getConnection().prepareStatement("SELECT * "
                    + "FROM contacts "
                    + "WHERE Contact_Name = ?");
        ps2.setString(1, contactBox.getValue());
        int ContactID = 0;
        ResultSet result1 = ps2.executeQuery();
            while (result1.next()) {
                ContactID = result1.getInt("Contact_ID");
            }
            //System.out.println("Contact ID IS: " + ContactID);
            
        // Get start and end time
        // START
        String startHH = startHourCombo.getValue();
        String startMM = startMinuteCombo.getValue();
        String startHour = startHH + ":" + startMM + ":00";
        //System.out.println(startHour);
        String startTime = selectDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + startHour;
        //System.out.println(startTime);
        
        
        // END
        String endHH = endHourCombo.getValue();
        String endMM = endMinuteCombo.getValue();
        String endHour = endHH + ":" + endMM + ":00";
        //System.out.println(endHour);
        String endTime = selectDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + endHour;
        // System.out.println(endTime);
        
        
        // Now we finally update it
                    PreparedStatement ps3 = DBConnection.getConnection().prepareStatement(""
                    + "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, Contact_ID, Created_By)"
                    +                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps3.setString(1, titleField.getText());
            ps3.setString(2, descriptionField.getText());
            ps3.setString(3, locationBox.getValue());
            ps3.setString(4, typeBox.getValue());
            ps3.setString(5, startTime);
            ps3.setString(6, endTime);
            ps3.setInt(7, CustID);
            ps3.setString(9, User.getUsername());
            ps3.setInt(8, ContactID);
            int resultado = ps3.executeUpdate();
            System.out.println("Appointment saved!");
            goBack();
    }
    
    
    private boolean checkIfRight() {
        // Check if the appointment is filled with all needed data
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationBox.getValue();
        String type = typeBox.getValue();
        String contact = contactBox.getValue();
        String customer = customerBox.getValue();
        String startH = startHourCombo.getValue();
        String startM = startMinuteCombo.getValue();
        String endH = endHourCombo.getValue();
        String endM = endMinuteCombo.getValue();
        //String date = selectDateBox.getValue();
        
        String messageError = "";
        
        if (title == null || title.length() == 0) {
            messageError += "Please enter a title.\n";
            }
        if (description == null || description.length() == 0) {
            messageError += "Please enter a description.\n";
            }
        if (location == null || location.length() == 0) {
            messageError += "Please choose a location\n"; 
        }
        if (type == null || type.length() == 0) {
            messageError += "Please choose a type\n";
            }
        if (customer == null || customer.length() == 0){
            messageError += "Please choose a customer\n";
        }
        if (contact == null || contact.length() == 0) {
            messageError += "Please choose a contact\n";
        }
        if ((startH == null || startH.length() == 0) || ( startM == null || startM.length() == 0)) {
            messageError += "Please choose a start time\n";
        }
        if ((endH == null || endH.length() == 0) || ( endM == null || endM.length() == 0)) {
            messageError += "Please chose an end time\n";
        }
        if (selectDateBox.getValue() == null) {
            messageError += "Please select a date";
        }
        if (messageError.length() == 0){
            return true;
        } else {
            // error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid appointment");
            alert.setContentText(messageError);
            Optional<ButtonType> result = alert.showAndWait();
            return false;
        }  
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
    /**
     * Calls the save appointment function
     * @param event
     * @throws Exception 
     */
    @FXML
    private void saveButtonHandler (ActionEvent event) throws Exception {
        if (checkIfRight()){
            saveAppointment();
        }
        
    }
    
    @FXML
    private void locationBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void typeBoxHandler (ActionEvent event) {
        
    }
    /**
     * Returns to main appointments screen
     * @param event
     * @throws IOException 
     */
    @FXML
    private void cancelButtonHandler (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/views/appointmentsscreen.fxml"));
        stage = (Stage) cancelButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
        
    }
    

