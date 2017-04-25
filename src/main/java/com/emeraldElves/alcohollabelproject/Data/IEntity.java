package com.emeraldElves.alcohollabelproject.Data;

import java.util.HashMap;

/**
 * Created by Essam on 4/25/2017.
 */
public interface IEntity {
    public void set(String propertyName, int value);
    public void set(String propertyName, long value);
    public void set(String propertyName, double value);
    public void set(String propertyName, Object value);
    public void set(String propertyName, String value);
    public String get(String propName);
    public HashMap<String, String> get();
    public Model getModel();
}
