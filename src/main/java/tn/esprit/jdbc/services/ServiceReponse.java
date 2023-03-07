package tn.esprit.jdbc.services;

import tn.esprit.jdbc.entities.Reclamation;
import tn.esprit.jdbc.entities.Reponse;
import tn.esprit.jdbc.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceReponse implements IService<Reponse> {
    private final Connection cnx;
    private Object sp;
    public ServiceReponse(){

        cnx = MaConnexion.getInstance().getCnx();
    }
    @Override
    public void createOne(Reponse t) throws SQLException {
        String req =   "INSERT INTO reponse( id_reponse , id_reclamation , dateR , reponse ) "+ "VALUES ('"+t.getId_reponse()+"','"+t.getId_reclamation()+"','"+t.getDateR()+"','"+t.getReponse()+"')";


        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Reponse ajoute !");
    }

    @Override
    public void updateOne(Reponse t, int id) throws SQLException {
        //String req =  " UPDATE reponse SET id_reclamation=?,dateR=?,reponse=? WHERE id_reponse="+id+"";
        String req = " UPDATE reponse SET reponse=? WHERE id_reponse=?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, t.getReponse());
        st.setInt(2, id);

        st.executeUpdate();
        System.out.println("reponse is updated successfully");
    }

    @Override
    public Reponse findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void deletOne(int id) throws SQLException {
        try {
            String req = "DELETE FROM Reponse WHERE id_reponse = ?";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("reponse supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Reponse> selectAll() throws SQLException {
        List<Reponse> temp = new ArrayList<>();

        String req = "SELECT * FROM `reponse`";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Reponse tr = new Reponse();


            tr.setId_reponse(rs.getInt("id_reponse"));
            tr.setId_reclamation(rs.getInt("id_reclamation"));
            tr.setDateR(rs.getDate("dateR"));
            tr.setReponse(rs.getString("reponse"));



            temp.add(tr);

        }


        return temp;

    }
    public String getEmail() {

        String requete = "SELECT * " +
                "FROM reclamation " +
                "INNER JOIN user ON user.id = reclamation.id_user " +
                "INNER JOIN reponse ON reclamation.id_reclamation = reponse.id_reclamation";
        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(requete)) {
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                return email;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return " ";
    }





}

