
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
 * @author yamif
 */
public class appointment {
    
    private int appointmentID;
    private int customerID;
    private int userID;
    private int contactID;
    
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String startTime;
    private String endTime; 
    
    
    public appointment(int appointmentID, int customerID, int userID, int contactID, String title, String description, String location, String contact, String type, String startTime, String endTime) {
        //
        appointmentID = appointmentID;
        customerID = customerID;
        userID = userID;
        contactID = contactID;
        title = title;
        description = description;
        location = location;
        contact = contact;
        type = type;
        startTime = startTime;
        endTime = endTime;
    }
    
    
}