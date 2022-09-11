package Controllers.ClientControllers;

import Entities.Plate;

import Interfaces.plateListener;
import Utils.DurationCalculator;
import animatefx.animation.BounceIn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SinglePlateController implements Initializable {
    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private AnchorPane singleplatePane;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        new BounceIn(singleplatePane).play();
        myListener.onClickListener(plate);
    }

    private Plate plate;
    private plateListener myListener;

    public void setData(Plate plate, plateListener myListener) {
        System.out.println(plate.getId());
        this.plate = plate;

        this.myListener = myListener;
        nameLabel.setText(plate.getName());
        roleLabel.setText(String.valueOf(plate.getPrice())+"$");
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        String url = "beefburger"+value+".jpg";
        Image image = new Image("Controllers/Images/"+url);
        img.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
