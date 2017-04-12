/**
 * Created by Joe on 4/3/2017.
 */

import java.util.ArrayList;

public class MarketingAssociate extends MarketingEmployee {
    protected String name;
    protected MarketingManager manager;
    protected long marketingID;
    protected ArrayList<Client> clients;

    public MarketingAssociate(String name, MarketingManager manager, long marketingID) {
        this.name = name;
        this.manager = manager;
        this.marketingID = marketingID;
        this.clients = new ArrayList<Client>();
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

    protected void addClient(String accountNumber) {
        int number = 1;
        while (true) {
            for ()
        }
        Client newClient = new Client();

    }

    protected double commissionRate() {

    }
}
