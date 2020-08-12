// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 9
 * Name: Chloe Crowe
 * Username:Crowechlo
 * ID: 300539785
 */

import ecs100.*;
import java.util.*;
import java.text.*;

/**
 * Earthquake
 * Describes a single earthquake, with information from the earthquake data
 * available from http://quakesearch.geonet.org.nz/
 * Has methods for computing the distance and time difference between two earthquakes
 */

public class Earthquake{

    //Constants needed to parse the date and time from the file and print it out
    public static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("HH:mm:ss' UTC, 'dd/MM/yyyy ");

    private String ID;
    private Date time;
    private double longitude, latitude;
    private double magnitude;

    /** Construct a new EarthQuake object */
    public Earthquake(String ID, String time,
    double longitude, double latitude, double magnitude){
        this.ID = ID;
        try{ this.time = DATE_PARSER.parse(time); }catch(ParseException e){System.out.println(e);}
        this.longitude = longitude;
        this.latitude = latitude;
        this.magnitude = magnitude;
    }

    /**
     * Distance from this earthquake to the other earthquake, in km
     * Formula from http://www.movable-type.co.uk/scripts/latlong.html
     */
    public double distanceTo(Earthquake other){
        double lat1 = this.latitude *Math.PI/180;
        double lat2 = other.latitude *Math.PI/180;
        double difLong = other.longitude*Math.PI/180 - this.longitude*Math.PI/180;

        return Math.acos( (Math.sin(lat1)*Math.sin(lat2)) +
            (Math.cos(lat1)*Math.cos(lat2))* Math.cos(difLong) ) * 6371;
    }

    /**
     * Time difference between this earthquake and the other, in minutes
     */
    public double timeBetween(Earthquake other){
        return (other.time.getTime()-this.time.getTime())/60000; 
    }

    /** Get magnitude of the earthquake */
    public double getMagnitude(){
        return this.magnitude;
    }

    /** Get earthquake ID */
    public String getID(){
        return this.ID;
    }

    /** Get time */
    public Date getTime(){
        return this.time;
    }

    /**
     * Returns a nicely formatted String describing the earthquake
     */
    public String toString(){
        return (this.ID +
            " at " + DATE_FORMATTER.format(this.time) +
            " mag:" + this.magnitude +
            " at (" + this.longitude +","+ this.latitude +") ");
    }

}
