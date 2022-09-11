package Controllers.AdminControllers;

import Entities.Users;
import Interfaces.usersListener;
import Services.UserService;
import Utils.DurationCalculator;
import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class InterfaceAdminController implements Initializable {

    private static Users u;
    public Users getUser(){
        return this.u;
    }
    @FXML
    public Label userStatus;
    @FXML
    private VBox chosenUserCard;
    @FXML
    private Label infoLabel;
    @FXML
    private VBox singleUserDynamicBox;
    @FXML
    private Label userjoinedDate;
    @FXML
    private ImageView userimg;
    @FXML
    private Label usernameaftername;
    @FXML
    private Label userrole;
    @FXML
    private Label accountLife;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Parent fxml;

    private final List<Users> users = new ArrayList<>();
    private Image image;
    private usersListener myListener;
    private DurationCalculator dc;

    private List<Users> getData() {
        List<Users> users = UserService.readUsers();
        return users;
    }

    private void setChosenUser(Users user) {
        u = user;
        UsersMenuButtons();
        new FadeIn(chosenUserCard).play();
        infoLabel.setText("Mail: " + user.getEmail() + "\n" + "Total Logins: 85 \n Last Seen: Yesterday\n Sorry am stupid");
        image = new Image("Controllers/Images/" + user.getProfilePicture());
        userimg.setImage(image);
        usernameaftername.setText(user.getName() + "-" + user.getAfterName().toUpperCase());
        userrole.setText(user.getRole());
       /* Date date = new Date((user.getJoinDate()).getTime());
        int[] duration = DurationCalculator.DurationCalculator(user.getJoinDate());
        userjoinedDate.setText("Registered " + date);
        accountLife.setText("Joined " + duration[0] + " Months," + duration[1] + " Days," + duration[2] + " Hours ago.");*/
        String bgcolor;
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        switch (value) {
            case 1:
                bgcolor = "FFB605";
            case 2:
                bgcolor = "FFB605";
                break;
            case 3:
                bgcolor = "80080C";
                break;
            case 4:
                bgcolor = "FB5D03";
                break;
            case 5:
                bgcolor = "22371D";
                break;
            case 6:
                bgcolor = "291D36";
                break;
            case 7:
                bgcolor = "F16C31";
                break;
            case 8:
                bgcolor = "A7745B";
                break;
            case 9:
                bgcolor = "6A7324";
                break;
            case 10:
                bgcolor = "ABB605";
                break;
            default:
                bgcolor = "5F060E";
        }
        chosenUserCard.setStyle("-fx-background-color: #" + bgcolor + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UsersMenuButtons();
        chosenUserCard.setVisible(false);
        users.addAll(getData());
        if (users.size() > 0) {
            //setChosenUser(users.get(0));
            myListener = new usersListener() {
                @Override
                public void onClickListener(Users user) {
                    u = user;
                    chosenUserCard.setVisible(true);
                    setChosenUser(user);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < users.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../Views/AdminViews/SingleUser.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SingleUserController userController = fxmlLoader.getController();
                userController.setData(users.get(i), myListener);
                if (column == 4) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    public void UsersMenuButtons(){
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/AdminViews/UserMenuButtons.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        singleUserDynamicBox.getChildren().removeAll();
        singleUserDynamicBox.getChildren().setAll(fxml);
    }

}
