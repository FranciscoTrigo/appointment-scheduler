
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
import model.appointment;

/**
 * FXML Controller class
 *
 * @author Francisco Trigo
 */
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
    private TableView<appointment> appTable;
    @FXML
    private TableColumn<appointment, Integer> AppIDColumn;
    @FXML
    private TableColumn<appointment, String> TitleColumn;
    @FXML
    private TableColumn<appointment, String> DescriptionColumn;
    @FXML
    private TableColumn<appointment, String> LocationColumn;
    @FXML
    private TableColumn<appointment, Integer> ContactColumn;
    @FXML
    private TableColumn<appointment, String> TypeColumn;
    @FXML
    private TableColumn<appointment, String> StartColumn;
    @FXML
    private TableColumn<appointment, String> EndColumn;
    @FXML
    private TableColumn<appointment, Integer> CustomerIDColumn;
    
    
    private final DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //private final ZoneId localZoneID = ZoneId.of("UTC-8");
//    private final ZoneId localZoneID = ZoneId.systemDefault();
//    private final ZoneId utcZoneID = ZoneId.of("UTC");
                
    ObservableList<appointment> appointmentsOL = FXCollections.observableArrayList();
    private static appointment selectedAppointment = new appointment();
    
    
    
    public int selectedAppointmentID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        weekRadio.setToggleGroup(filterGroup);
        monthRadio.setToggleGroup(filterGroup);
        noFilterRadio.setToggleGroup(filterGroup);
        noFilterRadio.setSelected(true);
        System.out.println("User is: " + User.getUserID());
        
        // Now we set the table values to what they are supposed to be
        PropertyValueFactory<appointment, Integer> apptIDFactory = new PropertyValueFactory<>("appointmentID");
        AppIDColumn.setCellValueFactory(apptIDFactory);
        
        PropertyValueFactory<appointment, String> apptTitleFactory = new PropertyValueFactory<>("title");
        TitleColumn.setCellValueFactory(apptTitleFactory);
        
        PropertyValueFactory<appointment, String> apptDescriptionFactory = new PropertyValueFactory<>("description");
        DescriptionColumn.setCellValueFactory(apptDescriptionFactory);
        
        PropertyValueFactory<appointment, String> apptLocationFactory = new PropertyValueFactory<>("location");
        LocationColumn.setCellValueFactory(apptLocationFactory);
        
        PropertyValueFactory<appointment, Integer> apptContactFactory = new PropertyValueFactory<>("contactID");
        ContactColumn.setCellValueFactory(apptContactFactory);
        
        PropertyValueFactory<appointment, String> apptTypeFactory = new PropertyValueFactory<>("type");
        TypeColumn.setCellValueFactory(apptTypeFactory);
        
        PropertyValueFactory<appointment, String> apptStartFactory = new PropertyValueFactory<>("startTime");
        StartColumn.setCellValueFactory(apptStartFactory);
        
        PropertyValueFactory<appointment, String> apptEndFactory = new PropertyValueFactory<>("endTime");
        EndColumn.setCellValueFactory(apptEndFactory);
        
        PropertyValueFactory<appointment, Integer> apptCustomerID = new PropertyValueFactory<>("customerID");
        CustomerIDColumn.setCellValueFactory(apptCustomerID);
        
        
        
        
        try {

            
            updateApptTable();
            noFilterRadio.setSelected(true);
                    
                    // TODO
                    } catch (Exception ex) {
            Logger.getLogger(AppointmentsscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        final Label selected = new Label();
        appTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            appointment selectedApp = new appointment();
            selectedApp = newValue;
            //int polio;
            if (selectedApp == null) {
                System.out.println();
            } else{
            selectedAppointmentID = selectedApp.getAppointmentID();
            }           
 
            }
            
        
        );
    }  
    
    
    public void updateApptTable() throws SQLException, Exception {
        System.out.println("Updating Appt table");
        appointmentsOL.clear();
        Statement stmt = DBConnection.conn.createStatement();
        //PreparedStatement ps;
        //ps = DBConnection.startConnection().prepareStatement(""
        String sqlStatement = "SELECT Appointment_ID, Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID "
                + "FROM appointments";
        //ResultSet rs = ps.executeQuery();
        ResultSet rs = stmt.executeQuery(sqlStatement);
        System.out.println("SQL Worked!");
        appointmentsOL.clear();
        
        while(rs.next()) {
            
            appointment appt = new appointment();
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
        }
        //appTable.setItems(appointmentsOL);
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
    
    public void filterMonth(ObservableList appointmentsOL) {
        // WE are gonna try to filter the table by week now
        LocalDate now = LocalDate.now();
        LocalDate nextMonth = now.plusMonths(1);
        
        FilteredList<appointment> filteredData = new FilteredList<>(appointmentsOL);
        filteredData.setPredicate(row -> {
            LocalDate rowDate = LocalDate.parse(row.getStartTime(), datetimeDTF);
            return rowDate.isAfter(now.minusDays(1)) && rowDate.isBefore(nextMonth);
        }
        );
        appTable.setItems(filteredData);
    }
    
    public void filterWeek(ObservableList appointmentsOL) {
        // filer week
        LocalDate now = LocalDate.now();
        LocalDate nextWeek = now.plusWeeks(1);
        
        FilteredList<appointment> filteredData = new FilteredList<>(appointmentsOL);
        filteredData.setPredicate(row -> {
            LocalDate rowDate = LocalDate.parse(row.getStartTime(), datetimeDTF);
            return rowDate.isAfter(now.minusDays(1)) && rowDate.isBefore(nextWeek);
        });
        appTable.setItems(filteredData);
    }
    
        public static void setCurrentUser(int currentUser) {
        currentUser = currentUser;
        System.out.println("Current user is: " + currentUser);
    }
    
    public void deleteAppointment() throws SQLException, Exception{

              //System.out.println(selectedAppointmentID);
              //appTable.getSelectionModel().select(-1);
              if (selectedAppointment == null) {
                  System.out.println("Nothing selected");
              } else {
                  
              
              System.out.println("Deleting selected appointment");
              PreparedStatement ps = DBConnection.startConnection().prepareStatement(""
                + "DELETE FROM appointments "
                + "WHERE Appointment_ID = ?");
        ps.setInt(1, selectedAppointmentID);
        ps.executeUpdate();
        
        System.out.println("Deleted appointment.");
        
        updateApptTable();}

        }

    /////////////////////////////////////////////////////////////////////
    
    @FXML
    private void AddAppointmentHandler (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/addAppointment.fxml"));
        stage = (Stage) AddButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            
        
    }
    
    @FXML
    private void DeleteHandler (ActionEvent event) throws Exception {
        deleteAppointment();
        
    }
    
    @FXML
    private void UpdateHandler (ActionEvent event){
        
    }
    
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
    
    
    @FXML
    private void BackHandler (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/MainMenu.fxml"));
        stage = (Stage) BackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
    }
    
