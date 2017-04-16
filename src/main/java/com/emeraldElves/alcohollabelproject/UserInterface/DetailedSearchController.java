package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Path;
import java.nio.file.Paths;
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



    public void init(Main main, SubmittedApplication application, String searchTerm) {
        this.main = main;
        this.application = application;
        this.searchTerm = searchTerm;
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
            case DISTILLEDSPIRITS:
                type = "Distilled Spirits";
                break;
        }
        alcoholType.setText(type);
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date date = application.getApplication().getSubmissionDate();
        submissionDate.setText(dateFormat.format(date));
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
        Path targetDir = Paths.get("Labels");
        Path target = targetDir.resolve(application.getproxyImage().getFileName());
        Image image = new Image(target.toUri().toString());
        labelView.setImage(image);

    }

    public void GoHome() {
        main.loadHomepage();
    }


}
