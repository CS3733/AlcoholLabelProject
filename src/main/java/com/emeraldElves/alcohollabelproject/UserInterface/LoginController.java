package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.Controller;
import com.emeraldElves.alcohollabelproject.Data.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Created by Essam on 4/4/2017.
 */
public class LoginController implements Controller {
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
        ToolbarController.onLoginPage=true;
    }

    public void login(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (Authenticator.getInstance().login(UserType.SUPERAGENT, username, password)) {
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else if(Authenticator.getInstance().login(UserType.APPLICANT, username, password)){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else if (Authenticator.getInstance().login(UserType.TTBAGENT, username, password)){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }
}
