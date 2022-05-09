package Services;

import Entities.CommandLine;
import Entities.Plate;
import Entities.Users;
import Utils.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandLineService {
    private static final String REQUETE_SELECT = "select * from commandline p inner join plate pr \n" +
            "where (id_user = ? and id_command = 0 and pr.id=p.id_plate) " ;
    private static final String REQUETE_INSERT = "INSERT INTO commandline (id_plate,id_user,quantity) VALUES(?,?,?) ";
    private static final String REQUETE_DELETE = "DELETE FROM commandline WHERE id = ?";
    private static final String REQUETE_UPDATE = "UPDATE commandline SET quantity = ? where id = ? ";
    private static final String REQUETE_SELECT_BYID = "SELECT * FROM commandline WHERE id = ? ";
    private static final String REQUETE_SELECT_IDPROD = "SELECT id_plate FROM commandline WHERE ID = ? ";
    private static final String REQUETE_SELECT_QUANTITE = "SELECT quantity FROM plate WHERE ID = ? ";
    private static final String REQUETTE_ID_PANIER = "SELECT ID FROM commandline WHERE ID_plate = ? and id_user = ? and id_command = 0";


    public boolean ajouterPanier(Plate produit, Users u, int Quantite){

            try {
                PreparedStatement pst =  DataSource.getInstance().getCnx().prepareStatement(REQUETE_INSERT);
                pst.setInt(1, produit.getId());
                pst.setInt(2, u.getId());
                pst.setInt(3, Quantite);
                pst.executeUpdate();
                System.out.println("Produit Ajouté au panier! ");
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.out.println("Erreur Produit deja dans panier ");
                return false;
            }


    }

    public boolean supprimerPanier(int id ){

        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(REQUETE_DELETE);
            pst.setInt(1, id);

            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Echec de suppression");
            return false;
        }
    }

    public ArrayList<CommandLine> afficherPanier(int id_user){
        ArrayList<CommandLine> list=new ArrayList();
        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(REQUETE_SELECT);
            pst.setInt(1, id_user);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                CommandLine p = new CommandLine();
                p.setId_client(id_user);
                p.setId(rs.getInt("id"));
                p.setId_plate(rs.getInt("id_plate"));
                p.setTotal(rs.getDouble("pr.total")*rs.getInt("p.quantity"));
                p.setQuantity(rs.getInt("p.quantity"));
                System.out.println(p);
                list.add(p);

            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
        return list;
    }

    public boolean modifierPanier(CommandLine p,int Quantite){

        try{
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(REQUETE_UPDATE);
            pst.setInt(1, Quantite);
            pst.setInt(2,p.getId() );
            pst.executeUpdate();
            System.out.println("Produit Modifier! ");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur ");
            return false;
        }



    }
    public CommandLine getData(int id){
        CommandLine p = new CommandLine();
        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(REQUETE_SELECT_BYID);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                p.setId_client(rs.getInt("id_user"));
                p.setId(rs.getInt("id"));
                p.setId_plate(rs.getInt("id_plate"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setId_command(rs.getInt("id_command"));
                return p;
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
        return p;
    }

    public int getQuantiteP(int id){
        Plate p = new Plate();
        int total = 0;
        int id_prod = 0;
        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(REQUETE_SELECT_IDPROD);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                id_prod = rs.getInt("id_plate");
            }
            PreparedStatement pst2 = DataSource.getInstance().getCnx().prepareStatement(REQUETE_SELECT_QUANTITE);
            pst2.setInt(1,id_prod);
            ResultSet RS = pst2.executeQuery();
            while(RS.next()){
                total = RS.getInt("quantity");
            }
            pst.close();
            rs.close();
            pst2.close();
            RS.close();
            return total;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
        return total;
    }

    public int getIdPanier(Plate p,Users u){
        Plate panier = new Plate();
        int id = 0;

        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(REQUETTE_ID_PANIER);
            pst.setInt(1, p.getId());
            pst.setInt(2, u.getId());

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }

            pst.close();
            rs.close();

            return id;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
        return id;
    }

}
