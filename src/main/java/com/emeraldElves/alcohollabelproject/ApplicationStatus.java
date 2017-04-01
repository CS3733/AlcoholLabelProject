package com.emeraldElves.alcohollabelproject;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public enum ApplicationStatus {
    APPROVED("Your application was approved"),
    PENDINGREVIEW("Pending Review"),
    REJECTED("Reason");
    private String message;

    ApplicationStatus(String message){
        this.message = message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
