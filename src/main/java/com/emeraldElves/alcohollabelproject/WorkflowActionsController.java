package com.emeraldElves.alcohollabelproject;


/**
 * Created by keionbis on 4/4/17.
 */
public class WorkflowActionsController {
    Main main;
    SubmittedApplication CurrentlyBeingUpdated;
    String Username;

    AlcoholDatabase alcoholDatabase;

    public void init(Main main, SubmittedApplication CurrentlyBeingUpdated, String Username) {
        this.main = main;
        this.CurrentlyBeingUpdated = CurrentlyBeingUpdated;
        this.Username = Username;
        alcoholDatabase = new AlcoholDatabase(Main.database);
    }

    public void approve() {
        alcoholDatabase.updateApplicationStatus(CurrentlyBeingUpdated, ApplicationStatus.APPROVED);
    }

    public void approveWithConditions() {
        alcoholDatabase.updateApplicationStatus(CurrentlyBeingUpdated, ApplicationStatus.APPROVEDWITHCONDITIONS);
    }

    public void needsCorrections() {
        alcoholDatabase.updateApplicationStatus(CurrentlyBeingUpdated, ApplicationStatus.NEEDSCORRECTIONS);
    }

    public void reject() {
        alcoholDatabase.updateApplicationStatus(CurrentlyBeingUpdated, ApplicationStatus.REJECTED);
    }

    public void forwardApplication() {
        alcoholDatabase.updateApplicationStatus(CurrentlyBeingUpdated, ApplicationStatus.PENDINGREVIEW);
    }

}

