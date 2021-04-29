
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
 * @author Francisco Trigo
 */

// mostly just a table to see appointments
// can be filtered to show appointmets whithin next week, month, or no filter

public class AppointmentsscreenController implements Initializable {
    Parent root;
    Stage stage;
    //int currentUser;
    
    int currentAppointment = 0;
    
    private boolean isWeek;
    private boolean noFilter;
    
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
    
    
    final ToggleGroup filterGroup = new ToggleGroup();
    
    @FXML
    private RadioButton weekRadio;
    @FXML
    private RadioButton monthRadio;
    @FXML
    private RadioButton noFilterRadio;
    
    
    
    
    @FXML
    private TableView<Appointment> appTable;
    @FXML
    private TableColumn<Appointment, Integer> AppIDColumn;
    @FXML
    private TableColumn<Appointment, String> TitleColumn;
    @FXML
    private TableColumn<Appointment, String> DescriptionColumn;
    @FXML
    private TableColumn<Appointment, String> LocationColumn;
    @FXML
    private TableColumn<Appointment, Integer> ContactColumn;
    @FXML
    private TableColumn<Appointment, String> TypeColumn;
    @FXML
    private TableColumn<Appointment, String> StartColumn;
    @FXML
    private TableColumn<Appointment, String> EndColumn;
    @FXML
    private TableColumn<Appointment, Integer> CustomerIDColumn;
    
    
    private final DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                
    ObservableList<Appointment> appointmentsOL = FXCollections.observableArrayList();
    
    private static Appointment selectedAppointment = new Appointment();
    
    /**
     * Used to grab and save the appointment id of the selected appointment in the table
     */
    public int selectedAppointmentID;
    public String selectedAppointmentType;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // i set the radio button in a group so that only one can be selected at the time
        // and try to set noFilter as a default, but seems like it does not work??! 
        
        weekRadio.setToggleGroup(filterGroup);
        monthRadio.setToggleGroup(filterGroup);
        noFilterRadio.setToggleGroup(filterGroup);
        noFilterRadio.setSelected(true);
        //System.out.println("User is: " + User.getUserID());
        
        // Now we set the table values to what they are supposed to be
        PropertyValueFactory<Appointment, Integer> apptIDFactory = new PropertyValueFactory<>("appointmentID");
        AppIDColumn.setCellValueFactory(apptIDFactory);
        
        PropertyValueFactory<Appointment, String> apptTitleFactory = new PropertyValueFactory<>("title");
        TitleColumn.setCellValueFactory(apptTitleFactory);
        
        PropertyValueFactory<Appointment, String> apptDescriptionFactory = new PropertyValueFactory<>("description");
        DescriptionColumn.setCellValueFactory(apptDescriptionFactory);
        
        PropertyValueFactory<Appointment, String> apptLocationFactory = new PropertyValueFactory<>("location");
        LocationColumn.setCellValueFactory(apptLocationFactory);
        
        PropertyValueFactory<Appointment, Integer> apptContactFactory = new PropertyValueFactory<>("contactID");
        ContactColumn.setCellValueFactory(apptContactFactory);
        
        PropertyValueFactory<Appointment, String> apptTypeFactory = new PropertyValueFactory<>("type");
        TypeColumn.setCellValueFactory(apptTypeFactory);
        
        PropertyValueFactory<Appointment, String> apptStartFactory = new PropertyValueFactory<>("startTime");
        StartColumn.setCellValueFactory(apptStartFactory);
        
        PropertyValueFactory<Appointment, String> apptEndFactory = new PropertyValueFactory<>("endTime");
        EndColumn.setCellValueFactory(apptEndFactory);
        
        PropertyValueFactory<Appointment, Integer> apptCustomerID = new PropertyValueFactory<>("customerID");
        CustomerIDColumn.setCellValueFactory(apptCustomerID);
 
        
        try {

            
            updateApptTable();
            UpdateButton.setDisable(true);
            //noFilterRadio.setSelected(true);
                    
                    // TODO
                    } catch (Exception ex) {
            Logger.getLogger(AppointmentsscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // this part looks for the selected row and do something with it. saves appointment id to a variable
        
        appTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Appointment selectedApp = new Appointment();
            selectedApp = newValue;
            if (selectedApp == null) {
                System.out.println();
            } else {
            // When selected, it puts the appt ID to a variable for later deletion, and also puts info into the dummy object for editing
            selectedAppointmentID = selectedApp.getAppointmentID();
            selectedAppointmentType = selectedApp.getType();
            Dummy.setAppointmentID(selectedAppointmentID);
            Dummy.setContactID(selectedApp.getContactID());
            Dummy.setCustomerID(selectedApp.getCustomerID());
            UpdateButton.setDisable(false);
            }           
            }
        );
    } 
    
    /**
     * Used to update the tableview with the latest information from the appointments table.
     * IT will then filter them by week, month, or no filter depending on the selection
     * @throws SQLException --
     * @throws Exception --
     */
    public void updateApptTable() throws SQLException, Exception {
        // Grabs all the info from sql appointment table and puts it into the table
        System.out.println("Updating Appt table");
        appointmentsOL.clear();
        Statement stmt = DBConnection.conn.createStatement();
        String sqlStatement = "SELECT Appointment_ID, Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID "
                + "FROM appointments";
        ResultSet rs = stmt.executeQuery(sqlStatement);
        System.out.println("SQL Worked!");
        
        while(rs.next()) {
            // Set things from sql result
            Appointment appt = new Appointment();
            appt.setAppointmentID(rs.getInt("Appointment_ID"));
            appt.setContactID(rs.getInt("Contact_ID"));
            appt.setTitle(rs.getString("Title"));
            appt.setDescription(rs.getString("Description"));
            appt.setLocation(rs.getString("Location"));
            appt.setType(rs.getString("Type"));
            appt.setStartTime(rs.getString("Start"));
            appt.setEndTime(rs.getString("End"));
            appt.setCustomerID(rs.getInt("Customer_ID"));
            
            appointmentsOL.addAll(appt);
           // System.out.println(appt.getAppointmentID() + " " + appt.getContactID() + " " + appt.getTitle());

        }
        appTable.setItems(appointmentsOL);
        System.out.println(appointmentsOL.sorted());
        //looks at what filter is selected and applies it
        if (isWeek == true && noFilter == false) {
            System.out.println("Week filter");
            filterWeek(appointmentsOL);
                    } else if (noFilter == true && isWeek == false){
                            System.out.println("No filter");
                            appTable.setItems(appointmentsOL);
                            }
                        else if (isWeek == false && noFilter == false){
            System.out.println("Month filter");
            filterMonth(appointmentsOL);
        }
    }
    
    /**
     * Filters the appointments by within a month
     * @param appointmentsOL
     */
    public void filterMonth(ObservableList appointmentsOL) {
        // WE are gonna try to filter the table by week now
        LocalDate now = LocalDate.now();
        LocalDate nextMonth = now.plusMonths(1);
        
        FilteredList<Appointment> filteredData = new FilteredList<>(appointmentsOL);
        filteredData.setPredicate(row -> {
            LocalDate rowDate = LocalDate.parse(row.getStartTime(), datetimeDTF);
            return rowDate.isAfter(now.minusDays(1)) && rowDate.isBefore(nextMonth);
        }
        );
        appTable.setItems(filteredData);
    }
    
    /**
     * Filters appointments by within a week
     * @param appointmentsOL --
     */
    public void filterWeek(ObservableList appointmentsOL) {
        // filer week
        LocalDate now = LocalDate.now();
        LocalDate nextWeek = now.plusWeeks(1);
        
        FilteredList<Appointment> filteredData = new FilteredList<>(appointmentsOL);
        filteredData.setPredicate(row -> {
            LocalDate rowDate = LocalDate.parse(row.getStartTime(), datetimeDTF);
            return rowDate.isAfter(now.minusDays(1)) && rowDate.isBefore(nextWeek);
        });
        appTable.setItems(filteredData);
    }
    
    /**
     * Deletes selected appointment from appointment table view.
     * It uses the saved appointment_ID variable
     * If nothing is selected, it will print to console
     * @throws SQLException --
     * @throws Exception --
     */
    public void deleteAppointment() throws SQLException, Exception{

              System.out.println(selectedAppointmentID);
              appTable.getSelectionModel().select(-1);
              if (selectedAppointment == null) {
                  System.out.println("Nothing selected");
              } else {
                  
              
              System.out.println("Deleting selected appointment");
              PreparedStatement ps = DBConnection.getConnection().prepareStatement(""
                + "DELETE FROM appointments "
                + "WHERE Appointment_ID = ?");
        ps.setInt(1, selectedAppointmentID);
        ps.executeUpdate();
        
        System.out.println("Deleted appointment.");
        
        updateApptTable();}

        }

    /////////////////////////////////////////////////////////////////////
    /**
     * Calls out the add appointment screen
     * @param event
     * @throws IOException 
     */
    @FXML
    private void AddAppointmentHandler (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/addAppointment.fxml"));
        stage = (Stage) AddButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            
        
    }
    /**
     * calls the delete appointment function
     * @param event
     * @throws Exception 
     */
    @FXML
    private void DeleteHandler (ActionEvent event) throws Exception {
        //deleteAppointment();
        
       var alert = new Alert(AlertType.CONFIRMATION);
       alert.setTitle("Confirm Delete");
       alert.setHeaderText("Please confirm or cancel");
       alert.setContentText("Delete the selected appointment with ID number: \n"
               + selectedAppointmentID + ", which is a " + selectedAppointmentType + "?");
       alert.showAndWait().ifPresent((btnType) -> {
           if (btnType == ButtonType.OK) {
               System.out.println("DELETE");
               try {
                   deleteAppointment();
               } catch (Exception ex) {
                   Logger.getLogger(AppointmentsscreenController.class.getName()).log(Level.SEVERE, null, ex);
               }
               } else { 
               System.out.println("Deletion aborted!");}
           });
       };
        

    
    
    /**
     * Calls the update appointment view, only when an appointment is selected
    */
    @FXML
    private void UpdateHandler (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/UpdateAppointment.fxml"));
        stage = (Stage) AddButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        
    }
    /**
     * Applies the by week filter
     * @param event 
     */
    @FXML
    private void weekRadioHandler (ActionEvent event){
        isWeek = true;
        noFilter = false;
        try {
            updateApptTable();
        } catch (Exception ex) {
            Logger.getLogger(AppointmentsscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * applies the by month filter
     * @param event 
     */
    @FXML
    private void monthRadioHandler (ActionEvent event) {
        isWeek = false;
        noFilter = false;
        try {
            updateApptTable();
        } catch (Exception ex) {
            Logger.getLogger(AppointmentsscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * applies the no filter filter
     * @param event 
     */
    @FXML
    private void noFilterRadioHandler (ActionEvent event) {
        isWeek = false;
        noFilter = true;
        try {
            updateApptTable();
        } catch (Exception ex) {
            Logger.getLogger(AppointmentsscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * Goes back to previous screen, the main menu
     * @param event
     * @throws IOException 
     */
    
    @FXML
    private void BackHandler (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/MainMenu.fxml"));
        stage = (Stage) BackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
    }
    
