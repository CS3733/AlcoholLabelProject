package com.emeraldElves.alcohollabelproject;

/**
 * Created by Harry and Joe on 4/2/2017.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class HomeController {
    public Button loginBtn;
    public ArrayList<Label> mostRecentLabels;
    public ArrayList<SubmittedApplication> mostRecentSubmissions;

    public LoginStatus status;
    private UserType usertype;
    private String username;
    private Main main;
    @FXML
    private Button utility;
    @FXML
    private TextField searchbox;
    @FXML
    private Button logButton;


    public HomeController(LoginStatus status) {
        mostRecentLabels = new ArrayList<Label>();
        mostRecentSubmissions = new ArrayList<SubmittedApplication>();
        this.status = status;
    }

    // TODO: put FXML in correct folder

    /**
     * Loads homepage
     */
    public void utilityButton(){
        switch (usertype) {
            case TTBAGENT:
                main.loadWorkflowPage(username);
                break;
            case APPLICANT:
                main.loadApplicantWorkflowPage(username);
                break;
        }
    }
    public void loadLog(){
        switch (usertype) {
            case TTBAGENT:
                main.loadHomepage(UserType.BASIC,"");
                break;
            case APPLICANT:
                main.loadHomepage(UserType.BASIC,"");
                break;
            case BASIC:
                main.loadLoginPage();
                break;
        }
    }

    public void searchDatabase(){
        main.l
    }

    public void init(Main main, UserType usertype, String username){
        this.usertype=usertype;
        this.username=username;
        this.main=main;
        switch (usertype){
            case TTBAGENT:
                utility.setVisible(true);
                utility.setText("Applications");
                logButton.setText("Log Out");
                break;
            case APPLICANT:
                utility.setVisible(true);
                utility.setText("Applications");
                logButton.setText("Log Out");
            default:
                utility.setVisible(false);
                logButton.setText("Log In");
                break;
        }
    }
    
}
