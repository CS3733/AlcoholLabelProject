package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Essam on 4/4/2017.
 */
public class NewUserController implements IController {
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
    @FXML
    Label accountError;
    @FXML
    Label emailError;
    @FXML
    Label phoneNumError;
    @FXML
    Label passwordError;
    @FXML
    Label permitNumError;
    @FXML
    Label nameError;
    @FXML
    Label addressError;
    @FXML
    Label repIDError;
    @FXML
    Tooltip passwordHint;
    @FXML
    ImageView isValid;

    private int repID;
    private Main main;
    private int userTypeInt = -1;
    private String FullName;
    private String password;
    private String address;
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
    }

    public void setUserTypeApplicant(){
        userTypeInt = 1;
        permitNumText.setDisable(false);
    }

    public void createPotentialUser() throws UnsupportedEncodingException {
        accountError.setText("");
        emailError.setText("");
        phoneNumError.setText("");
        passwordError.setText("");
        permitNumError.setText("");
        repIDError.setText("");
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
        if(Storage.getInstance().isValidUser(Email.getEmailAddress())){
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

        int permitNum = -1;

        if(permitNumText.isDisabled()){
            permitNum = -1;
        }


        if(permitNumText.isEditable() && !(permitNumText.getText().trim().isEmpty()) && !(userTypeInt == 0)){
            permitNum = Integer.parseInt(permitNumText.getText());//check if field is not null
        }
        else if(permitNumText.isEditable() && permitNumText.getText().trim().isEmpty() && !(userTypeInt == 0)){
            permitNumError.setText("Enter a valid permit number");
            return;
        }




        if(representativeID.getText().trim().isEmpty()){
            repIDError.setText("Enter a valid representative ID");
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
<<<<<<< HEAD
        password = EncryptPassword.encryptPassword(passwordField.getText());
        Email  = new EmailAddress(emailAddress.getText().toString());
        PhoneNumber = new PhoneNumber(phoneNumber.getText().toString());
        repID =(Integer.parseInt(representativeID.getText()));
=======
>>>>>>> develop
        password = passwordField.getText();
        permitNum = Integer.parseInt(representativeID.getText());//check if field is not null
        address = addressText.getText();//representative ID


        FullName = Name.getText();



        if (Storage.getInstance().applyForUser(new PotentialUser(FullName,repID ,Email, PhoneNumber, userType,
                password, newDate, permitNum, address))){
            errorMsg.setVisible(false);
            main.loadHomepage();
        } else {
            errorMsg.setVisible(true);
        }
    }

    public void checkPassword(){
        final Popup popup = new Popup();
        //popup.show();
        passwordHint.setText("Password must: \n - Contains atleast 8 Characters \n - A Uppercase character \n - A Lowercase character \n - A digit \n - A symbol");
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
