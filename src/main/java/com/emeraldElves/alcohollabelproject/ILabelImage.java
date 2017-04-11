package com.emeraldElves.alcohollabelproject;

import javafx.scene.image.Image;

/**
 * Created by keionbis on 4/10/17.
 */
interface ILabelImage {
    //public void display();
    public Image LabelImage(String fileName);
    public LabelImage ProxyLabelImage(String fileName);

    public void display();


}
