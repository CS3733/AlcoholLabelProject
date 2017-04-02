package com.emeraldElves.alcohollabelproject;

/**
 * Created by Joe on 4/2/2017.
 */

import java.util.ArrayList;

public class TTBAgent {
    private String name;
    private int governmentID;
    private ArrayList currentApps;

    public TTBAgent(String name, int governmentID) {
        name = this.name;
        governmentID = this.governmentID;
        currentApps = new ArrayList<SubmittedApplication>();
    }

    /*
     * getName(): get the name of the TTB agent
     * @return name of the TTB agent
     */
    String getName() {
        return name;
    }

    /*
     * getGovernmentID(): get the TTB agent's government ID
     * @return government ID of the TTB agent
     */
    int getGovernmentID() {
        return governmentID;
    }

    /*
     * getCurrentApplications(): get list of applications TTB agent is currently working on
     * @return ArrayList of current applications
     */
    ArrayList<SubmittedApplication> getCurrentApplications() {
        return currentApps;
    }
}
