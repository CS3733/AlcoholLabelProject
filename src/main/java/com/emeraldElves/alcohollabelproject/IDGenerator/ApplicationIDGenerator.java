package com.emeraldElves.alcohollabelproject.IDGenerator;

/**
 * Created by Kylec on 4/9/2017.
 */
public class ApplicationIDGenerator {

    private IDGenerator generator;

    /**
     * Create an ID generator for applications.
     *
     * @param generator The generator algorithm to use.
     */
    public ApplicationIDGenerator(IDGenerator generator) {
        this.generator = generator;
    }

    /**
     * Generate an ID for an application
     *
     * @return The ID as a {@link String}
     */
    public String generateID() {
        return generator.generateID();
    }
}
