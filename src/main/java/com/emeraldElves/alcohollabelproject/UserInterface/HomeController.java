package com.emeraldElves.alcohollabelproject.UserInterface;

/**
 * Created by Harry and Joe on 4/2/2017.
 */

import com.emeraldElves.alcohollabelproject.Data.AlcoholDatabase;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

import java.util.ArrayList;
import java.util.Random;

public class HomeController {
    public ArrayList<Label> mostRecentLabels;
    public ArrayList<SubmittedApplication> mostRecentSubmissions;
    public List<SubmittedApplication> submitted;


    private UserType usertype;
    private String username;
    private Main main;
    @FXML
    private Button utility;
    @FXML
    private TextField searchbox;
    @FXML
    private Button logButton;
    @FXML
    private Label label0;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;


    public HomeController() {
        mostRecentLabels = new ArrayList<>();
        mostRecentSubmissions = new ArrayList<>();
        submitted = Storage.getInstance().getRecentlyApprovedApplications(4);
    }

    // TODO: put FXML in correct folder

    /**
     * Loads homepage
     */
    public void utilityButton() {
        switch (usertype) {
            case TTBAGENT:
                main.loadWorkflowPage(username);
                break;
            case APPLICANT:
                main.loadApplicantWorkflowPage(username);
                break;
        }
    }

    public void loadLog() {
        switch (usertype) {
            case TTBAGENT:
                main.loadHomepage(UserType.BASIC, "");
                break;
            case APPLICANT:
                main.loadHomepage(UserType.BASIC, "");
                break;
            case BASIC:
                main.loadLoginPage();
                break;
        }
    }

    public void createNewUser(){
        main.loadNewUserPage(usertype, username);
    }

    public void searchDatabase() {
        main.loadSearchPage(usertype, username, searchbox.getText());
    }

    public void feelingThirsty(){
        List<SubmittedApplication> applications = Storage.getInstance().getApprovedApplications();
        Random random = new Random();
        SubmittedApplication application;
        if (applications.isEmpty()) {
            application = null;
        } else {
            int pos = random.nextInt(applications.size());
            application = applications.get(pos);
        }
        if(application != null)
            main.loadDetailedSearchPage(application, application.getApplication().getAlcohol().getBrandName(), usertype, username);
    }

    public void init(Main main, UserType usertype, String username) {
        this.usertype = usertype;
        this.username = username;
        this.main = main;
        for (int i = 0; i < submitted.size(); i++) {
            switch (i) {
                case 0:
                    label0.setText(submitted.get(i).getApplication().getAlcohol().getBrandName() + "    -    " + submitted.get(i).getApplication().getAlcohol().getName());
                    label0.setOnMouseClicked(event -> {
                        main.loadDetailedSearchPage(submitted.get(0), submitted.get(0).getApplication().getAlcohol().getBrandName(), usertype, username);
                    });
                    break;
                case 1:
                    label1.setText(submitted.get(i).getApplication().getAlcohol().getBrandName() + "    -    " + submitted.get(i).getApplication().getAlcohol().getName());
                    label1.setOnMouseClicked(event -> {
                        main.loadDetailedSearchPage(submitted.get(1), submitted.get(1).getApplication().getAlcohol().getBrandName(), usertype, username);
                    });
                    break;
                case 2:
                    label2.setText(submitted.get(i).getApplication().getAlcohol().getBrandName() + "    -    " + submitted.get(i).getApplication().getAlcohol().getName());
                    label2.setOnMouseClicked(event -> {
                        main.loadDetailedSearchPage(submitted.get(2), submitted.get(2).getApplication().getAlcohol().getBrandName(), usertype, username);
                    });
                    break;
                case 3:
                    label3.setText(submitted.get(i).getApplication().getAlcohol().getBrandName() + "    -    " + submitted.get(i).getApplication().getAlcohol().getName());
                    label3.setOnMouseClicked(event -> {
                        main.loadDetailedSearchPage(submitted.get(3), submitted.get(3).getApplication().getAlcohol().getBrandName(), usertype, username);
                    });
                    break;
            }
        }
        switch (usertype) {
            case TTBAGENT:
                utility.setVisible(true);
                utility.setText("Applications");
                logButton.setText("Log Out");
                break;
            case APPLICANT:
                utility.setVisible(true);
                utility.setText("Applications");
                logButton.setText("Log Out");
                break;
            default:
                utility.setVisible(false);
                logButton.setText("Log In");
                break;
        }


    }

}
