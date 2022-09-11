package Controllers.ClientControllers;

import Entities.Command;
import Entities.Plate;
import Entities.PlateCategory;
import Interfaces.categorieListener;
import Interfaces.commandListener;
import animatefx.animation.BounceIn;
import animatefx.animation.ZoomIn;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class SingleCategory {

    @FXML
    private AnchorPane categoryBackground;

    @FXML
    private Text categoryDeescription;

    @FXML
    private Text categoryName;

    @FXML
    private Label categoryNumber;

    @FXML
    private Label numberOfPaltes;

    @FXML
    private Label numberOfPlatesSold;
    @FXML
    private Label description;

    @FXML
    private Label nbrPlatesSold;

    @FXML
    private Label nbrText;
    @FXML
    private VBox categoryBox;

    @FXML
    void click(MouseEvent event) {
        new BounceIn(categoryBackground).play();
        myListener.onClickListener(category);
    }
    @FXML
    public void unhoverCategory(MouseEvent mouseEvent) {
    categoryNumber.setStyle("-fx-border-color: white ;-fx-border-width: 5;--fx-border-radius:30");
        categoryNumber.setTextFill(Color.WHITE);
        categoryName.setFill(Color.WHITE);
        categoryDeescription.setFill(Color.WHITE);
        description.setTextFill(Color.WHITE);
        nbrPlatesSold.setTextFill(Color.WHITE);
        nbrText.setTextFill(Color.WHITE);
        numberOfPaltes.setTextFill(Color.WHITE);
        numberOfPlatesSold.setTextFill(Color.WHITE);
        categoryBox.setStyle("-fx-background-color:gray;-fx-background-radius:30;");
    }
    @FXML
    public void hoverCategory(MouseEvent mouseEvent) {

        categoryNumber.setStyle("-fx-border-color: #e4be00 ;-fx-border-width: 3;-fx-border-insets:-6;");
        categoryNumber.setTextFill(Color.valueOf("#e4be00"));
        categoryName.setFill(Color.valueOf("#e4be00"));
        categoryDeescription.setFill(Color.valueOf("#e4be00"));
        description.setTextFill(Color.valueOf("#e4be00"));
        nbrPlatesSold.setTextFill(Color.valueOf("#e4be00"));
        nbrText.setTextFill(Color.valueOf("#e4be00"));
        numberOfPaltes.setTextFill(Color.valueOf("#e4be00"));
        numberOfPlatesSold.setTextFill(Color.valueOf("#e4be00"));
        categoryBox.setStyle("-fx-background-color:#151928;-fx-background-radius:30;-fx-border-color:linear-gradient(from 0% 100% to 0% 0%, #e4be00 , #e4be00);-fx-border-radius:30;-fx-border-width:3px");
    }
    private  categorieListener myListener ;
    private  PlateCategory category;

    public static int i ;
    public void setData(PlateCategory plateCategory, categorieListener categorieListener) {
        this.category = plateCategory;
        this.myListener = categorieListener;
        int platesold =0;
        categoryNumber.setText("#"+i);
        categoryName.setText(plateCategory.getName());
        categoryDeescription.setText(plateCategory.getDescription());
        numberOfPaltes.setText(String.valueOf(plateCategory.getPlates().size()));
        for (Plate plate : plateCategory.getPlates()){
            platesold = platesold + plate.getNumberOfSales();
        }
        numberOfPlatesSold.setText(String.valueOf(platesold));
    }
}
