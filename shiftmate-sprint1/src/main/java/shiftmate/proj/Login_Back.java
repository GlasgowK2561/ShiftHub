package shiftmate.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Login_Back{
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
    public static List<String[]> getEmployees() {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/seniorproject_test";
        String dbUsername = "shiftmate";
        String dbPassword = "shift_testpass1";

        // SQL query to retrieve all employees' information
        String query = "SELECT * FROM employeeInfo";

        try (
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            // Creating a prepared statement for the query
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Executing the query
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Create a list to store arrays representing each row of the result set
            List<String[]> employees = new ArrayList<>();

            // Iterating over the result set
            while (resultSet.next()) {
                // Retrieve data from each column
                int employeeID = resultSet.getInt("employeeID");
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String startDate = resultSet.getString("startDate");
                String contact = resultSet.getString("contact");
                String contactPhone = resultSet.getString("contactPhone");

                // Create an array for the current row
                String[] rowArray = {String.valueOf(employeeID), fName, lName, email, phone, startDate, contact, contactPhone};
                
                // Add the array representing the current row to the list
                employees.add(rowArray);
                // Print the current employee information
                System.out.println("Employee ID: " + employeeID);
                System.out.println("First Name: " + fName);
                System.out.println("Last Name: " + lName);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Start Date: " + startDate);
                System.out.println("Contact: " + contact);
                System.out.println("Contact Phone: " + contactPhone);
                System.out.println("----------------------");
            }
            return employees;
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            return null;
        }
    }
}

