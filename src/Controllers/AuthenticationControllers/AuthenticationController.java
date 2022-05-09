package Controllers.AuthenticationControllers;

import animatefx.animation.*;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthenticationController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            signInPane.setVisible(true);
            signUpPane.setVisible(false);
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/AuthenticationViews/SignUpInterface.fxml")));
            vbox.getChildren().setAll(fxml);
            new ZoomIn(signInPane).play();
            new ZoomIn(vbox).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private AnchorPane paneloding;

    @FXML
    private Circle loading1;

    @FXML
    private Circle loading2;

    @FXML
    private Circle loading3;
    @FXML
    private AnchorPane signInPane;
    @FXML
    private VBox vbox;

    private Parent fxml;

    @FXML
    public void open_signIn() {
        loadingManager(true);
        paneloding.setVisible(true);
        paneloding.toFront();
        new RollIn(loading1).setCycleCount(20).setSpeed(2).play();
        new RollIn(loading2).setCycleCount(20).setSpeed(2).play();
        new RollIn(loading3).setCycleCount(20).setSpeed(2).play();
        signInPane.setVisible(false);
        vbox.getChildren().clear();
        vbox.setMaxSize(50,50);
        vbox.setLayoutY(150);
        vbox.setLayoutX(-560);
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(1280);
        t.play();
        signUpPane.setVisible(true);
        new ZoomIn(signUpPane).play();
        t.setOnFinished(e ->{
            try {
                vbox.setMaxSize(480,480);
                vbox.setLayoutY(-40);
                vbox.setLayoutX(-800);
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/AuthenticationViews/LoginInterface.fxml")));
                new ZoomIn(vbox).play();
                vbox.getChildren().setAll(fxml);
                loadingManager(false);
            }catch(IOException ignored) {

            }
        });
    }

    @FXML
    public void open_signUp() {
        loadingManager(true);
        signUpPane.setVisible(false);
        vbox.setLayoutX(-600);
           vbox.getChildren().clear();
        vbox.setMaxSize(50,50);
        vbox.setLayoutY(150);
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(730);
        t.play();
        signInPane.setVisible(true);
        new ZoomIn(signInPane).play();
        t.setOnFinished(e ->{
            try {
                vbox.setLayoutY(-40);
                vbox.setLayoutX(-710);
                vbox.setMaxSize(480,480);
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/AuthenticationViews/SignUpInterface.fxml")));
                new ZoomIn(vbox).play();
                vbox.getChildren().setAll(fxml);
                loadingManager(false);

            }catch(IOException ignored) {

            }
        });
    }
    private void loadingManager(boolean t){
        if(t){
        paneloding.setVisible(true);
        paneloding.toFront();
        new RollIn(loading1).setCycleCount(20).setSpeed(2).play();
        new RollIn(loading2).setCycleCount(20).setSpeed(2).play();
        new RollIn(loading3).setCycleCount(20).setSpeed(2).play();
        }
        else {
            paneloding.setVisible(false);
            paneloding.toBack();
        }
    }
}
