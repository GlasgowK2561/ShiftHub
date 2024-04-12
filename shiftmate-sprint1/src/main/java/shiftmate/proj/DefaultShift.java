package shiftmate.proj;

import java.sql.Time;

public class DefaultShift 
{
    private int shiftID;
    private int depID;
    private String dayofWeek;
    private Time startTime;
    private Time endTime;  

 

public DefaultShift (int shiftID, int depID, String dayofWeek, Time startTime, Time endTime)
{
    this.shiftID = shiftID;
    this.depID = depID;
    this.dayofWeek = dayofWeek;
    this.startTime = startTime;
    this.endTime = endTime;

}

public int getShiftID() 
{
    return shiftID;
}

public void setShiftID(int shiftID)  
{
    this.shiftID = shiftID;
}

public int getDepID()     
{
    return depID;
}

public void setDepID(int depID)   
{
    this.depID = depID;
}

public String getDayofWeek()      
{
    return dayofWeek;
}

public void setDayofWeek(String dayofWeek)   
{
    this.dayofWeek = dayofWeek;
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






}


