package com.emeraldElves.alcohollabelproject.IDGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kylec on 4/9/2017.
 */
public class TTBFormatGenerator implements IDGenerator {

    private int counter;
    private int fileMethod;

    /**
     * Create a generator which uses the TTB format.
     * @param counter The number of applications submitted for the current day.
     * @param fileMethod The method of submission: 1 for electronic
     */
    public TTBFormatGenerator(int counter, int fileMethod) {
        this.counter = counter;
        this.fileMethod = fileMethod;
    }

    @Override
    public String generateID() {
        String id = getCalendarNumbers() + fileMethod + padZeros(counter, 4);
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
