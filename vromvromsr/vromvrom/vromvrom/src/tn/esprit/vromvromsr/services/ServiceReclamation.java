package tn.esprit.vromvrom.services;

import tn.esprit.vromvrom.entities.Reclamation;
import tn.esprit.vromvrom.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceReclamation implements IService<Reclamation> {

    private final Connection cnx;
    private Object sp;
    public ServiceReclamation(){

        cnx = MaConnexion.getInstance().getCnx();
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
        String req =  " UPDATE reclamation SET id_user=?,type_reclamation=?,date=?,description=? WHERE id_reclamation="+id+"";

        PreparedStatement st = cnx.prepareStatement(req);

        st.setInt(1, t.getId_user());
        st.setString(2, t.getType_reclamation());
        st.setString(3, t.getDate());
        st.setString(4, t.getDescription());

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
            tr.setDate(rs.getString("date"));
            tr.setDescription(rs.getString("description"));



            temp.add(tr);

        }


        return temp;

    }
}
