package com.emeraldElves.alcohollabelproject.Data;

import java.sql.JDBCType;

/**
 * Created by Essam on 4/17/2017.
 */
public class Attribute {
    private String propName;
    private JDBCType propType;
    public Attribute(String propName, JDBCType propType){
        this.propName = propName;
        this.propType = propType;
    }
    public JDBCType getType(){return this.propType;}
    public String getName(){return this.propName;}
}
