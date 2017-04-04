package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

import static com.emeraldElves.alcohollabelproject.Main.loadFXML;

public class NewApplicationController {
    @FXML
    TextField repIDNoTextField;
    @FXML
    TextField permitNoTextField;
    @FXML
    RadioButton international;
    @FXML
    RadioButton domestic;
    @FXML
    RadioButton beer;
    @FXML
    RadioButton wine;
    @FXML
    TextField alcoholName;
    @FXML
    TextField brandNameField;
    @FXML
    TextField addressField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextField emailAddressField;
    @FXML
    TextField signatureField;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField alcoholContentField;
    @FXML
    TextField wineVintageYearField;
    @FXML
    TextField pHLevelField;
    @FXML
    Button saveApplication;
    @FXML
    Button cancelApplication;
    @FXML
    Button nextPageBtn;
    @FXML
    Button submitBtn;
    @FXML
    Button logoutBtn;
    @FXML
    Label error1;
    @FXML
    Label error2;
    @FXML
    Label permitNoErrorField;
    @FXML
    Label addressErrorField;
    @FXML
    Label phoneNumErrorField;
    @FXML
    Label emailErrorField;


    //Creates a database to store the alcohol information from form
    AlcoholDatabase alcoholDB = new AlcoholDatabase(Main.database);

    //Stores representative ID number
    int repIDNo = -1; //means they didn't enter a rep ID num
    //Stores the permit number
    int permitNo = 0;
    //Stores the physical address of the applicant/company
    String physicalAddress= null;
    //Stores the applicant's email
    EmailAddress applicantEmail = null;
    //Stores the applicant's phone number
    PhoneNumber applicantPhone = null;
    //Stores the manufacturer's info
    ManufacturerInfo appManInfo = null;


    public void nextPage(){

        Boolean formFilled=false;

        if(permitNoTextField.getText().isEmpty()) {
            permitNoErrorField.setText("Please fill in your permit number.");
            //return;
        }
        if(addressField.getText().isEmpty()) {
            addressErrorField.setText("Please fill in the physical address of your company.");
            //return;
        }
        if(phoneNumberField.getText().isEmpty()) {
            phoneNumErrorField.setText("Please fill in the contact number.");
            //return;
        }
        if(emailAddressField.getText().isEmpty()) {
            emailErrorField.setText("Please fill in the contact email.");
            //return;
        }

        if(!emailAddressField.getText().isEmpty()&&!phoneNumberField.getText().isEmpty()&&
                !addressField.getText().isEmpty()&&!permitNoTextField.getText().isEmpty()){
            formFilled=true;
        }
        if(formFilled){
            //Store the applicant information now that the form is filled

            //NEED TO CHECK IF THIS IS ACTUALLY AN INT!!!
            if(!repIDNoTextField.getText().isEmpty()) {
                repIDNo=Integer.parseInt(repIDNoTextField.getText());
            }
            //Parses text from permit number field and stores it as an int
            //permitNo=Integer.parseInt(permitNoTextField.getText());

            //Gets text from email address field and stores it as a string
            physicalAddress=addressField.getText();

            //Gets text from the email address field and stores it as an EmailAddress
            applicantEmail = new EmailAddress(emailAddressField.getText());

            //Gets text from the email address field and stores it as a PhoneNumber
            applicantPhone = new PhoneNumber(phoneNumberField.getText());

            //also check if emails or phone numbers are valid!

            //form is now filled in so go to page 2 of label application
            Main.loadFXML("/fxml/new-app-page2.FXML");
        }
    }

    public void submitApp() {
        /*radio buttons need to be put in a group so that they can function, these two groups are for
        product source and the product type radio buttons.
         */
        ToggleGroup productType = new ToggleGroup();
        beer.setToggleGroup(productType);
        wine.setToggleGroup(productType);

        ToggleGroup productSource = new ToggleGroup();
        international.setToggleGroup(productSource);
        domestic.setToggleGroup(productSource);

        /*These next few if statements are all checking to see if there is anything in the application that hasnt been filled out that
        needs to be filled out. for the fields specific to wine, it will only throw an error if wine is selected in the product type
        radio button group
         */
        if(productType.getSelectedToggle() == null) {
            error2.setText("Please select Whether your product is international or domestic");
            return;
        }
        if(productSource.getSelectedToggle() == null) {
            error2.setText("Please select what Type of Alcohol you are selling");
            return;
        }
        if(alcoholName.getText().isEmpty()) {
            error2.setText("Please fill in your alcohols name");
            return;
        }
        if(brandNameField.getText().isEmpty()) {
            error2.setText("Please fill in your brand name");
            return;
        }
        if(alcoholContentField.getText().isEmpty()) {
            error2.setText("Please fill in the alcohol content of your beverage");
            return;
        }
        if(productType.getSelectedToggle() == wine && wineVintageYearField.getText().isEmpty()) {
            error2.setText("Please fill in the wine vintage year");
            return;
        }
        if(productType.getSelectedToggle() == wine && pHLevelField.getText().isEmpty()) {
            error2.setText("Please fill in the ph level");
            return;
        }
        if(datePicker == null) {
            error2.setText("Please fill in the date of your alcohol");
            return;
        }
        if(signatureField.getText().isEmpty()) {
            error2.setText("Please fill in the signature field");
            return;
        }



        //Creates a database to store the alcohol information from form
        AlcoholDatabase alcoholDB = new AlcoholDatabase(Main.database);

        //Parses the text from the email address field and stores it as an EmailAddress
        EmailAddress applicantEmail = new EmailAddress(emailAddressField.getText());

        //Parses the text from the email address field and stores it as a PhoneNumber
        PhoneNumber applicantPhone = new PhoneNumber(phoneNumberField.getText());

        //Creates a ManufacturerInfo from the address, brand
        ManufacturerInfo appManInfo = new ManufacturerInfo("Person", addressField.getText(), brandNameField.getText(),
                Integer.parseInt(repIDNoTextField.getText()), Integer.parseInt(permitNoTextField.getText()),
                applicantPhone, applicantEmail);

        /*
        The next 5 lines of code are all placeholders that may or may not be edited depending on if certain conditions
        are met
         */
        ProductSource pSource = ProductSource.DOMESTIC;
        AlcoholType mainAlcType = AlcoholType.OTHER;
        AlcoholInfo.Wine winePlaceholder = new AlcoholInfo.Wine(0, 0);


        //Checking if the product is domestic or imported by checking the product source radio button group
        if(productSource.getSelectedToggle() == domestic) {
            pSource = ProductSource.DOMESTIC;
        }
        if(productSource.getSelectedToggle() == international) {
            pSource = ProductSource.IMPORTED;
        }

        if(productType.getSelectedToggle() == beer) {
            mainAlcType = AlcoholType.BEER;
        }
        if(productType.getSelectedToggle() == wine) {
            mainAlcType = AlcoholType.WINE;
            winePlaceholder = new AlcoholInfo.Wine(Double.parseDouble(pHLevelField.getText()), Integer.parseInt(wineVintageYearField.getText()));
        }

        AlcoholInfo appAlcoholInfo =  new AlcoholInfo(Integer.parseInt(alcoholContentField.getText()), alcoholName.getText(), brandNameField.getText(),
                pSource, mainAlcType, winePlaceholder);

        java.sql.Date newDate = java.sql.Date.valueOf(datePicker.getValue());
        ApplicationInfo appInfo = new ApplicationInfo(newDate, appManInfo, appAlcoholInfo);

        ApplicationStatus placeholder = ApplicationStatus.APPROVED;

        List<SubmittedApplication> appliers = new ArrayList();
        Applicant applicant = new Applicant(appliers);

        SubmittedApplication newApp = new SubmittedApplication(appInfo, placeholder, applicant);
        alcoholDB.submitApplication(newApp);

        Main.loadFXML("/fxml/mainGUI.FXML");
    }

    public void cancelApp() {

        Main.loadFXML("/fxml/mainGUI.FXML");
    }
    public void saveApp() {
    }
    public void logout() {
    }
}