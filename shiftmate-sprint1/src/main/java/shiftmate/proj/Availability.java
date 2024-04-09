package shiftmate.proj;

import java.sql.Time;

public class Availability 
{
    private int availID;
    private int employeeID;
    private String weekdayDate;
    private Time startTime;
    private Time endTime;
    private String availType;    
    
public Availability(int availID, int employeeID, String weekdayDate,Time startTime,
Time endTime, String availType)

{
    this.availID = availID;
    this.employeeID = employeeID;
    this.weekdayDate = weekdayDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.availType =availType;
}

public int getAvailID()  
{       
    return availID;
}

public void setAvailID(int availID)
{     
    this.availID = availID;
}

public int getEmployeeID()      
{
    return employeeID;
}

public void setEmployeeID(int employeeID)      
{
    this.employeeID = employeeID;
}

public String getWeekdayDate()      
{
    return weekdayDate;
}

public void setWeekdayDate(String weekdayDate)   
{ 
    this.weekdayDate = weekdayDate;
}

public Time getStartTime()     
{
    return startTime;
}

public void setStartTime(Time startTime)       
{
    this.startTime = startTime;
}

public Time getEndTime() 
{
    return endTime;
}

public void setEndTime(Time endTime)
{
    this.endTime = endTime;
}

public String getAvailType()    
{
    return availType;
}

public void setAvailType(String availType)  
{
    this.availType = availType;
}

}
