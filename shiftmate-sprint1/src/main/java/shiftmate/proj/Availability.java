package shiftmate.proj;

public class Availability 
{
    private int availID;
    private int employeeID;
    private String weekdayDate;
    private String startTime;
    private String endTime;
    private String availType;    
    
public Availability(int availID, int employeeID, String weekdayDate,String startTime, String endTime, String availType)
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

public String getStartTime()     
{
    return startTime;
}

public void setStartTime(String startTime)       
{
    this.startTime = startTime;
}

public String getEndTime() 
{
    return endTime;
}

public void setEndTime(String endTime)
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
