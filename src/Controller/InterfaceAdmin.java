package Controller;


import Services.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InterfaceAdmin implements Initializable {

    @FXML
    private Button usersPagebtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button categoryPagebtn;
    @FXML
    private Button productsPagebtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Label pageLabel;
    @FXML
    private AnchorPane adminPane;

    @FXML
    private VBox vbox;

    private Parent fxml;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePage();
    }

    private void enableBtns() {
        usersPagebtn.setDisable(false);
        categoryPagebtn.setDisable(false);
        productsPagebtn.setDisable(false);
        profileBtn.setDisable(false);
        homeBtn.setDisable(false);
    }

    private void selectBtn(Button b) {
        enableBtns();
        b.setDisable(true);
    }

    @FXML
    public void homePage() {
        pageLabel.setText("Welcome Home Mr " + UserSession.getName());
        selectBtn(homeBtn);
        usersPagebtn.setDisable(true);

    }

    @FXML
    void productPage(ActionEvent event) {
        pageLabel.setText("Products Management");
        selectBtn(productsPagebtn);
    }

    @FXML
    void profilePage(ActionEvent event) {
        pageLabel.setText("Profile Management");
        selectBtn(profileBtn);
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/UserProfile.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(fxml);
    }

    @FXML
    void usersPage(ActionEvent event) {
        pageLabel.setText("Users Management");

        selectBtn(usersPagebtn);
        homeBtn.setDisable(true);
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Users_Management.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(fxml);
    }

    @FXML
    void categoryPage(ActionEvent event) {
        pageLabel.setText("Category Management");
        selectBtn(categoryPagebtn);
    }

    @FXML
    void logout(ActionEvent event) {
        Stage stage;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        alert.setContentText("Do you want to logout " + UserSession.getName() + " ? ");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) adminPane.getScene().getWindow();
            System.out.println("You logged out Mr admin " + UserSession.getName());
            UserSession.cleanUserSession();
            stage.close();
        }
    }
}
