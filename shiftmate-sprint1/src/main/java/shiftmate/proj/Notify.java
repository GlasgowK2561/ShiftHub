package shiftmate.proj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Notify{
  //public static String s;
  public static void sendEmail(String emailTo, String subjectDate, String bodyText) throws IOException 
  { 
    String[] cmd = {
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

  /*  
  public static void main(String[] args) throws IOException{
    String schedule = """
        Monday: N/A
        Tuesday: 9:00AM - 5:00PM
        Wednesday: N/A
        """;
    sendEmail("elizabethnjaa@gmail.com","testDate", schedule);
  }
  */

}