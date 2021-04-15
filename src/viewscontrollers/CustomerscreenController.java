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
import model.User;

/**
 * FXML Controller class
 *
 * @author Francisco Trigo
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
    @FXML
    private Button EditCustomerButton;
    
    Parent root;
    Stage stage;
    
    ObservableList<Customer> customerOL = FXCollections.observableArrayList();
    ObservableList<String> areaOptions = FXCollections.observableArrayList();
    ObservableList<String> countryOptions = FXCollections.observableArrayList();
    
    private static Customer selectedCustomer = new Customer();
    
//    Static int currentUser = 0;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        int currentUser;
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
        
            System.out.println("User name is " + User.getUsername()+ "!!! and the id is: " + User.getUserID());
        try {
            updateCustomerTable();
           // areaBoxFill();
            countryBoxFill();
            disableFields();
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
                //disableFields();
            } catch (Exception e) {
                System.out.println("Error!!!! " + e.getMessage());
               // System.out.println("Error!!!!!!!! ");
            }
        });
    }  
    
    public void deleteCustomer() throws SQLException, Exception {
        System.out.println("Deleting selected customer....");
        PreparedStatement ps = DBConnection.startConnection().prepareStatement(""
                + "DELETE FROM customers "
                + "WHERE Customer_ID = ?");
        String byeID = CustomerIDField.getText();
        System.out.println(byeID);
        ps.setString(1, byeID);
        ps.executeUpdate();
        
        System.out.println("Deleted customer.");
        
        clearFields();
        updateCustomerTable();
        disableFields();
        
        
        
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
        PreparedStatement ps;
        ps = DBConnection.startConnection().prepareStatement("SELECT Country_ID FROM countries WHERE Country = ?");
        ps.setString(1, CountryBox.getValue());
        int countryID = 0;
        ResultSet result = ps.executeQuery();
        while (result.next()) {
                countryID = result.getInt("Country_ID");
            }

        PreparedStatement ps1;
        ps1 = DBConnection.startConnection().prepareStatement("SELECT Division FROM first_level_divisions WHERE Country_ID = ?");
        ps1.setInt(1, countryID);
        ResultSet result2 = ps1.executeQuery();    
        while (result2.next()) {
            AreaBox.getItems().add(result2.getString("Division"));
            }
        AreaBox.setDisable(false);
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
        
    private void updateCustomer() throws Exception {
        System.out.println("Updating customer with ID: " + CustomerIDField.getText());
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
            ///////////////////////////
            
            PreparedStatement ps;
            ps = DBConnection.startConnection().prepareStatement(""
                    + "UPDATE customers "
                    + "SET Customer_Name = ?, "
                    + "Address = ?, "
                    + "Postal_Code = ?, "
                    + "Phone = ?, "
                    + "Division_ID = ? "
                    + "WHERE Customer_ID = ?");
            ps.setString(1,CustomerNameField.getText());
            ps.setString(2, AddressField.getText());
            ps.setString(3, ZIPField.getText());
            ps.setString(4, PhoneField.getText());
            ps.setInt(5, areaID);
            ps.setString(6, CustomerIDField.getText());
            
            ps.executeUpdate();
            System.out.println("Customer updated!");
            clearFields();
            updateCustomerTable();
            disableFields();
        } catch (SQLException e) {
            System.out.println("Hola soy un error!");
            System.out.println("Error " + e.getMessage()); 
        }   
    }
    
    public void listenCustomer(Customer customer) throws SQLException, Exception {
        System.out.println("Updating customer fields...");
        disableFields();
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
//        System.out.println("after sql");
//        System.out.println(cust.getCustomerID());
        
        ps.setInt(1, custId);
        ResultSet result = ps.executeQuery();
       // System.out.println("send quiery");
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
        System.out.println("Updated customer fields");
        disableFields();
            }
    
    public void disableFields() {
        CustomerIDField.setDisable(true);
        CustomerNameField.setDisable(true);
        AddressField.setDisable(true);
        ZIPField.setDisable(true);
        PhoneField.setDisable(true);
        AreaBox.setDisable(true);
        CountryBox.setDisable(true);
    }
    
    public void enableFields() {
        CustomerIDField.setDisable(false);
        CustomerNameField.setDisable(false);
        AddressField.setDisable(false);
        ZIPField.setDisable(false);
        PhoneField.setDisable(false);
       // AreaBox.setDisable(false);
        CountryBox.setDisable(false);
        
    }
    
    public void clearFields() {
        CustomerIDField.setText("");
        CustomerNameField.setText("");
        AddressField.setText("");
        ZIPField.setText("");
        PhoneField.setText("");
        AreaBox.setValue(null);
        CountryBox.setValue(null);
             
    }
    
    public void saveCustomer() throws Exception {
        System.out.println("SAVING CUSTOMER");
        try {
            // get Division ID
            System.out.println("Getting division ID for SQL statement");
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
            System.out.println("Customer saved!");
            clearFields();
            updateCustomerTable();
            disableFields();
        } catch (SQLException e) {
            
            System.out.println("Hola soy un error!");
            System.out.println("Error " + e.getMessage());
        }
            

    }
    
//    public static void setCurrentUser(int currentUser) {
//        currentUser = currentUser;
//        System.out.println("Current user is: " + currentUser);
//    }
//    
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
        try {
            
            areaBoxFill();
        } catch (Exception ex) {
            Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML private void AreaBoxHandler (ActionEvent event) {
        
    }
    
    @FXML
    private void SaveCustomerHandler (ActionEvent event) {
      if ( CustomerIDField.getText().equals("")) {
          System.out.println("Saving New Customer");
          try {
              // System.out.println(CustomerIDField.getText());
              saveCustomer();
          } catch (Exception ex) {
              Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
          }
         System.out.println("Saved customer");
          
      } else {
          //System.out.println(CustomerIDField.getText());
          System.out.println("Updating customer");
          try {
              updateCustomer();
              System.out.println("Customer updated");
          } catch (Exception ex) {
              Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

        }
    
    
    @FXML
    private void CancelHandler (ActionEvent event){
        clearFields();
        disableFields();
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
    private void DeleteCustomerHandler (ActionEvent event) {
        try {
            deleteCustomer();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            Logger.getLogger(CustomerscreenController.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    @FXML
    private void AddCustomerHandler (ActionEvent event){
        enableFields();
        clearFields();
        
    }
    
    @FXML
    private void EditCustomerHandler (ActionEvent event) {
        enableFields();
        
    }
    
}
