package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public class CSVExporter extends UserCharExporter {
    public CSVExporter(){
        super(',', "csv");
    }
}
