package shiftmate.proj;

public class EmployeeInfo 
{
    private int employeeID;
    private int depID;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String startDate;
    private String contact;
    private String contactPhone;
@Override
public String toString() {
    return fName + " " + lName; // Assuming fname and lname are fields representing first name and last name
}
public EmployeeInfo(int employeeID , int depID, String fname, String lName, String email, 
String phone, String startDate, String contact, String contactPhone)
{
    this.employeeID = employeeID;
    this.depID = depID;
    this.fName = fname;
    this.lName = lName;
    this.email = email;
    this.phone = phone;
    this.startDate = startDate;
    this.contact = contact;
    this.contactPhone = contactPhone;
}

public int getEmployeeID()
{
    return employeeID;
}

public void setEmployeeID(int employeeID) 
{
    this.employeeID = employeeID;
}

public int getDepID()    
{
    return depID;
}

public void setDepID(int depID)     
{
    this.depID = depID;
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

public String getEmail()       
{
    return email;
}

public void setEmail(String email)      
{
    this.email = email;
}

public String getPhone()       
{
    return phone;
}

public void setPhone(String phone)      
{
    this.phone = phone;
}

public String getStartDate()   
{
    return startDate;
}

public void setStartDate(String startDate)     
{
    this.startDate = startDate;
}

public String getContact()     
{
    return contact;
}

public void setContact(String contact)   
{
    this.contact = contact;
}

public String getContactPhone()        
{
    return contactPhone;
}

public void setContactPhone(String contactPhone)      
{
    this.contactPhone = contactPhone;
}

private String depName;

public String getDepName()
{
    return depName;
}

public void setDepName(String depName) 
{
    this.depName = depName;
}

}