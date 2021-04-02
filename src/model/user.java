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
public class user {
    private static int userID;
    private static String username;
    private static String userpassword;
    
    public user() {
        userID = 0;
        username = null;
        userpassword = null;
    }
    
public user(int userID, String username, String password) {
    this.userID = userID;
    this.username = username;
    this.userpassword = password;
}

// get 'em all

public static int getUserID() {
    return userID;
}

public static String getUsername() {
    return username;
}

public static String getPassword() {
    return userpassword;
}

    //set them
    public static void setUserID(int userID) {
        user.userID = userID;
    }

    public static void setUsername(String username) {
        user.username = username;
    }

    public static void setPassword(String password) {
        user.userpassword = password;  
    }
    
    
    
    
}

