package shiftmate.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database_Communication {
    public static boolean login(String username, String password) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/seniorproject_test";
        String dbUsername = "shiftmate";
        String dbPassword = "shift_testpass1";

        // SQL query to check if the provided username and password match
        String query = "SELECT * FROM login WHERE username = ? AND pass = ?";
        
        try (
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            // Creating a prepared statement for the query
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Setting parameters for the query (username and password)
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            // Executing the query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Checking if there is any result
            if (resultSet.next()) {
                // If the result set is not empty, login is successful
                System.out.println("Login Successful");
                connection.close();
                return(true);
            } else {
                // If the result set is empty, no matching username and password found
                System.out.println("Invalid Username/Password");
                connection.close();
                return(false);
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            return(false);
        }
    }
}


