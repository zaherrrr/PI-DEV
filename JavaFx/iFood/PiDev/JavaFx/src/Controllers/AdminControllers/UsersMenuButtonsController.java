package Controllers.AdminControllers;

import Entities.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UsersMenuButtonsController implements Initializable {
    public TextField quantiteField;
    @FXML
    private VBox buttonDynamicBox;

    Parent fxml;
    @FXML
    public void contactShow(ActionEvent actionEvent) {
       try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/AdminViews/Interface_Admin_Contact.fxml")));
            InterfaceAdminController admin = new InterfaceAdminController();
            Users u = admin.getUser();
            InterfaceAdminContact ifc = new InterfaceAdminContact();
            ifc.setInfo(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonDynamicBox.getChildren().setAll(fxml);

    }
    @FXML
    public void activityShow(ActionEvent actionEvent) {
    }
    @FXML
    public void banShow(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void decrementerQuantite(ActionEvent actionEvent) {
    }

    public void incrementerQuantite(ActionEvent actionEvent) {
    }

    public void addToCart(ActionEvent actionEvent) {
    }

    public void AddToPack(ActionEvent actionEvent) {
    }

    public void commentPlate(ActionEvent actionEvent) {
    }
}
