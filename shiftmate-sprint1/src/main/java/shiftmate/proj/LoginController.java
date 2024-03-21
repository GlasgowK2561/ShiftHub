package shiftmate.proj;

<<<<<<< Updated upstream
import javafx.application.Platform;
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
=======

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public void loginButtonOnAction(ActionEvent e) throws IOException
    {
        if (!usernameTextfield.getText().isBlank() && !passwordPasswordField.getText().isBlank())
        {
            Login_Back.connect(); //To establish connection to the database
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
=======
    @FXML
    private Button CreateanAccountButton;

    public void loginButtonOnAction(ActionEvent e) throws IOException
    {
        String username = usernameTextfield.getText();
        String password = passwordPasswordField.getText();
            if (!username.isBlank() && !password.isBlank()) {
                //if (username.equals("ea") && password.equals("sp")) {
                    Boolean valid_Login = Database_Communication.login(username, password); // Send username and password objects
                    if (valid_Login){
                        loginMessageLabel.setText("Login Successful");
                        openMainPage();
                    }
                    else {
                        loginMessageLabel.setText("Invalid Username/Password");
                    }
                } else {
                    loginMessageLabel.setText("Input Username/Password");
                }
        }

>>>>>>> Stashed changes
    public void closeButtonOnAction(ActionEvent e)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

<<<<<<< Updated upstream
=======
    public void CreateanAccountButtonOnAction(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createaccount.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)CreateanAccountButton.getScene().getWindow();
       
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

>>>>>>> Stashed changes
    public void openMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1250, 900));
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
}
