package com.emeraldElves.alcohollabelproject.Data;

import java.sql.JDBCType;

/**
 * Created by Essam on 4/9/2017.
 */
public class ManufacturerEntity extends Entity {
    public ManufacturerEntity(){
        super(new ManufacturerModel());
    }

    public int getApplicationId(){return Integer.parseInt(this.get("applicationID"));}
    public void setApplicationId(int id){
        this.set("applicationID", id);
    }
    public String getAuthorizedName(){return this.get("authorizedName");}
    public void setAuthorizedName(String name){
        this.set("authorizedName", name);
    }
    public String getAddress(){return this.get("physicalAddress");}
    public void setAddress(String address){
        this.set("physicalAddress", address);
    }
    public String getCompanyName(){return this.get("company");}
    public void setCompanyName(String company){
        this.set("company", company);
    }
    public void setRepresentativeId(int id){
        this.set("representativeID", id);
    }
    public void setPermit(int num){
        this.set("permitNum", num);
    }
    public String getPhone(){return this.get("phoneNum");}
    public void setPhone(String phoneNum){
        this.set("phoneNum", phoneNum);
    }
    public String getEmail(){return this.get("emailAddress");}
    public void setEmail(String email){
        this.set("emailAddress", email);
    }

}
