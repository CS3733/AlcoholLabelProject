package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.ApplicationAssigner;

import java.util.HashMap;

/**
 * Created by Kylec on 4/3/2017.
 */
final public class AppState extends HashMap<String, Object> {
    private static class InstanceHolder {
        static final AppState INSTANCE = new AppState();
    }
    public static AppState getInstance(){
        return InstanceHolder.INSTANCE;
    }
    private AppState() {
        super();
    }
}
