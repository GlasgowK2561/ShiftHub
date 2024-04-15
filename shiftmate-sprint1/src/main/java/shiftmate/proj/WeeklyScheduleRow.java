package shiftmate.proj;

public class WeeklyScheduleRow {
    private String mondayShift;
    private String tuesdayShift;
    private String wednesdayShift;
    private String thursdayShift;
    private String fridayShift;
    private String saturdayShift;
    private String sundayShift;

public WeeklyScheduleRow(String mondayShift, String tuesdayShift, String wednesdayShift, String thursdayShift, String fridayShift, String saturdayShift, String sundayShift) {
    this.mondayShift = mondayShift;
    this.tuesdayShift = tuesdayShift;
    this.wednesdayShift = wednesdayShift;
    this.thursdayShift = thursdayShift;
    this.fridayShift = fridayShift;
    this.saturdayShift = saturdayShift;
    this.sundayShift = sundayShift;
}
public void setMondayShift(String str){
    this.mondayShift = str;
}
public void setTuesdayShift(String str){
    this.tuesdayShift = str;
}
public void setWednesdayShift(String str){
    this.wednesdayShift = str;
}
public void setThursdayShift(String str){
    this.thursdayShift = str;
}
public void setFridayShift(String str){
    this.fridayShift = str;
}
public void setSaturdayShift(String str){
    this.saturdayShift = str;
}
public void setSundayShift(String str){
    this.sundayShift = str;
}
public String getMondayShift(){
    return mondayShift;
}
public String getTuesdayShift(){
    return tuesdayShift;
}
public String getWednesdayShift(){
    return wednesdayShift;
}
public String getThursdayShift(){
    return thursdayShift;
}
public String getFridayShift(){
    return fridayShift;
}
public String getSaturdayShift(){
    return saturdayShift;
}
public String getSundayShift(){
    return sundayShift;
}
public int getShiftCount() {
    int count = 0;
    if (!mondayShift.isEmpty()) count++;
    if (!tuesdayShift.isEmpty()) count++;
    if (!wednesdayShift.isEmpty()) count++;
    if (!thursdayShift.isEmpty()) count++;
    if (!fridayShift.isEmpty()) count++;
    if (!saturdayShift.isEmpty()) count++;
    if (!sundayShift.isEmpty()) count++;
    return count;
}
public String getShift(String dayOfWeek) {
    switch (dayOfWeek) {
        case "Monday":
            return mondayShift;
        case "Tuesday":
            return tuesdayShift;
        case "Wednesday":
            return wednesdayShift;
        case "Thursday":
            return thursdayShift;
        case "Friday":
            return fridayShift;
        case "Saturday":
            return saturdayShift;
        case "Sunday":
            return sundayShift;
        default:
            return ""; // Handle any unexpected cases
    }
}
public int getEmployeeID(String dayOfWeek) {
    int employeeID;
    String[] parts;
    switch (dayOfWeek) {
        case "Monday":
            parts = mondayShift.split(":"); // Split the string by ":" to separate employee ID
            employeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer
            return employeeID;
        case "Tuesday":
            parts = tuesdayShift.split(":"); // Split the string by ":" to separate employee ID
            employeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer
            return employeeID;
        case "Wednesday":
            parts = wednesdayShift.split(":"); // Split the string by ":" to separate employee ID
            employeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer
            return employeeID;
        case "Thursday":
            parts = thursdayShift.split(":"); // Split the string by ":" to separate employee ID
            employeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer
            return employeeID;
        case "Friday":
            parts = fridayShift.split(":"); // Split the string by ":" to separate employee ID
            employeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer
            return employeeID;
        case "Saturday":
            parts = saturdayShift.split(":"); // Split the string by ":" to separate employee ID
            employeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer
            return employeeID;
        case "Sunday":
            parts = sundayShift.split(":"); // Split the string by ":" to separate employee ID
            employeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer
            return employeeID;
        default:
            return -1; // Handle any unexpected cases by returning a default value (e.g., -1)
    }
}
@Override
public String toString() {
    return "Monday Shift: " + mondayShift + "\n" +
           "Tuesday Shift: " + tuesdayShift + "\n" +
           "Wednesday Shift: " + wednesdayShift + "\n" +
           "Thursday Shift: " + thursdayShift + "\n" +
           "Friday Shift: " + fridayShift + "\n" +
           "Saturday Shift: " + saturdayShift + "\n" +
           "Sunday Shift: " + sundayShift;
}
}