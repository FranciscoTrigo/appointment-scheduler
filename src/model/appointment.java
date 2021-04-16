
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
 */
public class Appointment {
    
    private int appointmentID;
    private int customerID;
    private int userID;
    private int contactID;
    
    private static String title;
    private String description;
    private String location;
    private static String type;
    private String startTime;
    private String endTime; 
    
    //constructor
    
    public Appointment() {
        appointmentID  = 0;
        customerID = 0;
        userID = 0;
        contactID =0 ;
        
        title = null;
        description = null;
        location = null;
        type = null;
        startTime = null;
        endTime = null;
        
    }
    
    public Appointment(int appointmentID, int CustomerID, int userID, String title, String description, String location, String type, String startTime, String endTime){
        this.appointmentID = appointmentID;
        this.customerID= CustomerID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        
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
//        endTime = endTime;
//    }
    
    // get em
    
    public int getAppointmentID(){
        return appointmentID;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
    public int getContactID(){
        return contactID;
    }
    
    public static String getTitle(){
        return title;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getLocation(){
        return location;
    }
    
    public static String getType(){
        return type;
    }
    
    public String getStartTime(){
        return startTime;
    }
    
    public String getEndTime(){
        return endTime;
    }
    
    // set them
    
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
}
