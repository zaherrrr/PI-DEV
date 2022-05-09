package Controllers.AdminControllers;

import Entities.Users;
import Interfaces.usersListener;
import Utils.DurationCalculator;
import animatefx.animation.BounceIn;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SingleUserController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private AnchorPane singleUserPane;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        new BounceIn(singleUserPane).play();
        myListener.onClickListener(user);


    }

    private Users user;
    private usersListener myListener;

    public void setData(Users user, usersListener myListener) {
        System.out.println(user.getId());
        this.user = user;
        this.myListener = myListener;
        nameLabel.setText(user.getName());
        roleLabel.setText(user.getRole());
        emailLabel.setText(user.getEmail());
        DurationCalculator calc = new DurationCalculator();
        calc.DurationCalculator(user.getJoinDate());
        Image image = new Image("Controllers/Images/" + user.getProfilePicture());
        img.setImage(image);
    }
}
