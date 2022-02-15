package main;

import Entity.*;
import Services.CommandLineService;
import Services.CommandService;
import Services.PlateCategoryService;
import Services.ReclamationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main extends Application {

    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root,600,500);
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


    public static void main(String[] args) {
        //launch(args);
        PlateCategoryService pcs =  new PlateCategoryService();
        //Select one
        //System.out.println(pcs.plateInfo(1));
        // Update
       /* PlateCategory pc = new PlateCategory(1,"CatEdit","descruption");
        pcs.updatePlateCategory(pc);*/
      /*  PlateCategory pc = new PlateCategory("Testt","descrption",0);
        boolean test = pcs.insertPlateCategory(pc);
        if (test){
            System.out.println("Cat inserted");
        }
        else {
            System.out.println("Cat not inserteed");
        }*/
        // SELECT
        /*
        List<PlateCategory> Categories = pcs.readPlateCategory();
        ListIterator<PlateCategory> CatIterator = Categories.listIterator();
        while (CatIterator.hasNext()) {
            System.out.println(CatIterator.next());
        }*/
        // DELETE
        //pcs.deleteCategoryplate(2);
        /*ReclamationService rc = new ReclamationService();
        List<Reclamation> mylist = rc.readReclamationAdmin();
        if(mylist.isEmpty()){
            System.out.println("There's no reclamation mr admin");
        }
        else{
            System.out.println(mylist);
        }*/
        Users u = new Users(4);


        CommandLine c = new CommandLine(2,2,1,1,1,0);
        CommandLineService cs = new CommandLineService();
        boolean test = cs.updateCommandline(c);
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
