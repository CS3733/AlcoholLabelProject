package com.emeraldElves.alcohollabelproject;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by keionbis on 4/4/17.
 */

public class ApplicantWorkflowController {
    @FXML
    Button UpdateApplications;
    String Username;
    Main main;
    private List<SubmittedApplication> applications;

    @FXML
    ListView<String> list;
    AlcoholDatabase alcoholDatabase;

    public void init(String Username, Main main) {
        this.Username = Username;
        this.main = main;
        alcoholDatabase = new AlcoholDatabase(Main.database);
        applications = alcoholDatabase.getApplicationsByApplicantUsername(Username);
        List<String> applicationNames = new ArrayList<>();
        for (SubmittedApplication application : applications) {
            String name = "";
            name += application.getApplication().getAlcohol().getBrandName();
            switch (application.getStatus()) {
                case APPROVED:
                    name += " - Approved";
                    break;
                case REJECTED:
                    name += " - Rejected: " + application.getTtbMessage();
                    break;
                case PENDINGREVIEW:
                    name += " - Pending Review";
                    break;
            }
            applicationNames.add(name);
        }
        ObservableList<String> items = FXCollections.observableList(applicationNames);
        list.setItems(items);
    }

    public SubmittedApplication getSelectedApplication() {
        int i = list.getSelectionModel().getSelectedIndex();
        return applications.get(i);
    }

    public void ApplicationWorkflow() {
        main.loadUpdateApplicationPage(getSelectedApplication(), Username);
    }

    public void GoHome() {
        main.loadHomepage(UserType.APPLICANT, Username);
    }

    public void MakeNewApplication() {
        main.loadNewApplicationPage(Username);
    }
}
