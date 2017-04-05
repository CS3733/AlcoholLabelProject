package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by keionbis on 4/5/17.
 */

public class ApprovalProcessController {
    Main main;
    String Username;
    SubmittedApplication application;
    @FXML
    Label brandName;

    @FXML
    Label fancifulName;

    @FXML
    Label alcoholType;

    @FXML
    Label submissionDate;

    @FXML
    Label company;

    @FXML
    Label origin;

    AlcoholDatabase alcoholDatabase;

    public void init(Main main, String Username, SubmittedApplication application){
        this.main = main;
        this.Username = Username;
        this.application = application;
        brandName.setText(application.getApplication().getAlcohol().getBrandName());
        fancifulName.setText(application.getApplication().getAlcohol().getName());
        String type = "";
        switch (application.getApplication().getAlcohol().getAlcoholType()) {
            case BEER:
                type = "Beer";
                break;
            case WINE:
                type = "Wine";
                break;
            case OTHER:
                type = "Other";
                break;
        }
        alcoholType.setText(type);
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Date date = application.getApplication().getSubmissionDate();
        date.setYear(date.getYear() - 1900);
        submissionDate.setText(dateFormat.format(date));
        company.setText(application.getApplication().getManufacturer().getCompany());
        String productSource = "";
        switch (application.getApplication().getAlcohol().getOrigin()) {
            case IMPORTED:
                productSource = "Imported";
                break;
            case DOMESTIC:
                productSource = "Domestic";
                break;
        }
        origin.setText(productSource);
        alcoholDatabase = new AlcoholDatabase(Main.database);
    }

    public void GoHome() {
        main.loadHomepage(UserType.TTBAGENT, Username);

    }
    public void Aprove(){
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.APPROVED);
        main.loadWorkflowPage(Username);
    }
    public void Reject(){
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.REJECTED);
        main.loadWorkflowPage(Username);
    }
    public void PendingReview(){
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.PENDINGREVIEW);
        main.loadWorkflowPage(Username);
    }
    public void ApprovedConditionally(){
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.APPROVEDWITHCONDITIONS);
        main.loadWorkflowPage(Username);
    }
    public void NeedsCorrections(){
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.NEEDSCORRECTIONS);
        main.loadWorkflowPage(Username);
    }
    public void MoveToNextApp(){

    }
}
