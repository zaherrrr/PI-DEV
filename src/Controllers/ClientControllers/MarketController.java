package Controllers.ClientControllers;

import Entities.CommandLine;
import Entities.Plate;
import Entities.Users;
import Interfaces.produitInterface;
import Services.CommandLineService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MarketController {
private CommandLine panier;
private Plate produit;
private produitInterface produitListener;
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
    private VBox chosenFruitCard;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane panierMenu;

    @FXML
    private TextField quantiteField;

    @FXML
    private ScrollPane scroll;
    public void setData(Plate produit, produitInterface produitListener) {
        System.out.println(produit.getId());
        this.produit = produit;
        this.produitListener = produitListener;
        Image image = new Image("Images/" + produit.getImage());
        ImageProduit.setImage(image);
    }
    @FXML
    void Avis(ActionEvent event) {

    }

    @FXML
    void ajouterPack(ActionEvent event) {

    }

    @FXML
    void ajouterPanier(ActionEvent event) {
        Users user = new Users(1);
        Plate produit = new Plate(2,1, 4,"plat plat","pasta","pasta.jpg",22.5,2);

        int quantite = Integer.parseInt(quantiteField.getText());
        CommandLineService PS = new CommandLineService();

        if (PS.ajouterPanier(produit, user, quantite)) {
            System.out.println("Insertion effecté avec succes");

        } else {
            System.out.println("Pas d'insertion");
        }
    }

    @FXML
    void decrementerQuantite(ActionEvent event) {
        int total = (Integer.parseInt(quantiteField.getText()) - 1);
        quantiteField.setText("" + total);
        TotalLignePanier.setText("Total: " + total * panier.getPlate().getPrice());
    }

    @FXML
    void incrementerQuantite(ActionEvent event) {

            int total = (Integer.parseInt(quantiteField.getText()) + 1);
            quantiteField.setText("" + total);
            TotalLignePanier.setText("Total: " + total * panier.getPlate().getPrice());

    }

}
