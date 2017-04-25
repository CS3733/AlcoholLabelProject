package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by Essam on 4/25/2017.
 */
public class ManufacturerModel extends Model<ManufacturerEntity> {
    public ManufacturerModel(){
        super(Storage.getManufacturerSchema());
    }
    public ManufacturerEntity create(){
        return new ManufacturerEntity();
    }
}
