package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;


public class Main extends Application {
    private static Stage pStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Views/UserDetails.fxml"));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root, 1315, 800);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }


    public static void main(String[] args) throws SQLException {

        launch(args);
        /*ListIterator<String> namesIterator
                = names.listIterator();
        while (namesIterator.hasNext()) {
            System.out.println(namesIterator.next());
        }*/

    }

    public void logout(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(primaryStage.getTitle());
        alert.setHeaderText("You're about to exit");
        alert.setContentText("Are you sure ? ");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You have exited");
            primaryStage.close();
        }
    }
}
