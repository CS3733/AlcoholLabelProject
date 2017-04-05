package com.emeraldElves.alcohollabelproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static com.emeraldElves.alcohollabelproject.LoginStatus.LOGGEDIN;

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

    public LoginController(){

    }
    public void login(ActionEvent e){
        System.out.println("Event called.");
        String username = usernameField.getText();
        String password = passwordField.getText();
        AuthenticatedUsersDatabase authDb = new AuthenticatedUsersDatabase(Main.database);
        if (authDb.isValidAccount(username, password)) {
            errorMsg.setVisible(false);
            //TODO: load the homepage.
            //new Main().loadHomepage(authDb.getAccountType(username, password), username);
        }
        else {
            errorMsg.setVisible(true);
        }
    }
}
