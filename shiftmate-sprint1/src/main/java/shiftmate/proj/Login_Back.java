package shiftmate.proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login_Back
{
    public static void connect() {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/seniorproject_test";
        String username = "shiftmate";
        String password = "shift_testpass1";
        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            // If the connection is successful
            System.out.println("Connected to the database.\n\n");
            // Do whatever you need with the connection 
            // Close the connection when done
            connection.close();
        } catch (SQLException e) {   
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }
}

