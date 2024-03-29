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

public class EditInformationController implements Initializable
{

    Stage stage;

    Parent scene;

    @FXML
    private TableView <EmployeeInfo> employeeTableView;

    @FXML
    private TableColumn<EmployeeInfo, Integer> employeeIDcolumn;
    @FXML
    private TableColumn<EmployeeInfo, Integer> deptIDcolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> fNamecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> lNamecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> emailcolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> phonecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> startDatecolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> contactcolumn;
    @FXML
    private TableColumn<EmployeeInfo, String> contactPhonecolumn;


    public void EmployeeInfoTable() 
    {
        LinkedList<Hashtable<String,String>> employeeInformation = DBController.getEmployees();  //gets employee data from DBController

        ObservableList<EmployeeInfo> employeeList = FXCollections.observableArrayList();    //list to hold employee objects

        for (int i = 0; i < employeeInformation.size(); i++)                //iterate through the data
        {
            Hashtable<String,String> data = employeeInformation.get(i);

            int employeeID = Integer.parseInt(data.get("employeeID"));      
            int depID = Integer.parseInt(data.get("depID"));
            String fName = data.get("fName");
            String lName = data.get("lName");                                  //extracts attributes of the employees
            String email = data.get("email");
            String phone = data.get("phone");
            String startDate = data.get("startDate");
            String contact = data.get("contact");
            String contactPhone = data.get("contactPhone");                                         
            
            //creates empinfo object to add to list
            employeeList.add(new EmployeeInfo(employeeID, depID, fName, lName, email,
             phone, startDate, contact, contactPhone)); 

        }
    
    //fill TableView with data
    employeeTableView.setItems(employeeList);

    employeeIDcolumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
    deptIDcolumn.setCellValueFactory(new PropertyValueFactory<>("depID"));
    fNamecolumn.setCellValueFactory(new PropertyValueFactory<>("fName"));
    lNamecolumn.setCellValueFactory(new PropertyValueFactory<>("lName"));
    emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phonecolumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    startDatecolumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    contactcolumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
    contactPhonecolumn.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));


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
    
    public void initialize(URL url, ResourceBundle rb)
    {
        EmployeeInfoTable();
    }
    



}

