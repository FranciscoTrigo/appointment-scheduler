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
 * 
 * Taken from an example in one of the videos recorded for  WGU
 * This manages and controls the SQL connection the the database
 * 
 */
public class DBConnection {
    // Database information in variable form
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/WJ068kM";
    
    private static final String jdbcURL = protocol + vendorName + ipAddress + "?connectionimeZone=SERVER";
    
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";

    /**
     *  null
     */
    public static Connection conn = null;
    
    private static final String username = "U068kM";
    private static final String password = "53688698850";
    
    
    ////
    /// Connecting to the database
    /**
     * Starts the initial connection to the db with provived parameters
     * @return conn is the connection
     * @throws ClassNotFoundException --
     * @throws SQLException --
     * @throws Exception --
     */
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
            System.out.println("There is an error connecting!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch(SQLException e)
        {
           // System.out.println("Error " + e.getMessage());
            System.out.println("There is an error connecting!");
            e.printStackTrace();
        }
        return conn;
    }
       
     // Bye bye connection DB

    /**
     *
     */
    public static void closeConnection()
    {
        System.out.println("Disconnecting...");
        try {
        conn.close();
        System.out.println("Connection deleted.");
        System.out.println("Bye bye!");
    }
        catch(Exception e)
        {
           System.out.println("Error " + e.getMessage());
        }
}
    
    // get connection to only have one connection and be faster!

    /**
     *
     * @return returns the current connection, this way we do not make too many connections
     */
    public static Connection getConnection()
    {
        return conn;
    }
}
    

    
    
    

