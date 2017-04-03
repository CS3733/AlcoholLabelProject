package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.emeraldElves.alcohollabelproject.Main.loadFXML;

/**
 * Created by jessieying on 4/3/17.
 */
public class LoginController {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Button returnHomeButton;

    //ctor
    public LoginController(){
    }

    public void login(){

    }

    public void returnHome(){
        loadFXML("mainGUI.FXML");
    }




}
