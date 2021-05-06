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
    private ComboBox<String> userBox;
   
    
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
            fillUserBox();
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
    
        public void fillUserBox() throws SQLException, Exception {
        Statement stmt = DBConnection.getConnection().createStatement();
        String sqlStatement = "SELECT User_Name FROM users";
        ResultSet result = stmt.executeQuery(sqlStatement);
        while (result.next()) {
            userBox.getItems().add(result.getString("User_Name"));
        }
        stmt.close();
        result.close();
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
    
        public boolean checkHours() {
        String startH = startHourCombo.getValue();
        String startM = startMinuteCombo.getValue();
        String endH = endHourCombo.getValue();
        String endM = endMinuteCombo.getValue();
        int startHInt = Integer.parseInt(startH);
        int endHInt = Integer.parseInt(endH);
        String messageError = "";
        
      if  (Integer.parseInt(startH) < 8 || Integer.parseInt(startH) >= 22 || (Integer.parseInt(endH) > 22) || endHInt < 8) {
          messageError += "Appointment must start and end between\n 8 and 10 P.M (22 hours)";
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
    /**
     * Gets the information for the selected appointment and populates it onto the fields for latter edition
     * @throws SQLException --
     * @throws Exception  --
     */
    public void getAppointmentInfo() throws SQLException, Exception {
        // Fill uout the information to latter edit it
        int apptUserID = 0;
        PreparedStatement ps01 = DBConnection.getConnection().prepareStatement("Select "
                + "User_ID "
                + "FROM appointments "
                + "WHERE Appointment_ID = ?");
        ps01.setInt(1, Dummy.getAppointmentID());
        ResultSet result01 = ps01.executeQuery();
        while (result01.next()) {
            apptUserID = result01.getInt("User_ID");
        }
        
        
        String apptUser = "";
        PreparedStatement ps00 = DBConnection.getConnection().prepareStatement("SELECT "
                + "User_Name "
                + "FROM users "
                + "WHERE User_ID = ?");
        ps00.setInt(1, apptUserID);
        ResultSet result00 = ps00.executeQuery();
        while (result00.next()) {
            apptUser = result00.getString("User_Name");
        }

        
        // get customer_name ...
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
        // Get Contact name
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
        String thedateUTC = "";
        String thedate2UTC = "";
        while (result.next()) {
            titleField.setText(result.getString("Title"));
            descriptionField.setText(result.getString("Description"));
            locationBox.setValue(result.getString("Location"));
            typeBox.setValue(result.getString("Type"));
            thedateUTC = result.getString("Start");
            thedate2UTC = result.getString("End");
            
            customerBox.setValue(apptCustomer);
            contactBox.setValue(apptContact);
            userBox.setValue(apptUser);
            appointmentTextField.setText(Integer.toString(Dummy.getAppointmentID()));
   
        }
        
        String thedate = utils.timeConvert.toLocal(thedateUTC);
        String thedate2 = utils.timeConvert.toLocal(thedate2UTC);
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
        
              //Get user ID
        PreparedStatement ps5 = DBConnection.getConnection().prepareStatement("SELECT User_ID "
                + "FROM users "
                + "WHERE User_Name = ?");
        ps5.setString(1, userBox.getValue());
        int usID = 0;
        ResultSet result5 = ps5.executeQuery();
        while (result5.next()) {
            usID = result5.getInt("User_ID");
        }
        
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
        String startUTC = utils.timeConvert.toUTC(startTime);
        
        
        // END
        String endHH = endHourCombo.getValue();
        String endMM = endMinuteCombo.getValue();
        String endHour = endHH + ":" + endMM + ":00";
        //System.out.println(endHour);
        String endTime = selectDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + endHour;
        // System.out.println(endTime);
        String endUTC = utils.timeConvert.toUTC(endTime);
        
        
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
                    + "Last_Updated_By = ?, "
                    + "User_ID = ? "
                    + "WHERE Appointment_ID = ?"
                    );
            ps3.setString(1, titleField.getText());
            ps3.setString(2, descriptionField.getText());
            ps3.setString(3, locationBox.getValue());
            ps3.setString(4, typeBox.getValue());
            ps3.setString(5, startUTC);
            ps3.setString(6, endUTC);
            ps3.setInt(7, CustID);
            ps3.setString(9, User.getUsername());
            ps3.setInt(8, ContactID);
            ps3.setInt(11, Dummy.getAppointmentID());
            ps3.setInt(10, usID);
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
        String IDuser = userBox.getValue();
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
            messageError += "Please select a date\n";
        }
                        if (IDuser == null || IDuser.length() == 0) {
            messageError += "Please select an user. \n";
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
        
        /**
     * Checks if this new appointment has a conflict with an existing one, time wise
     * @return true if conflict with existing appointment
     */
    public boolean checkIfConflict() throws SQLException {
        //create strings to check
        String thisDate = selectDateBox.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String thisStart = startHourCombo.getValue() + ":" + startMinuteCombo.getValue() + ":00";
        String almostCheckThis = thisDate + " " + thisStart;
        String checkThisStart = utils.timeConvert.toUTC(almostCheckThis);
        
        String thisEnd = endHourCombo.getValue() + ":" + startMinuteCombo.getValue() + ":00";
        String checkThisEnd = utils.timeConvert.toUTC(thisDate + " " + thisEnd);
        
        System.out.println("Conflict? " + checkThisStart);
        String testTitle = "no";
        String Titletest = "no";
   
        
        String contactID = "";
        
        // Getting Contact ID
        PreparedStatement ps2 = DBConnection.getConnection().prepareStatement("SELECT * "
                + "FROM contacts "
                + "WHERE Contact_Name = ?");
        ps2.setString(1, contactBox.getValue());
        ResultSet result1 = ps2.executeQuery();
            while (result1.next()) {
                contactID = result1.getString("Contact_ID");
            }
            
        // Now we grab all appointments for that one contact ID and compare it
        PreparedStatement ps3 = DBConnection.getConnection().prepareStatement("SELECT * "
                + "FROM appointments "
                + "WHERE (? BETWEEN Start AND End AND Contact_ID = ?) OR (Start BETWEEN ? AND ? AND Contact_ID = ?)");
        ps3.setString(1, checkThisStart);
        ps3.setString(2, contactID);
        ps3.setString(3, checkThisStart);
        ps3.setString(4, checkThisEnd);
        ps3.setString(5, contactID);
        ResultSet result = ps3.executeQuery();
        while (result.next()) {
            testTitle = result.getString("Start");
            Titletest = result.getString("End");
            System.out.println(result.getString("Start"));
        }      
            
        
        if (testTitle != "no") {
            System.out.println("There seems to be scheduling conflicts " + testTitle);
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Schedulling error");
            alert.setHeaderText("This appointment can not happen");
            alert.setContentText("Looks like " + contactBox.getValue() + " already has\nan appointment "
                    + "booked from\n " + utils.timeConvert.toLocal(testTitle) +" to " + utils.timeConvert.toLocal(Titletest)
                    + "\nPlease select another time or date.");
            Optional<ButtonType> result9 = alert.showAndWait();
            return false;
        }
        System.out.println("No conflict");
        return true;
    }
    
     /**
     * Calls the save appointment function
     * @param event
     * @throws Exception 
     */
    @FXML
    private void saveButtonHandler (ActionEvent event) throws Exception {
        if (checkIfRight()) {
            if ( checkHours()) {
                if (checkIfConflict()){
                    saveAppointment();
                }
            }
        }
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
    

