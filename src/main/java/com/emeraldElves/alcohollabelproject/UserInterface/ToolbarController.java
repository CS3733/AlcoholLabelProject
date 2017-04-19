package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.LogManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kylec on 4/18/2017.
 */
public class ToolbarController implements Initializable {

    @FXML
    private Button utility;
    @FXML
    private Button logButton;
    @FXML
    private Button extraButton;

    private Main main;


    public void goHome() {
        main.loadHomepage();
    }

    public void goBack(){
        LogManager.getInstance().logAction("ToolbarController", "Going back");
    }

    public void loadLog() {
        switch (Authenticator.getInstance().getUserType()) {
            case TTBAGENT:
                Authenticator.getInstance().logout();
                main.loadHomepage();
                break;
            case APPLICANT:
                Authenticator.getInstance().logout();
                main.loadHomepage();
                break;
            case SUPERAGENT:
                Authenticator.getInstance().logout();
                main.loadHomepage();
                break;
            case BASIC:
                main.loadLoginPage();
                break;
        }
    }

    public void utilityButton() {
        switch (Authenticator.getInstance().getUserType()) {
            case TTBAGENT:
                main.loadWorkflowPage();
                break;
            case APPLICANT:
                main.loadApplicantWorkflowPage();
                break;
            case SUPERAGENT:
                main.loadNewUserPage();
                break;
        }
    }

    public void extraFunction() {
        switch (Authenticator.getInstance().getUserType()) {
            case TTBAGENT:
                main.loadProfilePage("placeholder");
                break;
            case APPLICANT:
                main.loadProfilePage("placeholder");
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main = UISwitcher.getInstance().getMain();
        switch (Authenticator.getInstance().getUserType()) {
            case SUPERAGENT:
                extraButton.setVisible(false);
                utility.setVisible(true);
                utility.setText("CREATE USERS");
                logButton.setText("LOG OUT");
                break;
            case TTBAGENT:
                extraButton.setVisible(true);
                utility.setVisible(true);
                utility.setText("APPLICATIONS");
                logButton.setText("LOG OUT");
                break;
            case APPLICANT:
                extraButton.setVisible(true);
                utility.setVisible(true);
                utility.setText("MY APPLICATIONS");
                logButton.setText("LOG OUT");
                break;
            default:
                extraButton.setVisible(false);
                utility.setVisible(false);
                logButton.setText("LOGIN");
                break;
        }
    }
}
