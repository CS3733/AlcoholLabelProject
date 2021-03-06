package com.emeraldElves.alcohollabelproject.UserInterface;


import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by keionbis on 4/10/17.
 */

public class ImageSubmissionController implements IController {
    @FXML
    Button submitLabel;
    @FXML
    ImageView imageView;
    File file;
    Main main;
    SubmittedApplication application;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"), bundle.getApplication("app"));
    }

    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application = application;
    }
    public void submitImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files","*.png","*.jpg","*.jpeg","*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(null);
        if (file == null){
            file = new File("");
        }
        Path source = Paths.get((file.getPath()));
        Path targetDir = Paths.get("Labels");
        try {
            Files.createDirectories(targetDir);//in case target directory didn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path target = targetDir.resolve(String.valueOf(System.currentTimeMillis())+".jpg");// create new path ending with `name` content
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = new Image(String.valueOf(target));
        imageView.setImage(image);

    }
}


