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
import model.Dummy;

/**
 * FXML Controller class
 *
 * @author yamif
 */




public class UpdateAppointmentController implements Initializable {
    
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
    private TextField appointmentTextField;
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
     * @param url url
     * @param rb resource
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(User.getUserID());
        //System.out.println("ppointment id: " + appointment.getTitle());
        try {
            fillTimeBoxes();
            fillLocationBox();
            fillTypeBox();
            fillCustomerBox();
            fillContactBox();
            getAppointmentInfo();
            // TODO
        } catch (Exception ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     *
     * @param dateString its the date
     * @return 
     */
    public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
    /**
     * Fill out the comboBoxes to select start and end time
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
     * Gets the information for the selected appointment and populates it onto the fields for latter edition
     * @throws SQLException --
     * @throws Exception  --
     */
    public void getAppointmentInfo() throws SQLException, Exception {
        // Fill uout the information to latter edit it
        
        // get customer_id ...
        String apptCustomer = "";
        PreparedStatement ps0 = DBConnection.getConnection().prepareStatement("SELECT "
                + "Customer_Name "
                + "FROM customers "
                + "WHERE Customer_ID = ? ");
        ps0.setInt(1, Dummy.getCustomerID());
        ResultSet result0 = ps0.executeQuery();
        while (result0.next()) {
            apptCustomer = result0.getString("Customer_Name");
        }
        
        /////////////////
        // Get Contact ID
        String apptContact = "";
        PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("SELECT "
                + "Contact_Name "
                + "FROM contacts "
                + "WHERE Contact_ID = ? ");
        ps1.setInt(1, Dummy.getContactID());
        ResultSet result1 = ps1.executeQuery();
        while (result1.next()) {
            apptContact = result1.getString("Contact_Name");
        }
        
        
        System.out.println("Gathering information...");
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("SELECT "
                + "Title, Description, Location, Type, Start, End, Customer_ID, User_ID, "
                + "Contact_ID "
                + "FROM appointments "
                + "WHERE Appointment_ID = ? ");
//                + "AND customers.Customer_ID = ? "
//                + "AND contacts.Contact_ID = ? ");
        ps.setInt(1, Dummy.getAppointmentID());
//        ps.setString(2, apptCustomer);
//        ps.setString(3, apptContact);
        ResultSet result = ps.executeQuery();
        String thedate = "";
        String thedate2 = "";
        while (result.next()) {
            titleField.setText(result.getString("Title"));
            descriptionField.setText(result.getString("Description"));
            locationBox.setValue(result.getString("Location"));
            typeBox.setValue(result.getString("Type"));
            thedate = result.getString("Start");
            thedate2 = result.getString("End");
            customerBox.setValue(apptCustomer);
            contactBox.setValue(apptContact);
            appointmentTextField.setText(Integer.toString(Dummy.getAppointmentID()));
   
        }
        // 1029-09-09 hh:mm:ss
        //Fill out the datepicker
        String year = thedate.substring(0,10);
        selectDateBox.setValue(LOCAL_DATE(year));
        //fill start hour
        String startHora = thedate.substring(11, 13);
        startHourCombo.setValue(startHora);
        startHora = thedate.substring(14,16);
        startMinuteCombo.setValue(startHora);
        //fil end hour
        String endHora = thedate2.substring(11, 13);
        endHourCombo.setValue(endHora);
        endHora = thedate2.substring(14, 16);
        endMinuteCombo.setValue(endHora);
        
        
        
    }
    /**
     * goes back
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
     * Fills the location information 
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
     * Fills out the type options
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
    * Fills out the contact options and information form the db
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
     * Fills out the customer information options from the db
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
     * Saves(updates) current appointment with the information currently entered into the fields
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
                    + "UPDATE appointments "
                    + "SET Title = ?,"
                    + " Description = ?,"
                    + " Location = ?,"
                    + " Type = ?,"
                    + " Start = ?, "
                    + "End = ?, "
                    + "Customer_ID = ?,"
                    + " Contact_ID = ?, "
                    + "Last_Updated_By = ? "
                    + "WHERE Appointment_ID = ?"
                    );
            ps3.setString(1, titleField.getText());
            ps3.setString(2, descriptionField.getText());
            ps3.setString(3, locationBox.getValue());
            ps3.setString(4, typeBox.getValue());
            ps3.setString(5, startTime);
            ps3.setString(6, endTime);
            ps3.setInt(7, CustID);
            ps3.setString(9, User.getUsername());
            ps3.setInt(8, ContactID);
            ps3.setInt(10, Dummy.getAppointmentID());
            int resultado = ps3.executeUpdate();
            System.out.println("Appointment updated!");
            goBack();
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
     * saves
     * @param event --
     * @throws Exception --
     */
    @FXML
    private void saveButtonHandler (ActionEvent event) throws Exception {
        saveAppointment();
        
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
    

