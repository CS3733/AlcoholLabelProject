package com.emeraldElves.alcohollabelproject.Data;

import java.io.IOException;

/**
 * Created by Essam on 4/25/2017.
 */
public interface IModel <T extends Entity> {
    //public void save(T instance) throws IOException;
    public Entities<T> find(Object... args);
    public T retrieve(int id);
}
