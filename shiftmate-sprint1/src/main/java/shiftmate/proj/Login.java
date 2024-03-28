package shiftmate.proj;

public class Login 
{
    private int userID;
    private String username;
    private String pass;
    private int employeeID;


public Login(int userID, String username, String pass, int employeeID)
{
    this.userID = userID;
    this.username = username;
    this.pass = pass;
    this.employeeID = employeeID;
}


public int getUserID() 
{
    return userID;
}

public void setUserID(int userID)   
{
    this.userID = userID;
}




public String getUsername()  
{
    return username;
}

public void setUsername(String username)    
{
    this.username = username;
}





public String getPass()  
{
    return pass;
}

public void setPass(String pass)     
{
    this.pass = pass;
}






public int getEmployeeID() 
{
    return employeeID;
}

public void setEmployeeID(int employeeID)    
{
    this.employeeID = employeeID;
}



}

