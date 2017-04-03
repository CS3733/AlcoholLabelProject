package com.emeraldElves.alcohollabelproject;

/**
 * Created by Harry on 4/2/2017.
 */
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class HomeController {
    public Button loginBtn;
    public ArrayList<Label> mostRecentLabels;
    public ArrayList<SubmittedApplication> mostRecentSubmissions;

    public HomeController() {
        mostRecentLabels = new ArrayList<Label>();
        mostRecentSubmissions = new ArrayList<SubmittedApplication>();
    }

    /**
     * Loads homepage
     */
    public void loadHomePageFXML(){
        Main.loadFXML("/fxml/Homepage.fxml");
    }

    /**
     * Loads login page when login button is clicked
     */
    public void loadLoginPageFXML(){
        // This could change depending on actual file name
        Main.loadFXML("/fxml/Loginpage.fxml");
    }


}
