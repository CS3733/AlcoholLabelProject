package com.emeraldElves.alcohollabelproject;


/**
 * Created by keionbis on 4/4/17.
 */
public class WorkflowActionsController {
    Main main;
    SubmittedApplication CurrentlyBeingUpdated;
    String Username;
    public void init(Main main, SubmittedApplication CurrentlyBeingUpdated, String Username ) {
        this.main = main;
        this.CurrentlyBeingUpdated = CurrentlyBeingUpdated;
        this.Username = Username;
    }

    public void approve() {
        AlcoholDatabase alcoholDatabase = null;
        SubmittedApplication ApplicationUnderReview = null;
        alcoholDatabase.updateApplicationStatus(ApplicationUnderReview ,ApplicationStatus.APPROVED);

    }
    public void approveWithConditions() {
        AlcoholDatabase alcoholDatabase = null;
        SubmittedApplication ApplicationUnderReview = null;
        alcoholDatabase.updateApplicationStatus(ApplicationUnderReview ,ApplicationStatus.APPROVEDWITHCONDITIONS);
    }
    public void needsCorrections(){
        AlcoholDatabase alcoholDatabase = null;
        SubmittedApplication ApplicationUnderReview = null;
        alcoholDatabase.updateApplicationStatus(ApplicationUnderReview ,ApplicationStatus.NEEDSCORRECTIONS);
    }
    public void reject(){
        AlcoholDatabase alcoholDatabase = null;
        SubmittedApplication ApplicationUnderReview = null;
        alcoholDatabase.updateApplicationStatus(ApplicationUnderReview ,ApplicationStatus.REJECTED);

    }
    public void forwardApplication() {
        AlcoholDatabase alcoholDatabase = null;
        SubmittedApplication ApplicationUnderReview = null;
        alcoholDatabase.updateApplicationStatus(ApplicationUnderReview ,ApplicationStatus.PENDINGREVIEW);
    }

}

