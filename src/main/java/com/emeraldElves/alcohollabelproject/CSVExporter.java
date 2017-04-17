package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public class CSVExporter implements IExporter {
    public String encode(List<SubmittedApplication> apps){
        String encoded_str = "alcoholcontent, fancifulName, brandName, origin, type, formula, serial, pH, vintageyear, appellation, varietals\r\n";
        for (SubmittedApplication app: apps){
            encoded_str += app.getApplication().getAlcohol().getAlcoholContent() + ","; //alcohol content
            encoded_str += StringEscapeUtils.escapeCsv(app.getApplication().getAlcohol().getName()) + ","; //fanciul
            encoded_str += StringEscapeUtils.escapeCsv(app.getApplication().getAlcohol().getBrandName()) + ","; //brandname
            encoded_str += app.getApplication().getAlcohol().getOrigin() + ","; //origin
            encoded_str += app.getApplication().getAlcohol().getAlcoholType() + ","; //type
            encoded_str += StringEscapeUtils.escapeCsv(app.getApplication().getAlcohol().getFormula()) + ","; //formula
            encoded_str += StringEscapeUtils.escapeCsv(app.getApplication().getAlcohol().getSerialNumber()) + ","; //serial
            encoded_str += app.getApplication().getAlcohol().getWineInfo().pH + ","; //ph
            encoded_str += app.getApplication().getAlcohol().getWineInfo().vintageYear + ","; //vintageyear
            encoded_str += StringEscapeUtils.escapeCsv(app.getApplication().getAlcohol().getWineInfo().appellation) + ","; //appelation
            encoded_str += StringEscapeUtils.escapeCsv(app.getApplication().getAlcohol().getWineInfo().grapeVarietal) + ","; //varietals
            encoded_str += "\r\n";
        }
        return encoded_str;
    }
}
