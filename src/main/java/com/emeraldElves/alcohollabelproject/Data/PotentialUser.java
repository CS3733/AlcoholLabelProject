package com.emeraldElves.alcohollabelproject.Data;

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

    public PotentialUser(String Name, String UserName, int IDnum, EmailAddress email, PhoneNumber phoneNumber, UserType userType, String Password) {
        this.Name = Name;
        this.UserName = UserName;
        this.IDnum = IDnum;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.Password = Password;
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
}
