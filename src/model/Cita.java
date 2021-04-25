/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * This is a class that I made to make it easier to fill out the required reports.
 * It stores the possible appointment types, locations, month, and how many occurrences of each type/location/month are in the appointments table at the moment of running
 * @author Francisco Trigo
 */
public class Cita {
    private String type;
    private int manyType;
    private String month;
    private int manyMonth;
    private String location;
    private int manyLocation;
    


//constructor

    /**
     *
     */
public Cita() {

}

    /**
     *
     * @param type - String for Type of appointment
     * @param manyType - How many appointments with the particular type are on the table at the moment
     * @param month - This saves the month that the appointment takes place. Taken form the Start value
     * @param manyMonth - Stores how many instances of each month are there in the table
     * @param location - String for location of appointment
     * @param manyLocation - Stores how many times the location repeats
     */
    public Cita(String type, int manyType, String month, int manyMonth, String location, int manyLocation) {
    setType(type);
    setManyType(manyType);
    setMonth(month);
    setManyMonth(manyMonth); 
    setLocation(location);
    setManyLocation(manyLocation);
}

    /**
     *
     * @return the appointment type
     */
    public String getType() {
    return type;
}

    /**
     *
     * @return how many instances of this.Type are in the table
     */
    public int getManyType() {
    return manyType;
}

    /**
     *
     * @return the month, most likely in the form a number. IE April = 04
     */
    public String getMonth() {
    return month;
}

    /**
     *
     * @return how many instances of this.month are in the table
     */
    public int getManyMonth() {
    return manyMonth;
}

    /**
     *
     * @return the appointment location
     */
    public String getLocation() {
    return location;
}

    /**
     *
     * @return how many instances of this.location are on the table
     */
    public int getManyLocation() {
    return manyLocation;
}


/////////////////////////

    /**
     *
     * @param type the Appointment type, from appointments table
     */

public void setType(String type) {
    this.type = type;
}

    /**
     *
     * @param manyType - How many instances of Type are in the table
     */
    public void setManyType(int manyType) {
    this.manyType = manyType;
}

    /**
     *
     * @param month Small string(might be a number) from the Start column in the appointments table. 
     */
    public void setMonth(String month) {
    this.month = month;
}

    /**
     *
     * @param manyMonth How many instances of month are in the table
     */
    public void setManyMonth(int manyMonth) {
    this.manyMonth = manyMonth;
}

    /**
     *
     * @param location Location of the appointment, taken form the appointments table
     */
    public void setLocation(String location) {
    this.location = location;
}

    
    /**
     * 
     * @param manyLocation how many instances of location in the table
     */
public void setManyLocation(int manyLocation) {
    this.manyLocation = manyLocation;
}






}
