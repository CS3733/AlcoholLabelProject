package com.emeraldElves.alcohollabelproject.Data;

import java.util.List;

/**
 * Created by Kyle on 4/30/2017.
 */
public class LazyAssigner implements IAssigner {
    @Override
    public String assignAgent(List<String> agentNames, String nextAgentToAssign) {
        return "PENDING";
    }
}
