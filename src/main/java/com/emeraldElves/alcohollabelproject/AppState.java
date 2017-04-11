package com.emeraldElves.alcohollabelproject;

/**
 * Created by Kylec on 4/3/2017.
 */
public class AppState {

    private static AppState instance = null;
    public RoundRobin<String> ttbAgents = null;
    public boolean isInTestingMode = false;

    private AppState() {
    }

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }
}
