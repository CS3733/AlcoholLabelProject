package com.emeraldElves.alcohollabelproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kylec on 4/16/2017.
 */
public class SearchSubject {

    private List<SearchObserver> observers;
    private String searchTerm;


    public SearchSubject() {
        observers = new LinkedList<>();
    }

    public void attachObserver(SearchObserver observer) {
        observers.add(observer);
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
        notifyObservers();
    }


    public void notifyObservers() {
        for (SearchObserver observer : observers) {
            observer.update();
        }
    }


}
