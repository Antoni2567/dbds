package com.revature;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;
import java.util.*;

public class Banking {
    public static void main(String[] args) throws SQLException {
          BankDOA doa = BankingDOAFactory.getBankingDOA();
          Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the bank of Revature");
        System.out.println("Please select whether you are an Employee or a Customer \n Type E:Employee or C:Customer");
        String ec = input.next();
        if(ec.equalsIgnoreCase("c"))
        {
            System.out.println("Are you a new customer or an existing customer \n N: New & E: Existing");
            String ce = input.next();
            if(ce.equalsIgnoreCase("n"))
            {
                System.out.println("Please enter your First name");
                String first = input.next();
                System.out.println("Please enter your Last name");
                String last = input.next();
                System.out.println("Please enter your phone number");
                String number = input.next();
                System.out.println("Please enter your email");
                String email = input.next();
                System.out.println("Please enter the city that you are from");
                String city = input.next();
                System.out.println("Please enter the state you are from");
                String state = input.next();
                System.out.println("Enter a unique 6 digit Customer ID");
                int ID = input.nextInt();
                System.out.println("Create a username");
                String username = input.next();
                System.out.println("Create a password");
                String password = input.next();
                nCustomer newC = new nCustomer(ID,first,last,number,email,city,state,"Not Verified");

                System.out.println(" We require a minimum deposit balance of at least 50$ or more,\n" +
                        " How much would you like to deposit ?");
                int newBalance = input.nextInt();
                while(newBalance < 50)
                {
                    System.out.println("Try again has to be at least 50$");
                    newBalance = input.nextInt();
                }
                System.out.println("Which account did you want to deposit your money in? \n" +
                        "C:Checking or S:Savings account");
                String cs = input.next();
                if (cs.equalsIgnoreCase("C"))
                {
                    doa.addnCustomer(newC);
                    doa.Login(ID,username,password);
                    doa.addChecking(ID,first);
                    doa.DepositC(ID,newBalance);
                    doa.addSavings(ID,first,0);
                    System.out.println("Your account has been successfully created and " + newBalance + "has been deposited");
                }
                else
                {
                   doa.addnCustomer(newC);
                    doa.Login(ID,username,password);
                   doa.addChecking(ID,first,0);
                   doa.addSavings(ID,first,newBalance);
                    System.out.println("Your account has been successfully created and " + newBalance + "has been deposited");

                }


            }

            else
            {

                System.out.println("Please Login\n" + "Enter your username");
                String log = input.next();
                System.out.println("Please enter your password");
                String in = input.next();
                System.out.println("Enter your unique 6 digit Customer ID ");
                int cus = input.nextInt();
                if(doa.checkUserLogin(cus,log) && doa.checkPassLogin(cus,in) && doa.checkCustId(cus)) {
                    System.out.println("Login Successful");
                    System.out.println("Welcome back would you like to make a Withdrawal, Transfer, Deposit or View an account\n" + "Type W: Withdrawal Type T: Transfer Type D: Deposit Type V:View an account ");
                    String wd = input.next();
                    if (wd.equalsIgnoreCase("W")) {
                        System.out.println("Which account would you like to make a Withdraw from?? C:Checking or S:Savings");
                        String account = input.next();
                        if (account.equalsIgnoreCase("C")) {
                            System.out.println("How much did you want to withdrawal ");
                            int with = input.nextInt();
                            System.out.println("Please enter your unique 6 digit Customer ID");
                            int drawal = input.nextInt();
                            if(doa.checkCustId(drawal)) {
                                doa.WithdrawalC(with, drawal);
                                System.out.println("Your Withdraw of " + with + " from your Savings account was successfully completed ");
                            }
                            else {
                                System.out.println("Incorrect Customer ID");
                            }

                        } else {
                            System.out.println("How much did you want to withdrawal ");
                            int with = input.nextInt();
                            System.out.println("Please enter your unique 6 digit Customer ID");
                            int drawal = input.nextInt();
                            if(doa.checkCustId(drawal)) {
                                doa.WithdrawalS(with, drawal);
                                System.out.println("Your Withdraw of " + with + " from your Savings account was successfully completed ");
                            }
                            else
                            {
                                System.out.println("Incorrect Customer ID");
                            }

                        }
                    } else if (wd.equalsIgnoreCase("D")) {
                        System.out.println("How much did you want to deposit");
                        int amount = input.nextInt();
                        System.out.println("Which account would you like to use? \n"
                                + "Type:C for Checking & S:for Savings");
                        String cOS = input.next();
                        if (cOS.equals("C")) {
                            System.out.println("You have selected Checkings");
                            System.out.println("Please enter your unique 6 digit Customer_ID");
                            int ID = input.nextInt();
                            doa.Deposit(amount, ID);
                            System.out.println("You have successfully deposited " + amount + " into your Checkings account");
                        } else {
                            System.out.println("You have selected Savings");
                            System.out.println("Please enter your unique Customer_ID");
                            int ID = input.nextInt();
                            doa.DepositS(amount, ID);
                            System.out.println("You have successfully deposited " + amount + " into your Savings account");
                        }


                    } else if (wd.equals("T")) {
                        System.out.println("Please enter the amount you would like to Transfer");
                        int amount = input.nextInt();
                        System.out.println("Which account would you like to make a Transfer to, Please select an option \n" +
                                "1: Checkings to Savings \n" +
                                "2: Savings to Checkings \n" +
                                "3: Savings to another member \n" +
                                "4: Checkings to another member");
                        int whichA = input.nextInt();
                        System.out.println("One more question can you please enter your unique 6 digit Customer ID");
                        int ID = input.nextInt();
                        switch (whichA) {
                            case 1:
                                doa.transferToSavings(amount, ID);
                                break;
                            case 2:
                                doa.transferToCheckings(amount, ID);
                                break;
                            case 3:
                                System.out.println("Whats the 6 digit Customer ID of the member you want to transfer the money to?");
                                int idC = input.nextInt();
                                doa.transferFromS(amount, ID, idC);
                                System.out.println("Your transfer of " + amount + " was successfully completed");
                                break;
                            case 4:
                                System.out.println("Whats the Customer ID of the member you want to transfer the money to?");
                                int idS = input.nextInt();
                                doa.transferFromC(amount, ID, idS);
                                System.out.println("Your transfer of " + amount + " was successfully completed");
                                break;
                        }

                    }
                    else if(wd.equalsIgnoreCase("V"))
                    {
                        System.out.println("Would you like to look at your Checkings or Savings \nType C:Checkings Type S:Savings");
                        String account = input.next();
                        if(account.equalsIgnoreCase("C")) {
                            System.out.println("What is your Customer ID number");
                            int id = input.nextInt();
                            if(doa.checkCustId(id))
                            {
                                doa.checkCheckings(id);
                            }
                        }
                        else
                        {
                            System.out.println("What is your Customer ID number");
                            int id = input.nextInt();
                            if (doa.checkCustId(id))
                            {
                                doa.checkSavings(id);
                            }

                        }

                    }




                }
                else
                {
                    System.out.println("Wrong password, username or ID");
                }

            }


            }
        else
        {
            System.out.println("Please enter your employee username");
            String employee = input.next();
            System.out.println("Please enter your employee password");
            String password = input.next();

            if(doa.eUserLogin(employee) && doa.ePassLogin(password))
            {
                System.out.println("Login Successful");
                System.out.println("select an option\n1:View all Customer personal info 2:Verify Customers 3:View Customer Checking account 4:View Customer Savings account 5:View Customer Credentials");
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        doa.getCustomers();
                        break;
                    case 2:
                        doa.getidName();
                        System.out.println("By ID which customer would you like to Verify");
                        int ID = input.nextInt();
                        doa.verifyCustomers(ID);
                        break;
                    case 3:
                        doa.getidName();
                        System.out.println("By ID which customer Checking account would you like to view?");
                        int ID2 = input.nextInt();
                        doa.checkCAccount(ID2);
                        break;
                    case 4:
                        doa.getidName();
                        System.out.println("By ID which customer Savings account would you like to view?");
                        int ID3 = input.nextInt();
                        doa.checkSAccount(ID3);
                        break;
                    case 5:
                        doa.getidName();
                        System.out.println("By ID which Customer credentials would you like to view?");
                        int ID4 = input.nextInt();
                        doa.getUserPass(ID4);
                }

            }
            else
            {
                System.out.println("Wrong employee username or password");
            }
        }


    }


}
