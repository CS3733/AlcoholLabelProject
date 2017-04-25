package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Data.Attribute;

import java.sql.JDBCType;
import java.util.*;

/**
 * Created by Essam on 4/9/2017.
 */
public class Schema {
    private final Map<String, JDBCType> attributes = new HashMap<String, JDBCType>();
    private String tblName;
    public Schema(String tblName, Attribute... attrs){
        this.tblName = tblName;
        for (Attribute attr : attrs) {
            this.attributes.put(attr.getName(), attr.getType());
        }
    }
    public String getName(){return this.tblName;}
    public JDBCType getAttributeType(String attrName){
        return this.attributes.get(attrName);
    }
    public Map<String, JDBCType> getAttributes(){
        return this.attributes;
    }
}
