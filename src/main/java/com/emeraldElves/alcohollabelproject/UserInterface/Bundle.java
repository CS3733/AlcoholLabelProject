package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.PotentialUser;
import com.emeraldElves.alcohollabelproject.Data.SavedApplication;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kyle on 4/7/2017.
 */
public class Bundle {

    private Map<String, Object> data;

    public Bundle() {
        data = new HashMap<>();
    }

    public void putApplication(String key, SubmittedApplication application) {
        data.put(key, application);
    }

    public void putPotentialUser(String key, PotentialUser potentialUser){ data.put(key, potentialUser);}

    public void putMain(String key, Main main){ data.put(key, main);}

    public void putString(String key, String s){ data.put(key, s);}

    public void putSavedApplication(String key, SavedApplication savedApplication){data.put(key, savedApplication);}

    public SubmittedApplication getApplication(String key) {
        return (SubmittedApplication) data.get(key);
    }

    public PotentialUser getPotentialUser(String key){ return (PotentialUser) data.get(key);}

    public Main getMain(String key){ return (Main) data.get(key);}

    public String getString(String key){ return (String) data.get(key);}

    public SavedApplication getSavedApplication(String key){ return (SavedApplication) data.get(key);}

}
