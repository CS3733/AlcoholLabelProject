package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.PasswordStrengthChecker;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.UnsupportedEncodingException;

/**
 * Created by Essam on 4/4/2017.
 */
public class LoginController implements IController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    VBox errorMsg;
    StrongPasswordEncryptor EncryptPassword = new StrongPasswordEncryptor();
    PasswordStrengthChecker strengthChecker = new PasswordStrengthChecker();
    private Main main;

    public LoginController() {

    }

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"));
    }

    public void init(Main main) {
        this.main = main;
        ToolbarController.onLoginPage=true;
    }

    public void login(ActionEvent e) throws UnsupportedEncodingException {
        String username = usernameField.getText();
        String password = passwordField.getText();
//        Log.console(Authenticator.getInstance().login(UserType.TTBAGENT, username, password));
//        Log.console(Storage.getInstance().getAgentPassword(username));
//        Log.console(EncryptPassword.checkPassword(password,Storage.getInstance().getAgentPassword(username)));

        if (Authenticator.getInstance().login(UserType.SUPERAGENT, username, password)) {
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else if(Authenticator.getInstance().login(UserType.APPLICANT, username, password)&&(EncryptPassword.checkPassword(password, Storage.getInstance().getUserPassword(username)))){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else if (Authenticator.getInstance().login(UserType.TTBAGENT, username, password)&&(EncryptPassword.checkPassword(password, Storage.getInstance().getAgentPassword(username)))){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
        if(!strengthChecker.isPasswordValid(password)&&!Authenticator.getInstance().isSuperAgentLoggedIn()){
            main.loadFXML("/fxml/UpdatePassword.fxml");
        }
    }
    public void loadForgotPasswordController(){
        main.loadFXML("/fxml/Forgotpassword.fxml");
    }
}
