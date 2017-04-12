package com.emeraldElves.alcohollabelproject.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by keionbis on 4/11/17.
 */
public class DateHelper {

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = new Calendar.Builder()
                .setDate(year, month, day)
                .build();
        Date date = calendar.getTime();
        return date;
    }
}
