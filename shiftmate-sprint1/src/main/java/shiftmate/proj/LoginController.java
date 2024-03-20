package shiftmate.proj;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;




import java.io.IOException;




public class LoginController
{


    @FXML
    private Button loginButton;




    @FXML
    private Button closeButton;




    @FXML
    private Label loginMessageLabel;




    @FXML
    private TextField usernameTextfield;




    @FXML
    private PasswordField passwordPasswordField;


    @FXML
    private Button CreateanAccountButton;








    public void loginButtonOnAction(ActionEvent e) throws IOException
    {




        if (!usernameTextfield.getText().isBlank() && !passwordPasswordField.getText().isBlank())




        {
            if (usernameTextfield.getText().equals("ea") && passwordPasswordField.getText().equals("sp"))


            {
                loginMessageLabel.setText("Login Successful");
                openMainPage();
            }
            else
            {
                loginMessageLabel.setText("Invalid Username/Password");
            }
        }
        else
        {
            loginMessageLabel.setText("Input Username/Password");
        }
    }








    public void closeButtonOnAction(ActionEvent e)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void CreateanAccountButtonOnAction(ActionEvent e) throws IOException
    {
   
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createaccount.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 520, 400));
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }
   










    public void openMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1250, 900));
        stage.initStyle(StageStyle.DECORATED);
        stage.show();




    }




}


