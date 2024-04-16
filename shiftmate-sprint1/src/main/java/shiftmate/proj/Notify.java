package shiftmate.proj;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

public class Notify{
  //public static String s;
  public static void sendDepEmail(int depID) throws IOException{
    LinkedList<Hashtable<String,String>> employeeList = DBController.getDepartmentEmployeesWithEmail(depID);
    String depName = DBController.getDepartmentInformation(depID).getFirst().get("depName");
    System.out.println("Sending schedule emails for: " + depName);
    String startDate = "N/A";
    String monHours, tuesHours, wedHours, thursHours, friHours, satHours, sunHours = "N/A";
    
    for(Hashtable<String,String> employeeInfo: employeeList){ //get each employees info
      String eName = employeeInfo.get("eName");
      String eEmail = employeeInfo.get("email");
      int employeeID = Integer.parseInt(employeeInfo.get("employeeID"));
      monHours = tuesHours = wedHours = thursHours = friHours = satHours = sunHours = "N/A";

      //get and format weekly schedule
      LinkedList<Hashtable<String,String>> schedule = DBController.getEmployeeWeeklySchedule(depName, employeeID);
      if(schedule != null){ //check that department schedule exists
        startDate = schedule.peekFirst().get("WeekStartDate");
        
        for(Hashtable<String,String> dayOfWeek: schedule){ //format each day
          
          switch(dayOfWeek.get("DayOfWeek")){
            case "Monday":
              monHours = dayOfWeek.get("StartTime") + " - " + dayOfWeek.get("EndTime");
              break;
            case "Tuesday":
              tuesHours = dayOfWeek.get("StartTime") + " - " + dayOfWeek.get("EndTime");
              break;
            case "Wednesday":
              wedHours = dayOfWeek.get("StartTime") + " - " + dayOfWeek.get("EndTime");
              break;
            case "Thursday":
              thursHours = dayOfWeek.get("StartTime") + " - " + dayOfWeek.get("EndTime");
              break;
            case "Friday":
              friHours = dayOfWeek.get("StartTime") + " - " + dayOfWeek.get("EndTime");
              break;
            case "Saturday":
              satHours = dayOfWeek.get("StartTime") + " - " + dayOfWeek.get("EndTime");
              break;
            case "Sunday":
              sunHours = dayOfWeek.get("StartTime") + " - " + dayOfWeek.get("EndTime");
              break;
            default:
          }
        }
      } else {
        System.out.println("empty schedule");
      }

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

  //sends a single email 
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
    
    //for debugging/ returning errors if necessary
    //Runtime r = Runtime.getRuntime();
    //Process p = r.exec(cmd);
    
    //System.out.println("printed?");
    //BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
    //while((s=in.readLine()) != null){
    //  System.out.println(s);
    //}
  }

    
  public static void main(String[] args) throws IOException{
    /*String schedule = """
        Monday: N/A
        Tuesday: 9:00AM - 5:00PM
        Wednesday: N/A
        """;
    sendEmail("elizabethnjaa@gmail.com","testDate", schedule); */
    //sendDepEmail(1);
  }
  

}