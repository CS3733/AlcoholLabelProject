/**
 * Created by Joe on 4/3/2017.
 */

import java.util.ArrayList;

public class Client {
    protected String accountNumber;
    protected String name;

    public Client(String accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
    }

    // getter functions
    protected String getAccountNumber() {
        return accountNumber;
    }
    protected String getName() {
        return name;
    }

    protected double totalSales() {

    }
}
