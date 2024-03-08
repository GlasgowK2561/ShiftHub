
CREATE TABLE Departments (
    depID INT PRIMARY KEY,
    depName VARCHAR(50),
    depManager VARCHAR(50) 
);
CREATE TABLE EmployeeInfo (
    employeeID INT PRIMARY KEY,
    deptID INT, -- Define deptID column here
    fName VARCHAR(50),
    lName VARCHAR(50),
    email VARCHAR(50),
    phone VARCHAR(15),
    startDate VARCHAR(50),
    contact VARCHAR(100),
    contactPhone VARCHAR(15),
    FOREIGN KEY (deptID) REFERENCES Departments(depID) -- Fix typo in REFERENCES keyword
);

CREATE TABLE Login (
    userID INT PRIMARY KEY,
    username VARCHAR(50),
    pass VARCHAR(30),
    employeeID INT, -- Define employeeID column here
    FOREIGN KEY (employeeID) REFERENCES EmployeeInfo(employeeID)
);

CREATE TABLE DefaultShifts (
    ShiftID INT PRIMARY KEY,
    DepID INT,
    DayOfWeek VARCHAR(255),
    StartTime TIME,
    EndTime TIME,
    FOREIGN KEY (DepID) REFERENCES Departments(depID)
);

CREATE TABLE Availability (
    availID INT PRIMARY KEY,
    employeeID INT, -- Define employeeID column here
    FOREIGN KEY (employeeID) REFERENCES EmployeeInfo(employeeID),
    weekDayDate VARCHAR(20),
    StartTime TIME,
    EndTime TIME, -- Add comma here
    availType VARCHAR(30)
);
