package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.ApplicationExporter;
import com.emeraldElves.alcohollabelproject.CSVExporter;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Date;

/**
 * Created by Kylec on 4/4/2017.
 */
public class DetailedSearchController {
    

    private Main main;
    private SubmittedApplication application;
    private String searchTerm;


    @FXML
    Label brandName;

    @FXML
    Label serialNum;

    @FXML
    Label fancifulName;

    @FXML
    Label alcoholType;

    @FXML
    Label submissionDate;

    @FXML
    Label content;

    @FXML
    Label origin;

    @FXML
    ImageView labelView;

    @FXML
    Label status;

    @FXML
    Label email;

    @FXML
    Label phone;

    @FXML
    Label address;

    @FXML
    Label ttbID;

    @FXML
    AnchorPane wineInfo;

    @FXML
    Label vintageYear;

    @FXML
    Label appellation;

    @FXML
    Label pH;

    @FXML
    Label varietals;



    public void init(Main main, SubmittedApplication application, String searchTerm) {
        this.main = main;
        this.application = application;
        this.searchTerm = searchTerm;
        brandName.setText(application.getApplication().getAlcohol().getBrandName());
        fancifulName.setText(application.getApplication().getAlcohol().getName());
        String type = "";
        switch (application.getApplication().getAlcohol().getAlcoholType()) {
            case BEER:
                wineInfo.setVisible(false);
                type = "BEER";
                break;
            case WINE:
                wineInfo.setVisible(true);
                type = "WINE";
                if(application.getApplication().getAlcohol().getWineInfo().vintageYear==null){
                    vintageYear.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().vintageYear));
                }
                else {
                    vintageYear.setText("");
                }
                if(application.getApplication().getAlcohol().getWineInfo().pH==null){
                    pH.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().pH));
                }
                else {
                    pH.setText("");
                }
                if(application.getApplication().getAlcohol().getWineInfo().grapeVarietal==null){
                    varietals.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().grapeVarietal));
                }
                else {
                    varietals.setText("");
                }
                if(application.getApplication().getAlcohol().getWineInfo().appellation==null){
                    appellation.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().appellation));
                }
                else {
                    appellation.setText("");
                }
                break;
            case DISTILLEDSPIRITS:
                wineInfo.setVisible(false);
                type = "DISTILLED SPIRITS";
                break;
        }
        alcoholType.setText(type);
        Date date = application.getApplication().getSubmissionDate();
        submissionDate.setText("Submitted " + DateHelper.dateToString(date));
        content.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent())+"%");
        String productSource = "";
        switch (application.getApplication().getAlcohol().getOrigin()) {
            case IMPORTED:
                productSource = "Imported";
                break;
            case DOMESTIC:
                productSource = "Domestic";
                break;
        }
        origin.setText(productSource);
        ttbID.setText(String.valueOf(application.getApplicationID()));
        status.setText(application.getStatus().toString());
        labelView.setImage(application.getImage().display());
        ImageUtils.centerImage(labelView);

//        email.setText(application.getApplication().getManufacturer().getEmailAddress().getEmailAddress());
//        phone.setText(application.getApplication().getManufacturer().getPhoneNumber().getFormattedNumber());
        if(application.getApplication().getManufacturer().getPhysicalAddress()==", ,"){
            address.setText(application.getApplication().getManufacturer().getPhysicalAddress());
        }
        else{
            address.setText(application.getApplication().getManufacturer().getPhysicalAddress());

        }
    }


    public void printPage(){
        main.printPage();
    }

    public void export(){
        ApplicationExporter exporter = new ApplicationExporter(new CSVExporter());
        exporter.export(application);
    }


}
