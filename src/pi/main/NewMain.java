/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import pi.entites.PlateCategory;
import pi.entites.pi.services.PlateCategoryService;
import pi.utilis.Myconnexion;
import java.util.ListIterator;

/**
 *
 * @author bechi_msajrea
 */
public class NewMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Myconnexion mc = Myconnexion.getInstance();
        // TODO code application logic here<
        PlateCategoryService pp = new PlateCategoryService();
        PlateCategory h = new PlateCategory("lllll","jjjjjjj");
        PlateCategory a = new PlateCategory("222","aaajjjj");
        PlateCategory b = new PlateCategory("l33","jjjkkj");
        
         pp.insertPlateCategory(a);
          pp.insertPlateCategory(b);
          
         List<PlateCategory> listp=new ArrayList<>();
         listp=pp.displayAll();
         for (int i = 0; i < listp.size(); i++) {
             System.out.println(listp.get(i));
        }
 
        
        
}
    
    }
    
    