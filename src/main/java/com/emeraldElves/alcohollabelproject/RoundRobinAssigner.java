package com.emeraldElves.alcohollabelproject;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Joe on 4/9/2017.
 */
public class RoundRobinAssigner implements IAssigner {
    RoundRobin<String> roundRobin;

    public RoundRobinAssigner(RoundRobin<String> roundRobin) {
        this.roundRobin = roundRobin;
    }

    private String assignAgent(List<String> agentNames, String lastAssignedAgent) {
        String agent = "x";
        return agent;
    }
}
