/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacegraph;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pi.entites.pi.services.PlateCategoryService;

/**
 * FXML Controller class
 *
 * @author bechi_msajrea
 */
public class Menu_CategoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Inserer_Category(ActionEvent event) throws IOException {
     
        Parent affichescene = FXMLLoader.load(getClass().getResource("Menu_Inserer.fxml"));
        Scene affichage = new Scene(affichescene);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(affichage);
        appstage.show();

    }

    @FXML
    private void Afficher_Category(ActionEvent event) {
    }

    @FXML
    private void Modifier_Category(ActionEvent event) throws IOException {
        Parent affichescene = FXMLLoader.load(getClass().getResource("Menu_Update.fxml"));
        Scene affichage = new Scene(affichescene);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(affichage);
        appstage.show();
    }

    @FXML
    private void Supprimer_Category(ActionEvent event) {
    }

    @FXML
    private void Chercher_Category(ActionEvent event) {
    }
    
}
