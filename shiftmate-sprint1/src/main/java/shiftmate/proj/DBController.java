package shiftmate.proj;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.LinkedList;
 
 public class DBController {
    // Database connection parameters
         
    static Connection connection;
    static String url = "jdbc:mysql://dcm.uhcl.edu/sens24g2";
    static String username = "sens24g2";
    static String password = "Sce9902292!!";
 
    //takes a string query and returns a linked list of hastables where each row is a table of key value pairs 
    static LinkedList<Hashtable<String,String>> getParameterizedQuery(String query, int numParams, String [] param){
        LinkedList<Hashtable<String,String>> list = new LinkedList<>();     

        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            // If the connection is successful
            System.out.println("Connected to the database.\n\n");
            try {
                PreparedStatement prepstmt = connection.prepareStatement(query);
                try {
                    //fill in each parameter
                    for(int i = 1; i<= numParams; i++){
                        prepstmt.setString(numParams, param[i-1]);
                    }                   
                    
                    ResultSet rs = prepstmt.executeQuery() ;
                    try {
                        
                        while(rs.next()){
                            Hashtable<String, String> currentRowMap = new Hashtable<>();
                        
                            ResultSetMetaData rsmd = rs.getMetaData(); //gets column name
                            int columnCount = rsmd.getColumnCount();
                            for (int i = 1; i <= columnCount; i++) {
                                // retrieves column name and value.
                                String key = rsmd.getColumnLabel(i); //key is column name
                                String value = rs.getString(rsmd.getColumnName(i)); //value is column value
                                if (value == null) {
                                    value = "null";
                                }
                                // builds map.
                                currentRowMap.put(key, value);
                            }
                            list.add(currentRowMap);
                        }
                    } finally {
                        try { rs.close(); } 
                        catch (Throwable ignore) { 
                        // Propagate the original exception instead of this one that you may want just logged  
                        }
                    }
                    

                } finally {
                    try { prepstmt.close(); } 
                    catch (Throwable ignore) { 
                        // Propagate the original exception instead of this one that you may want just logged  
                    }
                }
            } finally {
                //It's important to close the connection when you are done with it
                try { connection.close(); } 
                catch (Throwable ignore) { 
                    // Propagate the original exception instead of this one that you may want just logged  
                }
            }
        } catch (SQLException e) {   
            // Handle any SQL exceptions
            e.printStackTrace();
        }
        return list;
    } //end of class
    
    public static boolean addEmployee(String fname, String lname, String phone, String email, String startDate, int deptID, String contact, String contactPhone) {
        String query = "INSERT INTO employeeinfo (fname, lname, phone, email, startDate, deptID, contact, contactPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try (PreparedStatement prepstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);
                prepstmt.setString(1, fname);
                prepstmt.setString(2, lname);
                prepstmt.setString(3, phone);
                prepstmt.setString(4, email);
                prepstmt.setString(5, startDate);
                prepstmt.setInt(6, deptID);
                prepstmt.setString(7, contact);
                prepstmt.setString(8, contactPhone);
    
                int rowsAffected = prepstmt.executeUpdate();
                connection.commit();
                if (rowsAffected > 0) {
                    System.out.println("Employee added successfully.");
                    return true;
                } else {
                    System.out.println("Failed to add employee.");
                    return false;
                }
            }
        } catch (SQLException e) {
            try{
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {}
            e.printStackTrace();
            return false;
        }
    }
    
    public static LinkedList<Hashtable<String,String>> getEmployeeInformation(int employeeID){
        String params[] = {Integer.toString(employeeID)};
        return getParameterizedQuery("SELECT e.* FROM employeeinfo as e WHERE employeeid = ?;", 1, params);
    }
   
    public static boolean editEmployeeInformation(int employeeID, String fname, String lname, int deptID, String phone, String email, String contact, String contactPhone) {
        String query = "UPDATE employeeinfo SET fname = ?, lname = ?, deptID = ?, phone = ?, email = ?, contact = ?, contactPhone = ? WHERE employee_id = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try (PreparedStatement prepstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);
                prepstmt.setString(1, fname);
                prepstmt.setString(2, lname);
                prepstmt.setInt(3, deptID);
                prepstmt.setString(4, phone);
                prepstmt.setString(5, email);
                prepstmt.setString(6, contact);
                prepstmt.setString(7, contactPhone);
                prepstmt.setInt(8, employeeID);
    
                int rowsAffected = prepstmt.executeUpdate();
                connection.commit();
                if (rowsAffected > 0) {
                    System.out.println("Employee information updated successfully.");
                    return true;
                } else {
                    System.out.println("Employee with ID " + employeeID + " not found.");
                    return false;
                }
            }
        } catch (SQLException e) {
            try{
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {}
            e.printStackTrace();
            return false;
        }
    }    

    public static LinkedList<Hashtable<String,String>> getEmployees(){
        String params[] = {};
        return getParameterizedQuery("SELECT e.*, d.depName FROM employeeinfo e INNER JOIN departments d ON e.deptid = d.depid", 0, params);
    }

    public static boolean deleteEmployee(int employeeID) {
        String query = "DELETE FROM employeeinfo WHERE employeeID = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try (PreparedStatement prepstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);
                prepstmt.setInt(1, employeeID);
    
                int rowsAffected = prepstmt.executeUpdate();
                connection.commit();
                if (rowsAffected > 0) {
                    System.out.println("Employee deleted successfully.");
                    return true;
                } else {
                    System.out.println("Employee with ID " + employeeID + " not found.");
                    return false;
                }
            }
        } catch (SQLException e) {
            try{
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {}
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addDepartment(String depName, String depManager) {
        String query = "INSERT INTO departments (depName, depManager) VALUES (?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try (PreparedStatement prepstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);
                prepstmt.setString(1, depName);
                prepstmt.setString(2, depManager);
    
                int rowsAffected = prepstmt.executeUpdate();
                connection.commit();
                if (rowsAffected > 0) {
                    System.out.println("Department added successfully.");
                    // Should we call createDefaultDepartmentShifts?
                    return true;
                } else {
                    System.out.println("Failed to add department.");
                    return false;
                }
            }
        } catch (SQLException e) {
            try{
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {}
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createDefaultDepartmentShifts(String depName) {
        String tableName = "DefaultShifts_" + depName.replace(" ", "_"); // Replace spaces with underscores
        String query = "CREATE TABLE " + tableName + " ( " +
                        "ShiftID INT PRIMARY KEY, " +
                        "DepID INT, " +
                        "DayOfWeek VARCHAR(255), " +
                        "StartTime TIME, " +
                        "EndTime TIME, " +
                        "FOREIGN KEY (DepID) REFERENCES Departments(depID) " +
                        ")";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
                System.out.println("Table " + tableName + " created successfully.");
                return true;
            }
        } catch (SQLException e) {
            try{
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {}
            e.printStackTrace();
            return false;
        }
    }
        

    public static LinkedList<Hashtable<String,String>> getDepartmentInformation(int depID){
        String params[] = {Integer.toString(depID)};
        return getParameterizedQuery("SELECT d.* FROM departments as d WHERE depid = ?;", 1, params);
    }

    public static boolean editDepartmentInformation(int depID, String depName, String depManager) {
        String query = "UPDATE departments SET depName = ?, depManager = ? WHERE depid = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try (PreparedStatement prepstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);
                prepstmt.setString(1, depName);
                prepstmt.setString(2, depManager);
                prepstmt.setInt(3, depID);
    
                int rowsAffected = prepstmt.executeUpdate();
                connection.commit();
                if (rowsAffected > 0) {
                    System.out.println("Department information updated successfully.");
                    return true;
                } else {
                    System.out.println("Department with ID " + depID + " not found.");
                    return false;
                }
            }
        } catch (SQLException e) {
            try{
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {}
            e.printStackTrace();
            return false;
        }
    }    

    public static LinkedList<Hashtable<String,String>> getDepartments(){
        String params[] = {};
        return getParameterizedQuery("SELECT d.* FROM departments d;", 0, params);
    }

    public static boolean deleteDepartment(int depID) {
        String query = "DELETE FROM departments WHERE depID = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try (PreparedStatement prepstmt = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);
                prepstmt.setInt(1, depID);

                int rowsAffected = prepstmt.executeUpdate();
                connection.commit();
                if (rowsAffected > 0) {
                    System.out.println("Department deleted successfully.");
                    return true;
                } else {
                    System.out.println("Department with ID " + depID + " not found.");
                    return false;
                }
            }
        } catch (SQLException e) {
            try{
                System.err.println("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {}
            e.printStackTrace();
            return false;
        }
    }    

    public static LinkedList<Hashtable<String,String>> getDepartmentEmployees(int depID){
        String params[] = {Integer.toString(depID)};
        return getParameterizedQuery("SELECT CONCAT(fname, ' ', lname) AS eName, employeeID FROM employeeinfo WHERE depID = ?", 1, params);
    } 

    public static void main (String[] args){
        System.out.println(getDepartmentEmployees(1));
    }
 }