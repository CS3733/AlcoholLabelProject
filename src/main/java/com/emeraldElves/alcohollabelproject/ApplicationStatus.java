package com.emeraldElves.alcohollabelproject;

 /** Created by elijaheldredge on 3/31/17.
 */
public enum ApplicationStatus {
    APPROVED("Your application was approved"),
    PENDINGREVIEW("Pending Review"),
    REJECTED("Reason");
    private String message;

    /**
     * This creates an ApplicationStatus with message
     * @param message The message associated with the application status
     */
    ApplicationStatus(String message){
        this.message = message;
    }

    /**
     * This sets the message for the ApplicationStatus
     * @param message The message for ApplicationStatus
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * This returns the message for application status
     * @return The message for the application status
     */
    public String getMessage(){
        return message;
    }
}
//>>>>>>> origin/develop
