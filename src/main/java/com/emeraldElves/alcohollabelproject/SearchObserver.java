package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.ApplicationEntity;
import javafx.collections.ObservableList;

/**
 * Created by Kylec on 4/16/2017.
 */
public class SearchObserver {

    private SearchSubject subject;
    private ObservableList<ApplicationEntity> applications;
    //private COLASearch colaSearch;

    public SearchObserver(SearchSubject subject, ObservableList<ApplicationEntity> applications) {
        this.subject = subject;
        this.applications = applications;
        //colaSearch = new COLASearch();
        this.subject.attachObserver(this);
    }

    public void update() {
        //applications.clear();
        //applications.addAll(colaSearch.searchByName(subject.getSearchTerm().trim()));
    }

}
