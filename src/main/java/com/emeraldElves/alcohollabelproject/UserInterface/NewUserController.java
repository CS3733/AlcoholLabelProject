package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    TextField IDNum;
    @FXML
    TextField Name;
    @FXML
    TextField emailAddress;
    @FXML
    TextField phoneNumber;
    @FXML
    VBox errorMsg;
    @FXML
    Label typeLabel;
    @FXML
    DatePicker date;

    private Main main;

    public NewUserController() {

    }

    public void init(Main main) {
        this.main = main;
    }

    public void createTTBAgent(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String FullName = Name.getText();
        UserType userType = UserType.fromInt(Integer.parseInt(typeLabel.getText()));
        int IDnum = Integer.parseInt(IDNum.getText());
        java.util.Date newDate =DateHelper.getDate(date.getValue().getDayOfMonth(),date.getValue().getMonthValue() - 1,date.getValue().getYear());
        EmailAddress Email  = new EmailAddress(emailAddress.getText().toString());
        PhoneNumber PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        if (Storage.getInstance().applyForUser(new PotentialUser(FullName,username,IDnum,Email, PhoneNumber, userType,password, newDate))){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void createApplicant(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        UserType userType = UserType.fromInt(Integer.parseInt(typeLabel.getText()));
        String FullName = Name.getText();
        int IDnum = Integer.parseInt(IDNum.getText());
        java.util.Date newDate =DateHelper.getDate(date.getValue().getDayOfMonth(),date.getValue().getMonthValue() - 1,date.getValue().getYear());
        EmailAddress Email  = new EmailAddress(emailAddress.getText().toString());
        PhoneNumber PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        if (Storage.getInstance().applyForUser(new PotentialUser(FullName,username,IDnum,Email, PhoneNumber, userType,password, newDate))){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void GoHome(){
        main.loadHomepage();
    }
}
