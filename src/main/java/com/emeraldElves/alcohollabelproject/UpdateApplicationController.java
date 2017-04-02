package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.emeraldElves.alcohollabelproject.UpdateApplication;
/**
 * Created by Kyle on 4/2/2017.
 */
public class UpdateApplicationController {

    @FXML
    TextField name;

    public void login(){

        UpdateApplication.user = name.getText();

        //UpdateApplication.loadFXML("UpdateApplication.fxml");
    }

}
