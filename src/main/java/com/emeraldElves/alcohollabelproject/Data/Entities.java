package com.emeraldElves.alcohollabelproject.Data;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Essam on 4/17/2017.
 */
public class Entities <T extends Entity> extends ArrayList<T> {
    public void set(String propertyName, int value) {
        set(propertyName, String.valueOf(value));
    }
    public void set(String propertyName, long value) {

        set(propertyName, String.valueOf(value));
    }
    public void set(String propertyName, double value) {

        set(propertyName, String.valueOf(value));
    }
    public void set(String propertyName, Object value) {

        set(propertyName, String.valueOf(value));
    }
    public void set(String propertyName, String value){
        for (int i = 0; i < size(); i++){
            get(i).set(propertyName, value);
        }
    }
    public void save(){
        
    }
}
