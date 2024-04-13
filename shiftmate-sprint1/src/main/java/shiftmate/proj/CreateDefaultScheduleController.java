package shiftmate.proj;

import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateDefaultScheduleController implements Initializable {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button saveScheduleButton;

    @FXML
    private TableView<DefaultSchedule> scheduleTableView;

    @FXML
    private TableColumn<DefaultSchedule, String> dayColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> shiftStartColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> shiftEndColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> mondayColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> tuesdayColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> wednesdayColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> thursdayColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> fridayColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> saturdayColumn;

    @FXML
    private TableColumn<DefaultSchedule, String> sundayColumn;

    private Button saveButton;

    private String depName;
    public void setDepName(String depName) {
        this.depName = depName;
    }

    public void DefaultScheduleTable() {
        System.out.println("Value in CreateDefaultScheduleController.java");
        System.out.println(depName);
        LinkedList<Hashtable<String, String>> defaultScheduleInformation = DBController.getDefaultSchedule(depName);
    
        ObservableList<DefaultSchedule> defaultScheduleList = FXCollections.observableArrayList();
    
        // Iterate over the default schedule information
        for (Hashtable<String, String> data : defaultScheduleInformation) {
            String scheduleID = data.get("scheduleID");
            String depID = data.get("depID");
            String dayOfWeek = data.get("dayOfWeek");
            String shiftStart = data.get("shiftStart");
            String shiftEnd = data.get("shiftEnd");
    
            // Create a DefaultSchedule object for each shift
            DefaultSchedule shift = new DefaultSchedule(Integer.parseInt(scheduleID), Integer.parseInt(depID), dayOfWeek, shiftStart, shiftEnd);
    
            // Add the shift to the list
            defaultScheduleList.add(shift);
        }
    
        // Set the items in the TableView
        scheduleTableView.setItems(defaultScheduleList);
    }    
    // Define the text fields for start time and end time
    private TextField startTimeField;
    private TextField endTimeField;

    // Define a method to show a dialog or popup window for adding a shift
    private void showAddShiftDialog(String day) {
        // Create the dialog
        Stage dialog = new Stage();
        dialog.setTitle("Add Shift for " + day);

        // Create text fields for entering start time and end time
        startTimeField = new TextField();
        endTimeField = new TextField();

        // Create a button to save the shift
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveShift(day));

        // Add the text fields and save button to the dialog layout
        // (You can use VBox, GridPane, or any other layout)
        // For simplicity, I'm using VBox in this example
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

    // Define a method to save the shift
    private void saveShift(String day) {
        // Retrieve the values entered in the text fields
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();

        // Save shift to database

        // Close the dialog
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

        // Show a confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Shift Added");
        alert.setHeaderText(null);
        alert.setContentText("Shift for " + day + " saved successfully.");
        alert.showAndWait();
    }
    @FXML
    private void addShiftMonday() {
        showAddShiftDialog("Monday");
    }
    @FXML
    private void addShiftTuesday() {
        // Logic for adding a shift on Monday
    }
    @FXML
    private void addShiftWednesday() {
        // Logic for adding a shift on Monday
    }
    @FXML
    private void addShiftThursday() {
        // Logic for adding a shift on Monday
    }
    @FXML
    private void addShiftFriday() {
        // Logic for adding a shift on Monday
    }
    @FXML
    private void addShiftSaturday() {
        // Logic for adding a shift on Monday
    }
    @FXML
    private void addShiftSunday() {
        // Logic for adding a shift on Monday
    }

    @FXML
    private void saveSchedule() {
        // Logic for saving the schedule
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
        // Populate the table with default schedule information
        Platform.runLater(() -> {
        DefaultScheduleTable();
        
        // Initialize the cell value factories for each column
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("dayOfWeek"));
        shiftStartColumn.setCellValueFactory(new PropertyValueFactory<>("shiftStart"));
        shiftEndColumn.setCellValueFactory(new PropertyValueFactory<>("shiftEnd"));
    });
}
}
