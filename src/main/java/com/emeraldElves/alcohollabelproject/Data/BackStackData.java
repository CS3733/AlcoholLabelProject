package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.UserInterface.Bundle;

/**
 * Created by keionbis on 4/17/17.
 */
public class BackStackData {

        private Bundle PreviousBundle;
        private String PrevScreen;

    public BackStackData(Bundle PrevousBundle, String PrevScreen) {
        this.PreviousBundle = PrevousBundle;
        this.PrevScreen = PrevScreen;
    }

}
