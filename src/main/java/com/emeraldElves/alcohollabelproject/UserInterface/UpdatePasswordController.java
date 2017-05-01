package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.PasswordStrengthChecker;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Created by keionbis on 4/21/17.
 */
public class UpdatePasswordController implements IController {
    private String currentUser;
    private String currentPassword;
    @FXML
    TextField NewPassword1, NewPassword2;
    @FXML
    VBox errorMsg;
    @FXML
    Label passwordError;

    private StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    private PasswordStrengthChecker checkPassword = new PasswordStrengthChecker();
    Main main = new Main();

    public void UpdatePassword() {
        passwordError.setText("");
        this.currentUser = Authenticator.getInstance().getUsername();
        if (Storage.getInstance().isValidUser(currentUser)) {
            if(checkPassword.isPasswordValid(NewPassword1.getText())) {
                if (NewPassword1.getText().equals(NewPassword2.getText())) {
                    Storage.getInstance().updatePassword(currentUser, passwordEncryptor.encryptPassword(NewPassword1.getText()));
                    main.loadFXML("/fxml/HomePage.fxml");

                } else {
                    errorMsg.setVisible(true);
                    return;

                }
            }
            else{
                passwordError.setText("Please Enter a valid Password");
                return;
            }
        }
    }

    @Override
    public void init(Bundle data) {

    }
}
