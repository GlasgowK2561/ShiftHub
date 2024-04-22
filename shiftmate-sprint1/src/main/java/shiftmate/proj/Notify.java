package shiftmate.proj;
//Imports
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Hashtable;
import java.util.LinkedList;
import java.nio.file.Path;
//REDO THIS TO TAKE A START DATE AND QUERY SPECIFICALLY FOR SHIFTS WITH THAT START DATE
public class Notify{
  // Helper method to calculate the duration of a shift in hours
  private static int calculateShiftHours(String startTime, String endTime) {
      // Assuming startTime and endTime are in "HH:mm:ss" format
      LocalTime start = LocalTime.parse(startTime);
      LocalTime end = LocalTime.parse(endTime);
      // Calculate the duration between start and end in hours
      long durationInMinutes = ChronoUnit.MINUTES.between(start, end);
      return (int) (durationInMinutes / 60); // Convert minutes to hours
  }
  //sends schedule notification email to entire department staff for given start date-- Written by: Elizabeth
  public static void sendDepEmail(int depID, String startDate) throws IOException{
    LinkedList<Hashtable<String,String>> employeeList = DBController.getDepartmentEmployeesWithEmail(depID);
    String depName = DBController.getDepartmentInformation(depID).getFirst().get("depName");
    System.out.println("Sending schedule emails for: " + depName);
    String monHours, tuesHours, wedHours, thursHours, friHours, satHours, sunHours = "N/A";
    int totalHours, expectedPay, shiftHours;
    totalHours = 0;
    for(Hashtable<String,String> employeeInfo: employeeList){ //get each employees info
      String eName = employeeInfo.get("eName");
      String eEmail = employeeInfo.get("email");
      int employeeID = Integer.parseInt(employeeInfo.get("employeeID"));
      monHours = tuesHours = wedHours = thursHours = friHours = satHours = sunHours = "N/A";
      System.out.println("Working on: " + eName);
      //get and format weekly schedule
      LinkedList<Hashtable<String,String>> schedule = DBController.getEmployeeScheduleWeekOf(depName, employeeID);
      if(schedule.peekFirst() != null){ //check that department schedule exists
        System.out.println("Found schedule. Retrieving data...");
        
        for(Hashtable<String,String> shift: schedule){ //format each day
          
          switch(shift.get("DayOfWeek")){
            case "Monday":
              monHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              shiftHours = calculateShiftHours(shift.get("StartTime"), shift.get("EndTime"));
              totalHours += shiftHours;
              break;
            case "Tuesday":
              tuesHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              shiftHours = calculateShiftHours(shift.get("StartTime"), shift.get("EndTime"));
              totalHours += shiftHours;
              break;
            case "Wednesday":
              wedHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              shiftHours = calculateShiftHours(shift.get("StartTime"), shift.get("EndTime"));
              totalHours += shiftHours;
              break;
            case "Thursday":
              thursHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              shiftHours = calculateShiftHours(shift.get("StartTime"), shift.get("EndTime"));
              totalHours += shiftHours;
              break;
            case "Friday":
              friHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              shiftHours = calculateShiftHours(shift.get("StartTime"), shift.get("EndTime"));
              totalHours += shiftHours;
              break;
            case "Saturday":
              satHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              shiftHours = calculateShiftHours(shift.get("StartTime"), shift.get("EndTime"));
              totalHours += shiftHours;
              break;
            case "Sunday":
              sunHours = shift.get("StartTime") + " - " + shift.get("EndTime");
              shiftHours = calculateShiftHours(shift.get("StartTime"), shift.get("EndTime"));
              totalHours += shiftHours;
              break;
            default:
          }
        }
      } else {
        System.out.println("empty schedule");
      }
      expectedPay = totalHours * 15;
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
        TOTAL HOURS: %d\n
        EXPECTED PAY: %d\n
        """, eName, startDate, monHours, tuesHours, wedHours, thursHours, friHours, satHours, sunHours, totalHours, expectedPay);
          
      sendEmail(eEmail, startDate, emailBody); //sends formatted email for this employee
      System.out.println("Email sent for: " + eName);
    }
  }

  //sends a single email -- Written by: Elizabeth
  private static void sendEmail(String emailTo, String subjectDate, String bodyText) throws IOException 
  { 
    System.out.println("SENDING EMAIL FUNCTION");
    String pythonScriptPath = "src/main/java/shiftmate/proj/notification_email.py"; // Relative path to Python script
    Path scriptPath = Paths.get(pythonScriptPath);
    String path = scriptPath.toString();
    String[] cmd = { //sends to python with command like args
      "python",
      path, //make sure relative path stays correct
      emailTo,
      subjectDate,
      bodyText
    };
    Runtime.getRuntime().exec(cmd);
  }
}