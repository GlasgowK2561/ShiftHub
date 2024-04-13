package shiftmate.proj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class scheduleRow {
    private String mondayShift;
    private String tuesdayShift;
    private String wednesdayShift;
    private String thursdayShift;
    private String fridayShift;
    private String saturdayShift;
    private String sundayShift;

public scheduleRow(String mondayShift, String tuesdayShift, String wednesdayShift, String thursdayShift, String fridayShift, String saturdayShift, String sundayShift) {
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
}