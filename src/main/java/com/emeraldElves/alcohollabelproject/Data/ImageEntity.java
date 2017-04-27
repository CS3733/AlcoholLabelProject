package com.emeraldElves.alcohollabelproject.Data;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Created by Essam on 4/26/2017.
 */
@Entity
@DiscriminatorValue("image")
public class ImageEntity extends FileEntity {
    @Transient
    private Image img;
    @Transient
    Image getImage(){
        if (img == null){
            new Image(new ByteArrayInputStream(getFile()));
        }
        return img;
    }
    @Transient
    private static byte[] ImageToByteArray(Image img) {
        BufferedImage bufImg = SwingFXUtils.fromFXImage(img, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufImg, "jpg", outputStream);
        }
        catch (IOException e){
            //do nothing
        }
        return outputStream.toByteArray();
    }

    public ImageEntity(byte[] file){
        super(file);
    }

    public ImageEntity(ByteArrayInputStream istream){
        super(istream);
    }
    public ImageEntity(InputStream istream) throws IOException {
        super(istream);
    }
    public ImageEntity(File file) throws IOException {
        super(file);
    }
    public ImageEntity(String filePath) throws IOException {
        super(filePath);
    }
    public ImageEntity(Image img) throws IOException {
        super(ImageToByteArray(img));
        this.img = img;
    }

}
