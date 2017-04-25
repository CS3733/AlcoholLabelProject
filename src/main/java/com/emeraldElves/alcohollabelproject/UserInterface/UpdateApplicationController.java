package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.*;
import com.emeraldElves.alcohollabelproject.Data.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateApplicationController {
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
    private ObservableList<String> stateList = FXCollections.observableArrayList("AL","AK","AZ","AR","CA","CO","CT","DE","DC",
            "FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH",
            "OK","OR","MD","MA","MI","MN","MS","MO","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");

    //Data for application type
    private ApplicationType appType;
    private ProxyLabelImage proxyLabelImage;

    //Applicant's info
    private String username;
    private ApplicantInterface applicant;
    private EmailAddress emailAddress;
    private int permitNum;
    private String address;
    private PhoneNumber phoneNum;
    private int representativeID;

    //Alcohol info
    private ProductSource pSource;
    private AlcoholType alcType;
    private String alcName;
    private String brandName;
    private int alcContent;
    private AlcoholInfo.Wine wineType = null;
    private String formula;
    private String serialNum; //needs to be a string!!!!
    private String extraInfo;
    private  File file;

    private Main main;

    private SubmittedApplication application;

    public void init(Main main){
        this.main = main;

        username= Authenticator.getInstance().getUsername();
        applicant = new ApplicantInterface(username);
        Log.console(username);
        welcomeApplicantLabel.setText("Welcome, " + applicant.getApplicant().getNamefromDB(username) + ".");
        emailAddress = new EmailAddress(applicant.getApplicant().getEmailAddress());
        permitNum = applicant.getApplicant().getPermitNumFromDB(username);
        address = applicant.getApplicant().getAddress();
        phoneNum = new PhoneNumber(applicant.getApplicant().getPhoneNum());
        representativeID = applicant.getApplicant().getRepresentativeID();
        repIDNoTextField.setText(String.valueOf(representativeID));
        permitNoTextField.setText(String.valueOf(permitNum));
        addressField.setText(String.valueOf(address));
        phoneNumberField.setText((phoneNum.getPhoneNumber()));
        emailAddressField.setText((emailAddress.getEmailAddress()));

        datePicker.setValue(LocalDate.now());
        stateSelect.setValue("State Abb.");
        stateSelect.setItems(stateList);
        pSourceSelect.setValue("Select a product source");
        pSourceSelect.setItems(sourceList);
        pTypeSelect.setValue("Select a product type");
        pTypeSelect.setItems(typeList);
    }
    public void init(Main main, SubmittedApplication application) {
        init(main);
        this.application = application;

        if (application.getApplication().getAlcohol().getOrigin() == ProductSource.DOMESTIC) {
            pSourceSelect.setValue("Domestic");
        } else if (application.getApplication().getAlcohol().getOrigin() == ProductSource.IMPORTED) {
            pSourceSelect.setValue("Imported");
        }

        if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
            pTypeSelect.setValue("Beer");
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


//        allowedUpdatesSelect.valueProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue ov, String oldVal, String newVal) {
//                if(allowedUpdatesSelect.getValue().equals("Delete non-mandatory label information")||
//                        allowedUpdatesSelect.getValue().equals("Reposition label information")||
//                        allowedUpdatesSelect.getValue().equals("Change label format (color, font, size of label, etc)")||
//                        allowedUpdatesSelect.getValue().equals("Change the net contents statement")){
//                    submitLabel.setDisable(false);
//                } else if(allowedUpdatesSelect.getValue().equals("Edit alcohol content")){
//                    alcoholContentField.setDisable(false);
//                    submitLabel.setDisable(false);
//                } else if(allowedUpdatesSelect.getValue().equals("Edit optional statement of alcohol content on label")){
//                    submitLabel.setDisable(false);
//                } else if(allowedUpdatesSelect.getValue().equals("Edit percentages of grape varietals and appellations on label")){
//                    varietalText.setDisable(false);
//                    appellationText.setDisable(false);
//                    submitLabel.setDisable(false);
//                } else if(allowedUpdatesSelect.getValue().equals("Edit the vintage date on label")){
//                    wineVintageYearField.setDisable(false);
//                    submitLabel.setDisable(false);
//                } else if(allowedUpdatesSelect.getValue().equals("Edit the pH level on label")){
//                    pHLevelField.setDisable(false);
//                    submitLabel.setDisable(false);
//                } else if(allowedUpdatesSelect.getValue().equals("Change 'produced' on label to 'blended' or 'prepared' statement")||
//                        allowedUpdatesSelect.getValue().equals("Change stated amount of sugar on label")||
//                        allowedUpdatesSelect.getValue().equals("Edit bonded winery or taxpaid bottling house number on label")){
//                    submitLabel.setDisable(false);
//                } else{ //nothing to change
//                }
//            }
//        });

    }

    public void submitApp() {
        LogManager.getInstance().logAction("NewApplicationController", "Logged Click from first page of the new Application");

        Boolean formFilled = false;
        Boolean fieldsValid = false;

        //errors are printed only if required fields are not filled in
        if (pSourceSelect.getValue().equals("Select a product source")) {
            pSourceErrorField.setText("Please select the alcohol source.");
        } else {
            pSourceErrorField.setText("");
        }
        if (pTypeSelect.getValue().equals("Select a product type")) {
            pTypeErrorField.setText("Please select the alcohol type.");
        } else {
            pTypeErrorField.setText("");
        }
        if (brandNameField.getText().isEmpty()) {
            brandNameErrorField.setText("Please fill in the brand name.");
        } else {
            brandNameErrorField.setText("");
        }
        if (alcoholContentField.getText().isEmpty()) {
            alcContentErrorField.setText("Please fill in the alcohol percentage.");
        } else if(!isInt(alcoholContentField)){
            alcContentErrorField.setText("Please enter a valid number.");
        } else {
            alcContentErrorField.setText("");
        }
        if (serialText.getText().isEmpty()) {
            serialErrorField.setText("Please input a serial number.");
        } else {
            serialErrorField.setText("");
        }
        if (signatureField.getText().isEmpty()) {
            signatureErrorField.setText("Please fill in the signature field.");
        } else {
            signatureErrorField.setText("");
        }
        if (pTypeSelect.getValue().equals("Wine")) {
            if (!isInt(wineVintageYearField)||!isDouble(pHLevelField)) {
                wineNumErrorField.setText("Please enter a valid number.");
            } else {
                wineNumErrorField.setText("");
            }
        }

        //check if required fields are filled in
        if ((!pTypeSelect.getValue().equals("Select a product type")) &&
                (!pSourceSelect.getValue().equals("Select a product source")) &&
                !brandNameField.getText().isEmpty() && !alcoholContentField.getText().isEmpty() &&
                !signatureField.getText().isEmpty() && !serialText.getText().isEmpty()) {
            formFilled = true;
        }

        //check if fields are valid
        if(isInt(alcoholContentField)) {
            if (pTypeSelect.getValue().equals("Wine")) {
                if (isInt(wineVintageYearField) && isDouble(pHLevelField)) {
                    fieldsValid = true;
                }
            } else fieldsValid = true;
        }

        if (formFilled && fieldsValid) {
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

            //Checking if the product is domestic or imported
            if (pSourceSelect.getValue().equals("Domestic")) {
                pSource = ProductSource.DOMESTIC;
            } else if (pSourceSelect.getValue().equals("Imported")) {
                pSource = ProductSource.IMPORTED;
            }

            //Checking if the product is a beer, wine or spirits
            if (pTypeSelect.getValue().equals("Malt Beverages") ){
                alcType = AlcoholType.BEER;
            } else if (pTypeSelect.getValue().equals("Wine")) {
                alcType = AlcoholType.WINE;
            } else if (pTypeSelect.getValue().equals("Distilled Spirits")) {
                alcType = AlcoholType.DISTILLEDSPIRITS;
            }

            //sets alc info fields
            alcName = alcoholName.getText();
            brandName = brandNameField.getText();
            alcContent = Integer.parseInt(alcoholContentField.getText());
            serialNum = serialText.getText();
            if (formulaText.getText().isEmpty()) {
                formula = " ";
            } else formula = formulaText.getText();
            if (extraInfoText.getText().isEmpty()) {
                extraInfo = " ";
            } else extraInfo = extraInfoText.getText();

            //creates alcohol info
            AlcoholInfo appAlcoholInfo = new AlcoholInfo(alcContent, alcName, brandName, pSource, alcType, wineType, serialNum, formula);

            //sets the date value
            Date newDate = DateHelper.getDate(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue() - 1, datePicker.getValue().getYear());

            //creates a new ManufacturerInfo
            ManufacturerInfo appManInfo = new ManufacturerInfo(applicant.getApplicant().getNamefromDB(username), address, "company", representativeID,
                    permitNum, phoneNum, emailAddress);

            ApplicationInfo appInfo = new ApplicationInfo(newDate, appManInfo, appAlcoholInfo, extraInfo, appType);

            //!!!!!placeholder for applicant's submitted applications!!!!!
            List<SubmittedApplication> appList = new ArrayList<>();

            //Create applicant to store in submitted application
            Applicant applicant = new Applicant(appList);

            //Create a SubmittedApplication
            SubmittedApplication newApp = new SubmittedApplication(appInfo, ApplicationStatus.PENDINGREVIEW, applicant);
            if (application != null)
                newApp.setApplicationID(application.getApplicationID());
            applicant.addSubmittedApp(newApp);
            if(proxyLabelImage!= null) {
                newApp.setImage(proxyLabelImage);
            } else{
                newApp.setImage(new ProxyLabelImage(""));
            }

            if (application != null)
                newApp.setApplicationID(application.getApplicationID());

            //Submit the new application to the database
            //ApplicantInterface applicantInterface = new ApplicantInterface(Authenticator.getInstance().getUsername());
            //boolean success = applicantInterface.submitApplication(newApp);

            main.loadHomepage();
        }
    }

    public void cancelApp() {
        //Go back to homepage
        main.loadApplicantWorkflowPage();
    }

    public void saveApp() {

    }

    public boolean isInt(TextField txt){
        try {
            Integer.parseInt(txt.getText());
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean isDouble(TextField txt){
        try {
            Double.parseDouble(txt.getText());
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void disableAllFields(){
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

    public void exemptionChecked(){
        stateSelect.setDisable(!certOfExemption.isSelected());
    }

    public void distinctiveChecked(){
        distinctiveText.setDisable(!distinctiveApproval.isSelected());
    }

    public void submitImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg","*jpeg","*png");
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