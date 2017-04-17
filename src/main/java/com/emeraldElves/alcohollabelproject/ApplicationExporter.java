package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.UserInterface.Main;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public class ApplicationExporter {
    IExporter serializer;
    public ApplicationExporter(IExporter serializer){
        this.serializer = serializer;
    }
    public boolean export(List<SubmittedApplication> apps){
        String encoded_str = serializer.encode(apps);
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Save Results");
        fileChooser.setInitialFileName("results.csv");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv"));
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv"));


        File file = fileChooser.showSaveDialog(Main.stage);
        if (file == null) {
            return false;
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(encoded_str);
            fileWriter.close();
            return true;
        }
        catch(IOException e) {
            return false;
        }
    }
    public boolean export(SubmittedApplication app){
        List<SubmittedApplication> apps = new ArrayList<SubmittedApplication>();
        apps.add(app);
        return export(apps);
    }
}
