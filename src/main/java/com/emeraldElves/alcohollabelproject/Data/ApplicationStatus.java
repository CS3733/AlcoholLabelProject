package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public enum ApplicationStatus {
    APPROVED("Your application was approved", 0),
    PENDINGREVIEW("Pending Review", 1),
    REJECTED("Reason", 2),
    APPROVEDWITHCONDITIONS("Approved with conditions",3),
    NEEDSCORRECTIONS("Needs corrections",4);
    private String message;
    private int value;

    /**
     * This creates an ApplicationStatus with message
     *
     * @param message The message associated with the application status
     */
    ApplicationStatus(String message, int value) {
        this.message = message;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * This sets the message for the ApplicationStatus
     *
     * @param message The message for ApplicationStatus
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This returns the message for application status
     *
     * @return The message for the application status
     */
    public String getMessage() {
        return message;
    }


    public static ApplicationStatus fromInt(int val) {
        switch (val) {
            case 0:
                return APPROVED;
            case 1:
                return PENDINGREVIEW;
            default:
                return REJECTED;
        }
    }
}

