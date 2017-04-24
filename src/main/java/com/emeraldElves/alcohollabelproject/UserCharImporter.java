package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.AlcoholType;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public class UserCharImporter implements IImporter {
    char delim;

    public UserCharImporter(char delim) throws IllegalArgumentException {
        this.delim = delim;
    }
    //unescapes a single line/row
    private String unescapeStr(String str){
        //All values are turned to quoted strings to allow support for using delimiters such as , or .
        if (str == null) return "\"\"";
        String escapedStr = (str.trim().replace("\\", "\\\\").replace("\"","\\\""));//.replaceAll(String.valueOf(delim),"\\\\" + String.valueOf(delim)));
        return "\"" + escapedStr + "\"";
    }
    //throws IllegalFormatException if format isn't as expected with selected delim
    public List<SubmittedApplication> decode(String encodedStr) throws IllegalFormatException {
        List<String> lines = Arrays.asList(encodedStr.split("\\r?\\n"));
        for (String line : lines){
            List<String> row = Arrays.asList(line.split("\""));
        }
        return new ArrayList<SubmittedApplication>();
    }
}
