/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.Controller;

import edu.esprit.Interface.PlateInterface;
import edu.esprit.entities.Plate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PlatesInterfaceController {
    

    @FXML
    private ImageView img_p;
    @FXML
    private Text name_p;
    @FXML
    private Text price_p;
    @FXML
    private Text qnt_p;
 private Plate plate;
 private PlateInterface pi;
    @FXML
    private AnchorPane back_p;
    @FXML
    private void click(MouseEvent event) {
        pi.onClickListener(plate);
    }
    public void setData(Plate plate, PlateInterface pi) {
        this.plate = plate;
        this.pi = pi;
        name_p.setText(plate.getName_c());
        price_p.setText(Double.toString(plate.getPrice_c())+" DT");
        if(plate.getQnt_p()==0){
            back_p.setStyle("-fx-background-color: #ff1212;");
            qnt_p.setText("Hors Stock");
        }
        else{
            back_p.setStyle("-fx-background-color: #aeff3d;");
            qnt_p.setText("Stock: "+Integer.toString(plate.getQnt_p()));
        }
    /**
     * Initializes the controller class.
     */

    
}
}
