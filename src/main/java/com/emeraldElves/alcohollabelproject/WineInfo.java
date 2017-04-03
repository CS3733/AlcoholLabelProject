package com.emeraldElves.alcohollabelproject;

/**
 * Created by Tarek on 4/3/2017.
 */
public class WineInfo{
    int vintageYear;
    int pH;
    public int getvintageYear() {
        return(this.vintageYear);
    }
    public int getpH() {
        return this.pH;
    }
    public void setVintageYear(int vintageYear){
        this.vintageYear = vintageYear;
    }
    public void setpH(int pH){
        this.pH = pH;
    }
}
