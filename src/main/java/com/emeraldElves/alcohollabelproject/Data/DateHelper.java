package com.emeraldElves.alcohollabelproject.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by keionbis on 4/11/17.
 */
public class DateHelper {

    public static Date getDate(int day, int month, int year) {
        Calendar calendar = new Calendar.Builder()
                .setDate(year, month, day)
                .build();
        return calendar.getTime();
    }

    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
        return dateFormat.format(date);
    }
}
