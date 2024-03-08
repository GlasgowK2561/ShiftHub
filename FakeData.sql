-- Insert data into the Departments table
INSERT INTO Departments (depID, depName, depManager) VALUES
(1, 'Grocery', 'John Doe'),
(2, 'Checkout', 'Jane Smith'),
(3, 'Beauty', 'Mike Johnson'),
(4, 'Clothes', 'Sarah Johnson'),
(5, 'Fulfillment', 'Michael Smith'),
(6, 'Deli', 'Emily Brown');

-- Insert data into the EmployeeInfo table
INSERT INTO EmployeeInfo (employeeID, deptID, fName, lName, email, phone, startDate, contact, contactPhone) VALUES
(101, 1, 'Alice', 'Johnson', 'alice@example.com', '123-456-7890', '2023-01-15', 'John Doe', '111-222-3333'),
(102, 2, 'Bob', 'Smith', 'bob@example.com', '987-654-3210', '2023-02-01', 'Jane Smith', '444-555-6666'),
(103, 3, 'Charlie', 'Brown', 'charlie@example.com', '555-123-4567', '2023-03-10', 'Mike Johnson', '777-888-9999'),
(104, 4, 'David', 'Wilson', 'david@example.com', '222-333-4444', '2023-04-15', 'Sarah Johnson', '555-666-7777'),
(105, 5, 'Emma', 'Davis', 'emma@example.com', '333-444-5555', '2023-05-01', 'Michael Smith', '666-777-8888'),
(106, 6, 'Frank', 'Taylor', 'frank@example.com', '444-555-6666', '2023-06-01', 'Emily Brown', '777-888-9999');

-- Insert data into the DefaultShifts table
INSERT INTO DefaultShifts (ShiftID, DepID, DayOfWeek, StartTime, EndTime) VALUES
(1, 1, 'Monday', '09:00:00', '17:00:00'),
(2, 1, 'Tuesday', '09:00:00', '17:00:00'),
(3, 2, 'Wednesday', '08:00:00', '16:00:00'),
(4, 3, 'Thursday', '08:00:00', '16:00:00'),
(5, 4, 'Friday', '09:00:00', '17:00:00'),
(6, 5, 'Monday', '07:00:00', '15:00:00');

-- Insert data into the Availability table
INSERT INTO Availability (availID, employeeID, weekDayDate, StartTime, EndTime, availType) VALUES
(1, 101, '2024-02-14', '08:00:00', '17:00:00', 'Available'),
(2, 102, '2024-02-14', '09:00:00', '18:00:00', 'Unavailable'),
(3, 103, '2024-02-14', '08:00:00', '17:00:00', 'Available'),
(4, 104, '2024-02-14', '08:00:00', '17:00:00', 'Available'),
(5, 105, '2024-02-14', '09:00:00', '18:00:00', 'Unavailable'),
(6, 106, '2024-02-14', '08:00:00', '17:00:00', 'Available');
