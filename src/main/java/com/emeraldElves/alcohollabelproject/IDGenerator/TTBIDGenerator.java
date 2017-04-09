package com.emeraldElves.alcohollabelproject.IDGenerator;

/**
 * Created by Kylec on 4/9/2017.
 */
public class TTBIDGenerator extends ApplicationIDGenerator {

    /**
     * Create a generator which produces application IDs in the TTB format of year, julian date, submission type, and application count for the day
     *
     * @param counter The current number of applications submitted in the day.
     */
    public TTBIDGenerator(int counter) {
        super(new TTBFormatGenerator(counter, 1));
    }

}
