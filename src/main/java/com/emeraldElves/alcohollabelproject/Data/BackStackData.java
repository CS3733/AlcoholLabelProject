package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by keionbis on 4/17/17.
 */
public class BackStackData {

        private Controller PreviousController;
        private String PrevScreen;

    public BackStackData(Controller PreviousController, String PrevScreen) {
        this.PreviousController = PreviousController;
        this.PrevScreen = PrevScreen;
    }

}
