package com.emeraldElves.alcohollabelproject.Data;

import org.apache.commons.lang3.StringEscapeUtils;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Essam on 4/9/2017.
 */
public class Model <T extends Entity> {
    private Schema schema;
    private Database db;
    public Model(Schema schema){
        this.schema = schema;

        //Make sure that there's one (and only one) primaryKey!
    }
    public Schema getSchema(){return this.schema;}
    public T retrieve(int id){
        Entities<T> entities = find("id", id);
        if (entities.size() == 0) return null;
        return entities.get(0);
    }
    private static String escapeSQL(String s){
        int length = s.length();
        int newLength = length;
        // first check for characters that might
        // be dangerous and calculate a length
        // of the string that has escapes.
        for (int i=0; i<length; i++){
            char c = s.charAt(i);
            switch(c){
                case '\\':
                case '\"':
                case '\'':
                case '\0':{
                    newLength += 1;
                } break;
            }
        }
        if (length == newLength){
            // nothing to escape in the string
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i=0; i<length; i++){
            char c = s.charAt(i);
            switch(c){
                case '\\':{
                    sb.append("\\\\");
                } break;
                case '\"':{
                    sb.append("\\\"");
                } break;
                case '\'':{
                    sb.append("\\\'");
                } break;
                case '\0':{
                    sb.append("\\0");
                } break;
                default: {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public Entities<T> find(HashMap<String, String> conditions) {
        Entities<T> entities = new Entities<T>();
        List<String> conditionList = new ArrayList<String>();
        for (String propName : schema.getAttributes().keySet()){
            JDBCType propType = schema.getAttributeType(propName);
            if (conditions.containsKey(propName)) {
                switch (propType){
                    case VARCHAR:
                        conditionList.add(propName + " = '" + escapeSQL(conditions.get(propName)) + "'");
                        break;
                    default:
                        conditionList.add(propName + " = " + escapeSQL(conditions.get(propName)));

                }

            }
        }
        String valuesStr = String.join(" AND ", conditionList);
        ResultSet rs = Storage.getInstance().db.select("*", getSchema().getName(), valuesStr);
        try {
            while (rs.next()) {
                T entity = (T)new Entity(this);
                for (String propName : schema.getAttributes().keySet()){
                    JDBCType propType = schema.getAttributeType(propName);

                    switch (propType) {
                        case INTEGER:
                            entity.set(propName, rs.getInt(propName));
                            break;
                        case BIGINT:
                            entity.set(propName, rs.getLong(propName));
                            break;
                        case VARCHAR:
                            entity.set(propName, rs.getString(propName));
                            break;
                        case DOUBLE:
                            entity.set(propName, rs.getDouble(propName));
                            break;
                        default:
                            break;
                    }

                }
                entities.add(entity);
            }
            return entities;
        }
        catch(SQLException e){
            System.err.print("Error finding Entities");
            e.printStackTrace();
            return null;
        }

    }
    public Entities<T> find(Object... args) {
        if (args.length % 2 != 0){
            throw new IllegalArgumentException("Model::find called with invalid number of arguments.");
        }
        HashMap<String, String> conditions = new HashMap<String, String>();
        Object[] argsArray = args;
        for (int i = 0; i < args.length; i+=2){
            conditions.put(String.valueOf(argsArray[i]), String.valueOf(argsArray[i+1]));
        }
        return find(conditions);
    }

    public boolean save(T instance){
        return true;
        /*

        HashMap<String, String> props = instance.get();
        List<String> valuesList = new ArrayList<String>();
        for (String propName : props.keySet()){
            SchemaType propType = schema.getAttribute(propName).getType();
            valuesList.add(propName + " = " + propType.escape(props.get(propName)));
        }
        String valuesStr = String.join(", ", valuesList);
        if (instance._isNew){
            //insert
            return db.insert(valuesStr, this.tblName);
        }
        else {
            //update

            return db.update(this.tblName, valuesStr, "id = " + schema.getAttribute("id").getType().escape(instance.get("id")));
        }
        */

    }
    //find(HashMap kv) -- find all instances that matches kv
    //save(Instance)
    //create(HashMap kv) --creates new instance with defined values or default values (for undefined ones)

}
