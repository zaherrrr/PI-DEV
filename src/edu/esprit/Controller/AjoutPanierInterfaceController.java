/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controller;

import edu.esprit.Interface.PlateInterface;
import edu.esprit.entities.Plate;
import edu.esprit.entities.Users;
import edu.esprit.services.LineCommandeCRUD;
import edu.esprit.services.PlateCRUD;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutPanierInterfaceController implements Initializable {

    @FXML
    private TextField qnt_p;
     @FXML
    private ImageView img_p;
       @FXML
    private Label PromotionPlate;
         @FXML
    private Label name_p;
    @FXML
    private Label price_p;
    @FXML
    private AnchorPane Panmenu;
  

   
  


    private Plate plate;
    private PlateInterface pl;

    public void setPanierMenu(Plate p) {
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
        price_p.setText(Double.toString(p.getPrice_c()));
        Panmenu.setStyle("-fx-background-color: #" + bgcolor + ";\n"
                + "    -fx-background-radius: 30;");
    }

    public void setData(Plate plate, PlateInterface pl) {
        System.out.println(plate.getId_p());
        this.plate = plate;
        this.pl = pl;
        Image image = new Image("edu.esprit.Image/" + plate.getImage_c());
        img_p.setImage(image);
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void decrementerQuantite(ActionEvent event) {
    }

    @FXML
    private void incrementerQuantite(ActionEvent event) {
    }

    @FXML
    private void ajouterPanier(ActionEvent event) {
        Users user = new Users(1,"Azzouz");
        Plate plate = new Plate( 20, 100);

        int quantite = Integer.parseInt(qnt_p.getText());
        LineCommandeCRUD PC = new LineCommandeCRUD();
        while (quantite > plate.getQnt_p()) {
            System.out.println("Quantite Insuffisante");
        }
        if (PC.ajouterCommandLine(plate, user, quantite)) {
            System.out.println("Insertion effecté avec succes");

        } else {
            System.out.println("Pas d'insertion");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pl = new PlateInterface() {
            @Override
            public void onClickListener(Plate plate) {
                setPanierMenu(plate);
            }
        };

    }
}
