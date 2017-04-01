package com.emeraldElves.alcohollabelproject;

/**
 * Created by Kylec on 4/1/2017.
 */
public class ManufacturerInfo {

    private String name;
    private String physicalAddress;
    private String company;
    private int representativeID;
    private int permitNum;
    private PhoneNumber phoneNumber;
    private EmailAddress emailAddress;

    public String getName() {
        return name;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public String getCompany() {
        return company;
    }

    public int getRepresentativeID() {
        return representativeID;
    }

    public int getPermitNum() {
        return permitNum;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }
}
