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

    
    public Dummy() {
        appointmentID = 0;


    }

    
    //constructor
    public Dummy(int appointmentiD) {
        this.appointmentID = appointmentID;

    }

    //get them
    public static int getAppointmentID() {
        return appointmentID;
    }


    //set them
    public static void setAppointmentID(int AppointmentID) {
        Dummy.appointmentID = AppointmentID;
    }


}
