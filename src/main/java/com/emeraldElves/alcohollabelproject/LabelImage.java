package com.emeraldElves.alcohollabelproject;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class LabelImage implements ILabelImage {
    @FXML
    Button submitLabel;
    @FXML
    ImageView imageView;
    File file;
    Image image;
    String fileName;
    Main main;
    SubmittedApplication application;
    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application = application;
    }

    public Image LabelImage(String fileName) {

        this.fileName = fileName;
        return (image);

    }

    public void load(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(null);
        Path source = Paths.get((file.getPath()));
        Path targetDir = Paths.get("Labels");
        try {
            Files.createDirectories(targetDir);//in case target directory didn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path target = targetDir.resolve(file.getName());// create new path ending with `name` content
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = new Image(String.valueOf(target));
        imageView.setImage(image);


    }
    public LabelImage ProxyLabelImage(String fileName){
        return (null);
    }
    public void display()
    {
        Path targetDir = Paths.get("Labels");
        Path target = targetDir.resolve(fileName);
        Image image = new Image(String.valueOf(target));
        imageView.setImage(image);
    }

}



