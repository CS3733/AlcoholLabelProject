package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.ResetPassword;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by keionbis on 4/25/17.
 */
public class ForgotPasswordController implements IController {
    @FXML
    TextField usernameField;
    @FXML
    Label InvalidAccountError;
    ResetPassword resetPassword = new ResetPassword();
    public void Reset(){
        if(Storage.getInstance().isValidUser(usernameField.getText())){
            resetPassword.resetEmail(usernameField.getText());
        }
        else{
            InvalidAccountError.setText("Not a valid username");
            Log.console(usernameField.getText());
            return;
        }


    }

    @Override
    public void init(Bundle data) {

    }
}
