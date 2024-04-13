
package shiftmate.proj;
// Imports
import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.SelectionMode;

public class CreateDefaultScheduleController implements Initializable {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button saveScheduleButton;

    @FXML
    TableView<scheduleRow> scheduleTableView = new TableView<>();
    
    @FXML
    private TableColumn<scheduleRow, String> mondayColumn;
    
    @FXML
    private TableColumn<scheduleRow, String> tuesdayColumn;
    
    @FXML
    private TableColumn<scheduleRow, String> wednesdayColumn;
    
    @FXML
    private TableColumn<scheduleRow, String> thursdayColumn;
    
    @FXML
    private TableColumn<scheduleRow, String> fridayColumn;
    
    @FXML
    private TableColumn<scheduleRow, String> saturdayColumn;
    
    @FXML
    private TableColumn<scheduleRow, String> sundayColumn;

    private Button saveButton;

    private String depName;
    private Integer depID;

    public void setDepName(String depName) {
        this.depName = depName;
    }
    public void setDepID(int depID) {
        this.depID = depID;
    }
    public ObservableList<scheduleRow> sortRows(ObservableList<scheduleRow> scheduleRows) {
        // Sort the rows based on their emptiness (non-empty rows first)
        scheduleRows.sort(Comparator.comparingInt(row -> {
            // Count the number of empty shifts in the row
            int emptyShifts = 0;
            if (row.getMondayShift().isEmpty()) emptyShifts++;
            if (row.getTuesdayShift().isEmpty()) emptyShifts++;
            if (row.getWednesdayShift().isEmpty()) emptyShifts++;
            if (row.getThursdayShift().isEmpty()) emptyShifts++;
            if (row.getFridayShift().isEmpty()) emptyShifts++;
            if (row.getSaturdayShift().isEmpty()) emptyShifts++;
            if (row.getSundayShift().isEmpty()) emptyShifts++;
            // Return count to sort rows in descending order of empty shifts
            return emptyShifts;
        }));
    
        return scheduleRows;
    }    
    public ObservableList<scheduleRow> reorganizeScheduleRows(ObservableList<scheduleRow> scheduleRows) {
        ObservableList<scheduleRow> updatedScheduleRows = FXCollections.observableArrayList();
        int i = 0;
        while (i < scheduleRows.size() - 1) {
            scheduleRow currentRow = scheduleRows.get(i);
            scheduleRow nextRow = scheduleRows.get(i + 1);
            
            // Check if the next row has any non-empty shifts
            if (!isRowEmpty(nextRow)) {
                // Merge non-empty shifts from the next row into the current row if they are consecutive days
                if (areConsecutiveDays(currentRow, nextRow)) {
                    mergeShifts(updatedScheduleRows, currentRow, nextRow);
                    // Remove the next row
                    scheduleRows.remove(nextRow);
                } else {
                    // Move to the next row if it's not consecutive
                    i++;
                }
            } else {
                // Move to the next row if it's empty
                i++;
            }
        }
        updatedScheduleRows.addAll(scheduleRows);
        return updatedScheduleRows;
    }
    private boolean areConsecutiveDays(scheduleRow row1, scheduleRow row2) {
        // Check if the days of the week for row2 follow row1 consecutively
        if (row2.getMondayShift().isEmpty() && !row1.getTuesdayShift().isEmpty()) {
            return true;
        } else if (row2.getTuesdayShift().isEmpty() && !row1.getWednesdayShift().isEmpty()) {
            return true;
        } else if (row2.getWednesdayShift().isEmpty() && !row1.getThursdayShift().isEmpty()) {
            return true;
        } else if (row2.getThursdayShift().isEmpty() && !row1.getFridayShift().isEmpty()) {
            return true;
        } else if (row2.getFridayShift().isEmpty() && !row1.getSaturdayShift().isEmpty()) {
            return true;
        } else if (row2.getSaturdayShift().isEmpty() && !row1.getSundayShift().isEmpty()) {
            return true;
        }
        return false;
    }    
    private void mergeShifts(ObservableList<scheduleRow> scheduleRows, scheduleRow targetRow, scheduleRow sourceRow) {
        mergeShiftsForDay(scheduleRows, targetRow, sourceRow, 0);
    }
    private String getShiftForDay(scheduleRow row, int dayIndex) {
        switch (dayIndex) {
            case 0: return row.getMondayShift();
            case 1: return row.getTuesdayShift();
            case 2: return row.getWednesdayShift();
            case 3: return row.getThursdayShift();
            case 4: return row.getFridayShift();
            case 5: return row.getSaturdayShift();
            case 6: return row.getSundayShift();
            default: return "";
        }
    }
    private void mergeShiftsForDay(ObservableList<scheduleRow> scheduleRows, scheduleRow targetRow, scheduleRow sourceRow, int dayIndex) {
        if (dayIndex == 7) {
            // Base case: we've processed all days
            return;
        }
    
        // Get the shift for the current day from both rows
        String targetShift = getShiftForDay(targetRow, dayIndex);
        String sourceShift = getShiftForDay(sourceRow, dayIndex);
    
        // Merge the shifts if source shift is non-empty
        if (!sourceShift.isEmpty()) {
            if (!targetShift.isEmpty()) {
                // If target shift is not empty, create a new row for the source shift
                scheduleRow newRow = new scheduleRow("", "", "", "", "", "", "");
                setShiftForDay(newRow, sourceShift, dayIndex);
                scheduleRows.add(scheduleRows.indexOf(targetRow) + 1, newRow);
            } else {
                // If target shift is empty, simply set it to source shift
                setShiftForDay(targetRow, sourceShift, dayIndex);
            }
        }

        // Recursively process the next day
        mergeShiftsForDay(scheduleRows, targetRow, sourceRow, dayIndex + 1);
    }     
    private void setShiftForDay(scheduleRow row, String shift, int dayIndex) {
        switch (dayIndex) {
            case 0: row.setMondayShift(shift); break;
            case 1: row.setTuesdayShift(shift); break;
            case 2: row.setWednesdayShift(shift); break;
            case 3: row.setThursdayShift(shift); break;
            case 4: row.setFridayShift(shift); break;
            case 5: row.setSaturdayShift(shift); break;
            case 6: row.setSundayShift(String.join("\n", shift)); break; // Concatenate shifts into a single string
        }
    }
    public boolean isRowEmpty(scheduleRow row) {
        return row.getMondayShift().isEmpty() &&
               row.getTuesdayShift().isEmpty() &&
               row.getWednesdayShift().isEmpty() &&
               row.getThursdayShift().isEmpty() &&
               row.getFridayShift().isEmpty() &&
               row.getSaturdayShift().isEmpty() &&
               row.getSundayShift().isEmpty();
    }
    public void DefaultScheduleTable() {
        System.out.println("POPULATING TABLE");
        scheduleTableView.getItems().clear();
    
        LinkedList<Hashtable<String, String>> defaultScheduleInformation = DBController.getDefaultSchedule(depName);
        ObservableList<scheduleRow> scheduleRows = FXCollections.observableArrayList();
        for (Hashtable<String, String> data : defaultScheduleInformation) {
            String dayOfWeek = data.get("DayOfWeek");
            String shiftStart = data.get("StartTime");
            String shiftEnd = data.get("EndTime");
            // Create a new scheduleRow instance
            scheduleRow row = new scheduleRow("", "", "", "", "", "", "");
            // Set the shift for the corresponding day of the week
            switch (dayOfWeek.toLowerCase()) {
                case "monday":
                    row.setMondayShift(shiftStart + " - " + shiftEnd);
                    break;
                case "tuesday":
                    row.setTuesdayShift(shiftStart + " - " + shiftEnd);
                    break;
                case "wednesday":
                    row.setWednesdayShift(shiftStart + " - " + shiftEnd);
                    break;
                case "thursday":
                    row.setThursdayShift(shiftStart + " - " + shiftEnd);
                    break;
                case "friday":
                    row.setFridayShift(shiftStart + " - " + shiftEnd);
                    break;
                case "saturday":
                    row.setSaturdayShift(shiftStart + " - " + shiftEnd);
                    break;
                case "sunday":
                    row.setSundayShift(shiftStart + " - " + shiftEnd);
                    break;
                default:
                    break;
            }
            // Add the schedule row to the list
            scheduleRows.add(row);
        }
        System.out.println("BEFORE REORGANIZING");
        for (scheduleRow row : scheduleRows) {
            System.out.println("Monday Shift: " + row.getMondayShift());
            System.out.println("Tuesday Shift: " + row.getTuesdayShift());
            System.out.println("Wednesday Shift: " + row.getWednesdayShift());
            System.out.println("Thursday Shift: " + row.getThursdayShift());
            System.out.println("Friday Shift: " + row.getFridayShift());
            System.out.println("Saturday Shift: " + row.getSaturdayShift());
            System.out.println("Sunday Shift: " + row.getSundayShift());
            System.out.println("---------------------");
        }
        ObservableList<scheduleRow> newScheduleRows = reorganizeScheduleRows(scheduleRows);
        System.out.println("AFTER REORGANIZING");
        for (scheduleRow row : newScheduleRows) {
            System.out.println("Monday Shift: " + row.getMondayShift());
            System.out.println("Tuesday Shift: " + row.getTuesdayShift());
            System.out.println("Wednesday Shift: " + row.getWednesdayShift());
            System.out.println("Thursday Shift: " + row.getThursdayShift());
            System.out.println("Friday Shift: " + row.getFridayShift());
            System.out.println("Saturday Shift: " + row.getSaturdayShift());
            System.out.println("Sunday Shift: " + row.getSundayShift());
            System.out.println("---------------------");
        }
        ObservableList<scheduleRow> sortedScheduleRows = sortRows(newScheduleRows);
        System.out.println("AFTER REORGANIZING");
        for (scheduleRow row : sortedScheduleRows) {
            System.out.println("Monday Shift: " + row.getMondayShift());
            System.out.println("Tuesday Shift: " + row.getTuesdayShift());
            System.out.println("Wednesday Shift: " + row.getWednesdayShift());
            System.out.println("Thursday Shift: " + row.getThursdayShift());
            System.out.println("Friday Shift: " + row.getFridayShift());
            System.out.println("Saturday Shift: " + row.getSaturdayShift());
            System.out.println("Sunday Shift: " + row.getSundayShift());
            System.out.println("---------------------");
        }
        scheduleTableView.setItems(sortedScheduleRows);
        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("mondayShift"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesdayShift"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesdayShift"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursdayShift"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("fridayShift"));
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<>("saturdayShift"));
        sundayColumn.setCellValueFactory(new PropertyValueFactory<>("sundayShift"));
    }    
    
    private void showAddShiftDialog(String day) {
        // Create the dialog
        Stage dialog = new Stage();
        dialog.setTitle("Add Shift for " + day);
    
        // Create text fields for entering start time and end time
        TextField startTimeField = new TextField();
        TextField endTimeField = new TextField();
    
        // Create a button to save the shift
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveShift(day, startTimeField.getText(), endTimeField.getText()));
        // Assign the saveButton to the field
        this.saveButton = saveButton;
        // Add the text fields and save button to the dialog layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Start Time: "), startTimeField,
                new Label("End Time: "), endTimeField,
                saveButton
        );
    
        // Show the dialog
        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.show();
    }   
    
    private void saveShift(String day, String startTime, String endTime) {
        // Find the DefaultSchedule object corresponding to the given day
        System.out.println("SAVING SHIFT");
        Integer scheduleID = DBController.addShiftDefaultSchedule(depName, day, startTime, endTime);
        if (scheduleID != -1) {
            System.out.println("USER HAS ADDED SHIFT");
            DefaultSchedule newShift = new DefaultSchedule(scheduleID, depID, day, startTime, endTime);
            // Update the corresponding list with the new shift information
            switch (day.toLowerCase()) {
                case "monday":
                    newShift.setMondayShift(Arrays.asList(startTime + " - " + endTime));
                    break;
                case "tuesday":
                    newShift.setTuesdayShift(Arrays.asList(startTime + " - " + endTime));
                    break;
                case "wednesday":
                    newShift.setWednesdayShift(Arrays.asList(startTime + " - " + endTime));
                    break;
                case "thursday":
                    newShift.setThursdayShift(Arrays.asList(startTime + " - " + endTime));
                    break;
                case "friday":
                    newShift.setFridayShift(Arrays.asList(startTime + " - " + endTime));
                    break;
                case "saturday":
                    newShift.setSaturdayShift(Arrays.asList(startTime + " - " + endTime));
                    break;
                case "sunday":
                    newShift.setSundayShift(Arrays.asList(startTime + " - " + endTime));
                    break;
                default:
                    break;
            }
            // Refresh the TableView to reflect the changes
            DefaultScheduleTable();
            
            // Close the dialog and show a confirmation message
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
    
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Shift Added");
            alert.setHeaderText(null);
            alert.setContentText("Shift for " + day + " saved successfully.");
            alert.showAndWait();
        } else {
            // Handle case when shift addition failed
            System.out.println("Failed to add shift.");
        }
    }    
    
    @FXML
    private void addShiftMonday() {
        showAddShiftDialog("Monday");
    }
    @FXML
    private void addShiftTuesday() {
        showAddShiftDialog("Tuesday");
    }
    @FXML
    private void addShiftWednesday() {
        showAddShiftDialog("Wednesday");
    }
    @FXML
    private void addShiftThursday() {
        showAddShiftDialog("Thursday");
    }
    @FXML
    private void addShiftFriday() {
        showAddShiftDialog("Friday");
    }
    @FXML
    private void addShiftSaturday() {
        showAddShiftDialog("Saturday");
    }
    @FXML
    private void addShiftSunday() {
        showAddShiftDialog("Sunday");
    }

    @FXML
    private void saveSchedule(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Default Department Schedule Added");
        alert.setHeaderText(null);
        alert.setContentText("Default Department Schedule Saved Succesfully");
        alert.showAndWait();
        loadFXML("main.fxml", event);
    }

    @FXML
    void homeButtonOnAction(ActionEvent event) throws IOException {
        loadFXML("main.fxml", event);
    }

    @FXML
    void createScheduleButtonOnAction(ActionEvent event) throws IOException {
        loadFXML("createschedules.fxml", event);
    }

    @FXML
    void editInformationButtonOnAction(ActionEvent event) throws IOException {
        loadFXML("editinformation.fxml", event);
    }

    @FXML
    void staffButtonOnAction(ActionEvent event) throws IOException {
        loadFXML("staff.fxml", event);
    }

    @FXML
    void departmentsButtonOnAction(ActionEvent event) throws IOException {
        loadFXML("departments.fxml", event);
    }

    @FXML
    void logoutButtonOnAction(ActionEvent event) throws IOException {
        // Implement logout functionality here
    }

    private void loadFXML(String fxmlFile, ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
        // Check if depName is available
        System.out.println("INITIALIZING");
        if (depName != null && !depName.isEmpty()) {
            DefaultScheduleTable();
            scheduleTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            // Set the cell value factories for each day column
            mondayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getMondayShift())));
            tuesdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getTuesdayShift())));
            wednesdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getWednesdayShift())));
            thursdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getThursdayShift())));
            fridayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getFridayShift())));
            saturdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getSaturdayShift())));
            sundayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getSundayShift())));
        } else {
            // depName is not available, display a message or take appropriate action
            System.out.println("Department name is not available. Cannot populate schedule table.");
            // Optionally, display an alert or message to the user indicating that department name is required.
        }
    });
    }
    public void initController() {
        System.out.println(depID);
        if (depName != null && !depName.isEmpty()) {
            DefaultScheduleTable();
            // Set cell value factories, etc.
        } else {
            System.out.println("Department name is not available. Cannot populate schedule table.");
            // Optionally, display an alert or message to the user indicating that department name is required.
        }
    }

}
