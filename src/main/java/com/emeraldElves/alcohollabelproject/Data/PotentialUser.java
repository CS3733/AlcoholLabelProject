package com.emeraldElves.alcohollabelproject.Data;

import java.util.Date;

/**
 * Created by Dan on 4/15/2017.
 */
public class PotentialUser {
    //TODO: add fields that are added to account stuff
    private String Name, UserName,Password;
    private int IDnum;
    private EmailAddress email;
    private PhoneNumber phoneNumber;
    private UserType userType;
    private Date date;

    public PotentialUser(String Name, String UserName, int IDnum, EmailAddress email, PhoneNumber phoneNumber, UserType userType, String Password, Date date) {
        this.Name = Name;
        this.UserName = UserName;
        this.IDnum = IDnum;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.Password = Password;
        this.date = date;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public int getIDnum() {
        return IDnum;
    }

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
}
