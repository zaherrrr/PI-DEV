/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacegraph;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pi.entites.PlateCategory;
import pi.entites.pi.services.PlateCategoryService;

/**
 * FXML Controller class
 *
 * @author bechi_msajrea
 */
public class Menu_UpdateController implements Initializable {

    @FXML
    private TableColumn<PlateCategory, String> Name;
    @FXML
    private TableColumn<PlateCategory, String> Descrip;

    @FXML
    private TextField Tfname;
    @FXML
    private TableColumn<PlateCategory, Integer> ID;
    @FXML
    private TableView<PlateCategory> Table;

    ObservableList<PlateCategory> CatList = FXCollections.observableArrayList();

    private ListData categories = new ListData();
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Table.setItems(categories.getcat());
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        System.out.print("ajout");

        //Rechercher
        CatList.addAll(categories.getcat());
        FilteredList<PlateCategory> filtereddata = new FilteredList<>(CatList, b -> true);

        Tfname.textProperty().addListener((observable, oldValue, newValue) -> {
            Tfname.textProperty().addListener((observables, oldVal, newVal) -> {
                filtereddata.setPredicate(cat -> {
                    // If filter text is empty, display all persons.

                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (cat.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches cin.
                    } else if (cat.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return false; // Does not match.
                    }
                });
            });

        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<PlateCategory> sortedData = new SortedList<>(filtereddata);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Table.setItems(sortedData);

        //end Recherche
        //supprimer
        delete.setOnAction(event -> {

            PlateCategory p = Table.getSelectionModel().getSelectedItem();
            PlateCategoryService fo = PlateCategoryService.getInstance().getInstance();
            int idd = p.getId();
            fo.deleteCategoryplate(idd);
            Table.getSelectionModel().getSelectedItems().forEach(this.CatList::remove);
            //apprenants.getItems().removeAll(apprenants.getSelectionModel().getSelectedItem());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("plateCategorie supprimee!");
            alert.show();

        });
        //endSupp

    }

    private void Tfname(ActionEvent event) {
        String name = Tfname.getText();
    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void Search(ActionEvent event) {
    }

}
