package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.ApplicantInterface;
import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.*;
import com.emeraldElves.alcohollabelproject.Log;
import com.emeraldElves.alcohollabelproject.LogManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;

public class UpdateApplicationController implements IController{

    public ApplicationStatus status;//get status from database

    @FXML
    private TextField repIDNoTextField, permitNoTextField, alcoholName, brandNameField;
    @FXML
    private TextField addressField, phoneNumberField, emailAddressField, signatureField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField alcoholContentField, wineVintageYearField, pHLevelField;
    @FXML
    private Label welcomeApplicantLabel;
    @FXML
    private Button cancelApplication, submitBtn, submitLabel, saveApplication;
    @FXML
    private Label pSourceErrorField, pTypeErrorField, brandNameErrorField, alcContentErrorField, signatureErrorField;
    @FXML
    private TextField varietalText, appellationText, formulaText, serialText, extraInfoText;
    @FXML
    private Label varietalErrorField, serialErrorField, wineNumErrorField;
    @FXML
    private CheckBox certOfApproval, certOfExemption, distinctiveApproval;
    @FXML
    private TextField distinctiveText; //relates to distinctive approval
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox pTypeSelect, pSourceSelect, stateSelect;

    //Options for the comboBox fields
    private ObservableList<String> sourceList = FXCollections.observableArrayList("Imported", "Domestic");
    private ObservableList<String> typeList = FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits");
    private ObservableList<String> stateList = FXCollections.observableArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC",
            "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH",
            "OK", "OR", "MD", "MA", "MI", "MN", "MS", "MO", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");

    //Data for application type
    private ApplicationType appType;
    private ProxyLabelImage proxyLabelImage;

    //Applicant's info
    private String username;
    private ApplicantInterface applicant;
    private EmailAddress emailAddress;
    private String permitNum; //needs to be a string!!!!
    private String address;
    private PhoneNumber phoneNum;
    private String representativeID; //needs to be a string!!!!

    //Alcohol info
    private ProductSource pSource;
    private AlcoholType alcType;
    private String alcName;
    private String brandName;
    private String alcContent;
    private AlcoholInfo.Wine wineType = null;
    private String formula;
    private String serialNum; //needs to be a string!!!!
    private String extraInfo;
    private File file;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"), bundle.getApplication("app"));
    }

    private Main main;

    private SubmittedApplication application;
    private SubmittedApplication newApplication;

    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application=application;
        Log.console(application);
        setUpApp();
    }

    public void setUpApp(){
        username= Authenticator.getInstance().getUsername();
        applicant = new ApplicantInterface(username);
        Log.console(username);
        welcomeApplicantLabel.setText("Welcome, " + applicant.getApplicant().getNamefromDB(username) + ".");
        emailAddress = new EmailAddress(applicant.getApplicant().getEmailAddress());
        permitNum = applicant.getApplicant().getPermitNumFromDB(username);
        address = applicant.getApplicant().getAddress();
        phoneNum = new PhoneNumber(applicant.getApplicant().getPhoneNum());
        representativeID = applicant.getApplicant().getRepresentativeID();
        repIDNoTextField.setText(""+representativeID);
        permitNoTextField.setText(String.valueOf(permitNum));
        addressField.setText(String.valueOf(address));
        phoneNumberField.setText((phoneNum.getPhoneNumber()));
        emailAddressField.setText((emailAddress.getEmailAddress()));

        datePicker.setValue(LocalDate.now());
//        stateSelect.setValue("State Abb.");
//        stateSelect.setItems(stateList);
//        pSourceSelect.setValue("Select a product source");
//        pSourceSelect.setItems(sourceList);
//        pTypeSelect.setValue("Select a product type");
//        pTypeSelect.setItems(typeList);

        //Application Type
        ApplicationType applicationType = application.getApplication().getApplicationType();
        if(applicationType.isLabelApproval()){
            certOfApproval.setSelected(true);
        }
        if(!applicationType.getStateOnly().equals("")){
            certOfExemption.setSelected(true);
            stateSelect.setValue(applicationType.getStateOnly());
            stateSelect.setDisable(false);
        }
        if(applicationType.getBottleCapacity() != -1){
            distinctiveApproval.setSelected(true);
            distinctiveText.setText("" + applicationType.getBottleCapacity());
            distinctiveText.setDisable(false);
        }
        //end application type

        //Image
        String imageURL = application.getImage().getFileName();
        if(!imageURL.equals("")) {
            Log.console("Image path: " + imageURL);
            File file = new File("Labels/" + imageURL);
            Image tempImage = new Image(file.toURI().toString());
            imageView.setImage(tempImage);
            this.proxyLabelImage = new ProxyLabelImage("Labels/"+ imageURL);
        }
        //End image

        if (application.getApplication().getAlcohol().getOrigin() == ProductSource.DOMESTIC) {
            pSourceSelect.setValue("Domestic");
        } else if (application.getApplication().getAlcohol().getOrigin() == ProductSource.IMPORTED) {
            pSourceSelect.setValue("Imported");
        }

        if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
            pTypeSelect.setValue("Malt Beverages");
        } else if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            pTypeSelect.setValue("Wine");
        } else if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.DISTILLEDSPIRITS) {
            pTypeSelect.setValue("Distilled Spirits");
        }

        alcoholName.setText(String.valueOf(application.getApplication().getAlcohol().getName()));
        brandNameField.setText(String.valueOf(application.getApplication().getAlcohol().getBrandName()));
        alcoholContentField.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
        formulaText.setText(String.valueOf(application.getApplication().getAlcohol().getFormula()));
        serialText.setText(String.valueOf(application.getApplication().getAlcohol().getSerialNumber()));
        extraInfoText.setText(String.valueOf(application.getApplication().getExtraInfo()));
        if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            wineVintageYearField.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().vintageYear));
            pHLevelField.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().pH));
            varietalText.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().grapeVarietal));
            appellationText.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().appellation));
        }

        disableAllFields();

        if(application.getUpdatesSelected().contains("label")){
            submitLabel.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("alcContent")){
            alcoholContentField.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("vintYr")){
            wineVintageYearField.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("pH")){
            pHLevelField.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("formula")){
            formulaText.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("stateSelect")){
            stateSelect.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("alcoholName")){
            alcoholName.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("brandName")){
            brandNameField.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("address")){
            addressField.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("extraInfo")){
            extraInfoText.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("repID")){
            repIDNoTextField.setDisable(false);
        }
        if(application.getUpdatesSelected().contains("date")){
            datePicker.setDisable(false);
        }
    }

    public void setUpdatedAppInfo(){

        if (pTypeSelect.getValue().equals("Wine")) {
            int vintageYr = 0;
            double pH = 0.0;
            String varietal = "";
            String appellation = "";
            if (!wineVintageYearField.getText().isEmpty())
                vintageYr = Integer.parseInt(wineVintageYearField.getText());
            if (!pHLevelField.getText().isEmpty())
                pH = Double.parseDouble(pHLevelField.getText());
            if (!varietalText.getText().isEmpty())
                varietal = varietalText.getText();
            if (!appellationText.getText().isEmpty())
                appellation = appellationText.getText();
            wineType = new AlcoholInfo.Wine(pH, vintageYr, varietal, appellation);
        }

        if (pSourceSelect.getValue().equals("Domestic")) {
            pSource = ProductSource.DOMESTIC;
        } else if (pSourceSelect.getValue().equals("Imported")) {
            pSource = ProductSource.IMPORTED;
        }

        if (pTypeSelect.getValue().equals("Malt Beverages") ){
            alcType = AlcoholType.BEER;
        } else if (pTypeSelect.getValue().equals("Wine")) {
            alcType = AlcoholType.WINE;
        } else if (pTypeSelect.getValue().equals("Distilled Spirits")) {
            alcType = AlcoholType.DISTILLEDSPIRITS;
        }

        alcName = alcoholName.getText();
        brandName = brandNameField.getText();
        alcContent = alcoholContentField.getText();
        serialNum = serialText.getText();
        if (formulaText.getText().isEmpty()) {
            formula = " ";
        } else formula = formulaText.getText();
        if (extraInfoText.getText().isEmpty()) {
            extraInfo = " ";
        } else extraInfo = extraInfoText.getText();

        //appType
        boolean labelApproval = certOfApproval.isSelected();
        String stateOnly;
        if(certOfExemption.isSelected()){ stateOnly = stateSelect.getValue().toString();}//Maybe change this
        else { stateOnly = "";}
        int bottleCapacity;
        if(distinctiveApproval.isSelected()){ bottleCapacity = Integer.parseInt(distinctiveText.getText());}
        else { bottleCapacity = -1;}//know this for future
        appType = new ApplicationType(labelApproval,stateOnly,bottleCapacity);
        //END appType



        AlcoholInfo appAlcoholInfo = new AlcoholInfo(alcContent, alcName, brandName, pSource, alcType, wineType, serialNum, formula);
        ManufacturerInfo appManInfo = new ManufacturerInfo(applicant.getApplicant().getNamefromDB(username), address, "company", "" + representativeID,
                permitNum, phoneNum, emailAddress);
        Date newDate= DateHelper.getDate(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue() - 1, datePicker.getValue().getYear());
        ApplicationInfo appInfo= new ApplicationInfo(newDate, appManInfo, appAlcoholInfo, extraInfo, appType);

        newApplication = new SubmittedApplication(appInfo, ApplicationStatus.APPROVED, applicant.getApplicant());
        newApplication.setApplicationID(application.getApplicationID());//setting id for updating
        newApplication.setImage(proxyLabelImage);
    }


    public void submitApp() {

        LogManager.getInstance().logAction("NewApplicationController", "Logged Click from first page of the new Application");
        Storage.getInstance().saveUpdateHistory(application,username);
        Boolean formFilled = false;
        Boolean fieldsValid = false;

        //errors are printed only if required fields are not filled in

        //malt beverages: don't need alcohol content
        if (!isDouble(alcoholContentField)) {
            alcContentErrorField.setText("Please enter a valid number.");
        } else {
            alcContentErrorField.setText("");
        }

        if (alcoholContentField.getText().isEmpty()) {
            alcContentErrorField.setText("Please fill in the alcohol percentage.");
        } else if (!isDouble(alcoholContentField)) {
            alcContentErrorField.setText("Please enter a valid number.");
        } else {
            alcContentErrorField.setText("");
        }

        if (signatureField.getText().isEmpty()) {
            signatureErrorField.setText("Please fill in the signature field.");
        } else {
            signatureErrorField.setText("");
        }
        if (pTypeSelect.getValue().equals("Wine")) {
            if (!isInt(wineVintageYearField) || !isDouble(pHLevelField)) {
                wineNumErrorField.setText("Please enter a valid number.");
            } else {
                wineNumErrorField.setText("");
            }
        }

        //check if required fields are filled in
        if (!alcoholContentField.getText().isEmpty() && !signatureField.getText().isEmpty()) {
            formFilled = true;
        }


        //check if fields are valid
        if (isDouble(alcoholContentField)) {
            if (pTypeSelect.getValue().equals("Wine")) {
                if (isInt(wineVintageYearField) && isDouble(pHLevelField)) {
                    fieldsValid = true;
                }
            } else fieldsValid = true;
        }

        if (formFilled && fieldsValid) {

            setUpdatedAppInfo();

            boolean success = Storage.getInstance().updateApplication(this.newApplication, this.username);
            main.loadHomepage();
        }
        else{
            Log.console("All fields not valid");
        }
    }

    public void cancelApp() {
        main.loadFXML("/fxml/ApplicantWorkflowPage.fxml");

    }

    public boolean isInt(TextField txt) {
        try {
            Integer.parseInt(txt.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(TextField txt) {
        try {
            Double.parseDouble(txt.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void disableAllFields() {
        pSourceSelect.setDisable(true);
        pTypeSelect.setDisable(true);
        alcoholName.setDisable(true);
        brandNameField.setDisable(true);
        alcoholContentField.setDisable(true);
        formulaText.setDisable(true);
        serialText.setDisable(true);
        extraInfoText.setDisable(true);
        if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            wineVintageYearField.setDisable(true);
            pHLevelField.setDisable(true);
            varietalText.setDisable(true);
            appellationText.setDisable(true);
        }
    }

    public void exemptionChecked() {
        stateSelect.setDisable(!certOfExemption.isSelected());
    }

    public void distinctiveChecked() {
        distinctiveText.setDisable(!distinctiveApproval.isSelected());
    }

    public void submitImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*jpeg", "*png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        java.nio.file.Path source = Paths.get((file.getPath()));
        java.nio.file.Path targetDir = Paths.get("Labels");
        try {
            Files.createDirectories(targetDir);//in case target directory didn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = (System.currentTimeMillis() + ".jpeg");
        java.nio.file.Path target = targetDir.resolve(fileName);// create new path ending with `name` content
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = new Image(target.toUri().toString());
        imageView.setImage(image);
        proxyLabelImage = new ProxyLabelImage(fileName);
        //application.setImage(proxyLabelImage);
    }


}