package Controllers.ClientControllers;

import Entities.Plate;
import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class PlateDetails {
    @FXML
    private VBox chosenPlate;

    @FXML
    private Text infoLabel;

    @FXML
    private Label platePrice;

    @FXML
    private Label plateQuantity;

    @FXML
    private Label platecat;

    @FXML
    private ImageView plateimg;

    @FXML
    private Label platename;

    @FXML
    private VBox singleUserDynamicBox;

    private static Plate plate;
    private static Parent fxml;
    private static Image image;

    @FXML
    void closePlateInfo(ActionEvent event) {
        ((VBox) chosenPlate.getParent()).getChildren().remove(chosenPlate);
    }
    public  void PlateMenuButtons(Plate plaaaate){
        if(plaaaate.getQuantity() == 0){
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(PlateDetails.class.getResource("../../Views/ClientViews/OutOfStock.fxml")));
                PlateMenuButtonsController.setInfo(plate);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(plaaaate.getQuantity()> 0){
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(PlateDetails.class.getResource("../../Views/ClientViews/PlateMenuButtons.fxml")));
                PlateMenuButtonsController.setInfo(plate);

            } catch (IOException e) {
                e.printStackTrace();
            } }
        singleUserDynamicBox.getChildren().removeAll();
        singleUserDynamicBox.getChildren().setAll(fxml);
    }
    public  void setPlate(Plate pl){
        plate = pl;
        plate=pl;
        PlateMenuButtons(plate);

        platename.setText(plate.getName());
        platePrice.setText("  "+plate.getPrice()+" $");
        plateQuantity.setText("Qautntiy: "+plate.getQuantity());
        platecat.setText(""+plate.getId_category());
        new FadeIn(chosenPlate).play();
        infoLabel.setText(plate.getDescription()+" \n ");
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        String url = "beefburger"+value+".jpg";
        image = new Image("Controllers/Images/"+url);
        plateimg.setImage(image);

        String bgcolor;

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
        chosenPlate.setStyle("-fx-background-color: #" + bgcolor + ";\n" +
                "    -fx-background-radius: 30;");
    }
    public  void setChosenPlate(Plate pl) {
        //titleBarView();

    }

}
