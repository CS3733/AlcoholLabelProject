package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.UserType;

/**
 * Created by Kylec on 4/9/2017.
 */
public class Authenticator {

    private Storage storage;
    private UserType userType;
    private String username;

    private Authenticator() {
        storage = Storage.getInstance();
        username = "";
        userType = UserType.BASIC;
    }

    public static Authenticator getInstance() {
        return AuthenticatorHolder.instance;
    }

    private static class AuthenticatorHolder {
        public static final Authenticator instance = new Authenticator();
    }

    public boolean login(UserType userType, String username, String password) {
        if (storage.isValidUser(userType, username, password)) {
            this.userType = userType;
            this.username = username;
            return true;
        }
        return false;
    }



    public void logout() {
        username = "";
        userType = UserType.BASIC;
    }

    public boolean isLoggedIn() {
        return userType != UserType.BASIC;
    }

    public boolean isAgentLoggedIn() {
        return userType == UserType.TTBAGENT;
    }

    public boolean isApplicantLoggedIn() {
        return userType == UserType.APPLICANT;
    }
    public boolean isSuperAgentLoggedIn() {
        return userType == UserType.SUPERAGENT;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }


}
