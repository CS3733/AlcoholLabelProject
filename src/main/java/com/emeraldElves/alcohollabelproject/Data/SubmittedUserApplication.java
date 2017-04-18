package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by keionbis on 4/17/17.
 */
public class SubmittedUserApplication {

    private String Name, UserName;
    private int IDnum;
    private EmailAddress email;
    private PhoneNumber phoneNumber;

    public SubmittedUserApplication(String Name, String UserName, int IDnum, EmailAddress email, PhoneNumber phoneNumber) {
        this.Name = Name;
        this.UserName = UserName;
        this.IDnum = IDnum;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

}
