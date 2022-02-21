package com.revature;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class BankDOAimpl implements BankDOA{
   Connection connection;
   private int balance;
    public BankDOAimpl ()
    {
     this.connection = ConnectionFactory.getConnection();
    }
//Method that transfers money from their Savings into Checkings
 @Override
 public void transferToCheckings(int amount, int Customer_ID) throws SQLException {
  String sql ="update Savings set Balance_s = Balance_S - " + amount +" WHERE Customer_ID = "+ Customer_ID +";";
  String sql2 = "update Checkings set Balance = Balance + " + amount +" WHERE Customer_ID = "+ Customer_ID + ";";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
  preparedStatement.executeUpdate();
  preparedStatement1.executeUpdate();

  //Checking for negative balance
  String sql3 = "select Balance from Checkings WHERE Customer_ID = "+ Customer_ID + ";";
  Statement statement2 = connection.createStatement();
  ResultSet rs = statement2.executeQuery(sql3);
  rs.next();
  int balance = rs.getInt("Balance");
  if(balance < amount)
  {
   String sql1 = "update Checkings set Balance = Balance + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
   Statement statement3 = connection.createStatement();
   statement3.executeUpdate(sql1);
   System.out.println("Sorry your request couldn't be processed duce to Insufficient Funds ");
   System.exit(0);
  }
 }
//Method that transfers money from their Checkings into Savings
 @Override
 public void transferToSavings(int amount, int Customer_ID) throws SQLException {
     String sql = "update Checkings set Balance = Balance - " + amount +" WHERE Customer_ID = "+ Customer_ID + ";";
     String sql2 = "update Savings set Balance_s = Balance_s + " + amount + " WHERE Customer_ID = " + Customer_ID + ";";
     PreparedStatement preparedStatement = connection.prepareStatement(sql);
     PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
     preparedStatement.executeUpdate();
     preparedStatement1.executeUpdate();


     //Checks for negative balance
  String sql3 = "select Balance from Checkings WHERE Customer_ID = "+ Customer_ID + ";";
  Statement statement2 = connection.createStatement();
  ResultSet rs = statement2.executeQuery(sql3);
  rs.next();
  int balance = rs.getInt("Balance_s");
  if(balance < amount)
  {
   String sql1 = "update Checkings set Balance = Balance + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
   Statement statement3 = connection.createStatement();
   statement3.executeUpdate(sql1);
   System.out.println("Sorry your request couldn't be processed due to Insufficient Funds ");
   System.exit(0);
  }
 }
//Method that transfers money from their Checking account into another members Checking account
 @Override
 public void transferFromC(int amount, int Customer_ID,int member) throws SQLException {
  String sql = "update Checkings set Balance = Balance - " + amount + " WHERE Customer_ID = "+ Customer_ID;
  String sql4 = "update Checkings set Balance = Balance + " + amount + " WHERE Customer_ID = "+ member;
  Statement statement = connection.createStatement();
  Statement statement1 = connection.createStatement();
  statement.executeUpdate(sql);
  statement1.executeUpdate(sql4);


  //Checking for negative balance
  String sql2 = "select Balance from Checkings WHERE Customer_ID = "+ Customer_ID + ";";
  Statement statement2 = connection.createStatement();
  ResultSet rs = statement2.executeQuery(sql2);
  rs.next();
  int balance = rs.getInt("Balance");
  if(balance < amount)
  {
   String sql1 = "update Checkings set Balance = Balance + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
   Statement statement3 = connection.createStatement();
   statement3.executeUpdate(sql1);
   System.out.println("Sorry your request couldn't be processed due to Insufficient Funds ");
   System.exit(0);
  }

 }
//Method that transfers money from a customer Savings Account into another members Savings account
 @Override
 public void transferFromS(int amount, int Customer_ID,int member) throws SQLException {
  String sql = "update Savings set Balance_s = Balance_s - " + amount + " WHERE Customer_ID = "+ Customer_ID;
  String sql4 = "update Savings set Balance_s = Balance_s + " + amount + " WHERE Customer_ID = "+ member;
  Statement statement = connection.createStatement();
  Statement statement1 = connection.createStatement();
  statement.executeUpdate(sql);
  statement1.executeUpdate(sql4);


  //Checking for negative balance
  String sql2 = "select Balance_s from Savings WHERE Customer_ID = "+ Customer_ID + ";";
  Statement statement2 = connection.createStatement();
  ResultSet rs = statement2.executeQuery(sql2);
  rs.next();
  int balance = rs.getInt("Balance_s");
  if(balance < amount)
  {
   String sql1 = "update Savings set Balance_s = Balance_s + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
   Statement statement3 = connection.createStatement();
   statement3.executeUpdate(sql1);
   System.out.println("Sorry your request couldn't be processed due to Insufficient Funds ");
   System.exit(0);
  }
 }
//Withdraws from Checkings account
 @Override
    public void WithdrawalC(int amount, int Customer_ID) throws SQLException {
     String sql = "update Checkings set Balance = Balance - " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
     Statement statement = connection.createStatement();
     statement.executeUpdate(sql);


     //Checking for a negative balance
     String sql2 = "select Balance from Checkings WHERE Customer_ID = "+ Customer_ID + ";";
     Statement statement2 = connection.createStatement();
     ResultSet rs = statement2.executeQuery(sql2);
     rs.next();
     int balance = rs.getInt("Balance");
     if(balance < amount)
     {
      String sql1 = "update Checkings set Balance = Balance + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
      Statement statement3 = connection.createStatement();
      statement3.executeUpdate(sql1);
      System.out.println("Sorry your request couldn't be processed due to Insufficient Funds ");
      System.exit(0);
     }

    }
//Withdraws from Savings account
 @Override
 public void WithdrawalS(int amount, int Customer_ID) throws SQLException {
  String sql = "update Savings set Balance_s = Balance_s - " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
  Statement statement = connection.createStatement();
  statement.executeUpdate(sql);
  String sql2 = "select Balance_s from Savings WHERE Customer_ID = "+ Customer_ID + ";";
  Statement statement2 = connection.createStatement();
  ResultSet rs = statement2.executeQuery(sql2);
  rs.next();
  int balance = rs.getInt("Balance_s");
  if(balance < amount)
  {
   String sql1 = "update Savings set Balance_s = Balance_s + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
   Statement statement3 = connection.createStatement();
   statement3.executeUpdate(sql1);
   System.out.println("Sorry your request couldn't be processed due to Insufficient Funds ");
   System.exit(0);
  }

 }
//Deposits money into a customers Savings account
 @Override
 public void DepositS(int amount, int Customer_ID) throws SQLException {
  String sql = "update Savings set Balance_s = Balance_s + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  preparedStatement.executeUpdate();
 }

//Deposits money into a customers Checking account
 @Override
 public void Deposit(int amount, int Customer_ID) throws SQLException {
  String sql = "update Checkings set Balance = Balance + " + amount +" WHERE Customer_ID = " + Customer_ID + ";";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  preparedStatement.executeUpdate();

 }
//Made another method that does the samething by accident
 @Override
 public void DepositC(int Customer_ID, int amount) throws SQLException {
  String sql ="update Checkings set Balance = "+ amount +" where Customer_ID = " + Customer_ID +";";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  preparedStatement.executeUpdate();
 }

//Creates a new customer in the Banking table along with all of their personal information
 @Override
    public void addnCustomer(nCustomer customer) throws SQLException {

    String sql = "insert into Banking(Customer_ID,last_name,phone_number,email,city,state,Verify) values (?,?,?,?,?,?,?);";
     PreparedStatement preparedStatement  = connection.prepareStatement(sql);
     preparedStatement.setInt(1,customer.getId());
     preparedStatement.setString(2, customer.getLast());
     preparedStatement.setString(3,customer.getNumber());
     preparedStatement.setString(4,customer.getEmail());
     preparedStatement.setString(5, customer.getCity());
     preparedStatement.setString(6,customer.getState());
     preparedStatement.setString(7,customer.getVerify());
     preparedStatement.executeUpdate();

     }
//after account gets created it places the amount of money along with your id number and name into the checking database
 @Override
 public void addChecking(int Customer_ID, String first_name, int Balance) throws SQLException {
  String sql = "insert into Checkings(Customer_ID,first_name,Balance) values (?,?,?);";
  String sql1 = "update Checkings set first_name = '" +first_name+ "'" + "where Customer_ID = " + Customer_ID+";";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
  preparedStatement.setInt(1,Customer_ID);
  preparedStatement.setString(2,first_name);
  preparedStatement.setInt(3,Balance);
  preparedStatement.executeUpdate();
  preparedStatement1.executeUpdate();

 }

//When an account is created this method inserts the name of the customer into the Checkings table along with the Customers ID
 @Override
 public void addChecking(int Customer_ID, String first_name) throws SQLException {
  String sql ="insert into Checkings(Customer_ID,first_name) values ("+Customer_ID+"," +"'"+first_name+"');";
  String sql2 ="update Banking set first_name = "+"'"+first_name+"'"+" where Customer_ID = "+Customer_ID+";";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
  preparedStatement.executeUpdate();
  preparedStatement1.executeUpdate();
 }
//When a new customer is created this method inserts their Username and Password into a Login table
 @Override
 public void Login(int Customer_ID, String username, String password) throws SQLException {
  String sql = "insert into Login(Customer_ID,username,password) values (?,?,?);";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  preparedStatement.setInt(1,Customer_ID);
  preparedStatement.setString(2,username);
  preparedStatement.setString(3,password);
  preparedStatement.executeUpdate();
 }
//This method checks if the customers username is correct and returns a boolean value
 @Override
 public boolean checkUserLogin(int Customer_ID, String username) throws SQLException {
  String sql = "select username from Login WHERE Customer_ID = " + Customer_ID;
  Statement statement = connection.createStatement();
  ResultSet rs = statement.executeQuery(sql);
  rs.next();
  String user = rs.getString("username");

  return user.equals(username);
  }
//This method check the customers password and returns a boolean
 @Override
 public boolean checkPassLogin(int Customer_ID, String password) throws SQLException {
  String sql1 = "select password from Login WHERE Customer_ID = " + Customer_ID;
  Statement statement = connection.createStatement();
  ResultSet rs = statement.executeQuery(sql1);
  rs.next();
  String pass = rs.getString("password");
  return password.equals(pass);
 }
//This method checks the Customer_ID and returns a boolean
 @Override
 public boolean checkCustId(int Customer_ID) throws SQLException {
     String sql = "select Customer_ID from Login WHERE Customer_ID = " + Customer_ID + ";";
     Statement statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery(sql);
     resultSet.next();
     int id = resultSet.getInt("Customer_ID");
  if(id == Customer_ID)
  {
   return true;
  }
  else
  {
   System.out.println("Wrong customer ID");
   return false;
  }
 }
//This method allows the customer to check the balance in their Checkings account
 @Override
 public void checkCheckings(int Customer_ID) throws SQLException {
  String sql = "select * from Checkings WHERE Customer_ID = " + Customer_ID+";";
  Statement statement = connection.createStatement();
  ResultSet rs = statement.executeQuery(sql);
  while(rs.next())
  {
   int balance = rs.getInt("Balance");
   System.out.println("You have " + balance + " dollars in your Checkings Account");
  }

 }
//This method allows the customer to check the balance in their Savings account
 @Override
 public void checkSavings(int Customer_ID) throws SQLException {
String sql = "select * from Savings WHERE Customer_ID = " + Customer_ID + ";";
Statement statement = connection.createStatement();
ResultSet rs = statement.executeQuery(sql);
while(rs.next())
{
 int balance = rs.getInt("Balance_s");
 System.out.println("You have " + balance + " dollars in your Savings account");
}
 }

//This method checks the username for the Employee
 @Override
 public boolean eUserLogin(String username) throws SQLException {
  String sql = "select employee_username from Employee ";
  Statement statement = connection.createStatement();
  ResultSet rs = statement.executeQuery(sql);
  rs.next();
  String user = rs.getString("employee_username");
  return user.equals(username);
 }
//This methods check the password of the Employee
 @Override
 public boolean ePassLogin(String password) throws SQLException {
  String sql = "select employee_password from Employee";
  Statement statement = connection.createStatement();
  ResultSet rs = statement.executeQuery(sql);
  rs.next();
  String user = rs.getString("employee_password");
  return user.equals(password);
 }
//When the customer creates an account it also automatically creates a savings account
 @Override
 public void addSavings(int Customer_ID, String first_name,int Balance) throws SQLException {
  String sql = "insert into Savings(Customer_ID,first_name,Balance_s) values (?,?,?);";
  String sql1 = "update Banking set first_name = '" +first_name+ "'" + "where Customer_ID = " + Customer_ID+";";
  PreparedStatement preparedStatement = connection.prepareStatement(sql);
  PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
  preparedStatement.setInt(1,Customer_ID);
  preparedStatement.setString(2,first_name);
  preparedStatement.setInt(3,Balance);
  preparedStatement.executeUpdate();
  preparedStatement1.executeUpdate();
 }
//Used for the employee when they want to see all personal information of the customers
 @Override
 public void getCustomers() throws SQLException {
  String sql = "select * from Banking";
  Statement statement = connection.createStatement();
  ResultSet resultSet = statement.executeQuery(sql);
  while (resultSet.next()) {
   int id = resultSet.getInt("Customer_ID");
   String first = resultSet.getString("first_name");
   String last = resultSet.getString("last_name");
   String phone_number = resultSet.getString("phone_number");
   String email = resultSet.getString("email");
   String city = resultSet.getString("city");
   String state = resultSet.getString("state");
   String verify = resultSet.getString("Verify");
   System.out.println("Customer_ID : " + id +
           " First Name: " + first +
           " Last Name: " + last +
           " Phone Number : " + phone_number
           + " Email: " + email +
           " City: " + city +
           " State: " + state +
           " Verified Status: " + verify);
  }
 }
//Employee uses this method to verify a customer
 @Override
 public void verifyCustomers(int Customer_ID) throws SQLException {
     String sql = "update Banking set Verify = 'Verified' WHERE Customer_ID = "+ Customer_ID + "; ";
     String sql2 = "select * from Banking WHERE Customer_ID = " + Customer_ID + ";";
     PreparedStatement statement = connection.prepareStatement(sql);
     Statement statement1 = connection.createStatement();
     ResultSet rs = statement1.executeQuery(sql2);
  while(rs.next())
  {
   String name = rs.getString("first_name");
   System.out.println(name + " is now a Verified customer");
  }
     statement.executeUpdate();


 }
//Employee uses this to see into Customers checking accounts
 @Override
 public void checkCAccount(int Customer_ID) throws SQLException {
    String sql = "select * from Checkings WHERE Customer_ID = "+ Customer_ID + ";";
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery(sql);
    while(rs.next())
    {
     String name  = rs.getString("first_name");
     int balance = rs.getInt("Balance");
     System.out.println(name + " has a balance of " + balance + " dollars in their Checkings Account");
    }
 }
//Employee uses this method to look into Customers Savings account
 @Override
 public void checkSAccount(int Customer_ID) throws SQLException {
    String sql = "select * from Savings WHERE Customer_ID = " + Customer_ID +";";
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery(sql);
    while(rs.next())
    {
     String name = rs.getString("first_name");
     int balance = rs.getInt("Balance");
     System.out.println(name + " has a balance of " + balance + " dollars in their Savings Account");
    }
 }
//Employee uses this method to look at the usernames and passwords of certain customers
 @Override
 public void getUserPass(int Customer_ID) throws SQLException {
  String sql = "select * from Login WHERE Customer_ID = " +Customer_ID+ ";";
  Statement statement = connection.createStatement();
  ResultSet rs = statement.executeQuery(sql);
  while(rs.next())
  {
   String username = rs.getString("username");
   String password = rs.getString("password");
   System.out.println("Username: " + username + "Password: " + password);
  }
 }
//Retrieves the Customer ID and the first name of customers
 @Override
 public void getidName() throws SQLException {
  String sql = "select * from Banking ";
  Statement statement = connection.createStatement();
  ResultSet rs = statement.executeQuery(sql);
  while(rs.next())
  {
   int ID = rs.getInt("Customer_ID");
   String name = rs.getString("first_name");
   System.out.println("Customer ID Number: " + ID +" Customer Name: " + name);
  }
 }
}
