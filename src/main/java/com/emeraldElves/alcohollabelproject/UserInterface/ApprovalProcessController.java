package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.*;
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


    public void init(Main main, String Username, SubmittedApplication application) {
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
//        date.setYear(date.getYear() - 1900);
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
        main.loadHomepage(UserType.TTBAGENT, Username);

    }

    public void Approve() {
        Date date = new Date();
        date.setYear(date.getYear() + 5 - 1900);
        Storage.getInstance().approveApplication(application, Username, date);
        main.loadWorkflowPage(Username);
    }

    public void Reject() {
        Storage.getInstance().rejectApplication(application, reason.getText());
        main.loadWorkflowPage(Username);
    }

    public void PendingReview() {
        application.setStatus(ApplicationStatus.PENDINGREVIEW);
        Storage.getInstance().submitApplication(application, Username);
        main.loadWorkflowPage(Username);
    }

    public void ApprovedConditionally() {
        application.setStatus(ApplicationStatus.APPROVEDWITHCONDITIONS);
        Storage.getInstance().submitApplication(application, Username);
        main.loadWorkflowPage(Username);
    }

    public void NeedsCorrections() {
        application.setStatus(ApplicationStatus.NEEDSCORRECTIONS);
        Storage.getInstance().submitApplication(application, Username);
        main.loadWorkflowPage(Username);
    }

    public void MoveToNextApp() {

    }
}
