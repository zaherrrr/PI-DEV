package Controllers.ClientControllers;

import Entities.Command;
import Entities.Plate;
import Services.UserSession;
import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Random;

public class CommandDetails {
    @FXML
    private Label commandDate;

    @FXML
    private Label commandTitle;
    @FXML
    private Label commandTotal;

    @FXML
    private GridPane grid;
    @FXML
    private VBox commandDetailsBox;
    private static Command command;
    private static int id;

    @FXML
    void closeCommand(ActionEvent event) {
        ((VBox) commandDetailsBox.getParent()).getChildren().remove(commandDetailsBox);
    }
    public  void setCommand(Command com, int i){
        this.command = com;
        this.id = i;
        commandTitle.setText("COMMAND "+i);
        commandDate.setText(String.valueOf(command.getDatecommand()));
        commandTotal.setText("TOTAL "+command.getTotal()+" $");
        new FadeIn(commandDetailsBox).play();
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        int column = 0;
        int row = 1;
        if (command.getCommandLines().size()>0) {
            try {
                for (int j = 0; j < command.getCommandLines().size(); j++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/CommandCommandLine.fxml"));
                    VBox anchorPane = fxmlLoader.load();
                    CommandCommandLine myController = fxmlLoader.getController();
                    myController.setData(command.getCommandLines().get(j));
                    if (column == 1) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row);
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(15));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String bgcolor;

        switch (value) {
            case 1:
                bgcolor = "FFB605";
            case 2:
                bgcolor = "FFB605";
                break;
            case 3:
                bgcolor = "80080C";
                break;
            case 4:
                bgcolor = "FB5D03";
                break;
            case 5:
                bgcolor = "22371D";
                break;
            case 6:
                bgcolor = "291D36";
                break;
            case 7:
                bgcolor = "F16C31";
                break;
            case 8:
                bgcolor = "A7745B";
                break;
            case 9:
                bgcolor = "6A7324";
                break;
            case 10:
                bgcolor = "ABB605";
                break;
            default:
                bgcolor = "5F060E";
        }
        commandDetailsBox.setStyle("-fx-background-color: #e4be00 "+";\n");
    }
}
