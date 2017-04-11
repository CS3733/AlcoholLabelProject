package com.emeraldElves.alcohollabelproject;

import java.util.List;

/**
 * Created by Joe on 4/9/2017.
 */
public class RoundRobinAssigner implements IAssigner {

    public String assignAgent(List<String> agentNames, String lastAssignedAgent) {
        RoundRobin<String> roundRobin = new RoundRobin(agentNames);

        while (roundRobin.next() != lastAssignedAgent); // iterative search for agent's name
        return roundRobin.next(); // search for next agent
    }
}
