
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
    
    private static int appointmentID;
    private static int customerID;
    private static int userID;
    private static int contactID;
    
    private static String title;
    private static String description;
    private static String location;
    private static String type;
    private static String startTime;
    private static String endTime; 
    
    //constructor
    
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
    
    public static int getAppointmentID(){
        return appointmentID;
    }
    
    public static int getCustomerID(){
        return customerID;
    }
    
    public static int getContactID(){
        return contactID;
    }
    
    public static String getTitle(){
        return title;
    }
    
    public static String getDescription(){
        return description;
    }
    
    public static String getLocation(){
        return location;
    }
    
    public static String getType(){
        return type;
    }
    
    public static String getStartTime(){
        return startTime;
    }
    
    public static String getEndTime(){
        return endTime;
    }
    
    // set them
    
    public static void setAppointmentID(int appointmentID) {
        Appointment.appointmentID = appointmentID;
    }
    
    public static void setCustomerID(int customerID) {
        Appointment.customerID = customerID;
    }
    
    public static void setContactID(int contactID) {
        Appointment.contactID = contactID;
    }
    
    public static void setTitle(String title) {
        Appointment.title = title;
    }
    
    public static void setDescription(String description){
        Appointment.description = description;
    }
    
    public static void setLocation(String location){
        Appointment.location = location;
    }
    
    public static void setType(String type){
        Appointment.type = type;
    }
    
    public static void setStartTime(String startTime){
        Appointment.startTime = startTime;
    }
    
    public static void setEndTime(String endTime){
        Appointment.endTime = endTime;
    }
}
