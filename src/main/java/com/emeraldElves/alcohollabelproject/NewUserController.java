package com.emeraldElves.alcohollabelproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Created by Essam on 4/4/2017.
 */
public class NewUserController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    VBox errorMsg;

    private Main main;
    private UserType userType;
    private String username;

    public NewUserController() {

    }

    public void init(Main main, UserType userType, String username) {
        this.main = main;
        this.username = username;
        this.userType = userType;
    }

    public void createTTBAgent(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (Main.database.insert("'" + username + "', '" + password + "'", "TTBAgentLogin")) {
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void createApplicant(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (Main.database.insert("'" + username + "', '" + password + "'", "ApplicantLogin")) {
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void login(ActionEvent e) {

    }
    public void GoHome(){
        main.loadHomepage();
    }
}
