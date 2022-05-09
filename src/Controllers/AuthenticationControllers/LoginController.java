package Controllers.AuthenticationControllers;

import Services.UserService;
import Services.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable {
    @FXML
    public TextField lineEditEmail;
    @FXML
    public PasswordField lineEditPwd;
    @FXML
    public Label errorLabel;
    @FXML
    private VBox mainBox;

    @FXML
    private VBox vboxElements;
    Parent fxml;
    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
    private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @FXML
    public void login(ActionEvent actionEvent) {
        UserService us = new UserService();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(lineEditEmail.getText());
        if (lineEditEmail.getText().length() < 1) {
            errorLabel.setText("Email field is empty!");
        } else if (lineEditPwd.getText().length() < 1) {
            errorLabel.setText("Password field is empty");
        } else if (!matcher.matches()) {
            errorLabel.setText("Invalid Email Format");
        } else {
            if (us.validateLogin(lineEditEmail.getText(), lineEditPwd.getText())) {
                us.setOnline(lineEditEmail.getText());
                try {
                    us.addUserLogin(UserSession.getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Login Successful ! ");
                System.out.println(UserSession.getId() + " " + UserSession.getName() + " Role: " + UserSession.getRole() + " Email: " + UserSession.getEmail() + " Picture: /Controllers.Images/" + UserSession.getProfilepicture());
                Parent root = null;
                try {
                    if (UserSession.getRole().equals("ROLE_ADMIN")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/AdminViews/Interface_Admin.fxml")));
                    } else if (UserSession.getRole().equals("ROLE_CHEF")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/ChefViews/Interface_Chef.fxml")));
                    } else if (UserSession.getRole().equals("ROLE_USER")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Interface_Client.fxml")));
                    }else if (UserSession.getRole().equals("ROLE_DELIVERY")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Interface_Client.fxml")));
                    }

                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    Stage stage = (Stage) lineEditEmail.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    primaryStage.initStyle(StageStyle.TRANSPARENT);
                    primaryStage.setTitle("InterFace : " + UserSession.getName());
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorLabel.setText("Email or Password is incorrect !");
            }
        }

    }
}
