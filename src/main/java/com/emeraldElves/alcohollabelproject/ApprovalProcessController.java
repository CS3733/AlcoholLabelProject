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
    }

    public void GoHome() {
        main.loadHomepage(UserType.TTBAGENT, Username);

    }
    public void Aprove(){
        application.setStatus(ApplicationStatus.APPROVED);
        //WorkflowActionsController.
    }
    public void Reject(){
        application.setStatus(ApplicationStatus.REJECTED);
    }
    public void PendingReview(){
        application.setStatus(ApplicationStatus.PENDINGREVIEW);
    }
    public void ApprovedConditionally(){
        application.setStatus(ApplicationStatus.APPROVEDWITHCONDITIONS);
    }
    public void NeedsCorrections(){
        application.setStatus(ApplicationStatus.NEEDSCORRECTIONS);
    }
    public void MoveToNextApp(){

    }
}
