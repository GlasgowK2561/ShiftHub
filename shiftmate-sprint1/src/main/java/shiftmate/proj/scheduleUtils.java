package shiftmate.proj;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
public class scheduleUtils {
    
public static ObservableList<scheduleRow> reorganizeScheduleRows(ObservableList<scheduleRow> scheduleRows) {
    ObservableList<scheduleRow> updatedScheduleRows = FXCollections.observableArrayList();
    int i = 0;
    while (i < scheduleRows.size() - 1) {
        scheduleRow currentRow = scheduleRows.get(i);
        scheduleRow nextRow = scheduleRows.get(i + 1);
        // Check if the next row has any non-empty shifts
        if (!isRowEmpty(nextRow)) {
            // Merge non-empty shifts from the next row into the current row
            mergeShifts(updatedScheduleRows,currentRow, nextRow);
            // Remove the next row
            scheduleRows.remove(nextRow);
        } else {
            // Move to the next row if it's empty
            i++;
        }
    }
    updatedScheduleRows.addAll(scheduleRows);
    return updatedScheduleRows;
}
private static void mergeShifts(ObservableList<scheduleRow> scheduleRows, scheduleRow targetRow, scheduleRow sourceRow) {
    // Merge non-empty shifts from source row to target row
    if (!sourceRow.getMondayShift().isEmpty()) {
        if (targetRow.getMondayShift().isEmpty()) {
            targetRow.setMondayShift(sourceRow.getMondayShift());
        } else {
            // Create a new row with the same shifts as the current row
            scheduleRow newRow = new scheduleRow(
                targetRow.getMondayShift(),
                targetRow.getTuesdayShift(),
                targetRow.getWednesdayShift(),
                targetRow.getThursdayShift(),
                targetRow.getFridayShift(),
                targetRow.getSaturdayShift(),
                targetRow.getSundayShift()
            );
            // Set the Monday shift of the new row to the shift of the source row
            newRow.setMondayShift(sourceRow.getMondayShift());
            // Add the new row after the current row
            scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
        }
    }
    if (!sourceRow.getTuesdayShift().isEmpty()) {
        if (targetRow.getTuesdayShift().isEmpty()) {
            targetRow.setTuesdayShift(sourceRow.getTuesdayShift());
        } else {
            // Create a new row with the same shifts as the current row
            scheduleRow newRow = new scheduleRow(
                targetRow.getMondayShift(),
                targetRow.getTuesdayShift(),
                targetRow.getWednesdayShift(),
                targetRow.getThursdayShift(),
                targetRow.getFridayShift(),
                targetRow.getSaturdayShift(),
                targetRow.getSundayShift()
            );
            // Set the Monday shift of the new row to the shift of the source row
            newRow.setTuesdayShift(sourceRow.getTuesdayShift());
            // Add the new row after the current row
            scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
        }
    }
    if (!sourceRow.getWednesdayShift().isEmpty()) {
        if (targetRow.getWednesdayShift().isEmpty()) {
            targetRow.setWednesdayShift(sourceRow.getWednesdayShift());
        } else {
            // Create a new row with the same shifts as the current row
            scheduleRow newRow = new scheduleRow(
                targetRow.getMondayShift(),
                targetRow.getTuesdayShift(),
                targetRow.getWednesdayShift(),
                targetRow.getThursdayShift(),
                targetRow.getFridayShift(),
                targetRow.getSaturdayShift(),
                targetRow.getSundayShift()
            );
            // Set the Monday shift of the new row to the shift of the source row
            newRow.setWednesdayShift(sourceRow.getWednesdayShift());
            // Add the new row after the current row
            scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
        }
    }
    if (!sourceRow.getThursdayShift().isEmpty()) {
        if (targetRow.getThursdayShift().isEmpty()) {
            targetRow.setThursdayShift(sourceRow.getThursdayShift());
        } else {
            // Create a new row with the same shifts as the current row
            scheduleRow newRow = new scheduleRow(
                targetRow.getMondayShift(),
                targetRow.getTuesdayShift(),
                targetRow.getWednesdayShift(),
                targetRow.getThursdayShift(),
                targetRow.getFridayShift(),
                targetRow.getSaturdayShift(),
                targetRow.getSundayShift()
            );
            // Set the Monday shift of the new row to the shift of the source row
            newRow.setThursdayShift(sourceRow.getThursdayShift());
            // Add the new row after the current row
            scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
        }
    }
    if (!sourceRow.getFridayShift().isEmpty()) {
        if (targetRow.getFridayShift().isEmpty()) {
            targetRow.setFridayShift(sourceRow.getFridayShift());
        } else {
            // Create a new row with the same shifts as the current row
            scheduleRow newRow = new scheduleRow(
                targetRow.getMondayShift(),
                targetRow.getTuesdayShift(),
                targetRow.getWednesdayShift(),
                targetRow.getThursdayShift(),
                targetRow.getFridayShift(),
                targetRow.getSaturdayShift(),
                targetRow.getSundayShift()
            );
            // Set the Monday shift of the new row to the shift of the source row
            newRow.setFridayShift(sourceRow.getFridayShift());
            // Add the new row after the current row
            scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
        }
    }
    if (!sourceRow.getSaturdayShift().isEmpty()) {
        if (targetRow.getSaturdayShift().isEmpty()) {
            targetRow.setSaturdayShift(sourceRow.getSaturdayShift());
        } else {
            // Create a new row with the same shifts as the current row
            scheduleRow newRow = new scheduleRow(
                targetRow.getMondayShift(),
                targetRow.getTuesdayShift(),
                targetRow.getWednesdayShift(),
                targetRow.getThursdayShift(),
                targetRow.getFridayShift(),
                targetRow.getSaturdayShift(),
                targetRow.getSundayShift()
            );
            // Set the Monday shift of the new row to the shift of the source row
            newRow.setSaturdayShift(sourceRow.getSaturdayShift());
            // Add the new row after the current row
            scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
        }
    }      
    if (!sourceRow.getSundayShift().isEmpty()) {
        if (targetRow.getSundayShift().isEmpty()) {
            targetRow.setSundayShift(sourceRow.getSundayShift());
        } else {
            // Create a new row with the same shifts as the current row
            scheduleRow newRow = new scheduleRow(
                targetRow.getMondayShift(),
                targetRow.getTuesdayShift(),
                targetRow.getWednesdayShift(),
                targetRow.getThursdayShift(),
                targetRow.getFridayShift(),
                targetRow.getSaturdayShift(),
                targetRow.getSundayShift()
            );
            // Set the Monday shift of the new row to the shift of the source row
            newRow.setSundayShift(sourceRow.getSundayShift());
            // Add the new row after the current row
            scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
        }
    }  
}

public static boolean isRowEmpty(scheduleRow row) {
    return row.getMondayShift().isEmpty() &&
           row.getTuesdayShift().isEmpty() &&
           row.getWednesdayShift().isEmpty() &&
           row.getThursdayShift().isEmpty() &&
           row.getFridayShift().isEmpty() &&
           row.getSaturdayShift().isEmpty() &&
           row.getSundayShift().isEmpty();
}

}