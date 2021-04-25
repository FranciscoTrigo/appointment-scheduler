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
 * This is a class created to easily transfer variables from screen to screen, takes parameters from the appointment table, but its not meant 
 * to reflect it.
 * @author Francisco Trigo
 */

//class is used to store selected appointment data to edit it afterwards
// its static so it keeps the information whem moving between screens
public class Dummy {

    private static int appointmentID;
    private static int customerID;
    private static int contactID;

    /**
     *  defaults to 0
     */
    public Dummy() {
        appointmentID = 0;
        customerID = 0;
        contactID = 0;


    }

    
    //constructor

    /**
     *
     * @param appointmentiD -  The appointment_ID of the currently selected appointment in the tableview
     * @param customerID - the customerID for the currently appointment selected
     * @param contactID - contactID for the selected appointment
     */
    public Dummy(int appointmentiD, int customerID, int contactID) {
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.contactID = contactID;

    }

    //get them

    /**
     *
     * @return returns the appointment ID. this is used to grab the information in the updateAppointment screen
     */
    public static int getAppointmentID() {
        return appointmentID;
    }
    
    /**
     *
     * @return returns the customerID, it is used to fill out the customer combbobox
     */
    public static int getCustomerID() {
    return customerID;
}

    /**
     *
     * @return returns the contactID, used for filling the contact combo box
     */
    public static int getContactID() {
        return contactID;
    }


    //set them

    /**
     *
     * @param AppointmentID - The appointment ID, set up from the selected appointment in the tableview
     */
    public static void setAppointmentID(int AppointmentID) {
        Dummy.appointmentID = AppointmentID;
    }
    
    /**
     *
     * @param contactID - sets the contactID, set up form the selected appointment in the tableview
     */
    public static void setContactID(int contactID) {
        Dummy.contactID = contactID;
    }
    
    /**
     *
     * @param customerID - sets the customerID, set up from the selected appointment in the tableview
     */
    public static void setCustomerID(int customerID) {
        Dummy.customerID = customerID;
    }

}
