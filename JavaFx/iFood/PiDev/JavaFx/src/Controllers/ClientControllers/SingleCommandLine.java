package Controllers.ClientControllers;

import Entities.Command;
import Entities.CommandLine;
import Entities.Plate;
import Interfaces.commandLineListener;
import Services.CommandLineService;
import Services.CommandService;
import Services.UserSession;
import animatefx.animation.Shake;
import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleCommandLine {
    @FXML
    private AnchorPane panierBackground;
    @FXML
    private Circle imgProd;
    @FXML
    private Text nomProd;
    @FXML

    private Text prixPord;
    @FXML

    private TextField quantiteField;
    @FXML
    private Text totalLabel;
    private Interfaces.commandLineListener commandLineListener;
    private CommandLine commandline;
    private Plate plate;
    CommandLineService commandLineService = new CommandLineService();

    public void setData(CommandLine cl, commandLineListener commandLineListener) {
        this.commandline = cl;
        this.commandLineListener = commandLineListener;
        plate = commandline.getPlate();
        nomProd.setText(plate.getName());
        prixPord.setText(plate.getPrice() + " $");
        quantiteField.setText("" + Integer.toString(commandline.getQuantity()));
        totalLabel.setText("" + commandline.getTotal() + " $");
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        String url = "beefburger"+value+".jpg";
        Image userImage = new Image("Controllers/Images/"+url);
        imgProd.setFill(new ImagePattern(userImage));

    }

    @FXML
    private void click(MouseEvent event) {
        commandLineListener.onClickListener(commandline);

    }

    @FXML
    private void decrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) > 1) {
            int total = (Integer.parseInt(quantiteField.getText()) - 1);
            quantiteField.setText("" + total);
            totalLabel.setText("" + total * commandline.getPlate().getPrice() + "$");
            Command command = new Command();
            command = CommandService.currentCart(UserSession.getId());
            System.out.println(command);
            int id = command.getId();
            System.out.println("Command id " + id);
            CommandLineService cls = new CommandLineService();
            CommandLine cl = new CommandLine(Integer.parseInt(quantiteField.getText()), plate.getId(), id);
            cls.check_info(cl);
            Command commands = CommandService.currentCart(UserSession.getId());
            Text totals = new Text();
            totals.setText("Command Total : "+command.getTotal());
            ((GridPane) panierBackground.getParent()).add(totals,0,1);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cart Plate " + plate.getName() + " ");
            alert.setHeaderText("Quantity Error");
            alert.setContentText("Quantity must be greater than 1");
            alert.show();
        }

    }

    @FXML
    private void incrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) < commandline.getPlate().getQuantity()) {
            int total = (Integer.parseInt(quantiteField.getText()) + 1);
            quantiteField.setText("" + total);
            totalLabel.setText(total * commandline.getPlate().getPrice() + "$");
            Command command = new Command();
            command = CommandService.currentCart(UserSession.getId());
            System.out.println(command);
            int id = command.getId();
            CommandLineService cls = new CommandLineService();
            CommandLine cl = new CommandLine(Integer.parseInt(quantiteField.getText()), plate.getId(), id);
            cls.check_info(cl);
            Command commands = CommandService.currentCart(UserSession.getId());
            Text totals = new Text();
            totals.setText("Command Total : "+command.getTotal());
            ((GridPane) panierBackground.getParent()).add(totals,0,1);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite est maximal ");
            alert.show();
        }
    }

    @FXML
    private void supprimerPanier(ActionEvent event) {
        panierBackground.setStyle("-fx-background-color: red;");
        new Shake(panierBackground).play();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cart item");
        alert.setHeaderText("Delete");
        alert.setContentText("Delete plate " + commandline.getPlate().getName() + " from your cart?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            if (commandLineService.deleteCommandline(commandline.getId())) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getCause());
                }
                Command command = CommandService.currentCart(UserSession.getId());
                Text total = new Text();
                total.setText("Command Total : "+command.getTotal());
                ((GridPane) panierBackground.getParent()).getChildren().remove(0,1);
                ((GridPane) panierBackground.getParent()).add(total,0,1);
                ((GridPane) panierBackground.getParent()).getChildren().remove(panierBackground);
                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                aler.setTitle("Cart");
                aler.setHeaderText("Delete");
                aler.setContentText("Plate " + commandline.getPlate().getName() + " has been deleted!");
                aler.show();
            } else {
                panierBackground.setStyle("-fx-background-color: green;");
                new ZoomIn(panierBackground).play();
                Alert aler = new Alert(Alert.AlertType.ERROR);
                aler.setTitle("Cart");
                aler.setHeaderText("Delete");
                aler.setContentText("Delete has been canceled.");
                aler.show();
            }

        } else {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getCause());
            }

            panierBackground.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");

        }
    }
}
