package com.emeraldElves.alcohollabelproject.IDGenerator;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Kylec on 4/9/2017.
 */
public class ApplicationIDGeneratorTest {

    @Test
    public void testTimeGenerator() {
        ApplicationIDGenerator generator = new TimeIDGenerator();
        long time = System.currentTimeMillis();
        String timeVal = String.valueOf(time);
        String id = generator.generateID();
        // This could vary by a few milliseconds, so I only care that it passes a certain threshold
        assertEquals(timeVal.substring(0, timeVal.length() - 3), id);
    }


    @Test
    public void testTTBGenerator(){
        // Don't run this test milliseconds before midnight, or it may fail :)
        ApplicationIDGenerator generator = new TTBIDGenerator(0);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYDDD");
        String formattedDate = dateFormat.format(date);
        assertEquals(formattedDate + "001000000", generator.generateID());
        assertEquals(formattedDate + "001000001", generator.generateID());
        assertEquals(formattedDate + "001000002", generator.generateID());
    }


}