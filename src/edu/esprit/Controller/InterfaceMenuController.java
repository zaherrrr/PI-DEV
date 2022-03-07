 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controller;

import edu.esprit.Interface.CommandLineInterface;
import edu.esprit.Interface.PlateInterface;
import edu.esprit.entities.CommandLine;
import edu.esprit.entities.Plate;
import edu.esprit.entities.Users;
import edu.esprit.services.CommandeCRUD;
import edu.esprit.services.LineCommandeCRUD;
import edu.esprit.services.PlateCRUD;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import animatefx.animation.ZoomIn;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InterfaceMenuController implements Initializable {

    @FXML
    private AnchorPane interfacePlats;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane interfacePaniers;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private GridPane grid1;
    @FXML
    private Button btnValider;
    @FXML
    private VBox vboxElement;
    @FXML
    private AnchorPane anchorElement;
    @FXML
    private VBox vboxPlatmenu;
    @FXML
    private Label PromotionProduit;
    @FXML
    private Label NomProduit;
    @FXML
    private Label PrixProduit;
    @FXML
    private TextField quantiteField;
    @FXML
    private VBox vboxCommandes;
    @FXML
    private Label PromotionProduit1;
    @FXML
    private Label NomProduit1;
    @FXML
    private Label PrixProduit1;
    @FXML
    private TextField quantiteField1;
      private Parent fxml;
       private List<Plate> produits = new ArrayList<>();
    private List<CommandLine> paniers = new ArrayList<>();
     private Image image;
    private PlateInterface pi;
    private CommandLineInterface panierInterface;
    @FXML
    private Text panierLabel;
    @FXML
    private ImageView ImageProduit;
    @FXML
    private Text totalLignePanier;
    @FXML
    private ImageView ImageProduit1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
  private void afficherPlats(ActionEvent event) {
        interfacePaniers.setVisible(false);
        vboxPlatmenu.setVisible(false);
        listDesPlats();
        interfacePlats.setVisible(true);
    }

    public void setPorduitDetails(Plate p) {
        if (pl.getQnt_p() == 0) {
            quantiteField.setText("0");
            totalLignePanier.setText("");
            PromotionProduit.setStyle("-fx-background-color: red ;");
            PromotionProduit.setText("Hors Stock");
        } else {
            quantiteField.setText("1");
            totalLignePanier.setText("Total: " + pl.getPrice_c());
            PromotionProduit.setStyle("-fx-background-color: green ;");
            PromotionProduit.setText("Disponible");
        }
        String bgcolor;
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        switch (value) {
            case 1:
                bgcolor = "FAB605";
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
        PrixProduit.setText(Double.toString(p.getPrice_c()));

        anchorElement.setStyle("-fx-background-color: #" + bgcolor + ";\n"
                + "    -fx-background-radius: 30;");
        vboxElement.setVisible(true);
        vboxPlatmenu.setVisible(true);
        anchorElement.setVisible(true);
        

    }

    

    @FXML
    private void afficherPanier(ActionEvent event) {
        Users u = new Users(1,"Azzouz");
        listDesPaniers(u);
        anchorElement.setVisible(false);
        interfacePlats.setVisible(false);
        vboxPlatmenu.setVisible(false);
        interfacePaniers.setVisible(true);
    }

    @FXML
    private void afficherCommandes(ActionEvent event) {
       
    }

    private List<Plate> getData() {
        PlateCRUD ps = new PlateCRUD();
        List<Plate> plates = ps.afficherPlate();
        return plates;
    }

    private List<CommandLine> getDataPanier(Users u) {
        LineCommandeCRUD ps = new LineCommandeCRUD();
        List<CommandLine> paniers = ps.afficherCommandLine(u.getId());
        return paniers;
    }
    private static Plate pl;
    private static CommandLine cl;

    private void listDesPaniers(Users u) {
        paniers.addAll(getDataPanier(u));
        if (paniers.size() > 0) {
            panierInterface = (CommandLine panier) -> {
                cl = panier;
                System.out.println(cl);
            };
        
       
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < paniers.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../edu.esprit.GUI/CommandLineInterface.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CommandLineInterfaceController myController = fxmlLoader.getController();
                myController.setData(paniers.get(i), panierInterface);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grid1.add(anchorPane, column++, row);
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);
                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.getMessage();
        }}
         else {
              btnValider.setText("Panier Vide");
            panierLabel.setText("Panier Vide");
        }
    }
    private void listDesPlats() {
        produits.addAll(getData());
        if (produits.size() > 0) {

            pi = (Plate produit) -> {
                pl = produit;
                setPorduitDetails(produit);
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../edu.esprit.GUI/PlatesInterface.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                PlatesInterfaceController myController = fxmlLoader.getController();
                myController.setData(produits.get(i), pi);
                if (column == 4) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void decrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) > 1) {
            int total = (Integer.parseInt(quantiteField.getText()) - 1);
            quantiteField.setText("" + total);
            totalLignePanier.setText("Total: " + total * pl.getPrice_c());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite doit etre plus que 1");
            alert.show();
        }

    }

    @FXML
    private void incrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) < pl.getQnt_p()) {;
            int total = (Integer.parseInt(quantiteField.getText()) + 1);
            quantiteField.setText("" + total);
            totalLignePanier.setText("Total: " + total * pl.getPrice_c());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite est maximal ");
            alert.show();
        }
    }

    @FXML
    private void ajouterPanier(ActionEvent event) {
      
        LineCommandeCRUD ps = new LineCommandeCRUD();
        Users u = new Users(1, "azzouz");
        int Quantite = Integer.parseInt(quantiteField.getText());
        if (Quantite < pl.getQnt_p()) {
            boolean test = ps.ajouterCommandLine(pl, u, Quantite);
            if (test) {
                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                aler.setTitle("Panier");
                aler.setHeaderText("Plat ajout");
                aler.setContentText("Plat Ajouté au panier");
                aler.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Panier");
                alert.setHeaderText("Produit déja existé dans votre Panier");
                alert.setContentText("Mettre a jours la quantite? ");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    CommandLine p = new CommandLine(ps.getIdPanier(pl, u));
                    ps.modifierCommandLine(p, Quantite);
                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                    aler.setTitle("Achat du Plat");
                    aler.setHeaderText("Modification Du Quantité");
                    aler.setContentText("La Quantite est modifié");
                    aler.show();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite est insuffisante");
            alert.show();

        }
    }

    @FXML
    private void validerPanier(ActionEvent event) {
        Users u = new Users(1,"Azzouz");
        List<CommandLine> p = getDataPanier(u);
        if(p.size()>0){ 
        CommandeCRUD cs = new CommandeCRUD();
        if(cs.ajouterCommande(new Users(1,"Azzouz"))) {
            listDesPaniers(u);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Panier");
            alert.setHeaderText("Validation");
            alert.setContentText("Votre Panier est maintenant une commande !");
            alert.show();
        }
        else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Panier");
            alert.setHeaderText("Validation");
            alert.setContentText("Erreur!");
            alert.show();
        }
    }
        else {
            btnValider.setText("Panier Vide");
            panierLabel.setText("Panier Vide");
           new ZoomIn(panierLabel).play();
        }
        
       
        
    }

    
}
