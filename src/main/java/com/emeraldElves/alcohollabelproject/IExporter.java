package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.List;

/**
 * Created by Essam on 4/17/2017.
 */
public interface IExporter {
    //Instead of having encode and encodeAll, we can use only encode with a List of 0 or more items.
    public String encode(List<SubmittedApplication> apps);
}
