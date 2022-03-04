/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.services.EvenementCRUD;
import edu.esprit.entities.Evenements; 
import edu.esprit.utils.MyConnection;
import edu.esprit.entities.Packs; 
import edu.esprit.services.PackCRUD; 
import edu.esprit.entities.PackLine; 
import edu.esprit.services.PackLineCRUD;
import java.io.FileOutputStream; 
import com.itextpdf.text.Document; 
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph; 
import com.itextpdf.text.pdf.PdfWriter; 
import java.io.*; 
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class MainClass {
    public static void main(String[] args) throws DocumentException, ParseException {
        //   MyConnection mc = new MyConnection();
        MyConnection mc1 = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        // System.out.println(mc.hashCode()+ " - "+mc2.hashCode());
  //    String sDate1="31/12/1998";  
   //    Date date=Date.valueOf(sDate1);     
    
    
    
    
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = format.parse("22028855");
        java.sql.Date sql1 = new java.sql.Date(parsed.getTime());
       Evenements e3 = new Evenements(8,sql1,"anniversaire",1,1);
       Evenements e4 = new Evenements(sql1,"nn",1,1);
    // SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
      //   java.util.Date parsed2 = format2.parse("20110211");
        //// java.sql.Date sql2 = new java.sql.Date(parsed.getTime());  
  //   Date d =new Date(Date.parse("2000/02/02"));
        EvenementCRUD pcd = new EvenementCRUD();
        
       
    
        
       //pcd.ajouterEvenement2(e4);
     // System.out.println(pcd.afficherEvenement());
//pcd.modifierEvenement(e3);
//pcd.supprimerEvenement(e3);

//pcd.supprimerEvenement(e4);
    PackCRUD pck = new PackCRUD();
Packs pck3 = new Packs ("sarr","pas de sale!!",22,1);
//pck.ajouterPack(pck3);

PackLineCRUD pL = new PackLineCRUD(); 
PackLine pL1 = new PackLine(1,14,1,1.2); 
pL.ajouterPackLine(pL1);

pck.supprimerPack(pck3);
/*pck1.setName("oo");
pck1.setDescription("gggg");
pck1.setPrice(11);
pck1.setId_owner(1);

*/

/*
Document document = new Document(); 
PdfWriter.getInstance(document, new FileOutputStream("hello.pdf"));
document.open();
Document //<editor-fold defaultstate="collapsed" desc="comment">
add
//</editor-fold>
= document.add(new Pragraph("le"));
document.close(); 
*/

    }
    
    
}
