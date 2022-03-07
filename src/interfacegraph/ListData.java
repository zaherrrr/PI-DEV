/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacegraph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.entites.PlateCategory;
import pi.entites.pi.services.PlateCategoryService;

/**
 *
 * @author bechi_msajrea
 */

public class ListData {
  private ObservableList<PlateCategory> catp = FXCollections.observableArrayList();

    public ListData() {
        PlateCategoryService pp=new PlateCategoryService();
        catp=(ObservableList<PlateCategory>) pp.displayAll();
    }
  
  
  
  public ObservableList<PlateCategory> getcat() {
		return catp;
	}
}
