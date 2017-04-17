package com.emeraldElves.alcohollabelproject.Data;

import java.sql.JDBCType;
import java.util.*;

/**
 * Created by Essam on 4/9/2017.
 */
public class Entity {
    //TODO: Create an inner class that has propertyValue, attributefield, ismodified
    private HashMap<String, String> properties;
    private HashMap<String, Boolean> propModified;
    public boolean _isNew;
    private Model model;
    private Schema schema;
    public Entity(Model model){
        this.properties = new HashMap<String, String>();
        this.propModified = new HashMap<String, Boolean>();
        this.model = model;
        this.schema = model.getSchema();
        this._isNew = false;
    }
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
        //check that Class for value is same as class for property.
        properties.put(propertyName, value);
        propModified.put(propertyName, true);
    }
    public <T> T get(String propName){
        JDBCType propType = schema.getAttributeType(propName);

        switch (propType) {
            case INTEGER:
                return (T)Integer.valueOf(properties.get(propName));
            case BIGINT:
                return (T)Long.valueOf(properties.get(propName));
            case DOUBLE:
                return (T)Double.valueOf(properties.get(propName));
            case VARCHAR:
            default:
                return (T)properties.get(propName);
        }
    }
    /*public <T> T get(String propertyName){
        if(model.getSchema().getAttribute(propertyName).getType().cast == T)
            System.out.println("WOW");
        return (T)properties.get(propertyName);
    }
    public <T> T get(String propertyName, Class<T> dataType){
        return dataType.cast(properties.get(propertyName));
    }*/
    public HashMap<String, String> get(){
        return properties;
        /*
        HashMap<String, Object> modifiedProps = new HashMap<String, Object>();
        Iterator it = propModified.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (((Boolean)pair.getValue()).booleanValue()){
                modifiedProps.put((String)pair.getKey(), get((String)pair.getKey()));
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return modifiedProps;
        */
    }
    boolean save(){
        return model.save(this);
    }

}
