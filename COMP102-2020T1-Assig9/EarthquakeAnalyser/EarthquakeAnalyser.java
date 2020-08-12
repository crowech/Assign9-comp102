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
import java.nio.file.*;
import java.io.*;

/**
 * EarthquakeAnalyser
 * Analyses data about a collection of 4335 NZ earthquakes from May 2016 to May 2017
 * Each line of the file "earthquake-data.txt" has a description of one earthquake:
 *   ID time longitude latitude magnitude
 * Data is from http://quakesearch.geonet.org.nz/
 *  Note bigearthquake-data.txt has just the 421 earthquakes of magnitude 4.0 and above
 *   which may be useful for testing, since it is not as big as the full file.
 * 
 * Core:  two methods:
 *   loadData
 *      Loads the data from a file into a field containing an
 *      ArrayList of Earthquake objects.
 *      Hint : to make an Earthquake object, read all the lines from the file
 *              and then take each line apart into the values to pass to the
 *              Earthquake constructor
 *   findBigOnes
 *      Prints out all the earthquakes in the ArrayList that have a magnitude 5.5 and over.
 *      Hint: see the methods in the Earthquake class, especially getMagnitude and toString
 *   
 * Completion: one method:
 *   findPairs
 *	Compares each Earthquake in the list with every other Earthquake
 *      and finds every pair of "close" earthquakes - earthquakes that
 *      are within 1km of each other, and
 *      For each pair, prints
 *        - their ID's,
 *        - the distance between them

 * Challenge: one method:
 *  findFollowOns;
 *      Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6.0
 *      For each such earthquake on this list
 *       - finds a list of all the "follow-on" earthquakes:
 *         later earthquakes within a distance of 10km
 *       - If there are at least two follow-on earthquakes, then it prints out
 *          the full details of the big earthquake followed by
 *          ID, magnitude and days since the big one for each follow-on earthquake
 *
 * The file "example-output.txt" has sample output for the "bigearthquake-data.txt"
 *   file, which only contains earthquakes with magnitude 4 and above.
 */

public class EarthquakeAnalyser{

    private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
    private Earthquake[] earthquakeData = new Earthquake[5];
    private double longitude;
    private double latitude;
    private double magnitude;
    private boolean close;



    public void setupGUI(){
        UI.initialise();
        UI.addButton("Load", this::loadData);
        UI.addButton("Big ones",  this::findBigOnes);
        UI.addButton("Pairs", this::findPairs);
        UI.addButton("Follow-ons", this::findFollowOns);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);  //text pane only
    }

    /*
     * Load data from the data file into the earthquakes field:
     * clear the earthquakes field.
     * Read lines from file
     * For each line, use Scanner to break up each line and make an Earthquake
     *  adding it to the earthquakes field.
     */
    public void loadData(){
        try {
            //Lets the user choose the file
            String fname = UIFileChooser.open("Choose your file");
            //Makes the scanner
            Scanner scan = new Scanner(Path.of(fname));
            //Clears the Array list
            this.earthquakes = new ArrayList<Earthquake>();
            //Whilst there is still lines within the choosen file
            while(scan.hasNextLine()) {
                if (scan.hasNext()) {
                    //Scans the next values in the file
                    String ID = scan.next();
                    String time = scan.next();
                    Double longitude = scan.nextDouble();
                    Double latitude = scan.nextDouble();
                    Double magnitude = scan.nextDouble();
                    //Adds all the values into a array
                    Earthquake e = new Earthquake(ID, time, longitude, latitude, magnitude);
                    //Add the earthquake to the list
                    this.earthquakes.add(e);

                } else {
                    scan.nextLine();
                }

            }

            scan.close();
            UI.printf("Loaded %d earthquakes into list\n", this.earthquakes.size());
            UI.println("----------------------------");
        } catch(IOException e){UI.println("File reading failed");}
    }

    /**
     * Print details of all earthquakes with a magnitude of 5.5 or more
     */
    public void findBigOnes(){
        UI.println("Earthquakes 5.5 and above");
        //For each of the earthquakes within the list
            for (Earthquake earthquake : earthquakes) {
                //For each earthquake it gets the magnitude and sees if its greater than 5.5
                if (earthquake.getMagnitude() >= 5.5) {
                    //Prints the earthquake
                    UI.println(earthquake);
                }

            }



        UI.println("------------------------");
    }

    /**
     * Print all pairs of earthquakes within 1km of each other
     */
    public void findPairs(){
        UI.println("Close earthquakes");
        //For each earthquake in the list
        for(int i=0; i<earthquakes.size();i++){
            //If the first earthquake and the one after and both not null
            if(this.earthquakes.get(i) == null && this.earthquakes.get(i+1)==null){
                //Then close is true
                this.close = true; }
            //if one earthquake is null and the other it returns false
            if(this.earthquakes.get(i) == null || this.earthquakes.get(i+1)==null){
                this.close = false; }
            //If each earthquake is within 1 of each other then close is true
            if(this.earthquakes.get(i).distanceTo(this.earthquakes.get(i+1)) <= 1){
                this.close = true; }

            if(close){
                //If close is true then it prints the earthquake.
                UI.println(earthquakes);
            }
        }

        UI.println("----------------------------");
    }

    /**
     * CHALLENGE
     * Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6 
     * For each earthquake on this list
     * - finds a list of all the "follow-on" earthquakes:
     *   later earthquakes within a distance of 10km.
     * - If there are at least two follow-on earthquakes, then it prints out
     *     the full details of the big earthquake followed by
     *    ID, magnitude and days since the big one for each follow-on earthquake
     */

    public void findFollowOns(){
        UI.println("Big earthquakes and their follow-on earthquakes");
        /*# YOUR CODE HERE */

        UI.println("-------------------------------------");
    }

    public static void main(String[] arguments){
        EarthquakeAnalyser obj = new EarthquakeAnalyser();
        obj.setupGUI();
    }	

}
