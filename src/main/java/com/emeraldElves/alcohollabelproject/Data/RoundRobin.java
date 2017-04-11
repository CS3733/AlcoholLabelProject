package com.emeraldElves.alcohollabelproject.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kylec on 4/4/2017.
 */
public class RoundRobin<T> {

    private List<T> values;
    private int pos = 0;

    public RoundRobin() {
        this.values = new ArrayList<>();
    }

    public RoundRobin(List<T> values) {
        this.values = values;
    }

    public void add(T value) {
        values.add(value);
    }

    public void remove(T value) {
        values.remove(value);
    }

    public int size() {
        return values.size();
    }

    public T next() {
        pos %= values.size();
        T value = values.get(pos);
        pos++;
        pos %= values.size();
        return value;
    }


    public void setPosition(int position) {
        pos = position;
        pos %= values.size();
    }

}
