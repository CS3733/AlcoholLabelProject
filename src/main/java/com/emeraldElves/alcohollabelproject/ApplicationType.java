package com.emeraldElves.alcohollabelproject;

import java.util.ArrayList;

/**
 * Created by Dan on 4/9/2017.
 */
public class ApplicationType {
    private boolean labelApproval; //14 a. on application
    private String stateOnly;//14 b. on application
    private String bottleCapacity;//bottle capacity before closure, 14 c. on application
    private String resubmissionID;//ttb id after resubmission, 14 d. on application

    public ApplicationType(boolean labelApproval, String stateOnly, String bottleCapacity, String resubmissionID) {
        this.labelApproval = labelApproval;
        this.stateOnly = stateOnly;
        this.bottleCapacity = bottleCapacity;
        this.resubmissionID = resubmissionID;
    }

    /**
     * This method returns the application type of the application based on
     * number 14 on the original application
     * @return An arraylist of strings representing the 4 checkboxes for the application type.
     * Returns an empty string if checkbox isn't checked, and string with message if box is checked.
     * For first checkbox, returns false if not checked and true otherwise.
     */
    public ArrayList<String> getApplicationType(){
        String booleanString;
        if(labelApproval) booleanString = "true";
        else booleanString = "";
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(booleanString);
        temp.add(stateOnly);
        temp.add(bottleCapacity);
        temp.add(resubmissionID);
        return temp;
    }
}
