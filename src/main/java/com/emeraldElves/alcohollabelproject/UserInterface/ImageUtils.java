package com.emeraldElves.alcohollabelproject.UserInterface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Kylec on 4/18/2017.
 */
public class ImageUtils {

    public static void centerImage(ImageView imageView){
        Image img = imageView.getImage();
        if (img != null && img.getWidth() != 0 && img.getHeight() != 0) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        } else {
            imageView.setImage(new Image("images/noImage.png"));
        }
    }

}

