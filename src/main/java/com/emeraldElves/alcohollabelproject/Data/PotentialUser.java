package com.emeraldElves.alcohollabelproject.Data;

import java.util.Date;

/**
 * Created by Dan on 4/15/2017.
 */
public class PotentialUser {
    //TODO: add fields that are added to account stuff
    private String Name, UserName,Password, address;
    private int representativeID, permitNum;
    private EmailAddress email;
    private PhoneNumber phoneNumber;
    private UserType userType;
    private Date date;

    public PotentialUser(String Name, String UserName, int representativeID, EmailAddress email, PhoneNumber phoneNumber, UserType userType, String Password, Date date,
    int permitNum, String address) {
        this.Name = Name;
        this.UserName = UserName;
        this.representativeID = representativeID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.Password = Password;
        this.date = date;
        this.address = address;
        this.permitNum = permitNum;

    }

    public EmailAddress getEmail() {
        return email;
    }

    public int getRepresentativeID() {
        return representativeID;
    }

    public int getPermitNum(){ return permitNum; }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return Name;
    }

    public String getUserName() {
        return UserName;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPassword() {
        return Password;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress(){ return address; }
}
