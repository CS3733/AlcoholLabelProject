package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by keionbis on 4/11/17.
 */
public class DisplayLabelController {
    @FXML
    ImageView labelView;
    Main main;
    Image image;
    SubmittedApplication application;
    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application = application;
        Path targetDir = Paths.get("Labels");
        Path target = targetDir.resolve(application.getproxyImage().getFileName());
        Image image = new Image(target.toUri().toString());
        labelView.setImage(image);
    }
    public void back()
    {
        main.loadFXML("/fxml/ApprovalPage.fxml",application);
    }
}
