package Controllers.ClientControllers;

import Entities.CommandLine;
import Entities.Plate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AjoutPanierController {
 private Plate produit;
 private CommandLine panier;
    @FXML
    private ImageView ImageProduit;

    @FXML
    private Label NomProduit;

    @FXML
    private Label PrixProduit;

    @FXML
    private Label PromotionProduit;

    @FXML
    private Text TotalLignePanier;

    @FXML
    private AnchorPane panierMenu;

    @FXML
    private TextField quantiteField;

    @FXML
    void ajouterPanier(ActionEvent event) {

    }

    @FXML
    void decrementerQuantite(ActionEvent event) {
        int total = (Integer.parseInt(quantiteField.getText()) - 1);
        quantiteField.setText("" + total);
        TotalLignePanier.setText("Total: " + total * panier.getPlate().getPrice());

    }

    @FXML
    void incrementerQuantite(ActionEvent event) {
         {
            int total = (Integer.parseInt(quantiteField.getText()) + 1);
            quantiteField.setText("" + total);
             TotalLignePanier.setText("Total: " + total * panier.getPlate().getPrice());

        }
    }

}
