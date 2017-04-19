package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
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

    public static boolean onLoginPage = false;

    public void goHome() {
        onLoginPage = false;
        main.loadHomepage();
    }

    public void loadLog() {
        switch (Authenticator.getInstance().getUserType()) {
            case TTBAGENT:
                Authenticator.getInstance().logout();
                goHome();
                break;
            case APPLICANT:
                Authenticator.getInstance().logout();
                goHome();
                break;
            case SUPERAGENT:
                Authenticator.getInstance().logout();
                goHome();
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
                main.loadNewUserApplicationDisplayController();
                break;
            case BASIC:
                utility.setVisible(true);
                main.loadNewUserPage();
                break;
        }
    }

    public void extraFunction() {
        switch (Authenticator.getInstance().getUserType()) {
            case TTBAGENT:
                main.loadProfilePage();
                break;
            case APPLICANT:
                main.loadProfilePage();
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main = UISwitcher.getInstance().getMain();
        switch (Authenticator.getInstance().getUserType()) {
            case SUPERAGENT:
                extraButton.setVisible(true);
                extraButton.setText("REVIEW ALL APPLICATIONS");
                utility.setVisible(true);
                utility.setText("REVIEW POTENTIAL USERS");
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
                utility.setVisible(true);
                utility.setText("APPLY FOR ACCOUNT");
                logButton.setText("LOGIN");
                break;
        }
        if (onLoginPage) {
            logButton.setVisible(false);
        }
    }
}
