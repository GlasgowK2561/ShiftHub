/*
use test function as template to copy paste
returning queried data to a hashtable is next
 */

 package shiftmate.proj;

 import java.sql.*;
 import java.util.*;
 import java.util.Hashtable;
 
 public class DBController {
    // Database connection parameters
         
    static Connection connection;
    static String url = "jdbc:mysql://dcm.uhcl.edu/sens24g2";
    static String username = "sens24g2";
    static String password = "Sce9902292!!";
 
    //takes a string query and returns a linked list of hastables where each row is a table of key value pairs 
    static LinkedList<Hashtable<String,String>> getQuery(String query){
        LinkedList<Hashtable<String,String>> list = new LinkedList<>();     
        
        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            // If the connection is successful
            System.out.println("Connected to the database.\n\n");
            try {
                Statement stmt = connection.createStatement() ;
                try {
                    //String query = "SHOW TABLES" ; //replace with query
                    ResultSet rs = stmt.executeQuery(query) ;
                    try {
                        /*while(rs.next()){ //iterates through returned data
                            int numColumns = rs.getMetaData().getColumnCount();
                            for(int i = 1; i <= numColumns; i++){
                                System.out.println(rs.getObject(i));
                             
                            }
                        } 
                        */
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
                    try { stmt.close(); } 
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
    
    public static LinkedList<Hashtable<String,String>> getEmployeeInfo(){
        return getQuery("SELECT * FROM employeeinfo");
    }


    public static void main (String[] args){
        LinkedList<Hashtable<String,String>> resultlist = getQuery("SHOW TABLES");
        System.out.println(resultlist);

        resultlist = getQuery("SELECT * FROM employeeinfo");//valid query
        System.out.println(resultlist);

        resultlist = getQuery("SELECT * FROM employeeinf");//invalid query
        System.out.println(resultlist);
        
        resultlist = getQuery("SELECT 1 WHERE false"); //empty result set test
        System.out.println(resultlist);

        resultlist = getEmployeeInfo();
        System.out.println(resultlist);
    }
 }
 