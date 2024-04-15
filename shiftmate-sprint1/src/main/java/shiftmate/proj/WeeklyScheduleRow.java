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
    String shiftDetails = getShift(dayOfWeek);
    String[] parts = shiftDetails.split("\n");
    // Now parts[0] will contain the employee name, and parts[1] will contain the shift information
    String employeeName = parts[0]; // "John Doe"    
    // Split the employee name string by space character to separate first name and last name
    String[] nameParts = employeeName.split(" ");
    String firstName = nameParts[0]; // "John"
    String lastName = nameParts[1]; // "Doe"
    int employeeID = DBController.getEmployeeID(firstName, lastName);
    return employeeID;
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