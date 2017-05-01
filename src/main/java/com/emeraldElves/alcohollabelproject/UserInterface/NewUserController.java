package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Essam on 4/4/2017.
 */
public class NewUserController implements IController {
    @FXML
    TextField Name, emailAddress, phoneNumber, permitNumText, addressText, representativeID, companyField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label errorMsg;
    @FXML
    RadioButton applicantBtn, agentBtn;
    @FXML
    Label accountError, emailError, phoneNumError, passwordError, permitNumError, nameError, addressError, companyError;
    @FXML
    Tooltip passwordHint;
    @FXML
    ImageView isValid;


    private String repID; //move this to application info
    String permitNum;
    private Main main;
    private int userTypeInt = -1;
    private String FullName;
    private String password;
    private String address;
    private String company;
    private PasswordStrengthChecker CheckStrength;
    private StrongPasswordEncryptor EncryptPassword = new StrongPasswordEncryptor();
    Image image;

    public NewUserController() {

    }

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"));
    }

    public void init(Main main) {
        this.main = main;
        ToggleGroup accountType = new ToggleGroup();
        applicantBtn.setToggleGroup(accountType);
        agentBtn.setToggleGroup(accountType);
        CheckStrength = new PasswordStrengthChecker();
        passwordHint.setText("Password must: \n Contains atleast 8 Characters \n 1 Uppercase character \n 1 Lowercase character \n A digit \n A symbol");

    }

    public void setUserTypeAgent(){
        userTypeInt = 0;
        //permitNumText.setText(null);
        permitNumText.setDisable(true);
        companyField.setDisable(true);
        representativeID.setDisable(true);
    }

    public void setUserTypeApplicant(){
        userTypeInt = 1;
        permitNumText.setDisable(false);
        companyField.setDisable(false);
        representativeID.setDisable(false);
    }

    public void createPotentialUser() throws UnsupportedEncodingException {
        accountError.setText("");
        emailError.setText("");
        phoneNumError.setText("");
        passwordError.setText("");
        permitNumError.setText("");
        addressError.setText("");
        nameError.setText("");
        if(!(applicantBtn.isSelected() || agentBtn.isSelected())) {
            accountError.setText("You need to select an account type");
            return;
        }
        if(!CheckStrength.isPasswordValid(passwordField.getText()))
        {
            passwordError.setText("Enter a valid Password");
            return;
        }

        EmailAddress Email  = new EmailAddress(emailAddress.getText().toString());
        if(Storage.getInstance().isValidUser(Email.getEmailAddress()) || Storage.getInstance().isCurrentNewApplicant(Email.getEmailAddress())){
            emailError.setText("There is already an account with this email");
            return;
        }
        PhoneNumber PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        if(!PhoneNumber.isValid()){
            phoneNumError.setText("Enter a valid phone number");
            return;
        }

        if(!Email.isValid()){
            emailError.setText("Enter a valid email address");
            return;
        }

        //permitNum = -1;

        if(permitNumText.isDisabled()){
            permitNum = "";
        }

        if(permitNumText.isEditable() && !(permitNumText.getText().trim().isEmpty()) && !(userTypeInt == 0)){
            permitNum = permitNumText.getText();//check if field is not null
        }
        else if(permitNumText.isEditable() && permitNumText.getText().trim().isEmpty() && !(userTypeInt == 0)){
            permitNumError.setText("Enter a valid permit number");
            return;
        }


//        if(representativeID.getText().trim().isEmpty()){
//            repIDError.setText("Enter a valid representative ID");
//            return;
//        }


        if (companyField.getText().trim().isEmpty()&&(userTypeInt == 1))
        {
            companyError.setText("Enter a valid company");
            return;
        }


        if (addressText.getText().trim().isEmpty())
        {
            addressError.setText("Enter a valid address");
            return;
        }


        if (Name.getText().trim().isEmpty())
        {
            nameError.setText("Enter a valid name");
            return;
        }


        //Setting all the fields for the new potential user

        UserType userType = UserType.fromInt(userTypeInt);
        java.util.Date newDate = new Date();
         Email  = new EmailAddress(emailAddress.getText().toString());
         PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());

        password = EncryptPassword.encryptPassword(passwordField.getText());
        Email  = new EmailAddress(emailAddress.getText().toString());
        PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        if(representativeID.getText().trim().isEmpty()){
            repID="";
        } else repID =representativeID.getText();
        permitNum = permitNumText.getText();
        address = addressText.getText();
        if(companyField.getText().trim().isEmpty()){
            company="";
        } else company = companyField.getText();

        FullName = Name.getText();


        if (Storage.getInstance().applyForUser(new PotentialUser(FullName, repID, Email, PhoneNumber, userType,
                password, newDate, permitNum, address, company))){
            //errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void checkPassword(){
        final Popup popup = new Popup();
        //popup.show();
        passwordHint.setText("Password must: \n - Contains at least 8 Characters \n - A Uppercase character \n - A Lowercase character \n - A digit \n - A symbol");
        passwordError.setVisible(false);
            if (!CheckStrength.isPasswordValid(passwordField.getText())) {
                image = new Image("/images/X.png");
                isValid.setImage(image);
            } else if (CheckStrength.isPasswordValid(passwordField.getText())) {
                image = new Image("/images/Tick.png");
                isValid.setImage(image);
            }
    }

    public void GoHome(){
        main.loadHomepage();
    }
}
