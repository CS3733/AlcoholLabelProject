package com.emeraldElves.alcohollabelproject.UserInterface;

import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;

/**
 * Created by elijaheldredge on 4/22/17.
 */
public class AboutPageController implements IController {
    Main main;
    @FXML
    ImageView imageID;

   // Image wilsonWong = new Image("file:images/wwong.jpg");
//    ImageView wilsonWongView = new ImageView((Element) wilsonWong);
   public AboutPageController() {

   }
   public void init(Bundle bundle){
       this.init(bundle.getMain("main"));
   }

    public void init(Main main) {
        this.main = main;
    }



}
