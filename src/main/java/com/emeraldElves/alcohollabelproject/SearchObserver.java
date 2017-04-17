package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.collections.ObservableList;

/**
 * Created by Kylec on 4/16/2017.
 */
public class SearchObserver {

    private SearchSubject subject;
    private ObservableList<SubmittedApplication> applications;
    private COLASearch colaSearch;

    public SearchObserver(SearchSubject subject, ObservableList<SubmittedApplication> applications) {
        this.subject = subject;
        this.applications = applications;
        colaSearch = new COLASearch();
        this.subject.attachObserver(this);
    }

    public void update() {
        applications.clear();
        applications.addAll(colaSearch.searchByName(subject.getSearchTerm().trim()));
    }

}
