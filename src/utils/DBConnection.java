/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author francisco trigo
 */
public class DBConnection {
    // Database infoirmation
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/WJ068kM";
    
    private static final String jdbcURL = protocol + vendorName + ipAddress + "?connectionimeZone=SERVER";
    
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    public static Connection conn = null;
    
    private static final String username = "U068kM";
    private static final String password = "53688698850";
    
    
    ////
    /// Connecting to the database
    
    public static Connection startConnection() throws ClassNotFoundException, SQLException, Exception
    {
        System.out.println("Connecting to database...");
        try{
        Class.forName(MYSQLJDBCDriver);
        conn = DriverManager.getConnection(jdbcURL, username, password);
        System.out.println("Connection is correct!");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch(SQLException e)
        {
           // System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
       
     // Bye bye connection DB
    public static void closeConnection()
    {
        System.out.println("Disconnecting...");
        try {
        conn.close();
        System.out.println("Connection deleted.");
    }
        catch(Exception e)
        {
           // System.out.println("Error " + e.getMessage());
        }
}
    
    // get connection to only have one connection and be faster!
    public static Connection getConnetion()
    {
        return conn;
    }
}
    

    
    
    

