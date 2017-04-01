package com.emeraldElves.alcohollabelproject;

import java.util.regex.Pattern;

/**
 * Created by Kylec on 4/1/2017.
 */
public class EmailAddress {
    private String emailAddress;

    public EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public boolean isValid() {
        Pattern email = Pattern.compile("^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$" );
        return email.matcher(emailAddress).matches();
    }
}
