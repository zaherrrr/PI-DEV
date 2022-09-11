package Controllers.ClientControllers;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class WebViewController implements Initializable {
    public Button validateLocationBtn;
    private String Lattitude;
    private String Longitude;
    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final WebEngine web = webView.getEngine();
        web.setJavaScriptEnabled(true);
        String editor = WebViewController.class.getResource("DeliveryInterface.html").toExternalForm();
        web.load(editor);
        web.getLoadWorker().stateProperty().addListener((ov, o, n) -> {
            if ( Worker.State.SUCCEEDED == n) {
                web.setOnStatusChanged(webEvent -> {
                    onValueChange(webEvent.getData());
                });
            }
        });

    }
    private void onValueChange(String data){
        String[] newdata = data.split(",");
        Lattitude = newdata[0];
        Longitude = newdata[1];
        System.out.println("Lattitude : " +Lattitude);
        System.out.println("Longitude : " +Longitude);
        validateLocationBtn.setDisable(false);
    }

    @FXML
    public void validateLocation(ActionEvent actionEvent) {
    }
}
