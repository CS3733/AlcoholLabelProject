package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.*;
import com.emeraldElves.alcohollabelproject.TTBAgent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by keionbis on 4/5/17.
 */

public class ApprovalProcessController {
    Main main;
    SubmittedApplication application;
    private TTBAgentInterface agentInterface;
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

    @FXML
    Label applicantID;

    @FXML
    TextArea reason;

    @FXML
    Label authorizedName;

    @FXML
    Label physicalAddress;

    @FXML
    Label permitNum;

    @FXML
    Label phoneNum;
    @FXML
    Label emailAddress;

    @FXML
    Label alcoholContent;

    @FXML
            Label applicationID;


    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application = application;
        agentInterface = new TTBAgentInterface(Authenticator.getInstance().getUsername());
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
            case DISTILLEDSPIRITS:
                type = "Distilled Spirits";
                break;
        }
        alcoholType.setText(type);
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Date date = application.getApplication().getSubmissionDate();
        submissionDate.setText("Submission date: " + dateFormat.format(date));
        applicationID.setText("Application ID: " + String.valueOf(application.getApplicationID()));
        company.setText("Company: " + application.getApplication().getManufacturer().getCompany());
        String productSource = "";
        switch (application.getApplication().getAlcohol().getOrigin()) {
            case IMPORTED:
                productSource = "Imported";
                break;
            case DOMESTIC:
                productSource = "Domestic";
                break;
        }
        origin.setText("Source: " + productSource);
        applicantID.setText("Representative ID: " + String.valueOf(application.getApplication().getManufacturer().getRepresentativeID()));
        authorizedName.setText("Authorized name: " + application.getApplication().getManufacturer().getName());
        physicalAddress.setText("Physical Address: " + application.getApplication().getManufacturer().getPhysicalAddress());
        permitNum.setText("Permit number: " + String.valueOf(application.getApplication().getManufacturer().getPermitNum()));
        phoneNum.setText("Phone number: " + application.getApplication().getManufacturer().getPhoneNumber().getPhoneNumber());
        emailAddress.setText("Email address: " + application.getApplication().getManufacturer().getEmailAddress().getEmailAddress());
        alcoholContent.setText("Alcohol content: " + String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
    }

    public void GoHome() {
        main.loadHomepage();

    }

    public void Approve() {
        Date date = new Date();
        date.setYear(date.getYear() + 5 - 1900);
        agentInterface.approveApplication(application,date);
        //Storage.getInstance().approveApplication(application, Authenticator.getInstance().getUsername(), date);
        main.loadWorkflowPage();
    }

    public void Reject() {
        agentInterface.rejectApplication(application,reason.getText());
        //Storage.getInstance().rejectApplication(application, reason.getText());
        main.loadWorkflowPage();
    }

    public void PendingReview() {
        application.setStatus(ApplicationStatus.PENDINGREVIEW);
        Storage.getInstance().submitApplication(application, Authenticator.getInstance().getUsername());
        main.loadWorkflowPage();
    }

    public void ApprovedConditionally() {
        application.setStatus(ApplicationStatus.APPROVEDWITHCONDITIONS);
        Storage.getInstance().submitApplication(application, Authenticator.getInstance().getUsername());
        main.loadWorkflowPage();
    }

    public void NeedsCorrections() {
        application.setStatus(ApplicationStatus.NEEDSCORRECTIONS);
        Storage.getInstance().submitApplication(application, Authenticator.getInstance().getUsername());
        main.loadWorkflowPage();
    }

    public void MoveToNextApp() {

    }
    public void viewLabel(){
        //main.loadLabelPage(application.)
    }
}
