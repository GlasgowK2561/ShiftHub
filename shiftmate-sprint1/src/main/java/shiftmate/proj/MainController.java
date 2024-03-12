package shiftmate.proj;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView exit;
    @FXML
    private Label menu;
    @FXML
    private AnchorPane panel1, panel2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        panel1.setVisible(false);
        // Set initial translation for panel2
        panel2.setTranslateX(-600);
        menu.setOnMouseClicked(event -> {
            if (panel1.isVisible()) {
                slideOutMenu();
            } else {
                slideInMenu();
            }
        });
        panel1.setOnMouseClicked(event -> {
            slideOutMenu();
        });
    }
    private void slideInMenu() {
        panel1.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), panel1);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), panel2);
        translateTransition.setToX(0);
        translateTransition.play();
    }
    private void slideOutMenu() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), panel1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(event -> panel1.setVisible(false));
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), panel2);
        translateTransition.setToX(-600);
        translateTransition.play();
    }
}