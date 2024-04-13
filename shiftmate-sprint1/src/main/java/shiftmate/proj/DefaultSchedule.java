package shiftmate.proj;

import java.util.List;
import java.util.Map;

public class DefaultSchedule {
    private int scheduleID;
    private int depID;
    private String dayOfWeek;
    private String shiftStart;
    private String shiftEnd;
    private Map<String, List<String>> shiftsByDay;
    private List<String> mondayShift;
    private List<String> tuesdayShift;
    private List<String> wednesdayShift;
    private List<String> thursdayShift;
    private List<String> fridayShift;
    private List<String> saturdayShift;
    private List<String> sundayShift;

    public DefaultSchedule(int scheduleID, int depID, String dayOfWeek, String shiftStart, String shiftEnd) {
        this.scheduleID = scheduleID;
        this.depID = depID; 
        this.dayOfWeek = dayOfWeek;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public DefaultSchedule(Map<String, List<String>> shiftsByDay) {
        this.shiftsByDay = shiftsByDay;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID; 
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

