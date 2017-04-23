package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Created by keionbis on 4/21/17.
 */
public class UpdatePasswordController {
    private String currentUser;
    private String currentPassword;
    @FXML
    TextField CurrPassword, NewPassword1, NewPassword2;

    private StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public void updatePassword(String Update1, String Update2) {
        this.currentUser = Authenticator.getInstance().getUsername();
        this.currentUser = CurrPassword.getText();
        if (Storage.getInstance().isValidUser(currentUser, currentPassword)) {
            if (Update1.equals(Update2)) {

                Storage.getInstance().updatePassword(currentUser, passwordEncryptor.encryptPassword(Update1));

            } else {
                //return error
            }
        }
    }
}
