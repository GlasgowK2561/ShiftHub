package shiftmate.proj;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditInformationController 
{

    Stage stage;

    Parent scene;

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
    
    

}
