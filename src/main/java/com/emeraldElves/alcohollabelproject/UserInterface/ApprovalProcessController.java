package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.*;
import com.emeraldElves.alcohollabelproject.updateCommands.ApplicationStatusChanger;
import com.emeraldElves.alcohollabelproject.updateCommands.ApproveCommand;
import com.emeraldElves.alcohollabelproject.updateCommands.RejectCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by keionbis on 4/5/17.
 */

public class ApprovalProcessController implements IController {
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
    TextArea qualifications;

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

    @FXML
    ComboBox assignUserBox;
    @FXML
    Button assignButton;
    @FXML
    Label assignErrorField;

    @FXML
    DatePicker ExpirationDate;

    ObservableList<String> assignUser = FXCollections.observableArrayList();


    public void init(Bundle bundle){
        this.init(bundle.getMain("main"), bundle.getApplication("app"));
    }

    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application = application;
        //Checks if super user
        if(Authenticator.getInstance().getUserType() == UserType.SUPERAGENT){
            assignUserBox.setVisible(true);
            assignUserBox.setDisable(false);
            assignButton.setVisible(true);
            assignButton.setDisable(false);
            //add all users to combo box
            List<String> userList = new ArrayList<>();
            userList = Storage.getInstance().getAllTTBUsernames();
            assignUser.addAll(userList);
            assignUserBox.setValue("Select a user");
            assignUserBox.setItems(assignUser);
        }

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
        permitNum.setText( application.getApplication().getManufacturer().getPermitNum());
        phoneNum.setText(application.getApplication().getManufacturer().getPhoneNumber().getPhoneNumber());
        emailAddress.setText( application.getApplication().getManufacturer().getEmailAddress().getEmailAddress());
        alcoholContent.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
    }

    public void GoHome() {
        main.loadFXML("/fxml/HomePage.fxml");

    }

    /**
     * Called when the assign button is clicked. This assigns the agent selected to the current
     * application and then returns to the view all applications screen.
     */
    public void assignToUser(){
        String userToAssign;
        userToAssign = assignUserBox.getValue().toString();
        if(userToAssign.equals("Select a user")){
            assignErrorField.setText("Select a user");
            return;
        }
        TTBAgentInterface agent = new TTBAgentInterface(userToAssign); // user to assign to
        //Log.console(application.getTtbAgentName());
        agent.addApplication(application);
        main.loadFXML("/fxml/SuperagentViewAllApplications.fxml");
    }

    public void Approve() {
        application.getApplication().setQualifications(qualifications.getText());//sets qualifications
        //need to update in database now
        ApplicationStatusChanger changer = new ApplicationStatusChanger();
        changer.changeStatus(new ApproveCommand(application, true));
        changer.commitUpdates();
        //Storage.getInstance().submitApplication(application,);
        main.loadFXML("/fxml/TTBWorkflowPage.fxml");

    }

    public void Reject() {
        ApplicationStatusChanger changer = new ApplicationStatusChanger();
        changer.changeStatus(new RejectCommand(application, reason.getText(), true));
        changer.commitUpdates();
        //Storage.getInstance().rejectApplication(application, reason.getText());
        main.loadFXML("/fxml/TTBWorkflowPage.fxml");
    }

    public void PendingReview() {
        application.setStatus(ApplicationStatus.PENDINGREVIEW);
        Storage.getInstance().submitApplication(application, Authenticator.getInstance().getUsername());
        main.loadFXML("/fxml/TTBWorkflowPage.fxml");
    }

    public void ApprovedConditionally() {
        application.setStatus(ApplicationStatus.APPROVEDWITHCONDITIONS);
        Storage.getInstance().submitApplication(application, Authenticator.getInstance().getUsername());
        main.loadFXML("/fxml/TTBWorkflowPage.fxml");
    }

    public void NeedsCorrections() {
        application.setStatus(ApplicationStatus.NEEDSCORRECTIONS);
        Storage.getInstance().submitApplication(application, Authenticator.getInstance().getUsername());
        main.loadFXML("/fxml/TTBWorkflowPage.fxml");
    }

    public void printPage(){
        main.printPage();
    }

    public void MoveToNextApp() {

    }
    public void viewLabel(){
        main.loadFXML("/fxml/DisplayLabel.fxml",application);
    }
}
