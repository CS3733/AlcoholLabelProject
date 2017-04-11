package com.emeraldElves.alcohollabelproject.Data;

import javafx.scene.image.Image;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by keionbis on 4/10/17.
 */

public class LabelImage implements ILabelImage {
    private Image image;
    private String fileName;


    public LabelImage(String fileName) {
        this.fileName = fileName;

    }

    public void load(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(null);
        Path source = Paths.get((file.getPath()));

        Path targetDir = Paths.get("Labels");
        Path target = targetDir.resolve(fileName);
        image = new Image(String.valueOf(target));
    }

    public String getFileName() {
        return fileName;
    }

    public Image display() {
        return image;
    }

}



