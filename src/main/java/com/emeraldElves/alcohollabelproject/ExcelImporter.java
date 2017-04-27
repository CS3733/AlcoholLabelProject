package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.BaseEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public class ExcelImporter implements IImporter {
    public ExcelImporter() {

    }
    //unescapes a single line/row
    private String unescapeStr(String str){
        //All values are turned to quoted strings to allow support for using delimiters such as , or .
        if (str == null) return "\"\"";
        String escapedStr = (str.trim().replace("\\", "\\\\").replace("\"","\\\""));//.replaceAll(String.valueOf(delim),"\\\\" + String.valueOf(delim)));
        return "\"" + escapedStr + "\"";
    }
    //throws IllegalFormatException if format isn't as expected with selected delim
    public List<BaseEntity> decode(InputStream fstream) throws IllegalFormatException {
        return new ArrayList<BaseEntity>();
    }
}
