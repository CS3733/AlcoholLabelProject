package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by Essam on 4/25/2017.
 */
public class AlcoholModel extends Model {
    public AlcoholModel(){
        super(Storage.getAlcoholSchema());
    }
    public AlcoholEntity create(){
        return new AlcoholEntity();
    }
}
