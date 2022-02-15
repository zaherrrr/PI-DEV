package Controller;

import Entity.Users;
import Services.UserService;
import Services.UserSession;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginC implements Initializable {
    private static String profilePicture = "";
    private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @FXML
    private Label pageNamelabel;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField lineEditEmail;

    @FXML
    private PasswordField lineEditPwd;

    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel1;

    @FXML
    private AnchorPane signupPane;

    @FXML
    private TextField nameAdd;

    @FXML
    private TextField lastnameAdd;

    @FXML
    private TextField emailAdd;

    @FXML
    private PasswordField passwordAdd;

    @FXML
    private ImageView signUpImage;

    @FXML
    void uploadPicSignUp(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("C:\\Users\\ME\\Desktop\\PI DEV\\Developpement\\JavaFX\\WeEatTunisian\\src\\Images"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String TempprofilePicture = file.toURI().toString();
            System.out.println(TempprofilePicture);
            profilePicture = file.getName();
            System.out.println(profilePicture);
            Image image = new Image(TempprofilePicture);
            signUpImage.setImage(image);
        }
    }

    @FXML
    void gotosignup(ActionEvent event) {
        loginPane.setVisible(false);
        signupPane.setVisible(true);
        pageNamelabel.setText("SIGNUP");
    }

    @FXML
    void signup(ActionEvent event) {
        Pattern pattern = Pattern.compile(regex);
        UserService us = new UserService();
        errorLabel1.setText("Please wait...");
        Matcher matcher = pattern.matcher(emailAdd.getText());
        if (profilePicture.equals("")) {
            errorLabel1.setText("Select Picture!");
        } else if (nameAdd.getText().length() < 3) {
            errorLabel1.setText("Name too short!");
        } else if (lastnameAdd.getText().length() < 3) {
            errorLabel1.setText("Last name too short!");
        } else if (!matcher.matches()) {
            errorLabel1.setText("Invalid mail formart!");
        } else if (passwordAdd.getText().length() < 6) {
            errorLabel1.setText("Password mush be greater than 6 symbols!");

        } else if (!us.validateEmail(emailAdd.getText())) {
            errorLabel1.setText("Email is already in use.");
        } else {

            errorLabel1.setText("Please wait..");
            LocalDateTime rightNow = LocalDateTime.now();
            /* this is used if the client wants the date as a string and specific format
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String rightNow = myDateObj.format(myFormatObj);*/
            Users p = new Users(nameAdd.getText(), lastnameAdd.getText(), emailAdd.getText(), passwordAdd.getText(), "ROLE_USER", profilePicture);
            try {
                if (us.add(p)) {
                    errorLabel1.setText("");
                    homePage();
                    clearSignup();
                } else {
                    System.out.println("User was not added");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @FXML
    void cancelSignup(ActionEvent event) {
        homePage();
        clearSignup();
    }

    void homePage() {
        loginPane.setVisible(true);
        signupPane.setVisible(false);
        pageNamelabel.setText("LOGIN");
    }

    void clearSignup() {
        nameAdd.setText("");
        lastnameAdd.setText("");
        emailAdd.setText("");
        passwordAdd.setText("");
        signUpImage.setImage(null);
    }

    @FXML
    void login() {
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
                System.out.println(UserSession.getId() + " " + UserSession.getName() + " Role: " + UserSession.getRole() + " Email: " + UserSession.getEmail() + " Picture: /Images/" + UserSession.getProfilepicture());
                Parent root = null;
                try {
                    if (UserSession.getRole().equals("ROLE_ADMIN")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Interface_Admin.fxml")));
                    } else if (UserSession.getRole().equals("ROLE_CLIENT")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Interface_User.fxml")));
                    } else if (UserSession.getRole().equals("ROLE_CHEF")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Interface_Moderator.fxml")));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lineEditPwd.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    login();
                }
            }
        });
        homePage();

    }


}
