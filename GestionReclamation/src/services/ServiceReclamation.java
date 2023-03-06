package services;
import entities.Reclamation;
import utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceReclamation implements IService<Reclamation> {
    private final Connection cnx;
    private Object sp;
    public ServiceReclamation(){

        cnx = MaConnexion.getInstance().getCnx();
    }
    
    
  public Reclamation readBytype_reclamation(String type_reclamation) {
    Reclamation t = null;
        String requete = "SELECT * FROM reclamation WHERE type_reclamation=? ";
    try (PreparedStatement stmt = cnx.prepareStatement(requete)) {
        stmt.setString(1, type_reclamation);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {  Reclamation tr = new Reclamation();

            t=new Reclamation(rs.getInt("id_reclamation"),rs.getInt("id_user"),rs.getString("type_reclamation"),rs.getDate("date"),rs.getString("description"));
            
     }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
}
    @Override
    public void createOne(Reclamation t) throws SQLException {
        String req =   "INSERT INTO reclamation( id_reclamation , id_user , type_reclamation , date , description ) "+ "VALUES ('"+t.getId_reclamation()+"','"+t.getId_user()+"','"+t.getType_reclamation()+"','"+t.getDate()+"','"+t.getDescription()+"')";


        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Reclamation ajoute !");
    }

    @Override
    public void updateOne(Reclamation t, int id) throws SQLException {
        //String req =  " UPDATE reclamation SET id_user=?,type_reclamation=?,date=?,description=? WHERE id_reclamation="+id+"";
        String req =  " UPDATE reclamation SET description=? WHERE id_reclamation=?";
        PreparedStatement st = cnx.prepareStatement(req);


        st.setString(1, t.getDescription());
        st.setInt(2, id);

        st.executeUpdate();
        System.out.println("reclamation is updated successfully");
    }

    @Override
    public void deletOne(int id) throws SQLException {
        try {
            String req = "DELETE FROM Reclamation WHERE id_reclamation = ?";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("reclamation supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Reclamation> selectAll() throws SQLException {
        List<Reclamation> temp = new ArrayList<>();

        String req = "SELECT * FROM `reclamation`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Reclamation tr = new Reclamation();

            tr.setId_reclamation(rs.getInt("id_reclamation"));
            tr.setId_user(rs.getInt("id_user"));
            tr.setType_reclamation(rs.getString("type_reclamation"));
            tr.setDate(rs.getDate("date"));
            tr.setDescription(rs.getString("description"));



            temp.add(tr);

        }


        return temp;

    }
   
    
    
    
}
