package com.emeraldElves.alcohollabelproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * Created by Essam on 4/4/2017.
 */
public class LoginController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    VBox errorMsg;

    private Main main;

    public LoginController() {

    }

    public void init(Main main) {
        this.main = main;
    }

    public void loginAsAgent() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        AuthenticatedUsersDatabase authDb = new AuthenticatedUsersDatabase(Main.database);
        if (authDb.isValidTTBAgent(username, password)) {
            errorMsg.setVisible(false);
            LoginState.getInstance().login(UserType.TTBAGENT, username);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void loginAsApplicant() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        AuthenticatedUsersDatabase authDb = new AuthenticatedUsersDatabase(Main.database);
        if (authDb.isValidApplicant(username, password)) {
            errorMsg.setVisible(false);
            LoginState.getInstance().login(UserType.APPLICANT, username);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void login(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        AuthenticatedUsersDatabase authDb = new AuthenticatedUsersDatabase(Main.database);
        if (authDb.isValidAccount(username, password)) {
            errorMsg.setVisible(false);
            if (authDb.isValidApplicant(username, password)) {
//                main.loadHomepage(UserType.APPLICANT, username);
            }
            if (authDb.isValidTTBAgent(username, password)) {
//                main.loadHomepage(UserType.TTBAGENT, username);
            }
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void GoHome() {
        LoginState.getInstance().logout();
        main.loadHomepage();
    }
}
