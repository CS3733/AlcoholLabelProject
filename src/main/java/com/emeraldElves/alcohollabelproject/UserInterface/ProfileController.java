package com.emeraldElves.alcohollabelproject.UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Joe on 4/18/2017.
 */
public class ProfileController {
    // FXML
    @FXML
    TextField representativeIDField;
    @FXML
    TextField permitNumField;
    @FXML
    TextField addressField;
    @FXML
    TextField phoneNumField;
    @FXML
    TextField emailAddressField;

    private Main main;

    private String representativeID;
    private String permitNum;
    private String address;
    private String phoneNum;
    private String emailAddress;

    public ProfileController() {
        representativeID = "";
        permitNum = "";
        address = "";
        phoneNum = "";
        emailAddress = "";
    }

    public ProfileController(String representativeID, String permitNum, String address, String phoneNum, String emailAddress) {
        this.representativeID = representativeID;
        this.permitNum = permitNum;
        this.address = address;
        this.phoneNum = phoneNum;
        this.emailAddress = emailAddress;
    }

    // note: default field value is empty string ("")

    public void init(Main main) {
        this.main = main;
    }

    // functions - simply modify fields when user changes them
    public void modifyRepresentativeID() {
        representativeID = representativeIDField.getText();
    }
    public void modifyPermitNum() {
        permitNum = permitNumField.getText();
    }
    public void modifyAddress() {
        address = addressField.getText();
    }
    public void modifyPhoneNum() {
        phoneNum = phoneNumField.getText();
    }
    public void modifyEmailAddress() {
        emailAddress = emailAddressField.getText();
    }
}
