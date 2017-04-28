package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.ApplicantInterface;
import com.emeraldElves.alcohollabelproject.Data.PhoneNumber;
import com.emeraldElves.alcohollabelproject.Log;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.emeraldElves.alcohollabelproject.Authenticator;

/**
 * Created by Joe on 4/18/2017.
 */
public class ProfileController implements IController {
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
    private ApplicantInterface applicant;

    private String emailAddress;
    private int representativeID;
    private String permitNum;
    private String address;
    private PhoneNumber phoneNum;

    public ProfileController() {

    }

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"));
    }

    // note: default field value is empty string ("")

    public void init(Main main) {
        this.main = main;

        emailAddress = Authenticator.getInstance().getUsername();
        applicant = new ApplicantInterface(emailAddress);
        representativeID = applicant.getApplicant().getRepresentativeIDFromDB(emailAddress);
        Log.console(representativeID);
        permitNum = applicant.getApplicant().getPermitNumFromDB(emailAddress);
        address = applicant.getApplicant().getAddressFromDB(emailAddress);
        phoneNum = new PhoneNumber(applicant.getApplicant().getPhoneNum());
        //phoneNum = applicant.getApplicant().getPhoneNumFromDB(emailAddress);

        // set text values to current values
        representativeIDField.setText(Integer.toString(representativeID));
        permitNumField.setText(permitNum);
        addressField.setText(address);
        phoneNumField.setText(phoneNum.getPhoneNumber());
        emailAddressField.setText(emailAddress);
    }

    // functions - simply modify fields when user changes them
    public void modifyRepresentativeID() {
        representativeID = Integer.valueOf(representativeIDField.getText());
        applicant.getApplicant().setRepresentativeID(emailAddress, representativeID);
    }
    public void modifyPermitNum() {
        permitNum = permitNumField.getText();
        applicant.getApplicant().setPermitNum(emailAddress, permitNum);
    }
    public void modifyAddress() {
        address = addressField.getText();
        applicant.getApplicant().setAddress(emailAddress, address);
    }
    public void modifyPhoneNum() {
        phoneNum = new PhoneNumber(phoneNumField.getText());
        applicant.getApplicant().setPhoneNum(emailAddress, phoneNum.getPhoneNumber());
    }
    public void modifyEmailAddress() {
        emailAddress = emailAddressField.getText();
        applicant.getApplicant().setEmailAddress();
    }

    public void updatePassword(){
        main.loadFXML("/fxml/ChangeUserPassword.fxml");
    }

    public void returnHome()
    {
        modifyRepresentativeID();
        modifyPermitNum();
        modifyAddress();
        modifyPhoneNum();
        modifyEmailAddress();
        main.loadHomepage();
    }
}
