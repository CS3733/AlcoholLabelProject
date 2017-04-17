package com.emeraldElves.alcohollabelproject.Data;

import java.sql.JDBCType;
import java.util.*;

/**
 * Created by Essam on 4/9/2017.
 */
public class Schema {
    private final Map<String, JDBCType> attributes = new HashMap<String, JDBCType>();
    private String tblName;
    public Schema(String tblName){
        this.tblName = tblName;
        /*for (Attribute attr : attributes) {
            this.attributes.put(attr.getName(), attr);
        }*/
    }
    public String getName(){return this.tblName;}
    public JDBCType getAttributeType(String attrName){
        return this.attributes.get(attrName);
    }
    public Map<String, JDBCType> getAttributes(){
        return this.attributes;
    }
}
