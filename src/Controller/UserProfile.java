package Controller;

import Services.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {

    @FXML
    private Circle profilePicture;
    @FXML
    private Label profileNameLastName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileHome();
    }

    private void profileHome() {
        Image im = new Image("Images/" + UserSession.getProfilepicture());
        ImagePattern pattern = new ImagePattern(im);
        profilePicture.setFill(pattern);
        profilePicture.setStroke(Color.SEAGREEN);
        profilePicture.setEffect(new DropShadow(20, Color.BLACK));
        profileNameLastName.setText("#" + UserSession.getName().toUpperCase() + " " + UserSession.getAftername().toUpperCase());

    }
}
