package Controllers.ClientControllers;

import Entities.CommandLine;
import Entities.Plate;
import Services.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class CommandCommandLine {
    @FXML
    private Label CommandLineTotal;
    @FXML
    private Label CommandLineQuantity;
    @FXML
    private Label PlateName;
    @FXML
    private VBox commandLineBox;
    @FXML
    private Circle plateImg;

    private CommandLine commandLine;
    private Plate plate;

    public void setData(CommandLine commandLine) {
        CommandLineTotal.setText(String.valueOf(commandLine.getTotal())+" $");
        CommandLineQuantity.setText(String.valueOf(commandLine.getQuantity()));
        PlateName.setText(commandLine.getPlate().getName());
        Image userImage = new Image("Controllers/Images/dessert-4.jpg");
        plateImg.setFill(new ImagePattern(userImage));
    }
}
