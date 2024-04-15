package shiftmate.proj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ResourceBundle;



public class MainController implements Initializable
{
    Stage stage;

    Parent scene;

    @FXML
    private TableView<WeeklyScheduleRow> schedulesTableView;
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

    @FXML
    private ComboBox<Departments> departmentComboBox;
    @FXML
    private Label dateRangeLabel;


    private void populateWeeklyScheduleTable(String depName) {
        schedulesTableView.getItems().clear();
        LinkedList<Hashtable<String, String>> weeklyScheduleInformation = DBController.getWeeklySchedule(depName);
        ObservableList<WeeklyScheduleRow> weeklyScheduleRows = FXCollections.observableArrayList();
        for (Hashtable<String, String> data : weeklyScheduleInformation) {
            String dayOfWeek = data.get("DayOfWeek");
            String employeeFName = data.get("fname");
            String employeeLName = data.get("lname");
            String shiftStart = data.get("StartTime");
            String shiftEnd = data.get("EndTime");
            // Create a new WeeklyScheduleRow instance
            WeeklyScheduleRow row = new WeeklyScheduleRow("", "", "", "", "", "", "");
            // Set the shift for the corresponding day of the week
            switch (dayOfWeek.toLowerCase()) {
                case "monday":
                    row.setMondayShift(employeeFName + " " + employeeLName + "\n" + shiftStart + " - " + shiftEnd);
                    break;
                case "tuesday":
                    row.setTuesdayShift(employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "wednesday":
                    row.setWednesdayShift(employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "thursday":
                    row.setThursdayShift(employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "friday":
                    row.setFridayShift(employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "saturday":
                    row.setSaturdayShift(employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                case "sunday":
                    row.setSundayShift(employeeFName + " " + employeeLName + "\n" +shiftStart + " - " + shiftEnd);
                    break;
                default:
                    break;
            }
            // Add the schedule row to the list
            weeklyScheduleRows.add(row);
        }
        schedulesTableView.setItems(weeklyScheduleRows);
        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("mondayShift"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesdayShift"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesdayShift"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursdayShift"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("fridayShift"));
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<>("saturdayShift"));
        sundayColumn.setCellValueFactory(new PropertyValueFactory<>("sundayShift"));
    }
    
    private void setColumnHeaders() 
    {
        LocalDate currentDate = LocalDate.now();
        String mondayHeader = "Monday " + currentDate.format(DateTimeFormatter.ofPattern("M/d"));
        String tuesdayHeader = "Tuesday " + currentDate.plusDays(1).format(DateTimeFormatter.ofPattern("M/d"));
        String wednesdayHeader = "Wednesday " + currentDate.plusDays(2).format(DateTimeFormatter.ofPattern("M/d"));
        String thursdayHeader = "Thursday " + currentDate.plusDays(3).format(DateTimeFormatter.ofPattern("M/d"));
        String fridayHeader = "Friday " + currentDate.plusDays(4).format(DateTimeFormatter.ofPattern("M/d"));
        String saturdayHeader = "Saturday " + currentDate.plusDays(5).format(DateTimeFormatter.ofPattern("M/d"));
        String sundayHeader = "Sunday " + currentDate.plusDays(6).format(DateTimeFormatter.ofPattern("M/d"));
        
        mondayColumn.setText(mondayHeader);
        tuesdayColumn.setText(tuesdayHeader);
        wednesdayColumn.setText(wednesdayHeader);
        thursdayColumn.setText(thursdayHeader);
        fridayColumn.setText(fridayHeader);
        saturdayColumn.setText(saturdayHeader);
        sundayColumn.setText(sundayHeader);
    }

    @FXML
    private void previousScheduleOnAction(ActionEvent event)
    {

    }

    @FXML
    private void nextScheduleOnAction(ActionEvent event)
    {

    }

    private void departmentsComboBox() 
    { 
       
        LinkedList<Hashtable<String, String>> departmentsList = DBController.getDepartments();
   
        ObservableList<Departments> departmentNames = FXCollections.observableArrayList();

        for (int i = 0; i < departmentsList.size(); i++)                
        {
            Hashtable<String,String> data = departmentsList.get(i);
        
            String depName = data.get("depName");
            Departments dep = new Departments(0, depName, depName); 
            departmentNames.add(dep);
        }
        departmentComboBox.setItems(departmentNames);

        if (!departmentNames.isEmpty())
        {
            departmentComboBox.getSelectionModel().selectFirst();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        departmentsComboBox();
        departmentComboBox.setOnAction(event -> 
        {
            Departments selectedDepartment = departmentComboBox.getSelectionModel().getSelectedItem();

            if (selectedDepartment != null) 
            {
                String depName = selectedDepartment.getDepName();

                LocalDate currentDate = LocalDate.now();
                LocalDate startDate = currentDate.with(java.time.DayOfWeek.MONDAY);
                LocalDate endDate = startDate.plusDays(6);
                String dateRangeText = startDate.format(DateTimeFormatter.ofPattern("MMMM/d")) + "   -   " +
                endDate.format(DateTimeFormatter.ofPattern("MMMM/d"));
                dateRangeLabel.setText(dateRangeText);

                setColumnHeaders();
                populateWeeklyScheduleTable(depName);
            }

        });

      
    }
    
    @FXML
    void homeButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void createScheduleButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("createWeeklySchedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @FXML
    void editInformationButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("editinformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @FXML
    void staffButtonOnAction (ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("staff.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void departmentsButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("departments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @FXML
    void logoutButtonOnAction(ActionEvent event) throws IOException
    {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
