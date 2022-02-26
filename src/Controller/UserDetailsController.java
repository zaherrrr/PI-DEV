package Controller;

import Entity.Users;
import Interfaces.MyListener;
import Services.UserService;
import Util.DurationCalculator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

public class UserDetailsController implements Initializable {

    @FXML
    public Label userStatus;
    @FXML
    private VBox chosenUserCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

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

    private List<Users> users = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private DurationCalculator dc;

    private List<Users> getData() {
        List<Users> users = UserService.readUsers();
        return users;
    }

    private void setChosenUser(Users user) {

        fruitNameLable.setText(user.getName());
        fruitPriceLabel.setText(user.getRole());
        image = new Image("Images/" + user.getProfilePicture());
        userimg.setImage(image);
        usernameaftername.setText(user.getName() + "-" + user.getAfterName());
        userrole.setText(user.getRole());
        Date date = new Date((user.getJoinDate()).getTime());
        int[] duration = dc.DurationCalculator(user.getJoinDate());
        userjoinedDate.setText("Registered " + date);
        accountLife.setText("Joined " + duration[0] + " Months," + duration[1] + " Days," + duration[2] + " Hours ago.");
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
        users.addAll(getData());
        if (users.size() > 0) {
            setChosenUser(users.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Users user) {
                    setChosenUser(user);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < users.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Views/SingleUser.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SingleUserController userController = fxmlLoader.getController();
                userController.setData(users.get(i), myListener);
                if (column ==4) {
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

}