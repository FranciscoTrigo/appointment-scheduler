/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franciscoscheduler;

import java.sql.SQLException;
import static javafx.application.Application.launch;
import utils.DBConnection;

/**
 *
 * @author yamif
 */
public class FranciscoScheduler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
        
        System.out.println("Scheduler app starting...");
        DBConnection.startConnection();
        //launch(args);
        DBConnection.closeConnection();
        System.out.println("Closing scheduler app...");
    
    }}

