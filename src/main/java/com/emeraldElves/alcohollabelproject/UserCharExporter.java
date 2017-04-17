package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public class UserCharExporter implements IExporter {
    char delim;
    public UserCharExporter (char delim){
        this.delim = delim;
    }
    private String escapeStr(String str){
        //All values are turned to quoted strings to allow support for using delimiters such as , or .
        return "\"" + StringEscapeUtils.escapeJson(str) + "\"";
    }
    public String encode(List<SubmittedApplication> apps){
        String encoded_str = "alcoholcontent"+delim+"fancifulName"+delim+"brandName"+delim+"origin"+delim+"type"+delim+"formula"+delim+"serial"+delim+"pH"+delim+"vintageyear"+delim+"appellation"+delim+"varietals\r\n";
        for (SubmittedApplication app: apps){
            encoded_str += escapeStr(String.valueOf(app.getApplication().getAlcohol().getAlcoholContent())) + delim; //alcohol content
            encoded_str += escapeStr(app.getApplication().getAlcohol().getName()) + delim; //fanciul
            encoded_str += escapeStr(app.getApplication().getAlcohol().getBrandName()) + delim; //brandname
            encoded_str += escapeStr(String.valueOf(app.getApplication().getAlcohol().getOrigin())) + delim; //origin
            encoded_str += escapeStr(String.valueOf(app.getApplication().getAlcohol().getAlcoholType())) + delim; //type
            encoded_str += escapeStr(app.getApplication().getAlcohol().getFormula()) + delim; //formula
            encoded_str += escapeStr(app.getApplication().getAlcohol().getSerialNumber()) + delim; //serial
            encoded_str += escapeStr(String.valueOf(app.getApplication().getAlcohol().getWineInfo().pH)) + delim; //ph
            encoded_str += escapeStr(String.valueOf(app.getApplication().getAlcohol().getWineInfo().vintageYear)) + delim; //vintageyear
            encoded_str += escapeStr(app.getApplication().getAlcohol().getWineInfo().appellation) + delim; //appelation
            encoded_str += escapeStr(app.getApplication().getAlcohol().getWineInfo().grapeVarietal) + delim; //varietals
            encoded_str += "\r\n";
        }
        return encoded_str;
    }
}
