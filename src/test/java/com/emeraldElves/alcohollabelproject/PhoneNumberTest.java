package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.PhoneNumber;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kylec on 4/1/2017.
 */
public class PhoneNumberTest {

    @Test
    public void testPhoneNumber() {
        PhoneNumber number = new PhoneNumber("4014477334");
        assertEquals(number.getPhoneNumber(), "4014477334");
        assertTrue(number.isValid());

        PhoneNumber number1 = new PhoneNumber("1-401-447-7334");
        assertEquals(number1.getPhoneNumber(), "14014477334");
        assertTrue(number1.isValid());

        PhoneNumber number2 = new PhoneNumber("401.447.7334");
        assertEquals(number2.getPhoneNumber(), "4014477334");
        assertTrue(number2.isValid());


        PhoneNumber number3 = new PhoneNumber("40.447.7334");
        assertEquals(number3.getPhoneNumber(), "404477334");
        assertFalse(number3.isValid());
    }
}