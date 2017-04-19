package com.emeraldElves.alcohollabelproject.IDGenerator;

/**
 * Created by Kylec on 4/9/2017.
 */
public class TimeIDGenerator extends ApplicationIDGenerator {

    /**
     * Create an ID generator which uses the current time of the system.
     */
    public TimeIDGenerator() {
        super(new TimeGenerator());
    }

}
