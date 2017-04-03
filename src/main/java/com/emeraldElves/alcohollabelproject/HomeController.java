package com.emeraldElves.alcohollabelproject;

/**
 * Created by Harry and Joe on 4/2/2017.
 */
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class HomeController {
    public Button loginBtn;
    public ArrayList<Label> mostRecentLabels;
    public ArrayList<SubmittedApplication> mostRecentSubmissions;
    public LoginState state;

    public HomeController(LoginState state) {
        this.state = state;
        mostRecentLabels = new ArrayList<Label>();
        mostRecentSubmissions = new ArrayList<SubmittedApplication>();
    }

    // TODO: put FXML in correct folder

    /**
     * Loads homepage
     */
    public void loadHomePageFXML(){
        switch (state) {
            case LOGGEDIN:
                Main.loadFXML("/fxml/HomePageLoggedIn.fxml");
                break;
            case NOTLOGGEDIN:
                Main.loadFXML("/fxml/HomePageLoggedOut.fxml");
                break;
        }
    }

    /**
     * Loads login page when login button is clicked
     */
    public void loadLoginPageFXML(){
        // This could change depending on actual file name
        Main.loadFXML("/fxml/Loginpage.fxml");
    }

    /**
     * Loads normal home page when logout button is clicked
     * note: similar code to loadHomePageFXML() but simpler to ensure errors are avoided
     */
    public void loadLogoutPageFXML(){
        Main.loadFXML("/fxml/HomePageLoggedOut.fxml");
    }

    /**
     * Loads profile page when "My Profile" is clicked
     */
    public void loadProfileFXML(){
        // TODO
    }


}
