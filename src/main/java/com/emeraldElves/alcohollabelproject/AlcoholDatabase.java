package com.emeraldElves.alcohollabelproject;

import java.util.List;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class AlcoholDatabase {


    private Database db;

    /**
     * Creates an AlcoholDatabase
     *
     * @param db the main database that contains the data
     */
    public AlcoholDatabase(Database db) {
        this.db = db;
    }

    /**
     * Get the most recently approved applications.
     *
     * @param numApplications The maximum number of applications to receive.
     * @return A list of the most recently approved applications ordered from most recent to least recent.
     */
    public List<SubmittedApplicaiton> getMostRecentApproved(int numApplications) {

        return null;
    }

    /**
     * Search by the brand name of alcohol.
     *
     * @param brandName The brand name to search.
     * @return A list of approved alcohols containing the brandName ordered by time approved.
     */
    public List<SubmittedApplication> searchByBrandName(String brandName) {

        return null;
    }

    /**
     * Submit an application for review.
     *
     * @param application The application to submit. If this is an update to an application, be sure to include the correct unique ID number in the application.
     * @return True if the application was submitted without error.
     */
    public boolean submitApplication(SubmittedApplication application) {

        return false;
    }

    /**
     * Get the most recent unapproved applications.
     *
     * @param numApplications The maximum number of applications to receive.
     * @return A list of the most recent unapproved applications ordered from most recent to least recent.
     */
    public List<SubmittedApplication> getMostRecentUnapproved(int numApplications) {
        return null;
    }

    /**
     * Update the approval status of the application.
     *
     * @param application The application to change the status of.
     * @param status      The new status of the application.
     * @return True if the status was updated without error.
     */
    public boolean updateApplicationStatus(SubmittedApplication application, ApplicationStatus status) {
        return false;
    }
}
