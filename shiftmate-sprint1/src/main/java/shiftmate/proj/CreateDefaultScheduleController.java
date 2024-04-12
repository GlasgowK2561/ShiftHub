package shiftmate.proj;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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

    public void DefaultScheduleTable() {
        LinkedList<Hashtable<String, String>> defaultScheduleInformation = DBController.getDefaultSchedule();
    
        ObservableList<DefaultSchedule> defaultScheduleList = FXCollections.observableArrayList();
    
        for (int i = 0; i < defaultScheduleInformation.size(); i++) {
            Hashtable<String, String> data = defaultScheduleInformation.get(i);
            int scheduleID = Integer.parseInt(data.get("scheduleID"));
            int depID = Integer.parseInt(data.get("depID"));
            String dayOfWeek = data.get("dayOfWeek");
            String shiftStart = data.get("shiftStart");
            String shiftEnd = data.get("shiftEnd");
    
            // Create a DefaultSchedule object for each shift
            DefaultSchedule shift = new DefaultSchedule(scheduleID, depID, dayOfWeek, shiftStart, shiftEnd);
            
            // Add the shift to the list
            defaultScheduleList.add(shift);
        }
    
        // Set the items in the TableView
        scheduleTableView.setItems(defaultScheduleList);
    }
    
    @FXML
    private void addShiftMonday() {
        // Logic for adding a shift on Monday
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DefaultScheduleTable();
    }
}
