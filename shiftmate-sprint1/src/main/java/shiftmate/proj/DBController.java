/*
use test function as template to copy paste
returning queried data to a hashtable is next
 */

 package shiftmate.proj;

 import java.sql.*;
 import java.util.Dictionary;
 import java.util.Enumeration;
 import java.util.Hashtable;
 
 public class DBController {
     // Database connection parameters
         
         static Connection connection;
         static String url = "jdbc:mysql://localhost:3306/seniorproject_test";
         static String username = "shiftmate";
         static String password = "shift_testpass1";
 
     public static void test(){
             
         try {
             // Establishing a connection to the database
             Connection connection = DriverManager.getConnection(url, username, password);
             // If the connection is successful
             System.out.println("Connected to the database.\n\n");
             try {
                 Statement stmt = connection.createStatement() ;
                 try {
                     String query = "SHOW TABLES" ; //replace with query
                     ResultSet rs = stmt.executeQuery(query) ;
                     try {
                         while(rs.next()){ //iterates through returned data
                             int numColumns = rs.getMetaData().getColumnCount();
                             for(int i = 1; i <= numColumns; i++){
                                 System.out.println(rs.getObject(i));
                             
                             }
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
     } //end of class
     
     public static void main (String[] args){
         test();
         
     }
 }
 