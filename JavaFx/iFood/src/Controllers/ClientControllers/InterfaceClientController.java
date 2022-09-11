package Controllers.ClientControllers;

import Entities.*;
import Interfaces.*;
import Services.*;
import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class InterfaceClientController implements Initializable {

    @FXML
    public Label platePrice;

    @FXML
    private HBox categoriesBox;

    @FXML
    private GridPane categoryGrid;
    public Label platename;
    @FXML
    private Circle userImg;
    @FXML
    private VBox chosenPlate;
    @FXML
    private Text infoLabel;
    @FXML
    private VBox singleUserDynamicBox;
    @FXML
    private Label plateQuantity;
    @FXML
    private ImageView plateimg;
    @FXML
    private Label platecat;

    @FXML
    private ScrollPane scroll;
    @FXML
    public GridPane grid;
    private Parent fxml;
    @FXML
    private VBox TitleBox;
    @FXML
    private VBox dynamicInfo;
    private Users users= new Users();
    private final UserService userService = new UserService();

    private static Plate plate;
    private static PlateCategory category;
    public Plate getPlate(){
        return plate;
    }
    private final List<Plate> plates = new ArrayList<>();
    private final List<PlateCategory> categories = new ArrayList<>();
    private categorieListener categorieListener;
    private List<Plate> getData() {
        return PlateServices.readPlates();
    }
    private List<PlateCategory> getCategories(){
        return CategoryService.categoryData();
    }


    private void setChosenPlate(Plate pl) {
        plate=pl;
        PlateMenuButtons(plate);
        //titleBarView();
        platename.setText(InterfaceClientController.plate.getName());
        platePrice.setText("  "+InterfaceClientController.plate.getPrice()+" $");
        plateQuantity.setText("Qautntiy: "+pl.getQuantity());
        platecat.setText(""+pl.getId_category());
        new FadeIn(chosenPlate).play();
        infoLabel.setText(plate.getDescription()+" \n ");
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        String url = "beefburger"+value+".jpg";
        Image image = new Image("Controllers/Images/" + url);
        plateimg.setImage(image);

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
        chosenPlate.setStyle("-fx-background-color: #" + bgcolor + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MainMenu(0);
    }
    @FXML
    void plateView(MouseEvent event) {
        //plateView();
        categoryView();
    }
    @FXML
    void reclamationView(MouseEvent event) {
        reclamationView();
    }
    @FXML
    void cartView(MouseEvent event) {
        cartView();
    }
    @FXML
    void commandView(MouseEvent event){
        commandView();
    }
    @FXML
    void closePlateInfo(ActionEvent event) {
        chosenPlate.setVisible(false);
    }
    private Interfaces.commandLineListener commandLineListener;
    private static CommandLine commandLine;
    public void titleBarView(){
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/ClientViews/TitleBar.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        TitleBox.getChildren().removeAll();
        TitleBox.getChildren().setAll(fxml);
    }
    public void plateDetails(Plate plate) {
        VBox anchorPane = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/plateDetails.fxml"));
            anchorPane = fxmlLoader.load();
            PlateDetails plateController = fxmlLoader.getController();
            plateController.setPlate(plate);

        } catch (IOException e) {
            e.printStackTrace();
        }

        dynamicInfo.getChildren().removeAll();
        dynamicInfo.getChildren().setAll(anchorPane);
    }
    public void commandDetails(Command command,int index) {

       VBox anchorPane = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/commandDetails.fxml"));
            anchorPane = fxmlLoader.load();
            CommandDetails commandDetails = fxmlLoader.getController();
            commandDetails.setCommand(command,index);

        } catch (IOException e) {
            e.printStackTrace();
        }

        dynamicInfo.getChildren().removeAll();
        dynamicInfo.getChildren().setAll(anchorPane);
    }
    public void cartView(){
        grid.getChildren().clear();
        MainMenu(0);
        Command commandd = new Command();
        commandd = CommandService.currentCart(UserSession.getId());
        System.out.println("command total : "+commandd.getTotal());
        List <CommandLine> list = CommandLineService.findByCommand(commandd);
        if (list.size() == 0){
            try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/EmptyCommandLine.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    grid.add(anchorPane, 1, 1);
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            catch (IOException e) {
                e.getMessage();
            }
        }
        else {
            if (list.size() > 0) {
                commandLineListener = (CommandLine commandLin) -> {
                    commandLine = commandLin;
                };
            }
            int column = 0;
            int row = 2;
            try {
                Text total = new Text();
                total.setText("Command Total : "+commandd.getTotal());
                grid.add(total,0,1);
                Button checkout = new Button();
                checkout.setText("Checkout");
                checkout.setOnAction(actionEvent ->  {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/WebView.fxml"));
                    try {
                        AnchorPane anchorPane = fxmlLoader.load();
                        grid.getChildren().clear();
                        grid.add(anchorPane,1,1);
                        grid.setMargin(anchorPane,new Insets(20,20,500,20));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                checkout.setStyle("-fx-text-fill:white ;-fx-font-size:12bold;-fx-min-width:300px;-fx-min-height:50px;-fx-background-color:green;-fx-background-radius:30;");
                for (int i = 0; i < list.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/SingleCommandLine.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    SingleCommandLine myController = fxmlLoader.getController();
                    myController.setData(list.get(i), commandLineListener);
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
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
                grid.add(checkout,0,row+1);
                GridPane.setMargin(checkout, new Insets(0,100,0,250));
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
    public void categoryView(){
        categories.clear();
        Date start = new Date();
        categories.addAll(getCategories());
        Date end = new Date();
        System.out.println("date diff=" +(end.getTime()-start.getTime()));
        if (categories.size() > 0) {
            MainMenu(1);
            categorieListener = category -> {
            InterfaceClientController.category = category;
            plateView(category.getPlates());
            dynamicInfo.getChildren().clear();
            chosenPlate.setVisible(false);
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < categories.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/SingleCategory.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SingleCategory singleCategory = fxmlLoader.getController();
                SingleCategory.i = i+1;
                singleCategory.setData(categories.get(i), categorieListener);
                categoryGrid.add(anchorPane, column++, row); //(child,column,row)
                if (!(i ==categories.size()-1)) {
                Separator sp = new Separator();
                categoryGrid.add(sp, column++, row);
                categoryGrid.setMargin(sp, new Insets(-25,10,0,10));
                }
                //set grid width
                categoryGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                categoryGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                categoryGrid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                categoryGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                categoryGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                categoryGrid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(-25,5,0,5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void plateView(List<Plate> CategoryPlates){
        chosenPlate.setVisible(false);
        plates.clear();
        grid.getChildren().clear();
        plates.addAll(CategoryPlates);
        if(plates.size() == 0){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/EmptyCategory.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                grid.add(anchorPane, 1, 0);
                grid.setMargin(anchorPane, new Insets(300,0,0,50));

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            if (plates.size() > 0) {
                plateListener myListener = plate -> {
                    InterfaceClientController.plate = plate;
                    PlateMenuButtonsController.plate = plate;
                    plateDetails(plate);
                };

                int column = 0;
                int row = 1;
                try {
                    for (int i = 0; i < plates.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/SinglePlate.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        SinglePlateController plateController = fxmlLoader.getController();
                        plateController.setData(plates.get(i), myListener);
                        if (column == 8) {
                            column = 0;
                            row++;
                        }
                        grid.add(anchorPane, column++, row); //(child,column,row)
                        Separator sp = new Separator();
                        grid.add(sp, column++, row); //(child,column,row)
                        //set grid width
                        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);
                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);
                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }
    public void PlateMenuButtons(Plate plaaaate){
        if(plaaaate.getQuantity() == 0){
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/ClientViews/OutOfStock.fxml")));
                PlateMenuButtonsController.plate = plate;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(plaaaate.getQuantity()> 0){
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Views/ClientViews/PlateMenuButtons.fxml")));
            PlateMenuButtonsController.plate = plate;
        } catch (IOException e) {
            e.printStackTrace();
        } }
        singleUserDynamicBox.getChildren().removeAll();
        singleUserDynamicBox.getChildren().setAll(fxml);
    }
    private List<Command> commandes = new ArrayList<>();
    private List<Reclamation> reclamations = new ArrayList<>();
    private commandListener commandeInterface;
    private reclamationListener reclamationInterface;
    private static Command comm;
    private void commandView(){
        MainMenu(0);
        grid.getChildren().clear();
        commandes.clear();
        users =   UserService.getData(UserSession.getId());
        commandes.addAll(users.getCommands());
        if (commandes.size() > 0) {
            commandeInterface = (Command commande, int index) -> {
                System.out.println(commande.getId());
                comm = commande;
                commandDetails(commande,index);
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < commandes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/SingleCommand.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SingleCommand myController = fxmlLoader.getController();
                SingleCommand.i = i+1;
                myController.setData(commandes.get(i), commandeInterface);
                if (column == 4) {
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
    private static Reclamation reclama;
    private void reclamationView(){
        grid.getChildren().clear();
        reclamations.clear();
        MainMenu(0);
        users =   UserService.getData(UserSession.getId());
        System.out.println(users.getReclamations());
        reclamations.addAll(users.getReclamations());
        if (reclamations.size() > 0) {
            reclamationInterface = (Reclamation reclamation) -> {
                reclama = reclamation;
                //setCommandeDetails(commande);
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reclamations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../Views/ClientViews/OneReclamation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                OneReclamation myController = fxmlLoader.getController();
                OneReclamation.i = i+1;
                myController.setData(reclamations.get(i), reclamationInterface);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                Separator sp = new Separator();
                sp.setStyle("-fx-background-color: lightgrey;");
                sp.setPrefHeight(5);
                grid.add(sp, column - 1, row + 1); //(child,column,row)
                row++;
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
    @FXML
    public void openProfile(MouseEvent mouseEvent) {
        System.out.println(UserService.getData(UserSession.getId()));

    }
    private void MainMenu(int value){
        Image userImage = new Image("Controllers/Images/" + UserSession.getProfilepicture());
        userImg.setFill(new ImagePattern(userImage));
        chosenPlate.setVisible(false);
        users =   userService.getData(UserSession.getId());
        dynamicInfo.getChildren().clear();
        categoryGrid.getChildren().clear();
        grid.getChildren().clear();
        if (value==1){
            categoryGrid.setVisible(true);
            categoriesBox.setVisible(true);
            categoriesBox.setManaged(true);
        }
        else {
            categoryGrid.setVisible(false);
            categoriesBox.setVisible(false);
            categoriesBox.setManaged(false);
        }
    }
    @FXML
    public void packView(MouseEvent mouseEvent){

    }
}
