package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.List;

/**
 * Created by Kylec on 4/11/2017.
 */
public class COLASearch {

    private Storage storage;

    public COLASearch(){
        storage = Storage.getInstance();
    }

    public List<SubmittedApplication> searchByBrandName(String brandName){
        return storage.getApplicationsByBrandName(brandName);
    }

    public List<SubmittedApplication> searchByFancifulName(String fancifulName){
        return storage.getApplicationsByFancifulName(fancifulName);
    }

    public List<SubmittedApplication> searchByName(String brandOrFancifulName, int rows, boolean hideBeer, boolean hideWine, boolean hideSpirits){
        return storage.getApplicationsByName(brandOrFancifulName, rows, hideBeer, hideWine, hideSpirits);
    }

    public List<SubmittedApplication> searchApprovedApplications(boolean hideBeer, boolean hideWine, boolean hideSpirits){
        return storage.getApprovedApplications(hideBeer, hideWine, hideSpirits);
    }

    public List<SubmittedApplication> searchRecentApplications(int numApps){
        return storage.getRecentlyApprovedApplications(numApps);
    }
}
