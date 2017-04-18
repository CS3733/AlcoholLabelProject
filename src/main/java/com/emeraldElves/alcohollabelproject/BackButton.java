package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.BackStackData;

import java.util.Stack;

/**
 * Created by keionbis on 4/15/17.
 */
public class BackButton {
    public Stack History = new Stack();
    public void AddToHistory(BackStackData prevControllerData){
        History.push(prevControllerData);

    }
    public BackStackData GetFromHistory(){
        return (BackStackData) History.pop();
    }
}
