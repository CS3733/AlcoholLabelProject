package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.AlcoholType;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import org.apache.commons.lang3.StringEscapeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Essam on 4/17/2017.
 */
public class UserCharExporter implements IExporter {
    char delim;
    String fileExt;
    public UserCharExporter (char delim, String fileExt) throws IllegalArgumentException {
        //TODO: Make sure the delim isn't \r, \n or double quotes.

        //Null characters not allowed as a delimiter in a string. We would need to write and read files as binary to be able to support that.
        //Quotes not allowed because we can't properly differentiate between a quote used as a delimiter and a quote used to start/end a string
        //Dots & integers not allowed because they exist in decimal numbers which are unquoted. I'm not sure why removing the quotes is a requirement for final iteration.
        //Spaces not allowed because spaces around delimiter seperated values should be ignored.
        //Can't be backslash because we use that to escape characters.
        //Can't be /r or /n because we use that at end of every line
        if (delim == '\n' || delim == '\r' || delim == '\u0000' || delim == '"' || delim == '.' || delim == ' ' || delim == '\\' || String.valueOf(delim).matches("[0-9]")){
            throw new IllegalArgumentException("Illegal delimiter");
        }
        this.delim = delim;
        this.fileExt = fileExt;
    }
    public String getFileExt(){return this.fileExt;}
    private String escapeStr(String str){
        //All values are turned to quoted strings to allow support for using delimiters such as , or .
        if (str == null) return "\"\"";
        //String escapedStr = (str.trim().replace("\\", "\\\\").replace("\"","\\\""));//.replaceAll(String.valueOf(delim),"\\\\" + String.valueOf(delim)));
        return "\"" + StringEscapeUtils.escapeJava(str) + "\"";
    }
    private String dateToStr(Date date){
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(date);
    }
    public String encode(List<SubmittedApplication> apps){
        String encoded_str = "status"+delim+"submitdate"+delim+"issuedate"+delim+"alcoholcontent"+delim+"fancifulName"+delim+"brandName"+delim+"origin"+delim+"type"+delim+"formula"+delim+"serial"+delim+"qualifications"+delim+"pH"+delim+"vintageyear"+delim+"appellation"+delim+"varietals\r\n";
        for (SubmittedApplication app: apps){
            encoded_str += String.valueOf(app.getStatus().getValue()) + delim; //status
            encoded_str += escapeStr(dateToStr(app.getApplication().getSubmissionDate())) + delim; //submission date
            encoded_str += escapeStr(dateToStr(app.getApplication().getSubmissionDate())) + delim; //approval date
            encoded_str += String.valueOf(app.getApplication().getAlcohol().getAlcoholContent()) + delim; //alcohol content
            encoded_str += escapeStr(app.getApplication().getAlcohol().getName()) + delim; //fanciul
            encoded_str += escapeStr(app.getApplication().getAlcohol().getBrandName()) + delim; //brandname
            encoded_str += String.valueOf(app.getApplication().getAlcohol().getOrigin()) + delim; //origin
            encoded_str += escapeStr(app.getApplication().getAlcohol().getAlcoholType().toText()) + delim; //type
            encoded_str += escapeStr(app.getApplication().getAlcohol().getFormula()) + delim; //formula
            encoded_str += escapeStr(app.getApplication().getAlcohol().getSerialNumber()) + delim; //serial
            encoded_str += escapeStr(app.getApplication().getQualifications()) + delim; //qualifications
            if (app.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
                encoded_str += String.valueOf(app.getApplication().getAlcohol().getWineInfo().pH) + delim; //ph
                encoded_str += String.valueOf(app.getApplication().getAlcohol().getWineInfo().vintageYear) + delim; //vintageyear
                encoded_str += escapeStr(app.getApplication().getAlcohol().getWineInfo().appellation) + delim; //appelation
                encoded_str += escapeStr(app.getApplication().getAlcohol().getWineInfo().grapeVarietal); //varietals
            }
            else {
                encoded_str += String.valueOf(delim) + String.valueOf(delim) + String.valueOf(delim);
            }
            encoded_str += "\r\n";
        }
        //System.out.println(escapeStr("\"Hello \r\n\","));
        return encoded_str;
    }
}
