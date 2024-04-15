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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class StaffController implements Initializable
{
    Stage stage;
    Parent scene;

    @FXML
    private TableView <EmployeeInfo> staffTableView;

    @FXML
    private TableColumn<EmployeeInfo, String> fNamecolumn;
    @FXML
    private TableColumn<EmployeeInfo,String> lNamecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> departmentcolumn;


    public void StaffTable()
    {
        LinkedList<Hashtable<String,String>> staffInformation = DBController.getEmployees();


        ObservableList<EmployeeInfo> staffList = FXCollections.observableArrayList();
   
       
        for (int i = 0; i < staffInformation.size(); i++)                
        {
            Hashtable<String,String> data = staffInformation.get(i);

            String fName = data.get("fName");
            String lName = data.get("lName");
            String depName = data.get("depName");

            EmployeeInfo staffInfo = new EmployeeInfo(0, 0, fName, lName, null,
            null, null, null, null);
            
            staffInfo.setDepName(depName);
            staffList.add(staffInfo);

        }

            staffTableView.setItems(staffList);

            fNamecolumn.setCellValueFactory(new PropertyValueFactory<>("fName"));
            lNamecolumn.setCellValueFactory(new PropertyValueFactory<>("lName"));
            departmentcolumn.setCellValueFactory(new PropertyValueFactory<>("depName"));

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
   
    public void initialize(URL url, ResourceBundle rb)
    { 
        StaffTable();
    }
}    


