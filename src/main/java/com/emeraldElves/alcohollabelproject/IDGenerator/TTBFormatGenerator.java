package com.emeraldElves.alcohollabelproject.IDGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kylec on 4/9/2017.
 */
public class TTBFormatGenerator implements IDGenerator {

    private int counter;
    private int fileMethod;

    public TTBFormatGenerator(int counter, int fileMethod) {
        this.counter = counter;
        this.fileMethod = fileMethod;
    }

    @Override
    public String generateID() {
        String id = getCalendarNumbers() + padZeros(fileMethod, 3) + padZeros(counter, 6);
        counter++;
        return id;
    }

    private String padZeros(int value, int desiredLength){
        String strVal = String.valueOf(value);
        while(strVal.length() < desiredLength)
            strVal = "0" + strVal;
        return strVal;
    }

    private String getCalendarNumbers(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYDDD");
        return dateFormat.format(date);
    }
}
