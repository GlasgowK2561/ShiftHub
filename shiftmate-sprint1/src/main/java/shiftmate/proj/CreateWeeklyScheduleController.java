package shiftmate.proj;
// Imports
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CreateWeeklyScheduleController implements Initializable
{
    Stage stage;
    Parent scene;
    // Initialize the table 
    @FXML
    private TableView <Departments> departmentTableView;
    // Initialzie the table columns, one for the department name and one for the department manager
    @FXML
    private TableColumn<Departments, String> depNamecolumn;
    @FXML
    private TableColumn<Departments, String> depManagercolumn;
    @FXML
    // Global variable for button
    private Button createWeeklyScheduleOnAction;
    // Populates the Department Table with details from DB -- Written By: Elmer
    public void DepartmentTable() 
    {
        // Get information from the database
        LinkedList<Hashtable<String,String>> departmentInformation = DBController.getDepartments();
        ObservableList<Departments> departmentList = FXCollections.observableArrayList();
        // Convert information into an observable list by iterating through departmentInformation
        for (int i = 0; i < departmentInformation.size(); i++)                
        {
            Hashtable<String,String> data = departmentInformation.get(i);
            int depID = Integer.parseInt(data.get("depID"));
            String depName = data.get("depName");
            String depManager = data.get("depManager");
            departmentList.add(new Departments(depID, depName, depManager));
            // Set the values to the table
            departmentTableView.setItems(departmentList);
            depNamecolumn.setCellValueFactory(new PropertyValueFactory<>("depName"));
            depManagercolumn.setCellValueFactory(new PropertyValueFactory<>("depManager"));
        }
    }
    // Button to create weekly schedule -- Written By: Elmer
    @FXML
    private void createWeeklyScheduleOnAction(ActionEvent event) {
        // Get the selected department information
        int selectedDepID = departmentTableView.getSelectionModel().getSelectedItem().getDepID();
        String selectedDepName = departmentTableView.getSelectionModel().getSelectedItem().getDepName();
        Alert confirmCreateSchedule = new Alert(AlertType.CONFIRMATION);
        // Confirm selection with user
        confirmCreateSchedule.setTitle("Create Weekly Schedule");
        confirmCreateSchedule.setHeaderText(null);
        confirmCreateSchedule.setContentText("Do you want to create a new weekly schedule for the selected department?");
        ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonData.NO);
        confirmCreateSchedule.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = confirmCreateSchedule.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            // Create weekly schedule if user agrees
            Boolean checkTableCreation = DBController.createWeeklyScheduleTable(selectedDepName);
            if (checkTableCreation){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("editWeeklySchedule.fxml"));
                    Parent root = loader.load();
                    EditWeeklyScheduleController controller = loader.getController();
                    controller.setDepID(selectedDepID);
                    controller.setDepName(selectedDepName);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Button to go to home page -- Written By: Elmer
    @FXML
    void homeButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // Button to go to create schedule -- Written By: Elmer
    @FXML
    void createScheduleButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("createWeeklySchedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
     // Button to go to edit staff information -- Written By: Elmer
    @FXML
    void editInformationButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("editinformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // Button to go to view staff -- Written By: Elmer  
    @FXML
    void staffButtonOnAction (ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("staff.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // Button to go view departments -- Written By: Elmer
    @FXML
    void departmentsButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("departments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // Button to go log out -- Written By: Elmer
    @FXML
    void logoutButtonOnAction(ActionEvent event) throws IOException
    {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    // Helper function to build alert window -- Written By: Elmer   
    void AlertWindow(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Helper function to build alert window -- Written By: Elmer
    void AlertWindow(String title, String message, String headerText, AlertType alertType) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.show(); // Change showAndWait() to show()
    }
   // Helper function to intialize the page values -- Written By: Elmer 
    public void initialize(URL url, ResourceBundle rb)
    {
        DepartmentTable();
    } 
}