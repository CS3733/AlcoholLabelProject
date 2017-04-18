package com.emeraldElves.alcohollabelproject.Data;

import javafx.scene.image.Image;

/**
 * Created by keionbis on 4/11/17.
 */
public class ProxyLabelImage implements ILabelImage {

    LabelImage realImage;
    String fileName;
    public  ProxyLabelImage(String fileName){
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }


    public Image display() {
        if (realImage == null) { // Lazy Loading
            realImage = new LabelImage(fileName);
        }
        return realImage.display();
    }

    }
