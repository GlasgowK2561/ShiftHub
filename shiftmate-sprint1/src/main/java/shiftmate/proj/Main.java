package shiftmate.proj;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
<<<<<<< Updated upstream
=======
        stage.setTitle("ShiftMate");

        Image image = new Image(getClass().getResourceAsStream("shiftmate_mascot.png"));
        stage.getIcons().add(image);
        
>>>>>>> Stashed changes
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}



