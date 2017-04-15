package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan on 3/31/2017.
 */
public class AuthenticatedUsersDatabase {
    private Database db;

    /**
     * Creates an AuthenticatedUsersDatabase
     *
     * @param db the main database that contains the data
     */
    public AuthenticatedUsersDatabase(Database db) {
        this.db = db;
    }

    /**
     * Checks if TTB agent login is valid.
     *
     * @param userName The username of the TTB agent
     * @param password The password of the TTB agent
     * @return True if the TTB agent is valid, False otherwise
     */
    public boolean isValidTTBAgent(String userName, String password) {
        ResultSet results = db.select("*", "TTBAgentLogin", "username = '" + userName +
                "' AND  password = '" + password + "'");
        if (results == null)
            return false;
        try {
            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public int getRepresentativeID(String username) {
        ResultSet resultSet = db.select("representativeID", "ApplicantLogin", "username = '" + username + "'");
        try {
            if (resultSet.next()) {
                return resultSet.getInt("representativeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getAllAgents() {
        ResultSet resultSet = db.select("username", "TTBAgentLogin");
        List<String> agents = new ArrayList<>();
        try {
            while (resultSet.next()) {
                agents.add(resultSet.getString("username"));
            }
            return agents;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    /**
     * Checks if Applicant login is valid.
     *
     * @param userName The username of the applicant
     * @param password The password of the applicant
     * @return True if the applicant login is valid, False otherwise
     */
    public boolean isValidApplicant(String userName, String password) {
        ResultSet results = db.select("*", "ApplicantLogin", "username = '" + userName +
                "' AND  password = '" + password + "'");
        if (results == null)
            return false;
        try {
            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isValidSuperUser(String userName, String password){
        if(userName.equals("JoseWong")) {
            if (password.equals("password")) {
                return true;
            }
        }
        else
            return false;
        return false;
    }

    public boolean isValidAccount(String userName, String password){
        return isValidApplicant(userName, password) || isValidTTBAgent(userName, password);
    }

    public UserType getAccountType(String userName, String password){
        if(isValidTTBAgent(userName, password))
            return UserType.TTBAGENT;
        if(isValidApplicant(userName, password))
            return UserType.APPLICANT;
        return UserType.BASIC;
    }

    /**
     * Adds a potential user to database to be accepted/rejected by superagent
     * @param username username of potential agent
     * @param password password of potential agent
     * @param userType type of potential agent
     * @return Whether or not it added the potential user to database successfully
     */
    public boolean addPotentialUser(String username, String password, UserType userType){
        boolean worked;
        // I dont think i need to check database, because you cant update if its already in queue
        try{
            worked = db.insert("'" + username + "', '"
                    + password + "', "
                    + userType, "NewApplicant");
            if(!worked){ throw new SQLException("Failed to add user");}
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
