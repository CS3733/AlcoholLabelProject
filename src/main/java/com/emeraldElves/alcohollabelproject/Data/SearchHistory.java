package com.emeraldElves.alcohollabelproject.Data;

import java.util.Stack;

/**
 * Created by keionbis on 4/18/17.
 */
public class SearchHistory {
    Stack searchHistory = new Stack();

    public void addMostRecentSearch(String searchText){
        searchHistory.push(searchText);
    }

    public String getMostRecentSearch(){
        return (String) searchHistory.pop();
    }
}
