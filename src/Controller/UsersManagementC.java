package Controller;

import Entity.Users;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersManagementC implements Initializable {
    @FXML
    private TableView<Users> usersTable;

    @FXML
    private TableColumn<Users, Integer> colID;

    @FXML
    private TableColumn<Users, String> colUsername;

    @FXML
    private TableColumn<Users, String> colLastname;

    @FXML
    private TableColumn<Users, String> colRole;

    @FXML
    private TableColumn<Users, String> colJoinDate;

    @FXML
    private PieChart rolePie;

    @FXML
    private Label caption;

    @FXML
    private BarChart<?, ?> dailyUsersChart;

    @FXML
    private VBox vboxStats;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            refresh();
            loadUsersPieChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void refresh() throws SQLException {
        UserService us = new UserService();
        colUsername.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUsername.setCellFactory(TextFieldTableCell.forTableColumn());
        colLastname.setCellValueFactory(new PropertyValueFactory<>("afterName"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        colLastname.setCellFactory(TextFieldTableCell.forTableColumn());
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        //col_duree.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        //col_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorieformation_id"));
        //col_categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        usersTable.getItems().clear();
        final ObservableList<Users> myList = FXCollections.observableArrayList(UserService.readUsers());
        usersTable.setItems(myList);
    }

    private void loadUsersPieChart() {
        UserService x = new UserService();
        rolePie.setTitle("Users Roles");
        rolePie.setLabelLineLength(15);
        rolePie.setLabelsVisible(false);
        rolePie.setLegendSide(Side.BOTTOM);
        rolePie.setStartAngle(0);
        rolePie.setClockwise(false);
        rolePie.setData(x.userStats());
        for (final PieChart.Data data : rolePie.getData()) {

            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setText("Number of accounts : " + (int) data.getPieValue());
                        }
                    });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setText("");
                        }
                    });
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setText(data.toString());
                        }
                    });


        }
    }
}
