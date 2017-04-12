package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.RoundRobin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kylec on 4/4/2017.
 */
public class RoundRobinTest {

    RoundRobin<String> roundRobin;


    @Before
    public void setup() {
        roundRobin = new RoundRobin<>();
    }

    @Test
    public void add() throws Exception {
        roundRobin.add("Test");
        roundRobin.add("Test2");
        roundRobin.add("Test3");
        assertEquals(3, roundRobin.size());
    }

    @Test
    public void remove() throws Exception {
        roundRobin.add("Test");
        roundRobin.add("Test2");
        roundRobin.add("Test3");
        roundRobin.remove("Test");
        assertEquals(2, roundRobin.size());
    }

    @Test
    public void next() throws Exception {
        roundRobin.add("Test");
        roundRobin.add("Test2");
        roundRobin.add("Test3");
        assertEquals("Test", roundRobin.next());
        assertEquals("Test2", roundRobin.next());
        assertEquals("Test3", roundRobin.next());
        assertEquals("Test", roundRobin.next());
    }

    @Test
    public void setPosition() throws Exception {
        roundRobin.add("Test");
        roundRobin.add("Test2");
        roundRobin.add("Test3");
        roundRobin.setPosition(2);
        assertEquals("Test3", roundRobin.next());
        assertEquals("Test", roundRobin.next());
        assertEquals("Test2", roundRobin.next());
        assertEquals("Test3", roundRobin.next());
        roundRobin.setPosition(10);
        assertEquals("Test2", roundRobin.next());
    }

}