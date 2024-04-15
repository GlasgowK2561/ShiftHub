package shiftmate.proj;

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

    @FXML
    private TableView <Departments> departmentTableView;
    @FXML
    private TableColumn<Departments, String> depNamecolumn;
    @FXML
    private TableColumn<Departments, String> depManagercolumn;
    @FXML
    private Button createWeeklyScheduleOnAction;

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
    @FXML
    private void createWeeklyScheduleOnAction(ActionEvent event) {
        int selectedDepID = departmentTableView.getSelectionModel().getSelectedItem().getDepID();
        String selectedDepName = departmentTableView.getSelectionModel().getSelectedItem().getDepName();

        Alert confirmCreateSchedule = new Alert(AlertType.CONFIRMATION);
        confirmCreateSchedule.setTitle("Create Weekly Schedule");
        confirmCreateSchedule.setHeaderText(null);
        confirmCreateSchedule.setContentText("Do you want to create a new weekly schedule for the selected department?");
        
        ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonData.NO);
        confirmCreateSchedule.getButtonTypes().setAll(yesButton, noButton);
        
        Optional<ButtonType> result = confirmCreateSchedule.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
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
        scene = FXMLLoader.load(getClass().getResource("createschedules.fxml"));
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
       
    }
    
    void AlertWindow(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void AlertWindow(String title, String message, String headerText, AlertType alertType) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.show(); // Change showAndWait() to show()
    }
    
    public void initialize(URL url, ResourceBundle rb)
    {
        DepartmentTable();
    }
    
}