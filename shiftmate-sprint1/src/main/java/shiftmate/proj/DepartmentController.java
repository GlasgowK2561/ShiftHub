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

public class DepartmentController implements Initializable
{
    Stage stage;

    Parent scene;

    @FXML
    private TableView <Departments> departmentTableView;

    @FXML
    private TableColumn<Departments, Integer> depIDcolumn;
    @FXML
    private TableColumn<Departments, Integer> depNamecolumn;
    @FXML
    private TableColumn<Departments, String> depManagercolumn;


    public void DepartmentTable() 
    {
        LinkedList<Hashtable<String,String>> departmentInformation = DBController.getDepartmentNamesandIDs();

        ObservableList<Departments> departmentList = FXCollections.observableArrayList();

        for (int i = 0; i < departmentInformation.size(); i++)                
        {
            Hashtable<String,String> data = departmentInformation.get(i);

            int depID = Integer.parseInt(data.get("depID"));
            String depName = data.get("depName");
            String depManager = data.get("depManager");


            departmentList.add(new Departments(depID, depName, depManager));


            departmentTableView.setItems(departmentList);

            depIDcolumn.setCellValueFactory(new PropertyValueFactory<>("depID"));
            depNamecolumn.setCellValueFactory(new PropertyValueFactory<>("depName"));
            depManagercolumn.setCellValueFactory(new PropertyValueFactory<>("depManager"));



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
    
    public void initialize(URL url, ResourceBundle rb)
    {
        DepartmentTable();
    }
}    
