package Controllers.AdminControllers;

import Entities.Users;
import Services.MailerService;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class InterfaceAdminContact implements Initializable {
    private static Users u;
    public void setInfo(Users u){
        this.u=u;
        System.out.println("user setted");
        System.out.println(u);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField title;
    @FXML
    void ContactUser(ActionEvent event) {
        MailerService ms = new MailerService();
        ms.replyMail(u.getEmail(),u.getName(),description.getText(),title.getText());
    }
}
