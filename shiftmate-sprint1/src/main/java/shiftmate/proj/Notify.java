package shiftmate.proj;
//Imports
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

//REDO THIS TO TAKE A START DATE AND QUERY SPECIFICALLY FOR SHIFTS WITH THAT START DATE


public class Notify{

  //sends schedule notification email to entire department staff for given start date-- Written by: Elizabeth
  public static void sendDepEmail(int depID, String startDate) throws IOException{
    LinkedList<Hashtable<String,String>> employeeList = DBController.getDepartmentEmployeesWithEmail(depID);
    String depName = DBController.getDepartmentInformation(depID).getFirst().get("depName");
    System.out.println("Sending schedule emails for: " + depName);
    String monHours, tuesHours, wedHours, thursHours, friHours, satHours, sunHours = "N/A";
    
    for(Hashtable<String,String> employeeInfo: employeeList){ //get each employees info
      String eName = employeeInfo.get("eName");
      String eEmail = employeeInfo.get("email");
      int employeeID = Integer.parseInt(employeeInfo.get("employeeID"));
      monHours = tuesHours = wedHours = thursHours = friHours = satHours = sunHours = "N/A";
      System.out.println("Working on: " + eName);

      //get and format weekly schedule
      LinkedList<Hashtable<String,String>> schedule = DBController.getEmployeeScheduleWeekOf(depName, employeeID, startDate);
      if(schedule.peekFirst() != null){ //check that department schedule exists
        System.out.println("Found schedule. Retrieving data...");
        
        for(Hashtable<String,String> shift: schedule){ //format each day
          
          switch(shift.get("DayOfWeek")){
            case "Monday":
              monHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              break;
            case "Tuesday":
              tuesHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              break;
            case "Wednesday":
              wedHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              break;
            case "Thursday":
              thursHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              break;
            case "Friday":
              friHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              break;
            case "Saturday":
              satHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              break;
            case "Sunday":
              sunHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              break;
            default:
          }
        }
      } else {
        System.out.println("empty schedule");
      }

      System.out.println("Formatting...");
      //formats schedule into email body
      String emailBody = String.format("""
        %s's schedule for week starting on %s\n
        Monday: %s\n
        Tuesday: %s\n
        Wednesday: %s\n
        Thursday: %s\n
        Friday: %s\n
        Saturday: %s\n
        Sunday: %s\n
        """, eName, startDate, monHours, tuesHours, wedHours, thursHours, friHours, satHours, sunHours);
          
      sendEmail(eEmail, startDate, emailBody); //sends formatted email for this employee
      System.out.println("Email sent for: " + eName);
    }
  }

  //sends a single email -- Written by: Elizabeth
  private static void sendEmail(String emailTo, String subjectDate, String bodyText) throws IOException 
  { 
    String[] cmd = { //sends to python with command like args
      "python",
      "shiftmate-sprint1/src/main/java/shiftmate/proj/notification_email.py", //make sure relative path stays correct
      emailTo,
      subjectDate,
      bodyText
    };
    Runtime.getRuntime().exec(cmd);
  }
}