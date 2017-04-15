package com.emeraldElves.alcohollabelproject;

import java.util.Stack;

/**
 * Created by keionbis on 4/15/17.
 */
public class BackButton {
    public Stack History = new Stack();
    public void AddToHistory(ControllerData prevController){
        History.push(prevController);

    }
}
