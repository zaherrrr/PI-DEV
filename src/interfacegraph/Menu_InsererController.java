/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacegraph;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pi.entites.PlateCategory;
import pi.entites.pi.services.PlateCategoryService;

/**
 * FXML Controller class
 *
 * @author bechi_msajrea
 */
public class Menu_InsererController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Valider_Insert(ActionEvent event) {
        String name= tfName.getText();
        String description= tfDescription.getText(); 
        
       PlateCategory pp =new PlateCategory (name,description);
       PlateCategoryService pcs =new PlateCategoryService ();
       pcs.insertPlateCategory(pp); 
    }
    
}
