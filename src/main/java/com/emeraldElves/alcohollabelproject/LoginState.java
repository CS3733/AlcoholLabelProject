package com.emeraldElves.alcohollabelproject;

/**
 * Created by Kyle on 4/6/2017.
 */
public class LoginState {

    private static LoginState instance = null;

    private UserType userType;
    private String username;

    private LoginState() {
        logout();
    }

    public static synchronized LoginState getInstance() {
        if (instance == null) {
            instance = new LoginState();
        }
        return instance;
    }

    public void login(UserType userType, String username) {
        this.userType = userType;
        this.username = username;
    }

    public void logout() {
        userType = UserType.BASIC;
        username = "";
    }

    public boolean isLoggedIn(){
        return userType != UserType.BASIC;
    }

    public boolean isAgentLoggedIn(){
        return userType == UserType.TTBAGENT;
    }

    public boolean isApplicantLoggedIn(){
        return userType == UserType.APPLICANT;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }
}
