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
public class Customer {

    private int customerID; //Auto incremented in database
    private String customerName;
    private int active;
    private Date createDate;
    private String createdBy;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String country;
    private String divisionArea;
    private Date lastUpdate;
    private String lastUpdateBy;

    //constructor
    public Customer() {

    }

    public Customer(int customerID, String customerName, String address, String postalCode, String phone, String country, String divisionArea, Date lastUpdate, String lastUpdateBy) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        setCustomerAddress(address); 
        setCustomerPostalCode(postalCode);
        setCustomerPhone(phone);
        setCustomerDivisionArea(divisionArea);
        setCustomerLastUpdate(lastUpdate);
        setCustomerLastUpdateBy(lastUpdateBy);

    }

    public Customer(int customerID, String customerName) {
        setCustomerID(customerID); //this is Auto Incremented in the database
        setCustomerName(customerName);
    }

    //getters
    
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }


    public String getCustomerAddress() {
        return address;
    }


    public String getCustomerPostalCode() {
        return postalCode;
    }

    public String getCustomerPhone() {
        return phone;
    }

    public String getCustomerDivisionArea() {
        return country;
    }
    
    public String getCustomerCountry() {
    return country;
    }

    public Date getCustomerLastUpdate() {
        return lastUpdate;
    }

    public String getCustomerLastUpdateBy() {
        return lastUpdateBy;
    }
    

    //setters
    
    public void setCustomerID(int customerID) {

        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public void setCustomerAddress(String address) {
        this.address = address;
    }


    public void setCustomerPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }

    public void setCustomerDivisionArea(String divisionArea) {
        this.divisionArea = divisionArea;
    }
    
    public void setCustomerCountry(String divisionArea) {
    this.country = country;
    }

    public void setCustomerLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCustomerLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}
