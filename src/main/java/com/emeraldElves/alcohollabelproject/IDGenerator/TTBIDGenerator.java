package com.emeraldElves.alcohollabelproject.IDGenerator;

/**
 * Created by Kylec on 4/9/2017.
 */
public class TTBIDGenerator extends ApplicationIDGenerator {

    public TTBIDGenerator(int counter){
        super(new TTBFormatGenerator(counter, 1));
    }

}
