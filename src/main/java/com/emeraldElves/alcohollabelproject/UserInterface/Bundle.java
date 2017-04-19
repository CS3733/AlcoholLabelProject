package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.Controller;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kyle on 4/7/2017.
 */
public class Bundle implements Controller {

    private Map<String, Object> data;

    public Bundle() {
        data = new HashMap<>();
    }

    public void putApplication(String key, SubmittedApplication application) {
        data.put(key, application);
    }

    public SubmittedApplication getApplication(String key) {
        return (SubmittedApplication) data.get(key);
    }

}
