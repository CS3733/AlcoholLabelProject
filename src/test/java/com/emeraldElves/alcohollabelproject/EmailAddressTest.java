package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.EmailAddress;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kylec on 4/1/2017.
 */
public class EmailAddressTest {

    @Test
    public void testEmailAddress() {
        EmailAddress address = new EmailAddress("kylecorry31@gmail.com");
        assertTrue(address.isValid());


        EmailAddress address1 = new EmailAddress("kncorry@wpi.edu");
        assertTrue(address1.isValid());


        EmailAddress address2 = new EmailAddress("k@com.@test.com");
        assertFalse(address2.isValid());
    }

}