package com.emeraldElves.alcohollabelproject;

/**
 * Created by Kylec on 4/3/2017.
 */
public class AppState {

    private static AppState instance = null;

    private AppState() {
    }

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }
}
