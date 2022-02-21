package com.revature;

public class BankingDOAFactory {
    private static BankDOA doa;

    private BankingDOAFactory()
    {}
    public static BankDOA getBankingDOA()
    {
        if(doa == null)

            doa = new BankDOAimpl();


        return doa;
    }

}
