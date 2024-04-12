package shiftmate.proj;

public class DefaultSchedule {
    private int scheduleID;
    private int depID;
    private String dayOfWeek;
    private String shiftStart;
    private String shiftEnd;

    public DefaultSchedule(int scheduleID, int depID, String dayOfWeek, String shiftStart, String shiftEnd) {
        this.scheduleID = scheduleID;
        this.depID = depID; 
        this.dayOfWeek = dayOfWeek;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
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
        this.depID = depID; // Correcting the parameter name in the setter method
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
}
