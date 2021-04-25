/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franciscoscheduler;


import model.Customer;
import model.User;
import model.Appointment;
import java.sql.SQLException;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;
import java.sql.SQLException;
import utils.DBConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Francisco Trigo
 * @version 0.8
 * @since 2021-04-01
 * 
 * This is the program for the C195 class at WGU
 * Its a appointment scheduler application that interfaces with a SQL database, and lets you add, edit, and remove customers.
 * It also lets you add, edit and remove appointments. And store and edit the information on the SQL database
 */
public class FranciscoScheduler extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/loginscreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Scheduler");
        stage.show();
    }
    

    /**
     * @param args the command line arguments
     * @throws Exception  -
     * @throws SQLException - 
     */
    public static void main(String[] args) throws SQLException, Exception {
        
        System.out.println("Scheduler app starting...");
        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
        System.out.println("Closing scheduler app...");
    
    }}

