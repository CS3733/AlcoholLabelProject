package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.UserInterface.Main;
import javafx.stage.FileChooser;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public class ApplicationImporter {
    IImporter deserializer;
    public ApplicationImporter(IImporter deserializer){
        this.deserializer = deserializer;
    }
    public List<SubmittedApplication> importFromFile() throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Import Applications");
        File file = fileChooser.showOpenDialog(Main.stage);

        //redundant, FileInputStream will throw that error if it's null
        /*if (file == null) {
            throw new FileNotFoundException();
        }*/

        String encodedStr = IOUtils.toString(new FileInputStream(file));
        return deserializer.decode(encodedStr);

    }
}
