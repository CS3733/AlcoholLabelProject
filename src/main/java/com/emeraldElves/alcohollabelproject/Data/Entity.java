package com.emeraldElves.alcohollabelproject.Data;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Essam on 4/9/2017.
 */
public abstract class Entity implements IEntity {
    final private HashMap<String, String> properties = new HashMap<String, String>();
    final private HashMap<String, Boolean> propModified = new HashMap<String, Boolean>();
    public boolean isNew = true;
    private Model model;
    public Entity(Model model){
        this.model = model;
    }
    public Model getModel(){return model;}
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
    public int getInt(String propName){
        return Integer.parseInt(this.get(propName));
    }
    public double getDouble(String propName){
        return Double.parseDouble(this.get(propName));
    }
    public boolean getBoolean(String propName){
        return Boolean.parseBoolean(this.get(propName));
    }
    public long getLong(String propName){
        return Long.parseLong(this.get(propName));
    }
    public String getString(String propName){
        return this.get(propName);
    }
    public String get(String propName){
        return properties.get(propName);
        /*
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
        */
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

    public void save() throws IOException {
        //TODO: Implement save here.
        //HashMap<String, String> values = this.get();
        for (String key : properties.keySet()){
            System.out.println(key + " - " + properties.get(key));
        }
    }
}
