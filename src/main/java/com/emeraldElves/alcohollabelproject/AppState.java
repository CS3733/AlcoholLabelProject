package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.ApplicationAssigner;
import com.emeraldElves.alcohollabelproject.Data.RoundRobin;

/**
 * Created by Kylec on 4/3/2017.
 */
public class AppState {

    private static AppState instance = null;
    public ApplicationAssigner ttbAgents = null;
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
