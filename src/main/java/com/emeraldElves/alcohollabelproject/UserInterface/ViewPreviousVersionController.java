package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.TTBAgentInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Date;

/**
 * Created by keionbis on 4/18/17.
 */
public class ViewPreviousVersionController {
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
        Date date = application.getApplication().getSubmissionDate();
        submissionDate.setText( DateHelper.dateToString(date));
        applicationID.setText( String.valueOf(application.getApplicationID()));
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
        origin.setText( productSource);
        applicantID.setText( String.valueOf(application.getApplication().getManufacturer().getRepresentativeID()));
        authorizedName.setText( application.getApplication().getManufacturer().getName());
        physicalAddress.setText( application.getApplication().getManufacturer().getPhysicalAddress());
        permitNum.setText( String.valueOf(application.getApplication().getManufacturer().getPermitNum()));
        phoneNum.setText(application.getApplication().getManufacturer().getPhoneNumber().getPhoneNumber());
        emailAddress.setText( application.getApplication().getManufacturer().getEmailAddress().getEmailAddress());
        alcoholContent.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
    }
}
