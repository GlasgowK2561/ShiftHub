package shiftmate.proj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;


public class MainController {
    @FXML
    private Button homeButton;
    @FXML
    private Button createScheduleButton;
    @FXML
    private Button editInformationButton;
    @FXML
    private Button staffButton;
    @FXML
    private Button DepartmentsButton;  
    @FXML  
    private Button logoutButton;
    @FXML

    private void homeButtonOnAction(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1250, 800));
        stage.show();
    }

    @FXML
    private void createScheduleButtonOnAction(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createschedules.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) staffButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1250, 800));
        stage.show();
    }

    @FXML
    private void editInformationButtonOnAction(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editinformation.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) editInformationButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1250, 800));
        stage.show();
    }

    @FXML
    private void staffButtonOnAction(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("staff.fxml"));
        List<String[]> employees = Login_Back.getEmployees();
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) staffButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1250, 800));
        stage.show();
    }

    @FXML
    private void departmentsButtonOnAction(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("departments.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) DepartmentsButton.getScene().getWindow();
        stage.setScene(new Scene(root, 1250, 800));
        stage.show();
    }


    @FXML
    private void logoutButtonOnAction(ActionEvent e) throws IOException
    {
       
    }
}
