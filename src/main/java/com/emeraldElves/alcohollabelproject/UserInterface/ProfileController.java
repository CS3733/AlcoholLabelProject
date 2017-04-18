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

    private int representativeID;
    private int permitNum;
    private String address;
    private String phoneNum;
    private String emailAddress;

    public ProfileController() {
        representativeID = 0;
        permitNum = 0;
        address = "";
        phoneNum = "";
        emailAddress = "";
    }

    public ProfileController(int representativeID, int permitNum, String address, String phoneNum, String emailAddress) {
        this.representativeID = representativeID;
        this.permitNum = permitNum;
        this.address = address;
        this.phoneNum = phoneNum;
        this.emailAddress = emailAddress;
    }

    // note: default field value is empty string ("")

    public void init(Main main) {
        this.main = main;

        // set text values to current values
        representativeIDField.setText(Integer.toString(representativeID));
        permitNumField.setText(Integer.toString(permitNum));
        addressField.setText(address);
        phoneNumField.setText(phoneNum);
        emailAddressField.setText(emailAddress);
    }

    // functions - simply modify fields when user changes them
    public void modifyRepresentativeID() {
        representativeID = Integer.valueOf(representativeIDField.getText());
    }
    public void modifyPermitNum() {
        permitNum = Integer.valueOf(permitNumField.getText());
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
