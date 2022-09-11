package Controllers.ClientControllers;

import Entities.Command;
import Interfaces.commandListener;
import animatefx.animation.BounceIn;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SingleCommand {
    @FXML
    private Text commandNumber;

    @FXML
    private Text commandStatus;

    @FXML
    private Text commandTotal;

    @FXML
    private AnchorPane commandeBackground;

    @FXML
    void click(MouseEvent event) {
        new BounceIn(commandeBackground).play();
        commandeListener.onClickListener(commande, commande.getNumber());
    }
    private commandListener commandeListener ;
    private Command commande;

    public static int i ;
    public void setData(Command commande, commandListener myListener) {
        this.commande = commande;
        this.commandeListener = myListener;
        this.commande.setNumber(i);
        if(commande.getStatus() == 1){
            commandeBackground.setStyle("-fx-background-color: green;");
            commandTotal.setText(commande.getTotal()+"$");
            commandStatus.setText("Command");
        }
        else {
            commandeBackground.setStyle("-fx-background-color: #ff1212;");
            commandTotal.setText(commande.getTotal()+"$");
            commandStatus.setText("Cart");
        }

        commandNumber.setText("#"+i);
    }
}
