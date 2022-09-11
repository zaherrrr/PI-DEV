package Main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.sql.SQLException;
import java.util.Objects;
// End of javafx imports
public class main extends Application {
    private static Stage pStage;
    public static Stage getPrimaryStage() {
        return pStage;
    }
    private void setPrimaryStage(Stage pStage) {
        main.pStage = pStage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/AuthenticationViews/AuthenticationInterface.fxml")));
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/ClientViews/WebView.fxml")));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Authentication");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });
    }
     private static HttpURLConnection connection;
    public static void main(String[] args) throws SQLException {
       launch(args);
	// Web Api cheatsheet
      /*  BufferedReader reader ;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
             connection = (HttpURLConnection) url.openConnection();
             connection.setRequestMethod("GET");
             connection.setConnectTimeout(5000);
             connection.setReadTimeout(5000);
             int code = connection.getResponseCode();
             if (code > 299 ){
                 reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                 while((line = reader.readLine()) != null){
                     responseContent.append(line);
                 }
                 reader.close();
             }
             else{
                 reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 while((line = reader.readLine()) != null){
                     responseContent.append(line);
                 }
                 reader.close();
             }
       } catch (MalformedURLException e) {
           throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            connection.disconnect();
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
