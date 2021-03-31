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

/**
 * FXML Controller class
 *
 * @author yamif
 */
public class CustomerscreenController implements Initializable {
    
    @FXML
    private Label CustomersMainTitle;
    
    @FXML
    private TableView<Customer> CustomerTable;
    @FXML
    private TableColumn<Customer, Integer> CustomerIDColumn;
    @FXML
    private TableColumn<Customer, String> CustomerNameColumn;
    @FXML
    private TableColumn<Customer, String> CustomerPhoneColumn;
    @FXML
    private TableColumn<Customer, String> AreaColumn;
    
    @FXML
    private Label CustomerIDLabel;
    @FXML
    private Label CustomerNameLabel;
    @FXML
    private Label AddressLabel;
    @FXML
    private Label ZIPLabel;
    @FXML
    private Label PhoneLabel;
    @FXML
    private Label CountryLabel;
    @FXML
    private Label AreaLabel;
    
    @FXML
    private TextField CustomerIDField;
    @FXML
    private TextField CustomerNameField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField ZIPField;
    @FXML
    private TextField PhoneField;
    
    @FXML
    private ComboBox<String> CountryBox;
    @FXML
    private ComboBox<String> AreaBox;
    
    @FXML
    private Button AddCustomerButton;
    @FXML
    private Button DeleteCustomerButton;
    @FXML
    private Button SaveCustomerButton;
    @FXML
    private Button CancelButton;
    @FXML
    private Button BackButton;
    
    Parent root;
    Stage stage;
    
    ObservableList<Customer> customerOL = FXCollections.observableArrayList();
    ObservableList<String> areaOptions = FXCollections.observableArrayList();
    ObservableList<String> countryOptions = FXCollections.observableArrayList();
    
    private static Customer selectedCustomer = new Customer();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Try  to fill in the table with the information from the customer table in the server!
        //Name
        PropertyValueFactory<Customer, String> custNameFactory = new PropertyValueFactory<>("CustomerName");
        CustomerNameColumn.setCellValueFactory(custNameFactory);
        //Phone
        PropertyValueFactory<Customer, String> custPhoneFactory = new PropertyValueFactory<>("CustomerPhone");
        CustomerPhoneColumn.setCellValueFactory(custPhoneFactory);
        //ID cuistomer number
        PropertyValueFactory<Customer, Integer> custCustomerIDFactory = new PropertyValueFactory<>("CustomerID");
        CustomerIDColumn.setCellValueFactory(custCustomerIDFactory);
        //Customer area column
        PropertyValueFactory<Customer, String> custAreaFactory = new PropertyValueFactory<>("CustomerArea");
        AreaColumn.setCellValueFactory(custAreaFactory);
        
        
        try {
            updateCustomerTable();
            areaBoxFill();
            countryBoxFill();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hello");
        
        CustomerTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            try {
                listenCustomer(newValue);
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
                System.out.println("Error!!!!!!!! ");
            }
        });
    }  
    
    
    
    public void updateCustomerTable() throws SQLException {
        System.out.println("Updating customer table.......... .");
        customerOL.clear();
        Statement stmt = DBConnection.conn.createStatement();
        
        String sqlStatement = "SELECT Customer_ID, Customer_Name, Phone, Division FROM first_level_divisions, customers WHERE customers.Division_ID = first_level_divisions.Division_ID;";
        
        ResultSet result = stmt.executeQuery(sqlStatement);
        
        while (result.next()) {
            Customer cust = new Customer();
            cust.setCustomerID(result.getInt("Customer_ID"));
            cust.setCustomerName(result.getString("Customer_Name"));
            cust.setCustomerPhone(result.getString("Phone"));
            cust.setCustomerArea(result.getString("Division"));
            customerOL.addAll(cust);
        }
        CustomerTable.setItems(customerOL);
        
        System.out.println("Customer table updated!");
    }
    
    public void areaBoxFill() throws SQLException, Exception {
        //statement creation
        Statement stmt = DBConnection.startConnection().createStatement();
        String sqlStatement = "SELECT Division FROM first_level_divisions";
        ResultSet result = stmt.executeQuery(sqlStatement);
        
        while (result.next()) {
            AreaBox.getItems().add(result.getString("Division"));
            }
        stmt.close();
        result.close();
    }
    
    public void countryBoxFill() throws SQLException, Exception {
        //statement creation
        Statement stmt = DBConnection.startConnection().createStatement();
        String sqlStatement = "SELECT Country FROM countries";
        ResultSet result = stmt.executeQuery(sqlStatement);
        
        while (result.next()) {
            CountryBox.getItems().add(result.getString("Country"));
            }
        stmt.close();
        result.close();
    }
        
    public void listenCustomer(Customer customer) throws SQLException, Exception {
        System.out.println("Chose your customer now");
        Customer cust = new Customer();
        cust = customer;
        String custName = cust.getCustomerName();
        int custId = cust.getCustomerID();
        ObservableList<Customer> CustomerOL = FXCollections.observableArrayList();
        
//        customerUpdate = true;
//        customerAdd = false;
        //System.out.println("cas");
        PreparedStatement ps = DBConnection.startConnection().prepareStatement(
                "SELECT  Customer_ID, Customer_Name,  Address, Postal_Code, Phone, Division, Country "
                + "FROM customers, first_level_divisions, countries "
                + "WHERE customers.Customer_ID = ? "
                + "AND customers.Division_ID = first_level_divisions.Division_ID "
                + "AND first_level_divisions.Country_ID = countries.Country_ID");
        System.out.println("after sql");
        System.out.println(cust.getCustomerID());
        
        ps.setInt(1, custId);
        ResultSet result = ps.executeQuery();
        System.out.println("send quiery");
        while (result.next()) {
            CustomerIDField.setText(Integer.toString(result.getInt("Customer_ID")));
            //System.out.println(result.getString("Address"));   
            CustomerNameField.setText(result.getString("Customer_Name"));
            AddressField.setText(result.getString("Address"));
            ZIPField.setText(result.getString("Postal_Code"));
            PhoneField.setText(result.getString("Phone"));
            CountryBox.setValue(result.getString("Country"));
            AreaBox.setValue(result.getString("Division"));
                    }      
            }
    
    
    public void clearFields() {
        CustomerIDField.setText("");
        CustomerNameField.setText("");
        AddressField.setText("");
        ZIPField.setText("");
        PhoneField.setText("");
             
    }
    
    public void saveCustomer() throws Exception {
        System.out.println("SAVING CUSTOMER");
        try {
            // get Division ID
            System.out.println("Getting division ID");
            System.out.println(AreaBox.getValue());
            PreparedStatement ps1 = DBConnection.startConnection().prepareStatement("SELECT Division_ID "
                    + "FROM first_level_divisions "
                    + "WHERE Division = ?");
           // String areaName = ""; Probably not need this time
            ps1.setString(1, AreaBox.getValue());
            
            int areaID = 0;
            ResultSet result = ps1.executeQuery();
            while (result.next()) {
                areaID = result.getInt("Division_ID");
            }
            System.out.println("Area ID IS: " + areaID);
            
            // now we add customer
            PreparedStatement ps2 = DBConnection.startConnection().prepareStatement(""
                    + "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID)"
                    +                "VALUES (?, ?, ?, ?, ?)");
            ps2.setString(1, CustomerNameField.getText());
            ps2.setString(2, AddressField.getText());
            ps2.setString(3, ZIPField.getText());
            ps2.setString(4, PhoneField.getText());
            ps2.setInt(5, areaID);
            
            int resultado = ps2.executeUpdate();
        } catch (SQLException e) {
            
            System.out.println("Hola soy un error!");
            System.out.println("Error " + e.getMessage());
        }
            

    }
    
    @FXML
    private void CustomerIDFieldHandler (ActionEvent event) {
    
    }
    
    @FXML
    private void CustomerNameFieldHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void AddressFieldHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void ZIPFieldHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void PhoneFieldHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void CountryBoxHandler (ActionEvent event) {
        
    }
    
    @FXML private void AreaBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void SaveCustomerHandler (ActionEvent event) {
        try {
            ///////////
            saveCustomer();
        } catch (Exception ex) {
            Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @FXML
    private void CancelHandler (ActionEvent event){
        
    }
    
    @FXML
    private void BackHandler (ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource("/views/MainMenu.fxml"));
        stage = (Stage) BackButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
        
    }
    
    @FXML
    private void DeleteCustomerHandler (ActionEvent event){
        
    }
    
    @FXML
    private void AddCustomerHandler (ActionEvent event){
        
    }
    
}
