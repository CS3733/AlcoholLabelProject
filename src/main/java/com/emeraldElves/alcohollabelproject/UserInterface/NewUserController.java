package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Date;

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
    TextField permitNumText;
    @FXML
    TextField addressText;
    @FXML
    RadioButton applicantBtn;
    @FXML
    RadioButton agentBtn;

    private Main main;
    private int userTypeInt = -1;

    public NewUserController() {

    }

    public void init(Main main) {
        this.main = main;
        ToggleGroup accountType = new ToggleGroup();
        applicantBtn.setToggleGroup(accountType);
        agentBtn.setToggleGroup(accountType);
    }

    public void setUserTypeAgent(){
        userTypeInt = 0;
    }

    public void setUserTypeApplicant(){
        userTypeInt = 1;
    }

    public void createPotentialUser(){
        //Setting all the fields for the new potential user
        String password = passwordField.getText();
        String FullName = Name.getText();
        UserType userType = UserType.fromInt(userTypeInt);
        int repID = Integer.parseInt(representativeID.getText());//representative ID
        java.util.Date newDate = new Date();
        EmailAddress Email  = new EmailAddress(emailAddress.getText().toString());
        PhoneNumber PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        int permitNum;
        if(permitNumText.isDisabled()){
            permitNum = -1;
        }
        else{
            permitNum = Integer.parseInt(permitNumText.getText());//check if field is not null
        }
        String address = addressText.getText();

        if (Storage.getInstance().applyForUser(new PotentialUser(FullName,repID ,Email, PhoneNumber, userType,
                password, newDate, permitNum, address))){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }


    //shouldn't be needed anymore
    /*
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
    */

    public void GoHome(){
        main.loadHomepage();
    }
}
