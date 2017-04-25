package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.ApplicantInterface;
import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.ApplicationStatus;
import com.emeraldElves.alcohollabelproject.Data.SavedApplication;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keionbis on 4/4/17.
 */

public class ApplicantWorkflowController implements IController {
    Main main;
    private ApplicantInterface applicantInterface;
    int numSavedApplications;

    @FXML
    ListView<String> list;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"));
    }

    public void init(Main main) {
        this.main = main;
        applicantInterface = new ApplicantInterface(Authenticator.getInstance().getUsername());
        List<String> applicationNames = new ArrayList<>();
        List<SubmittedApplication> applications = applicantInterface.getSubmittedApplications();
        List<SavedApplication> savedApplications = applicantInterface.getSavedApplications();
        applications.sort((a, b) -> {
            if (a.getStatus() == b.getStatus()) {
                return a.getApplicationID() - b.getApplicationID();
            } else {
                return a.getStatus().ordinal() - b.getStatus().ordinal();
            }
        });
        numSavedApplications = 0;
        for(SavedApplication application : savedApplications){
            String name = "";
            name += application.getApplicationID();
            name += " - Saved";
            applicationNames.add(name);
            numSavedApplications ++;
        }

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
    // make global variable for number of saved applications
    // check if its before the thing
    public SubmittedApplication getSelectedApplication() {
        int i = list.getSelectionModel().getSelectedIndex();
        //maybe this works
        return applicantInterface.getSubmittedApplications().get(i - numSavedApplications);
    }

    public SavedApplication getSelectedSavedApplication() {
        int i = list.getSelectionModel().getSelectedIndex();
        return applicantInterface.getSavedApplications().get(i);
    }

    public void viewApplication(){
        //TODO do this for saved application
        main.loadFXML("/fxml/DetailedSearchPage.fxml",getSelectedApplication(), "");
    }

    public void reviseApplication() {
        if (list.getSelectionModel().getSelectedIndex() < numSavedApplications) {
            main.loadFXML("/fxml/newApplication.fxml", getSelectedSavedApplication());
        } else {
            main.loadFXML("/fxml/newApplication.fxml", getSelectedApplication());

        }
    }

    public void ApplicationWorkflow() {
        if(getSelectedApplication().getStatus() == ApplicationStatus.APPROVED) {
            main.loadFXML("/fxml/updateApprovedApplication.fxml",getSelectedApplication());
        }
    }

    public void GoHome() {
        main.loadHomepage();
    }

    public void MakeNewApplication() {
        main.loadFXML("/fxml/newApplication.fxml");
    }
}
