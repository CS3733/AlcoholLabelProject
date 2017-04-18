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

    private Main main;


    public void goHome() {
        main.loadHomepage();
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
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main = UISwitcher.getInstance().getMain();
        switch (Authenticator.getInstance().getUserType()) {
            case SUPERAGENT:
                utility.setVisible(true);
                utility.setText("POTENTIAL USERS");
                logButton.setText("LOG OUT");
                break;
            case TTBAGENT:
                utility.setVisible(true);
                utility.setText("APPLICATIONS");
                logButton.setText("LOG OUT");
                break;
            case APPLICANT:
                utility.setVisible(true);
                utility.setText("MY APPLICATIONS");
                logButton.setText("LOG OUT");
                break;
            default:
                utility.setVisible(false);
                logButton.setText("LOGIN");
                break;
        }
    }
}
