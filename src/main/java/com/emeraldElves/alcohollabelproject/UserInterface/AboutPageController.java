package com.emeraldElves.alcohollabelproject.UserInterface;

/**
 * Created by elijaheldredge on 4/22/17.
 */
public class AboutPageController implements IController {
    Main main;

   public AboutPageController() {

   }
   public void init(Bundle bundle){
       this.init(bundle.getMain("main"));
   }

    public void init(Main main) {
        this.main = main;
    }

}
