package shiftmate.proj;

// Imports
import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.SelectionMode;

public class EditWeeklyScheduleController implements Initializable {

    @FXML
    private TableView<WeeklyScheduleRow> weeklyScheduleTableView;

    @FXML
    private TableColumn<WeeklyScheduleRow, String> mondayColumn;

    @FXML
    private TableColumn<WeeklyScheduleRow, String> tuesdayColumn;

    @FXML
    private TableColumn<WeeklyScheduleRow, String> wednesdayColumn;

    @FXML
    private TableColumn<WeeklyScheduleRow, String> thursdayColumn;

    @FXML
    private TableColumn<WeeklyScheduleRow, String> fridayColumn;

    @FXML
    private TableColumn<WeeklyScheduleRow, String> saturdayColumn;

    @FXML
    private TableColumn<WeeklyScheduleRow, String> sundayColumn;
    private Button saveButton;
    private String depName;
    private int depID;
    public void setDepName(String depName) {
        this.depName = depName;
    }
    public void setDepID(int depID) {
        this.depID = depID;
    }
    private ObservableList<EmployeeInfo> getEmployeeList() {
        LinkedList<Hashtable<String,String>> employees = DBController.getDepartmentEmployees(depID);
        ObservableList<EmployeeInfo> employeeList = FXCollections.observableArrayList();
    
        for (int i = 0; i < employees.size(); i++) {
            Hashtable<String,String> data = employees.get(i);
            int employeeID = Integer.parseInt(data.get("employeeID"));
            int departmentID = Integer.parseInt(data.get("depID")); // Assuming depID is the department ID
            String fname = data.get("fname");
            String lname = data.get("lname");
            String email = data.get("email");
            String phone = data.get("phone");
            String startDate = data.get("startDate");
            String contact = data.get("contact");
            String contactPhone = data.get("contactPhone");
            employeeList.add(new EmployeeInfo(employeeID, departmentID, fname, lname, email, phone, startDate, contact, contactPhone));
        }
        
        return employeeList;
    }
    
    private void populateWeeklyScheduleTable() {
        weeklyScheduleTableView.getItems().clear();

        LinkedList<Hashtable<String, String>> weeklyScheduleInformation = DBController.getWeeklySchedule(depName);
        ObservableList<WeeklyScheduleRow> weeklyScheduleRows = FXCollections.observableArrayList();
        for (Hashtable<String, String> data : weeklyScheduleInformation) {
            String dayOfWeek = data.get("DayOfWeek");
            String employeeFName = data.get("FName");
            String employeeLName = data.get("LName");
            String employeeID = data.get("EmployeeID");
            String shiftStart = data.get("StartTime");
            String shiftEnd = data.get("EndTime");
            // Create a new WeeklyScheduleRow instance
            WeeklyScheduleRow row = new WeeklyScheduleRow("", "", "", "", "", "", "");
            // Set the shift for the corresponding day of the week
            switch (dayOfWeek.toLowerCase()) {
                case "monday":
                    row.setMondayShift(employeeID + ":" + employeeFName + " " + employeeLName + "\n" + shiftStart + " - " + shiftEnd);
                    break;
                case "tuesday":
                    row.setTuesdayShift(employeeID + ":" + employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "wednesday":
                    row.setWednesdayShift(employeeID + ":" + employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "thursday":
                    row.setThursdayShift(employeeID + ":" + employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "friday":
                    row.setFridayShift(employeeID + ":" + employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "saturday":
                    row.setSaturdayShift(employeeID + ":" + employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "sunday":
                    row.setSundayShift(employeeID + ":" + employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                default:
                    break;
            }
            // Add the schedule row to the list
            weeklyScheduleRows.add(row);
        }
        // Populate the TableView
        weeklyScheduleTableView.setItems(weeklyScheduleRows);
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
    
        // Create a dropdown menu for selecting an employee
        ComboBox<EmployeeInfo> employeeDropdown = new ComboBox<>();
        // Retrieve the list of employees for the department and populate the dropdown
        ObservableList<EmployeeInfo> employeeList = getEmployeeList();
        employeeDropdown.setItems(employeeList);
    
        // Create text fields for entering start time and end time
        TextField startTimeField = new TextField();
        TextField endTimeField = new TextField();
        Label userHelpLabel = new Label("Please use HH:MM:SS format in 24 time");
    
        // Create a button to save the shift
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            EmployeeInfo selectedEmployee = employeeDropdown.getValue();
            if (selectedEmployee != null) {
                saveShift(day, selectedEmployee.getEmployeeID(), startTimeField.getText(), endTimeField.getText());
            } else {
                // Handle case when no employee is selected
                System.out.println("Please select an employee.");
            }
        });
    
        // Add the text fields, dropdown menu, and save button to the dialog layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                userHelpLabel,
                new Label("Employee: "), employeeDropdown,
                new Label("Start Time: "), startTimeField,
                new Label("End Time: "), endTimeField,
                saveButton
        );
    
        // Show the dialog
        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.show();
    }
    
    // Modify editShiftDialog to parse the selected employee and pass the employee ID to saveShift method
    private void editShiftDialog(WeeklyScheduleRow selectedRow, String dayOfWeek) {
        // Create the dialog
        Stage dialog = new Stage();
        dialog.setTitle("Edit shift for " + dayOfWeek);
    
        // Create a dropdown menu for selecting an employee
        ComboBox<EmployeeInfo> employeeDropdown = new ComboBox<>();
        // Retrieve the list of employees for the department and populate the dropdown
        ObservableList<EmployeeInfo> employeeList = getEmployeeList();
        employeeDropdown.setItems(employeeList);
    
        EmployeeInfo[] selectedEmployee = new EmployeeInfo[1]; // Array to hold the selectedEmployee
    
        int selectedEmployeeID = selectedRow.getEmployeeID(dayOfWeek);
        for (EmployeeInfo employee : employeeList) {
            if (employee.getEmployeeID() == selectedEmployeeID) {
                selectedEmployee[0] = employee; // Set the selectedEmployee in the array
                break;
            }
        }
        employeeDropdown.getSelectionModel().select(selectedEmployee[0]);
        
        Label currentShiftLabel = new Label("Current Shift: " + selectedRow.getShift(dayOfWeek));
        // Create text fields for entering start time and end time
        TextField startTimeField = new TextField();
        TextField endTimeField = new TextField();
        Label userHelpLabel = new Label("Please use HH:MM:SS format in 24 time");
        // Create a button to save the shift
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            EmployeeInfo selected = employeeDropdown.getValue();
            if (selected != null) {
                editShift(selectedRow.getShift(dayOfWeek), dayOfWeek, selected.getEmployeeID(), startTimeField.getText(), endTimeField.getText());
            } else {
                // Handle case when no employee is selected
                System.out.println("No employee selected.");
            }
        });
        // Assign the saveButton to the field
        this.saveButton = saveButton;
        // Add the text fields and save button to the dialog layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                userHelpLabel,
                new Label("Employee: "), employeeDropdown,
                currentShiftLabel,
                new Label("Start Time: "), startTimeField,
                new Label("End Time: "), endTimeField,
                saveButton
        );
    
        // Show the dialog
        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.show();
    }
    
    private void saveShift(String day, int employeeID, String startTime, String endTime) {
            System.out.println("SAVING SHIFT");
            Integer scheduleID = DBController.addShiftWeeklySchedule(depName, employeeID, day, startTime, endTime);
            if (scheduleID != -1) {
                System.out.println("USER HAS ADDED SHIFT");
                // Refresh the TableView to reflect the changes
                populateWeeklyScheduleTable();
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
    
    private void editShift(String shiftDetails, String day, int newEmployeeID, String startTime, String endTime) {
        System.out.println("SAVING SHIFT");
        String[] parts = shiftDetails.split(":"); // Split the string by ":" to separate employee ID and the rest of the information
        int oldemployeeID = Integer.parseInt(parts[0]); // Convert the first part (employee ID) to an integer

        // Extract the rest of the information
        String employeeInfo = parts[1].trim(); // Second part contains the rest of the information

        // Further split the employeeInfo string to extract employee's first name, last name, and shift details
        String[] employeeInfoParts = employeeInfo.split("\n"); // Split by newline to separate name and shift details
        String[] nameParts = employeeInfoParts[0].split(" "); // Split by space to separate first name and last name

        // Extract old start time and old end time
        String oldStartTime = employeeInfoParts[1].split("-")[0].trim(); // Extract start time from shift details
        String oldEndTime = employeeInfoParts[1].split("-")[1].trim(); // Extract end time from shift details

        boolean checkEdit = DBController.editShiftWeeklySchedule(depName, oldemployeeID, oldStartTime, oldEndTime, day, newEmployeeID, startTime, endTime);
        if (checkEdit) {
            System.out.println("USER HAS EDITED SHIFT");
            populateWeeklyScheduleTable();
            // Close the dialog and show a confirmation message
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Shift Edited");
            alert.setHeaderText(null);
            alert.setContentText("Shift for " + day + " edited successfully.");
            alert.showAndWait();
        } else {
            // If shift doesn't exist, ask if the user wants to add a new shift
            Alert confirmAddShift = new Alert(AlertType.CONFIRMATION);
            confirmAddShift.setTitle("Shift Not Found");
            confirmAddShift.setHeaderText(null);
            confirmAddShift.setContentText("Shift does not exist. Do you want to add a new shift?");
            
            ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonData.NO);
            confirmAddShift.getButtonTypes().setAll(yesButton, noButton);
            
            Optional<ButtonType> result = confirmAddShift.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                // User wants to add a new shift
                saveShift(day, newEmployeeID, startTime, endTime);
            } else {
                // User chose not to add a new shift
                System.out.println("Shift was not added to schedule.");
            }
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
        alert.setTitle("Weekly Schedule Added");
        alert.setHeaderText(null);
        alert.setContentText("Weekly Schedule Saved Succesfully");
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
            populateWeeklyScheduleTable();
            weeklyScheduleTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            // Set the cell value factories for each day column
            mondayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getMondayShift())));
            tuesdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getTuesdayShift())));
            wednesdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getWednesdayShift())));
            thursdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getThursdayShift())));
            fridayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getFridayShift())));
            saturdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getSaturdayShift())));
            sundayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.join("\n", cellData.getValue().getSundayShift())));
            weeklyScheduleTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Check if a cell is selected
                TablePosition<WeeklyScheduleRow, String> selectedCell = (TablePosition<WeeklyScheduleRow, String>) weeklyScheduleTableView.getSelectionModel().getSelectedCells().get(0);
                int columnIndex = selectedCell.getColumn();
                String dayOfWeek = ""; // Determine the day of the week based on the column index
                switch (columnIndex) {
                    case 0:
                        dayOfWeek = "Monday";
                        break;
                    case 1:
                        dayOfWeek = "Tuesday";
                        break;
                    case 2:
                        dayOfWeek = "Wednesday";
                        break;
                    case 3:
                        dayOfWeek = "Thursday";
                        break;
                    case 4:
                        dayOfWeek = "Friday";
                        break;
                    case 5:
                        dayOfWeek = "Saturday";
                        break;
                    case 6:
                        dayOfWeek = "Sunday";
                        break;
                    default:
                        break;
                }
                // Get the selected row
                WeeklyScheduleRow selectedRow = selectedCell.getTableView().getItems().get(selectedCell.getRow());
                // Edit the selected shift
                editShiftDialog(selectedRow, dayOfWeek);
            }
});

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
            populateWeeklyScheduleTable();
            // Set cell value factories, etc.
        } else {
            System.out.println("Department name is not available. Cannot populate schedule table.");
            // Optionally, display an alert or message to the user indicating that department name is required.
        }
    }
}
