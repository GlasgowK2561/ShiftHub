
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
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
    TableView<ScheduleRow> scheduleTableView = new TableView<>();
    
    @FXML
    private TableColumn<ScheduleRow, String> mondayColumn;
    
    @FXML
    private TableColumn<ScheduleRow, String> tuesdayColumn;
    
    @FXML
    private TableColumn<ScheduleRow, String> wednesdayColumn;
    
    @FXML
    private TableColumn<ScheduleRow, String> thursdayColumn;
    
    @FXML
    private TableColumn<ScheduleRow, String> fridayColumn;
    
    @FXML
    private TableColumn<ScheduleRow, String> saturdayColumn;
    
    @FXML
    private TableColumn<ScheduleRow, String> sundayColumn;

    private Button saveButton;

    private String depName;

    public void setDepName(String depName) {
        this.depName = depName;
    }
    public static ObservableList<ScheduleRow> buildShiftLists(List<ScheduleRow> scheduleRows) {
        ObservableList<ScheduleRow> newScheduleRows = FXCollections.observableArrayList();
        List<String> mondayShifts =  new ArrayList<>(); 
        List<String> tuesdayShifts =  new ArrayList<>(); 
        List<String> wednesdayShifts = new ArrayList<>();
        List<String> thursdayShifts = new ArrayList<>();
        List<String> fridayShifts = new ArrayList<>();
        List<String> saturdayShifts = new ArrayList<>();
        List<String> sundayShifts = new ArrayList<>();
        String mondayShift, tuesdayShift, wednesdayShift, thursdayShift, fridayShift, saturdayShift, sundayShift;

        // Determine the number of shifts in each row
        int numRows = scheduleRows.size();
        
        // Iterate through each row
        for (ScheduleRow row : scheduleRows) {
            // Iterate through each shift position in the row
            if (!row.getMondayShift().isEmpty()) {
                mondayShifts.add(row.getMondayShift());
            }
            if (!row.getTuesdayShift().isEmpty()) {
                tuesdayShifts.add(row.getTuesdayShift());
            }
            if (!row.getWednesdayShift().isEmpty()) {
                wednesdayShifts.add(row.getWednesdayShift());
            }
            if (!row.getThursdayShift().isEmpty()) {
                thursdayShifts.add(row.getThursdayShift());
            }
            if (!row.getFridayShift().isEmpty()) {
                fridayShifts.add(row.getFridayShift());
            }
            if (!row.getSaturdayShift().isEmpty()) {
                saturdayShifts.add(row.getSaturdayShift());
            }
            if (!row.getSundayShift().isEmpty()) {
                sundayShifts.add(row.getSundayShift());
            }
        }        
        for (int i = 0; i < numRows; i++) {        
            // Check if Monday shift is available
            if (mondayShifts.size() > i && !mondayShifts.get(i).isEmpty()) {
                mondayShift = mondayShifts.get(i);
            } else {
                mondayShift = "";
            }
            // Check if Tuesday shift is available
            if (tuesdayShifts.size() > i && !tuesdayShifts.get(i).isEmpty()) {
                tuesdayShift = tuesdayShifts.get(i);
            } else {
                tuesdayShift = "";
            }
            // Check if Wednesday shift is available
            if (wednesdayShifts.size() > i && !wednesdayShifts.get(i).isEmpty()) {
                wednesdayShift = wednesdayShifts.get(i);
            } else {
                wednesdayShift = "";
            }
            // Check if Thursday shift is available
            if (thursdayShifts.size() > i && !thursdayShifts.get(i).isEmpty()) {
                thursdayShift = thursdayShifts.get(i);
            } else {
                thursdayShift = "";
            }
            // Check if Friday shift is available
            if (fridayShifts.size() > i && !fridayShifts.get(i).isEmpty()) {
                fridayShift = fridayShifts.get(i);
            } else {
                fridayShift = "";
            }
            // Check if Saturday shift is available
            if (saturdayShifts.size() > i && !saturdayShifts.get(i).isEmpty()) {
                saturdayShift = saturdayShifts.get(i);
            } else {
                saturdayShift = "";
            }
            // Check if Sunday shift is available
            if (sundayShifts.size() > i && !sundayShifts.get(i).isEmpty()) {
                sundayShift = sundayShifts.get(i);
            } else {
                sundayShift = "";
            }
            // Create a new WeeklyScheduleRow object with shifts for each day
            ScheduleRow row = new ScheduleRow(mondayShift, tuesdayShift, wednesdayShift,
                                                           thursdayShift, fridayShift, saturdayShift, sundayShift);
            newScheduleRows.add(row);
        }        
        return newScheduleRows;
    }

    public void DefaultScheduleTable() {
        scheduleTableView.getItems().clear();
    
        LinkedList<Hashtable<String, String>> defaultScheduleInformation = DBController.getDefaultSchedule(depName);
        ObservableList<ScheduleRow> scheduleRows = FXCollections.observableArrayList();
        for (Hashtable<String, String> data : defaultScheduleInformation) {
            String dayOfWeek = data.get("DayOfWeek");
            String shiftStart = data.get("StartTime");
            String shiftEnd = data.get("EndTime");
            // Create a new scheduleRow instance
            ScheduleRow row = new ScheduleRow("", "", "", "", "", "", "");
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
        // Sort the original observable list
        ObservableList<ScheduleRow> organizedScheduleRows = buildShiftLists(scheduleRows);
        // Populate the TableView
        scheduleTableView.setItems(organizedScheduleRows);
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
        Label userHelpLabel = new Label("Please use HH:MM:SS format in 24 time");
        // Create a button to save the shift
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveShift(day, startTimeField.getText(), endTimeField.getText()));
        // Assign the saveButton to the field
        this.saveButton = saveButton;
        // Add the text fields and save button to the dialog layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                userHelpLabel,
                new Label("Start Time: "), startTimeField,
                new Label("End Time: "), endTimeField,
                saveButton
        );
    
        // Show the dialog
        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.show();
    }   
    private void editShiftDialog(ScheduleRow selectedRow, String dayOfWeek) {
        // Create the dialog
        Stage dialog = new Stage();
        dialog.setTitle("Edit shift for " + dayOfWeek);
        Label currentShiftLabel = new Label("Current Shift: " + selectedRow.getShift(dayOfWeek));
        // Create text fields for entering start time and end time
        TextField startTimeField = new TextField();
        TextField endTimeField = new TextField();
        Label userHelpLabel = new Label("Please use HH:MM:SS format in 24 time");
        // Create a button to save the shift
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> editShift(selectedRow.getShift(dayOfWeek), dayOfWeek, startTimeField.getText(), endTimeField.getText()));
        // Assign the saveButton to the field
        this.saveButton = saveButton;
        // Add the text fields and save button to the dialog layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                userHelpLabel,
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
    private void saveShift(String day, String startTime, String endTime) {
        // Find the DefaultSchedule object corresponding to the given day
        Integer scheduleID = DBController.addShiftDefaultSchedule(depName, day, startTime, endTime);
        if (scheduleID != -1) {
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
    private void editShift(String shiftDetails, String day, String startTime, String endTime) {
        // Find the DefaultSchedule object corresponding to the given day
        String[] times = shiftDetails.split("-");
        // Remove leading and trailing whitespace from each time
        String oldStartTime = times[0].trim();
        String oldEndTime = times[1].trim();

        Boolean checkEdit = DBController.editShiftDefaultSchedule(depName,oldStartTime, oldEndTime, day, startTime, endTime);
        if (checkEdit) {
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
                saveShift(day, startTime, endTime);
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
            scheduleTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Check if a cell is selected
                TablePosition<ScheduleRow, String> selectedCell = (TablePosition<ScheduleRow, String>) scheduleTableView.getSelectionModel().getSelectedCells().get(0);
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
                ScheduleRow selectedRow = selectedCell.getTableView().getItems().get(selectedCell.getRow());
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
        if (depName != null && !depName.isEmpty()) {
            DefaultScheduleTable();
            // Set cell value factories, etc.
        } else {
            System.out.println("Department name is not available. Cannot populate schedule table.");
            // Optionally, display an alert or message to the user indicating that department name is required.
        }
    }
}