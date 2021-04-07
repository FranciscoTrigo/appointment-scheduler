
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class appointment {
    
    private int appointmentID;
    private int customerID;
    private int userID;
    private int contactID;
    
    private String title;
    private String description;
    private String location;
    private String type;
    private String startTime;
    private String endTime; 
    
    //constructor
    
    public appointment() {
        
    }
    
    public appointment(int appointmentID, int CustomerID, int userID, String title, String description, String location, String type, String startTime, String endTime){
        
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
    
    public int getApptID(){
        return appointmentID;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
    public int getContactID(){
        return contactID;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getLocation(){
        return location;
    }
    
    public String getType(){
        return type;
    }
    
    public String getStartTime(){
        return startTime;
    }
    
    public String getEndTime(){
        return endTime;
    }
    
    // set them
    
    public void setApptID(int appointmentID) {
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
