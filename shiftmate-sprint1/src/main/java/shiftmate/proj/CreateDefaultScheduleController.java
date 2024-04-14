
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
    public static List<List<String>> buildShiftLists(List<scheduleRow> scheduleRows) {
        List<List<String>> shiftLists = new ArrayList<>();
    
        // Determine the number of shifts in each row
        int numRows = scheduleRows.size();
    
        // Initialize lists for each shift position
        for (int i = 0; i < numRows; i++) {
            shiftLists.add(new ArrayList<>());
        }
    
        // Iterate through each row
        for (scheduleRow row : scheduleRows) {
            // Iterate through each shift position in the row
            if (!row.getMondayShift().isEmpty()) {
                shiftLists.get(0).add(row.getMondayShift());
            }
            if (!row.getTuesdayShift().isEmpty()) {
                shiftLists.get(1).add(row.getTuesdayShift());
            }
            if (!row.getWednesdayShift().isEmpty()) {
                shiftLists.get(2).add(row.getWednesdayShift());
            }
            if (!row.getThursdayShift().isEmpty()) {
                shiftLists.get(3).add(row.getThursdayShift());
            }
            if (!row.getFridayShift().isEmpty()) {
                shiftLists.get(4).add(row.getFridayShift());
            }
            if (!row.getSaturdayShift().isEmpty()) {
                shiftLists.get(5).add(row.getSaturdayShift());
            }
            if (!row.getSundayShift().isEmpty()) {
                shiftLists.get(6).add(row.getSundayShift());
            }
        }        
        return shiftLists;
    }
    public ObservableList<scheduleRow> convertToScheduleRows(List<List<String>> shiftLists) {
        ObservableList<scheduleRow> scheduleRows = FXCollections.observableArrayList();
        int numRows = 0;

        for (List<String> shiftList : shiftLists) {
            int size = shiftList.size();
            if (size > numRows) {
                numRows = size;
            }
        }
        for (int i = 0; i < numRows; i++) {
            String mondayShift = i < shiftLists.get(0).size() ? shiftLists.get(0).get(i) : "";
            String tuesdayShift = i < shiftLists.get(1).size() ? shiftLists.get(1).get(i) : "";
            String wednesdayShift = i < shiftLists.get(2).size() ? shiftLists.get(2).get(i) : "";
            String thursdayShift = i < shiftLists.get(3).size() ? shiftLists.get(3).get(i) : "";
            String fridayShift = i < shiftLists.get(4).size() ? shiftLists.get(4).get(i) : "";
            String saturdayShift = i < shiftLists.get(5).size() ? shiftLists.get(5).get(i) : "";
            String sundayShift = i < shiftLists.get(6).size() ? shiftLists.get(6).get(i) : "";
        
            scheduleRow row = new scheduleRow(mondayShift, tuesdayShift, wednesdayShift,
                                               thursdayShift, fridayShift, saturdayShift, sundayShift);
            scheduleRows.add(row);
        }        
        return scheduleRows;
    }
    public void setDepID(int depID) {
        this.depID = depID;
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
        // Sort the original observable list
        List<List<String>> shiftLists = buildShiftLists(scheduleRows);
        ObservableList<scheduleRow> organizedScheduleRows = convertToScheduleRows(shiftLists);
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
    private void editShiftDialog(scheduleRow selectedRow, String dayOfWeek) {
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
        System.out.println("SAVING SHIFT");
        Integer scheduleID = DBController.addShiftDefaultSchedule(depName, day, startTime, endTime);
        if (scheduleID != -1) {
            System.out.println("USER HAS ADDED SHIFT");
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
        System.out.println("SAVING SHIFT");
        String[] times = shiftDetails.split("-");

        // Remove leading and trailing whitespace from each time
        String oldStartTime = times[0].trim();
        String oldEndTime = times[1].trim();

        Boolean checkEdit = DBController.editShiftDefaultSchedule(depName,oldStartTime, oldEndTime, day, startTime, endTime);
        if (checkEdit) {
            System.out.println("USER HAS EDITED SHIFT");
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
            scheduleTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Check if a cell is selected
                TablePosition<scheduleRow, String> selectedCell = (TablePosition<scheduleRow, String>) scheduleTableView.getSelectionModel().getSelectedCells().get(0);
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
                scheduleRow selectedRow = selectedCell.getTableView().getItems().get(selectedCell.getRow());
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
            DefaultScheduleTable();
            // Set cell value factories, etc.
        } else {
            System.out.println("Department name is not available. Cannot populate schedule table.");
            // Optionally, display an alert or message to the user indicating that department name is required.
        }
    }
}