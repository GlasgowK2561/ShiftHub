# ShiftHub: A Business Scheduler Application

## Introduction:
When working in the service industry, it is often difficult to create an effective schedule to manage a team, especially when considering the schedules of each employee. Inspired by the challenges faced with scheduling and managing a team, ShiftMate will create a dependable, reliable, and easy to use application that aims to streamline the schedule making process.

## Objective:
Create a dependable, reliable, and easy to use application to streamline operations, and help manage a team.

## Technical Details
## Tools:
NetBeans

## Resources:
JavaFX, MySQL

## Programming Languages and Packages
Java, JavaFX, MySQL

### Target Audience
Grocery Stores, Retailers, and Resturants. Any buisness trying to find a solution for scheduling needs!

### Main Functions
1. Secure SQL Server Connection
2. Login with Authentication and Encryption
3. Create Schedule
4. Add/Edit Employee Information
5. Distribute Schedules

### Creating a schedule
The general manager of the business will be the main user for this application. The general manager can hire/fire employees, create departments, and create schedules. When the manager creates a department, a "default" schedule will be created for a normal week. This can be edited at any time. This default schedule allows for the scheduler to create the weekly schedule seamlessly using the employees availablility from our database. When the manager creates the weekly schedule, the scheduler will create a schedule for each department and send the schedule straight to the employee.

### Adding an Employee
When an employee is hired, the manager can add their information, and then hire them for a specific department. This allows for the employee to be added to the weekly schedule for that department. Once the employee has been fully hired on, they are now able to be scheduled on their departments schedule.

### Distributing the schedule
Whenever a manager creates or edits a schedule, the application will either email or text the schedule to the employees. This allows for the employee to have their schedule available at all times.


## Getting Started
This section will discuss how to run the program. It will require the following programs:
1. XAMPP
2. VS Code
3. GitHub Desktop

First, clone the repo into GitHub Desktop, and open in the project (open the entire folder named "Shifthub-Sprint1") in Visual Studio Code. Upon opening the files, the user will need to download the appropriate extensions for visual studio code to run the application. To do so, the user must
1. Go to Extensions on Visual Studio
2. Download the Java Extension
3. Download the JavaFX Extension

Now the user should be able to run the first sprint of the project by running "Main.java". However, to have database integration, the user must download XAMPP. After downloading, the user can open "Apache" and "MySQL". With XAMPP running in the background, the user shoud now run the .sql files found in Sprint1-SQL using the terminal. This will create the correct database for the application to read and write to. At this time, this contains dummy data. Now, the user can run the application with a functional login!
