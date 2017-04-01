package com.emeraldElves.alcohollabelproject;

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
        return userName.equals("Admin") && password.equals("Admin1");
    }

    /**
     * Checks if Applicant login is valid.
     *
     * @param userName The username of the applicant
     * @param password The password of the applicant
     * @return True if the applicant login is valid, False otherwise
     */
    public boolean isValidApplicant(String userName, String password) {
        return userName.equals("Admin") && password.equals("Admin1");
    }
}
