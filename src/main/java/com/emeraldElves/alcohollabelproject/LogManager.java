package com.emeraldElves.alcohollabelproject;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Tarek on 4/9/2017.
 */
public class LogManager {

    private static LogManager _Log;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss");

    private LogManager() {}
    private static class logSingleton{
        public static final LogManager instance = new LogManager();
    }
    public static LogManager getInstance(){
        return logSingleton.instance;
    }

    public void writeFile (String path) {
        try {
            FileUtils.writeStringToFile(new File("Log.txt"), path, true);
        }
        catch (IOException e) {
            System.out.println("unable to create file");
        }
    }
    public void logAction(String classTag, String specialMessage) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String newTime = sdf.format(timestamp);
        writeFile(newTime + " " + classTag + " " + specialMessage + "\n");
    }

}
