package com.revature;

import java.sql.SQLException;

public class addingAccount{
    private final int amount;
    private final int Customer_ID;

    public addingAccount(int Customer_ID,int amount) {
        this.Customer_ID = Customer_ID;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }
}
