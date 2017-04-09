package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.AlcoholDatabase;
import com.emeraldElves.alcohollabelproject.Data.ApplicationStatus;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.UserType;
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

    AlcoholDatabase alcoholDatabase;

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
        alcoholDatabase = new AlcoholDatabase(Main.database);
    }

    public void GoHome() {
        main.loadHomepage(UserType.TTBAGENT, Username);

    }

    public void Approve() {
        Date date = new Date();
        date.setYear(date.getYear() + 5);
        alcoholDatabase.approveApplication(application, Username, date);
        main.loadWorkflowPage(Username);
    }

    public void Reject() {
        alcoholDatabase.rejectApplication(application, reason.getText());
        main.loadWorkflowPage(Username);
    }

    public void PendingReview() {
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.PENDINGREVIEW);
        main.loadWorkflowPage(Username);
    }

    public void ApprovedConditionally() {
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.APPROVEDWITHCONDITIONS);
        main.loadWorkflowPage(Username);
    }

    public void NeedsCorrections() {
        alcoholDatabase.updateApplicationStatus(application, ApplicationStatus.NEEDSCORRECTIONS);
        main.loadWorkflowPage(Username);
    }

    public void MoveToNextApp() {

    }
}
