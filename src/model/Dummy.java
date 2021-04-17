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

//class is used to store current user data
public class Dummy {

    private static int appointmentID;
    private static int customerID;
    private static int contactID;

    
    public Dummy() {
        appointmentID = 0;
        customerID = 0;
        contactID = 0;


    }

    
    //constructor
    public Dummy(int appointmentiD, int customerID, int contactID) {
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.contactID = contactID;

    }

    //get them
    public static int getAppointmentID() {
        return appointmentID;
    }
    
    public static int getCustomerID() {
    return customerID;
}
    public static int getContactID() {
        return contactID;
    }


    //set them
    public static void setAppointmentID(int AppointmentID) {
        Dummy.appointmentID = AppointmentID;
    }
    
    public static void setContactID(int contactID) {
        Dummy.contactID = contactID;
    }
    
    public static void setCustomerID(int customerID) {
        Dummy.customerID = customerID;
    }

}
