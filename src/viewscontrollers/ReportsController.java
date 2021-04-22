/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewscontrollers;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
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
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Tab;
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
import model.Cita;
import model.Dummy;

/**
 * FXML Controller class
 *
 * @author yamif
 */


public class ReportsController implements Initializable {
    Parent root;
    Stage stage;
    @FXML
    private Tab contactTabController;
    @FXML
    private ComboBox<String> contactBox;
    @FXML
    private TableView<Appointment> contactTable;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, String > TitleColumn;
    @FXML
    private TableColumn<Appointment, String> TypeColumn;
    @FXML
    private TableColumn<Appointment, String> DescriptionColumn;
    @FXML
    private TableColumn<Appointment, String> startColumn;
    @FXML
    private TableColumn<Appointment, String> endColumn;
    @FXML
    private TableColumn<Appointment, Integer> customerColumn;
    
    @FXML
    private TableView<Cita> typeNumberTable;
    @FXML
    private TableColumn<Cita, String> typeNumberColumn;
    @FXML
    private TableColumn<Cita, Integer> numberNumberColumn;
    
    @FXML
    private TableView<Cita> monthNumberTable;
    @FXML
    private TableColumn<Cita, String>  monthNumberColumn;
    @FXML
    private TableColumn<Cita, Integer> numberMonthColumn;
    
    @FXML
    private TableView<Cita> locationTable;
    @FXML
    private TableColumn<Cita, String> locationReportColumn;
    @FXML 
    private TableColumn<Cita, Integer> locationManyColumn;
    
    
    @FXML
    private Tab ownTab;
    @FXML
    private Tab MonthTab;
    @FXML
    private Button backButton;
    
    private static int contactID;
    
    ObservableList<Appointment> report1OL = FXCollections.observableArrayList();
    ObservableList<Cita> monthOL = FXCollections.observableArrayList();
    ObservableList<Cita> typeOL = FXCollections.observableArrayList();
    ObservableList<Cita> locationOL = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Set up the table for the first report
        PropertyValueFactory<Appointment, Integer> appointmentIDFactory = new PropertyValueFactory<>("appointmentID");
        AppointmentIDColumn.setCellValueFactory(appointmentIDFactory);
        
        PropertyValueFactory<Appointment, String> appointmentTitleFactory = new PropertyValueFactory<>("Title");
        TitleColumn.setCellValueFactory(appointmentTitleFactory);
        
        PropertyValueFactory<Appointment, String> appointmentTypeFactory = new PropertyValueFactory<>("Title");
        TypeColumn.setCellValueFactory(appointmentTypeFactory);
        
        PropertyValueFactory<Appointment, String> appointmentDescriptionFactory = new PropertyValueFactory<>("description");
        DescriptionColumn.setCellValueFactory(appointmentDescriptionFactory);
        
        PropertyValueFactory<Appointment, String> appoinmentStartFactory = new PropertyValueFactory<>("startTime");
        startColumn.setCellValueFactory(appoinmentStartFactory);
        
        PropertyValueFactory<Appointment, String> appointmentEnd = new PropertyValueFactory<>("endTime");
        endColumn.setCellValueFactory(appointmentEnd);
        
        PropertyValueFactory<Appointment, Integer> appointmentCustomerFactory = new PropertyValueFactory<>("CustomerID");
        customerColumn.setCellValueFactory(appointmentCustomerFactory);
                    //////////////////////////////////////
            //////////////////////////////////////////////////
            /////////////////////////////////
            
        PropertyValueFactory<Cita, String> typeFactory = new PropertyValueFactory<>("type");
        PropertyValueFactory<Cita, Integer> manyTypeFactory = new PropertyValueFactory<>("manyType");
        typeNumberColumn.setCellValueFactory(typeFactory);
        numberNumberColumn.setCellValueFactory(manyTypeFactory);
        
        PropertyValueFactory<Cita, String> monthFactory = new PropertyValueFactory<>("month");
        PropertyValueFactory<Cita, Integer> manyMonthFactory = new PropertyValueFactory<>("manyMonth");
        monthNumberColumn.setCellValueFactory(monthFactory);
        numberMonthColumn.setCellValueFactory(manyMonthFactory);
        
        PropertyValueFactory<Cita, String> locationFactory = new PropertyValueFactory<>("location");
        PropertyValueFactory<Cita, Integer> manyLocationFactory = new PropertyValueFactory<>("manyLocation");
        locationReportColumn.setCellValueFactory(locationFactory);
        locationManyColumn.setCellValueFactory(manyLocationFactory);
        
        
            
        try {


            contactBoxFill();
            fillOwnTable();
            fillSecondTabTable();
        } catch (Exception ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    public void contactBoxFill() throws SQLException, Exception {
        // Fill the contactBox to display only appoinrments for that contact!!!
        PreparedStatement ps;
        ps = DBConnection.getConnection().prepareStatement("SELECT Contact_Name from contacts");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            contactBox.getItems().add(result.getString("Contact_Name"));
        }
    }
    
    public void getContactID() throws SQLException, Exception {
        String contactName = contactBox.getValue();
        PreparedStatement ps;
        ps = DBConnection.getConnection().prepareStatement("SELECT Contact_ID from contacts WHERE Contact_NAME = ?");
        ps.setString(1, contactName);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            contactID = result.getInt("Contact_ID");
        }
        updateReportTable(contactID);
    }
    
    public void updateReportTable(int contactID) throws SQLException, Exception {
        System.out.println("Update report table");
        report1OL.clear();
        PreparedStatement ps;
        ps = DBConnection.getConnection().prepareStatement("SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID "
                + "FROM appointments "
                + "WHERE Contact_ID = ?");
        ps.setInt(1, contactID);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointmentID(result.getInt("Appointment_ID"));
            appointment.setTitle(result.getString("Title"));
            appointment.setType(result.getString("Type"));
            appointment.setDescription(result.getString("Description"));
            appointment.setStartTime(result.getString("Start"));
            appointment.setEndTime(result.getString("End"));
            appointment.setCustomerID(result.getInt("Customer_ID"));
            report1OL.addAll(appointment);                   
        }
        contactTable.setItems(report1OL);
        System.out.println("Updated table");
    }
    
    
    public void fillSecondTabTable() throws SQLException, Exception {
        monthOL.clear();
        typeOL.clear();
        
        PreparedStatement ps;
        ps = DBConnection.getConnection().prepareStatement("SELECT Type, count(*) FROM appointments group by Type");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Cita cita = new Cita();
            cita.setType(result.getString("Type"));
            cita.setManyType(result.getInt("Count(*)"));
            typeOL.addAll(cita);
        }
        typeNumberTable.setItems(typeOL);
//        select Type, count(*)
//FROM appointments
//group by Type

//select substr(Start, 6,2) as Start, count(*)
//from appointments
//GROUP BY substr(Start, 6, 2)
      PreparedStatement ps1;
      ps1 = DBConnection.getConnection().prepareStatement("SELECT substr(Start, 6, 2) as Month, count(*) "
              + "FROM appointments "
              + "GROUP BY substr(Start, 6, 2)");
      ResultSet result1 = ps1.executeQuery();
      while (result1.next()) {

          Cita cita = new Cita();
          cita.setMonth(result1.getString("Month"));
          cita.setManyMonth(result1.getInt("Count(*)"));
          monthOL.addAll(cita);           
      }
      monthNumberTable.setItems(monthOL);   
      System.out.println("Updated tables");
    }
    
    public void fillOwnTable() throws SQLException, Exception {
        locationOL.clear();
        PreparedStatement ps2;
        ps2=DBConnection.getConnection().prepareStatement("SELECT Location, count(*) FROM appointments group by Location");
        ResultSet result2 = ps2.executeQuery();
        while (result2.next()) {
            Cita cita = new Cita();
            cita.setLocation(result2.getString("Location"));
            cita.setManyLocation(result2.getInt("Count(*)"));
            locationOL.addAll(cita);
        }
        locationTable.setItems(locationOL);
    }
    
    @FXML
    private void contactBoxController(ActionEvent event) throws Exception {
        getContactID();
    }
    
        @FXML
    private void backbuttonHandler (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/MainMenu.fxml"));
        stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
            }
    
}
