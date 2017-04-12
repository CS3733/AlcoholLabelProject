package com.emeraldElves.alcohollabelproject.Data;

import java.util.List;

/**
 * Created by Joe on 4/9/2017.
 */
public class RoundRobinAssigner implements IAssigner {

    public String assignAgent(List<String> agentUserNames, String lastAssignedAgent) {
        RoundRobin<String> roundRobin = new RoundRobin(agentUserNames);

        int pos = roundRobin.find(lastAssignedAgent);
        roundRobin.setPosition(pos); // set cursor back one
        roundRobin.next(); // apply next() twice to return correct next string
        return roundRobin.next();
    }
}
