package Controllers.ClientControllers;

import Entities.Command;
import Entities.Reclamation;
import Interfaces.commandListener;
import Interfaces.plateListener;
import Interfaces.reclamationListener;
import Services.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class OneReclamation {
    @FXML
    private AnchorPane reclamationBackground;

    @FXML
    private Text reclamationDescription;

    @FXML
    private Label reclamationSujet;

    @FXML
    private Circle userImg;
    private reclamationListener myListener;
    @FXML
    void click(MouseEvent event) {
        myListener.onClickListener(reclama);
    }

    @FXML
    void deleteReclamation(ActionEvent event) {

    }
    private Interfaces.reclamationListener reclamationListener;
    private static Reclamation reclama;
    public static int i ;
    public void setData(Reclamation reclamation, reclamationListener myListener) {
       this.reclama= reclamation;
        this.reclamationListener = myListener;
        Image userImage = new Image("Controllers/Images/" + UserSession.getProfilepicture());
        userImg.setFill(new ImagePattern(userImage));

        if(reclamation.getStatus() == 1){
            reclamationBackground.setStyle("-fx-background-color: green;");

        }
        else {
            reclamationBackground.setStyle("-fx-background-color: #ff1212;");
        }
        reclamationDescription.setText(reclamation.getDescription());
        reclamationSujet.setText(reclamation.getSujet());

    }
}
