package shiftmate.proj;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

public class CreateWeeklyScheduleUtils {
    private static HashMap<Integer, Integer> scheduledHoursMap = new HashMap<>();
    private static void printScheduledShiftsList(LinkedList<Hashtable<String, String>> scheduledShiftsList) {
        for (Hashtable<String, String> scheduledShift : scheduledShiftsList) {
            System.out.println("Scheduled Shift:");
            for (String key : scheduledShift.keySet()) {
                System.out.println(key + ": " + scheduledShift.get(key));
            }
            System.out.println(); // Add a blank line for separation
        }
    }    
    public static LinkedList<Hashtable<String, String>> buildWeeklySchedule(String depName) {
        // Retrieve employee availabilities and default schedule information
        LinkedList<Hashtable<String, String>> employeeAvailabilities = DBController.getAvailabilities(depName);
        LinkedList<Hashtable<String, String>> defaultScheduleInformation = DBController.getDefaultSchedule(depName);
    
        // LinkedList to store scheduled shifts
        LinkedList<Hashtable<String, String>> scheduledShiftsList = new LinkedList<>();
    
        // HashSet to store scheduled shifts
        HashSet<String> scheduledShifts = new HashSet<>();
    
        for (Hashtable<String, String> shift : defaultScheduleInformation) {
            String dayOfWeek = shift.get("DayOfWeek");
    
            for (Hashtable<String, String> employeeAvailability : employeeAvailabilities) {
                if (employeeAvailability.get("weekDay").equalsIgnoreCase(dayOfWeek)) {
                    String startTime = shift.get("StartTime");
                    String endTime = shift.get("EndTime");
    
                    // Construct a unique identifier for the shift
                    String shiftIdentifier = dayOfWeek + "-" + startTime;
    
                    // Check if the shift has already been scheduled
                    if (scheduledShifts.contains(shiftIdentifier)) {
                        continue; // Skip to the next employee
                    }
    
                    String employeeStartTime = employeeAvailability.get("StartTime");
                    String employeeEndTime = employeeAvailability.get("EndTime");
                    String employeeAvailType = employeeAvailability.get("availType");
    
                    // Calculate the duration of the shift
                    LocalTime shiftStartTimeParsed = parseTime(startTime);
                    LocalTime shiftEndTimeParsed = parseTime(endTime);
                    long hoursForThisShift = ChronoUnit.HOURS.between(shiftStartTimeParsed, shiftEndTimeParsed);
    
                    // Check if employee is available during this shift
                    if (isAvailable(startTime, endTime, employeeStartTime, employeeEndTime, employeeAvailType)) {
                        int maxWeeklyHours = Integer.parseInt(employeeAvailability.get("maxWeeklyHours"));
                        int scheduledHours = calculateScheduledHours(employeeAvailability);
    
                        // Check if employee has not reached maxWeeklyHours
                        if (scheduledHours + hoursForThisShift <= maxWeeklyHours) {
                            // Construct a Hashtable to represent the scheduled shift
                            Hashtable<String, String> scheduledShift = new Hashtable<>();
                            scheduledShift.put("DayOfWeek", dayOfWeek);
                            scheduledShift.put("StartTime", startTime);
                            scheduledShift.put("EndTime", endTime);
                            scheduledShift.put("EmployeeID", employeeAvailability.get("employeeID"));
    
                            // Add the scheduled shift to the list
                            scheduledShiftsList.add(scheduledShift);
    
                            // Update the scheduled hours for the employee
                            int employeeID = Integer.parseInt(employeeAvailability.get("employeeID"));
                            scheduledHoursMap.put(employeeID, scheduledHoursMap.getOrDefault(employeeID, 0) + (int) hoursForThisShift);
    
                            // Add the shift to the set of scheduled shifts
                            scheduledShifts.add(shiftIdentifier);
                        }
                    }
                }
            }
        }
        return scheduledShiftsList;
    }    
    // Helper method to calculate the total scheduled hours for an employee
    private static int calculateScheduledHours(Hashtable<String, String> employeeAvailability) {
        int employeeID = Integer.parseInt(employeeAvailability.get("employeeID"));
        return scheduledHoursMap.getOrDefault(employeeID, 0);
    }
    // Helper method to check if the employee is available during a given shift
    private static boolean isAvailable(String shiftStartTime, String shiftEndTime, String employeeStartTime, String employeeEndTime, String availType) {
        // Format Shift Times
        LocalTime shiftStartTimeParsed = parseTime(shiftStartTime);
        LocalTime shiftEndTimeParsed = parseTime(shiftEndTime);
        // Format Employee Availability
        LocalTime employeeStartTimeParsed = parseTime(employeeStartTime);
        LocalTime employeeEndTimeParsed = parseTime(employeeEndTime);
    
        if (availType.equals("Available")) {
            // Check if the employee's available time overlaps with the shift time
            if (employeeStartTimeParsed.compareTo(shiftEndTimeParsed) < 0 &&
                employeeEndTimeParsed.compareTo(shiftStartTimeParsed) > 0) {
                return true; // Employee is available during the shift
            } else {
                return false; // Employee is not available during the shift
            }
        } else { // If availType is "Unavailable"
            // Check if the employee's unavailable time overlaps with the shift time
            if (employeeStartTimeParsed.compareTo(shiftEndTimeParsed) >= 0 ||
                employeeEndTimeParsed.compareTo(shiftStartTimeParsed) <= 0) {
                return true; // Employee is unavailable during the shift
            } else {
                return false; // Employee is available during the shift
            }
        }
    }
    
    // Helper method to parse time string to LocalTime
    private static LocalTime parseTime(String timeStr) {
        // Assuming timeStr is in "HH:mm:ss" format
        return LocalTime.parse(timeStr);
    }

}
