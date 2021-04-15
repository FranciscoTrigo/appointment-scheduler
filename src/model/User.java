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
public class User {

    private static int userID; 
    private static String username;
    private static String password;
    
    public User() {
        userID = 0;
        username = null;
        password = null;

    }

    
    //constructor
    public User(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    //get them
    public static int getUserID() {
        return userID;
    }
    
    public static String  getUsername() {
        return username;
    }
     
    public String getPassword() {
        return this.password;
    }

    //set them
    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}
