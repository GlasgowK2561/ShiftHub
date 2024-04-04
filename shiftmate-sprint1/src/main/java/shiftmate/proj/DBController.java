/*
use test function as template to copy paste
returning queried data to a hashtable is next
 */

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
 
    /* 
    public static Boolean addEmployee(){
        
    }
    */

    public static LinkedList<Hashtable<String,String>> getEmployeeInformation(int employeeID){
        String params[] = {Integer.toString(employeeID)};
        return getParameterizedQuery("SELECT e.* FROM employeeinfo as e WHERE employeeid = ?;", 1, params);
    }
   
    /* 
    public static Boolean editEmployeeInformation(){
        
    }
    */
    

    public static LinkedList<Hashtable<String,String>> getEmployees(){
        String params[] = {};
        return getParameterizedQuery("SELECT e.*, d.depName FROM employeeinfo e INNER JOIN departments d ON e.deptid = d.depid", 0, params);
    }

    /* 
    public static Boolean deleteEmployee(){
        
    }
    */

    /* 
    public static Boolean addDepartment(){
        
    }
    */

    public static LinkedList<Hashtable<String,String>> getDepartmentInformation(int depID){
        String params[] = {Integer.toString(depID)};
        return getParameterizedQuery("SELECT d.* FROM departments as d WHERE depid = ?;", 1, params);
    }

    /* 
    public static Boolean editDepartmentInformation(){
        
    }
    */

    public static LinkedList<Hashtable<String,String>> getDepartments(){
        String params[] = {};
        return getParameterizedQuery("SELECT d.* FROM departments d;", 0, params);
    }

    /* 
    public static Boolean deleteDepartments(){
        
    }
    */

    public static LinkedList<Hashtable<String,String>> getDepartmentEmployees(int depID){
        String params[] = {Integer.toString(depID)};
        return getParameterizedQuery("SELECT CONCAT(fname, ' ', lname) AS eName, employeeID FROM employeeinfo WHERE depID = ?", 1, params);
    } 

    public static void main (String[] args){
        System.out.println(getDepartmentEmployees(1));

        
    }
 }
 