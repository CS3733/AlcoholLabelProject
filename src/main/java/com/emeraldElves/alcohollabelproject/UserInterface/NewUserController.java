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
    PasswordField passwordField;
    @FXML
    TextField representativeID;
    @FXML
    TextField Name;
    @FXML
    TextField emailAddress;
    @FXML
    TextField phoneNumber;
    @FXML
    VBox errorMsg;
    @FXML
    DatePicker date;
    @FXML
    TextField permitNumText;
    @FXML
    TextField addressText;

    private Main main;

    public NewUserController() {

    }

    public void init(Main main) {
        this.main = main;
    }

    public void createTTBAgent(){
        //Setting all the fields for the new potential user
        String password = passwordField.getText();
        String FullName = Name.getText();
        UserType userType = UserType.TTBAGENT;
        int repID = Integer.parseInt(representativeID.getText());//representative ID
        java.util.Date newDate =DateHelper.getDate(date.getValue().getDayOfMonth(),date.getValue().getMonthValue() - 1,date.getValue().getYear());
        EmailAddress Email  = new EmailAddress(emailAddress.getText().toString());
        PhoneNumber PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        int permitNum = Integer.parseInt(permitNumText.getText());
        String address = addressText.getText();
        
        if (Storage.getInstance().applyForUser(new PotentialUser(FullName,repID ,Email, PhoneNumber, userType,
                password, newDate, -1, address))){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void createApplicant(){
        //Setting all the fields for the new potential user
        String password = passwordField.getText();
        String FullName = Name.getText();
        UserType userType = UserType.APPLICANT;
        int repID = Integer.parseInt(representativeID.getText());//representative ID
        java.util.Date newDate =DateHelper.getDate(date.getValue().getDayOfMonth(),date.getValue().getMonthValue() - 1,date.getValue().getYear());
        EmailAddress Email  = new EmailAddress(emailAddress.getText().toString());
        PhoneNumber PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        int permitNum = Integer.parseInt(permitNumText.getText());
        String address = addressText.getText();

        if (Storage.getInstance().applyForUser(new PotentialUser(FullName,repID ,Email, PhoneNumber, userType,
                password, newDate, permitNum, address))){
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
