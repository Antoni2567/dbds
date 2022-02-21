package com.revature;
import java.util.Scanner;
import java.sql.*;
import javax.swing.plaf.nimbus.State;
/*public class Main {

    public static void main(String[] args) throws ClassNotFoundException,SQLException{
        // Step 1: Load the Driver
        //Class.forName("jdbc:mysql.jdbc.Driver");

        // Step 2: Create a connection
        /*
        String url = "jdbc:mysql://localhost:3306/Bank";
        String username = "root";
        String password = "Tony2567!";
        Connection connection = DriverManager.getConnection(url, username, password);



        // Step 3: Create Statement Object
        Statement statement = connection.createStatement();



        // Step 4: Execute the Query
        String sql = "Select * from employee";
       /* ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println("Id: "+ resultSet.getInt(1)+ ", Name: "+
                    resultSet.getString(2)+ ", Email: "+ resultSet.getString(3));
        }
        String query = "insert into employee(name,email) values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,"Hunxho");
        preparedStatement.setString(2,"Streetpoet@freshmail.com");

        int rowAffected = preparedStatement.executeUpdate();
        System.out.println("("+rowAffected+") rows affected");

        // Step 5: Close the connection
        connection.close();

    }
}
*/