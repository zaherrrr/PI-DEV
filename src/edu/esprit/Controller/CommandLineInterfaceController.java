/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controller;

import edu.esprit.Interface.CommandLineInterface;
import edu.esprit.entities.CommandLine;
import edu.esprit.entities.Plate;
import edu.esprit.services.LineCommandeCRUD;
import edu.esprit.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CommandLineInterfaceController {

    @FXML
    private ImageView img_p;
    @FXML
    private Text nom_p;
    @FXML
    private Text price_p;
    @FXML
    private Button delete_p;
    @FXML
    private Button pricem;
    @FXML
    private Button priceadd;
    @FXML
    private TextField qnt_p;
    @FXML
    private Text total_p;
    private CommandLineInterface ci;
    private CommandLine c1;
    private Plate plate;
    LineCommandeCRUD cc=new LineCommandeCRUD();
@FXML 
      public void setData(CommandLine cl,CommandLineInterface ci){
         this.c1=c1;
         this.ci=ci;
         c1.setPlate(c1.getId_p());
         plate=c1.getPlate();
         nom_p.setText(plate.getName_c());
         price_p.setText(Double.toString(plate.getPrice_c())+" DT");
         qnt_p.setText(Integer.toString(c1.getQuantityPLT()));
         total_p.setText(Double.toString(plate.getPrice_c()*c1.getQuantityPLT())+" DT");
        

}

    @FXML
    private void deletePlate(ActionEvent event) {
    } 

    @FXML
    private void Lprice(ActionEvent event) {
            if (Integer.parseInt(qnt_p.getText()) > 1) {
            int total = (Integer.parseInt(qnt_p.getText()) - 1);
            qnt_p.setText("" + total);
            total_p.setText("Total: " + total *c1.getPlate().getPrice_c() );
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite doit etre plus que 1");
            alert.show();
        }
    }

    @FXML
    private void addPrice(ActionEvent event) {
            if (Integer.parseInt(qnt_p.getText()) < c1.getPlate().getQnt_p()) {
            int total = (Integer.parseInt(qnt_p.getText()) + 1);
            qnt_p.setText("" + total);
            total_p.setText("Total: " + total * c1.getPlate().getPrice_c());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite est maximal ");
            alert.show();
        }
        
    }



    /**
     * Initializes the controller class.
     */
 

    
}
