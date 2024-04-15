package shiftmate.proj;

import java.util.List;
import java.util.Map;

public class WeeklySchedule {
    private int shiftID; // Changed from scheduleID
    private int depID;
    private String fName;
    private String lName;
    private String dayOfWeek;
    private String shiftStart;
    private String shiftEnd;
    private int employeeID;
    private Map<String, List<String>> shiftsByDay;
    private List<String> mondayShift;
    private List<String> tuesdayShift;
    private List<String> wednesdayShift;
    private List<String> thursdayShift;
    private List<String> fridayShift;
    private List<String> saturdayShift;
    private List<String> sundayShift;

    public WeeklySchedule(int shiftID, int depID, int employeeID, String dayOfWeek, String shiftStart, String shiftEnd) {
        this.shiftID = shiftID; // Changed from scheduleID
        this.depID = depID; 
        this.employeeID = employeeID;
        this.dayOfWeek = dayOfWeek;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public WeeklySchedule(Map<String, List<String>> shiftsByDay) {
        this.shiftsByDay = shiftsByDay;
    }

    public String getFName()   
    {
        return fName;
    }
    
    public void setFName(String fName) 
    {
        this.fName = fName;
    }
    
    public String getLName()   
    {
        return lName;
    }
    
    public void setLName(String lName)     
    {
        this.lName = lName;
    }
    public String getEmployee(String FullName){
        return fName + " " + lName;
    }
    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID; 
    }
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID; 
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(String shiftStart) {
        this.shiftStart = shiftStart;
    }

    public String getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(String shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public Map<String, List<String>> getShiftsByDay() {
        return shiftsByDay;
    }

    public void setShiftsByDay(Map<String, List<String>> shiftsByDay) {
        this.shiftsByDay = shiftsByDay;
    }
    public List<String> getMondayShift(){
       return mondayShift;
    }

    public List<String> getTuesdayShift(){
        return tuesdayShift;
     }

    public List<String> getWednesdayShift(){
        return wednesdayShift;
    }
    public List<String> getThursdayShift(){
        return thursdayShift;
    }
    public List<String> getFridayShift(){
        return fridayShift;
    }
    public List<String> getSaturdayShift(){
        return saturdayShift;
    }
    public List<String> getSundayShift(){
        return sundayShift;
    }

    public void setMondayShift(List<String> mondayShift) {
        this.mondayShift = mondayShift;
    }

    public void setTuesdayShift(List<String> tuesdayShift) {
        this.tuesdayShift = tuesdayShift;
    }

    public void setWednesdayShift(List<String> wednesdayShift) {
        this.wednesdayShift = wednesdayShift;
    }

    public void setThursdayShift(List<String> thursdayShift) {
        this.thursdayShift = thursdayShift;
    }

    public void setFridayShift(List<String> fridayShift) {
        this.fridayShift = fridayShift;
    }

    public void setSaturdayShift(List<String> saturdayShift) {
        this.saturdayShift = saturdayShift;
    }

    public void setSundayShift(List<String> sundayShift) {
        this.sundayShift = sundayShift;
    }
}

