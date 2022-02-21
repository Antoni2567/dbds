package com.revature;

import java.sql.SQLException;
import java.util.List;

public interface BankDOA {
    void transferToCheckings(int amount,int Customer_ID) throws SQLException;
    void transferToSavings(int amount, int Customer_ID) throws SQLException;
    void transferFromC(int amount, int Customer_ID,int member) throws SQLException;
    void transferFromS(int amount,int Customer_ID,int member) throws SQLException;
    void WithdrawalC(int amount, int Customer_ID) throws SQLException;
    void WithdrawalS(int amount,int Customer_ID) throws SQLException;
    void DepositS(int amount, int Customer_ID) throws SQLException;
    void Deposit(int amount, int Customer_ID) throws SQLException;
    void DepositC(int Customer_ID,int amount) throws SQLException;
    void addnCustomer(nCustomer customer) throws SQLException;
    void addChecking(int Customer_ID,String first_name ,int Balance) throws SQLException;
    void addChecking(int Customer_ID, String first_name) throws SQLException;
    void Login(int Customer_ID,String username,String password) throws SQLException;
    boolean checkUserLogin(int Customer_ID, String username) throws SQLException;
    boolean checkPassLogin(int Customer_ID, String password) throws SQLException;
    boolean checkCustId(int Customer_ID) throws SQLException;
    void checkCheckings(int Customer_ID) throws SQLException;
    void checkSavings(int Customer_ID) throws SQLException;
    boolean eUserLogin(String username) throws SQLException;
    boolean ePassLogin(String password) throws SQLException;
    void addSavings(int Customer_ID,String first_name,int Balance) throws SQLException;
    void getCustomers() throws SQLException;
    void verifyCustomers(int Customer_ID) throws SQLException;
    void checkCAccount(int Customer_ID) throws SQLException;
    void checkSAccount(int Customer_ID) throws SQLException;
    void getUserPass(int Customer_ID) throws SQLException;
    void getidName() throws  SQLException;

}
