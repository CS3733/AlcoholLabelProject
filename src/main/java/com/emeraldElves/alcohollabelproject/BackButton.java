package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.BackStackData;

import java.util.Stack;

/**
 * Created by keionbis on 4/15/17.
 */
public class BackButton {
    public static Stack History = new Stack();

    public void AddToHistory(BackStackData prevControllerData){
        History.push(prevControllerData);

    }
    public static BackStackData GetFromHistory(){
        return (BackStackData) History.pop();
    }
}
