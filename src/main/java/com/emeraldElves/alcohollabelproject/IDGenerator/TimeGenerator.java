package com.emeraldElves.alcohollabelproject.IDGenerator;

/**
 * Created by Kylec on 4/9/2017.
 */
public class TimeGenerator implements IDGenerator {
    @Override
    public String generateID() {
        return String.valueOf(getTime());
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
