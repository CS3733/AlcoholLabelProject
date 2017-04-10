package com.emeraldElves.alcohollabelproject.IDGenerator;

/**
 * Created by Kylec on 4/9/2017.
 */
public class TimeGenerator implements IDGenerator {
    @Override
    /**
     * Generate an ID based on the current system time.
     */
    public String generateID() {
        return String.valueOf(getTime());
    }

    private int getTime() {
        return (int) (System.currentTimeMillis()/1000.0);
    }
}
