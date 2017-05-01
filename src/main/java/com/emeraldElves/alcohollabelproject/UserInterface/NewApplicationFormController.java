package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.*;
import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class NewApplicationFormController implements IController {
    @FXML
    private TextField repIDNoTextField, permitNoTextField, addressField, companyField;
    @FXML
    private TextField phoneNumberField, emailAddressField, alcoholName, brandNameField, signatureField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField alcoholContentField, wineVintageYearField, pHLevelField;
    @FXML
    private Label welcomeApplicantLabel, errorMsg;
    @FXML
    private Button cancelApplication, submitBtn, submitLabel, saveApplication;
    @FXML
    private TextField varietalText, appellationText, formulaText, serialText, extraInfoText;
    @FXML
    private CheckBox certOfApproval, certOfExemption, distinctiveApproval;
    @FXML
    private TextField distinctiveText; //relates to distinctive approval
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox pTypeSelect, pSourceSelect, stateSelect;
    @FXML
    private Button normalFormButton;

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
    private String permitNum;
    private String address;
    private PhoneNumber phoneNum;
    private int representativeID;
    private String company;

    //Alcohol info
    private ProductSource pSource;
    private AlcoholType alcType;
    private String alcName;
    private String brandName;
    private double alcContent;
    private AlcoholInfo.Wine wineType = null; //null if type is not wine
    private String formula;
    private String serialNum;
    private String extraInfo;
    private File file;

    private Main main;

    private LabelImage image;

    private SubmittedApplication application;
    private SavedApplication savedApplication;

    private boolean isSavedApplication = false; // whether or not current application is a saved application

    /**
     * Inits a new application, calling a different init if we have a submitted application
     * vs if we do not have one.
     * @param bundle Bundle of info passed to this controller
     */
    public void init(Bundle bundle) {
        if (bundle.getApplication("app") != null) {
            this.init(bundle.getMain("main"), bundle.getApplication("app"));
        } else if (bundle.getSavedApplication("saved") != null) {
            this.init(bundle.getMain("main"), bundle.getSavedApplication("saved"));
        } else{
            this.init(bundle.getMain("main"));
        }
    }

    //initializing for filling out a blank application
    public void init(Main main){
        this.main = main;

        //get applicant
        username= Authenticator.getInstance().getUsername();
        applicant = new ApplicantInterface(username);
        Log.console(username);

        //set manufacturer info
        welcomeApplicantLabel.setText("Welcome, " + applicant.getApplicant().getNamefromDB(username) + ".");
        emailAddress = new EmailAddress(applicant.getApplicant().getEmailAddress());
        phoneNum = new PhoneNumber(applicant.getApplicant().getPhoneNum());
        address = applicant.getApplicant().getAddress();
        permitNum = applicant.getApplicant().getPermitNumFromDB(username);
        representativeID = applicant.getApplicant().getRepresentativeID();
        company = applicant.getApplicant().getCompany();
        emailAddressField.setText((emailAddress.getEmailAddress()));
        phoneNumberField.setText((phoneNum.getPhoneNumber()));
        addressField.setText(String.valueOf(address));
        permitNoTextField.setText(String.valueOf(permitNum));
        repIDNoTextField.setText(String.valueOf(representativeID));
        companyField.setText(String.valueOf(company));

        //set up combo boxes
        datePicker.setValue(LocalDate.now());
        stateSelect.setValue("State Abb.");
        stateSelect.setItems(stateList);
        pSourceSelect.setValue("Select a product source");
        pSourceSelect.setItems(sourceList);
        pTypeSelect.setValue("Select a product type");
        pTypeSelect.setItems(typeList);
        pTypeSelect.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldVal, String newVal) {
                if(pTypeSelect.getValue().equals("Wine")){
                    showTheWine();
                } else hideTheWine();
            }
        });
    }

    //initialize for editing a saved application
    public void init(Main main, SavedApplication savedApplication){
        isSavedApplication= true;
        init(main);
        //TODO set field of image
        this.savedApplication = savedApplication;
        ApplicationType applicationType = savedApplication.getApplicationType();
        AlcoholInfo alcoholInfo = savedApplication.getAlcoholInfo();
        String extraInfo = savedApplication.getExtraInfo();
        String imageURL = (savedApplication.getImage().getFileName());

        //Application Type
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

        //imported or domestic
        if(alcoholInfo.getOrigin() == ProductSource.DOMESTIC){
            pSourceSelect.setValue("Domestic");
        }
        else{
            pSourceSelect.setValue("Imported");//right now default value, should change
        }
        //Alcohol Type
        //Need to make default case
        if(alcoholInfo.getAlcoholType() == AlcoholType.BEER){
            pTypeSelect.setValue("Malt Beverages");
        }
        else if(alcoholInfo.getAlcoholType() == AlcoholType.WINE){
            pTypeSelect.setValue("Wine");
        }
        else{
            pTypeSelect.setValue("Distilled Spirits");
        }
        //fanciful name
        alcoholName.setText(alcoholInfo.getName());
        //brand name
        brandNameField.setText(alcoholInfo.getBrandName());
        //Alcohol Content
        if(alcoholInfo.getAlcoholContent() != -1) {
            alcoholContentField.setText("" + alcoholInfo.getAlcoholContent());
        }
        //formula
        formulaText.setText(alcoholInfo.getFormula());
        //serial number
        serialText.setText(alcoholInfo.getSerialNumber());
        //extra info
        extraInfoText.setText(extraInfo);
        //Wine info things
        if(alcoholInfo.getAlcoholType() == AlcoholType.WINE){
            //Vintage Year
            if(alcoholInfo.getWineInfo().vintageYear != -1){
                wineVintageYearField.setText("" + alcoholInfo.getWineInfo().vintageYear);
            }
            //pH
            if(alcoholInfo.getWineInfo().pH != -1.0){
                pHLevelField.setText("" + alcoholInfo.getWineInfo().pH);
            }
            //varietals
            varietalText.setText(alcoholInfo.getWineInfo().grapeVarietal);
            //appellation
            appellationText.setText(alcoholInfo.getWineInfo().appellation);
        }
        //Image
        if(!imageURL.equals("")) {
            Log.console("Image path: " + imageURL);
            File file = new File("Labels/" + imageURL);
            Image tempImage = new Image(file.toURI().toString());
            imageView.setImage(tempImage);
            this.proxyLabelImage = new ProxyLabelImage("Labels/"+ imageURL);
        }
    }

    //initialize for a revising a form
    public void init(Main main, SubmittedApplication application) {
        init(main);
        this.application = application;

        file = new File("");

        alcoholName.setText(String.valueOf(application.getApplication().getAlcohol().getName()));
        brandNameField.setText(String.valueOf(application.getApplication().getAlcohol().getBrandName()));
        alcoholContentField.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
        formulaText.setText(String.valueOf(application.getApplication().getAlcohol().getFormula()));
        serialText.setText(String.valueOf(application.getApplication().getAlcohol().getSerialNumber()));
        extraInfoText.setText(String.valueOf(application.getApplication().getExtraInfo()));
    }

    public void submitApp() {
        LogManager.getInstance().logAction("NewApplicationController", "Logged Click from first page of the new Application");

        Boolean formFilled = false;
        Boolean fieldsValid = false;

        //getting and storing the image
        saveImage(file);
        //filling out application type
        boolean labelApproval;
        String stateOnly;
        int bottleCapacity;
        //14a
        labelApproval = certOfApproval.isSelected();
        //14b
        if (certOfExemption.isSelected()) {
            if(!stateSelect.getValue().equals("State Abb."))
                stateOnly = (String)stateSelect.getValue();
            else stateOnly = "";
        } else {
            stateOnly = "";
        }
        //14c
        if (distinctiveApproval.isSelected()) {
            bottleCapacity = Integer.parseInt(distinctiveText.getText());
        } else {
            bottleCapacity = -1;
        }

        appType = new ApplicationType(labelApproval, stateOnly, bottleCapacity);

        //check if required fields are filled in
        if ((!pTypeSelect.getValue().equals("Select a product type")) &&
                (!pSourceSelect.getValue().equals("Select a product source")) &&
                !brandNameField.getText().isEmpty() && !alcoholContentField.getText().isEmpty() &&
                !signatureField.getText().isEmpty() && !serialText.getText().isEmpty()) {
            formFilled = true;
        }

        //check if fields are valid
        if(isDouble(alcoholContentField)) {
            if (pTypeSelect.getValue().equals("Wine")) {
                if (isInt(wineVintageYearField) && isDouble(pHLevelField)) {
                    fieldsValid = true;
                }
            } else fieldsValid = true;
        }

        //error message saying to fill in all required fields
        if (!formFilled) {
            errorMsg.setText("Please fill in all the required fields.");
        } else if (!fieldsValid) {
            errorMsg.setText("Please make sure number fields are valid.");
        } else if(!(serialText.getText().length()<7 && serialText.getText().length()>0)){
            fieldsValid = false;
            errorMsg.setText("Please enter a valid serial number.");
        } else {
            errorMsg.setText("");
        }

            //Checking alcohol content field and serial number for validity
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

                alcContent = Double.parseDouble(alcoholContentField.getText()); //CHECK IF INTEGER

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

                //placeholder for applicant's submitted applications
                List<SubmittedApplication> appList = new ArrayList<>();

                //Create applicant to store in submitted application
                Applicant applicant = new Applicant(appList);

                //Create a SubmittedApplication
                SubmittedApplication newApp = new SubmittedApplication(appInfo, ApplicationStatus.PENDINGREVIEW, applicant);
                if (application != null)
                    newApp.setApplicationID(application.getApplicationID());
                applicant.addSubmittedApp(newApp);
                if(isSavedApplication) {
                    if(!(savedApplication.getImage().getFileName().equals("")||savedApplication.getImage().getFileName().isEmpty()|savedApplication.getImage().getFileName().equals(null))) {
                        proxyLabelImage = new ProxyLabelImage(savedApplication.getImage().getFileName());
                    }
                }
                if(proxyLabelImage!= null) {
                    newApp.setImage(proxyLabelImage);
                } else{
                    newApp.setImage(new ProxyLabelImage(""));
                }
                if (application != null)
                    newApp.setApplicationID(application.getApplicationID());

                //Submit the new application to the database
                ApplicantInterface applicantInterface = new ApplicantInterface(Authenticator.getInstance().getUsername());
              boolean success = applicantInterface.submitApplication(newApp);
                if(isSavedApplication){
                    //delete old saved application after submitting it
                    boolean saveSuccess = Storage.getInstance().removeSavedApplication(savedApplication);
                }
                Log.console("Submitted application");

                main.loadFXML("/fxml/HomePage.fxml");
            }
        }

    /**
     * Makes the current application into a SavedApplication object and then adds it
     * to the SavedApplications database
     */
    public void saveApplication(){
        LogManager.getInstance().logAction("newApplicationController", "Save Application has been clicked.");
        if(isSavedApplication){
            //delete old saved application first
            Storage.getInstance().removeSavedApplication(savedApplication);
        }
        SavedApplication app; // app to be submitted to database
        //Things to add into SavedApplication
        ApplicationType appType;
        AlcoholInfo alcoholInfo;
        String extraInfo;

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

        //alcoholInfo
        double alcoholContent;
        String fanciful;
        String brand;
        ProductSource origin;
        String serialNumber;
        String formula;
        AlcoholInfo.Wine wineInfo;
        AlcoholType alcoholType;
        //alcoholContent
        if(alcoholContentField.getText().isEmpty()){ alcoholContent = -1;}
        else{ alcoholContent = Double.parseDouble(alcoholContentField.getText());}
        //fanciful
        if(alcoholName.getText().isEmpty()){ fanciful = "";}
        else{ fanciful = alcoholName.getText(); }
        //brand
        if(brandNameField.getText().isEmpty()){ brand = "";}
        else{ brand = brandNameField.getText();}
        //origin
        if (pSourceSelect.getValue().equals("Domestic")) {
            origin = ProductSource.DOMESTIC;
        } else if (pSourceSelect.getValue().equals("Imported")) {
            origin = ProductSource.IMPORTED;
        }
        else{ origin = ProductSource.DOMESTIC;} //default value??
        //serial number
        if(serialText.getText().isEmpty()){ serialNumber = "";}
        else{ serialNumber = serialText.getText(); }
        //formula
        if(formulaText.getText().isEmpty()){ formula = "";}
        else{ formula = formulaText.getText(); }

        //Alcohol Type
        //Checking if the product is a beer, wine or spirits
        if (pTypeSelect.getValue().equals("Malt Beverages") ){
            alcoholType = AlcoholType.BEER;
        } else if (pTypeSelect.getValue().equals("Wine")) {
            alcoholType = AlcoholType.WINE;
        } else if (pTypeSelect.getValue().equals("Distilled Spirits")) {
            alcoholType = AlcoholType.DISTILLEDSPIRITS;
        }
        else{
            alcoholType = AlcoholType.BEER; // default to beer I guess
        }
        //END Alcohol Type

        //wineInfo
        double pH;
        int vintageYear;
        String varietal;
        String appellation;

        if(alcoholType == AlcoholType.WINE){
            //Its wine
            //pH
            if(pHLevelField.getText().isEmpty()){ pH = -1.0;}
            else { pH = Double.parseDouble(pHLevelField.getText());}
            //vintageYear
            if(wineVintageYearField.getText().isEmpty()){ vintageYear = -1;}
            else{ vintageYear = Integer.parseInt(wineVintageYearField.getText());}
            //varietal
            if(varietalText.getText().isEmpty()){ varietal = "";}
            else{ varietal = varietalText.getText();}
            //appellation
            if(appellationText.getText().isEmpty()){ appellation = ""; }
            else{ appellation = appellationText.getText();}
        }
        else{
            pH = -1.0;
            vintageYear = -1;
            varietal = "";
            appellation = "";
        }
        wineInfo = new AlcoholInfo.Wine(pH,vintageYear,varietal,appellation);
        //END wineInfo
        alcoholInfo = new AlcoholInfo(alcoholContent,fanciful,brand,origin,alcoholType,wineInfo,serialNumber,formula);
        //END alcoholInfo

        //extra info
        if(extraInfoText.getText().isEmpty()){ extraInfo = "";}
        else{ extraInfo = extraInfoText.getText(); }
        //END extra info
        //image
        saveImage(file);
        if(proxyLabelImage!= null) {
            image = new LabelImage(proxyLabelImage.getFileName());
        }
        else{
            image = new LabelImage("");
        }
        Log.console(image);
        //END image

        app = new SavedApplication(appType,alcoholInfo,extraInfo,image);

        Storage.getInstance().saveApplication(app, username);
        Log.console("Saved Application");
        main.loadHomepage();
    }

    public void cancelApp() {
        //Go back to homepage
        main.loadFXML("/fxml/ApplicantWorkflowPage.fxml");
    }

    public void logout() {
        Authenticator.getInstance().logout();
        main.loadFXML("/fxml/HomePage.fxml");
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

    public void exemptionChecked(){
        stateSelect.setDisable(!certOfExemption.isSelected());
    }

    public void distinctiveChecked(){
        distinctiveText.setDisable(!distinctiveApproval.isSelected());
    }

    public void hideTheWine(){
        wineVintageYearField.setDisable(true);
        pHLevelField.setDisable(true);
        varietalText.setDisable(true);
        appellationText.setDisable(true);
    }

    public void showTheWine(){
        wineVintageYearField.setDisable(false);
        pHLevelField.setDisable(false);
        varietalText.setDisable(false);
        appellationText.setDisable(false);
    }

    public void submitImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(null);
        Log.console(file);
        if (file == null) {
            Log.console(file);
            file = new File("");
        }
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void saveImage(File file){
        if (file == null) {
            proxyLabelImage = new ProxyLabelImage("");
            return;
        }
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
        proxyLabelImage = new ProxyLabelImage(fileName);
    }

    public void useNormalLayout(){
        main.loadFXML("/fxml/NewApplication.fxml");
    }
}
