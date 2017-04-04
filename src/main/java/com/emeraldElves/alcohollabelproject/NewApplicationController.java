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

    //Initializes and temporarily stores applicant's info
    int repIDNo = -1; //means they didn't enter a rep ID num
    int permitNo = 0;
    String physicalAddress= null;
    EmailAddress applicantEmail = null;
    PhoneNumber applicantPhone = null;

    //Stores the manufacturer's info
    ManufacturerInfo appManInfo = null;

    //Initializes and temporarily stores data fields for alc info
    ProductSource pSource = null;
    AlcoholType alcType = null;
    String alcName = null;
    String brandName = null;
    int alcContent = 0;
    AlcoholInfo.Wine wineType = null; //null if type is beer (this doesn't get edited)

    //Stores the alcohol info from the form
    AlcoholInfo appAlcoholInfo = null;


    public void nextPage(){

        Boolean formFilled=false;

        //errors are printed if required fields are not filled in
        if(permitNoTextField.getText().isEmpty()) {
            permitNoErrorField.setText("Please fill in your permit number.");
        }
        if(addressField.getText().isEmpty()) {
            addressErrorField.setText("Please fill in the physical address of your company.");
        }
        if(phoneNumberField.getText().isEmpty()) {
            phoneNumErrorField.setText("Please fill in the contact number.");
        }
        if(emailAddressField.getText().isEmpty()) {
            emailErrorField.setText("Please fill in the contact email.");
        }

        //check if required fields are filled
        if(!emailAddressField.getText().isEmpty()&&!phoneNumberField.getText().isEmpty()&&
                !addressField.getText().isEmpty()&&!permitNoTextField.getText().isEmpty()){
            formFilled=true;
        }
        if(formFilled) { //Store the applicant information now that the form is filled

            //NEED TO CHECK IF THIS IS ACTUALLY AN INT!!!
            if(!repIDNoTextField.getText().isEmpty()) {
                repIDNo=Integer.parseInt(repIDNoTextField.getText());
            }
            //Parses text from permit number field and stores it as an int
            permitNo=Integer.parseInt(permitNoTextField.getText()); //CHECK INT!!!

            //Gets text from email address field and stores it as a string
            physicalAddress=addressField.getText();

            //Gets text from the email address field and stores it as an EmailAddress
            applicantEmail = new EmailAddress(emailAddressField.getText());

            //Gets text from the email address field and stores it as a PhoneNumber
            applicantPhone = new PhoneNumber(phoneNumberField.getText());

            //also check if emails or phone numbers are valid!!!!!!

            //form is now filled in so go to page 2 of label application
            Main.loadFXML("/fxml/new-app-page2.FXML");
        }
    }


    public void submitApp() {

        Boolean formFilled=false;

        //radio buttons are grouped in the following groups:
        //product type group
        ToggleGroup productType = new ToggleGroup();
        beer.setToggleGroup(productType);
        wine.setToggleGroup(productType);

        //product source group
        ToggleGroup productSource = new ToggleGroup();
        international.setToggleGroup(productSource);
        domestic.setToggleGroup(productSource);

        //errors are printed if required fields are not filled in
        if(productSource.getSelectedToggle() == null) {
            error2.setText("Please select whether the alcohol is imported or domestic.");
        }
        if(productType.getSelectedToggle() == null) {
            error2.setText("Please select whether the alcohol is a beer or wine.");
        }
        if(brandNameField.getText().isEmpty()) {
            error2.setText("Please fill in the brand name.");
        }
        if(alcoholContentField.getText().isEmpty()) {
            error2.setText("Please fill in the alcohol content (as a %) of the beverage.");
        }
        if (productType.getSelectedToggle() == wine){
            int vintageYr=0;
            double pH=0.0;
            if(!wineVintageYearField.getText().isEmpty()) {
                vintageYr=Integer.parseInt(wineVintageYearField.getText()); //CHECK IF INPUT INTEGER!
            }
            if(!pHLevelField.getText().isEmpty()) {
                pH=Double.parseDouble(pHLevelField.getText()); //CHECK IF INPUT INTEGER!
            }
            wineType = new AlcoholInfo.Wine(pH, vintageYr);
        }
        if(datePicker == null) {
            error2.setText("Please select the date.");
        }
        if(signatureField.getText().isEmpty()) {
            error2.setText("Please fill in the signature field.");
        }

        //check if required fields are filled in
        if((productType.getSelectedToggle() != null) && (productSource.getSelectedToggle() != null) &&
                !brandNameField.getText().isEmpty() && !alcoholContentField.getText().isEmpty() &&
                (datePicker != null) && !signatureField.getText().isEmpty()){
            formFilled=true;
        }

        if(formFilled) {
            //Checking if the product is domestic or imported by checking the product source radio button group
            if (productSource.getSelectedToggle() == domestic) {
                pSource = ProductSource.DOMESTIC;
            }
            else if (productSource.getSelectedToggle() == international) {
                pSource = ProductSource.IMPORTED;
            }

            //Checking if the product is a beer or wine by checking the product source radio button group
            if (productType.getSelectedToggle() == beer) {
                alcType = AlcoholType.BEER;
            }
            else if (productType.getSelectedToggle() == wine) {
                alcType = AlcoholType.WINE;
            }

            //sets alc info fields
            alcName = alcoholName.getText();
            brandName = brandNameField.getText();
            alcContent = Integer.parseInt(alcoholContentField.getText()); //CHECK IF INTEGER

            //sets the alcohol info
            appAlcoholInfo = new AlcoholInfo(alcContent, alcName, brandName, pSource, alcType, wineType);

            //creates and sets the date value
            java.sql.Date newDate = java.sql.Date.valueOf(datePicker.getValue());

            // Creates a new application info and sets data
            ApplicationInfo appInfo = new ApplicationInfo(newDate, appManInfo, appAlcoholInfo);

            //!!!!!placeholder for applicant's submitted applications!!!!!
            List<SubmittedApplication> appList = new ArrayList();
            //appList.add(newApp); <---need to be able to add this application to appList in workflow

            //Create applicant to store in submitted application
            Applicant applicant = new Applicant(appList);

            //Create a SubmittedApplication
            SubmittedApplication newApp = new SubmittedApplication(appInfo, ApplicationStatus.PENDINGREVIEW, applicant);

            //Submit the new application to the database
            //alcoholDB.submitApplication(newApp);

            //Go back to homepage
            Main.loadFXML("/fxml/mainGUI.FXML");
        }
    }

    public void cancelApp() {
        //Go back to homepage
        Main.loadFXML("/fxml/mainGUI.FXML");
    }
    public void saveApp() {
    }
    public void logout() {
    }
}