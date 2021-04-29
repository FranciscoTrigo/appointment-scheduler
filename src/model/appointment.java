
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates jkkjkjkjkj
 * and open the template in the editor.
 */
package model;
import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Francisco Trigo
 * This is the appointment class, it is used mostly to populate the appointment table in the appointment screen.
 * Its parameters are taken form the appointments table in the database.
 * 
 */
public class Appointment {
    
    private int appointmentID;
    private  int customerID;
    private  int userID;
    private int contactID;
    
    private String title;
    private String description;
    private String location;
    private String type;
    private String startTime;
    private String endTime; 
    private String customerName;

    
    //constructor

    /**
     *The constructor for this class
     * 
     * 
     * 
     */
    
    public Appointment() {
        appointmentID  = 0;
        customerID = 0;
        userID = 0;
        contactID = 0 ;
        
        title = null;
        description = null;
        location = null;
        type = null;
        startTime = null;
        endTime = null;
        customerName = null;
        
        
    }
    
    /**
     * This is to set up the appointment object
     * @param appointmentID AppointmentID is auto generated in the db
     * @param CustomerID CustomerID from the db
     * @param userID userid
     * @param title title from the db
     * @param description description from the db
     * @param location location form the db
     * @param type type from the db
     * @param startTime start time of the appointment
     * @param endTime end time form the db
     * @param customerName customer name of the customerID
     */
    public Appointment(int appointmentID, int CustomerID, int userID, String title, String description, String location, String type, String startTime, String endTime, String customerName){
        this.appointmentID = appointmentID;
        this.customerID= CustomerID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerName = customerName;
        
    }
//    
//    public appointment(int appointmentID, int customerID, int userID, int contactID, String title, String description, String location, String type, String startTime, String endTime) {
//        //
//        appointmentID = appointmentID;
//        customerID = customerID;
//        userID = userID;
//        contactID = contactID;
//        title = title;
//        description = description;
//        location = location;
//        type = type;
//        startTime = startTime;
//        endTime = endTime;a
//    }
    
    
    // waaa
    
    // get em

    /**
     *This is the list of getters for this class
     * They will return the different parameters of the object
     * @return the customer name, this is NOT a part of the appointments table, which only has a customer_ID, but I added customer_name to make
     * it easier to work with when filling a table, and is not a needed parameter.
     * 
     */
    
    public String getCustomerName() {
        return customerName;
    }
    
    /**
     *
     * @return appointment_ID from sql table
     */
    public  int getAppointmentID(){
        return appointmentID;
    }
    
    /**
     *
     * @return will return customer_ID from sql table
     */
    public  int getCustomerID(){
        return customerID;
    }
    
    /**
     *
     * @return will return Contact_ID from table
     */
    public  int getContactID(){
        return contactID;
    }
    
    /**
     *
     * @return will return Title value form table
     */
    public String getTitle(){
        return title;
    }
    
    /**
     *
     * @return will return Description from table
     */
    public  String getDescription(){
        return description;
    }
    
    /**
     *
     * @return will return Location form table
     */
    public  String getLocation(){
        return location;
    }
    
    /**
     *
     * @return will return Type from table
     */
    public String getType(){
        return type;
    }
    
    /**
     *
     * @return will return start Time from table in a string format
     */
    public  String getStartTime(){
        return startTime;
    }
    
    /**
     *
     * @return will return end time from table
     */
    public  String getEndTime(){
        return endTime;
    }
    
    // set them

    /** 
     * Set ups customerName parameter in the object, this parameter is not part of the appointments table, but it helps to fill some tableviews in this program
     * It is not needed for the object to work
     *
     * @param customerName Name of the customer, it comes from customers table in the DB when using Inner join
     */
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    /**
     * Sets the appointment_ID, it is auto generated by the SQL table, and we need to add it to the object to make some functions work
     * @param appointmentID ID for the appointment. This is part of the table.
     */
    public  void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
    
    /**
     * Sets the customer ID from appointments table in the db
     * @param customerID customer ID from the db
     */
    public  void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    /**
     * sets the contact id
     * @param contactID contact id for the appointment
     */
    public  void setContactID(int contactID) {
        this.contactID = contactID;
    }
    
    /**
     * Sets the appointment Title from the appointments table
     * @param title title for the appointment
     */
    public  void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Sets the appointment description from the appointments table
     * @param description description for the appointment
     */
    public  void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Sets the appointments location from the appointments table
     * @param location location for the appointment
     */
    public  void setLocation(String location){
        this.location = location;
    }
    
    /**
     * sets the appointment type from the appointments table
     * @param type appointment type
     */
    public  void setType(String type){
        this.type = type;
    }
    
    /**
     * sets the appointment start time from the appointments table
     * @param startTime start time for appointment
     */
    public  void setStartTime(String startTime){
        this.startTime = startTime;
    }
    
    /**
     * sets the appointment end time from the appointment table
     * @param endTime end time for appointment
     */
    public  void setEndTime(String endTime){
        this.endTime = endTime;
    }
}
