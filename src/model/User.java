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
 * This class is used to keep track of the user that logs in, as this information is used to later fill out tables correctly
 * @author Francisco Trigo
 */

//class is used to store current user data
public class User {

    private static int userID; 
    private static String username;
    private static String password;
    
    /**
     *
     */
    public User() {
        userID = 0;
        username = null;
        password = null;

    }

    
    //constructor

    /**
     *
     * @param userID - the userID, from the users table
     * @param username - username from users table
     * @param password - password from users table
     */
    public User(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    //get them

    /**
     *
     * @return returns the user_ID
     */
    public static int getUserID() {
        return userID;
    }
    
    /**
     *
     * @return returns the user_name
     */
    public static String  getUsername() {
        return username;
    }
     
    /**
     *
     * @return returns the password. never really used
     */
    public String getPassword() {
        return this.password;
    }

    //set them

    /**
     *
     * @param userID - User_ID taken form the users table in the db
     */
    public static void setUserID(int userID) {
        User.userID = userID;
    }

    /**
     *
     * @param username username taken from the users table
     */
    public static void setUsername(String username) {
        User.username = username;
    }

    /**
     *
     * @param password password taken from the user table
     */
    public static void setPassword(String password) {
        User.password = password;
    }
}
