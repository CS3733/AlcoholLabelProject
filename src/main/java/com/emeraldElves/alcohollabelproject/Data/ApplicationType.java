package com.emeraldElves.alcohollabelproject.Data;

import java.util.ArrayList;

/**
 * Created by Dan on 4/9/2017.
 */
public class ApplicationType {
    private boolean labelApproval; //14 a. on application
    private String stateOnly;//14 b. on application
    private int bottleCapacity;//bottle capacity before closure, 14 c. on application

    public ApplicationType(boolean labelApproval, String stateOnly, int bottleCapacity) {
        this.labelApproval = labelApproval;
        this.stateOnly = stateOnly;
        this.bottleCapacity = bottleCapacity;
    }

    public boolean isLabelApproval() {
        return labelApproval;
    }

    public String getStateOnly() {
        return stateOnly;
    }

    public int getBottleCapacity() {
        return bottleCapacity;
    }
    //this method is useless lol
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
        else booleanString = "false";
        ArrayList<String> temp = new ArrayList<>();
        temp.add(booleanString);
        temp.add(stateOnly);
        temp.add(""+bottleCapacity);
        return temp;
    }
}
