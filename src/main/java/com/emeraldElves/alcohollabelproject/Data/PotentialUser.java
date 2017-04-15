package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by Dan on 4/15/2017.
 */
public class PotentialUser {
    //TODO: add fields that are added to account stuff
    private String username;
    private String password;
    private UserType userType;

    public PotentialUser(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }
}
