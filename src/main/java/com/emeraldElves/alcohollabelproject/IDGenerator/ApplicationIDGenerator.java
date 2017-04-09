package com.emeraldElves.alcohollabelproject.IDGenerator;

/**
 * Created by Kylec on 4/9/2017.
 */
public class ApplicationIDGenerator {

    private IDGenerator generator;

    public ApplicationIDGenerator(IDGenerator generator) {
        this.generator = generator;
    }

    public String generateID() {
        return generator.generateID();
    }
}
