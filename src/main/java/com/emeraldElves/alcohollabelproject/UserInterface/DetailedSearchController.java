package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.Controller;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.util.Date;

/**
 * Created by Kylec on 4/4/2017.
 */
public class DetailedSearchController implements Controller {
    

    private Main main;
    private SubmittedApplication application;
    private String searchTerm;


    @FXML
    Label brandName;

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



    public void init(Main main, SubmittedApplication application, String searchTerm) {
        this.main = main;
        this.application = application;
        this.searchTerm = searchTerm;
        brandName.setText(application.getApplication().getAlcohol().getBrandName());
        fancifulName.setText(application.getApplication().getAlcohol().getName());
        String type = "";
        switch (application.getApplication().getAlcohol().getAlcoholType()) {
            case BEER:
                type = "BEER";
                break;
            case WINE:
                type = "WINE";
                break;
            case DISTILLEDSPIRITS:
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
        email.setText(application.getApplication().getManufacturer().getEmailAddress().getEmailAddress());
        phone.setText(application.getApplication().getManufacturer().getPhoneNumber().getFormattedNumber());
        address.setText(application.getApplication().getManufacturer().getPhysicalAddress());
    }


    public void printPage(){
        main.printPage();
    }

    public void export(){
        // Exporter
    }


}
