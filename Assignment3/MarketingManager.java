/**
 * Created by Joe on 4/3/2017.
 */

import java.util.ArrayList;

public class MarketingManager extends MarketingEmployee {
    protected String name;
    protected MarketingManager manager;
    protected long marketingID;
    protected ArrayList<MarketingEmployee> henchmen;

    public MarketingManager(String name, MarketingManager manager, long marketingID) {
        this.name = name;
        this.manager = manager;
        this.marketingID = marketingID;
        this.henchmen = new ArrayList<MarketingEmployee>();
    }

    // getter functions
    protected String getName() {
        return name;
    }
    protected MarketingManager getManager() {
        return manager;
    }
    protected long getMarketingID() {
        return marketingID;
    }

    protected void addHenchman(MarketingEmployee henchman) {
        henchmen.add(henchman);
    }

    protected ArrayList<MarketingEmployee> getAssociates() {
        return henchmen;
    }
}
