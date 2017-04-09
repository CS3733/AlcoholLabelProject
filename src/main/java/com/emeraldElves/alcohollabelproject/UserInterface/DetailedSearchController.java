package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kylec on 4/4/2017.
 */
public class DetailedSearchController {

    private Main main;
    private SubmittedApplication application;
    private String searchTerm;
    public String Username;
    private UserType userType;


    @FXML
    Label brandName;

    @FXML
    Label fancifulName;

    @FXML
    Label alcoholType;

    @FXML
    Label submissionDate;

    @FXML
    Label company;

    @FXML
    Label origin;

    public void init(Main main, SubmittedApplication application, String searchTerm, UserType userType, String Username) {
        this.main = main;
        this.application = application;
        this.searchTerm = searchTerm;
        this.Username = Username;
        this.userType = userType;
        brandName.setText(application.getApplication().getAlcohol().getBrandName());
        fancifulName.setText(application.getApplication().getAlcohol().getName());
        String type = "";
        switch (application.getApplication().getAlcohol().getAlcoholType()) {
            case BEER:
                type = "Beer";
                break;
            case WINE:
                type = "Wine";
                break;
            case OTHER:
                type = "Other";
                break;
        }
        alcoholType.setText(type);
        DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        Date date = application.getApplication().getSubmissionDate();
        date.setYear(date.getYear() - 1900);
        submissionDate.setText(dateFormat.format(date));
        company.setText(application.getApplication().getManufacturer().getCompany());
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
    }

    public void GoHome() {
        main.loadHomepage(userType, Username);
    }


}
