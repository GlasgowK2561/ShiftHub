package shiftmate.proj;
// This class is used to build the default schedule table -- Written By:Elmer
public class Departments 
{
    private int depID;
    private String depName;
    private String depManager;
public Departments(int depID, String depName, String depManager) {
    this.depID = depID;
    this.depName = depName;
    this.depManager = depManager;
}
public int getDepID()      
{
    return depID;
}
public void setDepID(int depID)     
{
    this.depID = depID;
}
public String getDepName()     
{
    return depName;
}
public void setDepName(String depName)      
{
    this.depName = depName;
}
public String getDepManager()     
{
    return depManager;
}
public void setDepManager(String depManager)       
{
    this.depManager = depManager;
}
@Override
    public String toString() {
        return depName;
    }
    
}