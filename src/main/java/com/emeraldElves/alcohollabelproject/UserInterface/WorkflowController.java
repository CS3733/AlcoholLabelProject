package com.emeraldElves.alcohollabelproject.UserInterface;

/**
 * Created by Harry and Joe on 4/3/2017.
 */

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.ListIterator;
import java.util.Date;
import java.text.SimpleDateFormat;


public class WorkflowController {
    @FXML
    Hyperlink id1;
    @FXML
    Hyperlink id2;
    @FXML
    Hyperlink id3;
    @FXML
    Hyperlink id4;
    @FXML
    Hyperlink id5;
    @FXML
    Label fanciful1;
    @FXML
    Label fanciful2;
    @FXML
    Label fanciful3;
    @FXML
    Label fanciful4;
    @FXML
    Label fanciful5;
    @FXML
    Label brand1;
    @FXML
    Label brand2;
    @FXML
    Label brand3;
    @FXML
    Label brand4;
    @FXML
    Label brand5;
    @FXML
    Label date1;
    @FXML
    Label date2;
    @FXML
    Label date3;
    @FXML
    Label date4;
    @FXML
    Label date5;


    // TODO: array sizes are currently arbitrary; possibly change to ArrayList<> in the future
    public SubmittedApplication[] submittedApps = new SubmittedApplication[50];
    public String[] ids = new String[50];
    public String[] appBrandNames = new String[50];
    public String[] appNames = new String[50];
    public String[] dates = new String[50];
    public int i = 0;

    // for init
    Main main;

    /**
     * public void init()
     * Initializes the FXML page
     *
     * @param main
     */
    public void init(Main main) {
        this.main = main;

        // set global values
        queryDatabase(Authenticator.getInstance().getUsername());

        // load application values into labels on FXML
        id1.setText(ids[0]);
        id1.setOnMouseClicked(e -> {
            main.loadApprovalProcessController(submittedApps[0]);
        });
        id2.setText(ids[1]);
        id2.setOnMouseClicked(e -> {
            main.loadApprovalProcessController(submittedApps[1]);
        });
        id3.setText(ids[2]);
        id3.setOnMouseClicked(e -> {
            main.loadApprovalProcessController(submittedApps[2]);
        });
        id4.setText(ids[3]);
        id4.setOnMouseClicked(e -> {
            main.loadApprovalProcessController(submittedApps[3]);
        });
        id5.setText(ids[4]);
        id5.setOnMouseClicked(e -> {
            main.loadApprovalProcessController(submittedApps[4]);
        });
        fanciful1.setText(appNames[0]);
        fanciful2.setText(appNames[1]);
        fanciful3.setText(appNames[2]);
        fanciful4.setText(appNames[3]);
        fanciful5.setText(appNames[4]);
        brand1.setText(appBrandNames[0]);
        brand2.setText(appBrandNames[1]);
        brand3.setText(appBrandNames[2]);
        brand4.setText(appBrandNames[3]);
        brand5.setText(appBrandNames[4]);
        date1.setText(dates[0]);
        date2.setText(dates[1]);
        date3.setText(dates[2]);
        date4.setText(dates[3]);
        date5.setText(dates[4]);
    }

    public void logout() {
        Authenticator.getInstance().logout();
        main.loadHomepage();
    }

    public void goHome() {
        main.loadHomepage();
    }

    /**
     * queryDatabase()
     * Queries database for queued associated applications; stores them in arrays
     *
     * @param ttbUsername
     */
    public void queryDatabase(String ttbUsername) {
        List<SubmittedApplication> list = Storage.getInstance().getAssignedApplications(ttbUsername);
        for (ListIterator<SubmittedApplication> itr = list.listIterator(); itr.hasNext(); ) {
            SubmittedApplication current = itr.next();
            submittedApps[i] = current;
            ids[i] = String.valueOf(current.getApplicationID());
            appBrandNames[i] = current.getApplication().getAlcohol().getBrandName();
            appNames[i] = current.getApplication().getAlcohol().getName();

            // format date into string
            Date currentDate = current.getApplication().getSubmissionDate();

            dates[i] = DateHelper.dateToString(currentDate);
            i++;
        }
    }

    /**
     * linkToApplication1(), linkToApplication2(), etc.
     * Links to the workflowActionController page for the associated function
     */
    // TODO: make all of these one function
    public void linkToApplication1() {
        main.loadWorkflowActionsPage(submittedApps[0]);
    }

    public void linkToApplication2() {
        main.loadWorkflowActionsPage(submittedApps[1]);
    }

    public void linkToApplication3() {
        main.loadWorkflowActionsPage(submittedApps[2]);
    }

    public void linkToApplication4() {
        main.loadWorkflowActionsPage(submittedApps[3]);
    }

    public void linkToApplication5() {
        main.loadWorkflowActionsPage(submittedApps[4]);
    }
}
