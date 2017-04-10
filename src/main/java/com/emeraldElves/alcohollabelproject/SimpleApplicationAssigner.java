package com.emeraldElves.alcohollabelproject;

import java.util.List;

/**
 * Created by Joe on 4/9/2017.
 */
public class SimpleApplicationAssigner extends ApplicationAssigner {
    IAssigner assigner; //fix this

    public SimpleApplicationAssigner(List<String> agentUserNames) {
        super(assigner, agentUserNames);
    }

    public SimpleApplicationAssigner(List<String> agentUserNames, String lastAssignedAgent) {
        super(assigner, agentUserNames);
    }
}

