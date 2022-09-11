package Controllers.AuthenticationControllers;

import Entities.Users;
import Services.UserService;
import Services.UserSession;
import animatefx.animation.Shake;
import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {
    private static final UserService us = new UserService();
    private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String stringregex ="[a-zA-Z]*$";
    private static String picture = "";
    private final Pattern pattern = Pattern.compile(regex);
    private final Pattern stringpattern = Pattern.compile(stringregex);
    public JFXPasswordField passwordAdd;
    public JFXTextField emailAdd;
    public JFXTextField nameAdd;
    public JFXTextField lastnameAdd;
    public JFXDatePicker birthdate;
    public VBox mainBox;

    @FXML
    private ImageView profilePicture;

    @FXML
    private HBox signUpTitle;
    @FXML
    private Label signUpError;

    @FXML
    private Button signupbtn;
 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInLeft(signUpTitle).setCycleCount(1).setSpeed(0.7).play();
        birthdate.setDefaultColor(Color.GOLD);
        birthdate.setStyle("-fx-text-fill: white");
        emailAdd.textProperty().addListener((observableValue, s, s2) -> {
            Matcher matcher = pattern.matcher(emailAdd.getText());
            if (!us.validateEmail(emailAdd.getText())) {
                emailAdd.validate();
                System.out.println("Email is already in use.");
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Email Already in use.");
                new Shake(emailAdd).play();
            } else if (!matcher.matches()) {
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Invalid E-Mail Format.");
            } else if (matcher.matches()) {
                emailAdd.setStyle("-fx-border-color : #5beb34;-fx-border-inset : solid;");
                signUpError.setText("");

            }
        });
        passwordAdd.textProperty().addListener((observableValue, s, s2) -> {
            boolean test = pwdStrengh(s2);
            if(!test){
                passwordAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Password Must >=6 & contain Uppercase &  number !");

            }
            else if(test){
                passwordAdd.setStyle("-fx-border-color : #5beb34;-fx-border-inset : solid;");
                signUpError.setText("");
            }
            Matcher matcher = pattern.matcher(emailAdd.getText());
            if (!us.validateEmail(emailAdd.getText())) {
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Email Already in use.");
                new Shake(emailAdd).play();
            } else if (!matcher.matches()) {
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Invalid E-Mail Format.");
                new Shake(emailAdd).play();
            }
        });
        nameAdd.textProperty().addListener((observableValue, s, s2) -> {
            Matcher stringmatcher = stringpattern.matcher(nameAdd.getText());
            if(s2.length()<3 || !stringmatcher.matches()){
                nameAdd.setStyle("-fx-border-color : red; -fx-border-inset : solid;");
                signUpError.setText("Name must B >=3 Letters Only !");

            }
            else if(s2.length()>3 && stringmatcher.matches()){
                nameAdd.setStyle("-fx-border-color : #5beb34;-fx-border-inset : solid;");
                signUpError.setText("");
            }
            Matcher matcher = pattern.matcher(emailAdd.getText());
            if (!us.validateEmail(emailAdd.getText())) {
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Email Already in use.");
                new Shake(emailAdd).play();
            } else if (!matcher.matches()) {
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Invalid E-Mail Format.");
                new Shake(emailAdd).play();
            }
        });
        lastnameAdd.textProperty().addListener((observableValue, s, s2) -> {
            Matcher stringmatcher = stringpattern.matcher(lastnameAdd.getText());
            if(s2.length()<3 || !stringmatcher.matches()){
                lastnameAdd.setStyle("-fx-border-color : red; -fx-border-inset : solid;");
                signUpError.setText("Lastname must B >=3 Letters Only !");

            }
            else if(s2.length()>3 && stringmatcher.matches()){
                lastnameAdd.setStyle("-fx-border-color : #5beb34;-fx-border-inset : solid;");
                signUpError.setText("");
            }
            Matcher matcher = pattern.matcher(emailAdd.getText());
            if (!us.validateEmail(emailAdd.getText())) {
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Email Already in use.");
                new Shake(emailAdd).play();
            } else if (!matcher.matches()) {
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Invalid E-Mail Format.");
                new Shake(emailAdd).play();
            }
        });

    }

    @FXML
    void handleDragOver(DragEvent event) {

        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    @FXML
    void handleDrop(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();
        try {
            picture = (files.get(0).getName());
            System.out.println(picture);
            Image img = new Image(new FileInputStream(files.get(0)));
            profilePicture.setImage(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selectPicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.gif", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("C:\\Users\\zaher\\OneDrive\\Bureau\\iFood\\public\\Profilepictures"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String TempprofilePicture = file.toURI().toString();
            System.out.println(TempprofilePicture);
            picture = file.getName();
            System.out.println(picture);
            Image image = new Image(TempprofilePicture);
            profilePicture.setImage(image);
        }
    }

    @FXML
    public void signup() {
        Matcher matcher = pattern.matcher(emailAdd.getText());
        LocalDate date = birthdate.getValue();
        if (!matcher.matches()) {
            signUpError.setText("Invalid mail formart!");
        }
        else if (!us.validateEmail(emailAdd.getText())) {
            signUpError.setText("Email is already in use.");
        }
        else if (passwordAdd.getText().length() < 6) {
            signUpError.setText("Password mush be greater than 6 symbols!");
        }
        else if (nameAdd.getText().length() < 3) {
            signUpError.setText("Name too short!");
        } else if (lastnameAdd.getText().length() < 3) {
            signUpError.setText("Last name too short!");
        }

        else if (picture.equals("")) {
            signUpError.setText("Select Picture!");
        }else {
            signUpError.setText("Please wait..");
            //LocalDateTime rightNow = LocalDateTime.now();
            /* this is used if the client wants the date as a string and specific format
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String rightNow = myDateObj.format(myFormatObj);*/

            Users p = new Users(nameAdd.getText(), lastnameAdd.getText(), emailAdd.getText(), passwordAdd.getText(), "[\"ROLE_USER\"]", picture);
            try {
                if (us.add(p)) {
                    us.validateLogin(emailAdd.getText(),passwordAdd.getText());
                    us.setOnline(emailAdd.getText());
                 /*   try {
                        us.addUserLogin(UserSession.getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }*/
                    clearSignup();

                    System.out.println("Login Successful ! ");
                    System.out.println(UserSession.getId() + " " + UserSession.getName() + " Role: " + UserSession.getRole() + " Email: " + UserSession.getEmail() + " Picture: /Controllers.Images/" + UserSession.getProfilepicture());
                    Parent root = null;
                    try {
                        if (UserSession.getRole().equals("[\"ROLE_ADMIN\"]")) {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/AdminViews/Interface_Admin.fxml")));
                        } else if (UserSession.getRole().equals("[\"ROLE_CHEF\"]")) {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/ChefViews/Interface_Chef.fxml")));
                        } else if (UserSession.getRole().equals("[\"ROLE_USER\"]")) {
                            System.out.println("yup");
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/ClientViews/Interface_Client.fxml")));
                        }else if (UserSession.getRole().equals("[\"ROLE_DELIVERY\"]")) {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/Interface_Client.fxml")));
                        }

                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);
                        Stage stage = (Stage) emailAdd.getScene().getWindow();
                        stage.close();
                        Stage primaryStage = new Stage();
                        primaryStage.initStyle(StageStyle.TRANSPARENT);
                        primaryStage.setTitle("InterFace : " + UserSession.getName());
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("User was not added");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void clearSignup() {
        nameAdd.setText("");
        lastnameAdd.setText("");
        emailAdd.setText("");
        passwordAdd.setText("");
        profilePicture.setImage(null);
    }

    @FXML
    public void validateForm(MouseEvent event) {
        if (event.getSource() == passwordAdd) {
            Matcher matcher = pattern.matcher(emailAdd.getText());
            if (!matcher.matches()) {
                System.out.println("Email is already in use.");
                emailAdd.setStyle("-fx-border-color : red;-fx-border-inset : solid;");
                signUpError.setText("Email Already in use.");
                new Shake(emailAdd).play();
            } else {
                emailAdd.setStyle("-fx-border-color : #5beb34;-fx-border-inset : solid;");

            }
        }
    }

    private boolean pwdStrengh(String pwd){
        int len = pwd.length();
        int digit = 0;
        int lowerCase = 0;
        int upperCase = 0;
        int count = 0;

        char ch;
        if (len >= 6) {
            while (count < len) {
                ch = pwd.charAt(count);
                if (Character.isDigit(ch)) {
                    digit = digit + 1;
                }
                if (Character.isLowerCase(ch)) {
                    lowerCase = lowerCase + 1;
                }
                if (Character.isUpperCase(ch)) {
                    upperCase = upperCase + 1;
                }
                count = count + 1;
            }
        }
        if (digit >= 1 && lowerCase >= 1 && upperCase >= 1)
        { System.out.println("Acceptable PW.");
            return true;}
        else
            System.out.println("PW not Accepted.");
            return false;


    }
  /*  @FXML
    public void login() {
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
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Views/Interface_Admin.fxml")));
                    } else if (UserSession.getRole().equals("ROLE_CLIENT")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Views/Interface_User.fxml")));
                    } else if (UserSession.getRole().equals("ROLE_CHEF")) {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Views/Interface_Moderator.fxml")));
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
    private void lineEditsKeyPressed(ActionEvent event){
        lineEditPwd.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    login();
                }
            }
        });
    }*/
}
