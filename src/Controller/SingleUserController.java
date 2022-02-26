package Controller;

import Entity.Users;
import Util.DurationCalculator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Interfaces.MyListener;


public class SingleUserController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(user);
    }

    private Users user;
    private MyListener myListener;

    public void setData(Users user, MyListener myListener) {
        System.out.println(user.getId());
        this.user = user;
        this.myListener = myListener;
        nameLabel.setText(user.getName());
        roleLabel.setText(user.getRole());
        emailLabel.setText(user.getEmail());
        DurationCalculator calc = new DurationCalculator();
        calc.DurationCalculator(user.getJoinDate());
        Image image = new Image("Images/" + user.getProfilePicture());
        img.setImage(image);
    }
}
