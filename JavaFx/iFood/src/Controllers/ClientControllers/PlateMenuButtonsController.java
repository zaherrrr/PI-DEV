package Controllers.ClientControllers;


import Entities.Command;
import Entities.CommandLine;
import Entities.Plate;

import Services.CommandLineService;
import Services.CommandService;
import Services.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PlateMenuButtonsController implements Initializable {

     static Plate plate;
    public static void setInfo(Plate p){
        plate = p;
       // totalLignePanier.setText("Total: "+plate.getPrice()+" $.");
    }
    Parent fxml;

    public boolean checkInfo(Plate plate){
        if(plate.getQuantity() == 0){
            btnAddtoCart.setText("Out of Stock.");
            btnAddtoCart.setDisable(true);
            quantiteField.setText("--");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Plate "+plate.getName());
            alert.setHeaderText("QUANTITY ERROR");
            alert.setContentText("Plate "+plate.getName()+" is out of stock.");
            alert.show();
            return false;
        }
        return true;
    }

    @FXML
    private VBox buttonDynamicBox;
    @FXML
    private Button btnAddtoCart;
    @FXML
    private TextField quantiteField;

    @FXML
    void AddToPack(ActionEvent event) {

    }

    @FXML
    void addToCart(ActionEvent event) {
        if (checkInfo(plate)) {
            Command command = new Command();
            command = CommandService.currentCart(UserSession.getId());
            System.out.println(command);
            int id = command.getId();
            System.out.println("Command id "+id);
            CommandLineService cls = new CommandLineService();
            CommandLine cl = new CommandLine(Integer.parseInt(quantiteField.getText()),plate.getId(),id);
            cls.check_info(cl);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cart");
            alert.setHeaderText("Plate "+plate.getName());
            alert.setContentText(Integer.parseInt(quantiteField.getText()) +" x Plates of "+plate.getName()+" has been added to your cart.");
            alert.show();
        }

    }




    @FXML
    private Text totalLignePanier;

    @FXML
    void decrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) > 1) {
            int total = (Integer.parseInt(quantiteField.getText()) - 1);
            quantiteField.setText("" + total);
            totalLignePanier.setText("Total: " + total*plate.getPrice());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ADD TO CART");
            alert.setHeaderText("QUANTITY ERROR");
            alert.setContentText("QUANTITY MUST BE GREATER THAN 0 ");
            alert.show();
        }
    }

    @FXML
    void incrementerQuantite(ActionEvent event) {
        int price = plate.getPrice();
        System.out.println(price);
        if ( checkInfo(plate)){

            if (Integer.parseInt(quantiteField.getText()) < plate.getQuantity()) {
                int total = (Integer.parseInt(quantiteField.getText()) + 1);
                quantiteField.setText("" + total);
                totalLignePanier.setText("Total: " + total * price + " $.");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD TO CART");
                alert.setHeaderText("QUANTITY ERROR");
                alert.setContentText("QUANTITY MAX");
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
