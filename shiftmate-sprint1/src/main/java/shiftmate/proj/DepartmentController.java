package shiftmate.proj;
// Imports
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
import javafx.stage.Stage;

public class DepartmentController implements Initializable
{
    Stage stage;

    Parent scene;
    // Define the table
    @FXML
    private TableView <Departments> departmentTableView;
    // Define the table columns
    @FXML
    private TableColumn<Departments, String> depNamecolumn;
    @FXML
    private TableColumn<Departments, String> depManagercolumn;
    // Define the text fields
    @FXML
    private TextField depIDTextField;
    @FXML
    private TextField depNameTextField;
    @FXML 
    private TextField depManagerTextField;
    // Populate the department table -- Written By: Elmer
    public void DepartmentTable() 
    {
        LinkedList<Hashtable<String,String>> departmentInformation = DBController.getDepartments();
        ObservableList<Departments> departmentList = FXCollections.observableArrayList();
        for (int i = 0; i < departmentInformation.size(); i++)                
        {
            Hashtable<String,String> data = departmentInformation.get(i);
            int depID = Integer.parseInt(data.get("depID"));
            String depName = data.get("depName");
            String depManager = data.get("depManager");
            departmentList.add(new Departments(depID, depName, depManager));
            departmentTableView.setItems(departmentList);
            depNamecolumn.setCellValueFactory(new PropertyValueFactory<>("depName"));
            depManagercolumn.setCellValueFactory(new PropertyValueFactory<>("depManager"));
        }
    }
    // Add department button -- Written by: Elmer
    @FXML
    void addDepartmentOnAction(ActionEvent event) throws IOException {
        // Get text from TextField
        String depName = depNameTextField.getText();
        String depManager = depManagerTextField.getText();
        if (depName.isEmpty() || depManager.isEmpty()) {
            StringBuffer missingField = new StringBuffer("Missing: ");

            if (depName.isEmpty()) {
                missingField.append("  Department Name  ");
            }
            if (depManager.isEmpty()) {
                missingField.append("  Department Manager ");
            }
            AlertWindow("Missing Information", missingField.toString(), " ", AlertType.NONE);
        } else {
            // Call method to add department to the database
            boolean valid = DBController.addDepartment(depName, depManager);
            if (valid) {
                AlertWindow("Success", "You have successfully added a department");
                // Load the new JavaFX page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("createDefaultSchedule.fxml"));
                Parent root = loader.load();
                CreateDefaultScheduleController controller = loader.getController();
                controller.setDepName(depName); // Set the department name
                controller.initController(); // Initialize the controller manually
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                AlertWindow("Fail", "Failed to add department");
            }
        }
    }
    // Delete department button - Written by: Elmer
    @FXML
    void deleteDepartmentOnAction (ActionEvent event) throws IOException
    {
        Departments slctdDep = departmentTableView.getSelectionModel().getSelectedItem();

        if(slctdDep == null)
        {
            AlertWindow("No department selected", "Please select a department to delete");
            return;
        
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText("Delete Department");
        confirmAlert.setContentText("Are you sure you want to delete the selected department?");
        confirmAlert.showAndWait().ifPresent(response ->
        {
            if (response == ButtonType.OK)
            {
                boolean delete = DBController.deleteDepartment(slctdDep.getDepID(), slctdDep.getDepName());
                if (delete)
                {
                    AlertWindow("Success", "Department has been deleted successfully");
                    DepartmentTable();
                }
                else
                {
                    AlertWindow("Fail", "Failed to delete department");
                }
            }
        });
    }
    // Edit schedule button -- Written by: Elmer
    @FXML
    void editScheduleOnAction(ActionEvent event) throws IOException {
        Departments slctdDep = departmentTableView.getSelectionModel().getSelectedItem();
        if (slctdDep != null) {
            String depName = slctdDep.getDepName();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createDefaultSchedule.fxml"));
            Parent root = loader.load();
            CreateDefaultScheduleController controller = loader.getController();
            controller.setDepName(depName);
            controller.initController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            AlertWindow("Select Department", "Please select a department you'd like to edit");
        }
    }    
    // edit department. Select the department you'd like to edit and click edit button. The Text will go into the TextBoxes -- Written By: Elmer
    @FXML
    void editDepartmentOnAction(ActionEvent event) throws IOException     
    {
        Departments slctdDep = departmentTableView.getSelectionModel().getSelectedItem();

        if (slctdDep != null)
        {
            int depID = slctdDep.getDepID();        

            depIDTextField.setText(String.valueOf(depID));
            depNameTextField.setText(slctdDep.getDepName());
            depManagerTextField.setText(slctdDep.getDepManager());
        }
        else
        {
            AlertWindow("Select Department", "Please select a department you'd like to edit");
        }
    }
    // Save department button -- Written by: Elmer
    @FXML
    void saveDepartmentOnAction(ActionEvent event) throws IOException {
        Departments selectedDepartment = departmentTableView.getSelectionModel().getSelectedItem();
        String depIDText = depIDTextField.getText();
        String depName = depNameTextField.getText();
        String depManager = depManagerTextField.getText();
        boolean isDepartmentNameChanged = !depName.equals(selectedDepartment.getDepName());
        // Check if any required field is empty
        if (depIDText.isEmpty() || depName.isEmpty() || depManager.isEmpty()) {
            StringBuffer missingField = new StringBuffer("Missing: ");
    
            if (depIDText.isEmpty()) {
                missingField.append("  Department ID  ");
            }
            if (depName.isEmpty()) {
                missingField.append("  Department Name  ");
            }
            if (depManager.isEmpty()) {
                missingField.append("  Department Manager ");
            }
    
            AlertWindow("Missing Information", missingField.toString(), " ", AlertType.NONE);
        } else {
            if (selectedDepartment != null) {
                int depID = Integer.parseInt(depIDText);
    
                // Call method to update department information in the database
                boolean updated = DBController.editDepartmentInformation(depID, depName, depManager);
                if (updated) {
                    if (isDepartmentNameChanged) {
                        // If department name is changed, update the corresponding default shift table name
                        boolean renamed = DBController.renameDefaultShiftTable(selectedDepartment.getDepName(), depName);
                        if (renamed) {
                            AlertWindow("Success", "Department name and default shift table name updated successfully");
                        } else {
                            AlertWindow("Fail", "Failed to update department name and default shift table name");
                        }
                    } else {
                        AlertWindow("Success", "Department information updated successfully");
                    }
                    DepartmentTable(); // Refresh the department table
                    depIDTextField.clear();
                    depNameTextField.clear();
                    depManagerTextField.clear();
                } else {
                    AlertWindow("Fail", "Failed to update department information");
                }
            }
        }
    }
    // Home button -- Written by: Elmer
    @FXML
    void homeButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // Create schedule button -- Written by: Elmer
    @FXML
    void createScheduleButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("createWeeklySchedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // Edit information button -- Written by: Elmer
    @FXML
    void editInformationButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("editinformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // View staff button -- Written by: Elmer
    @FXML
    void staffButtonOnAction (ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("staff.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // View departments button -- Written by: Elmer
    @FXML
    void departmentsButtonOnAction(ActionEvent event) throws IOException
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("departments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    // Logout button -- Written by: Elmer
    @FXML
    void logoutButtonOnAction(ActionEvent event) throws IOException
    {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    // Helper function to build alert window -- Written by: Elmer
    void AlertWindow(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Helper function to build alert window -- Written by: Elmer
    void AlertWindow(String title, String message, String headerText, AlertType alertType) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.show(); // Change showAndWait() to show()
    }
    // Build the values on the page -- Written by: Elmer
    public void initialize(URL url, ResourceBundle rb)
    {
        DepartmentTable();
    }
}